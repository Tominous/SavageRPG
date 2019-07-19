package net.prosavage.savagerpg.listeners;

import com.SirBlobman.combatlogx.utility.CombatUtil;
import net.prosavage.savagerpg.PDCPlayer;
import net.prosavage.savagerpg.SavageRPG;
import net.prosavage.savagerpg.itembuilder.Armor;
import net.prosavage.savagerpg.utils.Number;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class RegenListener implements Listener {

    Armor Armor = new Armor();
    Number Number = new Number();

    @EventHandler
    public void regen(PlayerJoinEvent e){
        Player player = e.getPlayer();
        PDCPlayer pdcPlayer = new PDCPlayer(player);
        new BukkitRunnable(){
            @Override
            public void run() {
                if (player.isOnline()) {
                    ItemStack helmet = player.getInventory().getHelmet();
                    ItemStack chestplate = player.getInventory().getChestplate();
                    ItemStack leggings = player.getInventory().getLeggings();
                    ItemStack boots = player.getInventory().getBoots();
                    double regen = Armor.getRegen(helmet) + Armor.getRegen(chestplate) + Armor.getRegen(leggings) + Armor.getRegen(boots);
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
