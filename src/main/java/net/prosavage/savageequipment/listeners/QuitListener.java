package net.prosavage.savageequipment.listeners;

import net.prosavage.savageequipment.SavageEquipment;
import net.prosavage.savageequipment.data.YAML;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.metadata.MetadataValue;

import java.util.List;

public class QuitListener implements Listener {

    net.prosavage.savageequipment.data.YAML YAML = new YAML();

    @EventHandler
    public void playerQuit(PlayerQuitEvent e){

        Player player = e.getPlayer();
        List<MetadataValue> level = player.getMetadata("SavageEquipments-"+ player.getUniqueId() + "-level");
        List<MetadataValue> exp = player.getMetadata("SavageEquipments-"+ player.getUniqueId() + "-exp");
        List<MetadataValue> max_exp = player.getMetadata("SavageEquipments-"+ player.getUniqueId() + "-max_exp");
        List<MetadataValue> skillpoints = player.getMetadata("SavageEquipments-"+ player.getUniqueId() + "-skillpoints");
        if (player.hasMetadata("SavageEquipments-"+ player.getUniqueId() + "-level")) {
            YAML.setLevel(player, Integer.valueOf(String.valueOf(level.get(0).value())));
        }
        if (player.hasMetadata("SavageEquipments-"+ player.getUniqueId() + "-exp")) {
            YAML.setEXP(player, Double.valueOf(String.valueOf(exp.get(0).value())));
        }
        if (player.hasMetadata("SavageEquipments-"+ player.getUniqueId() + "-max_exp")) {
            YAML.setMaxEXP(player, Double.valueOf(String.valueOf(max_exp.get(0).value())));
        }
        if (player.hasMetadata("SavageEquipments-"+ player.getUniqueId() + "-skillpoints")) {
            YAML.setSkillPoints(player, Integer.valueOf(String.valueOf(skillpoints.get(0).value())));
        }

        player.removeMetadata("SavageEquipments-"+ player.getUniqueId() + "-level", SavageEquipment.getInstance());
        player.removeMetadata("SavageEquipments-"+ player.getUniqueId() + "-exp", SavageEquipment.getInstance());
        player.removeMetadata("SavageEquipments-"+ player.getUniqueId() + "-max_exp", SavageEquipment.getInstance());
        player.removeMetadata("SavageEquipments-"+ player.getUniqueId() + "-skillpoints", SavageEquipment.getInstance());

    }
}
