package net.prosavage.savageequipment.commands;

import net.prosavage.savageequipment.SEPlayers;
import net.prosavage.savageequipment.SavageEquipment;
import net.prosavage.savageequipment.utils.Color;
import net.prosavage.savageequipment.utils.Formula;
import net.prosavage.savageequipment.utils.Placeholder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SavageEquipmentCommand implements CommandExecutor {

    Color Color = new Color();
    Formula Formula = new Formula();
    Placeholder Placeholder = new Placeholder();

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
                    SavageEquipment.getInstance().saveDefaultConfig();
                    SavageEquipment.getInstance().sendConsole("&aReloading complete");
                    return true;
                }
            }
            if (args[0].equals("info")){
                if (player.isOp()){
                    SEPlayers players = new SEPlayers(player);
                    players.setSEMaxExp(Formula.eval(Placeholder.getPlayerPlaceholders(player, String.valueOf(SavageEquipment.getInstance().getConfig().get("formulas.exp")))));
                    player.sendMessage("Level " + String.valueOf(players.getSELevel()));
                    player.sendMessage("EXP " + String.valueOf(players.getSEExp()));
                    player.sendMessage("MAX-EXP " + String.valueOf(players.getSEMaxExp()));
                    player.sendMessage("SKILLPOINTS " + String.valueOf(players.getSESkillpoints()));
                }
            }
        }
        return false;
    }
}
