package fr.lygaen.asciax.commands;

import fr.lygaen.asciax.src.interfaces.CommandBase;
import fr.lygaen.asciax.src.manager.CommandContext;
import fr.lygaen.asciax.src.others.Config;
import fr.lygaen.asciax.src.shield.category.Category;

public class help implements CommandBase {
    @Override
    public void handle(CommandContext ctx) {
        StringBuilder str = new StringBuilder();
        for (CommandBase cmd : Config.commands) {
            str.append("**").append(cmd.getName()).append(" ").append(cmd.getUsage()).append("**\n").append(cmd.getDescription()).append("\n");
        }
        ctx.reply(str.toString());
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getUsage() {
        return "";
    }

    @Override
    public String getDescription() {
        return "Show all of the fr.lygaen.asciax.commands";
    }

    @Override
    public int getCategory() {
        return Category.INFO;
    }
}
