package me.kingalteriv.pragmata.SomewhatUsefulStuff;

import me.kingalteriv.pragmata.Main;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

public class SpawnMob
{
    public void damageIndicator(Entity e, String name)
    {
        final ArmorStand damageHologram = (ArmorStand)e.getWorld().spawn(e.getLocation(), ArmorStand.class);
        damageHologram.setCustomName(name);
        damageHologram.setCustomNameVisible(true);
        damageHologram.setVisible(false);
        damageHologram.setGravity(false);
        damageHologram.setSmall(true);
        damageHologram.setInvulnerable(true);
        damageHologram.setMarker(true);
        new BukkitRunnable()
        {
            public void run()
            {
                damageHologram.remove();
            }
        }.runTaskLater(Main.getInstance(), 40L);
    }
}

