package net.prosavage.savagerpg.api.files;

import net.prosavage.savagerpg.SavageRPG;
import net.prosavage.savagerpg.utils.Color;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class SWeaponFiles {

    Color Color = new Color();

    public File[] getRarityFolders() {
        File dir = new File(SavageRPG.getInstance().getWeaponFolder());
        File[] files = dir.listFiles();
        return files;
    }

    public YamlConfiguration getWeaponConfig(){
        YamlConfiguration weaponConfig = new YamlConfiguration();
        try {
            weaponConfig.load(SavageRPG.getInstance().getRarityFile());
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        return weaponConfig;

    }

    public List<String> getRarityNames(){
        File dir = new File(SavageRPG.getInstance().getWeaponFolder());
        SavageRPG.getInstance().sendConsole(String.valueOf(dir.list()));
        if (dir.list() == null){
            return Collections.singletonList("null");
        }
        return Arrays.asList(dir.list());
    }

    public File[] getRarityWeaponFiles(String string) {
        File[] dir = getRarityFolders();
        for (File file : dir) {
            if (file.getName().equals(string)) {
                return file.listFiles();
            }
        }
        return null;
    }

    public List<String> getRarityWeaponNames(String string) {
        File[] dir = getRarityFolders();
        for (File file : dir) {
            if (file.getName().equals(string)) {
                return Arrays.asList(Objects.requireNonNull(file.list()));
            }
        }
        return null;
    }

    public int getWeaponAmount() {
        File[] dir = getRarityFolders();
        int amount = 0;
        for (File file : dir) {
            amount = amount + Objects.requireNonNull(file.listFiles()).length;
        }
        return amount;
    }

    public String getStat(String rarity, String weaponName, String stat_node){
        File[] dir = getRarityWeaponFiles(rarity);
        File selectedFile = null;
        assert dir != null;
        for (File file : dir){
            if(file.getName().equals(weaponName + ".yml")){
                selectedFile = file;
            }
        }
        YamlConfiguration WeaponFiles = YamlConfiguration.loadConfiguration(Objects.requireNonNull(selectedFile));
        if (WeaponFiles.get(stat_node) != null){
            return String.valueOf(WeaponFiles.get(stat_node));
        }
        return null;
    }

    public List<String> getFileLore(String rarity, String weaponName){
        File[] dir = getRarityWeaponFiles(rarity);
        File selectedFile = null;
        assert dir != null;
        for (File file : dir){
            if(file.getName().equals(weaponName + ".yml")){
                selectedFile = file;
            }
        }
        YamlConfiguration WeaponFiles = YamlConfiguration.loadConfiguration(Objects.requireNonNull(selectedFile));
        return WeaponFiles.getStringList("lore");
    }

    public List<String> getFileAbilityDescription(String rarity, String weaponName){
        File[] dir = getRarityWeaponFiles(rarity);
        File selectedFile = null;
        assert dir != null;
        for (File file : dir){
            if(file.getName().equals(weaponName + ".yml")){
                selectedFile = file;
            }
        }
        YamlConfiguration WeaponFiles = YamlConfiguration.loadConfiguration(Objects.requireNonNull(selectedFile));
        return WeaponFiles.getStringList("ability-description");
    }

    public List<String> getList(String rarity, String weaponName, String node){
        File[] dir = getRarityWeaponFiles(rarity);
        File selectedFile = null;
        assert dir != null;
        for (File file : dir){
            if(file.getName().equals(weaponName + ".yml")){
                selectedFile = file;
            }
        }
        YamlConfiguration WeaponFiles = YamlConfiguration.loadConfiguration(Objects.requireNonNull(selectedFile));
        return WeaponFiles.getStringList(node);
    }

    public String getName(String rarity, String weaponName){
        return getStat(rarity, weaponName, "item-name");
    }

    public String getMaterial(String rarity, String weaponName){
        return getStat(rarity, weaponName, "materials");
    }

    public String getRarity(String rarity, String weaponName){
        return getStat(rarity, weaponName, "rarity");
    }

    public List<String> getLore(String rarity, String weaponName){
        return getFileLore(rarity,weaponName);
    }

    public int getMinLevel(String rarity, String weaponName){
        return Integer.parseInt(getStat(rarity, weaponName, "min-level"));
    }

    public int getMaxLevel(String rarity, String weaponName){
        return Integer.parseInt(getStat(rarity, weaponName, "max-level"));
    }

    public double getMinDamage(String rarity, String weaponName){
        return Double.parseDouble(getStat(rarity, weaponName, "min-damage"));
    }

    public double getMaxDamage(String rarity, String weaponName){
        return Double.parseDouble(getStat(rarity, weaponName, "max-damage"));
    }

    public int getMinGem(String rarity, String weaponName){
        return Integer.parseInt(getStat(rarity, weaponName, "min-gem"));
    }

    public int getMaxGem(String rarity, String weaponName){
        return Integer.parseInt(getStat(rarity, weaponName, "max-gem"));
    }

    public String getAbility(String rarity, String weaponName){
        return getStat(rarity, weaponName, "ability-name");
    }

    public List<String> getAbilityDescription(String rarity, String weaponName){
        return getList(rarity, weaponName, "ability-description");
    }

    public String getAbilityCastType(String rarity, String weaponName){
        return getStat(rarity, weaponName, "ability-cast-type");
    }

    public String getAbilityActionType(String rarity, String weaponName){
        return getStat(rarity, weaponName, "ability-action-type");
    }

    public double getAbilityCooldown(String rarity, String weaponName){
        return Double.parseDouble(getStat(rarity, weaponName, "ability-cooldown"));
    }

}
