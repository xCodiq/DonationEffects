package me.xcodiq.donationeffects.commands;

import me.xcodiq.donationeffects.Core;
import me.xcodiq.donationeffects.commands.base.CommandBase;
import me.xcodiq.donationeffects.utilities.ChatUtils;
import org.bukkit.command.CommandSender;
import org.yaml.snakeyaml.error.YAMLException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Arrays;
import java.util.logging.Level;

public class ReloadCommand extends CommandBase {

    public ReloadCommand() {
        super("reload", "Reload all configuration files", "donationeffects.reload", null, 0, 0);
    }

    @Override
    public void execute(CommandSender sender, String[] args) throws NotImplementedException {
        try {
            Core.getInstance().reloadConfig();
            sender.sendMessage(ChatUtils.format("&a&l[!] &aSuccessfully reloaded all configuration files"));
        } catch (YAMLException e) {
            sender.sendMessage(ChatUtils.format("&c&l[!] &cFailed to reload: " + e.getMessage()));
            Core.getInstance().getLogger().log(Level.WARNING, "YAML: " + Arrays.toString(e.getStackTrace()));
        }
    }
}
