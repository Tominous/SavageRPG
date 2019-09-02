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
        final ArmorStand damageHologram = e.getWorld().spawn(location, ArmorStand.class, consumer -> {
            String damage = String.valueOf(Double.valueOf(String.format("%.2f", Double.valueOf(name))));
            String nameValue = String.valueOf(SavageRPG.getInstance().getConfig().get("damage-indicator.name"));
            nameValue = nameValue.replace("{player-damage}", damage);
            consumer.setCustomName(Color.ify(nameValue));
            consumer.setCustomNameVisible(true);
            consumer.setVisible(false);
            consumer.setGravity(false);
            consumer.setSmall(true);
            consumer.setInvulnerable(true);
            consumer.setMarker(true);
        });
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
        final ArmorStand expHologram = (ArmorStand) e.getWorld().spawn(location, ArmorStand.class, consumer ->{
            String damage = String.valueOf(Double.valueOf(String.format("%.2f", Double.valueOf(name))));
            String nameValue = String.valueOf(SavageRPG.getInstance().getConfig().get("exp-indicator.name"));
            nameValue = nameValue.replace("{exp-amount}", damage);
            consumer.setCustomName(Color.ify(nameValue));
            consumer.setCustomNameVisible(true);
            consumer.setVisible(false);
            consumer.setGravity(false);
            consumer.setSmall(true);
            consumer.setInvulnerable(true);
            consumer.setMarker(true);
        });
        new BukkitRunnable() {
            public void run() {
                expHologram.remove();
            }
        }.runTaskLater(SavageRPG.getInstance(), 40L);
        return new ArmorStand[]{expHologram};
    }

}
