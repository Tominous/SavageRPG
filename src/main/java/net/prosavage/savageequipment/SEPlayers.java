package net.prosavage.savageequipment;

import net.prosavage.savageequipment.utils.Formula;
import net.prosavage.savageequipment.utils.Placeholder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import java.util.UUID;

public class SEPlayers {

    private String player;
    private UUID uuid;
    private int level;
    private double exp;
    private double maxexp;
    private int skillpoints;

    public SEPlayers(Player p){
        this.player = p.getName();
        this.uuid = p.getUniqueId();
        this.level = 1;
        this.exp = 0.00;
        this.maxexp = 99999999999.99;
        this.skillpoints = 0;
    }

    public Player getPlayer(){
        return Bukkit.getPlayer(uuid);
    }

    public UUID getUUID(){
        return this.uuid;
    }

    public int getSELevel(){
        return this.level;
    }

    public double getSEExp(){
        return this.exp;
    }

    public double getSEMaxExp(){
        return this.maxexp;
    }

    public int getSESkillpoints(){
        return this.skillpoints;
    }

    public void setSELevel(int level){
        this.level = level;
    }

    public void setSEExp(double exp){
        this.exp = exp;
    }

    public void setSEMaxExp(double maxexp){
        this.maxexp = maxexp;
    }

    public void setSESkillpoints(int skillpoints){
        this.skillpoints = skillpoints;
    }



}
