package org.farpost.farpostapi2.facades;

import lombok.RequiredArgsConstructor;
import org.farpost.farpostapi2.dto.bot_dto.BotAdDto;
import org.farpost.farpostapi2.dto.bot_dto.BotAdFileVersionDto;
import org.farpost.farpostapi2.dto.bot_dto.BotDto;
import org.farpost.farpostapi2.dto.timeweb_dto.TimewebVpsResponseDto;
import org.farpost.farpostapi2.enitities.Ad;
import org.farpost.farpostapi2.enitities.Bot;
import org.farpost.farpostapi2.enitities.Client;
import org.farpost.farpostapi2.enitities.Vps;
import org.farpost.farpostapi2.exceptions.system.ElementAlreadyExist;
import org.farpost.farpostapi2.facades.utils.FacadeUtils;
import org.farpost.farpostapi2.facades.utils.RequestVariables;
import org.farpost.farpostapi2.facades.utils.TimewebRequests;
import org.farpost.farpostapi2.repositories.AdRepository;
import org.farpost.farpostapi2.repositories.BotRepository;
import org.farpost.farpostapi2.services.RestService;
import org.farpost.farpostapi2.services.SshService;
import org.farpost.farpostapi2.services.VpsParser;
import org.farpost.farpostapi2.services.utils.SshCommands;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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

    @Value("${timeweb.access_token}")
    private String accessToken;

    @Transactional
    public void createNewBot(BotDto botDto) {
        if (!checkOnExistBot(botDto.getName())) {
            Vps vps = facadeUtils.checkAvailability(botDto.getVpsId(), Vps.class, false);
            Map<String, Object> variables = Map.of(RequestVariables.TIMEWEB_ID.getName(), vps.getTimewebId());
            String response = restService.sendGetRequestSimple(TimewebRequests.GET_SERVER, variables, headers -> headers.add(HttpHeaders.AUTHORIZATION, accessToken), String.class);
            TimewebVpsResponseDto timewebVpsResponseDto = vpsParser.parseVpsResponse(response);
            String jsonTouchCommand = UriComponentsBuilder.fromUriString(SshCommands.JSON_TOUCH).buildAndExpand(Map.of("bot_name", botDto.getName())).toString();
            botRepository.save(new Bot(botDto, vps));

            if (!vps.getBots().isEmpty())
                CompletableFuture.runAsync(() -> sshService.execCommands(timewebVpsResponseDto, List.of(SshCommands.ENTER_IN_BOT_USER, SshCommands.CD_JSONS, jsonTouchCommand)));
            else
                CompletableFuture.runAsync(() -> sshService.execCommands(timewebVpsResponseDto, List.of(SshCommands.ENTER_IN_BOT_USER, SshCommands.JSON_MKDIR, jsonTouchCommand, SshCommands.CD_FARPOST, SshCommands.CHMOD_CONSOLE,
                        SshCommands.CHMOD_START, SshCommands.DEF_DISABLE_IPV6, SshCommands.IO_DISABLE_IPV6, SshCommands.ALL_DISABLE_IPV6, SshCommands.UPDATE, SshCommands.START_SH)));
        }
    }


    @Transactional
    public void addAdInBot(BotAdDto botAdDto) {
        Bot bot = facadeUtils.checkAvailability(botAdDto.getBotId(), Bot.class, false);
        Client client = facadeUtils.checkAvailability(botAdDto.getClientId(), Client.class, false);
        adRepository.save(new Ad(botAdDto, client, bot));
        Map<String, Object> variables = Map.of(RequestVariables.TIMEWEB_ID.getName(), bot.getVps().getTimewebId());
        String response = restService.sendGetRequestSimple(TimewebRequests.GET_SERVER, variables, headers -> headers.add(HttpHeaders.AUTHORIZATION, accessToken), String.class);
        TimewebVpsResponseDto timewebVpsResponseDto = vpsParser.parseVpsResponse(response);
        BotAdFileVersionDto botAdFileVersionDto = new BotAdFileVersionDto(botAdDto, client.getBoobs());
        sshService.sendAdFile(timewebVpsResponseDto, bot.getName(), botAdFileVersionDto);
        Map<String, Object> variablesBotInit = Map.of("bot_name", bot.getName());
        String initBotCommand = UriComponentsBuilder.fromUriString(SshCommands.INIT_BOT).buildAndExpand(variablesBotInit).toString();
        CompletableFuture.runAsync(() -> sshService.execCommands(timewebVpsResponseDto, List.of(SshCommands.ENTER_IN_BOT_USER, SshCommands.CD_FARPOST, SshCommands.ENTER_VENV, initBotCommand)));
    }

    @Transactional(readOnly = true) //пофиксить скобки в json файле
    public void systemctlOperations(Integer botId, String command) {
        Bot bot = facadeUtils.checkAvailability(botId, Bot.class, false);
        Map<String, Object> variables = Map.of(RequestVariables.TIMEWEB_ID.getName(), bot.getVps().getTimewebId());
        String response = restService.sendGetRequestSimple(TimewebRequests.GET_SERVER, variables, headers -> headers.add(HttpHeaders.AUTHORIZATION, accessToken), String.class);
        TimewebVpsResponseDto timewebVpsResponseDto = vpsParser.parseVpsResponse(response);
        Map<String, Object> variablesBotInit = Map.of("bot_name", bot.getName());
        String commandFinish = UriComponentsBuilder.fromUriString(command).buildAndExpand(variablesBotInit).toString();
        sshService.execCommands(timewebVpsResponseDto, List.of(SshCommands.ENTER_IN_BOT_USER, commandFinish));
    }

        private boolean checkOnExistBot (String name){
            if (botRepository.findByName(name).isPresent())
                throw new ElementAlreadyExist(String.format("Bot with name %s already exist", name));
            else
                return false;
        }
    }
