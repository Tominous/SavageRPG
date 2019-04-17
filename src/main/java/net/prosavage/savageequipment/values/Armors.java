package net.prosavage.savageequipment.values;

import net.prosavage.savageequipment.SavageEquipment;
import net.prosavage.savageequipment.utils.Color;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class Armors {

    FileConfiguration armorConfig = SavageEquipment.getInstance().getConfig();
    net.prosavage.savageequipment.utils.Color Color = new Color();

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

        Double helmetProtection = getProtectionLore(playerHelmet);
        Double helmetDefaultProtection = getDefaultProtection(playerHelmet);
        Double chestplateProtection = getProtectionLore(playerChestplate);
        Double chestplateDefaultProtection = getDefaultProtection(playerChestplate);
        Double leggingsProtection = getProtectionLore(playerLeggings);
        Double leggingsDefaultProtection = getDefaultProtection(playerLeggings);
        Double bootsProtection = getProtectionLore(playerBoots);
        Double bootsDefaultProtection = getDefaultProtection(playerBoots);

        double protectionValue = helmetProtection + chestplateProtection + leggingsProtection + bootsProtection;
        double removeValue = helmetDefaultProtection + chestplateDefaultProtection + leggingsDefaultProtection + bootsDefaultProtection;

        return (removeValue * -1) + protectionValue;

    }

    public double getArmorHealth(ItemStack playerHelmet, ItemStack playerChestplate, ItemStack playerLeggings, ItemStack playerBoots){

        Double helmetHealth = getHealthLore(playerHelmet);
        Double chestplateHealth = getHealthLore(playerChestplate);
        Double leggingsHealth = getHealthLore(playerLeggings);
        Double bootsHealth = getHealthLore(playerBoots);

        double healthValue = helmetHealth + chestplateHealth + leggingsHealth + bootsHealth;

        return healthValue;

    }

}