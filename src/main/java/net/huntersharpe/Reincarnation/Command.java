package net.huntersharpe.Reincarnation;

import org.spongepowered.api.Server;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.util.command.CommandCallable;
import org.spongepowered.api.util.command.CommandException;
import org.spongepowered.api.util.command.CommandResult;
import org.spongepowered.api.util.command.CommandSource;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created by intronate67 on 10/29/15.
 */
public class Command implements CommandCallable{

    private final Optional<Text> desc = Optional.of((Text) Texts.of("net.huntersharpe.Reincarnation.Reincarnation command."));
    private final Optional<Text> help = Optional.of((Text) Texts.of("Used by admins to change settings of the net.huntersharpe.Reincarnation.Reincarnation plugin."));
    //TODO: Add usage arguments.
    private final Text usage = (Text) Texts.of("<>");

    private final Server server;

    public Command(Server server){
        this.server = server;
    }

    public CommandResult process(CommandSource src, String arguments){
        return CommandResult.success();
    }

    public boolean testPermission(CommandSource source) {
        return source.hasPermission("reincarnation.use");
    }

    public Optional<Text> getShortDescription(CommandSource source) {
        return desc;
    }

    public Optional<Text> getHelp(CommandSource source) {
        return help;
    }

    public Text getUsage(CommandSource source) {
        return usage;
    }

    public List<String> getSuggestions(CommandSource source, String arguments) throws CommandException {
        return Collections.emptyList();
    }

    public void sendHelp(Player player){
        player.sendMessage(Texts.of(TextColors.DARK_GRAY, "[", TextColors.BLUE, "net.huntersharpe.Reincarnation.Reincarnation", TextColors.DARK_GRAY, "] ", TextColors.RED, "Incorrect Usage! Use /re help for more info."));
    }

    public void helpMenu(Player player){

    }

    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isDigit(str.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

}
