package org.farpost.farpostapi2.services.utils;

public interface SshCommands {

    String ENTER_IN_BOT_USER = "su bot";
    String CD_FARPOST = "cd /home/bot/farpost-2.0";
    String CHMOD_START = " sudo chmod 777 start.sh";
    String CHMOD_CONSOLE = "sudo chmod 777 new_controle.sh";
    String START_SH = "sudo ./start.sh";
    String JSON_TOUCH = "sudo touch /home/bot/jsons/{bot_name}.json";
    String JSON_MKDIR = "sudo mkdir /home/bot/jsons";
    String ALL_DISABLE_IPV6 = "sudo sysctl -w net.ipv6.conf.all.disable_ipv6=1";
    String DEF_DISABLE_IPV6 = "sudo sysctl -w net.ipv6.conf.default.disable_ipv6=1";
    String IO_DISABLE_IPV6 = "sudo sysctl -w net.ipv6.conf.lo.disable_ipv6=1";
    String ENTER_VENV = "source .venv/bin/activate";
    String INIT_BOT = "sudo ./new_controle.sh {bot_name}.json 0";

    String START_BOT = "sudo systemctl start {bot_name}.service";
    String STOP_BOT = "sudo systemctl stop {bot_name}.service";
    String RESTART_BOT = "sudo systemctl restart {bot_name}.service";
}
