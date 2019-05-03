package me.xcodiq.donationeffects.effects;

import me.xcodiq.donationeffects.Core;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class EffectLoader {

    private List<Effect> effectList = new ArrayList<>();

    public EffectLoader() {
        FileConfiguration config = Core.getInstance().getConfig();
        for (String key : config.getConfigurationSection("effects").getKeys(false)) {
            Effect effect;
            PotionEffectType potionEffectType = PotionEffectType.getByName(config.getString("effects." + key + ".type"));
            int level = config.getInt("effects." + key + ".level");
            int time = config.getInt("effects." + key + ".time");
            effect = new Effect(potionEffectType, time * 20, level);
            effectList.add(effect);
        }
    }

    /**
     * @return Registered effects
     */
    public List<Effect> getEffectList() {
        return effectList;
    }
}
