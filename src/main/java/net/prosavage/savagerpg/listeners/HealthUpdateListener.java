package net.prosavage.savagerpg.listeners;

import net.prosavage.savagerpg.SavageRPG;
import net.prosavage.savagerpg.SNamespacedKey;
import net.prosavage.savagerpg.utils.Color;
import net.prosavage.savagerpg.utils.Formula;
import net.prosavage.savagerpg.utils.Placeholder;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

public class HealthUpdateListener implements Listener {

    SNamespacedKey SNamespacedKey = new SNamespacedKey();
    net.prosavage.savagerpg.utils.Placeholder Placeholder = new Placeholder();
    net.prosavage.savagerpg.utils.Formula Formula = new Formula();
    FileConfiguration SConfig = SavageRPG.getInstance().getConfig();
    Color Color = new Color();

    @EventHandler
    public void entityHealthUpdate(EntityDamageByEntityEvent e){
        if (e.getDamager() instanceof Player) {
            Player player = (Player) e.getDamager();
            Entity entity = e.getEntity();
            Double damage = e.getDamage();
            Double health = entity.getPersistentDataContainer().get(SNamespacedKey.getEntityHealth(), PersistentDataType.DOUBLE);
            if (health != null) {
                String mobNameParse = String.valueOf(SavageRPG.getInstance().getConfig().get("format.mob-names"));
                String parsedName = Placeholder.parseEntityInfo(entity, mobNameParse);
                SavageRPG.getInstance().sendConsole(parsedName);
                new BukkitRunnable() {
                    public void run() {
                        entity.setCustomName(Color.ify(parsedName));
                    }
                }.runTaskLater(SavageRPG.getInstance(), 1);
            }
        }
    }


}
