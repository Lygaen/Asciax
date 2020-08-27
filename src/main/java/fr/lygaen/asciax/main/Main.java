package fr.lygaen.asciax.main;

import fr.lygaen.asciax.commands.*;
import fr.lygaen.asciax.events.Guilds;
import net.dv8tion.jda.api.JDABuilder;
import fr.lygaen.asciax.src.others.Config;
import fr.lygaen.asciax.src.shield.client.Listener;

import javax.security.auth.login.LoginException;

import static fr.lygaen.asciax.src.others.Common.addCommand;
import static fr.lygaen.asciax.src.others.Config.waiter;

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
        addCommand(new prefix());
    }

}
