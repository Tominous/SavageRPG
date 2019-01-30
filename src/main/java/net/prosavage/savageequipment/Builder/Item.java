package net.prosavage.savageequipment.builder;

import net.prosavage.savageequipment.somewhatusefulstuff.Color;
import net.prosavage.savageequipment.somewhatusefulstuff.RandomNum;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Item {

    Color Color = new Color();
    RandomNum Random = new RandomNum();

    public Double getDamage(ItemStack item) {
        if (item == null) return 0.0;

        if (!item.hasItemMeta()) return 0.0;

        ItemMeta meta = item.getItemMeta();
        if (!(meta).hasLore()) return 0.0;

        String line = null;
        for (String string : meta.getLore()) {
            if (string.contains("Damage: ")) {
                line = Color.strip(string).replace("Damage: ", "");
                String[] damages = line.split("[-]");

                Double minDamage = Double.parseDouble(damages[0]);
                Double maxDamage = Double.parseDouble(damages[1]);

                double randomDamage = Random.getDouble(minDamage, maxDamage);

                return randomDamage;
            }
            return 0.0;

        }
        return 0.0;
    }
}
