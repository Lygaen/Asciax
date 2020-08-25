package src.others;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Activity;
import src.interfaces.CommandBase;
import src.shield.waiter.EventWaiter;

import java.util.ArrayList;
import java.util.List;

public class Config {

    public static final String token = Secret.TOKEN;
    public static final String prefix = "!";
    public static final String owner_id = "413743021759135745";

    public static JDA client;

    public static EventWaiter waiter = new EventWaiter();

    public static final Activity activity = Activity.watching("connected !");

    public static List<CommandBase> commands = new ArrayList<>();

}
