package net.prosavage.savageequipment;

import net.prosavage.savageequipment.command.GiveItem;
import net.prosavage.savageequipment.command.romanToIntAndIntToRoman;
import net.prosavage.savageequipment.enchant.CESword;
import net.prosavage.savageequipment.listener.DamageListener;
import net.prosavage.savageequipment.listener.ArmorChange;
import net.prosavage.savageequipment.listener.ClickToEnchant;
import net.prosavage.savageequipment.loop.JoinRegenLoop;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import net.prosavage.savageequipment.somewhatusefulstuff.Color;

import java.io.File;
import java.io.IOException;

public final class Main extends JavaPlugin {

    private static Main instance;
    Color Color = new Color();
    FileConfiguration config = this.getConfig();
    String prefix = (String) this.getConfig().get("PREFIX");

    @Override
    public void onEnable() {
        loadConfig();
        loadArmorConfig();
        loadWeaponConfig();
        getLogger().info(Color.ify("&aSavageEquipment have been loaded/enabled."));
        getServer().getPluginManager().registerEvents(new ArmorChange(), this);
        getServer().getPluginManager().registerEvents(new DamageListener(), this);
        getServer().getPluginManager().registerEvents(new JoinRegenLoop(), this);
        getServer().getPluginManager().registerEvents(new CESword(), this);
        getServer().getPluginManager().registerEvents(new ClickToEnchant(), this);
        getCommand("giveThing").setExecutor(new GiveItem());
        getCommand("romanToIntAndIntToRoman").setExecutor(new romanToIntAndIntToRoman());
    }

    @Override
    public void onDisable() {
        getLogger().info(Color.ify("&cSavageEquipment have unloaded/disabled."));
    }
    public Main(){
        instance = this;
    }

    public static Main getInstance() {
        return instance;
    }

    public void loadConfig(){

        if (!(new File(this.getDataFolder(), "config.yml").exists())){

            config.set("prefix", "&7[&eSavageEquipment&7] &7");
            saveConfig();
            getLogger().info("Default config loaded.");
        }
    }


    public Object getWeaponConfig() {
        if ((new File(this.getDataFolder(), "weapon.yml").exists())) {
            File file = new File(this.getDataFolder(), "weapon.yml");
            FileConfiguration armorConfig = YamlConfiguration.loadConfiguration(file);
            return armorConfig;
        }
        return null;
    }

