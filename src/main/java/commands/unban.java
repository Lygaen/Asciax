package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import src.interfaces.CommandBase;
import src.manager.CommandContext;
import src.shield.category.Category;

import java.awt.*;

import static src.others.Common.basicReactionCheck;


public class unban implements CommandBase {
    private String target_id;
    @Override
    public void handle(CommandContext ctx) {
        try {
            target_id = ctx.getArgs().get(0).replaceAll("[^0-9]", "");
        } catch (IndexOutOfBoundsException e) {
            ctx.reply("Merci de donner une mention ou un id");
            return;
        }
        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("Confirmez ?")
                .setDescription("Confirmez-vous de débannir <@"+target_id+"> ?")
                .setColor(Color.GREEN);
        ctx.reply(embed.build(), event -> {
            event.addReaction("✔").queue();
            ctx.getWaiter().waitForEvent(MessageReactionAddEvent.class, e -> basicReactionCheck(e, ctx, event.getId(), "✔"), ev ->{
                ctx.getGuild().retrieveBanById(target_id).queue((ban -> ctx.getGuild().unban(ban.getUser()).queue()));
                ctx.reply("Débannis !");
            });
        });
    }

    @Override
    public String getName() {
        return "unban";
    }

    @Override
    public String getUsage() {
        return "<member>";
    }

    @Override
    public String getDescription() {
        return "Débannis <member>";
    }

    @Override
    public int getCategory() {
        return Category.ADMIN;
    }

    @Override
    public Permission requirePermission() {
        return Permission.MANAGE_SERVER;
    }
}
