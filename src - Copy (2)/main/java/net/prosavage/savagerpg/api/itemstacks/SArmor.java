package net.prosavage.savagerpg.api.itemstacks;

import net.prosavage.savagerpg.SavageRPG;
import net.prosavage.savagerpg.api.keys.SNamespacedKeys;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SArmor {

    public String getName(ItemStack itemStack) {
        if (itemStack.getType() == Material.AIR){ return null; }
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
            return persistentDataContainer.get(SNamespacedKeys.ARMOR_NAME, PersistentDataType.STRING);
        }
        return null;
    }

    public String getMaterial(ItemStack itemStack) {
        if (itemStack.getType() == Material.AIR){ return null; }
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
            return persistentDataContainer.get(SNamespacedKeys.ARMOR_MATERIAL, PersistentDataType.STRING);
        }
        return null;
    }

    public String getRarity(ItemStack itemStack) {
        if (itemStack.getType() == Material.AIR){ return null; }
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
            return persistentDataContainer.get(SNamespacedKeys.ARMOR_RARITY, PersistentDataType.STRING);
        }
        return null;
    }

    public double getChance(ItemStack itemStack) {
        if (itemStack.getType() == Material.AIR){ return 0.0; }
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
            return persistentDataContainer.get(SNamespacedKeys.ARMOR_CHANCE, PersistentDataType.DOUBLE);
        }
        return 0.0;
    }

    public List<String> getLore(ItemStack itemStack) {
        if (itemStack.getType() == Material.AIR){ return null; }
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
            List<String> split = Arrays.asList(persistentDataContainer.get(SNamespacedKeys.ARMOR_LORE, PersistentDataType.STRING).split("||"));
            return split;
        }
        return Collections.singletonList("");
    }

    public int getLevel(ItemStack itemStack) {
        if ((itemStack == null) || (itemStack.getType() == Material.AIR)) { return 0; }
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
            return persistentDataContainer.get(SNamespacedKeys.ARMOR_LEVEL, PersistentDataType.INTEGER);
        }
        return 0;
    }

    public double getProtection(ItemStack itemStack) {
        if ((itemStack == null) || (itemStack.getType() == Material.AIR)) { return 0; }
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
            return persistentDataContainer.get(SNamespacedKeys.ARMOR_PROTECTION, PersistentDataType.DOUBLE);
        }
        return 0;
    }

    public double getHealth(ItemStack itemStack) {
        if ((itemStack == null) || (itemStack.getType() == Material.AIR)) { return 0; }
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
            return persistentDataContainer.get(SNamespacedKeys.ARMOR_HEALTH, PersistentDataType.DOUBLE);
        }
        return 0;
    }

    public double getRegen(ItemStack itemStack) {
        if ((itemStack == null) || (itemStack.getType() == Material.AIR)) { return 0; }
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
            return persistentDataContainer.get(SNamespacedKeys.ARMOR_REGEN, PersistentDataType.DOUBLE);
        }
        return 0;
    }

    public int getGems(ItemStack itemStack) {
        if ((itemStack == null) || (itemStack.getType() == Material.AIR)) { return 0; }
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
            return persistentDataContainer.get(SNamespacedKeys.ARMOR_GEM, PersistentDataType.INTEGER);
        }
        return 0;
    }

}
