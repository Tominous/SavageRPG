package net.prosavage.savagerpg.utils;

import net.prosavage.savagerpg.SavageRPG;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Spawn {

    FileConfiguration SEConfig = SavageRPG.getInstance().getConfig();
    Color Color = new Color();

    public void damageIndicator(Player player, Entity e, String name) {

        final ArmorStand damageHologram = (ArmorStand) e.getWorld().spawn(e.getLocation(), ArmorStand.class);
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
    }
}