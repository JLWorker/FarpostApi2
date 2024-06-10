package org.farpost.farpostapi2.api;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.farpost.farpostapi2.dto.bot_dto.BotAdDto;
import org.farpost.farpostapi2.dto.bot_dto.BotDto;
import org.farpost.farpostapi2.dto.bot_dto.SuccessAdCreateDto;
import org.farpost.farpostapi2.dto.bot_dto.SuccessBotCreateDto;
import org.farpost.farpostapi2.dto.farpost_ad_dto.FarpostAdResponseDto;
import org.farpost.farpostapi2.enitities.Bot;
import org.farpost.farpostapi2.facades.BotFacade;
import org.farpost.farpostapi2.services.utils.SshCommands;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bot")
@RequiredArgsConstructor
@SecurityRequirement(name = "Authentication")
@Tag(name = "bot", description = "Bot controller api")
public class BotApi {

    private final BotFacade botFacade;

    @Operation(summary = "Create new bot", description = "If you start this command for first bot in vps, after complete request, please, wait minimum 2 minutes that bot can init!")
    @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Access token (ONLY WITH ROLE ADMIN!!)", example = "Bearer uhdYUhskn879jd...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", content = @Content(), description = "Vps not found"),
            @ApiResponse(responseCode = "400", content = @Content(), description = "Bot with name already exist"),
            @ApiResponse(responseCode = "409", content = @Content(), description = "Validation exception"),
            @ApiResponse(responseCode = "200", description = "Success operation", content = @Content(schema = @Schema(implementation = SuccessBotCreateDto.class)))
    })
    @PostMapping()
    public SuccessBotCreateDto createBot(@RequestBody BotDto botDto){
        return botFacade.createNewBot(botDto);
    }


    @Operation(summary = "Create new ad")
    @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Access token (ONLY WITH ROLE ADMIN!!)", example = "Bearer uhdYUhskn879jd...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", content = @Content(), description = "Bot or client not found in ad"),
            @ApiResponse(responseCode = "400", content = @Content(), description = "Different bot or client IDs for ads"),
            @ApiResponse(responseCode = "409", content = @Content(), description = "Validation exception"),
            @ApiResponse(responseCode = "200", description = "Success operation", content = @Content(schema = @Schema(implementation = SuccessAdCreateDto.class)))
    })
    @PostMapping("/ad")
    public SuccessAdCreateDto createBotAd(@RequestBody @Valid @JsonView(BotAdDto.NewAd.class) @NotEmpty(message = "Bot ads cannot be empty") List<BotAdDto> botAdDto){
        return botFacade.addAdInBot(botAdDto);
    }

    @Operation(summary = "Update ad")
    @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Access token (ONLY WITH ROLE ADMIN!!)", example = "Bearer uhdYUhskn879jd...")
    @Parameter(name = "ad_id",in = ParameterIn.PATH, description = "Access token (ONLY WITH ROLE ADMIN!!)", example = "12" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", content = @Content(), description = "Ad not found"),
            @ApiResponse(responseCode = "409", content = @Content(), description = "Validation exception"),
            @ApiResponse(responseCode = "200", description = "Success operation", content = @Content())
    })
    @PutMapping("/ad/{ad_id}")
    public void updateBotAd(@RequestBody @Valid @JsonView(BotAdDto.UpdateAd.class) BotAdDto botAdDto, @PathVariable(name = "ad_id") Integer adId) {
        botFacade.updateAdInfo(botAdDto, adId);
    }

    @Operation(summary = "Get all bots")
    @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Access token (ONLY WITH ROLE ADMIN!!)", example = "Bearer uhdYUhskn879jd...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success operation")
    })
    @GetMapping()
    public List<Bot> getAllBots() {
        return botFacade.getAllBots();
    }

    @Operation(summary = "Delete bot with ads")
    @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Access token (ONLY WITH ROLE ADMIN!!)", example = "Bearer uhdYUhskn879jd...")
    @Parameter(name = "bot_id",in = ParameterIn.PATH, description = "Access token (ONLY WITH ROLE ADMIN!!)", example = "12" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", content = @Content(), description = "Bot not found"),
            @ApiResponse(responseCode = "409", content = @Content(), description = "Validation exception"),
            @ApiResponse(responseCode = "200", description = "Success operation", content = @Content())
    })
    @DeleteMapping("/{bot_id}")
    public void deleteBot(@PathVariable(name = "bot_id") Integer botId){
        botFacade.deleteBot(botId);
    }

    @Operation(summary = "Restart bot")
    @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Access token (ONLY WITH ROLE ADMIN!!)", example = "Bearer uhdYUhskn879jd...")
    @Parameter(name = "bot_id",in = ParameterIn.PATH, example = "12" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", content = @Content(), description = "Bot not found"),
            @ApiResponse(responseCode = "409", content = @Content(), description = "Validation exception"),
            @ApiResponse(responseCode = "200", description = "Success operation", content = @Content())
    })
    @GetMapping("/restart/{bot_id}")
    public void restartBot(@PathVariable(name = "bot_id") Integer botId){
        botFacade.systemctlOperations(botId, SshCommands.RESTART_BOT);
    }

    @Operation(summary = "Stop bot")
    @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Access token (ONLY WITH ROLE ADMIN!!)", example = "Bearer uhdYUhskn879jd...")
    @Parameter(name = "bot_id",in = ParameterIn.PATH, example = "12" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", content = @Content(), description = "Bot not found"),
            @ApiResponse(responseCode = "409", content = @Content(), description = "Validation exception"),
            @ApiResponse(responseCode = "200", description = "Success operation", content = @Content())
    })
    @GetMapping("/stop/{bot_id}")
    public void stopBot(@PathVariable(name = "bot_id") Integer botId){
        botFacade.systemctlOperations(botId, SshCommands.STOP_BOT);
    }

    @Operation(summary = "Start bot")
    @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Access token (ONLY WITH ROLE ADMIN!!)", example = "Bearer uhdYUhskn879jd...")
    @Parameter(name = "bot_id",in = ParameterIn.PATH, example = "12" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", content = @Content(), description = "Bot not found"),
            @ApiResponse(responseCode = "409", content = @Content(), description = "Validation exception"),
            @ApiResponse(responseCode = "200", description = "Success operation", content = @Content())
    })
    @GetMapping("/start/{bot_id}")
    public void startBot(@PathVariable(name = "bot_id") Integer botId){
        botFacade.systemctlOperations(botId, SshCommands.START_BOT);
    }

}
