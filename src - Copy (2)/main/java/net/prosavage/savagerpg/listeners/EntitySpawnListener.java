package net.prosavage.savagerpg.listeners;

import net.prosavage.savagerpg.api.entities.SMob;
import net.prosavage.savagerpg.builder.Mob;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class EntitySpawnListener implements Listener {

    SMob SMob = new SMob();

    @EventHandler
    public void spawn(EntitySpawnEvent event) {
        Entity entity = event.getEntity();
        if (entity.getType() == EntityType.ARMOR_STAND || !(entity instanceof LivingEntity)) {
            return;
        }
        if (!(SMob.isAlreadySpawned(entity))) {
            LivingEntity livingEntity = (LivingEntity) event.getEntity();
            Mob mob = new Mob(livingEntity);
            mob.spawnExistingEntity();

        }
    }
}
