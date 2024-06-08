package org.farpost.farpostapi2.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.farpost.farpostapi2.dto.bot_dto.BotAdDto;
import org.farpost.farpostapi2.dto.farpost_ad_dto.FarpostAdDto;
import org.farpost.farpostapi2.dto.bot_dto.BotDto;
import org.farpost.farpostapi2.facades.BotFacade;
import org.farpost.farpostapi2.services.utils.SshCommands;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bot")
@RequiredArgsConstructor
public class BotApi {

    private final BotFacade botFacade;

    @PostMapping()
    public void createBot(@RequestBody BotDto botDto){
        botFacade.createNewBot(botDto);
    }

    @PostMapping("/ad")
    public void createBotAd(@RequestBody @Valid BotAdDto botAdDto){
        botFacade.addAdInBot(botAdDto);
    }

    @GetMapping("/restart/{bot_id}")
    public void restartBot(@PathVariable(name = "bot_id") Integer botId){
        botFacade.systemctlOperations(botId, SshCommands.RESTART_BOT);
    }

    @GetMapping("/stop/{bot_id}")
    public void stopBot(@PathVariable(name = "bot_id") Integer botId){
        botFacade.systemctlOperations(botId, SshCommands.STOP_BOT);
    }

    @GetMapping("/start/{bot_id}")
    public void startBot(@PathVariable(name = "bot_id") Integer botId){
        botFacade.systemctlOperations(botId, SshCommands.START_BOT);
    }

}
