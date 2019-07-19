package net.prosavage.savagerpg.listeners;

import net.prosavage.savagerpg.SavageRPG;
import net.prosavage.savagerpg.SNamespacedKey;
import net.prosavage.savagerpg.itembuilder.Weapon;
import net.prosavage.savagerpg.utils.Color;
import net.prosavage.savagerpg.utils.Formula;
import net.prosavage.savagerpg.utils.Placeholder;
import net.prosavage.savagerpg.utils.SpawnEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

public class EntityDeathListener implements Listener {

    Color Color = new Color();
    SNamespacedKey SNamespacedKey = new SNamespacedKey();
    Weapon Weapon = new Weapon();
    SpawnEntity SpawnEntity = new SpawnEntity();
    net.prosavage.savagerpg.utils.Formula Formula = new Formula();
    net.prosavage.savagerpg.utils.Placeholder Placeholder = new Placeholder();

    @EventHandler
    public void death(EntityDeathEvent e) {
        if (!(e.getEntity() instanceof Player)){
            Entity entity = e.getEntity();
            String finalMobName = entity.getCustomName();
            Player player = e.getEntity().getKiller();
            new BukkitRunnable() {
                public void run() {
                    entity.setCustomName(Color.ify(finalMobName));
                }
            }.runTaskLater(SavageRPG.getInstance(),1);
            Double playerExp = player.getPersistentDataContainer().get(SNamespacedKey.getPlayerEXP(),PersistentDataType.DOUBLE);
            Double entityExp = entity.getPersistentDataContainer().get(SNamespacedKey.getEntityExp(),PersistentDataType.DOUBLE);
            assert playerExp != null;
            assert entityExp != null;
            entityExp = Formula.eval(Placeholder.parsePlayerInfo(player,Placeholder.parseEntityInfo(entity,String.valueOf(SavageRPG.getInstance().getConfig().get("formulas.exp-drop")))));
            player.getPersistentDataContainer().set(SNamespacedKey.getPlayerEXP(),PersistentDataType.DOUBLE,playerExp + entityExp);
            SpawnEntity.expIndicator(player,entity,String.valueOf(entityExp));
        }
    }
}
