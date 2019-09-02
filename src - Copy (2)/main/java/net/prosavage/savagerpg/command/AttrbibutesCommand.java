package net.prosavage.savagerpg.command;

import com.github.stefvanschie.inventoryframework.Gui;
import com.github.stefvanschie.inventoryframework.GuiItem;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import net.prosavage.savagerpg.SavageRPG;
import net.prosavage.savagerpg.api.entities.SPlayer;
import net.prosavage.savagerpg.utils.Color;
import net.prosavage.savagerpg.utils.Formula;
import net.prosavage.savagerpg.utils.Number;
import net.prosavage.savagerpg.utils.Placeholder;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class AttrbibutesCommand implements CommandExecutor {

    Number Number = new Number();
    Placeholder Placeholder = new Placeholder();
    Formula Formula = new Formula();
    Color Color = new Color();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args[0].equals("attributes")) {
                Gui gui = new Gui(SavageRPG.getInstance(), 3, "Test");
                OutlinePane pane = new OutlinePane(1, 1, 7, 1);
                OutlinePane pane2 = new OutlinePane(0, 0, 9, 3);
                for (String e : Objects.requireNonNull(SavageRPG.getInstance().getConfig().getConfigurationSection("gui.attribute")).getKeys(false)) {
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
                    GuiItem blackpane = new GuiItem(new ItemStack(Material.BLACK_STAINED_GLASS_PANE), event -> {
                        event.setCancelled(true);
                    });
                    pane2.addItem(blackpane);
                }
                gui.addPane(pane2);
                gui.addPane(pane);
                gui.show(player);
            }
        }
        return false;
    }

// Attribute applying

    public boolean attributeApply(Player player, String string) {
        SPlayer sPlayer = new SPlayer(player);
        if (Number.isLessThan(sPlayer.getAttributePoints(), 1)) {
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
            if (Number.isGreaterOrEqualTo(sPlayer.getAttributePoints(), 1)) {
                sPlayer.setStrengthPoint(sPlayer.getStrengthPoints() + 1);
                sPlayer.setAttributePoints(sPlayer.getAttributePoints() - 1);
            }
        }
        if (string.equals("intelligence")) {
            if (Number.isGreaterOrEqualTo(sPlayer.getAttributePoints(), 1)) {
                sPlayer.setIntelligencePoint(sPlayer.getIntelligencePoints() + 1);
                sPlayer.setAttributePoints(sPlayer.getAttributePoints() - 1);
            }
        }
        if (string.equals("constitution")) {
            if (Number.isGreaterOrEqualTo(sPlayer.getAttributePoints(), 1)) {
                sPlayer.setConstitutionPoint(sPlayer.getConstitutionPoints() + 1);
                sPlayer.setAttributePoints(sPlayer.getAttributePoints() - 1);
            }
        }
        if (string.equals("dexterity")) {
            if (Number.isGreaterOrEqualTo(sPlayer.getAttributePoints(), 1)) {
                sPlayer.setDexterityPoint(sPlayer.getDexterityPoints() + 1);
                sPlayer.setAttributePoints(sPlayer.getAttributePoints() - 1);
            }
        }
        if (string.equals("charisma")) {
            if (Number.isGreaterOrEqualTo(sPlayer.getAttributePoints(), 1)) {
                sPlayer.setCharismaPoint(sPlayer.getCharismaPoints() + 1);
                sPlayer.setAttributePoints(sPlayer.getAttributePoints() - 1);
            }
        }
        if (string.equals("wisdom")) {
            if (Number.isGreaterOrEqualTo(sPlayer.getAttributePoints(), 1)) {
                sPlayer.setWisdomPoint(sPlayer.getWisdomPoints() + 1);
                sPlayer.setAttributePoints(sPlayer.getAttributePoints() - 1);
            }
        }
        if (string.equals("luck")) {
            if (Number.isGreaterOrEqualTo(sPlayer.getAttributePoints(), 1)) {
                sPlayer.setLuckPoints(sPlayer.getLuckPoints() + 1);
                sPlayer.setAttributePoints(sPlayer.getAttributePoints() - 1);
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

}