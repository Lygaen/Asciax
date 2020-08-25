package src.interfaces;

import net.dv8tion.jda.api.Permission;
import src.manager.CommandContext;

import java.util.Collections;
import java.util.List;

public interface CommandBase {

    void handle(CommandContext ctx);

    String getName();

    String getUsage();

    String getDescription();

    int getCategory();

    default List<String> getAliases() { return Collections.emptyList(); }

    default Boolean ownerOnly() {return false;}

    default Boolean adminOnly() {return false;}

    default Permission requirePermission() {return null;}

}
