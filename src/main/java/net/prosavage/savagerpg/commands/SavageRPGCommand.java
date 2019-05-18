package net.prosavage.savagerpg.commands;

import net.prosavage.savagerpg.SavageRPG;
import net.prosavage.savagerpg.itembuilder.Weapon;
import net.prosavage.savagerpg.utils.Color;
import net.prosavage.savagerpg.utils.Formula;
import net.prosavage.savagerpg.utils.Placeholder;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class SavageRPGCommand implements CommandExecutor {

    Color Color = new Color();
    Formula Formula = new Formula();
    Placeholder Placeholder = new Placeholder();
    Weapon Weapon = new Weapon();

    NamespacedKey NSK_WEAPON_TYPE = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Weapon-Type");
    NamespacedKey NSK_WEAPON_RARITY = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Weapon-Rarity");
    NamespacedKey NSK_WEAPON_CLASS = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Weapon-Class");
    NamespacedKey NSK_WEAPON_LEVEL = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Weapon-Level");
    NamespacedKey NSK_WEAPON_DAMAGE_MIN = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Weapon-Damage-Min");
    NamespacedKey NSK_WEAPON_DAMAGE_MAX = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Weapon-Damage-Max");
    NamespacedKey NSK_WEAPON_COOLDOWN = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Weapon-Damage-Max");
    NamespacedKey NSK_WEAPON_DAMAGE_PER_SECOND = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Weapon-Damage-Per-Second");
    NamespacedKey NSK_WEAPON_GEM = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Weapon-Gem");
    NamespacedKey NSK_WEAPON_HAVE_GEM = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Weapon-NoGem");

    NamespacedKey NSK_ARMOR_TYPE = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Armor-Type");
    NamespacedKey NSK_ARMOR_RARITY = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Armor-Rarity");
    NamespacedKey NSK_ARMOR_CLASS = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Armor-Class");
    NamespacedKey NSK_ARMOR_LEVEL = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Armor-Level");
    NamespacedKey NSK_ARMOR_PROTECTION = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Armor-Protection");
    NamespacedKey NSK_ARMOR_HEALTH = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Armor-Health");
    NamespacedKey NSK_ARMOR_REGEN = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Armor-Regen");
    NamespacedKey NSK_ARMOR_GEM = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Armor-Gem");
    NamespacedKey NSK_ARMOR_HAVE_GEM = new NamespacedKey(SavageRPG.getInstance(), "SavageRPG-Armor-NoGem");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player player = (Player) sender;
            if (args[0].equals("help")){
                if (player.isOp()){
                    player.sendMessage(Color.ify("&a/savageequipment reload - reloads the config"));
                    return true;
                }
            }
            if (args[0].equals("reload")){
                if (player.isOp()) {
                    SavageRPG.getInstance().sendConsole("&aReloading config...");
                    SavageRPG.getInstance().reloadConfig();
                    SavageRPG.getInstance().reloadFiles();
                    SavageRPG.getInstance().sendConsole("&aReloading complete");
                    return true;
                }
            }
            if (args[0].equals("info")){
                if (player.isOp()){
                    List<MetadataValue> level = player.getMetadata("SavageEquipments-"+ player.getUniqueId() + "-level");
                    List<MetadataValue> exp = player.getMetadata("SavageEquipments-"+ player.getUniqueId() + "-exp");
                    List<MetadataValue> max_exp = player.getMetadata("SavageEquipments-"+ player.getUniqueId() + "-max_exp");
                    List<MetadataValue> skillpoints = player.getMetadata("SavageEquipments-"+ player.getUniqueId() + "-skillpoints");
                    if (!(level.isEmpty())) {
                        player.sendMessage("Level " + String.valueOf(level.get(level.size() - 1).value()));
                        player.sendMessage("EXP " + String.valueOf(exp.get(exp.size() - 1).value()));
                        player.sendMessage("Max EXP " + String.valueOf(max_exp.get(max_exp.size() - 1).value()));
                        player.sendMessage("Skillpoints " + String.valueOf(skillpoints.get(skillpoints.size() - 1).value()));
                    }
                }
            }
            if (args[0].equals("check")){
                if (player.isOp()){

                    ItemMeta meta = player.getInventory().getItemInMainHand().getItemMeta();

                    assert meta != null;
                    String itemName = meta.getPersistentDataContainer().get(NSK_WEAPON_TYPE, PersistentDataType.STRING);
                    String rarity = meta.getPersistentDataContainer().get(NSK_WEAPON_RARITY, PersistentDataType.STRING);
                    String classType = meta.getPersistentDataContainer().get(NSK_WEAPON_CLASS, PersistentDataType.STRING);
                    Integer level = meta.getPersistentDataContainer().get(NSK_WEAPON_LEVEL, PersistentDataType.INTEGER);
                    Double damageLowest = meta.getPersistentDataContainer().get(NSK_WEAPON_DAMAGE_MIN, PersistentDataType.DOUBLE);
                    Double damageHighest = meta.getPersistentDataContainer().get(NSK_WEAPON_DAMAGE_MAX, PersistentDataType.DOUBLE);
                    Double cooldown = meta.getPersistentDataContainer().get(NSK_WEAPON_COOLDOWN, PersistentDataType.DOUBLE);
                    Double damagePerSecond = meta.getPersistentDataContainer().get(NSK_WEAPON_DAMAGE_PER_SECOND, PersistentDataType.DOUBLE);
                    Integer gem = meta.getPersistentDataContainer().get(NSK_WEAPON_GEM, PersistentDataType.INTEGER);
                    String noGem = meta.getPersistentDataContainer().get(NSK_WEAPON_HAVE_GEM, PersistentDataType.STRING);
                    player.sendMessage("");
                    player.sendMessage("Name: " + itemName);
                    player.sendMessage("Rarity: " + rarity);
                    player.sendMessage("classType: " + classType);
                    player.sendMessage("level: " + level);
                    player.sendMessage("damageLowest: " + damageLowest);
                    player.sendMessage("damageHighest: " + damageHighest);
                    player.sendMessage("cooldown: " + cooldown);
                    player.sendMessage("damagePerSecond: " + damagePerSecond);
                    player.sendMessage("gem: " + gem);
                    player.sendMessage("noGem: " + noGem);

                    String itemName2 = meta.getPersistentDataContainer().get(NSK_ARMOR_TYPE, PersistentDataType.STRING);
                    String rarity2 = meta.getPersistentDataContainer().get(NSK_ARMOR_RARITY, PersistentDataType.STRING);
                    String classType2 = meta.getPersistentDataContainer().get(NSK_ARMOR_CLASS, PersistentDataType.STRING);
                    Integer level2 = meta.getPersistentDataContainer().get(NSK_ARMOR_LEVEL, PersistentDataType.INTEGER);
                    Double protection = meta.getPersistentDataContainer().get(NSK_ARMOR_PROTECTION, PersistentDataType.DOUBLE);
                    Double health = meta.getPersistentDataContainer().get(NSK_ARMOR_HEALTH, PersistentDataType.DOUBLE);
                    Double regen = meta.getPersistentDataContainer().get(NSK_ARMOR_REGEN, PersistentDataType.DOUBLE);
                    Integer gem2 = meta.getPersistentDataContainer().get(NSK_ARMOR_GEM, PersistentDataType.INTEGER);
                    String noGem2 = meta.getPersistentDataContainer().get(NSK_ARMOR_HAVE_GEM, PersistentDataType.STRING);

                    player.sendMessage("");
                    player.sendMessage("Name: " + itemName2);
                    player.sendMessage("Rarity: " + rarity2);
                    player.sendMessage("classType: " + classType2);
                    player.sendMessage("level: " + level2);
                    player.sendMessage("protection: " + protection);
                    player.sendMessage("health: " + health);
                    player.sendMessage("regen: " + regen);
                    player.sendMessage("gem: " + gem2);
                    player.sendMessage("noGem: " + noGem2);
                }
            }
        }
        return false;
    }
}
