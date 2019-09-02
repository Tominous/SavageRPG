package net.prosavage.savagerpg.api.keys;

import net.prosavage.savagerpg.SavageRPG;
import org.bukkit.NamespacedKey;

public final class SNamespacedKeys {

    private static final SavageRPG PLUGIN = SavageRPG.getInstance();

    public static final NamespacedKey PLAYER_LEVEL = create("player.level");
    public static final NamespacedKey PLAYER_EXP = create("player.exp");
    public static final NamespacedKey PLAYER_MAX_EXP = create("player.max.exp");
    public static final NamespacedKey PLAYER_ATTRIBUTE_POINTS = create("player.attribute.points");
    public static final NamespacedKey PLAYER_STRENGTH_ATTRIBUTES = create("player.strength.attribute");
    public static final NamespacedKey PLAYER_INTELLIGENCE_ATTRIBUTES = create("player.intelligence.attribute");
    public static final NamespacedKey PLAYER_CONSTITUTION_ATTRIBUTES = create("player.constitution.attribute");
    public static final NamespacedKey PLAYER_DEXTERITY_ATTRIBUTES = create("player.dexterity.attribute");
    public static final NamespacedKey PLAYER_CHARISMA_ATTRIBUTES = create("player.charisma.attribute");
    public static final NamespacedKey PLAYER_WISDOM_ATTRIBUTES = create("player.wisdom.attribute");
    public static final NamespacedKey PLAYER_LUCK_ATTRIBUTES = create("player.luck.attribute");
    public static final NamespacedKey PLAYER_RECENT_DAMAGE = create("player.recent.damage");
    public static final NamespacedKey PLAYER_DAMAGE = create("player.damage");
    public static final NamespacedKey PLAYER_DEFENSE = create("player.defense");
    public static final NamespacedKey PLAYER_GEAR_SCORE = create("player.gear.score");
    public static final NamespacedKey PLAYER_MANA = create("player.mana");
    public static final NamespacedKey PLAYER_MAX_MANA = create("player.max.mana");
    public static final NamespacedKey PLAYER_REGENERATION = create("player.regeneration");

    public static final NamespacedKey ITEM_NAME = create("item.name");
    public static final NamespacedKey ITEM_MATERIAL = create("item.material");
    public static final NamespacedKey ITEM_RARITY = create("item.rarity");
    public static final NamespacedKey ITEM_CHANCE = create("item.chance");
    public static final NamespacedKey ITEM_LORE = create("item.lore");
    public static final NamespacedKey ITEM_MAX_LEVEL = create("item.max.level");
    public static final NamespacedKey ITEM_LEVEL = create("item.level");
    public static final NamespacedKey ITEM_MIN_LEVEL = create("item.max.level");
    public static final NamespacedKey ITEM_MAX_DAMAGE = create("item.max.damage");
    public static final NamespacedKey ITEM_MIN_DAMAGE = create("item.min.damage");
    public static final NamespacedKey ITEM_MAX_GEM = create("item.max.gem");
    public static final NamespacedKey ITEM_MIN_GEM = create("item.min.gem");
    public static final NamespacedKey ITEM_GEM = create("item.gem");
    public static final NamespacedKey ITEM_HAVE_GEMS = create("item.have.gems");
    public static final NamespacedKey ITEM_ABIILTY_COOLDOWN = create("item.ability.cooldown");
    public static final NamespacedKey ITEM_ABIILTY_NAME = create("item.ability.name");
    public static final NamespacedKey ITEM_ABIILTY_DESCRIPTION = create("item.ability.description");
    public static final NamespacedKey ITEM_ABIILTY_CAST_TYPE = create("item.ability.cast.type");
    public static final NamespacedKey ITEM_ABIILTY_ACTION_TYPE = create("item.ability.action.type");
    public static final NamespacedKey ITEM_ABIILTY_MANA_COST = create("item.ability.mana.cost");

