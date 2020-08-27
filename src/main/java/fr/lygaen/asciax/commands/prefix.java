package fr.lygaen.asciax.commands;

import fr.lygaen.asciax.src.interfaces.CommandBase;
import fr.lygaen.asciax.src.manager.CommandContext;
import fr.lygaen.asciax.src.mongodb.DBGuild;
import fr.lygaen.asciax.src.shield.category.Category;

public class prefix implements CommandBase {
    @Override
    public void handle(CommandContext ctx) {
        DBGuild dbGuild = new DBGuild(ctx.getGuild());
        String prefix = "";

        try {
            prefix = ctx.getArgs().get(0);
        } catch (IndexOutOfBoundsException e) {
            ctx.reply("Merci de donner un préfixe !");
            return;
        }
        prefix = prefix.strip();
        if(prefix.length() > 3) {
            ctx.reply("Le préfixe à besoin doit être maximum 3 charactères !");
            return;
        }
        dbGuild.change(prefix);
        ctx.reply("Préfixe changé en `" + prefix + "` !");
    }

    @Override
    public String getName() {
        return "prefix";
    }

    @Override
    public String getUsage() {
        return "[prefix]";
    }

    @Override
    public String getDescription() {
        return "Change votre préfixe à [prefix]";
    }

    @Override
    public int getCategory() {
        return Category.ADMIN;
    }

    @Override
    public Boolean adminOnly() {
        return true;
    }
}
