package net.prosavage.savagerpg;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedParticle;
import net.prosavage.savagerpg.command.SavageRPGDebugCommand;
import net.prosavage.savagerpg.libs.com.codingforcookies.armorequip.ArmorListener;
import net.prosavage.savagerpg.libs.com.codingforcookies.armorequip.DispenserArmorListener;
import net.prosavage.savagerpg.listeners.*;
import net.prosavage.savagerpg.utils.Color;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public final class SavageRPG extends JavaPlugin {

    private static SavageRPG instance;
    Color Color = new Color();
    String weaponFolder = getDataFolder() + "\\weapons";
    String armorFolder = getDataFolder() + "\\armors";
    File configFile = new File(getDataFolder(), "config.yml");
    File rarityFile = new File(getDataFolder(), "rarity.yml");
    File placeholderFile = new File(getDataFolder(),"placeholders.yml");
    File armorListenerFile = new File(getDataFolder(), "armor-listener-config.yml");

    @Override
    public void onEnable() {
        if (!(configFile.exists())){
            saveDefaultConfig();
            this.saveResource("placeholders.yml", false);
        }
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
        getServer().getPluginManager().registerEvents(new MerchantDeathListener(),this);
        getServer().getPluginManager().registerEvents(new PlayerDamageEntityListener(),this);
        getServer().getPluginManager().registerEvents(new EntitySpawnListener(),this);
        getServer().getPluginManager().registerEvents(new HealthUpdateListener(),this);
        getServer().getPluginManager().registerEvents(new EntityDeathListener(), this);
        getServer().getPluginManager().registerEvents(new ArmorListener(getArmorListenerFile().getStringList("blocked")), this);
        getServer().getPluginManager().registerEvents(new ArmorEquipListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinEventListener(), this);
        getServer().getPluginManager().registerEvents(new EntityDamagePlayerListener(), this);
        getServer().getPluginManager().registerEvents(new RegenListener(), this);
        getServer().getPluginManager().registerEvents(new GearScoreListener(), this);

        try
        {
            Class.forName("org.bukkit.event.block.BlockDispenseArmorEvent");
            getServer().getPluginManager().registerEvents(new DispenserArmorListener(), this);
        }
        catch (Exception localException) {}

        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(SavageRPG.getInstance(), PacketType.Play.Server.WORLD_PARTICLES) {
                    @Override
                    public void onPacketSending(PacketEvent e) {
                        for (WrappedParticle i : e.getPacket().getNewParticles().getValues()) {
                            if (i.getParticle().toString().equals("DAMAGE_INDICATOR")) {
                                e.setCancelled(true);
                            }
                        }
                    }
                }
        );
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

    public String getWeaponFolder(){
        return weaponFolder;
    }

    public File getRarityFile() {
        return rarityFile;
    }

    public String getArmorFolder() {
        return armorFolder;
    }

    public YamlConfiguration getArmorListenerFile() {
        YamlConfiguration yamlConfiguration = new YamlConfiguration();
        try {
            yamlConfiguration.load(armorListenerFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
        return yamlConfiguration;
    }

    public YamlConfiguration getPlaceholderFile() {
        YamlConfiguration yamlConfiguration = new YamlConfiguration();
        try {
            yamlConfiguration.load(placeholderFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
        return yamlConfiguration;
    }

}
