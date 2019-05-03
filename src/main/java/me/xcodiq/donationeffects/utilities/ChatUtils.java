package me.xcodiq.donationeffects.utilities;

import org.bukkit.ChatColor;

public class ChatUtils {

    /**
     * Get a formatted and color-coded string
     *
     * @param string The string to format and colorcode
     * @return the formatted and color-coded string
     * @see org.bukkit.ChatColor#translateAlternateColorCodes(char, String)
     */
    public static String format(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    /**
     * Get a formatted string without brackets if included
     *
     * @param text The string to format
     * @return the formatted and bracket-less string
     */
    public static String removeBrackets(String text) {
        return text.replaceAll("[\\[\\](){}]", "");
    }
}
