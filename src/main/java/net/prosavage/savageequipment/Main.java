package me.kingalteriv.pragmata;

import me.kingalteriv.pragmata.Command.GiveItem;
import me.kingalteriv.pragmata.Command.romanToIntAndIntToRoman;
import me.kingalteriv.pragmata.Enchant.CESword;
import me.kingalteriv.pragmata.Event.DamageListener;
import me.kingalteriv.pragmata.Listener.ArmorChange;
import me.kingalteriv.pragmata.Listener.ClickToEnchant;
import me.kingalteriv.pragmata.Loop.JoinRegenLoop;
import org.bukkit.plugin.java.JavaPlugin;
import me.kingalteriv.pragmata.SomewhatUsefulStuff.Color;

public final class Main extends JavaPlugin {

    private static Main instance;
    Color Color = new Color();

    @Override
    public void onEnable() {
        getLogger().info(Color.ify("&aPragmata have been loaded/enabled."));
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
        getLogger().info(Color.ify("&cPragmata have unloaded/disabled."));
    }

    public Main(){
        instance = this;
    }

    public static Main getInstance(){
        return instance;
    }

}
