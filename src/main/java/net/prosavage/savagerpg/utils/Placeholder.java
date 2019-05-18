package net.prosavage.savagerpg.utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;

import java.util.List;

public class Placeholder {
    // This dumb thing below gives me a fat error and so I disabled it
    // java.lang.StackOverflowError: null
    //	at net.prosavage.savagerpg.utils.Placeholder.<init>(Placeholder.java:12) ~[?:?]
    //	at net.prosavage.savagerpg.utils.Formula.<init>(Formula.java:8) ~[?:?]
    //	at net.prosavage.savagerpg.itembuilder.Weapon.<init>(Weapon.java:26) ~[?:?]
    // Weapon Weapon = new Weapon();

    @SuppressWarnings("DEPRECATED")
    public String getPlayerPlaceholders(Player player, String string){
        List<MetadataValue> metadata = player.getMetadata("SE_LEVEL");
        List<MetadataValue> level = player.getMetadata("SavageEquipments-"+ player.getUniqueId() + "-level");
        if (player.hasMetadata("SavageEquipments-"+ player.getUniqueId() + "-level")) {
            string = string.replace("{player-level}", String.valueOf(level.get(level.size() - 1).value()));
        }

        string = string.replace("{player-health}", String.valueOf(player.getHealth()));
        string = string.replace("{player-max-health}", String.valueOf(player.getMaxHealth()));
        return string;
    }

    public String getDamagePerSecondPlaceholders(ItemStack item, String max_damage, String min_damage, String cooldown, String string){
        string = string.replace("{max}", String.valueOf(max_damage));
        string = string.replace("{min}", String.valueOf(min_damage));
        string = string.replace("{cooldown}", String.valueOf(cooldown));
        return string;

    }



}
