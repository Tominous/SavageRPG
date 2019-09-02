package net.prosavage.savagerpg.debug;

import net.prosavage.savagerpg.utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class DebugScoreboardManager {

    Color Color = new Color();
    Scoreboard scoreboard;
    Objective sidebar;

    public DebugScoreboardManager(Player player){
        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        sidebar = scoreboard.registerNewObjective("sidebar", "dummy");
        sidebar.setDisplaySlot(DisplaySlot.SIDEBAR);
        for (int i=1; i<=15; i++){
            Team team = scoreboard.registerNewTeam("SLOT_" + i);
            team.addEntry(genEntry(i));
        }
        player.setScoreboard(scoreboard);
    }

    public void setSlot(int slot, String prefix, String suffix){
        Team team = scoreboard.getTeam("SLOT_" + slot);
        sidebar.getScore(genEntry(slot)).setScore(slot);
        assert team != null;
        team.setPrefix(Color.ify(prefix));
        team.setSuffix(Color.ify(suffix));
    }

    private String genEntry(int slot) {
        return ChatColor.values()[slot].toString();
    }
}
