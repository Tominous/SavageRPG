package net.prosavage.savagerpg.itembuilder;


import net.prosavage.savagerpg.SavageRPG;
import net.prosavage.savagerpg.api.SNamespacedKey;
import net.prosavage.savagerpg.utils.Chance;
import net.prosavage.savagerpg.utils.Color;
import net.prosavage.savagerpg.utils.Number;
import net.prosavage.savagerpg.utils.Weapon;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public class Item {

    Weapon Weapon = new Weapon();
    Number Number = new Number();
    Chance Chance = new Chance();
    Color Color = new Color();
    SNamespacedKey SNamespacedKey = new SNamespacedKey();

    public ItemStack getNewWeapon() {

        List<String> itemRarity = new ArrayList<String>(SavageRPG.getInstance().getRarityNames());
        String selectedRarity = null;
        String randomWeaponName = null;
        Boolean chanceOf = false;
        int randomNumber = 0;
        String string = "common";
        ItemStack weaponType = null;

        String itemType = null;
        String material = null;
        double minDamage = 0.0;
        double maxDamage = 0.0;
        int minLevel = 0;
        int maxLevel = 100;
        double minCooldown = 1.0;
        double maxCooldown = 2.0;
        int minGem = 0;
        int maxGem = 3;

        List<ItemStack> weapons = new ArrayList<>(Collections.singletonList(new ItemStack(Material.AIR)));
        weapons.clear();
        weapons.add(new ItemStack(Material.DIAMOND_SWORD));
        weapons.add(new ItemStack(Material.IRON_SWORD));
        weapons.add(new ItemStack(Material.GOLDEN_SWORD));
        weapons.add(new ItemStack(Material.STONE_SWORD));
        weapons.add(new ItemStack(Material.WOODEN_SWORD));


        randomNumber = Number.getInteger(0, weapons.size() - 1);
        weaponType = weapons.get(randomNumber);

        for (int i = 0; i < 10; i++) {
            randomNumber = Number.getInteger(0, itemRarity.size() - 1);
            string = itemRarity.get(randomNumber);
            SavageRPG.getInstance().sendConsole("&a" + string + " " + itemRarity.get(randomNumber));
            SavageRPG.getInstance().sendConsole("&b" + String.valueOf(SavageRPG.getInstance().getWeaponConfig().get("rarity." + string + ".chance")));
            chanceOf = Chance.ofDouble(Double.parseDouble(String.valueOf(SavageRPG.getInstance().getWeaponConfig().get("rarity." + string + ".chance"))));
            if (chanceOf) {
                selectedRarity = string;
                i = 10;
                SavageRPG.getInstance().sendConsole("&e" + chanceOf.toString());
            }
        }
        if (chanceOf) {
            List<String> weaponNames = SavageRPG.getInstance().getRarityWeaponNames(selectedRarity);
            SavageRPG.getInstance().sendConsole("&c" + weaponNames);
            assert weaponNames != null;
            randomNumber = Number.getInteger(0, weaponNames.size() - 1);
            string = weaponNames.get(randomNumber);
            String weaponName = string;
            SavageRPG.getInstance().sendConsole(string);
            weaponName = weaponName.replace(".yml", "");

            itemType = Weapon.getStat(selectedRarity, weaponName, "item-name");

            material = Weapon.getStat(selectedRarity, weaponName, "materials");

            minDamage = Double.valueOf(Weapon.getStat(selectedRarity, weaponName, "min-damage"));
            maxDamage = Double.valueOf(Weapon.getStat(selectedRarity, weaponName, "max-damage"));

            minLevel = Integer.valueOf(Weapon.getStat(selectedRarity, weaponName, "min-level"));
            maxLevel = Integer.valueOf(Weapon.getStat(selectedRarity, weaponName, "max-level"));

            minCooldown = Double.valueOf(Weapon.getStat(selectedRarity, weaponName, "min-cooldown"));
            maxCooldown = Double.valueOf(Weapon.getStat(selectedRarity, weaponName, "max-cooldown"));

            minGem = Integer.valueOf(Weapon.getStat(selectedRarity, weaponName, "min-gem"));
            maxGem = Integer.valueOf(Weapon.getStat(selectedRarity, weaponName, "max-gem"));

            SavageRPG.getInstance().sendConsole("&e&lITEM NAME - " + itemType);
            SavageRPG.getInstance().sendConsole("&e&lMATERIAL - " + material);
            SavageRPG.getInstance().sendConsole("&e&lMIN DAMAGE - " + minDamage);
            SavageRPG.getInstance().sendConsole("&e&lMAX DAMAGE - " + maxDamage);
            SavageRPG.getInstance().sendConsole("&e&lMIN LEVEL - " + minLevel);
            SavageRPG.getInstance().sendConsole("&e&lMAX LEVEL - " + maxLevel);
            SavageRPG.getInstance().sendConsole("&e&lMIN COOLDOWN - " + minCooldown);
            SavageRPG.getInstance().sendConsole("&e&lMAX COOLDOWN - " + maxCooldown);
            SavageRPG.getInstance().sendConsole("&e&lMIN GEM - " + minGem);
            SavageRPG.getInstance().sendConsole("&e&lMAX GEM - " + maxGem);


            double cooldown = 1;
            minDamage = Double.valueOf(String.format("%.2f", Number.getDouble(minDamage, maxDamage)));
            maxDamage = Double.valueOf(String.format("%.2f", Number.getDouble(minDamage, maxDamage)));
            int level = Number.getInteger(minLevel, maxLevel);
            cooldown = Double.valueOf(String.format("%.2f", Number.getDouble(minCooldown, maxCooldown)));
            int gem = Number.getInteger(minGem, maxGem);

            ItemMeta meta = weaponType.getItemMeta();
            meta.getPersistentDataContainer().set(SNamespacedKey.getWeaponType(), PersistentDataType.STRING, material);
            meta.getPersistentDataContainer().set(SNamespacedKey.getWeaponLevel(), PersistentDataType.INTEGER, level);
            meta.getPersistentDataContainer().set(SNamespacedKey.getWeaponRarity(), PersistentDataType.STRING, selectedRarity);
            meta.getPersistentDataContainer().set(SNamespacedKey.getWeaponMaxDamage(), PersistentDataType.DOUBLE, maxDamage);
            meta.getPersistentDataContainer().set(SNamespacedKey.getWeaponMinDamage(), PersistentDataType.DOUBLE, minDamage);
            meta.getPersistentDataContainer().set(SNamespacedKey.getWeaponCooldown(), PersistentDataType.DOUBLE, cooldown);
            meta.getPersistentDataContainer().set(SNamespacedKey.getWeaponGems(), PersistentDataType.INTEGER, gem);
            if (gem < 0){
                meta.getPersistentDataContainer().set(SNamespacedKey.getDoesWeaponHaveGems(), PersistentDataType.STRING, "true");
            }

            material = meta.getPersistentDataContainer().get(SNamespacedKey.getWeaponType(), PersistentDataType.STRING);
            String rarity = meta.getPersistentDataContainer().get(SNamespacedKey.getWeaponRarity(), PersistentDataType.STRING);
            String noGem = meta.getPersistentDataContainer().get(SNamespacedKey.getDoesWeaponHaveGems(), PersistentDataType.STRING);

            SavageRPG.getInstance().sendConsole("&aITEMNAME " + material);
            SavageRPG.getInstance().sendConsole("&aRARITY " + rarity);
            SavageRPG.getInstance().sendConsole("&aLEVEL " + level);
            SavageRPG.getInstance().sendConsole("&aLOW DAMAGE " + minDamage);
            SavageRPG.getInstance().sendConsole("&aHIGH DAMAGE " + maxDamage);
            SavageRPG.getInstance().sendConsole("&aCD " + cooldown);
            SavageRPG.getInstance().sendConsole("&aGEM " + gem);
            SavageRPG.getInstance().sendConsole("&aHAS GEM" + noGem);

            List<String> lore = SavageRPG.getInstance().getConfig().getStringList("weapon.lore");


            for (int i = 0; i < lore.size(); i++) {
                String s = lore.get(i);
                if ((material != null) && (rarity != null)) {
                    s = s.replace("{weapon-type}", material);
                    s = s.replace("{weapon-rarity}", rarity);
                    s = s.replace("{weapon-required-level}", String.valueOf(level));
                    s = s.replace("{weapon-min-damage}", String.valueOf(minDamage));
                    s = s.replace("{weapon-max-damage}", String.valueOf(maxDamage));
                    s = s.replace("{weapon-cooldown}", String.valueOf(cooldown));

                    if (Boolean.valueOf(String.valueOf(noGem))) {
                        s = s.replace("{weapon-gem-sockets}", "");
                    }

                    lore.set(i, Color.ify(s));

                    if (!(Boolean.valueOf(String.valueOf(noGem))) && s.contains("{weapon-gem-sockets}")) {
                        String gemName = lore.get(i);
                        int gemIndex = lore.indexOf(gemName);
                        lore.remove(gemName);
                        lore.remove(gemIndex);
                        for (int a = 0; a < gem; a++) lore.add(Color.ify("&7» [ Gems Socket # " + a + " ]"));
                    }
                }
            }
            lore = Color.ifying(lore);
            meta.setLore(lore);
            meta.setDisplayName(material + " " + itemType);
            weaponType.setItemMeta(meta);
            return weaponType;
        }
        return new ItemStack(Material.AIR);
    }

//        Weapon.getStat("artifact", "axe", "item-name");

}
