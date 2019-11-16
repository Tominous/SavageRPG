package net.prosavage.illyriarpg.commands;

import me.tom.sparse.spigot.chat.menu.ChatMenu;
import me.tom.sparse.spigot.chat.menu.element.BooleanElement;
import me.tom.sparse.spigot.chat.menu.element.ButtonElement;
import me.tom.sparse.spigot.chat.menu.element.InputElement;
import me.tom.sparse.spigot.chat.menu.element.TextElement;
import net.prosavage.illyriarpg.IllyriaRPG;
import net.prosavage.illyriarpg.api.ICreator;
import net.prosavage.illyriarpg.api.files.IWeaponFiles;
import net.prosavage.illyriarpg.api.keys.INamespacedKeys;
import net.prosavage.illyriarpg.builder.Weapon;
import net.prosavage.illyriarpg.chatmenu.WeaponFileEditor;
import net.prosavage.illyriarpg.chatmenu.WeaponItemEditor;
import net.prosavage.illyriarpg.utils.Color;
import net.prosavage.illyriarpg.utils.Number;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class WeaponCommand implements CommandExecutor {

    Number Number = new Number();
    IWeaponFiles IWeaponFiles = new IWeaponFiles();
    Color Color = new Color();
    WeaponItemEditor WeaponItemEditor = new WeaponItemEditor();
    WeaponFileEditor WeaponFileEditor = new WeaponFileEditor();

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
                if (args[0].equals("file")) {
                    WeaponFileEditor.getWeaponCreateMenu(player, 1);
                }
                return true;
            }
        }
        return false;
    }

}
