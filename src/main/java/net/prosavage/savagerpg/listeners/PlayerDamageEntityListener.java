package net.prosavage.savagerpg.listeners;

import net.prosavage.savagerpg.SavageRPG;
import net.prosavage.savagerpg.SNamespacedKey;
import net.prosavage.savagerpg.itembuilder.Weapon;
import net.prosavage.savagerpg.utils.Formula;
import net.prosavage.savagerpg.utils.Number;
import net.prosavage.savagerpg.utils.Placeholder;
import net.prosavage.savagerpg.utils.SpawnEntity;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class PlayerDamageEntityListener implements Listener {

    Weapon Weapon = new Weapon();
    Number Number = new Number();
    FileConfiguration SConfig = SavageRPG.getInstance().getConfig();
    SpawnEntity SpawnEntity = new SpawnEntity();
    SNamespacedKey SNamespacedKey = new SNamespacedKey();
    Placeholder Placeholder = new Placeholder();
    Formula Formula = new Formula();

    @EventHandler
    public void damage(EntityDamageByEntityEvent e){
        if (e.getDamager() instanceof Player){
            Player player = (Player) e.getDamager();
            ItemStack item = player.getInventory().getItemInMainHand();
            double damage = Number.getDouble(Weapon.getMinDamage(item), Weapon.getMaxDamage(item));
            double cooldown = Weapon.getCooldown(item);
            player.getPersistentDataContainer().set(SNamespacedKey.getPlayerRecentDamage(), PersistentDataType.DOUBLE, damage);
            damage = Formula.eval(Placeholder.parsePlayerInfo(player, String.valueOf(SConfig.get("formulas.player-damage"))));
            player.getPersistentDataContainer().set(SNamespacedKey.getPlayerDamage(), PersistentDataType.DOUBLE, damage);
            player.getPersistentDataContainer().set(SNamespacedKey.getPlayerRecentDamage(), PersistentDataType.DOUBLE, damage);
            if (e.getEntity() instanceof Player) {
                Player victim = (Player) e.getEntity();
                damage = Formula.eval(Placeholder.parsePlayerInfo(victim ,String.valueOf(SConfig.get("formulas.player-defense"))));
                e.setDamage(damage);
            }
            else if (!(e.getEntity() instanceof Player)) {
                damage = Math.abs(Formula.eval(Placeholder.parseEntityInfo(e.getEntity(),Placeholder.parsePlayerInfo(player,String.valueOf(SConfig.get("formulas.mob-defense"))))));
                e.setDamage(damage);
                Damageable entity = (Damageable) e.getEntity();
                Double health = Double.valueOf(String.format("%.2f", entity.getHealth()));
                e.getEntity().getPersistentDataContainer().set(SNamespacedKey.getEntityHealth(), PersistentDataType.DOUBLE, health);
                SpawnEntity.damageIndicator(player,e.getEntity(),String.valueOf(damage));
            }
        }
    }
}
