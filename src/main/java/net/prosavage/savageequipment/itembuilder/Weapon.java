package net.prosavage.savageequipment.itembuilder;

import net.prosavage.savageequipment.SavageEquipment;
import net.prosavage.savageequipment.utils.Color;
import net.prosavage.savageequipment.utils.Number;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Weapon {

    Color Color = new Color();
    Number Number = new Number();
    FileConfiguration weaponConfig = YamlConfiguration.loadConfiguration(new File(SavageEquipment.getInstance().getDataFolder(), "weapon.yml"));

    public ItemStack getNewWeapon(){
        ItemStack Item = null;
        LinkedList<ItemStack> items = new LinkedList();

        items.add(new ItemStack(Material.WOODEN_SWORD));
        items.add(new ItemStack(Material.STONE_SWORD));
        items.add(new ItemStack(Material.IRON_SWORD));
        items.add(new ItemStack(Material.GOLDEN_SWORD));
        items.add(new ItemStack(Material.DIAMOND_SWORD));

        Collections.shuffle(items);

        Item = items.pop();
        return Item;
    }

    public ItemStack setItem(ItemStack item) {

        Double dmHighest = 0.0;
        Double dmLowest = 0.0;
        Double clHighest = 0.0;
        Double clLowest = 0.0;
        Integer geHighest = 3;
        Integer geLowest = 1;
        String rarity = "broken";

        ItemMeta meta = item.getItemMeta();

        List<String> itemlist = new ArrayList<String>();
        itemlist.add(0, "wooden");
        itemlist.add(1, "stone");
        itemlist.add(2, "iron");
        itemlist.add(3, "golden");
        itemlist.add(4, "diamond");

        for (String string : itemlist) {
            if (item.getType().toString().toLowerCase().contains(string)) {

                dmHighest = (Double) weaponConfig.get("material." + string + ".max-damage");
                dmLowest = (Double) weaponConfig.get("material." + string + ".min-damage");

                clHighest = (Double) weaponConfig.get("material." + string + ".max-cooldown");
                clLowest = (Double) weaponConfig.get("material." + string + ".min-cooldown");

                geHighest = (Integer) weaponConfig.get("material." + string + ".max-gem");
                geLowest = (Integer) weaponConfig.get("material." + string + ".min-gem");

            }
        }

        String level = String.valueOf(Number.getInteger(1, 100));

        String damageLowest = String.format("%.2f", Number.getDouble(dmLowest, dmHighest));

        String damageHighest = String.format("%.2f", Number.getDouble(dmLowest, Double.valueOf(damageLowest)));

        String cooldown = String.format("%.2f", Number.getDouble(clLowest, clHighest));

        String gem = String.valueOf(Number.getInteger(geLowest, geHighest));

        List<String> lore = SavageEquipment.getInstance().getConfig().getStringList("weapon.lore");

        boolean noGem = false;

        if (Integer.valueOf(gem) == 0){
            noGem = true;
        }

        for (int i = 0; i < lore.size(); i++){
            String string = lore.get(i);
            string = string.replace("{weapon-type}", item.getType().toString());
            string = string.replace("{weapon-rarity}", rarity);
            string = string.replace("{weapon-class}", "test");
            string = string.replace("{weapon-required-level}", level);
            string = string.replace("{weapon-min-damage}", damageLowest);
            string = string.replace("{weapon-max-damage}", damageHighest);
            string = string.replace("{weapon-cooldown}", cooldown);
            string = string.replace("{weapon-damage-per-seconds}", String.format("%.2f", (Double.valueOf(damageLowest) + Double.valueOf(damageHighest)) / 2 / Double.valueOf(cooldown)));

            if (noGem){
                string = string.replace("{weapon-gem-sockets}", "");
            }

            lore.set(i, Color.ify(string));

            if (!(noGem)) {
                if (string.contains("{weapon-gem-sockets}")) {
                    String gemName = lore.get(i);
                    int gemIndex = lore.indexOf(gemName);
                    lore.remove(gemName);
                    lore.remove(gemIndex);
                    for (int a = 0; a < Integer.valueOf(gem); a++) {
                        if (Integer.valueOf(gem) != 0) {
                            lore.add(Color.ify("&7» [ Gems Socket # " + a + " ]"));
                        }
                    }
                }
            }

        }

        meta.setLore(lore);
        item.setItemMeta(meta);


        return item;
    }
}
