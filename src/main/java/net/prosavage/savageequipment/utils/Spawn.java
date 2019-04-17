package net.prosavage.savageequipment.utils;

import net.prosavage.savageequipment.SavageEquipment;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Spawn {

    FileConfiguration SEConfig = SavageEquipment.getInstance().getConfig();
    net.prosavage.savageequipment.utils.Color Color = new Color();

    public void damageIndicator(Player player, Entity e, String name) {

        final ArmorStand damageHologram = (ArmorStand) e.getWorld().spawn(e.getLocation(), ArmorStand.class);
        damageHologram.setCustomNameVisible(true);
        damageHologram.setVisible(false);
        damageHologram.setGravity(false);
        damageHologram.setSmall(true);
        damageHologram.setInvulnerable(true);
        damageHologram.setMarker(true);
        String damage = String.valueOf(Double.valueOf(String.format("%.2f", Double.valueOf(name))));
        String nameValue = String.valueOf(SavageEquipment.getInstance().getConfig().get("damage-indicator.name"));
        nameValue = nameValue.replace("{player-damage}", damage);
        damageHologram.setCustomName(Color.ify(nameValue));
        new BukkitRunnable() {
            public void run() {
                damageHologram.remove();
            }
        }.runTaskLater(SavageEquipment.getInstance(), 40L);
    }
}