package net.prosavage.savagerpg.utils;

import net.prosavage.savagerpg.SavageRPG;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Weapon {

    Number Number = new Number();

    public String getStat(String rarity, String weaponName, String stat_node){
        File[] dir = SavageRPG.getInstance().getRarityWeaponFiles(rarity);
        File selectedFile = null;
        assert dir != null;
        for (File file : dir){
            if(file.getName().equals(weaponName + ".yml")){
                selectedFile = file;
            }
        }
        YamlConfiguration WeaponFiles = YamlConfiguration.loadConfiguration(Objects.requireNonNull(selectedFile));
        String value = String.valueOf(WeaponFiles.get(stat_node));
        return value;
    }

    public void updateLore(String rarity){
        File[] dir = SavageRPG.getInstance().getRarityWeaponFiles(rarity);
        for (File file : Objects.requireNonNull(dir)) {
            YamlConfiguration WeaponFiles = YamlConfiguration.loadConfiguration(Objects.requireNonNull(file));

            List<String> listLore = new ArrayList<>();
            listLore.add(String.valueOf(WeaponFiles.get("lore")));
            List<String> lore = splitLore(String.valueOf(listLore));
            WeaponFiles.set("lore", lore);
//            SavageRPG.getInstance().sendConsole("&e" + listLore + " " + "&a" + lore);
            try {
                WeaponFiles.save(file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateCooldown(String rarity){
        File[] dir = SavageRPG.getInstance().getRarityWeaponFiles(rarity);
        for (File file : Objects.requireNonNull(dir)) {
            YamlConfiguration WeaponFiles = YamlConfiguration.loadConfiguration(Objects.requireNonNull(file));
            if (WeaponFiles.get("max-cooldown") != null) {
                double maxCooldown = (double) WeaponFiles.get("max-cooldown");
                WeaponFiles.set("min-cooldown", Number.getDouble(1.0, maxCooldown));
            }
            try {
                WeaponFiles.save(file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    public List<String> splitLore(String string) {
        int maxLength = (int) ((string.length() / 4.) + 1);
        Pattern p = Pattern.compile("\\G\\s*(.{1," + maxLength + "})(?=\\s|$)", Pattern.DOTALL);
        Matcher m = p.matcher(string);
        List<String> list = new ArrayList<String>();

        while (m.find())
            list.add(m.group(1));
        return list;
    }

}
