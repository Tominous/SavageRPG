package net.prosavage.savagerpg.command;

import net.prosavage.savagerpg.SavageRPG;
import net.prosavage.savagerpg.api.SMerchant;
import net.prosavage.savagerpg.api.SPlayer;
import net.prosavage.savagerpg.debug.DebugScoreboardManager;
import net.prosavage.savagerpg.utils.Number;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.banner.PatternType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

public class SavageRPGDebugCommand implements CommandExecutor {

    Number Number = new Number();
    SMerchant SMerchant = new SMerchant();

    @Override
    public boolean onCommand(@NotNull CommandSender sender,@NotNull Command command,@NotNull String label,@NotNull String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            SPlayer sPlayer = new SPlayer(player);
            if (args[0].equals("on")){
                DebugScoreboardManager scoreboardManager = new DebugScoreboardManager(player);
                new BukkitRunnable(){
                    @Override
                    public void run() {
                        if (player.isOnline()) {
                            sPlayer.setLevel(Number.getInteger(1, 100));
                            scoreboardManager.setSlot(1,"LVL " + sPlayer.getLevel(),"");
                            scoreboardManager.setSlot(2,"EXP " + sPlayer.getExp(),"");
                            scoreboardManager.setSlot(3,"MXP " + sPlayer.getMaxExp(),"");
                            scoreboardManager.setSlot(4,"ATR " + sPlayer.getAttributePoints(),"");
                            scoreboardManager.setSlot(5,"STR " + sPlayer.getStrengthPoints(),"");
                            scoreboardManager.setSlot(6,"INT " + sPlayer.getIntelligencePoints(),"");
                            scoreboardManager.setSlot(7,"CON " + sPlayer.getConstitutionPoints(),"");
                            scoreboardManager.setSlot(8,"DEX " + sPlayer.getDexterityPoints(),"");
                            scoreboardManager.setSlot(9,"CHA " + sPlayer.getCharismaPoints(),"");
                            scoreboardManager.setSlot(10,"WIS " + sPlayer.getWisdomPoints(),"");
                            scoreboardManager.setSlot(11,"LUK " + sPlayer.getLuckPoints(),"");
                        }else if (!(player.isOnline())){
                            this.cancel();
                        }
                    }
                }.runTaskTimer(SavageRPG.getInstance(), 5L, 5L);
            }
            if (args[0].equals("save")){
                sPlayer.saveData();
            }
        }
        return false;

    }
}
