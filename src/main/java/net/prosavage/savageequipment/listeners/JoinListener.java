package net.prosavage.savageequipment.listeners;

import net.prosavage.savageequipment.SavageEquipment;
import net.prosavage.savageequipment.data.YAML;
import net.prosavage.savageequipment.utils.Formula;
import net.prosavage.savageequipment.utils.Placeholder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

import java.util.List;
import java.util.Map;

public class JoinListener implements Listener {

    YAML YAML = new YAML();
    Placeholder Placeholder = new Placeholder();
    Formula Formula = new Formula();

    @EventHandler
    public void playerJoin(PlayerLoginEvent e){
        Player player = e.getPlayer();

        if (!(YAML.fileExist(player))) {
            YAML.createFile(player);
        }

        player.setMetadata("SavageEquipments-"+ player.getUniqueId() + "-level", new FixedMetadataValue(SavageEquipment.getInstance(), 1));
        player.setMetadata("SavageEquipments-"+ player.getUniqueId() + "-skillpoints", new FixedMetadataValue(SavageEquipment.getInstance(), 1));
        player.setMetadata("SavageEquipments-"+ player.getUniqueId() + "-exp", new FixedMetadataValue(SavageEquipment.getInstance(), 0));

        List<MetadataValue> level = player.getMetadata("SavageEquipments-"+ player.getUniqueId() + "-level");

        double evaluated = Formula.eval(Placeholder.getPlayerPlaceholders(player, String.valueOf(SavageEquipment.getInstance().getConfig().get("formulas.exp"))));

        player.setMetadata("SavageEquipments-"+ player.getUniqueId() + "-max_exp", new FixedMetadataValue(SavageEquipment.getInstance(), evaluated));


    }

}

