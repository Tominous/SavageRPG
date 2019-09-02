package net.prosavage.savagerpg.api.itemstacks;

import net.prosavage.savagerpg.api.keys.SNamespacedKeys;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SWeapon {

    public String getName(ItemStack itemStack) {
        if (itemStack.getType() == Material.AIR){ return null; }
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
            return persistentDataContainer.get(SNamespacedKeys.ITEM_NAME, PersistentDataType.STRING);
        }
        return null;
    }

    public String getMaterial(ItemStack itemStack) {
        if (itemStack.getType() == Material.AIR){ return null; }
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
            return persistentDataContainer.get(SNamespacedKeys.ITEM_MATERIAL, PersistentDataType.STRING);
        }
        return null;
    }

    public String getRarity(ItemStack itemStack) {
        if (itemStack.getType() == Material.AIR){ return null; }
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
            return persistentDataContainer.get(SNamespacedKeys.ITEM_RARITY, PersistentDataType.STRING);
        }
        return null;
    }

    public double getChance(ItemStack itemStack) {
        if (itemStack.getType() == Material.AIR){ return -1.0; }
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
            return persistentDataContainer.get(SNamespacedKeys.ITEM_CHANCE, PersistentDataType.DOUBLE);
        }
        return 0.0;
    }

    public List<String> getLore(ItemStack itemStack) {
        if (itemStack.getType() == Material.AIR){ return null; }
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
            List<String> split = Arrays.asList(persistentDataContainer.get(SNamespacedKeys.ITEM_LORE, PersistentDataType.STRING).split("||"));
            return split;
        }
        return Collections.singletonList("");
    }

    public int getLevel(ItemStack itemStack) {
        if (itemStack.getType() == Material.AIR){ return -1; }
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
            return persistentDataContainer.get(SNamespacedKeys.ITEM_LEVEL, PersistentDataType.INTEGER);
        }
        return 0;
    }

    public double getMaximumDamage(ItemStack itemStack) {
        if (itemStack.getType() == Material.AIR){ return -1.0; }
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
            return persistentDataContainer.get(SNamespacedKeys.ITEM_MAX_DAMAGE, PersistentDataType.DOUBLE);
        }
        return 0.0;
    }

    public double getMinimumDamage(ItemStack itemStack) {
        if (itemStack.getType() == Material.AIR){ return -1.0; }
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
            return persistentDataContainer.get(SNamespacedKeys.ITEM_MIN_DAMAGE, PersistentDataType.DOUBLE);
        }
        return 0.0;
    }

    public int getGems(ItemStack itemStack) {
        if (itemStack.getType() == Material.AIR){ return -1; }
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
            return persistentDataContainer.get(SNamespacedKeys.ITEM_GEM, PersistentDataType.INTEGER);
        }
        return 0;
    }

    public double getAbilityCooldown(ItemStack itemStack) {
        if (itemStack.getType() == Material.AIR){ return -1.0; }
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
            return persistentDataContainer.get(SNamespacedKeys.ITEM_ABIILTY_COOLDOWN, PersistentDataType.DOUBLE);
        }
        return 0.0;
    }

    public String getAbilityName(ItemStack itemStack) {
        if (itemStack.getType() == Material.AIR){ return null; }
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
            return persistentDataContainer.get(SNamespacedKeys.ITEM_ABIILTY_NAME, PersistentDataType.STRING);
        }
        return null;
    }

    public List<String> getAbilityDescription(ItemStack itemStack) {
        if (itemStack.getType() == Material.AIR){ return null; }
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
            List<String> split = Arrays.asList(persistentDataContainer.get(SNamespacedKeys.ITEM_ABIILTY_DESCRIPTION, PersistentDataType.STRING).split("||"));
            return split;
        }
        return Collections.singletonList("");
    }

    public String getAbilityCastType(ItemStack itemStack) {
        if (itemStack.getType() == Material.AIR){ return null; }
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
            return persistentDataContainer.get(SNamespacedKeys.ITEM_ABIILTY_CAST_TYPE, PersistentDataType.STRING);
        }
        return null;
    }

    public double getAbilityManaCost(ItemStack itemStack) {
        if (itemStack.getType() == Material.AIR){ return -1.0; }
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
            return persistentDataContainer.get(SNamespacedKeys.ITEM_ABIILTY_MANA_COST, PersistentDataType.DOUBLE);
        }
        return 0.0;
    }

    public String getAbilityActionType(ItemStack itemStack) {
        if (itemStack.getType() == Material.AIR){ return null; }
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
            return persistentDataContainer.get(SNamespacedKeys.ITEM_ABIILTY_ACTION_TYPE, PersistentDataType.STRING);
        }
        return null;
    }
}
