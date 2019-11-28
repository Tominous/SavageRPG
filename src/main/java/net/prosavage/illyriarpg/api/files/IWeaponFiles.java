package net.prosavage.illyriarpg.api.files;

import net.prosavage.illyriarpg.IllyriaRPG;
import net.prosavage.illyriarpg.utils.Color;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class IWeaponFiles {

    Color Color = new Color();

    private File[] getRarityFolders() {
        File dir = new File(IllyriaRPG.getInstance().getWeaponFolder());
        return dir.listFiles();
    }

    public YamlConfiguration getWeaponConfig(){
        YamlConfiguration weaponConfig = new YamlConfiguration();
        try {
            weaponConfig.load(IllyriaRPG.getInstance().getRarityFile());
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        return weaponConfig;

    }

    public List<String> getRarityNames(){
        File dir = new File(IllyriaRPG.getInstance().getWeaponFolder());
        if (dir.list() == null){
            return Collections.singletonList("null");
        }
        return Arrays.asList(Objects.requireNonNull(dir.list()));
    }

    private File[] getRarityWeaponFiles(String string) {
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

    private String getStat(String rarity, String weaponName, String stat_node){
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

    private List<String> getFileLore(String rarity, String weaponName){
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

    private YamlConfiguration getWeaponYAMLConfiguration(String rarity, String weaponName){
        File[] dir = getRarityWeaponFiles(rarity);
        File selectedFile = null;
        assert dir != null;
        for (File file : dir){
            if(file.getName().equals(weaponName + ".yml")){
                selectedFile = file;
            }
        }
        return YamlConfiguration.loadConfiguration(Objects.requireNonNull(selectedFile));
    }

    private File getWeaponYAMLFileString(String rarity, String weaponName){
        File[] dir = getRarityWeaponFiles(rarity);
        File selectedFile = null;
        assert dir != null;
        for (File file : dir){
            if(file.getName().equals(weaponName + ".yml")){
                selectedFile = file;
            }
        }
        return selectedFile;
    }


    private List<String> getList(String rarity, String weaponName, String node){
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
        return rarity;
    }

    public double getCooldown(String rarity, String weaponName){
        return Double.parseDouble(Objects.requireNonNull(getStat(rarity, weaponName, "attack-cooldown")));
    }

    public List<String> getLore(String rarity, String weaponName){
        return getFileLore(rarity,weaponName);
    }

    public int getMinimumLevel(String rarity, String weaponName){
        return Integer.parseInt(Objects.requireNonNull(getStat(rarity, weaponName, "minimum-level")));
    }

    public int getMaximumLevel(String rarity, String weaponName){
        return Integer.parseInt(Objects.requireNonNull(getStat(rarity, weaponName, "maximum-level")));
    }

    public double getMinimumDamage(String rarity, String weaponName){
        return Double.parseDouble(Objects.requireNonNull(getStat(rarity, weaponName, "minimum-damage")));
    }

    public double getMaximumDamage(String rarity, String weaponName){
        return Double.parseDouble(Objects.requireNonNull(getStat(rarity, weaponName, "maximum-damage")));
    }

    public int getMinimumGem(String rarity, String weaponName){
        return Integer.parseInt(Objects.requireNonNull(getStat(rarity, weaponName, "minimum-gem")));
    }

    public int getMaximumGem(String rarity, String weaponName){
        return Integer.parseInt(Objects.requireNonNull(getStat(rarity, weaponName, "maximum-gem")));
    }

    public int getMinimumScroll(String rarity, String weaponName){
        return Integer.parseInt(Objects.requireNonNull(getStat(rarity, weaponName, "minimum-scroll")));
    }

    public int getMaximumScroll(String rarity, String weaponName){
        return Integer.parseInt(Objects.requireNonNull(getStat(rarity, weaponName, "maximum-scroll")));
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
        return Double.parseDouble(Objects.requireNonNull(getStat(rarity, weaponName, "ability-cooldown")));
    }

    public double getAbilityManaCost(String rarity, String weaponName){
        return Double.parseDouble(Objects.requireNonNull(getStat(rarity, weaponName, "ability-mana-cost")));
    }

    public void setAttackCooldown(String rarity, String weaponName, double value){
        YamlConfiguration weaponYAML = getWeaponYAMLConfiguration(rarity, weaponName);
        weaponYAML.set("attack-cooldown", value);
        try {
            weaponYAML.save(getWeaponYAMLFileString(rarity, weaponName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMinimumLevel(String rarity, String weaponName, int value){
        YamlConfiguration weaponYAML = getWeaponYAMLConfiguration(rarity, weaponName);
        weaponYAML.set("minimum-level", value);
        try {
            weaponYAML.save(getWeaponYAMLFileString(rarity, weaponName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMaximumLevel(String rarity, String weaponName, int value){
        YamlConfiguration weaponYAML = getWeaponYAMLConfiguration(rarity, weaponName);
        weaponYAML.set("maximum-level", value);
        try {
            weaponYAML.save(getWeaponYAMLFileString(rarity, weaponName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMinimumDamage(String rarity, String weaponName, double value){
        YamlConfiguration weaponYAML = getWeaponYAMLConfiguration(rarity, weaponName);
        weaponYAML.set("minimum-damage", value);
        try {
            weaponYAML.save(getWeaponYAMLFileString(rarity, weaponName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMaximumDamage(String rarity, String weaponName, double value){
        YamlConfiguration weaponYAML = getWeaponYAMLConfiguration(rarity, weaponName);
        weaponYAML.set("maximum-damage", value);
        try {
            weaponYAML.save(getWeaponYAMLFileString(rarity, weaponName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMinimumGem(String rarity, String weaponName, int value){
        YamlConfiguration weaponYAML = getWeaponYAMLConfiguration(rarity, weaponName);
        weaponYAML.set("minimum-gem", value);
        try {
            weaponYAML.save(getWeaponYAMLFileString(rarity, weaponName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMaximumGem(String rarity, String weaponName, int value){
        YamlConfiguration weaponYAML = getWeaponYAMLConfiguration(rarity, weaponName);
        weaponYAML.set("maximum-gem", value);
        try {
            weaponYAML.save(getWeaponYAMLFileString(rarity, weaponName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMinimumScroll(String rarity, String weaponName, int value){
        YamlConfiguration weaponYAML = getWeaponYAMLConfiguration(rarity, weaponName);
        weaponYAML.set("minimum-scroll", value);
        try {
            weaponYAML.save(getWeaponYAMLFileString(rarity, weaponName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMaximumScroll(String rarity, String weaponName, int value) {
        YamlConfiguration weaponYAML = getWeaponYAMLConfiguration(rarity, weaponName);
        weaponYAML.set("maximum-scroll", value);
        try {
            weaponYAML.save(getWeaponYAMLFileString(rarity, weaponName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAbility(String rarity, String weaponName, String value){
        YamlConfiguration weaponYAML = getWeaponYAMLConfiguration(rarity, weaponName);
        weaponYAML.set("ability-name", value);
        try {
            weaponYAML.save(getWeaponYAMLFileString(rarity, weaponName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAbilityDescription(String rarity, String weaponName, List<String> value){
        YamlConfiguration weaponYAML = getWeaponYAMLConfiguration(rarity, weaponName);
        weaponYAML.set("ability-description", value);
        try {
            weaponYAML.save(getWeaponYAMLFileString(rarity, weaponName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAbilityCastType(String rarity, String weaponName, String value){
        YamlConfiguration weaponYAML = getWeaponYAMLConfiguration(rarity, weaponName);
        weaponYAML.set("ability-cast-type", value);
        try {
            weaponYAML.save(getWeaponYAMLFileString(rarity, weaponName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAbilityActionType(String rarity, String weaponName, String value){
        YamlConfiguration weaponYAML = getWeaponYAMLConfiguration(rarity, weaponName);
        weaponYAML.set("ability-action-type", value);
        try {
            weaponYAML.save(getWeaponYAMLFileString(rarity, weaponName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAbilityCooldown(String rarity, String weaponName, String value){
        YamlConfiguration weaponYAML = getWeaponYAMLConfiguration(rarity, weaponName);
        weaponYAML.set("ability-cooldown", value);
        try {
            weaponYAML.save(getWeaponYAMLFileString(rarity, weaponName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setAbilityManaCost(String rarity, String weaponName, String value){
        YamlConfiguration weaponYAML = getWeaponYAMLConfiguration(rarity, weaponName);
        weaponYAML.set("ability-mana-cost", value);
        try {
            weaponYAML.save(getWeaponYAMLFileString(rarity, weaponName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
