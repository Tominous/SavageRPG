package net.prosavage.savagerpg;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedParticle;
import net.prosavage.savagerpg.command.AttrbibutesCommand;
import net.prosavage.savagerpg.command.SavageRPGDebugCommand;
import net.prosavage.savagerpg.hooks.permissionsex.PermissionsEX;
import net.prosavage.savagerpg.libs.com.codingforcookies.armorequip.ArmorListener;
import net.prosavage.savagerpg.listeners.*;
import net.prosavage.savagerpg.utils.Color;
import org.bukkit.Particle;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
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
    PermissionsEX PermissionsEX = new PermissionsEX();

    @Override
    public void onEnable() {
        if (!(PermissionsEX.checkToSeeIfServerHasPermissionsEX())) {
            if (!(configFile.exists())) {
                saveDefaultConfig();
                this.saveResource("placeholders.yml",false);
            }
            registerEvent(new PlayerDamageEntityListener());
            registerEvent(new ArmorListener(getArmorListenerFile().getStringList("blocked")));
            registerEvent(new ArmorEquipEventListener());
            registerEvent(new PlayerJoinListener());
            registerEvent(new PlayerRegenListener());
            registerEvent(new EntitySpawnListener());
            registerEvent(new EntityDeathListener());
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
            Objects.requireNonNull(getCommand("attributes")).setExecutor(new AttrbibutesCommand());

            ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(SavageRPG.getInstance(),PacketType.Play.Server.WORLD_PARTICLES) {
                   @Override
                   public void onPacketSending(PacketEvent e) {
                       for (WrappedParticle i : e.getPacket().getNewParticles().getValues()) {
                           if (i.getParticle() == Particle.DAMAGE_INDICATOR) {
                               e.setCancelled(true);
                           }
                       }
                   }
               }
            );
        }else if (PermissionsEX.checkToSeeIfServerHasPermissionsEX()){
            PermissionsEX.disable();
        }

    }

    @Override
    public void onDisable() {
        if (PermissionsEX.checkToSeeIfServerHasPermissionsEX()) {
            sendConsole("Stop being outdated by using PEX, this plugin isn't built for 1.8.8");
        }
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

    public void registerEvent(Listener listener){
        getServer().getPluginManager().registerEvents(listener, this);
        String stringListener = listener.toString().replaceAll("[a-z].*\\.", "");
        stringListener = stringListener.replaceAll("[\\@].*", "");
        sendConsole("Event " + stringListener + " loaded");
    }

}
