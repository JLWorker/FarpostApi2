package org.farpost.farpostapi2.services;

import org.farpost.farpostapi2.dto.bot_dto.BotAdFileVersionDto;
import org.farpost.farpostapi2.dto.timeweb_dto.TimewebVpsResponseDto;
import org.farpost.farpostapi2.facades.utils.RequestVariables;
import org.farpost.farpostapi2.facades.utils.TimewebRequests;
import org.farpost.farpostapi2.services.utils.SshCommands;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
public class SshServiceTest {

    @Autowired
    private SshService sshService;

    @Autowired
    private RestService restService;

    @Autowired
    private VpsParser vpsParser;

    private final String accessToken = "Bearer eyJhbGciOiJSUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6IjFrYnhacFJNQGJSI0tSbE1xS1lqIn0.eyJ1c2VyIjoiZXU4MTk4OSIsInR5cGUiOiJhcGlfa2V5IiwicG9ydGFsX3Rva2VuIjoiNjM5MDQzZWQtNmMzNC00NmY1LWJlNGYtMDMxYzEwNjE5YjUwIiwiYXBpX2tleV9pZCI6Ijc3ODA3M2Y2LWE1YWYtNDQzOS04MDY1LTU0YmQ0ZjIwZmRmMCIsImlhdCI6MTcxNzYzOTEwNH0.XIXItDtGjd4wTlOBtYlHF1tpxdbfvXVu9OuTPvgJ7FG9_SZJklaW65TRUnRgt4_DxKbJzijDhi3nbbtVAMVwUfHDyFXA0HgUvnB13Bdkk_XtI6XvzsJByG_muyUp-WSU1TkOw1q58WrYiHRIvQSCWYXkVjwSOHpoa4SFgJYCVN24YwormdDr4qP2h7TfHhW_5gmvZkg4tkhHO734baIYcPlVxUZ4LY9T2ksJqjPaUWDOjwWaEukcT8iuROtjZ3UR0O20Mlpeq79WXZKtnAo2YKr4FV-yQXc6fj2eVjUCvSUV0UjqJ0oCAeveWJwqxQK0IJ6YwftYriARVDX_tGwwd-Pm9x4jJBTuIqQsPjjYbm_O_sCkiIuL0m91ZD6ialdq7jc-3E2xEp5-kIDQgR90kGF5Ucc3OFo1RW55gKG4MOmd9NxT2tbbLG1Dgvr82hHkKXa5UuJAtcJBDssbEvKKb33SlwFh-twyNHbCSMpXTrSS-rGLoJMiQbOxFhT-oRHP";
    private final Integer timewebId = 3066887;

    private final Logger logger = Logger.getLogger(this.getClass().getName());
//    private final List<String> commandList = List.of(SshCommands.ENTER_IN_BOT_USER, SshCommands.JSON_MKDIR, "sudo touch /home/bot/jsons/bot_3.json", SshCommands.CD_FARPOST, SshCommands.CHMOD_CONSOLE,
//            SshCommands.CHMOD_START, SshCommands.DEF_DISABLE_IPV6, SshCommands.IO_DISABLE_IPV6, SshCommands.ALL_DISABLE_IPV6, SshCommands.START_SH);

//    private final List<String> commandList = List.of(SshCommands.ENTER_IN_BOT_USER, SshCommands.JSON_MKDIR, "sudo touch /home/bot/jsons/bot_3.json", SshCommands.CD_FARPOST, SshCommands.CHMOD_CONSOLE,
//            SshCommands.CHMOD_START, SshCommands.DEF_DISABLE_IPV6, SshCommands.IO_DISABLE_IPV6, SshCommands.ALL_DISABLE_IPV6);

    private final List<String> startSh = List.of(SshCommands.ENTER_IN_BOT_USER, SshCommands.CD_FARPOST, SshCommands.UPDATE_KERNAL, SshCommands.START_SH);


    @Test
    public void execCommand() {
        Map<String, Object> variables = Map.of(RequestVariables.TIMEWEB_ID.getName(), timewebId);
        String response = restService.sendGetRequestSimple(TimewebRequests.GET_SERVER, variables, headers -> headers.add(HttpHeaders.AUTHORIZATION, accessToken), String.class);
        TimewebVpsResponseDto timewebVpsResponseDto = vpsParser.parseVpsResponse(response);
        logger.info(timewebVpsResponseDto.getVpsTimewebId() + " : " + timewebVpsResponseDto.getRootPassword());
        sshService.execCommands(timewebVpsResponseDto, startSh);
    }

//    @Test
//    public void sshKeyscan() {
//        logger.info(sshService.sshKeyScan("85.193.81.123"));
//    }

//    @Test
//    public void exexShell(){
//
////        BotAdFileVersionDto botAdFileVersionDto = new BotAdFileVersionDto("boobs");
//
//        BotAdFileVersionDto botAdFileVersionDtoTest = new BotAdFileVersionDto(
//
//
//        );
//
//        TimewebVpsResponseDto timeWebVpsResponse = new TimewebVpsResponseDto(3065045, "hE3F-^b3Ho+tua", "", "85.193.81.123");
//        sshService.execShell(timeWebVpsResponse, List.of());
//
//    }

}
