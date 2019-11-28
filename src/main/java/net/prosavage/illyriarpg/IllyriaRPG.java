package net.prosavage.illyriarpg;

import net.prosavage.illyriarpg.api.files.IWeaponFiles;
import net.prosavage.illyriarpg.commands.WeaponCommand;
import net.prosavage.illyriarpg.utils.Color;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.util.Objects;

public final class IllyriaRPG extends JavaPlugin {

    private static IllyriaRPG instance;
    private Color Color = new Color();
    private String weaponFolder = getDataFolder() + "\\weapons";
    private File rarityFile = new File(getDataFolder(), "rarity.yml");
    private File configFile = new File(getDataFolder(), "config.yml");
    private IWeaponFiles IWeaponFiles = new IWeaponFiles();

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
                    "██▓ ██▓     ██▓   ▓██   ██▓ ██▀███   ██▓ ▄▄▄       ██▀███   ██▓███    ▄████ \n" +
                    "▓██▒▓██▒    ▓██▒    ▒██  ██▒▓██ ▒ ██▒▓██▒▒████▄    ▓██ ▒ ██▒▓██░  ██▒ ██▒ ▀█▒\n" +
                    "▒██▒▒██░    ▒██░     ▒██ ██░▓██ ░▄█ ▒▒██▒▒██  ▀█▄  ▓██ ░▄█ ▒▓██░ ██▓▒▒██░▄▄▄░\n" +
                    "░██░▒██░    ▒██░     ░ ▐██▓░▒██▀▀█▄  ░██░░██▄▄▄▄██ ▒██▀▀█▄  ▒██▄█▓▒ ▒░▓█  ██▓\n" +
                    "░██░░██████▒░██████▒ ░ ██▒▓░░██▓ ▒██▒░██░ ▓█   ▓██▒░██▓ ▒██▒▒██▒ ░  ░░▒▓███▀▒\n" +
                    "░▓  ░ ▒░▓  ░░ ▒░▓  ░  ██▒▒▒ ░ ▒▓ ░▒▓░░▓   ▒▒   ▓▒█░░ ▒▓ ░▒▓░▒▓▒░ ░  ░ ░▒   ▒ \n" +
                    " ▒ ░░ ░ ▒  ░░ ░ ▒  ░▓██ ░▒░   ░▒ ░ ▒░ ▒ ░  ▒   ▒▒ ░  ░▒ ░ ▒░░▒ ░       ░   ░ \n" +
                    " ▒ ░  ░ ░     ░ ░   ▒ ▒ ░░    ░░   ░  ▒ ░  ░   ▒     ░░   ░ ░░       ░ ░   ░ \n" +
                    " ░      ░  ░    ░  ░░ ░        ░      ░        ░  ░   ░                    ░ \n");
        }
        sendConsole("");
        sendConsole("&aTotal (YAML) weapons loaded: &e" + IWeaponFiles.getWeaponAmount());
        sendConsole("");
        Objects.requireNonNull(this.getCommand("weapon")).setExecutor(new WeaponCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public File getRarityFile() {
        return rarityFile;
    }

    public String getWeaponFolder() {
        return weaponFolder;
    }

    public void sendConsole(String string){
        getLogger().info(Color.ify(string));
    }

}