    public void loadWeaponConfig(){
        if (!(new File(this.getDataFolder(), "weapon.yml").exists())){
            File file = new File(this.getDataFolder(), "weapon.yml");
            YamlConfiguration yaml = new YamlConfiguration();
            yaml.set("material.WOODEN.max-damage", 0.50);
            yaml.set("material.WOODEN.min-damage", 0.01);
            yaml.set("material.WOODEN.max-crit-chance", 3.00);
            yaml.set("material.WOODEN.min-crit-chance", 1.00);
            yaml.set("material.WOODEN.max-crit-multiplier", 2.00);
            yaml.set("material.WOODEN.min-crit-multiplier", 1.50);

            yaml.set("material.STONE.max-damage", 1.00);
            yaml.set("material.STONE.min-damage", 0.50);
            yaml.set("material.STONE.max-crit-chance", 2.50);
            yaml.set("material.STONE.min-crit-chance", 1.00);
            yaml.set("material.STONE.max-crit-multiplier", 2.00);
            yaml.set("material.STONE.min-crit-multiplier", 1.50);

            yaml.set("material.GOLDEN.max-damage", 1.50);
            yaml.set("material.GOLDEN.min-damage", 1.00);
            yaml.set("material.GOLDEN.max-crit-chance", 2.00);
            yaml.set("material.GOLDEN.min-crit-chance", 1.00);
            yaml.set("material.GOLDEN.max-crit-multiplier", 2.00);
            yaml.set("material.GOLDEN.min-crit-multiplier", 1.50);

            yaml.set("material.IRON.max-damage", 2.00);
            yaml.set("material.IRON.min-damage", 1.50);
            yaml.set("material.IRON.max-crit-chance", 1.50);
            yaml.set("material.IRON.min-crit-chance", 1.00);
            yaml.set("material.IRON.max-crit-multiplier", 2.00);
            yaml.set("material.IRON.min-crit-multiplier", 1.50);

            yaml.set("material.DIAMOND.max-damage", 2.00);
            yaml.set("material.DIAMOND.min-damage", 1.50);
            yaml.set("material.DIAMOND.max-crit-chance", 1.50);
            yaml.set("material.DIAMOND.min-crit-chance", 1.00);
            yaml.set("material.DIAMOND.max-crit-multiplier", 2.00);
            yaml.set("material.DIAMOND.min-crit-multiplier", 1.50);

            try {
                yaml.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            getLogger().info("Default Weapon config loaded.");
        }
    }

    public Object getArmorConfig() {
        if ((new File(this.getDataFolder(), "armor.yml").exists())) {
            File file = new File(this.getDataFolder(), "armor.yml");
            FileConfiguration armorConfig = YamlConfiguration.loadConfiguration(file);
            return armorConfig;
        }
        return null;
    }


    public void loadArmorConfig(){
        if (!(new File(this.getDataFolder(), "armor.yml").exists())){
            File file = new File(this.getDataFolder(), "armor.yml");
            YamlConfiguration yaml = new YamlConfiguration();
            yaml.set("material.LEATHER.max-health", 0.00);
            yaml.set("material.LEATHER.min-health", 5.00);
            yaml.set("material.LEATHER.max-protection", 1.00);
            yaml.set("material.LEATHER.min-protection", 1.00);
            yaml.set("material.LEATHER.max-regen", 3.00);
            yaml.set("material.LEATHER.min-regen", 0.00);
            yaml.set("material.LEATHER.max-scroll", 3);
            yaml.set("material.LEATHER.min-scroll", 0);
            yaml.set("material.LEATHER.max-gem", 3);
            yaml.set("material.LEATHER.min-gem", 0);

            yaml.set("material.GOLDEN.max-health", 7.00);
            yaml.set("material.GOLDEN.min-health", 3.00);
            yaml.set("material.GOLDEN.max-protection", 2.00);
            yaml.set("material.GOLDEN.min-protection", 0.00);
            yaml.set("material.GOLDEN.max-regen", 3.00);
            yaml.set("material.GOLDEN.min-regen", 0.00);
            yaml.set("material.GOLDEN.max-scroll", 3);
            yaml.set("material.GOLDEN.min-scroll", 0);
            yaml.set("material.GOLDEN.max-gem", 3);
            yaml.set("material.GOLDEN.min-gem", 0);

            yaml.set("material.CHAINMAIL.max-health", 5.00);
            yaml.set("material.CHAINMAIL.min-health", 1.00);
            yaml.set("material.CHAINMAIL.max-protection", 3.00);
            yaml.set("material.CHAINMAIL.min-protection", 1.00);
            yaml.set("material.CHAINMAIL.max-regen", 2.00);
            yaml.set("material.CHAINMAIL.min-regen", 0.00);
            yaml.set("material.CHAINMAIL.max-scroll", 3);
            yaml.set("material.CHAINMAIL.min-scroll", 0);
            yaml.set("material.CHAINMAIL.max-gem", 3);
            yaml.set("material.CHAINMAIL.min-gem", 0);

            yaml.set("material.IRON.max-health", 3.00);
            yaml.set("material.IRON.min-health", 1.00);
            yaml.set("material.IRON.max-protection", 4.00);
            yaml.set("material.IRON.min-protection", 2.00);
            yaml.set("material.IRON.max-regen", 2.00);
            yaml.set("material.IRON.min-regen", 0.00);
            yaml.set("material.IRON.max-scroll", 3);
            yaml.set("material.IRON.min-scroll", 0);
            yaml.set("material.IRON.max-gem", 3);
            yaml.set("material.IRON.min-gem", 0);

            yaml.set("material.DIAMOND.max-health", 1.00);
            yaml.set("material.DIAMOND.min-health", 3.00);
            yaml.set("material.DIAMOND.max-protection", 5.00);
            yaml.set("material.DIAMOND.min-protection", 3.00);
            yaml.set("material.DIAMOND.max-regen", 1.00);
            yaml.set("material.DIAMOND.min-regen", 0.00);
            yaml.set("material.DIAMOND.max-scroll", 3);
            yaml.set("material.DIAMOND.min-scroll", 0);
            yaml.set("material.DIAMOND.max-gem", 3);
            yaml.set("material.DIAMOND.min-gem", 0);

            try {
                yaml.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            getLogger().info("Default Armor config loaded.");
        }
    }

}
