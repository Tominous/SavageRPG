package net.prosavage.savagerpg;

import net.prosavage.savageequipment.listeners.JoinListener;
import net.prosavage.savageequipment.listeners.QuitListener;
import net.prosavage.savagerpg.commands.GiveItem;
import net.prosavage.savagerpg.commands.SavageRPGCommand;
import net.prosavage.savagerpg.libs.com.codingforcookies.armorequip.ArmorListener;
import net.prosavage.savagerpg.listeners.ArmorEquipListener;
import net.prosavage.savagerpg.listeners.DamageListener;
import net.prosavage.savagerpg.utils.Color;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.Objects;

public final class SavageRPG extends JavaPlugin {

    private static SavageRPG instance;
    Color Color = new Color();
    String prefix = (String) this.getConfig().get("prefix");

    File armorFile = new File(getDataFolder(), "armor.yml");
    YamlConfiguration armorConfig;

    File weaponFile = new File(getDataFolder(), "weapon.yml");
    YamlConfiguration weaponConfig;

    File armorEventFile = new File(getDataFolder(), "armor-event.yml");
    YamlConfiguration armorEventConfig;

    @Override
    public void onEnable() {
        if (!(weaponFile.exists())){
            saveDefaultConfig();
            this.saveResource("armor.yml", true);
            this.saveResource("weapon.yml", true);
            this.saveResource("armor-event.yml", true);
        }
        getCommand("savagerpg").setExecutor(new SavageRPGCommand());
        getCommand("giveitem").setExecutor(new GiveItem());
        loadEvents();
    }

    @Override
    public void onDisable() {
        saveFiles();
        saveConfig();
    }

    public SavageRPG() {
        instance = this;
    }

    public static SavageRPG getInstance() {
        return instance;
    }

    public void sendConsole(String s) {
        System.out.println(Color.ify(prefix + s));
    }

    public FileConfiguration getArmorConfig(){
        armorConfig = new YamlConfiguration();
        try {
            armorConfig.load(armorFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
        return armorConfig;
    }

    public FileConfiguration getWeaponConfig(){
        weaponConfig = new YamlConfiguration();
        try {
            weaponConfig.load(weaponFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
        return weaponConfig;
    }

    public FileConfiguration getArmorEventConfig(){
        armorEventConfig = new YamlConfiguration();
        try {
            armorEventConfig.load(armorEventFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
        return armorEventConfig;
    }

    public File getArmorFile(){
        return armorFile;
    }

    public File getWeaponFile(){
        return weaponFile;
    }

    public File getArmorEventFile(){
        return armorEventFile;
    }

    public void loadEvents(){
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new QuitListener(), this);
        getServer().getPluginManager().registerEvents(new DamageListener(), this);
        getServer().getPluginManager().registerEvents(new ArmorEquipListener(), this);
        getServer().getPluginManager().registerEvents(new ArmorListener(getArmorEventConfig().getStringList("blocked")), this);
    }

    public void reloadFiles(){
        weaponConfig = YamlConfiguration.loadConfiguration(weaponFile);
        Reader defConfigStream = null;
        Reader defConfigStream2 = null;
        Reader defConfigStream3 = null;
        try {
            defConfigStream = new InputStreamReader(Objects.requireNonNull(this.getResource("weapon.yml")), "UTF8");
            defConfigStream2 = new InputStreamReader(Objects.requireNonNull(this.getResource("armor.yml")), "UTF8");
            defConfigStream3 = new InputStreamReader(Objects.requireNonNull(this.getResource("armor-event.yml")), "UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            YamlConfiguration defConfig2 = YamlConfiguration.loadConfiguration(defConfigStream2);
            YamlConfiguration defConfig3 = YamlConfiguration.loadConfiguration(defConfigStream3);
            weaponConfig.setDefaults(defConfig);
            armorConfig.setDefaults(defConfig2);
            armorEventConfig.setDefaults(defConfig3);
        }
    }

    public void saveFiles(){
        try {
            getWeaponConfig().save(weaponFile);
            getArmorConfig().save(armorFile);
            getArmorEventConfig().save(armorEventFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
