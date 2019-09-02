package net.prosavage.savagerpg.listeners;

import com.SirBlobman.combatlogx.utility.CombatUtil;
import net.prosavage.savagerpg.api.entities.SPlayer;
import net.prosavage.savagerpg.SavageRPG;
import net.prosavage.savagerpg.utils.Number;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerRegenListener implements Listener {

    Number Number = new Number();

    @EventHandler
    public void regen(PlayerJoinEvent e){
        Player player = e.getPlayer();
        SPlayer sPlayer = new SPlayer(player);
        new BukkitRunnable(){
            @Override
            public void run() {
                if (player.isOnline()) {
                    sPlayer.updateRegen();
                    double regen = sPlayer.getRegen();
                    if (!(CombatUtil.isInCombat(player))) {
                        if (Number.isGreaterOrEqualTo(player.getHealth() + regen,player.getHealth())) {
                            if (Number.isLessOrEqualTo(player.getHealth() + regen,player.getMaxHealth())) {
                                player.setHealth(player.getHealth() + regen);
                            }
                            if (Number.isGreaterOrEqualTo(player.getHealth() + regen,player.getMaxHealth())) {
                                player.setHealth(player.getMaxHealth());
                            }
                        }
                    }
                } else if (!(player.isOnline())) {
                    this.cancel();
                }
            }
        }.runTaskTimer(SavageRPG.getInstance(), 1, 10*20);

    }

}
