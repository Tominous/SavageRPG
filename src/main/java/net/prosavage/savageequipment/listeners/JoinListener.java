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

public class JoinListener implements Listener {

    YAML YAML = new YAML();
    Placeholder Placeholder = new Placeholder();
    Formula Formula = new Formula();

    @EventHandler
    public void playerJoin(PlayerLoginEvent e){
        Player player = e.getPlayer();

        player.setMetadata("SavageEquipments-"+ player.getUniqueId() + "-level", new FixedMetadataValue(SavageEquipment.getInstance(), 1));
        player.setMetadata("SavageEquipments-"+ player.getUniqueId() + "-skillpoints", new FixedMetadataValue(SavageEquipment.getInstance(), 1));
        player.setMetadata("SavageEquipments-"+ player.getUniqueId() + "-exp", new FixedMetadataValue(SavageEquipment.getInstance(), 0));

        if (!(YAML.fileExist(player))) {
            YAML.createFile(player);
        }


        List<MetadataValue> level = player.getMetadata("SavageEquipments-"+ player.getUniqueId() + "-level");
        if (player.hasMetadata("SavageEquipments-"+ player.getUniqueId() + "-level")) {
            SavageEquipment.getInstance().sendConsole(level.toString());
        }

        player.sendMessage(SavageEquipment.getInstance().getYAMLValues().get("formulas.exp"));

        double evaluated = Formula.eval(Placeholder.getPlayerPlaceholders(player, String.valueOf(SavageEquipment.getInstance().getYAMLValues().get("formulas.exp"))));

        player.sendMessage(String.valueOf(evaluated));

        player.setMetadata("SavageEquipments-"+ player.getUniqueId() + "-max_exp", new FixedMetadataValue(SavageEquipment.getInstance(), evaluated));

    }

}
