package net.prosavage.illyriarpg;

import net.prosavage.illyriarpg.commands.WeaponCommand;
import net.prosavage.illyriarpg.utils.Color;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class IllyriaRPG extends JavaPlugin {

    private static IllyriaRPG instance;
    Color Color = new Color();
    String weaponFolder = getDataFolder() + "\\weapons";
    File rarityFile = new File(getDataFolder(), "rarity.yml");
    File configFile = new File(getDataFolder(), "config.yml");

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
            saveResource("formulas.yml", false);
        }
        this.getCommand("weapon").setExecutor(new WeaponCommand());
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

