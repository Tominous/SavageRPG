package net.prosavage.savagerpg.listeners;

import net.prosavage.savagerpg.api.entities.SPlayer;
import net.prosavage.savagerpg.SavageRPG;
import net.prosavage.savagerpg.api.itemstacks.SArmor;
import net.prosavage.savagerpg.libs.com.codingforcookies.armorequip.ArmorEquipEvent;
import net.prosavage.savagerpg.utils.Number;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;


public class ArmorEquipEventListener implements Listener {

    SArmor SArmor = new SArmor();
    Number Number = new Number();

    @EventHandler
    public void armorEquip(ArmorEquipEvent e){
        new BukkitRunnable(){
            @Override
            public void run() {
                Player player = e.getPlayer();
                SPlayer sPlayer = new SPlayer(player);
                sPlayer.updateDefense();
                sPlayer.updateHealth();
            }
        }.runTaskLaterAsynchronously(SavageRPG.getInstance(), 1);

    }

}