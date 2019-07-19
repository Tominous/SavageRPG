package net.prosavage.savagerpg.utils;

import net.prosavage.savagerpg.SavageRPG;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class SpawnEntity {

    FileConfiguration SEConfig = SavageRPG.getInstance().getConfig();
    Color Color = new Color();
    Number Number = new Number();

    public Entity[] damageIndicator(Player player, Entity e, String name) {

        Location location = e.getLocation();
        Integer randomX = Number.getInteger(-1, 1);
        Integer randomY = Number.getInteger(0, 2);
        Integer randomZ = Number.getInteger(-1, 1);
        location.add(randomX, randomY, randomZ);
        final ArmorStand damageHologram = (ArmorStand) e.getWorld().spawn(location, ArmorStand.class);
        damageHologram.setCustomNameVisible(true);
        damageHologram.setVisible(false);
        damageHologram.setGravity(false);
        damageHologram.setSmall(true);
        damageHologram.setInvulnerable(true);
        damageHologram.setMarker(true);
        String damage = String.valueOf(Double.valueOf(String.format("%.2f", Double.valueOf(name))));
        String nameValue = String.valueOf(SavageRPG.getInstance().getConfig().get("damage-indicator.name"));
        nameValue = nameValue.replace("{player-damage}", damage);
        damageHologram.setCustomName(Color.ify(nameValue));
        new BukkitRunnable() {
            public void run() {
                damageHologram.remove();
            }
        }.runTaskLater(SavageRPG.getInstance(), 40L);
        return new ArmorStand[]{damageHologram};
    }

    public Entity[] expIndicator(Player player, Entity e, String name) {

        Location location = e.getLocation();
        Integer randomX = Number.getInteger(-1, 1);
        Integer randomY = Number.getInteger(0, 2);
        Integer randomZ = Number.getInteger(-1, 1);
        location.add(randomX, randomY, randomZ);
        final ArmorStand damageHologram = (ArmorStand) e.getWorld().spawn(location, ArmorStand.class);
        damageHologram.setCustomNameVisible(true);
        damageHologram.setVisible(false);
        damageHologram.setGravity(false);
        damageHologram.setSmall(true);
        damageHologram.setInvulnerable(true);
        damageHologram.setMarker(true);
        String damage = String.valueOf(Double.valueOf(String.format("%.2f", Double.valueOf(name))));
        String nameValue = String.valueOf(SavageRPG.getInstance().getConfig().get("exp-indicator.name"));
        nameValue = nameValue.replace("{exp-amount}", damage);
        damageHologram.setCustomName(Color.ify(nameValue));
        new BukkitRunnable() {
            public void run() {
                damageHologram.remove();
            }
        }.runTaskLater(SavageRPG.getInstance(), 40L);
        return new ArmorStand[]{damageHologram};
    }

}
