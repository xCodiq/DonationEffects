package me.xcodiq.donationeffects;

import me.xcodiq.donationeffects.commands.base.CommandHandler;
import me.xcodiq.donationeffects.effects.EffectLoader;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {

    private static Core instance;
    private CommandHandler commandHandler;
    private EffectLoader effectLoader;

    /**
     * @return Static instance of this class {@link Core}
     */
    public static Core getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {

        instance = this;
        loadFiles();

        commandHandler = new CommandHandler();
        commandHandler.enable(this);

        effectLoader = new EffectLoader();
        this.getLogger().info("Registered " + effectLoader.getEffectList().size() + " effects");
    }

    @Override
    public void onDisable() {
        instance = null;
        commandHandler.disable(this);
    }

    /**
     * Load YAML files
     *
     * @see JavaPlugin#saveDefaultConfig()
     */
    private void loadFiles() {
        this.saveDefaultConfig();
        this.getLogger().info("Loading files... [YAML]");
    }

    /**
     * @return An instance of the effect loader {@link EffectLoader}
     */
    public EffectLoader getEffectLoader() {
        return effectLoader;
    }
}
