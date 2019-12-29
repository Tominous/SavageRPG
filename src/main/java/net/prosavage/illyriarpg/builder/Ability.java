package net.prosavage.illyriarpg.builder;

import net.prosavage.illyriarpg.IllyriaRPG;
import net.prosavage.illyriarpg.utils.Config;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Ability {

    private final String name;
    private final double mana;
    private final double cooldown;
    private List<String> description;
    private Consumer<PlayerInteractEvent> leftAction;
    private Consumer<PlayerInteractEvent> rightAction;
    private Consumer<PlayerDropItemEvent> dropAction;
    private boolean hasLeftAction = false;
    private boolean hasRightAction = false;
    private boolean hasDropAction = false;

    public Ability(String name, double mana, double cooldown, String description){
        this.name = name;
        this.mana = mana;
        this.cooldown = cooldown;
        this.description = Arrays.asList(description.split("\\|\\|"));
    }

    public String getName() {
        return name;
    }

    public double getMana() {
        return mana;
    }

    public double getCooldown() {
        return cooldown;
    }

    public Consumer<PlayerInteractEvent> getLeftAction() {
        return this.leftAction;
    }

    public Ability addLeftClickInteraction(Consumer<PlayerInteractEvent> action){
        this.leftAction = action;
        this.hasLeftAction = true;
        return this;
    }

    public Consumer<PlayerInteractEvent> getRightAction() {
        return this.rightAction;
    }

    public Ability addRightClickInteraction(Consumer<PlayerInteractEvent> action){
        this.rightAction = action;
        this.hasRightAction = true;
        return this;
    }

    public Consumer<PlayerInteractEvent> getDropAction() {
        return this.leftAction;
    }

    public Ability addItemDropInteraction(Consumer<PlayerDropItemEvent> action){
        this.dropAction = action;
        this.hasDropAction = true;
        return this;
    }

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public Ability build(){
        File file = new File(IllyriaRPG.getInstance().getDataFolder(), "abilities\\" + name + ".yml");
        if (!(file.exists())){
            Config config = new Config(IllyriaRPG.getInstance(), "abilities\\" + name + ".yml");
            config.set("ability-name", this.name);
            config.set("ability-mana-cost", this.mana);
            config.set("ability-cooldown", this.cooldown);
            config.set("ability-description", this.description);
            config.set("ability-left-click-action", this.hasLeftAction);
            config.set("ability-right-click-action", this.hasRightAction);
            config.set("ability-drop-action", this.hasDropAction);
            config.saveFile();
        }
        if (this.hasLeftAction){
            IllyriaRPG.getInstance().getLeftClickInteractions().put(name, leftAction);
        }
        if (this.hasRightAction){
            IllyriaRPG.getInstance().getRightClickInteractions().put(name, rightAction);
        }
        if (this.hasDropAction){
            IllyriaRPG.getInstance().getItemDropInteractions().put(name, dropAction);
        }
        return this;
    }

}
