package net.prosavage.savageequipment;

import net.prosavage.savageequipment.command.GiveItem;
import net.prosavage.savageequipment.command.romanToIntAndIntToRoman;
import net.prosavage.savageequipment.enchant.CESword;
import net.prosavage.savageequipment.listener.DamageListener;
import net.prosavage.savageequipment.listener.ArmorChange;
import net.prosavage.savageequipment.listener.ClickToEnchant;
import net.prosavage.savageequipment.loop.JoinRegenLoop;
import org.bukkit.plugin.java.JavaPlugin;
import net.prosavage.savageequipment.somewhatusefulstuff.Color;

public final class Main extends JavaPlugin {

    private static Main instance;
    Color Color = new Color();

    @Override
    public void onEnable() {
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

    public static Main getInstance(){
        return instance;
    }

}
