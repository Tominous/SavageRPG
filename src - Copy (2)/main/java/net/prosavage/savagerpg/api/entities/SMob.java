package net.prosavage.savagerpg.api.entities;

import net.prosavage.savagerpg.SavageRPG;
import net.prosavage.savagerpg.api.keys.SNamespacedKeys;
import net.prosavage.savagerpg.utils.Color;
import net.prosavage.savagerpg.utils.Formula;
import net.prosavage.savagerpg.utils.Number;
import net.prosavage.savagerpg.utils.Placeholder;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

public class SMob {

    Placeholder Placeholder = new Placeholder();
    Color Color = new Color();
    Number Number = new Number();
    Formula Formula = new Formula();

    public String getName(Entity entity) {
        if (!(entity instanceof Player)) {
            return entity.getPersistentDataContainer().get(SNamespacedKeys.ENTITY_NAME, PersistentDataType.STRING);
        }
        return null;
    }

    public String getDefaultName(Entity entity){
        return WordUtils.capitalize(entity.getName());
    }

    public int getLevel(Entity entity) {
        if (!(entity instanceof Player)) {
            return entity.getPersistentDataContainer().get(SNamespacedKeys.ENTITY_LEVEL, PersistentDataType.INTEGER);
        }
        return 0;
    }

    public double getExp(Entity entity) {
        if (!(entity instanceof Player)) {
            return entity.getPersistentDataContainer().get(SNamespacedKeys.ENTITY_EXP, PersistentDataType.DOUBLE);
        }
        return 0.0;
    }

    public double getMinExp(Entity entity) {
        if (!(entity instanceof Player)) {
            return entity.getPersistentDataContainer().get(SNamespacedKeys.ENTITY_MIN_EXP, PersistentDataType.DOUBLE);
        }
        return 0.0;
    }

    public double getMaxExp(Entity entity) {
        if (!(entity instanceof Player)) {
            return entity.getPersistentDataContainer().get(SNamespacedKeys.ENTITY_MAX_EXP, PersistentDataType.DOUBLE);
        }
        return 0.0;
    }

    public double getHealth(Entity entity) {
        if (!(entity instanceof Player)) {
            return entity.getPersistentDataContainer().get(SNamespacedKeys.ENTITY_HEALTH, PersistentDataType.DOUBLE);
        }
        return 0.0;
    }

    public double getMinHealth(Entity entity) {
        if (!(entity instanceof Player)) {
            return entity.getPersistentDataContainer().get(SNamespacedKeys.ENTITY_MIN_HEALTH, PersistentDataType.DOUBLE);
        }
        return 0.0;
    }

    public double getMaxHealth(Entity entity) {
        if (!(entity instanceof Player)) {
            return entity.getPersistentDataContainer().get(SNamespacedKeys.ENTITY_MAX_HEALTH, PersistentDataType.DOUBLE);
        }
        return 0.0;
    }

    public double getDamage(Entity entity) {
        if (!(entity instanceof Player)) {
            return entity.getPersistentDataContainer().get(SNamespacedKeys.ENTITY_DAMAGE, PersistentDataType.DOUBLE);
        }
        return 0.0;
    }

    public double getMinDamage(Entity entity) {
        if (!(entity instanceof Player)) {
            return entity.getPersistentDataContainer().get(SNamespacedKeys.ENTITY_MIN_DAMAGE, PersistentDataType.DOUBLE);
        }
        return 0.0;
    }

    public double getMaxDamage(Entity entity) {
        if (!(entity instanceof Player)) {
            return entity.getPersistentDataContainer().get(SNamespacedKeys.ENTITY_MAX_DAMAGE, PersistentDataType.DOUBLE);
        }
        return 0.0;
    }

    public double getDistance(Entity entity) {
        if (!(entity instanceof Player)) {
            return entity.getPersistentDataContainer().get(SNamespacedKeys.ENTITY_DISTANCE, PersistentDataType.DOUBLE);
        }
        return 0.0;
    }

    public double getPlayerDamage(Entity entity, NamespacedKey namespacedKey) {
        if (!(entity instanceof Player)) {
            if (entity.getPersistentDataContainer().get(namespacedKey, PersistentDataType.DOUBLE) == null){
                return 0.0;
            }
            return entity.getPersistentDataContainer().get(namespacedKey, PersistentDataType.DOUBLE);
        }
        return 0.0;
    }

    public String getPlayers(Entity entity) {
        if (!(entity instanceof Player)) {
            if (entity.getPersistentDataContainer().get(SNamespacedKeys.ENTITY_PLAYERS, PersistentDataType.STRING) == null){
                return "~||";
            }
            return entity.getPersistentDataContainer().get(SNamespacedKeys.ENTITY_PLAYERS, PersistentDataType.STRING);
        }
        return null;
    }


    public boolean isAlreadySpawned(Entity entity) {
        if (!(entity instanceof Player)) {
            return Boolean.parseBoolean(entity.getPersistentDataContainer().get(SNamespacedKeys.ENTITY_ALREADY_SPAWNED, PersistentDataType.STRING));
        }
        return false;
    }

    public void setName(Entity entity, String name) {
        entity.getPersistentDataContainer().set(SNamespacedKeys.ENTITY_NAME, PersistentDataType.STRING, name);
    }

    public void setLevel(Entity entity, int level) {
        entity.getPersistentDataContainer().set(SNamespacedKeys.ENTITY_LEVEL, PersistentDataType.INTEGER, level);
    }

    public void setExp(Entity entity, double exp) {
        entity.getPersistentDataContainer().set(SNamespacedKeys.ENTITY_EXP, PersistentDataType.DOUBLE, exp);
    }

    public void setHealth(Entity entity, double health){
        entity.getPersistentDataContainer().set(SNamespacedKeys.ENTITY_HEALTH, PersistentDataType.DOUBLE, health);
    }

    public void setMaxHealth(Entity entity, double maxHealth){
        entity.getPersistentDataContainer().set(SNamespacedKeys.ENTITY_MAX_HEALTH, PersistentDataType.DOUBLE, maxHealth);
    }

    public void setMinDamage(Entity entity, double minDamage) {
        entity.getPersistentDataContainer().set(SNamespacedKeys.ENTITY_MIN_DAMAGE, PersistentDataType.DOUBLE, minDamage);
    }

    public void setMaxDamage(Entity entity, double maxDamage) {
        entity.getPersistentDataContainer().set(SNamespacedKeys.ENTITY_MAX_DAMAGE, PersistentDataType.DOUBLE, maxDamage);
    }

    public void setDistance(Entity entity, double distance) {
        entity.getPersistentDataContainer().set(SNamespacedKeys.ENTITY_DISTANCE, PersistentDataType.DOUBLE, distance);
    }

    public void setPlayerDamage(Entity entity, NamespacedKey namespacedKey, double damage){
        entity.getPersistentDataContainer().set(namespacedKey, PersistentDataType.DOUBLE, damage);
    }

    public void setPlayers(Entity entity, String string) {
        entity.getPersistentDataContainer().set(SNamespacedKeys.ENTITY_PLAYERS, PersistentDataType.STRING, string);
    }

}
