package net.prosavage.savagerpg.utils.files;

import net.prosavage.savagerpg.SavageRPG;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SArmorFiles {

    public File[] getRarityFolders() {
        File dir = new File(SavageRPG.getInstance().getArmorFolder());
        File[] files = dir.listFiles();
        return files;
    }

    public YamlConfiguration getArmorConfig(){
        YamlConfiguration armorConfig = new YamlConfiguration();
        try {
            armorConfig.load(SavageRPG.getInstance().getRarityFile());
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        return armorConfig;

    }

    public List<String> getRarityNames(){
        File dir = new File(SavageRPG.getInstance().getArmorFolder());
        return Arrays.asList(Objects.requireNonNull(dir.list()));
    }

    public File[] getRarityArmorFiles(String string) {
        File[] dir = getRarityFolders();
        for (File file : dir) {
            if (file.getName().equals(string)) {
                return file.listFiles();
            }
        }
        return null;
    }

    public List<String> getRarityArmorNames(String string) {
        File[] dir = getRarityFolders();
        for (File file : dir) {
            if (file.getName().equals(string)) {
                return Arrays.asList(Objects.requireNonNull(file.list()));
            }
        }
        return null;
    }

    public int getArmorAmount() {
        File[] dir = getRarityFolders();
        int amount = 0;
        for (File file : dir) {
            amount = amount + Objects.requireNonNull(file.listFiles()).length;
        }
        return amount;
    }

    public String getStat(String rarity, String weaponName, String stat_node){
        File[] dir = getRarityArmorFiles(rarity);
        File selectedFile = null;
        assert dir != null;
        for (File file : dir){
            if(file.getName().equals(weaponName + ".yml")){
                selectedFile = file;
            }
        }
        YamlConfiguration WeaponFiles = YamlConfiguration.loadConfiguration(Objects.requireNonNull(selectedFile));
        return String.valueOf(WeaponFiles.get(stat_node));
    }

    public String[] getLore(String rarity, String weaponName){
        File[] dir = getRarityArmorFiles(rarity);
        File selectedFile = null;
        assert dir != null;
        for (File file : dir){
            if(file.getName().equals(weaponName + ".yml")){
                selectedFile = file;
            }
        }
        YamlConfiguration WeaponFiles = YamlConfiguration.loadConfiguration(Objects.requireNonNull(selectedFile));
        return (String[]) WeaponFiles.get("lore");
    }

}
