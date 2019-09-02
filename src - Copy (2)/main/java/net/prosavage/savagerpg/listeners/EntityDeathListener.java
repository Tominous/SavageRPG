package net.prosavage.savagerpg.listeners;

import net.prosavage.savagerpg.SavageRPG;
import net.prosavage.savagerpg.api.entities.SMob;
import net.prosavage.savagerpg.api.entities.SPlayer;
import net.prosavage.savagerpg.utils.Number;
import net.prosavage.savagerpg.utils.SpawnEntity;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class EntityDeathListener implements Listener {

    SMob SMob = new SMob();
    Number Number = new Number();
    SpawnEntity SpawnEntity = new SpawnEntity();

    @EventHandler
    public void entity(EntityDeathEvent event){
        Entity entity = event.getEntity();
        if (entity.getType() == EntityType.ARMOR_STAND || !(entity instanceof LivingEntity)) {
            return;
        }
        if (event.getEntity().getKiller() != null) {
            Player player = event.getEntity().getKiller();
            String[] playerList = SMob.getPlayers(entity).split("\\|\\|");
            NamespacedKey namespacedKey = new NamespacedKey(SavageRPG.getInstance(), "uuid." + player.getUniqueId().toString());
            double maxHealth = SMob.getMaxHealth(entity);
            double health = SMob.getHealth(entity);
            double exp = 0.0;
            for (String string : playerList){
                if (!string.equals("~")) {
                    SavageRPG.getInstance().sendConsole(string);
                    namespacedKey = new NamespacedKey(SavageRPG.getInstance(), "uuid." + string);
                    UUID playerUUID = UUID.fromString(string);
                    SPlayer sPlayer = new SPlayer(Objects.requireNonNull(Bukkit.getPlayer(playerUUID)));
                    if (Number.isLessThan(SMob.getPlayerDamage(entity, namespacedKey), SMob.getMaxHealth(entity))){
                        exp = ((SMob.getPlayerDamage(entity, namespacedKey) / maxHealth) * SMob.getExp(entity));
                    }
                    if (Number.isGreaterOrEqualTo(SMob.getPlayerDamage(entity, namespacedKey), SMob.getMaxHealth(entity))) {
                        exp = SMob.getExp(entity);
                    }
                    sPlayer.setExp(sPlayer.getExp() + exp);
                    SpawnEntity.expIndicator(Bukkit.getPlayer(playerUUID), entity, String.valueOf(exp));
                }
            }
        }
    }
}
