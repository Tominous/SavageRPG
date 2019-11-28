package net.prosavage.illyriarpg.api.itemstacks;

import net.prosavage.illyriarpg.api.keys.INamespacedKeys;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class IWeapon {

    public String getName(ItemStack itemStack) {
        if (itemStack.getType() == Material.AIR){ return null; }
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
            return persistentDataContainer.get(INamespacedKeys.ITEM_NAME, PersistentDataType.STRING);
        }
        return null;
    }

    public String getMaterial(ItemStack itemStack) {
        if (itemStack.getType() == Material.AIR){ return null; }
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
            return persistentDataContainer.get(INamespacedKeys.ITEM_MATERIAL, PersistentDataType.STRING);
        }
        return null;
    }

    public String getRarity(ItemStack itemStack) {
        if (itemStack.getType() == Material.AIR){ return null; }
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
            return persistentDataContainer.get(INamespacedKeys.ITEM_RARITY, PersistentDataType.STRING);
        }
        return null;
    }

    public double getChance(ItemStack itemStack) {
        if (itemStack.getType() == Material.AIR){ return -1.0; }
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
            return persistentDataContainer.get(INamespacedKeys.ITEM_CHANCE, PersistentDataType.DOUBLE);
        }
        return 0.0;
    }

    public List<String> getLore(ItemStack itemStack) {
        if (itemStack.getType() == Material.AIR){ return null; }
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
            return Arrays.asList(persistentDataContainer.get(INamespacedKeys.ITEM_LORE, PersistentDataType.STRING).split("||"));
        }
        return Collections.singletonList("");
    }

    public int getLevel(ItemStack itemStack) {
        if (itemStack.getType() == Material.AIR){ return -1; }
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
            return persistentDataContainer.get(INamespacedKeys.ITEM_LEVEL, PersistentDataType.INTEGER);
        }
        return 0;
    }

    public double getMaximumDamage(ItemStack itemStack) {
        if (itemStack.getType() == Material.AIR){ return -1.0; }
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
            return persistentDataContainer.get(INamespacedKeys.ITEM_MAX_DAMAGE, PersistentDataType.DOUBLE);
        }
        return 0.0;
    }

    public double getMinimumDamage(ItemStack itemStack) {
        if (itemStack.getType() == Material.AIR){ return -1.0; }
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
            return persistentDataContainer.get(INamespacedKeys.ITEM_MIN_DAMAGE, PersistentDataType.DOUBLE);
        }
        return 0.0;
    }

    public int getGems(ItemStack itemStack) {
        if (itemStack.getType() == Material.AIR){ return -1; }
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
            return persistentDataContainer.get(INamespacedKeys.ITEM_GEM, PersistentDataType.INTEGER);
        }
        return 0;
    }

    public int getScrolls(ItemStack itemStack) {
        if (itemStack.getType() == Material.AIR){ return -1; }
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
            return persistentDataContainer.get(INamespacedKeys.ITEM_SCROLL, PersistentDataType.INTEGER);
        }
        return 0;
    }

    public double getAbilityCooldown(ItemStack itemStack) {
        if (itemStack.getType() == Material.AIR){ return -1.0; }
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
            return persistentDataContainer.get(INamespacedKeys.ITEM_ABIILTY_COOLDOWN, PersistentDataType.DOUBLE);
        }
        return 0.0;
    }

    public String getAbilityName(ItemStack itemStack) {
        if (itemStack.getType() == Material.AIR){ return null; }
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
            return persistentDataContainer.get(INamespacedKeys.ITEM_ABIILTY_NAME, PersistentDataType.STRING);
        }
        return null;
    }

    public List<String> getAbilityDescription(ItemStack itemStack) {
        if (itemStack.getType() == Material.AIR){ return null; }
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
            return Arrays.asList(Objects.requireNonNull(persistentDataContainer.get(INamespacedKeys.ITEM_ABIILTY_DESCRIPTION, PersistentDataType.STRING)).split("||"));
        }
        return Collections.singletonList("");
    }

    public String getAbilityCastType(ItemStack itemStack) {
        if (itemStack.getType() == Material.AIR){ return null; }
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
            return persistentDataContainer.get(INamespacedKeys.ITEM_ABIILTY_CAST_TYPE, PersistentDataType.STRING);
        }
        return null;
    }

    public double getAbilityManaCost(ItemStack itemStack) {
        if (itemStack.getType() == Material.AIR){ return -1.0; }
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
            return persistentDataContainer.get(INamespacedKeys.ITEM_ABIILTY_MANA_COST, PersistentDataType.DOUBLE);
        }
        return 0.0;
    }

    public String getAbilityActionType(ItemStack itemStack) {
        if (itemStack.getType() == Material.AIR){ return null; }
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
            return persistentDataContainer.get(INamespacedKeys.ITEM_ABIILTY_ACTION_TYPE, PersistentDataType.STRING);
        }
        return null;
    }
}
