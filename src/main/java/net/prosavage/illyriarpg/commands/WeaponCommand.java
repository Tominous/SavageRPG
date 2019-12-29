package net.prosavage.illyriarpg.commands;

import net.prosavage.illyriarpg.api.files.IWeaponFiles;
import net.prosavage.illyriarpg.chatmenu.WeaponItemEditor;
import net.prosavage.illyriarpg.utils.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class WeaponCommand implements CommandExecutor {

    IWeaponFiles IWeaponFiles = new IWeaponFiles();
    Color Color = new Color();
    WeaponItemEditor WeaponItemEditor = new WeaponItemEditor();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                player.sendMessage(Color.ify(""));
                player.sendMessage(Color.ify("------------------------------------------------"));
                player.sendMessage(Color.ify(""));
                player.sendMessage(Color.ify("&dTotal (yaml) weapons loaded: " + IWeaponFiles.getWeaponAmount()));
                player.sendMessage(Color.ify(""));
                player.sendMessage(Color.ify("&d/weapon edit (allows you to create an ingame item)"));
                player.sendMessage(Color.ify("&d/weapon file (allows you to edit a yaml file item)"));
                player.sendMessage(Color.ify(""));
                player.sendMessage(Color.ify("------------------------------------------------"));
                player.sendMessage(Color.ify(""));
            }
            if (args.length >= 1) {
                if (args[0].equals("edit")) {
                    WeaponItemEditor.getWeaponCreateMenu(player, 1);
                }
                return true;
            }
        }
        return false;
    }

}
