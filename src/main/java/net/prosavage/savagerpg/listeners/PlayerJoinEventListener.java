package net.prosavage.savagerpg.listeners;

import net.prosavage.savagerpg.PDCPlayer;
import net.prosavage.savagerpg.SNamespacedKey;
import net.prosavage.savagerpg.SavageRPG;
import net.prosavage.savagerpg.itembuilder.Armor;
import net.prosavage.savagerpg.utils.Color;
import net.prosavage.savagerpg.utils.Formula;
import net.prosavage.savagerpg.utils.Placeholder;
import net.prosavage.savagerpg.utils.packets.Actionbar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerJoinEventListener implements Listener {

    Actionbar Actionbar = new Actionbar();
    Color Color = new Color();
    SNamespacedKey SNamespacedKey = new SNamespacedKey();
    Placeholder Placeholder = new Placeholder();
    Armor Armor = new Armor();
    Formula Formula = new Formula();

    @EventHandler
    public void joinListener(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        player.setHealthScale(20);
        player.setHealthScaled(true);
        PDCPlayer pdcPlayer = new PDCPlayer(player);
        pdcPlayer.setLevel(0);
        double maxExp = pdcPlayer.getMaxExp();
        Integer playerLevel = player.getPersistentDataContainer().get(SNamespacedKey.getPlayerLevel(),PersistentDataType.INTEGER);
        if (pdcPlayer.getLevel() == 0){
            maxExp = pdcPlayer.getMaxExp();
            pdcPlayer.setDefault();
        }
        pdcPlayer.updateDefense();
        new BukkitRunnable(){
            @Override
            public void run() {
                if (player.isOnline()){
                    Actionbar.sendMessage(player, Color.ify("&cHEALTH: " + String.format("%.2f", player.getHealth()) + "&e/&c" + String.format("%.2f", player.getMaxHealth()) + "          " + "&eDefense: " + String.format("%.2f", pdcPlayer.getDefense())));
                }else if (!(player.isOnline())){
                    this.cancel();
                }
            }
        }.runTaskTimer(SavageRPG.getInstance(), 1, 5);
    }

    @EventHandler
    public void sneak(PlayerToggleSneakEvent e){
        e.getPlayer().setSwimming(true);
    }
}
