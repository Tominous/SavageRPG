package net.prosavage.savageequipment.utils;

import net.prosavage.savageequipment.SavageEquipment;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;

import java.util.List;

public class Placeholder {

    Weapons Weapons = new Weapons();

    @SuppressWarnings("DEPRECATED")
    public String getPlayerPlaceholders(Player player, String string){
        List<MetadataValue> metadata = player.getMetadata("SE_LEVEL");
        string = string.replace("{player-damage}", String.valueOf(Weapons.getDamageLore(player.getInventory().getItemInMainHand())));

        List<MetadataValue> level = player.getMetadata("SavageEquipments-"+ player.getUniqueId() + "-level");
        if (player.hasMetadata("SavageEquipments-"+ player.getUniqueId() + "-level")) {
            string = string.replace("{player-level}", String.valueOf(level.get(level.size() - 1).value()));
        }

        string = string.replace("{player-health}", String.valueOf(player.getHealth()));
        string = string.replace("{player-max-health}", String.valueOf(player.getMaxHealth()));
        return string;
    }



}
