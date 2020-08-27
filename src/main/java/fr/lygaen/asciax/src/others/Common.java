package fr.lygaen.asciax.src.others;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import fr.lygaen.asciax.src.interfaces.CommandBase;
import fr.lygaen.asciax.src.manager.CommandContext;
import fr.lygaen.asciax.src.shield.category.Category;
import net.dv8tion.jda.api.entities.Member;

import java.awt.*;
import java.util.Objects;

import static fr.lygaen.asciax.src.others.Config.commands;


public class Common {
    public static void notOwner(CommandContext ctx) {
        EmbedBuilder e = new EmbedBuilder()
                .setTitle("NON DEV")
                .setColor(Color.RED)
                .setDescription("Vous avez besoin d'être le dev du Bot pour utiliser cette commande.");
        ctx.reply(e.build());
    }

    public static void notAdmin(CommandContext ctx) {
        EmbedBuilder e = new EmbedBuilder()
                .setTitle("NON ADMIN")
                .setColor(Color.RED)
                .setDescription("Vous avez besoin d'être l'Admin du Serveur pour utiliser cette commande.");
        ctx.reply(e.build());
    }

    public static void missingPermission(Permission perm, CommandContext ctx) {
        EmbedBuilder e = new EmbedBuilder()
                .setTitle("PERMS MANQUANTES")
                .setColor(Color.RED)
                .setDescription("Vous avez besoin de la permission `" + perm.getName() + "` pour utiliser cette commande.");
        ctx.reply(e.build());
    }


    public static void addCommand(CommandBase cmd) {
        if(!commands.contains(cmd)){
            commands.add(cmd);
            switch (cmd.getCategory()) {
                case Category.OWNER:
                    Category.owner_commands.add(cmd);
                    break;
                case Category.ADMIN:
                    Category.admin_commands.add(cmd);
                    break;
                case Category.FUN:
                    Category.fun_commands.add(cmd);
                    break;
                case Category.INFO:
                    Category.info_commands.add(cmd);
                    break;
            }
        }
    }

    public static Member getMemberNonNull(CommandContext ctx, String mention) {
        try {
            return ctx.getGuild().retrieveMemberById(mention.replaceAll("[^0-9]", "")).complete();
        } catch(net.dv8tion.jda.api.exceptions.ErrorResponseException e) {
            return null;
        }
    }


    public static boolean basicReactionCheck(MessageReactionAddEvent event, CommandContext ctx, String message_id, String emoji) {
        return Objects.equals(event.getMember(), ctx.getMember()) && event.getMessageId().equals(message_id) && event.getReactionEmote().getEmoji().equals(emoji);
    }

    public static void notPrenium(CommandContext ctx) {
        EmbedBuilder e = new EmbedBuilder()
                .setTitle("NON PRENIUM")
                .setColor(Color.RED)
                .setDescription("Vous avez besoin d'être prenium pour utiliser cette commande ! ");
        ctx.reply(e.build());
    }
}
