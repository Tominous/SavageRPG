package net.prosavage.savageequipment.itembuilder;

import net.prosavage.savageequipment.SavageEquipment;
import net.prosavage.savageequipment.utils.Chance;
import net.prosavage.savageequipment.utils.Color;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import net.prosavage.savageequipment.utils.Number;
import java.io.File;
import java.util.*;

public class Armor {

    Color Color = new Color();
    Number Number = new Number();
    FileConfiguration armorConfig = SavageEquipment.getInstance().getArmorConfig();
    Chance Chance = new Chance();


    public ItemStack getNewArmor(){
        ItemStack Item = null;
        LinkedList<ItemStack> items = new LinkedList();

        items.add(new ItemStack(Material.LEATHER_HELMET));
        items.add(new ItemStack(Material.LEATHER_CHESTPLATE));
        items.add(new ItemStack(Material.LEATHER_LEGGINGS));
        items.add(new ItemStack(Material.LEATHER_BOOTS));

        items.add(new ItemStack(Material.GOLDEN_HELMET));
        items.add(new ItemStack(Material.GOLDEN_CHESTPLATE));
        items.add(new ItemStack(Material.GOLDEN_LEGGINGS));
        items.add(new ItemStack(Material.GOLDEN_BOOTS));

        items.add(new ItemStack(Material.CHAINMAIL_HELMET));
        items.add(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
        items.add(new ItemStack(Material.CHAINMAIL_LEGGINGS));
        items.add(new ItemStack(Material.CHAINMAIL_BOOTS));

        items.add(new ItemStack(Material.IRON_HELMET));
        items.add(new ItemStack(Material.IRON_CHESTPLATE));
        items.add(new ItemStack(Material.IRON_LEGGINGS));
        items.add(new ItemStack(Material.IRON_BOOTS));

        items.add(new ItemStack(Material.DIAMOND_HELMET));
        items.add(new ItemStack(Material.DIAMOND_CHESTPLATE));
        items.add(new ItemStack(Material.DIAMOND_LEGGINGS));
        items.add(new ItemStack(Material.DIAMOND_BOOTS));

        Collections.shuffle(items);

        Item = items.pop();
        return Item;
    }

    public ItemStack setItem(ItemStack item) {

        double hpHighest = 0.0;
        double hpLowest = 0.0;
        double prHighest = 0.0;
        double prLowest = 0.0;
        double reHighest = 0.0;
        double reLowest = 0.0;
        int geHighest = 3;
        int geLowest = 1;
        String rarity = "broken";
        String itemType = "";

        boolean chanceOf = false;

        ItemMeta meta = item.getItemMeta();

        LinkedList<String> itemlist = new LinkedList<String>();

        itemlist.addAll(Objects.requireNonNull(SavageEquipment.getInstance().getWeaponConfig().getConfigurationSection("rarity")).getKeys(false));

        for (int i = 0; i < 10; i++) {

            Integer randomNumber = Number.getInteger(0, itemlist.size() - 1);
            String string = itemlist.get(randomNumber);
            itemType = string;

            prHighest = Double.valueOf(String.valueOf(armorConfig.get("rarity." + string + ".max-protection")));
            prLowest = Double.valueOf(String.valueOf(armorConfig.get("rarity." + string + ".min-protection")));

            hpHighest = Double.valueOf(String.valueOf(armorConfig.get("rarity." + string + ".max-health")));
            hpLowest = Double.valueOf(String.valueOf(armorConfig.get("rarity." + string + ".min-health")));

            reHighest = Double.valueOf(String.valueOf(armorConfig.get("rarity." + string + ".max-regen")));
            reLowest = Double.valueOf(String.valueOf(armorConfig.get("rarity." + string + ".min-regen")));

            geHighest = Integer.valueOf(String.valueOf(armorConfig.get("rarity." + string + ".max-gem")));
            geLowest = Integer.valueOf(String.valueOf(armorConfig.get("rarity." + string + ".min-gem")));

            chanceOf = Chance.ofDouble(Double.parseDouble(String.valueOf(armorConfig.get("rarity." + itemType + ".chance"))));
            if (chanceOf) {
                rarity = itemType;
                i = 10;
            }
        }

        if (chanceOf) {
            String level = String.valueOf(Number.getInteger(1, 100));

            String protection = String.format("%.2f", Number.getDouble(prLowest, prHighest));

            String health = String.format("%.2f", Number.getDouble(hpLowest, hpHighest));

            String regen = String.format("%.2f", Number.getDouble(reLowest, reHighest));

            String gem = String.valueOf(Number.getInteger(geLowest, geHighest));

            List<String> lore = SavageEquipment.getInstance().getConfig().getStringList("armor.lore");

            boolean noGem = false;

            if (Integer.valueOf(gem) == 0) {
                noGem = true;
            }

            for (int i = 0; i < lore.size(); i++) {
                String string = lore.get(i);
                string = string.replace("{armor-type}", item.getType().toString());
                string = string.replace("{armor-rarity}", rarity);
                string = string.replace("{armor-class}", "test");
                string = string.replace("{armor-required-level}", level);
                string = string.replace("{armor-protection}", protection);
                string = string.replace("{armor-health}", health);
                string = string.replace("{armor-regen}", regen);

                if (noGem) {
                    string = string.replace("{armor-gem-sockets}", "");
                }

                lore.set(i, Color.ify(string));

                if (!(noGem)) {
                    if (string.contains("{armor-gem-sockets}")) {
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

            assert meta != null;
            meta.setLore(lore);
            item.setItemMeta(meta);

            return item;
        }
        return new ItemStack(Material.AIR);
    }
}