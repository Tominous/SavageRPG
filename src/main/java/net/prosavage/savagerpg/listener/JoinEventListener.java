package net.prosavage.savagerpg.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEventListener implements Listener {

    @EventHandler
    public void PlayerJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
/*/        SPlayer sPlayer = new SPlayer(player);
        int level = sPlayer.getLevel();
        double exp = sPlayer.getExp();
        double max_exp = sPlayer.getMaxEXP();
        int skillpoints = sPlayer.getSkillPoints();
        if ((Integer.valueOf(level).equals(0))) {
            sPlayer.setLevel(1);
            sPlayer.setExp(0);
            sPlayer.setMaxEXP(0);
            sPlayer.setSkillPoints(0);
            level = sPlayer.getLevel();
            exp = sPlayer.getExp();
            max_exp = sPlayer.getMaxEXP();
            skillpoints = sPlayer.getSkillPoints();
        }
/*/
    }


}
