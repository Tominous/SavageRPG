package me.kingalteriv.pragmata.Builder;

import me.kingalteriv.pragmata.SomewhatUsefulStuff.Color;
import me.kingalteriv.pragmata.SomewhatUsefulStuff.RandomNum;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Item {

    Color Color = new Color();
    RandomNum Random = new RandomNum();

    public Double getDamage(ItemStack item) {
        if (item == null) return Double.valueOf(0);

        if (!item.hasItemMeta()) return Double.valueOf(0);

        ItemMeta meta = item.getItemMeta();
        if (!((ItemMeta) meta).hasLore()) return Double.valueOf(0);

        String line = null;
        for (String string : meta.getLore()) {
            if (string.contains("Damage: ") == true) {
                line = Color.strip(string).replace("Damage: ", "");
                String[] damages = line.split("[-]");

                Double minDamage = Double.valueOf(Double.parseDouble(damages[0]));
                Double maxDamage = Double.valueOf(Double.parseDouble(damages[1]));

                double randomDamage = Random.getDouble(minDamage, maxDamage);

                return randomDamage;
            }
            return Double.valueOf(0);

        }
        return Double.valueOf(0);
    }
}
