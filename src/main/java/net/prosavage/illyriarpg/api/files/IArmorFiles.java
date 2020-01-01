package net.prosavage.illyriarpg.api.files;

import net.prosavage.illyriarpg.IllyriaRPG;
import net.prosavage.illyriarpg.utils.Color;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class IArmorFiles {

    Color Color = new Color();

    private File[] getRarityFolders() {
        File dir = new File(IllyriaRPG.getInstance().getArmorFolder());
        return dir.listFiles();
    }

    public List<String> getRarityNames(){
        File dir = new File(IllyriaRPG.getInstance().getArmorFolder());
        if (dir.list() == null){
            return null;
        }
        return Arrays.asList(Objects.requireNonNull(dir.list()));
    }

    private File[] getRarityArmorFiles(String string) {
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

    private String getStat(String rarity, String ArmorName, String stat_node){
        File[] dir = getRarityArmorFiles(rarity);
        File selectedFile = null;
        assert dir != null;
        for (File file : dir){
            if(file.getName().equals(ArmorName + ".yml")){
                selectedFile = file;
            }
        }
        YamlConfiguration ArmorFiles = YamlConfiguration.loadConfiguration(Objects.requireNonNull(selectedFile));
        if (ArmorFiles.get(stat_node) != null){
            return String.valueOf(ArmorFiles.get(stat_node));
        }
        return null;
    }

    private List<String> getArmorBackgroundLore(String rarity, String ArmorName){
        File[] dir = getRarityArmorFiles(rarity);
        File selectedFile = null;
        assert dir != null;
        for (File file : dir){
            if(file.getName().equals(ArmorName + ".yml")){
                selectedFile = file;
            }
        }
        YamlConfiguration ArmorFiles = YamlConfiguration.loadConfiguration(Objects.requireNonNull(selectedFile));
        return ArmorFiles.getStringList("background-lore");
    }

    private YamlConfiguration getArmorYAMLConfiguration(String rarity, String ArmorName){
        File[] dir = getRarityArmorFiles(rarity);
        File selectedFile = null;
        assert dir != null;
        for (File file : dir){
            if(file.getName().equals(ArmorName + ".yml")){
                selectedFile = file;
            }
        }
        return YamlConfiguration.loadConfiguration(Objects.requireNonNull(selectedFile));
    }

    private File getArmorYAMLFileString(String rarity, String ArmorName){
        File[] dir = getRarityArmorFiles(rarity);
        File selectedFile = null;
        assert dir != null;
        for (File file : dir){
            if(file.getName().equals(ArmorName + ".yml")){
                selectedFile = file;
            }
        }
        return selectedFile;
    }


    private List<String> getList(String rarity, String ArmorName, String node){
        File[] dir = getRarityArmorFiles(rarity);
        File selectedFile = null;
        assert dir != null;
        for (File file : dir){
            if(file.getName().equals(ArmorName + ".yml")){
                selectedFile = file;
            }
        }
        YamlConfiguration ArmorFiles = YamlConfiguration.loadConfiguration(Objects.requireNonNull(selectedFile));
        return ArmorFiles.getStringList(node);
    }

    public String getName(String rarity, String ArmorName){
        return getStat(rarity, ArmorName, "item-name");
    }

    public String getMaterial(String rarity, String ArmorName){
        return getStat(rarity, ArmorName, "materials");
    }

    public String getRarity(String rarity, String ArmorName){
        return rarity;
    }

    public double getCooldown(String rarity, String ArmorName){
        return Double.parseDouble(Objects.requireNonNull(getStat(rarity, ArmorName, "attack-cooldown")));
    }

    public int getMinimumLevel(String rarity, String ArmorName){
        return Integer.parseInt(Objects.requireNonNull(getStat(rarity, ArmorName, "minimum-level")));
    }

    public double getMinimumProtection(String rarity, String ArmorName){
        return Double.parseDouble(Objects.requireNonNull(getStat(rarity, ArmorName, "minimum-protection")));
    }

    public double getMaximumProtection(String rarity, String ArmorName){
        return Double.parseDouble(Objects.requireNonNull(getStat(rarity, ArmorName, "maximum-protection")));
    }

    public double getMinimumHealth(String rarity, String ArmorName){
        return Double.parseDouble(Objects.requireNonNull(getStat(rarity, ArmorName, "minimum-health")));
    }

    public double getMaximumHealth(String rarity, String ArmorName){
        return Double.parseDouble(Objects.requireNonNull(getStat(rarity, ArmorName, "maximum-health")));
    }

    public double getMinimumRegen(String rarity, String ArmorName){
        return Double.parseDouble(Objects.requireNonNull(getStat(rarity, ArmorName, "minimum-regen")));
    }

    public double getMaximumRegen(String rarity, String ArmorName){
        return Double.parseDouble(Objects.requireNonNull(getStat(rarity, ArmorName, "maximum-regen")));
    }

    public int getMinimumGem(String rarity, String ArmorName){
        return Integer.parseInt(Objects.requireNonNull(getStat(rarity, ArmorName, "minimum-gem")));
    }

    public int getMaximumGem(String rarity, String ArmorName){
        return Integer.parseInt(Objects.requireNonNull(getStat(rarity, ArmorName, "maximum-gem")));
    }

    public int getMinimumScroll(String rarity, String ArmorName){
        return Integer.parseInt(Objects.requireNonNull(getStat(rarity, ArmorName, "minimum-scroll")));
    }

    public int getMaximumScroll(String rarity, String ArmorName){
        return Integer.parseInt(Objects.requireNonNull(getStat(rarity, ArmorName, "maximum-scroll")));
    }

    public String getAbility(String rarity, String ArmorName){
        return getStat(rarity, ArmorName, "ability-name");
    }

    public void setAttackCooldown(String rarity, String ArmorName, double value){
        YamlConfiguration ArmorYAML = getArmorYAMLConfiguration(rarity, ArmorName);
        ArmorYAML.set("attack-cooldown", value);
        try {
            ArmorYAML.save(getArmorYAMLFileString(rarity, ArmorName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMinimumLevel(String rarity, String ArmorName, int value){
        YamlConfiguration ArmorYAML = getArmorYAMLConfiguration(rarity, ArmorName);
        ArmorYAML.set("minimum-level", value);
        try {
            ArmorYAML.save(getArmorYAMLFileString(rarity, ArmorName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMinimumProtection(String rarity, String ArmorName, double value){
        YamlConfiguration ArmorYAML = getArmorYAMLConfiguration(rarity, ArmorName);
        ArmorYAML.set("minimum-protection", value);
        try {
            ArmorYAML.save(getArmorYAMLFileString(rarity, ArmorName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMaximumProtection(String rarity, String ArmorName, double value){
        YamlConfiguration ArmorYAML = getArmorYAMLConfiguration(rarity, ArmorName);
        ArmorYAML.set("maximum-protection", value);
        try {
            ArmorYAML.save(getArmorYAMLFileString(rarity, ArmorName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMinimumHealth(String rarity, String ArmorName, double value){
        YamlConfiguration ArmorYAML = getArmorYAMLConfiguration(rarity, ArmorName);
        ArmorYAML.set("minimum-health", value);
        try {
            ArmorYAML.save(getArmorYAMLFileString(rarity, ArmorName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMaximumHealth(String rarity, String ArmorName, double value){
        YamlConfiguration ArmorYAML = getArmorYAMLConfiguration(rarity, ArmorName);
        ArmorYAML.set("maximum-health", value);
        try {
            ArmorYAML.save(getArmorYAMLFileString(rarity, ArmorName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMinimumRegen(String rarity, String ArmorName, double value){
        YamlConfiguration ArmorYAML = getArmorYAMLConfiguration(rarity, ArmorName);
        ArmorYAML.set("minimum-regen", value);
        try {
            ArmorYAML.save(getArmorYAMLFileString(rarity, ArmorName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMaximumRegen(String rarity, String ArmorName, double value){
        YamlConfiguration ArmorYAML = getArmorYAMLConfiguration(rarity, ArmorName);
        ArmorYAML.set("maximum-regen", value);
        try {
            ArmorYAML.save(getArmorYAMLFileString(rarity, ArmorName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMinimumGem(String rarity, String ArmorName, int value){
        YamlConfiguration ArmorYAML = getArmorYAMLConfiguration(rarity, ArmorName);
        ArmorYAML.set("minimum-gem", value);
        try {
            ArmorYAML.save(getArmorYAMLFileString(rarity, ArmorName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMaximumGem(String rarity, String ArmorName, int value){
        YamlConfiguration ArmorYAML = getArmorYAMLConfiguration(rarity, ArmorName);
        ArmorYAML.set("maximum-gem", value);
        try {
            ArmorYAML.save(getArmorYAMLFileString(rarity, ArmorName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMinimumScroll(String rarity, String ArmorName, int value){
        YamlConfiguration ArmorYAML = getArmorYAMLConfiguration(rarity, ArmorName);
        ArmorYAML.set("minimum-scroll", value);
        try {
            ArmorYAML.save(getArmorYAMLFileString(rarity, ArmorName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMaximumScroll(String rarity, String ArmorName, int value) {
        YamlConfiguration ArmorYAML = getArmorYAMLConfiguration(rarity, ArmorName);
        ArmorYAML.set("maximum-scroll", value);
        try {
            ArmorYAML.save(getArmorYAMLFileString(rarity, ArmorName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
