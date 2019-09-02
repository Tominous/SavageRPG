package net.prosavage.savagerpg.listeners;
import net.prosavage.savagerpg.api.entities.SPlayer;
import net.prosavage.savagerpg.api.keys.SNamespacedKeys;
import net.prosavage.savagerpg.SavageRPG;
import net.prosavage.savagerpg.api.itemstacks.SArmor;
import net.prosavage.savagerpg.customevents.PlayerLevelUpEvent;
import net.prosavage.savagerpg.utils.Color;
import net.prosavage.savagerpg.utils.Formula;
import net.prosavage.savagerpg.utils.Placeholder;
import net.prosavage.savagerpg.utils.packets.Actionbar;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerJoinListener implements Listener{


    Actionbar Actionbar = new Actionbar();
    Color Color = new Color();
    Placeholder Placeholder = new Placeholder();
    SArmor SArmor = new SArmor();
    Formula Formula = new Formula();

    @EventHandler
    public void joinListener(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.setHealthScale(20);
        player.setHealthScaled(true);
        SPlayer sPlayer = new SPlayer(player);
        double maxExp = sPlayer.getMaxExp();
        if ((sPlayer.getLevel() == null) || (sPlayer.getLevel() == 0)){
            maxExp = sPlayer.getMaxExp();
            sPlayer.setDefault();
        }
        sPlayer.updateDefense();
        new BukkitRunnable(){
            @Override
            public void run() {
                if (player.isOnline()){
                    Actionbar.sendMessage(player, Color.ify("&cHEALTH: " + String.format("%.2f", player.getHealth()) + "&e/&c" + String.format("%.2f", player.getMaxHealth()) + "          " + "&eDefense: " + String.format("%.2f", sPlayer.getDefense())));
                    if (sPlayer.getExp() > sPlayer.getMaxExp()){
                        PlayerLevelUpEvent playerLevelUpEvent = new PlayerLevelUpEvent(player, sPlayer.getLevel());
                        Bukkit.getPluginManager().callEvent(playerLevelUpEvent);
                    }
                }else if (!(player.isOnline())){
                    this.cancel();
                }
            }
        }.runTaskTimer(SavageRPG.getInstance(), 1, 5);
    }

}
