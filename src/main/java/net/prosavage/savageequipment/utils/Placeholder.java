package net.prosavage.savageequipment.utils;

import net.prosavage.savageequipment.SEPlayers;
import net.prosavage.savageequipment.listeners.DamageListener;
import org.bukkit.entity.Player;

public class Placeholder {

    Weapons Weapons = new Weapons();

    @SuppressWarnings("DEPRECATED")
    public String getPlayerPlaceholders(Player player, String string){
        string = string.replace("{player-damage}", String.valueOf(Weapons.getDamageLore(player.getInventory().getItemInMainHand())));
        string = string.replace("{player-level}", String.valueOf(new SEPlayers(player).getSELevel()));
        string = string.replace("{player-health}", String.valueOf(player.getHealth()));
        string = string.replace("{player-max-health}", String.valueOf(player.getMaxHealth()));
        return string;
    }



}
