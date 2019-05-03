package me.xcodiq.donationeffects;

import me.xcodiq.donationeffects.commands.base.CommandHandler;
import me.xcodiq.donationeffects.effects.EffectLoader;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
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
     * Send title to a in-game player
     *
     * @param player   player to send title to
     * @param title    the big text in screen
     * @param subTitle the sub text in screen
     * @param fadeIn   fade in of title
     * @param stay     stay time of title
     * @param fadeOut  fade out of title
     */
    public void sendTitle(Player player, String title, String subTitle, int fadeIn, int stay, int fadeOut) {
        CraftPlayer craftplayer = (CraftPlayer) player;
        PlayerConnection connection = craftplayer.getHandle().playerConnection;

        IChatBaseComponent titleJSON = IChatBaseComponent.ChatSerializer.a("{'text': '" + title + "'}");
        IChatBaseComponent subtitleJSON = IChatBaseComponent.ChatSerializer.a("{'text': '" + subTitle + "'}");

        PacketPlayOutTitle titlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, titleJSON, fadeIn, stay, fadeOut);
        PacketPlayOutTitle subtitlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, subtitleJSON);

        connection.sendPacket(titlePacket);
        connection.sendPacket(subtitlePacket);
    }

    /**
     * @return An instance of the effect loader {@link EffectLoader}
     */
    public EffectLoader getEffectLoader() {
        return effectLoader;
    }
}
