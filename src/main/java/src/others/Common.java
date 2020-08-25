package src.others;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import src.interfaces.CommandBase;
import src.manager.CommandContext;
import src.shield.category.Category;
import net.dv8tion.jda.api.entities.Member;

import java.awt.*;
import java.util.Objects;

import static src.others.Config.commands;


public class Common {
    public static void notOwner(CommandContext ctx) {
        EmbedBuilder e = new EmbedBuilder()
                .setTitle("NOT OWNER")
                .setColor(Color.RED)
                .setDescription("You need to be the owner of the bot to use this command.");
        ctx.reply(e.build());
    }

    public static void notAdmin(CommandContext ctx) {
        EmbedBuilder e = new EmbedBuilder()
                .setTitle("NOT ADMIN")
                .setColor(Color.RED)
                .setDescription("You need to be the admin of the Server to use this command.");
        ctx.reply(e.build());
    }

    public static void missingPermission(Permission perm, CommandContext ctx) {
        EmbedBuilder e = new EmbedBuilder()
                .setTitle("MISSING PERM")
                .setColor(Color.RED)
                .setDescription("You need to have the permission `" + perm.getName() + "` to use this command.");
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
}
