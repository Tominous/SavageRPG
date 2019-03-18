package net.prosavage.savageequipment.utils;

import net.prosavage.savageequipment.SavageEquipment;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Weapons {


    FileConfiguration weaponConfig = SavageEquipment.getInstance().getConfig();
    Number Number = new Number();
    Color Color = new Color();

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

}
