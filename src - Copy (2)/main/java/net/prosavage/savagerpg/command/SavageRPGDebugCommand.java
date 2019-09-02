package net.prosavage.savagerpg.command;

import net.prosavage.savagerpg.SavageRPG;
import net.prosavage.savagerpg.api.entities.SPlayer;
import net.prosavage.savagerpg.builder.Armor;
import net.prosavage.savagerpg.builder.Mob;
import net.prosavage.savagerpg.utils.Formula;
import net.prosavage.savagerpg.utils.Placeholder;
import net.prosavage.savagerpg.api.itemstacks.SWeapon;
import net.prosavage.savagerpg.utils.merchants.SMerchant;
import net.prosavage.savagerpg.builder.Weapon;
import net.prosavage.savagerpg.utils.Color;
import net.prosavage.savagerpg.utils.Number;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;

public class SavageRPGDebugCommand implements CommandExecutor {

    Number Number = new Number();
    SMerchant SMerchant = new SMerchant();
    Color Color = new Color();
    Formula Formula = new Formula();
    Placeholder Placeholder = new Placeholder();
    FileConfiguration SConfig = SavageRPG.getInstance().getConfig();
    SWeapon SWeapon = new SWeapon();
//    SkillCast SkillCast = new SkillCast();


    @Override
    public boolean onCommand(@NotNull CommandSender sender,@NotNull Command command,@NotNull String label,@NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            SPlayer sPlayer = new SPlayer(player);
            if (args[0].equals("value")) {
                player.sendMessage("LVL " + sPlayer.getLevel());
                player.sendMessage("EXP " + sPlayer.getExp());
                player.sendMessage("MXP " + sPlayer.getMaxExp());
                player.sendMessage("ATR " + sPlayer.getAttributePoints());
                player.sendMessage("STR " + sPlayer.getStrengthPoints());
                player.sendMessage("INT " + sPlayer.getIntelligencePoints());
                player.sendMessage("CON " + sPlayer.getConstitutionPoints());
                player.sendMessage("DEX " + sPlayer.getDexterityPoints());
                player.sendMessage("CHA " + sPlayer.getCharismaPoints());
                player.sendMessage("WIS " + sPlayer.getWisdomPoints());
                player.sendMessage("LUK " + sPlayer.getLuckPoints());
                player.sendMessage("DEF " + sPlayer.getDefense());
                player.sendMessage("GEAR SCORE " + sPlayer.getGearScore());
                sPlayer.setMaxExp();
            }
            if (args[0].equals("weapon")) {
                ItemStack itemStack = new ItemStack(Material.WOODEN_SWORD);
                ItemStack weapon = new Weapon(itemStack)
                        .setMaterial("WOODEN")
                        .setRarity("Common")
                        .setLevel(1)
                        .setMinDamage(1.0)
                        .setMaxDamage(5.0)
                        .setGem(1)
                        .build();
                player.getInventory().addItem(weapon);
                itemStack = new ItemStack(itemStack);
                weapon = new Weapon(itemStack, "Test", "String", "Unwanted", 0.1, Collections.singletonList("a||b"), 10, 2.0, 0.2, 1).build();
                player.getInventory().addItem(weapon);
                itemStack = new ItemStack(itemStack);
                weapon = new Weapon(itemStack, "test", "WATC", "Unwanted", 0.1, Collections.singletonList("hey||ab"), 1, 5.0, 1.0, 1, 1.0, "Test",Arrays.asList("you suck"), "PLAYER", 5.0).build();
                player.getInventory().addItem(weapon);
                weapon = new Weapon(itemStack).createItemFromFiles("TEST", "test", true);
                player.getInventory().addItem(weapon);
            }
            if (args[0].equals("armor")){
                ItemStack itemStack = new ItemStack(Material.DIAMOND_HELMET);
                ItemStack armor = new Armor(itemStack)
                        .setName("ARMOR TEST")
                        .setMaterial("Wooden")
                        .setRarity("Common")
                        .setLevel(1)
                        .setProtection(25.0)
                        .setHealth(95.0)
                        .setRegen(25.0)
                        .build();
                player.getInventory().addItem(armor);
                itemStack = new ItemStack(Material.DIAMOND_CHESTPLATE);
                armor = new Armor(itemStack)
                        .setName("ARMOR TEST")
                        .setMaterial("Wooden")
                        .setRarity("Common")
                        .setLevel(1)
                        .setProtection(25.0)
                        .setHealth(95.0)
                        .setRegen(25.0)
                        .build();
                player.getInventory().addItem(armor);
                itemStack = new ItemStack(Material.DIAMOND_LEGGINGS);
                armor = new Armor(itemStack)
                        .setName("ARMOR TEST")
                        .setMaterial("Wooden")
                        .setRarity("Common")
                        .setLevel(1)
                        .setProtection(25.0)
                        .setHealth(95.0)
                        .setRegen(25.0)
                        .build();
                player.getInventory().addItem(armor);
                itemStack = new ItemStack(Material.DIAMOND_BOOTS);
                armor = new Armor(itemStack)
                        .setName("ARMOR TEST")
                        .setMaterial("Wooden")
                        .setRarity("Common")
                        .setLevel(1)
                        .setProtection(25.0)
                        .setHealth(95.0)
                        .setRegen(25.0)
                        .setGem(3)
                        .build();
                player.getInventory().addItem(armor);

            }
            if (args[0].equals("testvalues")) {
                sPlayer.setLevel(99);
                sPlayer.setExp(0.0);
                sPlayer.setMaxExp();
                sPlayer.setAttributePoints(99);
                sPlayer.setStrengthPoint(99);
                sPlayer.setIntelligencePoint(99);
                sPlayer.setConstitutionPoint(99);
                sPlayer.setDexterityPoint(99);
                sPlayer.setCharismaPoint(99);
                sPlayer.setWisdomPoint(99);
                sPlayer.setLuckPoints(99);
                sPlayer.setDefense(999999);
            }
            if (args[0].equals("water")){
                player.setRemainingAir(10);
            }
        }
        if (args[0].equals("mobby")){
            World world = Bukkit.getWorld("world");
            EntityType entity = EntityType.ZOMBIE;
            Mob mob = new Mob(entity);
            mob.spawn(world, 0.0, 125.0, 0.0);
        }
        if (args[0].equals("weaponStats")) {
            Player player = (Player) sender;
            SavageRPG.getInstance().sendConsole(SWeapon.getName(player.getInventory().getItemInMainHand()));
            SavageRPG.getInstance().sendConsole(SWeapon.getMaterial(player.getInventory().getItemInMainHand()));
            SavageRPG.getInstance().sendConsole(SWeapon.getRarity(player.getInventory().getItemInMainHand()));
            SavageRPG.getInstance().sendConsole(String.valueOf(SWeapon.getLore(player.getInventory().getItemInMainHand())));
            SavageRPG.getInstance().sendConsole(String.valueOf(SWeapon.getLevel(player.getInventory().getItemInMainHand())));
            SavageRPG.getInstance().sendConsole("" + SWeapon.getMinimumDamage(player.getInventory().getItemInMainHand()));
            SavageRPG.getInstance().sendConsole("" + SWeapon.getMaximumDamage(player.getInventory().getItemInMainHand()));
            SavageRPG.getInstance().sendConsole("" + SWeapon.getGems(player.getInventory().getItemInMainHand()));
            SavageRPG.getInstance().sendConsole("" + SWeapon.getAbilityName(player.getInventory().getItemInMainHand()));
            SavageRPG.getInstance().sendConsole(SWeapon.getAbilityName(player.getInventory().getItemInMainHand()));
            SavageRPG.getInstance().sendConsole("" + SWeapon.getAbilityDescription(player.getInventory().getItemInMainHand()));
            SavageRPG.getInstance().sendConsole(SWeapon.getAbilityCastType(player.getInventory().getItemInMainHand()));
            SavageRPG.getInstance().sendConsole("" + SWeapon.getAbilityCooldown(player.getInventory().getItemInMainHand()));
        }
        return false;
    }
}


