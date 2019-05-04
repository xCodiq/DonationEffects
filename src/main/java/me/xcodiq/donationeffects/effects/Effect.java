package me.xcodiq.donationeffects.effects;

import org.bukkit.potion.PotionEffectType;

public class Effect {

    private String id;
    private PotionEffectType type;
    private int level;
    private int time;

    public Effect(String id, PotionEffectType type, int level, int time) {
        this.id = id;
        this.type = type;
        this.level = level;
        this.time = time;
    }

    /**
     * @return PotionEffect unique Id
     */
    public String getId() {
        return id;
    }

    /**
     * @return PotionEffect type {@link PotionEffectType}
     */
    public PotionEffectType getType() {
        return type;
    }

    /**
     * @return PotionEffect level
     */
    public int getLevel() {
        return level;
    }

    /**
     * @return PotionEffect duration in seconds
     */
    public int getTime() {
        return time;
    }

}
