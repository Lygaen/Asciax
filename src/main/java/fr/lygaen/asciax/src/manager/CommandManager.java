package fr.lygaen.asciax.src.manager;

import fr.lygaen.asciax.src.interfaces.CommandBase;
import fr.lygaen.asciax.src.mongodb.DBGuild;
import fr.lygaen.asciax.src.others.Common;
import fr.lygaen.asciax.src.others.Config;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static fr.lygaen.asciax.src.others.Config.waiter;

public class CommandManager {
    public static CommandBase getCommand(String search) {
        String searchLower = search.toLowerCase();

        for (CommandBase cmd : Config.commands) {
            if (cmd.getName().equals(searchLower) || cmd.getAliases().contains(searchLower)) {
                return cmd;
            }
        }

        return null;
    }


    public static void handle(GuildMessageReceivedEvent event) {

        DBGuild dbGuild = new DBGuild(event.getGuild());

        String[] split = event.getMessage().getContentRaw()
                .replaceFirst("(?i)" + Pattern.quote(dbGuild.prefix), "")
                .split("\\s+");

        String invoke = split[0].toLowerCase();
        CommandBase cmd = getCommand(invoke);

        if (cmd != null) {
            List<String> args = Arrays.asList(split).subList(1, split.length);

            CommandContext ctx = new CommandContext(event, args, waiter);


            if (cmd.isPrenium() && !dbGuild.isPrenium && !ctx.getUser().getId().equals(Config.owner_id)) { Common.notPrenium(ctx); return; }

            if (cmd.ownerOnly() && !ctx.getUser().getId().equals(Config.owner_id)) { Common.notOwner(ctx); return; }

            if (cmd.adminOnly() && !ctx.getMember().getPermissions().contains(Permission.ADMINISTRATOR) && !ctx.getUser().getId().equals(Config.owner_id)) { Common.notAdmin(ctx); return; }

            Permission perm = cmd.requirePermission();

            if (perm != null && !ctx.getMember().getPermissions().contains(perm) && !ctx.getUser().getId().equals(Config.owner_id)) { Common.missingPermission(perm, ctx); return;}
            cmd.handle(ctx);
        }
    }
}
