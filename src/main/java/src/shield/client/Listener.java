package src.shield.client;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import src.manager.CommandManager;
import src.others.Config;

import javax.annotation.Nonnull;

public class Listener extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        User user = event.getAuthor();

        if (user.isBot() || event.isWebhookMessage()) {
            return;
        }

        String prefix = Config.prefix;
        String raw = event.getMessage().getContentRaw();

        if (raw.startsWith(prefix)) {
            CommandManager.handle(event);
        }
    }

}
