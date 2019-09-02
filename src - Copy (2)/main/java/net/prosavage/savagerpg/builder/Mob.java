package net.prosavage.savagerpg.builder;

import net.prosavage.savagerpg.api.keys.SNamespacedKeys;
import net.prosavage.savagerpg.SavageRPG;
import net.prosavage.savagerpg.utils.Color;
import net.prosavage.savagerpg.utils.Formula;
import net.prosavage.savagerpg.utils.Number;
import net.prosavage.savagerpg.utils.Placeholder;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class Mob {

    Number Number = new Number();
    Placeholder Placeholder = new Placeholder();
    Formula Formula = new Formula();
    Color Color = new Color();

    private String name;
    private String customName;
    private EntityType entityType;
    LivingEntity entity;
    private int level = 1;
    private int minLevel = level;
    private int maxLevel = level;
    private double exp = 1.0;
    private double minExp = exp;
    private double maxExp = exp;
    private double health = 20.0;
    private double minHealth = health;
    private double maxHealth = health;
    private double damage = 0.0;
    private double minDamage = damage;
    private double maxDamage = damage;
    private double distance = 0.1;
    private String alreadySpawned;

    public Mob(EntityType entityType){
        this.entityType = entityType;
        this.name = getDefaultName();
    }

    public Mob(EntityType entityType, String name, int level, double exp, double health, double damage) {
        this.entityType = entityType;
        this.name = name;
        this.level = level;
        this.exp = exp;
        this.health = health;
        this.damage = damage;
    }

    public Mob(LivingEntity entity){
        this.entity = entity;
        this.entityType = entity.getType();
    }

    public Mob(LivingEntity entity, String name, int level, double health, double minHealth, double maxHealth){
        this.entity = entity;
        this.entityType = entity.getType();
        this.level = level;
        this.health = health;
        this.minHealth = minHealth;
        this.maxHealth = maxHealth;
    }

    public Mob(EntityType entityType, String name, int level, int minLevel, int maxLevel, double exp, double minExp, double maxExp, double health, double minHealth, double maxHealth, double damage, double minDamage, double maxDamage){
        this.entityType = entityType;
        this.name = name;
        this.level = level;
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
        this.exp = exp;
        this.minExp = minExp;
        this.maxExp = maxExp;
        this.health = health;
        this.minHealth = minHealth;
        this.maxHealth = maxHealth;
        this.damage = damage;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    public Mob setEntityType(EntityType entityType) {
        this.entityType = entityType;
        return this;
    }

    public Mob setName(String name) {
        this.name = name;
        return this;
    }

    public Mob setLevel(int level) {
        this.level = level;
        return this;
    }

    public Mob setMinLevel(int minLevel) {
        this.minLevel = minLevel;
        return this;
    }

    public Mob setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
        return this;
    }

    public Mob setExp(double exp) {
        this.exp = exp;
        return this;
    }

    public Mob setMinExp(double minExp) {
        this.minExp = minExp;
        return this;
    }

    public Mob setMaxExp(double maxExp) {
        this.maxExp = maxExp;
        return this;
    }

    public Mob setHealth(double health) {
        this.health = health;
        return this;
    }

    public Mob setMinHealth(double minHealth) {
        this.minHealth = minHealth;
        return this;
    }

    public Mob setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
        return this;
    }

    public Mob setDamage(double damage) {
        this.damage = damage;
        return this;
    }

    public Mob setMinDamage(double minDamage) {
        this.minDamage = minDamage;
        return this;
    }

    public Mob setMaxDamage(double maxDamage) {
        this.maxDamage = maxDamage;
        return this;
    }

    public Mob setDistance(double distance) {
        this.distance = distance;
        return this;
    }

    public String getDefaultName(){
        return WordUtils.capitalize(this.entityType.name());
    }

    public EntityType getEntityType() {
        return this.entityType;
    }

    public String getName() {
        return this.name;
    }

    public int getLevel() {
        return this.level;
    }

    public int getMinLevel() {
        return this.minLevel;
    }

    public int getMaxLevel() {
        return this.maxLevel;
    }

    public double getExp() {
        return this.exp;
    }

    public double getMinExp() {
        return this.minExp;
    }

    public double getMaxExp() {
        return this.maxExp;
    }

    public double getHealth() {
        return this.health;
    }

    public double getMinHealth() {
        return this.minHealth;
    }

    public double getMaxHealth() {
        return this.maxHealth;
    }

    public double getDamage() {
        return this.damage;
    }

    public double getMaxDamage() {
        return this.maxDamage;
    }

    public double getMinDamage() {
        return this.minDamage;
    }

    public double getDistance() {
        return this.distance;
    }

    public Mob setPersistentDataContainers(){
        PersistentDataContainer persistentDataContainer = this.entity.getPersistentDataContainer();
        if (this.name == null){
            this.name = getDefaultName();
        }
        persistentDataContainer.set(SNamespacedKeys.ENTITY_NAME, PersistentDataType.STRING,this.name);
        persistentDataContainer.set(SNamespacedKeys.ENTITY_LEVEL,PersistentDataType.INTEGER,this.level);
        persistentDataContainer.set(SNamespacedKeys.ENTITY_MIN_LEVEL,PersistentDataType.INTEGER,this.minLevel);
        persistentDataContainer.set(SNamespacedKeys.ENTITY_MAX_LEVEL,PersistentDataType.INTEGER,this.maxLevel);
        persistentDataContainer.set(SNamespacedKeys.ENTITY_EXP, PersistentDataType.DOUBLE,this.exp);
        persistentDataContainer.set(SNamespacedKeys.ENTITY_MIN_EXP,PersistentDataType.DOUBLE,this.minExp);
        persistentDataContainer.set(SNamespacedKeys.ENTITY_MAX_EXP,PersistentDataType.DOUBLE,this.maxExp);
        persistentDataContainer.set(SNamespacedKeys.ENTITY_HEALTH, PersistentDataType.DOUBLE,this.health);
        persistentDataContainer.set(SNamespacedKeys.ENTITY_MIN_HEALTH,PersistentDataType.DOUBLE,this.minHealth);
        persistentDataContainer.set(SNamespacedKeys.ENTITY_MAX_HEALTH,PersistentDataType.DOUBLE,this.maxHealth);
        persistentDataContainer.set(SNamespacedKeys.ENTITY_DAMAGE, PersistentDataType.DOUBLE,this.damage);
        persistentDataContainer.set(SNamespacedKeys.ENTITY_MIN_DAMAGE,PersistentDataType.DOUBLE,this.minDamage);
        persistentDataContainer.set(SNamespacedKeys.ENTITY_MAX_DAMAGE,PersistentDataType.DOUBLE,this.maxDamage);
        persistentDataContainer.set(SNamespacedKeys.ENTITY_DISTANCE,PersistentDataType.DOUBLE,this.distance);
        persistentDataContainer.set(SNamespacedKeys.ENTITY_ALREADY_SPAWNED,PersistentDataType.STRING,this.alreadySpawned);
        return this;
    }

    public Mob updateData(LivingEntity entity){
        Location location = entity.getLocation();
        double x = location.getX();
        double z = location.getZ();
        double y = location.getY();
        World world = location.getWorld();
        double configX = (double) SavageRPG.getInstance().getConfig().get("mob-spawn.center-x");
        double configZ = (double) SavageRPG.getInstance().getConfig().get("mob-spawn.center-z");
        double configBlocks = (double) SavageRPG.getInstance().getConfig().get("mob-spawn.block-difficulty-increase");
        String configHealth = String.valueOf(SavageRPG.getInstance().getConfig().get("mob-spawn.mob-hp-increase"));
        Location configLocation = new Location(world,configX,y,configZ);
        double distance = location.distance(configLocation) / configBlocks;
        if (distance == 0){
            distance = 1;
        }
        this.distance = distance;
        setPersistentDataContainers();
        this.distance = distance;
        int entityLevel = (int) Math.floor(Double.parseDouble(String.format("%.2f", distance)));
        double health = Double.parseDouble(String.format("%.2f", Formula.eval(Placeholder.parseConfigInfo(Placeholder.parseEntityInfo(entity, configHealth)))));
        double entityDamage = Number.getDouble(this.minDamage, this.maxDamage);
        this.level = entityLevel;
        this.damage = entityDamage;
        this.maxHealth = health;
        this.health = health;
        this.minHealth = health;
        setPersistentDataContainers();
        String configExp = String.valueOf(SavageRPG.getInstance().getConfig().get("formulas.exp-drop"));
        double entityExp = Formula.eval(Placeholder.parseConfigInfo(Placeholder.parseEntityInfo(entity, configExp)));
        SavageRPG.getInstance().sendConsole(Placeholder.parseConfigInfo(Placeholder.parseEntityInfo(entity, configExp)));
        SavageRPG.getInstance().sendConsole("MOB EXP - " + entityExp);
        this.exp = entityExp;
        setPersistentDataContainers();
        this.customName = String.valueOf(Placeholder.parseEntityInfo(entity,String.valueOf(SavageRPG.getInstance().getConfig().get("format.mob-names"))));
        return this;
    }

    public void spawn(World world, double x, double y, double z){
        this.entity = (LivingEntity) world.spawnEntity(new Location(world, x, y, z), entityType);
        this.alreadySpawned = "true";
        updateData(this.entity);
        Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(this.health);
        entity.setHealth(this.health);
        setPersistentDataContainers();
        this.customName = String.valueOf(Placeholder.parseEntityInfo(entity,String.valueOf(SavageRPG.getInstance().getConfig().get("format.mob-names"))));
        entity.setCustomName(Color.ify(this.customName));
        entity.setCustomNameVisible(true);
    }

    public void spawnExistingEntity(){
        this.alreadySpawned = "true";
        updateData(this.entity);
        Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(this.health);
        entity.setHealth(this.health);
        setPersistentDataContainers();
        this.customName = String.valueOf(Placeholder.parseEntityInfo(entity,String.valueOf(SavageRPG.getInstance().getConfig().get("format.mob-names"))));
        entity.setCustomName(Color.ify(this.customName));
        entity.setCustomNameVisible(true);
    }

}
