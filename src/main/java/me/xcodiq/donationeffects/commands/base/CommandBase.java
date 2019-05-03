package me.xcodiq.donationeffects.commands.base;

import org.bukkit.command.CommandSender;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class CommandBase {

    private String name;
    private String description;
    private String permission;

    private List<String> aliases;

    private int minimumArguments;
    private int maximumArguments;

    public CommandBase(String name, String description, String permission, String[] aliases, int minimumArguments, int maximumArguments) {
        this.name = name;
        this.description = description;
        this.permission = permission;

        this.aliases = aliases == null ? new ArrayList<>() : Arrays.asList(aliases);

        this.minimumArguments = minimumArguments;
        this.maximumArguments = maximumArguments;
    }

    public abstract void execute(CommandSender sender, String[] args) throws NotImplementedException;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPermission() {
        return permission;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public int getMinimumArguments() {
        return minimumArguments;
    }

    public int getMaximumArguments() {
        return maximumArguments;
    }
}
