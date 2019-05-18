package net.prosavage.savagerpg.itembuilder;

import net.prosavage.savagerpg.SavageRPG;
import net.prosavage.savagerpg.utils.Number;
import net.prosavage.savagerpg.utils.*;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Weapon {

    Color Color = new Color();
    Number Number = new Number();
    Chance Chance = new Chance();
    Formula Formula = new Formula();
    Placeholder Placeholder = new Placeholder();
    FileConfiguration WeaponValues = SavageRPG.getInstance().getWeaponConfig();
    FileConfiguration SRConfig = SavageRPG.getInstance().getConfig();

    NamespacedKey NSK_WEAPON_TYPE = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Weapon-Type");
    NamespacedKey NSK_WEAPON_RARITY = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Weapon-Rarity");
    NamespacedKey NSK_WEAPON_CLASS = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Weapon-Class");
    NamespacedKey NSK_WEAPON_LEVEL = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Weapon-Level");
    NamespacedKey NSK_WEAPON_DAMAGE_MIN = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Weapon-Damage-Min");
    NamespacedKey NSK_WEAPON_DAMAGE_MAX = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Weapon-Damage-Max");
    NamespacedKey NSK_WEAPON_COOLDOWN = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Weapon-Damage-Max");
    NamespacedKey NSK_WEAPON_DAMAGE_PER_SECOND = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Weapon-Damage-Per-Second");
    NamespacedKey NSK_WEAPON_GEM = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Weapon-Gem");
    NamespacedKey NSK_WEAPON_HAVE_GEM = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Weapon-NoGem");


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

        ItemMeta meta = item.getItemMeta();
        assert meta != null;


        LinkedList<String> itemlist = new LinkedList<String>();

        itemlist.addAll(Objects.requireNonNull(SavageRPG.getInstance().getWeaponConfig().getConfigurationSection("rarity")).getKeys(false));


        for (int i = 0; i < 10; i++) {

            int randomNumber = Number.getInteger(0, itemlist.size() - 1);
            String string = itemlist.get(randomNumber);
            itemType = string;

            SavageRPG.getInstance().sendConsole(randomNumber + " " + string);

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
            int level = Number.getInteger(1, 100);

            double damageHighest = Double.valueOf(String.format("%.2f", Number.getDouble(dmLowest, dmHighest)));

            double damageLowest = Double.valueOf(String.format("%.2f", Number.getDouble(dmLowest, damageHighest)));

            double cooldown = Double.valueOf(String.format("%.2f", Number.getDouble(clLowest, clHighest)));

            int gem = Integer.valueOf(String.valueOf(Number.getInteger(geLowest, geHighest)));

            boolean noGem = false;

            if (gem == 0) {
                noGem = true;
            }

            double evaluated = Formula.eval(Placeholder.getDamagePerSecondPlaceholders(item, String.valueOf(damageHighest), String.valueOf(damageLowest), String.valueOf(cooldown), String.valueOf(WeaponValues.get("rarity." + itemType + ".damage-per-second"))));

            String itemName = item.getType().toString().replace("_", " ").replace("(?:(?<=^)|(?<=[^\\w]))\\w", "\\U$0\\E");

            meta.getPersistentDataContainer().set(NSK_WEAPON_TYPE, PersistentDataType.STRING, itemName);
            meta.getPersistentDataContainer().set(NSK_WEAPON_RARITY, PersistentDataType.STRING, rarity);
            meta.getPersistentDataContainer().set(NSK_WEAPON_CLASS, PersistentDataType.STRING, "null");
            meta.getPersistentDataContainer().set(NSK_WEAPON_LEVEL, PersistentDataType.INTEGER, level);
            meta.getPersistentDataContainer().set(NSK_WEAPON_DAMAGE_MIN, PersistentDataType.DOUBLE, damageLowest);
            meta.getPersistentDataContainer().set(NSK_WEAPON_DAMAGE_MAX, PersistentDataType.DOUBLE, damageHighest);
            meta.getPersistentDataContainer().set(NSK_WEAPON_COOLDOWN, PersistentDataType.DOUBLE, cooldown);
            meta.getPersistentDataContainer().set(NSK_WEAPON_DAMAGE_PER_SECOND, PersistentDataType.DOUBLE, evaluated);
            meta.getPersistentDataContainer().set(NSK_WEAPON_GEM, PersistentDataType.INTEGER, gem);
            meta.getPersistentDataContainer().set(NSK_WEAPON_HAVE_GEM, PersistentDataType.STRING, String.valueOf(noGem));

            item.setItemMeta(meta);

            return item;
        }
        return new ItemStack(Material.AIR);
    }

    public ItemStack setLore(ItemStack item) {
        if (item.getType() != Material.AIR) {
            ItemMeta meta = item.getItemMeta();
            assert meta != null;

            String itemName = meta.getPersistentDataContainer().get(NSK_WEAPON_TYPE, PersistentDataType.STRING);
            String rarity = meta.getPersistentDataContainer().get(NSK_WEAPON_RARITY, PersistentDataType.STRING);
            String classType = meta.getPersistentDataContainer().get(NSK_WEAPON_CLASS, PersistentDataType.STRING);
            Integer level = meta.getPersistentDataContainer().get(NSK_WEAPON_LEVEL, PersistentDataType.INTEGER);
            Double damageLowest = meta.getPersistentDataContainer().get(NSK_WEAPON_DAMAGE_MIN, PersistentDataType.DOUBLE);
            Double damageHighest = meta.getPersistentDataContainer().get(NSK_WEAPON_DAMAGE_MAX, PersistentDataType.DOUBLE);
            Double cooldown = meta.getPersistentDataContainer().get(NSK_WEAPON_COOLDOWN, PersistentDataType.DOUBLE);
            Double damagePerSecond = meta.getPersistentDataContainer().get(NSK_WEAPON_DAMAGE_PER_SECOND, PersistentDataType.DOUBLE);
            Integer gem = meta.getPersistentDataContainer().get(NSK_WEAPON_GEM, PersistentDataType.INTEGER);
            String noGem = meta.getPersistentDataContainer().get(NSK_WEAPON_HAVE_GEM, PersistentDataType.STRING);

            assert gem != null;

            List<String> lore = SavageRPG.getInstance().getConfig().getStringList("weapon.lore");


            for (int i = 0; i < lore.size(); i++) {
                String string = lore.get(i);
                if ((itemName != null) && (rarity != null) && (classType != null)) {
                    string = string.replace("{weapon-type}", itemName);
                    string = string.replace("{weapon-rarity}", rarity);
                    string = string.replace("{weapon-class}", classType);
                    string = string.replace("{weapon-required-level}", String.valueOf(level));
                    string = string.replace("{weapon-min-damage}", String.valueOf(damageLowest));
                    string = string.replace("{weapon-max-damage}", String.valueOf(damageHighest));
                    string = string.replace("{weapon-cooldown}", String.valueOf(cooldown));
                    string = string.replace("{weapon-damage-per-seconds}", String.format("%.2f", damagePerSecond));

                    if (Boolean.valueOf(String.valueOf(noGem))) {
                        string = string.replace("{weapon-gem-sockets}", "");
                    }

                    lore.set(i, Color.ify(string));

                    if (!(Boolean.valueOf(String.valueOf(noGem))) && string.contains("{weapon-gem-sockets}")) {
                        String gemName = lore.get(i);
                        int gemIndex = lore.indexOf(gemName);
                        lore.remove(gemName);
                        lore.remove(gemIndex);
                        for (int a = 0; a < gem; a++) lore.add(Color.ify("&7» [ Gems Socket # " + a + " ]"));
                    }
                }
            }
            meta.setLore(lore);
            item.setItemMeta(meta);
            return item;
        }
        return new ItemStack(Material.AIR);

    }


}
