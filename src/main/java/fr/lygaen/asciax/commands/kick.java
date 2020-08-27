package fr.lygaen.asciax.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import fr.lygaen.asciax.src.interfaces.CommandBase;
import fr.lygaen.asciax.src.manager.CommandContext;
import fr.lygaen.asciax.src.shield.category.Category;

import java.awt.*;

import static fr.lygaen.asciax.src.others.Common.basicReactionCheck;
import static fr.lygaen.asciax.src.others.Common.getMemberNonNull;

public class kick implements CommandBase {
    @Override
    public void handle(CommandContext ctx) {
        Member target;
        try {
            target = getMemberNonNull(ctx, ctx.getArgs().get(0));
        } catch (IndexOutOfBoundsException e) {
            ctx.reply("Merci de donner une mention ou un id");
            return;
        }
        if (target == null) {
            ctx.reply("Ce membre n'existe pas ! Il a besoin d'être sur votre Serveur pour être Kicker !");
            return;
        }
        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("Confirmez ?")
                .setDescription("Confirmez-vous de kicker <@"+target.getId()+"> ?")
                .setColor(Color.GREEN);
        ctx.reply(embed.build(), event -> {
            event.addReaction("✔").queue();
            ctx.getWaiter().waitForEvent(MessageReactionAddEvent.class, e -> basicReactionCheck(e, ctx, event.getId(), "✔"), ev ->{
                target.kick().queue();
                ctx.reply("Kické !");
            });
        });
    }

    @Override
    public String getName() {
        return "kick";
    }

    @Override
    public String getUsage() {
        return "<member>";
    }

    @Override
    public String getDescription() {
        return "Kick <member>";
    }

    @Override
    public int getCategory() {
        return Category.ADMIN;
    }
}
