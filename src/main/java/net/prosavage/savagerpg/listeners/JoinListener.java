package net.prosavage.savagerpg.listeners;

import net.prosavage.savagerpg.SavageRPG;
import net.prosavage.savagerpg.utils.Formula;
import net.prosavage.savagerpg.utils.Placeholder;
import net.prosavage.savagerpg.utils.SRNamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.persistence.PersistentDataType;

public class JoinListener implements Listener {

    Placeholder Placeholder = new Placeholder();
    Formula Formula = new Formula();
    SRNamespacedKey SRNamespacedKey = new SRNamespacedKey();

    @EventHandler
    public void playerJoin(PlayerLoginEvent e){
        Player player = e.getPlayer();

        if (player.getPersistentDataContainer().get(SRNamespacedKey.getNamespacedKey_player_level(), PersistentDataType.INTEGER) == null) {

            player.getPersistentDataContainer().set(SRNamespacedKey.getNamespacedKey_player_level(), PersistentDataType.INTEGER, 1);
            player.getPersistentDataContainer().set(SRNamespacedKey.getNamespacedKey_player_exp(), PersistentDataType.DOUBLE, 0.0);
            player.getPersistentDataContainer().set(SRNamespacedKey.getNamespacedKey_player_skill_points(), PersistentDataType.INTEGER, 0);
            double evaluated = Formula.eval(Placeholder.getPlayerPlaceholders(player, String.valueOf(SavageRPG.getInstance().getConfig().get("formulas.exp"))));
            player.getPersistentDataContainer().set(SRNamespacedKey.getNamespacedKey_player_max_exp(), PersistentDataType.DOUBLE, evaluated);

        }

    }

}

