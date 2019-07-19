package net.prosavage.savagerpg.command;

import com.github.stefvanschie.inventoryframework.Gui;
import com.github.stefvanschie.inventoryframework.GuiItem;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import net.prosavage.savagerpg.SavageRPG;
import net.prosavage.savagerpg.PDCPlayer;
import net.prosavage.savagerpg.hooks.mythicmobs.SkillCast;
import net.prosavage.savagerpg.itembuilder.Armor;
import net.prosavage.savagerpg.utils.Formula;
import net.prosavage.savagerpg.utils.Placeholder;
import net.prosavage.savagerpg.utils.merchants.SMerchant;
import net.prosavage.savagerpg.debug.DebugScoreboardManager;
import net.prosavage.savagerpg.itembuilder.Weapon;
import net.prosavage.savagerpg.utils.Color;
import net.prosavage.savagerpg.utils.Number;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class SavageRPGDebugCommand implements CommandExecutor {

    Number Number = new Number();
    SMerchant SMerchant = new SMerchant();
    Weapon Weapon = new Weapon();
    Color Color = new Color();
    Armor Armor = new Armor();
    Formula Formula = new Formula();
    Placeholder Placeholder = new Placeholder();
    FileConfiguration SConfig = SavageRPG.getInstance().getConfig();
    SkillCast SkillCast = new SkillCast();


    @Override
    public boolean onCommand(@NotNull CommandSender sender,@NotNull Command command,@NotNull String label,@NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            PDCPlayer pdcPlayer = new PDCPlayer(player);
            if (args[0].equals("on")) {
                DebugScoreboardManager scoreboardManager = new DebugScoreboardManager(player);
                PDCPlayer finalPdcPlayer = pdcPlayer;
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (player.isOnline()) {
                            finalPdcPlayer.setLevel(Number.getInteger(1,100));
                            scoreboardManager.setSlot(1,"LVL " + finalPdcPlayer.getLevel(),"");
                            scoreboardManager.setSlot(2,"EXP " + finalPdcPlayer.getExp(),"");
                            scoreboardManager.setSlot(3,"MXP " + finalPdcPlayer.getMaxExp(),"");
                            scoreboardManager.setSlot(4,"ATR " + finalPdcPlayer.getAttributePoints(),"");
                            scoreboardManager.setSlot(5,"STR " + finalPdcPlayer.getStrengthPoints(),"");
                            scoreboardManager.setSlot(6,"INT " + finalPdcPlayer.getIntelligencePoints(),"");
                            scoreboardManager.setSlot(7,"CON " + finalPdcPlayer.getConstitutionPoints(),"");
                            scoreboardManager.setSlot(8,"DEX " + finalPdcPlayer.getDexterityPoints(),"");
                            scoreboardManager.setSlot(9,"CHA " + finalPdcPlayer.getCharismaPoints(),"");
                            scoreboardManager.setSlot(10,"WIS " + finalPdcPlayer.getWisdomPoints(),"");
                            scoreboardManager.setSlot(11,"LUK " + finalPdcPlayer.getLuckPoints(),"");
                        } else if (!(player.isOnline())) {
                            this.cancel();
                        }
                    }
                }.runTaskTimer(SavageRPG.getInstance(),5L,5L);
            }
            if (args[0].equals("value")) {
                player.sendMessage("LVL " + pdcPlayer.getLevel());
                player.sendMessage("EXP " + pdcPlayer.getExp());
                player.sendMessage("MXP " + pdcPlayer.getMaxExp());
                player.sendMessage("ATR " + pdcPlayer.getAttributePoints());
                player.sendMessage("STR " + pdcPlayer.getStrengthPoints());
                player.sendMessage("INT " + pdcPlayer.getIntelligencePoints());
                player.sendMessage("CON " + pdcPlayer.getConstitutionPoints());
                player.sendMessage("DEX " + pdcPlayer.getDexterityPoints());
                player.sendMessage("CHA " + pdcPlayer.getCharismaPoints());
                player.sendMessage("WIS " + pdcPlayer.getWisdomPoints());
                player.sendMessage("LUK " + pdcPlayer.getLuckPoints());
                player.sendMessage("DEF " + pdcPlayer.getDefense());
                player.sendMessage("GEAR SCORE " + pdcPlayer.getGearScore());
                pdcPlayer.setMaxExp();
            }
            if (args[0].equals("spawn")) {
                final int[] i = {0};
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (Number.isLessThan(i[0],10)) {
                            i[0]++;
                            Location location = new Location(player.getWorld(),player.getLocation().getX(),player.getLocation().getY(),player.getLocation().getZ());
                            Slime slime = player.getWorld().spawn(location,Slime.class);
                            slime.setSize(1);
                            slime.setAI(false);
                            slime.setInvulnerable(true);
                            ArmorStand armorStand = player.getWorld().spawn(location,ArmorStand.class);
                            armorStand.setHelmet(new ItemStack(Material.YELLOW_BANNER));
                            armorStand.setVisible(false);
                            armorStand.setInvulnerable(true);
                            armorStand.setSmall(true);
                            armorStand.setMarker(true);
                            armorStand.setCustomName("RPG Merchant Testing...");
                            armorStand.setCustomNameVisible(true);
                            WanderingTrader merchant = player.getWorld().spawn(location,WanderingTrader.class);
                            merchant.addPassenger(slime);
                            slime.addPassenger(armorStand);
                            MerchantRecipe recipe = SMerchant.createRecipe(new ItemStack(Material.STONE),new ItemStack(Material.STICK),new ItemStack(Material.DIAMOND));
                            SMerchant.addOneToMerchant(merchant,recipe);
                        } else if (Number.isGreaterOrEqualTo(i[0],11)) {
                            this.cancel();
                        }
                    }
                }.runTaskTimer(SavageRPG.getInstance(),5L,20L);

            }
            if (args[0].equals("weapon")) {
                player.getInventory().addItem(Weapon.getRandomItem());
            }
            if (args[0].equals("armor")) {
                player.getInventory().addItem(Armor.getRandomItem());
            }
            if (args[0].equals("testvalues")) {
                pdcPlayer.setLevel(99);
                pdcPlayer.setExp(0.0);
                pdcPlayer.setMaxExp();
                pdcPlayer.setAttributePoints(99);
                pdcPlayer.setStrengthPoint(99);
                pdcPlayer.setIntelligencePoint(99);
                pdcPlayer.setConstitutionPoint(99);
                pdcPlayer.setDexterityPoint(99);
                pdcPlayer.setCharismaPoint(99);
                pdcPlayer.setWisdomPoint(99);
                pdcPlayer.setLuckPoints(99);
                pdcPlayer.setDefense(999999);
            }
            if (args[0].equals("attributes")) {
                Gui gui = new Gui(SavageRPG.getInstance(), 3, "Test");
                OutlinePane pane = new OutlinePane(1, 1, 7, 1);
                OutlinePane pane2 = new OutlinePane(0, 0, 9, 3);
                for (String e : Objects.requireNonNull(SavageRPG.getInstance().getConfig().getConfigurationSection("gui.attribute")).getKeys(false)){
                    ItemStack item = new ItemStack(Material.valueOf((String) SavageRPG.getInstance().getConfig().get("gui.attribute." + e + ".material")));
                    ItemMeta meta = item.getItemMeta();
                    List<String> lore = (SavageRPG.getInstance().getConfig().getStringList("gui.attribute." + e + ".lore"));
                    assert meta != null;
                    lore = parseLore(lore, player);
                    meta.setLore(lore);
                    item.setItemMeta(meta);
                    GuiItem item2 = new GuiItem(item, event -> {
                        event.setCancelled(true);
                        attributeApply(player, e);
                    });
                    pane.addItem(item2);
                }
                for (int i = 0; i < 27; i++) {
                    GuiItem blackpane = new GuiItem(new ItemStack(Material.BLACK_STAINED_GLASS_PANE),event -> {
                        event.setCancelled(true);
                    });
                    pane2.addItem(blackpane);
                }
                gui.addPane(pane2);
                gui.addPane(pane);
                gui.show(player);
            }
            if (args[0].equals("skill")){
                Entity etarget = getTarget(player, 20);
                SkillCast.forPlayer(player, "test", etarget);
            }
            return false;
        }

        return false;
    }

    public boolean attributeApply(Player player, String string) {

        PDCPlayer pdcPlayer = new PDCPlayer(player);
        if (Number.isLessThan(pdcPlayer.getAttributePoints(), 1)){
            player.closeInventory();
            String title = (String) SavageRPG.getInstance().getConfig().get("gui.attribute." + string + ".title.title");
            String subtitle = (String) SavageRPG.getInstance().getConfig().get("gui.attribute." + string + ".title.subtitle");
            int fadeIn = (int) SavageRPG.getInstance().getConfig().get("gui.attribute." + string + ".title.fadeIn");
            int stay = (int) SavageRPG.getInstance().getConfig().get("gui.attribute." + string + ".title.stay");
            int fadeOut = (int) SavageRPG.getInstance().getConfig().get("gui.attribute." + string + ".title.fadeOut");
            player.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
            return false;
        }
        if (string.equals("strength")) {
            if (Number.isGreaterOrEqualTo(pdcPlayer.getAttributePoints(),1)) {
                pdcPlayer.setStrengthPoint(pdcPlayer.getStrengthPoints() + 1);
                pdcPlayer.setAttributePoints(pdcPlayer.getAttributePoints() - 1);
            }
        }

        if (string.equals("intelligence")) {
            if (Number.isGreaterOrEqualTo(pdcPlayer.getAttributePoints(),1)) {
                pdcPlayer.setIntelligencePoint(pdcPlayer.getIntelligencePoints() + 1);
                pdcPlayer.setAttributePoints(pdcPlayer.getAttributePoints() - 1);
            }
        }

        if (string.equals("constitution")) {
            if (Number.isGreaterOrEqualTo(pdcPlayer.getAttributePoints(),1)) {
                pdcPlayer.setConstitutionPoint(pdcPlayer.getConstitutionPoints() + 1);
                pdcPlayer.setAttributePoints(pdcPlayer.getAttributePoints() - 1);
            }
        }

        if (string.equals("dexterity")) {
            if (Number.isGreaterOrEqualTo(pdcPlayer.getAttributePoints(),1)) {
                pdcPlayer.setDexterityPoint(pdcPlayer.getDexterityPoints() + 1);
                pdcPlayer.setAttributePoints(pdcPlayer.getAttributePoints() - 1);
            }
        }

        if (string.equals("charisma")) {
            if (Number.isGreaterOrEqualTo(pdcPlayer.getAttributePoints(),1)) {
                pdcPlayer.setCharismaPoint(pdcPlayer.getCharismaPoints() + 1);
                pdcPlayer.setAttributePoints(pdcPlayer.getAttributePoints() - 1);
            }
        }

        if (string.equals("wisdom")) {
            if (Number.isGreaterOrEqualTo(pdcPlayer.getAttributePoints(),1)) {
                pdcPlayer.setWisdomPoint(pdcPlayer.getWisdomPoints() + 1);
                pdcPlayer.setAttributePoints(pdcPlayer.getAttributePoints() - 1);
            }
        }

        if (string.equals("luck")) {
            if (Number.isGreaterOrEqualTo(pdcPlayer.getAttributePoints(),1)) {
                pdcPlayer.setLuckPoints(pdcPlayer.getLuckPoints() + 1);
                pdcPlayer.setAttributePoints(pdcPlayer.getAttributePoints() - 1);
            }
        }
        return true;
    }

    public List<String> parseLore(List<String> lore, Player player){
        for (String i : lore){
            int index = lore.indexOf(i);
            i = Color.ify(i);
            double damage = Formula.eval(Placeholder.parsePlayerInfo(player,getEquation("player-damage-attribute")));
            double health = Formula.eval(Placeholder.parsePlayerInfo(player,getEquation("player-health-attribute")));
            double mana = Formula.eval(Placeholder.parsePlayerInfo(player,getEquation("player-mana-attribute")));
            double skillchance = Formula.eval(Placeholder.parsePlayerInfo(player,getEquation("player-attribute-chance-attribute")));
            double defense = Formula.eval(Placeholder.parsePlayerInfo(player,getEquation("player-defense-attribute")));
            double consHealth = Formula.eval(Placeholder.parsePlayerInfo(player,getEquation("player-constitution-health-attribute")));
            double speed = Formula.eval(Placeholder.parsePlayerInfo(player,getEquation("player-speed-attribute")));
            double evasion = Formula.eval(Placeholder.parsePlayerInfo(player,getEquation("player-evasion-attribute")));
            double liking = Formula.eval(Placeholder.parsePlayerInfo(player,getEquation("player-liking-attribute")));
            double lowerPrice = Formula.eval(Placeholder.parsePlayerInfo(player,getEquation("player-lower-price-attribute")));
            double manaRegen = Formula.eval(Placeholder.parsePlayerInfo(player,getEquation("player-mana-regeneration-attribute")));
            double abilityCooldown = Formula.eval(Placeholder.parsePlayerInfo(player,getEquation("player-ability-cooldown-attribute")));
            double lootChance = Formula.eval(Placeholder.parsePlayerInfo(player,getEquation("player-loot-drop-attribute")));
            i = i.replace(Placeholder.getYAMLPlaceholder("player-damage-equation"),String.valueOf(damage))
                    .replace(Placeholder.getYAMLPlaceholder("player-health-equation"),String.valueOf(health))
                    .replace(Placeholder.getYAMLPlaceholder("player-mana-equation"),String.valueOf(mana))
                    .replace(Placeholder.getYAMLPlaceholder("player-attribute-chance-equation"),String.valueOf(skillchance))
                    .replace(Placeholder.getYAMLPlaceholder("player-defense-equation"),String.valueOf(defense))
                    .replace(Placeholder.getYAMLPlaceholder("player-constitution-health-equation"),String.valueOf(consHealth))
                    .replace(Placeholder.getYAMLPlaceholder("player-speed-equation"),String.valueOf(speed))
                    .replace(Placeholder.getYAMLPlaceholder("player-evasion-equation"),String.valueOf(evasion))
                    .replace(Placeholder.getYAMLPlaceholder("player-liking-equation"),String.valueOf(liking))
                    .replace(Placeholder.getYAMLPlaceholder("player-lower-price-equation"),String.valueOf(lowerPrice))
                    .replace(Placeholder.getYAMLPlaceholder("player-mana-regeneration-equation"),String.valueOf(manaRegen))
                    .replace(Placeholder.getYAMLPlaceholder("player-ability-cooldown-equation"),String.valueOf(abilityCooldown))
                    .replace(Placeholder.getYAMLPlaceholder("player-loot-drop-equation"),String.valueOf(lootChance));
            lore.set(index, i);
        }
        return lore;
    }

    public String getEquation(String node){
        return SavageRPG.getInstance().getConfig().getString("formulas." + node);
    }

    public Entity getTarget(Player player, int range){
        Vector dir = player.getLocation().getDirection(); // get the player's direction.
        Location loc = player.getEyeLocation(); // get the player's eye location.
        double x = player.getLocation().getX();
        double y = player.getLocation().getY();
        double z = player.getLocation().getZ();
        for (int i = 1; i <= range; i++) {
            loc.add(x * i, y * i, z * i); // move the location forward.
            for (Entity ent : Objects.requireNonNull(loc.getWorld()).getNearbyEntities(loc, 2, 2, 2)) // get entities around 'loc'.
                if (ent instanceof Player) // the entity is a player
                    return ent; // player found!
            loc.subtract(x * i, y * i, z * i); // move loc back
        }
        return null;
    }

}


