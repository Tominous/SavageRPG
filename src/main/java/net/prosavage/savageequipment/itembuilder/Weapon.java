package net.prosavage.savageequipment.itembuilder;

import net.prosavage.savageequipment.SavageEquipment;
import net.prosavage.savageequipment.utils.*;
import net.prosavage.savageequipment.utils.Number;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class Weapon {

    net.prosavage.savageequipment.utils.Color Color = new Color();
    net.prosavage.savageequipment.utils.Number Number = new Number();
    net.prosavage.savageequipment.utils.Chance Chance = new Chance();
    net.prosavage.savageequipment.utils.Formula Formula = new Formula();
    net.prosavage.savageequipment.utils.Placeholder Placeholder = new Placeholder();
    FileConfiguration WeaponValues = SavageEquipment.getInstance().getWeaponConfig();
    FileConfiguration SEConfig = SavageEquipment.getInstance().getConfig();

    public ItemStack getNewWeapon(){
        ItemStack Item = null;
        LinkedList<ItemStack> items = new LinkedList<ItemStack>();

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

        double dmHighest = 0.0;
        double dmLowest = 0.0;
        double clHighest = 0.0;
        double clLowest = 0.0;
        int geHighest = 3;
        int geLowest = 1;
        String rarity = "undefined";
        String itemType = "";

        boolean chanceOf = false;

        List<String> lore = new ArrayList<String>();

        ItemMeta meta = item.getItemMeta();

        LinkedList<String> itemlist = new LinkedList<String>();

        itemlist.addAll(Objects.requireNonNull(SavageEquipment.getInstance().getWeaponConfig().getConfigurationSection("rarity")).getKeys(false));

        for (int i = 0; i < 10; i++) {

            int randomNumber = Number.getInteger(0, itemlist.size() - 1);
            String string = itemlist.get(randomNumber);
            itemType = string;

            SavageEquipment.getInstance().sendConsole(randomNumber + " " + string);

            dmHighest = Double.valueOf(String.valueOf(WeaponValues.get("rarity." + string + ".max-damage")));
            dmLowest = Double.valueOf(String.valueOf(WeaponValues.get("rarity." + string + ".min-damage")));

            clHighest = Double.valueOf(String.valueOf(WeaponValues.get("rarity." + string + ".max-cooldown")));
            clLowest = Double.valueOf(String.valueOf(WeaponValues.get("rarity." + string + ".min-cooldown")));

            geHighest = Integer.valueOf(String.valueOf(WeaponValues.get("rarity." + string + ".max-gem")));
            geLowest = Integer.valueOf(String.valueOf(WeaponValues.get("rarity." + string + ".min-gem")));

            chanceOf = Chance.ofDouble(Double.parseDouble(String.valueOf(WeaponValues.get("rarity." + itemType + ".chance"))));
            if (chanceOf) {
                rarity = itemType;
                i = 10;
            }
        }
        if (chanceOf) {
            String level = String.valueOf(Number.getInteger(1, 100));

            String damageHighest = String.format("%.2f", Number.getDouble(dmLowest, dmHighest));

            String damageLowest = String.format("%.2f", Number.getDouble(dmLowest, Double.valueOf(damageHighest)));

            String cooldown = String.format("%.2f", Number.getDouble(clLowest, clHighest));

            String gem = String.valueOf(Number.getInteger(geLowest, geHighest));

            boolean noGem = false;

            if (Integer.valueOf(gem) == 0) {
                noGem = true;
            }

            lore = SEConfig.getStringList("weapon.lore");

            double evaluated = Formula.eval(Placeholder.getDamagePerSecondPlaceholders(item, damageHighest, damageLowest, cooldown, String.valueOf(WeaponValues.get("rarity." + itemType + ".damage-per-second"))));

            String itemName = item.getType().toString().replace("_", " ").replace("(?:(?<=^)|(?<=[^\\w]))\\w", "\\U$0\\E");

            for (int i = 0; i < lore.size(); i++) {
                String string = lore.get(i);
                string = string.replace("{weapon-type}", itemName);
                string = string.replace("{weapon-rarity}", rarity);
                string = string.replace("{weapon-class}", "test");
                string = string.replace("{weapon-required-level}", level);
                string = string.replace("{weapon-min-damage}", damageLowest);
                string = string.replace("{weapon-max-damage}", damageHighest);
                string = string.replace("{weapon-cooldown}", cooldown);
                string = string.replace("{weapon-damage-per-seconds}", String.format("%.2f", evaluated));

                if (noGem) {
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

            assert meta != null;
            meta.setLore(lore);
            item.setItemMeta(meta);


            return item;
        }
        return new ItemStack(Material.AIR);
    }
}
