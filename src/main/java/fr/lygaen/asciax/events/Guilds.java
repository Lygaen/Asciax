package fr.lygaen.asciax.events;

import fr.lygaen.asciax.src.mongodb.DBGuild;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static fr.lygaen.asciax.src.mongodb.DBConfig.guildsCollection;
import static fr.lygaen.asciax.src.mongodb.DBWrapper.findByKey;

public class Guilds extends ListenerAdapter {

    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {
        Guild guild = event.getGuild();
        DBGuild dbGuild = new DBGuild(guild);
        dbGuild.push();
        try {
            Objects.requireNonNull(guild.getDefaultChannel()).sendMessage("Thank you for adding Asciax !").queue();
        } catch (NullPointerException e) {
            Objects.requireNonNull(guild.getOwner()).getUser().openPrivateChannel().complete().sendMessage("Thank you for adding Asciax !").queue();
        }

        System.out.println("Added to a Guild ! Id : "+guild.getId());
    }

    @Override
    public void onGuildLeave(@NotNull GuildLeaveEvent event) {
        guildsCollection.findOneAndDelete(Objects.requireNonNull(findByKey(guildsCollection, "id", event.getGuild().getId())));
        System.out.println("Deleted from a Guild ! Id : "+event.getGuild().getId());
    }
}
