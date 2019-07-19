package net.prosavage.savagerpg.listeners;

import net.prosavage.savagerpg.SavageRPG;
import net.prosavage.savagerpg.SNamespacedKey;
import net.prosavage.savagerpg.utils.Color;
import net.prosavage.savagerpg.utils.Formula;
import net.prosavage.savagerpg.utils.Number;
import net.prosavage.savagerpg.utils.Placeholder;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class EntitySpawnListener implements Listener {

    SNamespacedKey SNamespacedKey = new SNamespacedKey();
    Number Number = new Number();
    Color Color = new Color();
    Formula Formula = new Formula();
    Placeholder Placeholder = new Placeholder();

    @EventHandler
    public void event(EntitySpawnEvent e) {
        if (e.getEntity() instanceof LivingEntity) {
            if (!(e.getEntity() instanceof ArmorStand)) {
                LivingEntity entity = (LivingEntity) e.getEntity();
                Location location = e.getLocation();
                double x = location.getX();
                double z = location.getZ();
                double y = location.getY();
                World world = location.getWorld();
                double configX = (double) SavageRPG.getInstance().getConfig().get("mob-spawn.center-x");
                double configZ = (double) SavageRPG.getInstance().getConfig().get("mob-spawn.center-z");
                double configBlocks = (double) SavageRPG.getInstance().getConfig().get("mob-spawn.block-difficulty-increase");
                String configHealth = String.valueOf(SavageRPG.getInstance().getConfig().get("mob-spawn.mob-hp-increase"));
                Location configLocation = new Location(world,configX,y,configZ);
                double distance = location.distance(configLocation) / configBlocks;
                entity.getPersistentDataContainer().set(SNamespacedKey.getEntityHealth(),PersistentDataType.DOUBLE,((LivingEntity) e.getEntity()).getHealth());
                entity.getPersistentDataContainer().set(SNamespacedKey.getEntityDistance(),PersistentDataType.DOUBLE,distance);
                int level = (int) Math.floor(Double.parseDouble(String.format("%.2f",location.distance(configLocation) / configBlocks)));
                entity.getPersistentDataContainer().set(SNamespacedKey.getEntityLevel(),PersistentDataType.INTEGER,level);
                double health = Double.parseDouble(String.format("%.2f", Formula.eval(Placeholder.parseConfigInfo(Placeholder.parseEntityInfo(entity, configHealth)))));
                entity.getPersistentDataContainer().set(SNamespacedKey.getEntityHealth(),PersistentDataType.DOUBLE,health);
                String placeholder = Placeholder.parseEntityInfo(entity, String.valueOf(SavageRPG.getInstance().getConfig().get("formulas.mob-health")));
                health = Formula.eval(placeholder);
                health = Double.parseDouble(String.format("%.2f",Math.floor(health)));
                entity.setCustomNameVisible(true);
                entity.getPersistentDataContainer().set(SNamespacedKey.getEntityHealth(),PersistentDataType.DOUBLE,Double.valueOf(String.format("%.2f",health)));
                entity.getPersistentDataContainer().set(SNamespacedKey.getEntityMaxHealth(),PersistentDataType.DOUBLE,Double.valueOf(String.format("%.2f",health)));
                entity.getPersistentDataContainer().set(SNamespacedKey.getEntityName(),PersistentDataType.STRING,entity.getType().toString());
                String finalMobName = String.valueOf(Placeholder.parseEntityInfo(entity,String.valueOf(SavageRPG.getInstance().getConfig().get("format.mob-names"))));
                SavageRPG.getInstance().sendConsole(finalMobName + " " + Color.ify(finalMobName));
                SavageRPG.getInstance().sendConsole("" + Placeholder.parseConfigInfo(Placeholder.parseEntityInfo(entity, configHealth)));
                double finalHealth = health;
                new BukkitRunnable() {
                    public void run() {
                        Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(finalHealth);
                        entity.setHealth(entity.getMaxHealth());
                        entity.setCustomName(Color.ify(finalMobName));
                    }
                }.runTaskLater(SavageRPG.getInstance(),1);
            }
        }
    }
}
