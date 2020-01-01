package net.prosavage.illyriarpg;

import net.prosavage.illyriarpg.api.files.IAbilityFiles;
import net.prosavage.illyriarpg.api.files.IArmorFiles;
import net.prosavage.illyriarpg.api.files.IWeaponFiles;
import net.prosavage.illyriarpg.commands.ArmorCommand;
import net.prosavage.illyriarpg.commands.WeaponCommand;
import net.prosavage.illyriarpg.utils.Color;
import net.prosavage.illyriarpg.utils.handlers.abilities.DropAction;
import net.prosavage.illyriarpg.utils.handlers.abilities.LeftAndRightAction;
import org.bukkit.Bukkit;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import java.io.File;
import java.util.HashMap;
import java.util.Objects;
import java.util.function.Consumer;

public final class IllyriaRPG extends JavaPlugin {

    private static IllyriaRPG instance;
    private Color Color = new Color();
    private String weaponFolder = getDataFolder() + "\\weapons";
    private String armorFolder = getDataFolder() + "\\armors";
    private String abilityFolder = getDataFolder() + "\\abilities";
    private File configFile = new File(getDataFolder(), "config.yml");

    private IWeaponFiles IWeaponFiles = new IWeaponFiles();
    private IArmorFiles IArmorFiles = new IArmorFiles();
    private IAbilityFiles IAbilityFiles = new IAbilityFiles();

    private HashMap<String, Consumer<PlayerInteractEvent>> leftClickInteractions = new HashMap<>();
    private HashMap<String, Consumer<PlayerInteractEvent>> rightClickInteractions = new HashMap<>();
    private HashMap<String, Consumer<PlayerDropItemEvent>> itemDropInteractions = new HashMap<>();

    public IllyriaRPG() {
        instance = this;
    }

    public static IllyriaRPG getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        if (!configFile.exists()) {
            saveDefaultConfig();
        }
        if (getConfig().getBoolean("server-startup.big-fat-bloody-message-enabled")) {
            sendConsole("");
            sendConsole("\n\n&c" +
                    " ██▓ ██▓     ██▓   ▓██   ██▓ ██▀███   ██▓ ▄▄▄       ██▀███   ██▓███    ▄████ \n" +
                    "▓██▒▓██▒    ▓██▒    ▒██  ██▒▓██ ▒ ██▒▓██▒▒████▄    ▓██ ▒ ██▒▓██░  ██▒ ██▒ ▀█▒\n" +
                    "▒██▒▒██░    ▒██░     ▒██ ██░▓██ ░▄█ ▒▒██▒▒██  ▀█▄  ▓██ ░▄█ ▒▓██░ ██▓▒▒██░▄▄▄░\n" +
                    "░██░▒██░    ▒██░     ░ ▐██▓░▒██▀▀█▄  ░██░░██▄▄▄▄██ ▒██▀▀█▄  ▒██▄█▓▒ ▒░▓█  ██▓\n" +
                    "░██░░██████▒░██████▒ ░ ██▒▓░░██▓ ▒██▒░██░ ▓█   ▓██▒░██▓ ▒██▒▒██▒ ░  ░░▒▓███▀▒\n" +
                    "░▓  ░ ▒░▓  ░░ ▒░▓  ░  ██▒▒▒ ░ ▒▓ ░▒▓░░▓   ▒▒   ▓▒█░░ ▒▓ ░▒▓░▒▓▒░ ░  ░ ░▒   ▒ \n" +
                    " ▒ ░░ ░ ▒  ░░ ░ ▒  ░▓██ ░▒░   ░▒ ░ ▒░ ▒ ░  ▒   ▒▒ ░  ░▒ ░ ▒░░▒ ░       ░   ░ \n" +
                    " ▒ ░  ░ ░     ░ ░   ▒ ▒ ░░    ░░   ░  ▒ ░  ░   ▒     ░░   ░ ░░       ░ ░   ░ \n" +
                    " ░      ░  ░    ░  ░░ ░        ░      ░        ░  ░   ░                    ░ \n" +
                    "                    ░ ░                                                      ");
        }
        Objects.requireNonNull(this.getCommand("weapon")).setExecutor(new WeaponCommand());
        Objects.requireNonNull(this.getCommand("armor")).setExecutor(new ArmorCommand());
        Bukkit.getPluginManager().registerEvents(new LeftAndRightAction(), this);
        Bukkit.getPluginManager().registerEvents(new DropAction(), this);
        new BukkitRunnable(){
            @Override
            public void run() {
                sendConsole("");
                sendConsole("&aTotal (YAML) weapon(s) loaded: &e" + IWeaponFiles.getWeaponAmount());
                sendConsole("&aTotal (YAML) armor(s) loaded: &e" + IArmorFiles.getArmorAmount());
                sendConsole("&aTotal abilities loaded: &e" + IAbilityFiles.getAbilities().size());
                sendConsole("");
            }
        }.runTaskLater(this, 1L);
    }

    @Override
    public void onDisable() {
        IAbilityFiles.removeAllFiles();
    }

    public void sendConsole(Object object){
        System.out.println(Color.ify(String.valueOf(object)));
    }

    public String getWeaponFolder() {
        return weaponFolder;
    }

    public String getArmorFolder() {
        return armorFolder;
    }

    public String getAbilityFolder() {
        return abilityFolder;
    }

    public HashMap<String, Consumer<PlayerInteractEvent>> getLeftClickInteractions(){
        return leftClickInteractions;
    }

    public HashMap<String, Consumer<PlayerInteractEvent>> getRightClickInteractions() {
        return rightClickInteractions;
    }

    public HashMap<String, Consumer<PlayerDropItemEvent>> getItemDropInteractions() {
        return itemDropInteractions;
    }

}
