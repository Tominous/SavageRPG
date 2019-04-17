package net.prosavage.savageequipment.values;

import net.prosavage.savageequipment.SavageEquipment;
import net.prosavage.savageequipment.utils.Color;
import net.prosavage.savageequipment.utils.Number;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class Weapons {


    FileConfiguration weaponConfig = SavageEquipment.getInstance().getConfig();
    net.prosavage.savageequipment.utils.Number Number = new Number();
    net.prosavage.savageequipment.utils.Color Color = new Color();

    public double getDamageLore(ItemStack item){
        if (item == null) return 0.0;

        if (!item.hasItemMeta()) return 0.0;

        ItemMeta meta = item.getItemMeta();
        if (!(meta).hasLore()) return 0.0;

        for (String string : meta.getLore()){
            if (Color.strip(string).contains(weaponConfig.get("weapon.damage-clear").toString())){
                String line = Color.strip(string).replace(weaponConfig.get("weapon.damage-clear").toString(), "");
                String[] damages = line.split(weaponConfig.get("weapon.damage-split").toString());
                double minDamage = Double.parseDouble(damages[0]);
                double maxDamage = Double.parseDouble(damages[1]);
                double randomDamage = Number.getDouble(minDamage, maxDamage);
                return randomDamage;
            }
        }

        return 0.0;
    }

    public double getCooldownLore(ItemStack item){
        if (item == null) return 0.0;

        if (!item.hasItemMeta()) return 0.0;

        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        if (!(meta).hasLore()) return 0.0;

        for (String string : Objects.requireNonNull(meta.getLore())){
            if (Color.strip(string).contains(weaponConfig.get("weapon.cooldown-clear").toString())){
                String line = Color.strip(string).replace(weaponConfig.get("weapon.cooldown-clear").toString(), "");
                double cooldown = Double.parseDouble(line);
                return cooldown;
            }
        }

        return 0.0;
    }
}
