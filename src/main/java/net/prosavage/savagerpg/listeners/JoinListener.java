package net.prosavage.savageequipment.listeners;

import net.prosavage.savagerpg.SavageRPG;
import net.prosavage.savagerpg.data.YAML;
import net.prosavage.savagerpg.utils.Formula;
import net.prosavage.savagerpg.utils.Placeholder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

import java.util.List;

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

        player.setMetadata("SavageEquipments-"+ player.getUniqueId() + "-level", new FixedMetadataValue(SavageRPG.getInstance(), 1));
        player.setMetadata("SavageEquipments-"+ player.getUniqueId() + "-skillpoints", new FixedMetadataValue(SavageRPG.getInstance(), 1));
        player.setMetadata("SavageEquipments-"+ player.getUniqueId() + "-exp", new FixedMetadataValue(SavageRPG.getInstance(), 0));

        List<MetadataValue> level = player.getMetadata("SavageEquipments-"+ player.getUniqueId() + "-level");

        double evaluated = Formula.eval(Placeholder.getPlayerPlaceholders(player, String.valueOf(SavageRPG.getInstance().getConfig().get("formulas.exp"))));

        player.setMetadata("SavageEquipments-"+ player.getUniqueId() + "-max_exp", new FixedMetadataValue(SavageRPG.getInstance(), evaluated));


    }

}

