package net.prosavage.savagerpg.utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class Placeholder {
    SRNamespacedKey SRNamespacedKey = new SRNamespacedKey();
    // This dumb thing below gives me a fat error and so I disabled it
    // java.lang.StackOverflowError: null
    //	at net.prosavage.savagerpg.utils.Placeholder.<init>(Placeholder.java:12) ~[?:?]
    //	at net.prosavage.savagerpg.utils.Formula.<init>(Formula.java:8) ~[?:?]
    //	at net.prosavage.savagerpg.itembuilder.Weapon.<init>(Weapon.java:26) ~[?:?]
    // Weapon Weapon = new Weapon();

    @SuppressWarnings("DEPRECATED")
    public String getPlayerPlaceholders(Player player, String string){
        Double player_exp = player.getPersistentDataContainer().get(SRNamespacedKey.namespacedKey_player_exp, PersistentDataType.DOUBLE);
        Integer player_level = player.getPersistentDataContainer().get(SRNamespacedKey.getNamespacedKey_player_level(), PersistentDataType.INTEGER);
        assert player_level != null;
        string = string.replace("{player-level}", String.valueOf(player_level));
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
