package me.xcodiq.donationeffects.commands;

import me.xcodiq.donationeffects.Core;
import me.xcodiq.donationeffects.commands.base.CommandBase;
import me.xcodiq.donationeffects.effects.Effect;
import me.xcodiq.donationeffects.utilities.ChatUtils;
import org.bukkit.command.CommandSender;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

public class ListCommand extends CommandBase {

    public ListCommand() {
        super("list", "Get a list of all registered effects", "donationeffects.list", new String[]{"effects"}, 0, 0);
    }

    @Override
    public void execute(CommandSender sender, String[] args) throws NotImplementedException {
        List<String> list = new ArrayList<>();
        for (Effect effect : Core.getInstance().getEffectLoader().getEffectList()) {
            list.add(effect.getType().getName());
        }
        sender.sendMessage(ChatUtils.format("&a&l[!] &aEffects: " + String.join("&7, &a", list)));
    }
}
