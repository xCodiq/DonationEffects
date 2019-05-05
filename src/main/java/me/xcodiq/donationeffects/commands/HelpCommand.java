package me.xcodiq.donationeffects.commands;

import me.xcodiq.donationeffects.commands.base.CommandBase;
import me.xcodiq.donationeffects.commands.base.CommandHandler;
import me.xcodiq.donationeffects.utilities.ChatUtils;
import org.bukkit.command.CommandSender;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class HelpCommand extends CommandBase {

    public HelpCommand() {
        super("help", "Get a list of all available commands", "donationeffects.help", null, 0, 0);
    }

    @Override
    public void execute(CommandSender sender, String[] args) throws NotImplementedException {
        sender.sendMessage(ChatUtils.format("&8&m-+----------------------------------+-"));
        sender.sendMessage(ChatUtils.format(" "));
        sender.sendMessage(ChatUtils.format("&b&l[!] &bAvailable Commands"));
        sender.sendMessage(ChatUtils.format(" "));
        for (CommandBase command : CommandHandler.getInstance().getCommands()) {
            sender.sendMessage(ChatUtils.format(" &3× &b&l/donation " + command.getName() + " &7" + command.getDescription()));
        }
        sender.sendMessage(ChatUtils.format(" &3× &b&l/donation <player> &7Announce someone donated"));
        sender.sendMessage(ChatUtils.format(" "));
        sender.sendMessage(ChatUtils.format("&8&m-+----------------------------------+-"));
    }
}
