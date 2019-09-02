package net.prosavage.savagerpg.listeners;

import net.prosavage.savagerpg.SavageRPG;
import net.prosavage.savagerpg.api.entities.SMob;
import net.prosavage.savagerpg.api.entities.SPlayer;
import net.prosavage.savagerpg.api.itemstacks.SWeapon;
import net.prosavage.savagerpg.utils.*;
import net.prosavage.savagerpg.utils.Number;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class PlayerDamageEntityListener implements Listener {

    Number Number = new Number();
    Formula Formula = new Formula();
    Placeholder Placeholder = new Placeholder();
    FileConfiguration SConfig = SavageRPG.getInstance().getConfig();
    SWeapon SWeapon = new SWeapon();
    SpawnEntity SpawnEntity = new SpawnEntity();
    SMob SMob = new SMob();
    Color Color = new Color();

    @EventHandler
    public void playerDamageEntity(EntityDamageByEntityEvent event){
        if (event.getDamager() instanceof Player){
            Player player = (Player) event.getDamager();
            ItemStack item = player.getInventory().getItemInMainHand();
            UUID uuid = player.getUniqueId();
            double damage = Number.getDouble(SWeapon.getMinimumDamage(item), SWeapon.getMaximumDamage(item));
            SPlayer sPlayer = new SPlayer(player);
            sPlayer.setDamage(damage);
            damage = Formula.eval(Placeholder.parsePlayerInfo(player, String.valueOf(SConfig.get("formulas.player-damage"))));
            if (event.getEntity() instanceof Player) {
                Player victim = (Player) event.getEntity();
                damage = Formula.eval(Placeholder.parsePlayerInfo(victim ,String.valueOf(SConfig.get("formulas.player-defense"))));
                event.setDamage(damage);
            }
            if (!(event.getEntity() instanceof Player)){
                Entity victim = event.getEntity();
                damage = Math.abs(Formula.eval(Placeholder.parseEntityInfo(event.getEntity(),Placeholder.parsePlayerInfo(player,String.valueOf(SConfig.get("formulas.mob-defense"))))));
                Damageable entity = (Damageable) event.getEntity();
                if (Number.isGreaterOrEqualTo(damage, entity.getHealth())){
                    damage = entity.getHealth() + 0.1;
                }
                event.setDamage(damage);
                sPlayer.setRecentDamage(damage);
                double health = Double.parseDouble(String.format("%.2f", entity.getHealth()));
                String getPlayers;
                NamespacedKey namespacedKey = new NamespacedKey(SavageRPG.getInstance(), "uuid." + player.getUniqueId().toString());
                ArrayList<String> playerList = new ArrayList<>(Arrays.asList(SMob.getPlayers(entity).split("\\|\\|")));
                if (!(playerList.contains(player.getUniqueId().toString()))){
                    playerList.add(uuid.toString());
                }
                getPlayers = String.join("||", playerList);
                SMob.setPlayers(entity, getPlayers);
                SMob.setPlayerDamage(entity, namespacedKey, SMob.getPlayerDamage(entity, namespacedKey) + damage);
                SpawnEntity.damageIndicator(player, event.getEntity(),String.valueOf(damage));
                SMob.setHealth(victim, health);
                victim.setCustomName(Color.ify(String.valueOf(Placeholder.parseEntityInfo(entity,String.valueOf(SavageRPG.getInstance().getConfig().get("format.mob-names"))))));
            }
        }
    }
}
