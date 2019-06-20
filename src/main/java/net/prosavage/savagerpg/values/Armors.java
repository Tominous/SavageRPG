package net.prosavage.savagerpg.values;

import net.prosavage.savagerpg.SavageRPG;
import net.prosavage.savagerpg.utils.Color;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class Armors {

    FileConfiguration armorConfig = SavageRPG.getInstance().getConfig();
    net.prosavage.savagerpg.utils.Color Color = new Color();

    public double getProtectionLore(ItemStack item) {
        if (item == null) return 0.0;

        if (!item.hasItemMeta()) return 0.0;

        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        if (!(meta).hasLore()) return 0.0;

        for (String string : Objects.requireNonNull(meta.getLore())) {
            if (Color.strip(string).contains(armorConfig.get("armor.protection-clear").toString())) {
                String line = Color.strip(string).replace(armorConfig.get("armor.protection-clear").toString(), "");
                double protection = Double.parseDouble(line);
                return protection;
            }
        }

        return 0.0;
    }

    public double getHealthLore(ItemStack item) {
        if (item == null) return 0.0;

        if (!item.hasItemMeta()) return 0.0;

        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        if (!(meta).hasLore()) return 0.0;

        for (String string : Objects.requireNonNull(meta.getLore())) {
            if (Color.strip(string).contains(armorConfig.get("armor.health-clear").toString())) {
                String line = Color.strip(string).replace(armorConfig.get("armor.health-clear").toString(), "");
                double health = Double.parseDouble(line);
                return health;
            }
        }

        return 0.0;
    }

    public double getDefaultProtection(ItemStack item) {

        if (item == null) return 0.0;

        Material material = item.getType();

        switch (material) {
            case LEATHER_HELMET:
            case LEATHER_BOOTS:
            case GOLDEN_BOOTS:
            case CHAINMAIL_BOOTS:
                return 1.0;
            case LEATHER_LEGGINGS:
            case GOLDEN_HELMET:
            case CHAINMAIL_HELMET:
            case IRON_HELMET:
            case IRON_BOOTS:
                return 2.0;
            case LEATHER_CHESTPLATE:
            case GOLDEN_LEGGINGS:
            case DIAMOND_HELMET:
            case DIAMOND_BOOTS:
                return 3.0;
            case CHAINMAIL_LEGGINGS:
                return 4.0;
            case GOLDEN_CHESTPLATE:
            case CHAINMAIL_CHESTPLATE:
            case IRON_LEGGINGS:
                return 5.0;
            case IRON_CHESTPLATE:
            case DIAMOND_LEGGINGS:
                return 6.0;
            case DIAMOND_CHESTPLATE:
                return 8.0;
            default:
                return 0.0;
        }

    }

    public double getArmorProtection(ItemStack playerHelmet, ItemStack playerChestplate, ItemStack playerLeggings, ItemStack playerBoots){

        double helmetProtection = getProtectionLore(playerHelmet);
        double helmetDefaultProtection = getDefaultProtection(playerHelmet);
        double chestplateProtection = getProtectionLore(playerChestplate);
        double chestplateDefaultProtection = getDefaultProtection(playerChestplate);
        double leggingsProtection = getProtectionLore(playerLeggings);
        double leggingsDefaultProtection = getDefaultProtection(playerLeggings);
        double bootsProtection = getProtectionLore(playerBoots);
        double bootsDefaultProtection = getDefaultProtection(playerBoots);

        double protectionValue = helmetProtection + chestplateProtection + leggingsProtection + bootsProtection;
        double removeValue = helmetDefaultProtection + chestplateDefaultProtection + leggingsDefaultProtection + bootsDefaultProtection;

        return (removeValue * -1) + protectionValue;

    }

    public double getArmorHealth(ItemStack playerHelmet, ItemStack playerChestplate, ItemStack playerLeggings, ItemStack playerBoots){

        double helmetHealth = getHealthLore(playerHelmet);
        double chestplateHealth = getHealthLore(playerChestplate);
        double leggingsHealth = getHealthLore(playerLeggings);
        double bootsHealth = getHealthLore(playerBoots);

        double healthValue = helmetHealth + chestplateHealth + leggingsHealth + bootsHealth;

        return healthValue;

    }

}