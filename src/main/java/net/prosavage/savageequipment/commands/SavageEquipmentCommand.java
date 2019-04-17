package net.prosavage.savageequipment.commands;

import net.prosavage.savageequipment.SavageEquipment;
import net.prosavage.savageequipment.utils.Color;
import net.prosavage.savageequipment.utils.Formula;
import net.prosavage.savageequipment.utils.Placeholder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;

import java.io.IOException;
import java.util.List;

public class SavageEquipmentCommand implements CommandExecutor {

    net.prosavage.savageequipment.utils.Color Color = new Color();
    net.prosavage.savageequipment.utils.Formula Formula = new Formula();
    net.prosavage.savageequipment.utils.Placeholder Placeholder = new Placeholder();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player player = (Player) sender;
            if (args[0].equals("help")){
                if (player.isOp()){
                    player.sendMessage(Color.ify("&a/savageequipment reload - reloads the config"));
                    return true;
                }
            }
            if (args[0].equals("reload")){
                if (player.isOp()) {
                    SavageEquipment.getInstance().sendConsole("&aReloading config...");
                    SavageEquipment.getInstance().reloadConfig();
                    SavageEquipment.getInstance().reloadFiles();
                    SavageEquipment.getInstance().sendConsole("&aReloading complete");
                    return true;
                }
            }
            if (args[0].equals("info")){
                if (player.isOp()){
                    List<MetadataValue> level = player.getMetadata("SavageEquipments-"+ player.getUniqueId() + "-level");
                    List<MetadataValue> exp = player.getMetadata("SavageEquipments-"+ player.getUniqueId() + "-exp");
                    List<MetadataValue> max_exp = player.getMetadata("SavageEquipments-"+ player.getUniqueId() + "-max_exp");
                    List<MetadataValue> skillpoints = player.getMetadata("SavageEquipments-"+ player.getUniqueId() + "-skillpoints");
                    if (!(level.isEmpty())) {
                        player.sendMessage("Level " + String.valueOf(level.get(level.size() - 1).value()));
                        player.sendMessage("EXP " + String.valueOf(exp.get(exp.size() - 1).value()));
                        player.sendMessage("Max EXP " + String.valueOf(max_exp.get(max_exp.size() - 1).value()));
                        player.sendMessage("Skillpoints " + String.valueOf(skillpoints.get(skillpoints.size() - 1).value()));
                    }
                }
            }
        }
        return false;
    }
}
