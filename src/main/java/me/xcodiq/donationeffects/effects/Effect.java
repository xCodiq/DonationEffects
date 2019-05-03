package me.xcodiq.donationeffects.effects;

import org.bukkit.potion.PotionEffectType;

public class Effect {

    private PotionEffectType type;
    private int time;
    private int level;

    public Effect(PotionEffectType type, int time, int level) {
        this.type = type;
        this.time = time;
        this.level = level;
    }

    /**
     * @return PotionEffect type {@link PotionEffectType}
     */
    public PotionEffectType getType() {
        return type;
    }

    /**
     * @return PotionEffect duration in seconds
     */
    public int getTime() {
        return time;
    }

    /**
     * @return PotionEffect level
     */
    public int getLevel() {
        return level;
    }

}
