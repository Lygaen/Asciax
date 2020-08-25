package src.shield.category;

import src.interfaces.CommandBase;

import java.util.ArrayList;
import java.util.List;

public class Category {
    public static final int OWNER = 0;
    public static final int ADMIN = 1;
    public static final int FUN = 2;
    public static final int INFO = 3;

    public static List<CommandBase> owner_commands = new ArrayList<>();
    public static List<CommandBase> admin_commands = new ArrayList<>();
    public static List<CommandBase> fun_commands = new ArrayList<>();
    public static List<CommandBase> info_commands = new ArrayList<>();
}
