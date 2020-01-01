package net.prosavage.illyriarpg.commands;

import net.prosavage.illyriarpg.api.files.IArmorFiles;
import net.prosavage.illyriarpg.chatmenu.ArmorItemEditor;
import net.prosavage.illyriarpg.utils.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ArmorCommand implements CommandExecutor {

    Color Color = new Color();
    ArmorItemEditor ArmorItemEditor = new ArmorItemEditor();
    IArmorFiles IArmorFiles = new IArmorFiles();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                player.sendMessage(Color.ify(""));
                player.sendMessage(Color.ify("------------------------------------------------"));
                player.sendMessage(Color.ify(""));
                player.sendMessage(Color.ify("&dTotal (yaml) armor loaded: " + IArmorFiles.getArmorAmount()));
                player.sendMessage(Color.ify(""));
                player.sendMessage(Color.ify("&d/armor edit (allows you to create an ingame item)"));
                player.sendMessage(Color.ify(""));
                player.sendMessage(Color.ify("------------------------------------------------"));
                player.sendMessage(Color.ify(""));
            }
            if (args.length >= 1) {
                if (args[0].equals("edit")) {
                    ArmorItemEditor.getArmorCreateMenu(player, 1);
                }
                return true;
            }
        }
        return false;
    }
}
