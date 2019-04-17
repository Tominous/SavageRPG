package net.prosavage.savageequipment.data;

import net.prosavage.savageequipment.utils.Formula;
import net.prosavage.savageequipment.utils.Placeholder;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class YAML {

    net.prosavage.savageequipment.utils.Formula Formula = new Formula();
    net.prosavage.savageequipment.utils.Placeholder Placeholder = new Placeholder();

    public void createFile(Player player){
        File playerFile = new File("plugins\\SavageEquipment\\playerdata\\", player.getUniqueId().toString() + ".yml");
        YamlConfiguration playerFileLoad = YamlConfiguration.loadConfiguration(playerFile);

        playerFileLoad.set("username", player.getName());
        playerFileLoad.set("level", 1);
        playerFileLoad.set("exp", 0.00);
        playerFileLoad.set("max-exp", 9999999999.99);
        playerFileLoad.set("skill-points", 0);
        try {
            playerFileLoad.save(playerFile);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean fileExist(Player player){
        File playerFile = new File("plugins\\SavageEquipment\\playerdata\\", player.getUniqueId().toString() + ".yml");
        if (!playerFile.exists()){
            return false;
        }
        return true;
    }

    public String getData(Player player, String string){
        File playerFile = new File("plugins\\SavageEquipment\\playerdata\\", player.getUniqueId().toString() + ".yml");
        YamlConfiguration playerFileLoad = YamlConfiguration.loadConfiguration(playerFile);
        return String.valueOf(playerFileLoad.get(string));
    }

    public void setData(Player player, String path, String value){
        File playerFile = new File("plugins\\SavageEquipment\\playerdata\\", player.getUniqueId().toString() + ".yml");
        YamlConfiguration playerFileLoad = YamlConfiguration.loadConfiguration(playerFile);
        playerFileLoad.set(path, value);
        try {
            playerFileLoad.save(playerFile);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setMaxEXP(Player player, Double value){
        File playerFile = new File("plugins\\SavageEquipment\\playerdata\\", player.getUniqueId().toString() + ".yml");
        YamlConfiguration playerFileLoad = YamlConfiguration.loadConfiguration(playerFile);
        playerFileLoad.set("max-exp", value);
        try {
            playerFileLoad.save(playerFile);
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public void setEXP(Player player, Double value){
        File playerFile = new File("plugins\\SavageEquipment\\playerdata\\", player.getUniqueId().toString() + ".yml");
        YamlConfiguration playerFileLoad = YamlConfiguration.loadConfiguration(playerFile);
        playerFileLoad.set("exp", value);
        try {
            playerFileLoad.save(playerFile);
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public void setLevel(Player player, Integer value){
        File playerFile = new File("plugins\\SavageEquipment\\playerdata\\", player.getUniqueId().toString() + ".yml");
        YamlConfiguration playerFileLoad = YamlConfiguration.loadConfiguration(playerFile);
        playerFileLoad.set("level", value);
        try {
            playerFileLoad.save(playerFile);
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public void setSkillPoints(Player player, Integer value){
        File playerFile = new File("plugins\\SavageEquipment\\playerdata\\", player.getUniqueId().toString() + ".yml");
        YamlConfiguration playerFileLoad = YamlConfiguration.loadConfiguration(playerFile);
        playerFileLoad.set("skill-points", value);
        try {
            playerFileLoad.save(playerFile);
        } catch (IOException e){
            e.printStackTrace();
        }

    }

}
