package me.xcodiq.donationeffects.commands.base;

import me.xcodiq.donationeffects.Core;
import me.xcodiq.donationeffects.commands.AboutCommand;
import me.xcodiq.donationeffects.commands.HelpCommand;
import me.xcodiq.donationeffects.commands.ListCommand;
import me.xcodiq.donationeffects.commands.ReloadCommand;
import me.xcodiq.donationeffects.effects.Effect;
import me.xcodiq.donationeffects.utilities.ChatUtils;
import me.xcodiq.donationeffects.utilities.IHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommandHandler implements CommandExecutor, TabCompleter, IHandler {

    private static List<CommandBase> commands;
    private static CommandHandler instance = new CommandHandler();
    private FileConfiguration config = Core.getInstance().getConfig();

    /**
     * @return Static instance of this class {@link CommandHandler}
     */
    public static CommandHandler getInstance() {
        return instance;
    }

    @Override
    public void enable(Core core) {

        commands = new ArrayList<>();
        core.getCommand("donation").setExecutor(this);

        Stream.of(
                new HelpCommand(), new ReloadCommand(), new AboutCommand(), new ListCommand()
        ).forEach(this::register);

        core.getLogger().info("Enabling commands...");
    }

    @Override
    public void disable(Core core) {
        commands.clear();
        commands = null;
        core.getLogger().info("Disabling commands...");
    }

    private void register(CommandBase command) {
        commands.add(command);
    }

    /**
     * @return List of all registered sub commands {@link CommandBase}
     */
    public List<CommandBase> getCommands() {
        return commands;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!cmd.getName().equalsIgnoreCase("donation")) {
            return true;
        }
        if (args.length == 0 || args[0].isEmpty()) {
            sender.sendMessage(ChatUtils.format("&b&l[!] &bType &n/donation help&b for help"));
        } else if (args.length == 1) {
            if (!sender.hasPermission("donationeffects.use")) {
                sender.sendMessage(ChatUtils.format("&c&l[!] &cYou don't have access to that command"));
                return true;
            } else {
                Player target = Bukkit.getPlayerExact(args[0]);
                if (target == null) {
                    for (CommandBase command : commands) {
                        if (!command.getName().equalsIgnoreCase(args[0]) && !command.getAliases().contains(args[0].toLowerCase())) {
                            continue;
                        }
                        if (!sender.hasPermission(command.getPermission())) {
                            sender.sendMessage(ChatUtils.format("&c&l[!] &cYou don't have access to that command"));
                            return true;
                        }
                        args = Arrays.copyOfRange(args, 1, args.length);

                        if ((command.getMinimumArguments() != -1 && command.getMinimumArguments() > args.length)
                                || (command.getMaximumArguments() != -1 && command.getMaximumArguments() < args.length)) {
                            return true;
                        }
                        command.execute(sender, args);
                        return true;
                    }
                    return true;
                } else for (String lines : config.getStringList("messages.donated")) {
                    Bukkit.broadcastMessage(ChatUtils.format(lines).replaceAll("%target%", target.getName()));
                }
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.sendTitle(ChatUtils.format(config.getString("messages.titles.title")
                            .replaceAll("%target%", target.getName())), ChatUtils.format(config.getString("messages.titles.subtitle")
                            .replaceAll("%target%", target.getName())));
                    for (Effect effect : Core.getInstance().getEffectLoader().getEffectList()) {
                        players.addPotionEffect(new PotionEffect(effect.getType(), effect.getTime(), effect.getLevel()), true);
                    }
                }
            }
            return true;
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("donation")) {
            if (args.length == 1) {
                List<String> commandNames = new ArrayList<>();
                if (!args[0].equals("")) {
                    for (String commandName : commands.stream().map(CommandBase::getName).collect(Collectors.toList())) {
                        if (!commandName.startsWith(args[0].toLowerCase())) {
                            continue;
                        }
                        commandNames.add(commandName);
                    }
                } else {
                    commandNames = commands.stream().map(CommandBase::getName).collect(Collectors.toList());
                }
                Collections.sort(commandNames);
                return commandNames;
            }
        }
        return null;
    }

}
