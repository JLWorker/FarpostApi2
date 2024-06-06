package org.farpost.farpostapi2.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jcraft.jsch.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.farpost.farpostapi2.dto.bot_dto.BotAdFileVersionDto;
import org.farpost.farpostapi2.dto.timeweb_dto.TimewebVpsResponseDto;
import org.farpost.farpostapi2.exceptions.ssh.SshConnectionException;
import org.farpost.farpostapi2.services.utils.SshCommands;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class SshService {
    private final Integer port = 22;
    private final String username = "root";
    private final ObjectMapper objectMapper;
    private final String jsonFilePath = "/home/bot/jsons/{bot_name}.json";

    @SneakyThrows
    public void execCommands(TimewebVpsResponseDto timewebVpsResponseDto, List<String> commands){
        JSch jSch = new JSch();
        try {
            Session session = jSch.getSession(username, timewebVpsResponseDto.getIpv4(), port);
            session.setPassword(timewebVpsResponseDto.getRootPassword());
            session.setConfig("StrictHostKeyChecking", "no");
            session.setTimeout(3000);
            session.connect();
            if (session.isConnected()){
                ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
                StringBuilder stringBuilder = new StringBuilder();
                commands.forEach(el -> stringBuilder.append(el).append(" && "));
                String command = stringBuilder.substring(0 , stringBuilder.length()-3).concat("/&");
                channelExec.setCommand(command);
                channelExec.connect();
                channelExec.disconnect();
                session.disconnect();
            }
        } catch (JSchException e) {
            throw new SshConnectionException(String.format("Ssh connection failed. Message - %s", e.getMessage()));
        }
    }

    public void sendAdFile(TimewebVpsResponseDto timewebVpsResponseDto, String botName, BotAdFileVersionDto botAdFileVersionDto){
        JSch jSch = new JSch();
        try {
            Session session = jSch.getSession(username, timewebVpsResponseDto.getIpv4(), port);
            session.setPassword(timewebVpsResponseDto.getRootPassword());
            session.setConfig("StrictHostKeyChecking", "no");
            session.setTimeout(3000);
            session.connect();
            ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
            Map<String, Object> variables = Map.of("bot_name", botName);
            String pathToFile = UriComponentsBuilder.fromUriString(jsonFilePath).buildAndExpand(variables).toString();
            String jsonAd = objectMapper.writeValueAsString(botAdFileVersionDto);
            InputStream inputStream = new ByteArrayInputStream(jsonAd.getBytes());
            channelSftp.connect();
            channelSftp.put(inputStream, pathToFile);
            channelSftp.disconnect();
            session.disconnect();
        } catch (JSchException | SftpException e) {
            log.error(e.getMessage());
            throw new SshConnectionException(String.format("Ssh connection failed. Message - %s", e.getMessage()));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }


    }


}
