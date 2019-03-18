package net.prosavage.savageequipment;


import net.prosavage.savageequipment.commands.GiveItem;
import net.prosavage.savageequipment.commands.SavageEquipmentCommand;
import net.prosavage.savageequipment.listeners.DamageListener;
import net.prosavage.savageequipment.listeners.JoinListener;
import net.prosavage.savageequipment.utils.Color;
import net.prosavage.savageequipment.utils.Formula;
import net.prosavage.savageequipment.utils.Placeholder;
import org.bukkit.plugin.java.JavaPlugin;

public final class SavageEquipment extends JavaPlugin {

    private static SavageEquipment instance;
    Color Color = new Color();
    String prefix = (String) this.getConfig().get("prefix");

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

}