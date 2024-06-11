package org.farpost.farpostapi2.facades;

import lombok.RequiredArgsConstructor;
import org.farpost.farpostapi2.dto.bot_dto.*;
import org.farpost.farpostapi2.dto.timeweb_dto.TimewebVpsResponseDto;
import org.farpost.farpostapi2.enitities.Ad;
import org.farpost.farpostapi2.enitities.Bot;
import org.farpost.farpostapi2.enitities.Client;
import org.farpost.farpostapi2.enitities.Vps;
import org.farpost.farpostapi2.exceptions.system.BadRequestException;
import org.farpost.farpostapi2.exceptions.system.ElementAlreadyExist;
import org.farpost.farpostapi2.exceptions.system.ElementNotFoundException;
import org.farpost.farpostapi2.facades.utils.FacadeUtils;
import org.farpost.farpostapi2.facades.utils.RequestVariables;
import org.farpost.farpostapi2.facades.utils.TimewebRequests;
import org.farpost.farpostapi2.repositories.AdRepository;
import org.farpost.farpostapi2.repositories.BotRepository;
import org.farpost.farpostapi2.repositories.VpsRepository;
import org.farpost.farpostapi2.services.RestService;
import org.farpost.farpostapi2.services.SshService;
import org.farpost.farpostapi2.services.VpsParser;
import org.farpost.farpostapi2.services.utils.SshCommands;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class BotFacade {

    private final RestService restService;
    private final FacadeUtils facadeUtils;
    private final VpsParser vpsParser;
    private final SshService sshService;
    private final BotRepository botRepository;
    private final AdRepository adRepository;
    private final VpsRepository vpsRepository;

    @Value("${timeweb.access_token}")
    private String accessToken;

    @Transactional
    public SuccessBotCreateDto createNewBot(BotDto botDto) {
            checkOnExistBot(botDto.getName());
            Vps vps = facadeUtils.checkAvailability(botDto.getVpsId(), Vps.class, true);
            TimewebVpsResponseDto timewebVpsResponseDto = getVpsParams(vps.getTimewebId());
            String jsonTouchCommand = UriComponentsBuilder.fromUriString(SshCommands.JSON_TOUCH).buildAndExpand(Map.of("bot_name", botDto.getName())).toString();
            if (!vps.getBots().isEmpty() || vps.getIsActive()) {
                CompletableFuture.runAsync(() -> sshService.execCommands(timewebVpsResponseDto, List.of(SshCommands.ENTER_IN_BOT_USER, SshCommands.CD_JSONS, jsonTouchCommand)));
                botRepository.save(new Bot(botDto, vps));
                return new SuccessBotCreateDto("Bot created");
            }
            else {
                CompletableFuture.runAsync(() -> sshService.execCommands(timewebVpsResponseDto, List.of(SshCommands.ENTER_IN_BOT_USER, SshCommands.JSON_MKDIR, jsonTouchCommand, SshCommands.CD_FARPOST, SshCommands.CHMOD_CONSOLE,
                        SshCommands.CHMOD_START, SshCommands.DEF_DISABLE_IPV6, SshCommands.IO_DISABLE_IPV6, SshCommands.ALL_DISABLE_IPV6, SshCommands.UPDATE, SshCommands.START_SH)));
                vps.setIsActive(true);
                vpsRepository.save(vps);
                botRepository.save(new Bot(botDto, vps));
                return new SuccessBotCreateDto();
            }
    }


    @Transactional
    public SuccessAdCreateDto addAdInBot(List<BotAdDto> botAdDto) {
        Integer botId = botAdDto.get(0).getBotId();
        Integer clientId = botAdDto.get(0).getClientId();
        List<Ad> ads = new ArrayList<>();
        List<BotAdFileDto> fileAds = new ArrayList<>();
        if (!botAdDto.stream().allMatch(el -> el.getBotId().equals(botId) && el.getClientId().equals(clientId)))
            throw new BadRequestException("Not all ads are for one bot or client");
        else {
            Bot bot = facadeUtils.checkAvailability(botId, Bot.class, false);
            for (BotAdDto adDto : botAdDto) {
                Client client = facadeUtils.checkAvailability(adDto.getClientId(), Client.class, false);
                Ad ad = new Ad(adDto, client, bot);
                BotAdFileDto botAdFileDto = new BotAdFileDto(ad);
                ads.add(ad);
                fileAds.add(botAdFileDto);
            }
            adRepository.saveAll(ads);
            CompletableFuture.runAsync(() -> {
                TimewebVpsResponseDto timewebVpsResponseDto = getVpsParams(bot.getVps().getTimewebId());
                sshService.sendAdFile(timewebVpsResponseDto, bot.getName(), fileAds, false);
                Map<String, Object> variablesBotInit = Map.of("bot_name", bot.getName());
                String initBotCommand = UriComponentsBuilder.fromUriString(SshCommands.INIT_BOT).buildAndExpand(variablesBotInit).toString();
                sshService.execCommands(timewebVpsResponseDto, List.of(SshCommands.ENTER_IN_BOT_USER, SshCommands.CD_FARPOST, SshCommands.ENTER_VENV, initBotCommand));
            });
        }
        return new SuccessAdCreateDto();
    }

    @Transactional
    public void updateAdInfo(BotUpdateAdDto botAd, Integer adId) {
        Ad oldAd = facadeUtils.checkAvailability(adId, Ad.class, true);
        Ad ad = new Ad(botAd, oldAd.getClient(), oldAd.getBot(), oldAd.getId());
        adRepository.save(ad);
        List<Ad> botAds = ad.getBot().getAd();
        List<BotAdFileDto> fileAds = botAds.stream().map(BotAdFileDto::new).toList();
        Bot bot = ad.getBot();
        TimewebVpsResponseDto timewebVpsResponseDto = getVpsParams(bot.getVps().getTimewebId());
        Map<String, Object> variablesBotInit = Map.of("bot_name", bot.getName());
        String commandStop = UriComponentsBuilder.fromUriString(SshCommands.STOP_BOT).buildAndExpand(variablesBotInit).toString();
        String commandStart = UriComponentsBuilder.fromUriString(SshCommands.START_BOT).buildAndExpand(variablesBotInit).toString();
        CompletableFuture.runAsync(() -> {
            sshService.execCommands(timewebVpsResponseDto, List.of(SshCommands.ENTER_IN_BOT_USER, commandStop));
            sshService.sendAdFile(timewebVpsResponseDto, bot.getName(), fileAds, true);
            sshService.execCommands(timewebVpsResponseDto, List.of(SshCommands.ENTER_IN_BOT_USER, commandStart));
        });

    }

    @Transactional(readOnly = true)
    public void systemctlOperations(Integer botId, String command) {
        Bot bot = facadeUtils.checkAvailability(botId, Bot.class, false);
        TimewebVpsResponseDto timewebVpsResponseDto = getVpsParams(bot.getVps().getTimewebId());
        Map<String, Object> variablesBotInit = Map.of("bot_name", bot.getName());
        String commandFinish = UriComponentsBuilder.fromUriString(command).buildAndExpand(variablesBotInit).toString();
        CompletableFuture.runAsync(() -> sshService.execCommands(timewebVpsResponseDto, List.of(SshCommands.ENTER_IN_BOT_USER, commandFinish)));
    }

    @Transactional(readOnly = true)
    public List<Bot> getAllBots(){
        return botRepository.getAllBots();
    }

    @Transactional
    public void deleteBot(Integer botId) {
        Bot bot = botRepository.findBotByIdAndLockAd(botId)
                .orElseThrow(() -> new ElementNotFoundException(botId));
        TimewebVpsResponseDto timewebVpsResponseDto = getVpsParams(bot.getVps().getTimewebId());
        botRepository.deleteById(botId);
        Map<String, Object> variableCommand = Map.of(RequestVariables.BOT_NAME.getName(), bot.getName());
        String commandDeleteService = UriComponentsBuilder.fromUriString(SshCommands.DELETE_SERVICE).buildAndExpand(variableCommand).toString();
        String commandStopService = UriComponentsBuilder.fromUriString(SshCommands.STOP_BOT).buildAndExpand(variableCommand).toString();
        String commandDeleteJson = UriComponentsBuilder.fromUriString(SshCommands.DELETE_JSON).buildAndExpand(variableCommand).toString();
        CompletableFuture.runAsync(() -> sshService.execCommands(timewebVpsResponseDto, List.of(SshCommands.ENTER_IN_BOT_USER, SshCommands.CD_SYSTEM, commandStopService, commandDeleteService, commandDeleteJson)));
    }

    private TimewebVpsResponseDto getVpsParams(Integer timewebId) {
        Map<String, Object> variables = Map.of(RequestVariables.TIMEWEB_ID.getName(), timewebId);
        String response = restService.sendGetRequestSimple(TimewebRequests.GET_SERVER, variables, headers -> headers.add(HttpHeaders.AUTHORIZATION, accessToken), String.class);
        return vpsParser.parseVpsResponse(response);
    }

    private boolean checkOnExistBot(String name) {
        if (botRepository.findByName(name).isPresent())
            throw new ElementAlreadyExist(String.format("Bot with name %s already exist", name));
        else
            return false;
    }

}