    public static final NamespacedKey ARMOR_NAME = create("armor.name");
    public static final NamespacedKey ARMOR_MATERIAL = create("armor.material");
    public static final NamespacedKey ARMOR_RARITY = create("armor.rarity");
    public static final NamespacedKey ARMOR_CHANCE = create("armor.chance");
    public static final NamespacedKey ARMOR_LORE = create("armor.lore");
    public static final NamespacedKey ARMOR_MAX_LEVEL = create("armor.max.level");
    public static final NamespacedKey ARMOR_LEVEL = create("armor.level");
    public static final NamespacedKey ARMOR_MIN_LEVEL = create("armor.min.level");
    public static final NamespacedKey ARMOR_MAX_PROTECTION = create("armor.max.protection");
    public static final NamespacedKey ARMOR_PROTECTION = create("armor.protection");
    public static final NamespacedKey ARMOR_MIN_PROTECTION = create("armor.min.protection");
    public static final NamespacedKey ARMOR_MAX_HEALTH = create("armor.max.health");
    public static final NamespacedKey ARMOR_HEALTH = create("armor.health");
    public static final NamespacedKey ARMOR_MIN_HEALTH = create("armor.min.health");
    public static final NamespacedKey ARMOR_MAX_REGEN = create("armor.max.regen");
    public static final NamespacedKey ARMOR_REGEN = create("armor.regen");
    public static final NamespacedKey ARMOR_MIN_REGEN = create("armor.min.regen");
    public static final NamespacedKey ARMOR_MAX_GEM = create("armor.max.gem");
    public static final NamespacedKey ARMOR_GEM = create("armor.gem");
    public static final NamespacedKey ARMOR_HAVE_GEMS = create("armor.have.gems");
    public static final NamespacedKey ARMOR_MIN_GEM = create("armor.min.gem");

    public static final NamespacedKey ENTITY_LEVEL = create("entity.level");
    public static final NamespacedKey ENTITY_MIN_LEVEL = create("entity.min.level");
    public static final NamespacedKey ENTITY_MAX_LEVEL = create("entity.max.level");
    public static final NamespacedKey ENTITY_EXP = create("entity.exp");
    public static final NamespacedKey ENTITY_MIN_EXP = create("entity.min.exp");
    public static final NamespacedKey ENTITY_MAX_EXP = create("entity.max.exp");
    public static final NamespacedKey ENTITY_RECENT_DAMAGE = create("entity.recent.damage");
    public static final NamespacedKey ENTITY_DAMAGE = create("entity.damage");
    public static final NamespacedKey ENTITY_MIN_DAMAGE = create("entity.min.damage");
    public static final NamespacedKey ENTITY_MAX_DAMAGE = create("entity.max.damage");
    public static final NamespacedKey ENTITY_NAME = create("entity.name");
    public static final NamespacedKey ENTITY_HEALTH = create("entity.health");
    public static final NamespacedKey ENTITY_MIN_HEALTH = create("entity.min.health");
    public static final NamespacedKey ENTITY_MAX_HEALTH = create("entity.max.health");
    public static final NamespacedKey ENTITY_DROPS = create("entity.drops");
    public static final NamespacedKey ENTITY_DROP_CHANCE = create("entity.drop.chance");
    public static final NamespacedKey ENTITY_IS_SAVAGERPG_MOB = create("entity.is.savagerpg.mob");
    public static final NamespacedKey ENTITY_DISTANCE = create("entity.distance");
    public static final NamespacedKey ENTITY_OWNER = create("entity.owner");
    public static final NamespacedKey ENTITY_ALREADY_SPAWNED = create("entity.already.spawned");
    public static final NamespacedKey ENTITY_PLAYERS = create("entity.players");
    // so on and so forth


    private SNamespacedKeys() { }

    private static NamespacedKey create(String path) {
        return new NamespacedKey(PLUGIN, "savagerpg." + path);
    }

}