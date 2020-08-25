package commands;

import src.interfaces.CommandBase;
import src.manager.CommandContext;
import src.others.Config;
import src.shield.category.Category;

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
        return "Show all of the commands";
    }

    @Override
    public int getCategory() {
        return Category.INFO;
    }
}
