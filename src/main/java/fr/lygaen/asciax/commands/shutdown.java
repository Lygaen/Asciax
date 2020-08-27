package fr.lygaen.asciax.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import fr.lygaen.asciax.src.interfaces.CommandBase;
import fr.lygaen.asciax.src.manager.CommandContext;
import fr.lygaen.asciax.src.shield.category.Category;

import java.awt.*;
import java.util.Arrays;
import java.util.List;


public class shutdown implements CommandBase {
    @Override
    public void handle(CommandContext ctx) {
        EmbedBuilder e = new EmbedBuilder()
                .setTitle("Shutting down...")
                .setDescription("Bot is now shutting down...")
                .setColor(Color.GREEN);
        ctx.reply(e.build());
        ctx.getJDA().shutdown();
    }

    @Override
    public String getName() {
        return "shutdown";
    }

    @Override
    public String getUsage() {
        return "";
    }

    @Override
    public String getDescription() {
        return "A command to Shutdown the Bot";
    }

    @Override
    public int getCategory() {
        return Category.OWNER;
    }


    @Override
    public Boolean ownerOnly() {
        return true;
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("kill", "shut");
    }
}
