package net.prosavage.illyriarpg.api.files;

import net.prosavage.illyriarpg.IllyriaRPG;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class IAbilityFiles {

    public List<String> getAbilities(){
        File dir = new File(IllyriaRPG.getInstance().getAbilityFolder());
        if (dir.list() == null){
            return null;
        }
        return Arrays.asList(Objects.requireNonNull(dir.list()));
    }

    public int getAbilitiesAmount(){
        return getAbilities().size();
    }

    public String getAbilityName(String abilityName){
        boolean isValid = false;
        for (String s : getAbilities()){
            if (abilityName.equals(s.replace(".yml", ""))){
             isValid = true;
            }
        }
        if (isValid) {
            return abilityName;
        }
        return null;
    }

    private String getStat(String abilityName, String stat_node){
        File file = new File(IllyriaRPG.getInstance().getDataFolder() + "\\abilities", abilityName + ".yml");
        YamlConfiguration abilityFiles = YamlConfiguration.loadConfiguration(Objects.requireNonNull(file));
        if (abilityFiles.get(stat_node) != null){
            return String.valueOf(abilityFiles.get(stat_node));
        }
        return null;
    }

    private List<String> getList(String abilityName, String stat_node){
        File file = new File(IllyriaRPG.getInstance().getDataFolder() + "\\abilities",abilityName + ".yml");
        YamlConfiguration abilityFiles = YamlConfiguration.loadConfiguration(Objects.requireNonNull(file));
        if (abilityFiles.get(stat_node) != null){
            return abilityFiles.getStringList(stat_node);
        }
        return null;
    }

    public double getAbilityManaCost(String abilityName){
        return Double.parseDouble(Objects.requireNonNull(getStat(abilityName, "ability-mana-cost")));
    }

    public double getAbilityCooldown(String abilityName){
        return Double.parseDouble(Objects.requireNonNull(getStat(abilityName, "ability-cooldown")));
    }

    public List<String> getAbilityDescription(String abilityName){
        return getList(abilityName, "ability-description");
    }

    public boolean isLeftClickAction(String abilityName){
        return Boolean.parseBoolean(Objects.requireNonNull(getStat(abilityName, "ability-left-click-action")));
    }

    public boolean isRightClickAction(String abilityName){
        return Boolean.parseBoolean(Objects.requireNonNull(getStat(abilityName, "ability-right-click-action")));
    }

    public boolean isDroppableAction(String abilityName){
        return Boolean.parseBoolean(Objects.requireNonNull(getStat(abilityName, "ability-drop-action")));
    }

    public void removeAllFiles(){
        for (String s : getAbilities()){
            File file = new File(IllyriaRPG.getInstance().getDataFolder() + "\\abilities", s);
            file.delete();
        }
    }

}
