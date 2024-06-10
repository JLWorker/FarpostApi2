package org.farpost.farpostapi2.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jcraft.jsch.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.farpost.farpostapi2.dto.bot_dto.BotAdFileDto;
import org.farpost.farpostapi2.dto.timeweb_dto.TimewebVpsResponseDto;
import org.farpost.farpostapi2.exceptions.ssh.SshConnectionException;
import org.farpost.farpostapi2.exceptions.system.InvalidSystemOperationException;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.*;
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
        jSch.setKnownHosts(sshKeyScan(timewebVpsResponseDto.getIpv4()));
        Session session = jSch.getSession(username, timewebVpsResponseDto.getIpv4(), port);
        try {
            session.setPassword(timewebVpsResponseDto.getRootPassword());
            session.setTimeout(3000);
            session.connect();
            if (session.isConnected()){
                ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
                StringBuilder stringBuilder = new StringBuilder();
                commands.forEach(el -> stringBuilder.append(el).append(" && "));
                String command = stringBuilder.substring(0 , stringBuilder.length()-3).concat("/&");
                channelExec.setCommand(command);
                InputStream in = channelExec.getInputStream();
                channelExec.connect();

                byte[] tmp = new byte[1024];
                while (true) {
                    while (in.available() > 0) {
                        int i = in.read(tmp, 0, 1024);
                        if (i < 0) break;
                        System.out.print(new String(tmp, 0, i));
                    }
                    if (channelExec.isClosed()) {
                        if (in.available() > 0) continue;
                        System.out.println("exit-status: "
                                + channelExec.getExitStatus());
                        break;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (Exception ee) {
                    }
                }

                channelExec.disconnect();
            }
        } catch (JSchException e) {
            throw new SshConnectionException(String.format("Ssh connection failed. Message - %s", e.getMessage()));
        }
        finally {
            session.disconnect();
        }
    }

    @SneakyThrows
    public void sendAdFile(TimewebVpsResponseDto timewebVpsResponseDto, String botName, List<BotAdFileDto> botAdFileDto, boolean override){
        JSch jSch = new JSch();
        jSch.setKnownHosts(sshKeyScan(timewebVpsResponseDto.getIpv4()));
        Session session = null;
        try {
            session = jSch.getSession(username, timewebVpsResponseDto.getIpv4(), port);
            session.setPassword(timewebVpsResponseDto.getRootPassword());
            session.setTimeout(3000);
            session.connect();
            ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
            Map<String, Object> variables = Map.of("bot_name", botName);
            String pathToFile = UriComponentsBuilder.fromUriString(jsonFilePath).buildAndExpand(variables).toString();
            String jsonAds = objectMapper.writeValueAsString(botAdFileDto);
            InputStream inputStream = new ByteArrayInputStream(jsonAds.getBytes());
            channelSftp.connect();
            if (override) {
                channelSftp.put(inputStream, pathToFile, ChannelSftp.OVERWRITE);
            } else {
                channelSftp.put(inputStream, pathToFile);
            }
            channelSftp.disconnect();
        } catch (JSchException | SftpException e) {
            log.error(e.getMessage());
            throw new SshConnectionException(String.format("Ssh connection failed. Message - %s", e.getMessage()));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        finally {
            if (session != null)
                session.disconnect();
        }
    }

    public InputStream sshKeyScan(String ipv4){
        ProcessBuilder builder = new ProcessBuilder("ssh-keyscan", ipv4);
        builder.redirectErrorStream(true);
        try {
            Process process = builder.start();
            return process.getInputStream();
        } catch (IOException e) {
            throw new InvalidSystemOperationException(e.getMessage());
        }

    }


}
