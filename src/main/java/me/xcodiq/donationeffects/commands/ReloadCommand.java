package me.xcodiq.donationeffects.commands;

import me.xcodiq.donationeffects.Core;
import me.xcodiq.donationeffects.commands.base.CommandBase;
import me.xcodiq.donationeffects.utilities.ChatUtils;
import me.xcodiq.donationeffects.utilities.PluginUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ReloadCommand extends CommandBase {

    public ReloadCommand() {
        super("reload", "Reload all configuration files", "donationeffects.reload", null, 0, 0);
    }

    @Override
    public void execute(CommandSender sender, String[] args) throws NotImplementedException {
        Core.getInstance().reloadConfig();
        Plugin plugin = PluginUtils.getPluginByName("DonationEffects");
        PluginUtils.restartPlugin(plugin);
        sender.sendMessage(ChatUtils.format("&b&l[!] &bSuccessfully reloaded all configuration files"));
    }
}
