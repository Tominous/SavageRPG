package net.prosavage.savagerpg.hooks.permissionsex;

import net.prosavage.savagerpg.SavageRPG;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class PermissionsEX {

    public boolean checkToSeeIfServerHasPermissionsEX(){
        return Bukkit.getServer().getPluginManager().isPluginEnabled("PermissionsEX");
    }

    public void disable(){
        if (checkToSeeIfServerHasPermissionsEX()) {
            SavageRPG.getInstance().sendConsole("PEX detected.");
            final int[] i = {10};
            new BukkitRunnable() {
                public void run() {
                    if (i[0] > 0) {
                        SavageRPG.getInstance().sendConsole("The plugin will disable itself in... " + i[0] + " seconds");
                    } else if (i[0] == 0) {
                        this.cancel();
                        Bukkit.getServer().getPluginManager().disablePlugin(SavageRPG.getInstance());
                    }
                    i[0]--;
                }
            }.runTaskTimer(SavageRPG.getInstance(),0L,20L);
        }
    }

}
