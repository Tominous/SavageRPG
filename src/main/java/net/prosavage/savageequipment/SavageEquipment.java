package net.prosavage.savageequipment;


import net.prosavage.savageequipment.commands.GiveItem;
import net.prosavage.savageequipment.commands.SavageEquipmentCommand;
import net.prosavage.savageequipment.listeners.DamageListener;
import net.prosavage.savageequipment.listeners.JoinListener;
import net.prosavage.savageequipment.utils.Color;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class SavageEquipment extends JavaPlugin {

    private static SavageEquipment instance;
    Color Color = new Color();
    String prefix = (String) this.getConfig().get("prefix");
    Map<String, String> YAMLValues = new HashMap();
    List<String> WeaponLores = new ArrayList<>();
    List<String> ArmorLores = new ArrayList<>();
    FileConfiguration SEConfig = this.getConfig();



    @Override
    public void onEnable() {
        saveDefaultConfig();
        this.saveResource("armor.yml", true);
        this.saveResource("weapon.yml", true);
        sendConsole("Plugin loaded.");
        getCommand("giveItem").setExecutor(new GiveItem());
        getCommand("savageequipment").setExecutor(new SavageEquipmentCommand());
        getServer().getPluginManager().registerEvents(new DamageListener(), this);
        getServer().getPluginManager().registerEvents(new JoinListener(), this);

        YAMLValues.clear();
        ArmorLores.clear();
        WeaponLores.clear();

        WeaponLores = SEConfig.getStringList("weapon.lore");
        ArmorLores = SEConfig.getStringList("armor.lore");

        YAMLValues.put("prefix", String.valueOf(SEConfig.get("prefix")));
        YAMLValues.put("damage-indicator.name", String.valueOf(SEConfig.get("damage-indicator.name")));
        YAMLValues.put("damage-indicator.client-side", String.valueOf(SEConfig.get("damage-indicator.client-side")));
        YAMLValues.put("formulas.exp", String.valueOf(SEConfig.get("formulas.exp")));
        YAMLValues.put("formulas.exp-drop", String.valueOf(SEConfig.get("formulas.exp-drop")));
        YAMLValues.put("formulas.damage", String.valueOf(SEConfig.get("formulas.damage")));
        YAMLValues.put("formulas.defense", String.valueOf(SEConfig.get("formulas.defense")));
        YAMLValues.put("gear-score.multiplier", String.valueOf(SEConfig.get("gear-score-multiplier")));
        YAMLValues.put("gear-score.multiplier.weapon.damage", String.valueOf(SEConfig.get("gear-score.multiplier.weapon.damage")));
        YAMLValues.put("gear-score.multiplier.weapon.cooldown", String.valueOf(SEConfig.get("gear-score.multiplier.weapon.cooldown")));
        YAMLValues.put("gear-score.multiplier.armor.health", String.valueOf(SEConfig.get("damage-indicator.name")));
        YAMLValues.put("gear-score.multiplier.armor.regen", String.valueOf(SEConfig.get("damage-indicator.name")));

        YAMLValues.put("weapon.lore-amount", String.valueOf(SEConfig.get("weapon.lore-amount")));
        YAMLValues.put("armor.lore-amount", String.valueOf(SEConfig.get("armor.lore-amount")));

        for (int i = 0; i < Integer.parseInt(YAMLValues.get("weapon.lore-amount")); i++) {
            YAMLValues.put("weapon.lore." + i, WeaponLores.get(i));
        }

        for (int i = 0; i < Integer.parseInt(YAMLValues.get("armor.lore-amount")); i++) {
            YAMLValues.put("armor.lore." + i, ArmorLores.get(i));
        }

        YAMLValues.put("weapon.type-clear", String.valueOf(SEConfig.get("weapon.type-clear")));
        YAMLValues.put("weapon.rarity-clear", String.valueOf(SEConfig.get("weapon.rarity-clear")));
        YAMLValues.put("weapon.class-clear", String.valueOf(SEConfig.get("weapon.class-clear")));
        YAMLValues.put("weapon.required-level-clear", String.valueOf(SEConfig.get("weapon.required-level-clear")));
        YAMLValues.put("weapon.damage-clear", String.valueOf(SEConfig.get("weapon.damage-clear")));
        YAMLValues.put("weapon.damage-split", String.valueOf(SEConfig.get("weapon.damage-split")));
        YAMLValues.put("weapon.cooldown-clear", String.valueOf(SEConfig.get("weapon.cooldown-clear")));
        YAMLValues.put("weapon.damage-per-second-clear", String.valueOf(SEConfig.get("weapon.damage-per-second-clear")));

        YAMLValues.put("armor.type-clear", String.valueOf(SEConfig.get("armor.type-clear")));
        YAMLValues.put("armor.rarity-clear", String.valueOf(SEConfig.get("armor.rarity-clear")));
        YAMLValues.put("armor.class-clear", String.valueOf(SEConfig.get("armor.class-clear")));
        YAMLValues.put("armor.required-level-clear", String.valueOf(SEConfig.get("armor.required-level-clear")));
        YAMLValues.put("armor.protection-clear", String.valueOf(SEConfig.get("armor.protection-clear")));
        YAMLValues.put("armor.health-clear", String.valueOf(SEConfig.get("armor.health-clear")));
        YAMLValues.put("armor.regen-clear", String.valueOf(SEConfig.get("armor.regen-clear")));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public SavageEquipment() {
        instance = this;
    }

    public static SavageEquipment getInstance() {
        return instance;
    }

    public void sendConsole(String s) {
        System.out.println(Color.ify(prefix + s));
    }

    public Map<String, String> getYAMLValues(){
        return YAMLValues;
    }

}