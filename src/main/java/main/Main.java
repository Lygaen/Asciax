package main;

import commands.*;
import events.Guilds;
import net.dv8tion.jda.api.JDABuilder;
import src.others.Config;
import src.shield.client.Listener;

import javax.security.auth.login.LoginException;

import static src.others.Common.addCommand;
import static src.others.Config.waiter;

public class Main {


    public static void main(String[] args) throws LoginException {

        Config.client = JDABuilder.createDefault(Config.token)
                .setActivity(Config.activity)
                .addEventListeners(new Listener())
                .addEventListeners(new Guilds())
                .addEventListeners(waiter)
                .build();


        buildCommands();
    }

    private static void buildCommands() {
        addCommand(new shutdown());
        addCommand(new help());
        addCommand(new ban());
        addCommand(new unban());
        addCommand(new kick());
    }

}
