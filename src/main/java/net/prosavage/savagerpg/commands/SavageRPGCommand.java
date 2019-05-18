package net.prosavage.savagerpg.commands;

import net.prosavage.savagerpg.SavageRPG;
import net.prosavage.savagerpg.itembuilder.Armor;
import net.prosavage.savagerpg.itembuilder.Weapon;
import net.prosavage.savagerpg.utils.Color;
import net.prosavage.savagerpg.utils.Formula;
import net.prosavage.savagerpg.utils.Placeholder;
import net.prosavage.savagerpg.utils.SRNamespacedKey;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class SavageRPGCommand implements CommandExecutor {

    Color Color = new Color();
    Formula Formula = new Formula();
    Placeholder Placeholder = new Placeholder();
    Weapon Weapon = new Weapon();
    Armor Armor = new Armor();
    SRNamespacedKey SRNamespacedKey = new SRNamespacedKey();

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
                    Integer level = player.getPersistentDataContainer().get(SRNamespacedKey.getNamespacedKey_player_level(), PersistentDataType.INTEGER);
                    Double exp = player.getPersistentDataContainer().get(SRNamespacedKey.getNamespacedKey_player_exp(), PersistentDataType.DOUBLE);
                    Double max_exp = player.getPersistentDataContainer().get(SRNamespacedKey.getNamespacedKey_player_max_exp(), PersistentDataType.DOUBLE);
                    Integer skillpoints = player.getPersistentDataContainer().get(SRNamespacedKey.getNamespacedKey_player_skill_points(), PersistentDataType.INTEGER);
                    player.sendMessage("Level " + level);
                    player.sendMessage("EXP " + exp);
                    player.sendMessage("Max EXP " + max_exp);
                    player.sendMessage("Skillpoints " + skillpoints);
                }
            }
            if (args[0].equals("check")){
                if (player.isOp()){

                    ItemStack item = player.getInventory().getItemInMainHand();
                    if (item.getType().toString().contains("SWORD")) {
                        String itemName = Weapon.getItemType(item);
                        String rarity = Weapon.getRarity(item);
                        String classType = Weapon.getClass(item);
                        int level = Weapon.getLevel(item);
                        double damageLowest = Weapon.getMinDamage(item);
                        double damageHighest = Weapon.getMaxDamage(item);
                        double cooldown = Weapon.getCooldown(item);
                        double damagePerSecond = Weapon.getDamagePerSecond(item);
                        int gem = Weapon.getGem(item);
                        boolean noGem = Weapon.haveGem(item);
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
                    }

                    String itemName2 = Armor.getType(item);
                    String rarity2 = Armor.getRarity(item);
                    String classType2 = Armor.getClass(item);
                    int level2 = Armor.getLevel(item);
                    double protection = Armor.getProtection(item);
                    double health = Armor.getHealth(item);
                    double regen = Armor.getRegen(item);
                    int gem2 = Armor.getGem(item);
                    String noGem2 = Armor.getType(item);

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
