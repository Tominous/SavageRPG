package net.prosavage.savagerpg;

import net.prosavage.savagerpg.command.SavageRPGDebugCommand;
import net.prosavage.savagerpg.debug.DebugScoreboardManager;
import net.prosavage.savagerpg.listener.MerchantDeathListener;
import net.prosavage.savagerpg.utils.Color;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public final class SavageRPG extends JavaPlugin {

    private static SavageRPG instance;
    Color Color = new Color();

    @Override
    public void onEnable() {
        sendConsole("&c\n " +
                "&c\n " +
                "&c\n " +
                "&c    ██████  ▄▄▄    ██▒   █▓ ▄▄▄        ▄████ ▓█████  ██▀███   ██▓███    ▄████ \n" +
                "&c  ▒██    ▒ ▒████▄ ▓██░   █▒▒████▄     ██▒ ▀█▒▓█   ▀ ▓██ ▒ ██▒▓██░  ██▒ ██▒ ▀█▒\n" +
                "&c  ░ ▓██▄   ▒██  ▀█▄▓██  █▒░▒██  ▀█▄  ▒██░▄▄▄░▒███   ▓██ ░▄█ ▒▓██░ ██▓▒▒██░▄▄▄░\n" +
                "&c    ▒   ██▒░██▄▄▄▄██▒██ █░░░██▄▄▄▄██ ░▓█  ██▓▒▓█  ▄ ▒██▀▀█▄  ▒██▄█▓▒ ▒░▓█  ██▓\n" +
                "&c  ▒██████▒▒ ▓█   ▓██▒▒▀█░   ▓█   ▓██▒░▒▓███▀▒░▒████▒░██▓ ▒██▒▒██▒ ░  ░░▒▓███▀▒\n" +
                "&c  ▒ ▒▓▒ ▒ ░ ▒▒   ▓▒█░░ ▐░   ▒▒   ▓▒█░ ░▒   ▒ ░░ ▒░ ░░ ▒▓ ░▒▓░▒▓▒░ ░  ░ ░▒   ▒ \n" +
                "&c  ░ ░▒  ░ ░  ▒   ▒▒ ░░ ░░    ▒   ▒▒ ░  ░   ░  ░ ░  ░  ░▒ ░ ▒░░▒ ░       ░   ░ \n" +
                "&c  ░  ░  ░    ░   ▒     ░░    ░   ▒   ░ ░   ░    ░     ░░   ░ ░░       ░ ░   ░ \n" +
                "&c        ░        ░  ░   ░        ░  ░      ░    ░  ░   ░                    ░ \n" +
                "&c                       ░                                                      \n" +
                "&c\n " +
                "&c\n "
        );
        Objects.requireNonNull(getCommand("savagerpgdebug")).setExecutor(new SavageRPGDebugCommand());
        getServer().getPluginManager().registerEvents(new MerchantDeathListener(), this);
    }

    @Override
    public void onDisable() {
        sendConsole("&ahi, this is restarting");
    }

    public SavageRPG() {
        instance = this;
    }

    public static SavageRPG getInstance(){
        return instance;
    }

    public void sendConsole(String s) {
        getLogger().info(Color.ify(s));
    }

}
