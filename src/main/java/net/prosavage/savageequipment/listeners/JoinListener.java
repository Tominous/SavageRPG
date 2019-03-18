package net.prosavage.savageequipment.listeners;

import net.prosavage.savageequipment.SEPlayers;
import net.prosavage.savageequipment.SavageEquipment;
import net.prosavage.savageequipment.data.YAML;
import net.prosavage.savageequipment.utils.Formula;
import net.prosavage.savageequipment.utils.Placeholder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class JoinListener implements Listener {

    YAML YAML = new YAML();
    Placeholder Placeholder = new Placeholder();
    Formula Formula = new Formula();

    @EventHandler
    public void playerJoin(PlayerLoginEvent e){
        Player player = e.getPlayer();
        if (!(YAML.fileExist(player))) {
            YAML.createFile(player);
        }

        SEPlayers players = new SEPlayers(player);

        double evaluated = Formula.eval(Placeholder.getPlayerPlaceholders(player, String.valueOf(SavageEquipment.getInstance().getConfig().get("formulas.exp"))));
        YAML.setMaxExp(player, evaluated);

        players.setSELevel(Integer.valueOf(YAML.getData(player, "level")));
        players.setSEExp(Double.valueOf(YAML.getData(player, "exp")));
        players.setSEMaxExp(Double.valueOf(YAML.getData(player, "max-exp")));
        players.setSESkillpoints(Integer.valueOf(YAML.getData(player, "skill-points")));

        SavageEquipment.getInstance().sendConsole(String.valueOf(evaluated));
        SavageEquipment.getInstance().sendConsole(String.valueOf(YAML.getData(player, "max-exp")));

    }

}
