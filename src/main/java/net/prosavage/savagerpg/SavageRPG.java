package net.prosavage.savagerpg;

import net.prosavage.savagerpg.command.getItemCommand;
import net.prosavage.savagerpg.itembuilder.Item;
import net.prosavage.savagerpg.listener.JoinEventListener;
import net.prosavage.savagerpg.utils.Color;
import net.prosavage.savagerpg.utils.Weapon;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class SavageRPG extends JavaPlugin {

    private static SavageRPG instance;
    String weaponFolder = getDataFolder() + "\\weapons";
    File configFile = new File(getDataFolder() + "\\config.yml");
    File rarityFile = new File(getDataFolder() + "\\rarity.yml");
    net.prosavage.savagerpg.utils.Weapon Weapon = new Weapon();
    String prefix = (String) this.getConfig().get("prefix");
    Color Color = new Color();

    @Override
    public void onEnable() {
        if (!(configFile.exists())){
            saveDefaultConfig();
        }
        getServer().getPluginManager().registerEvents(new JoinEventListener(), this);
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
                "&cWeapons amount: " + getWeaponAmount() +
                "&c\n "
        );
        Objects.requireNonNull(getCommand("getItem")).setExecutor(new getItemCommand());



// DEBUGGING STUFF
/*/
        sendConsole("&e" + Weapon.getStat("artifact", "axe", "item-name"));
        sendConsole("&e" + Weapon.getStat("artifact", "axe", "materials"));
        sendConsole("&e" + Weapon.getStat("artifact", "axe", "rarity"));
        sendConsole("&e" + Weapon.getStat("artifact", "axe", "chance"));
        List<String> lore = Weapon.splitLore(Weapon.getStat("artifact", "axe", "lore"));
        for (String e : lore){
            sendConsole("&e" + e);
        }
        sendConsole("&e" + Weapon.getStat("artifact", "axe", "level"));
        sendConsole("&e" + Weapon.getStat("artifact", "axe", "min-damage"));
        sendConsole("&e" + Weapon.getStat("artifact", "axe", "max-damage"));
        sendConsole("&e" + Weapon.getStat("artifact", "axe", "gem-amount"));
        sendConsole("&e" + Weapon.getStat("artifact", "axe", "cooldown"));
/*/
    }

    @Override
    public void onDisable() {
        sendConsole("&ahi, this is restarting");
    }

    public SavageRPG() {
        instance = this;
    }

    public static SavageRPG getInstance() {
        return instance;
    }

    public void sendConsole(String s) {
        getLogger().info(Color.ify(s));
    }

    public File[] getRarityFolders() {
        File dir = new File(weaponFolder);
        File[] files = dir.listFiles();
        return files;
    }

    public YamlConfiguration getWeaponConfig(){
        YamlConfiguration weaponConfig = new YamlConfiguration();
        try {
            weaponConfig.load(rarityFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
        return weaponConfig;

    }

    public List<String> getRarityNames(){
        File dir = new File(weaponFolder);
        return Arrays.asList(Objects.requireNonNull(dir.list()));
    }

    public File[] getRarityWeaponFiles(String string) {
        File[] dir = getRarityFolders();
        for (File file : dir) {
            if (file.getName().equals(string)) {
                return file.listFiles();
            }
        }
        return null;
    }

    public List<String> getRarityWeaponNames(String string) {
        File[] dir = getRarityFolders();
        for (File file : dir) {
            if (file.getName().equals(string)) {
                return Arrays.asList(Objects.requireNonNull(file.list()));
            }
        }
        return null;
    }

    public int getWeaponAmount() {
        File[] dir = getRarityFolders();
        int amount = 0;
        for (File file : dir) {
            amount = amount + Objects.requireNonNull(file.listFiles()).length;
        }
        return amount;
    }

}

