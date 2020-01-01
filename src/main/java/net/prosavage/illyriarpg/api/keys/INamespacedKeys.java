package net.prosavage.illyriarpg.api.keys;

import net.prosavage.illyriarpg.IllyriaRPG;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataType;

public final class INamespacedKeys {

    private static final IllyriaRPG PLUGIN = IllyriaRPG.getInstance();

    public static final NamespacedKey WEAPON_NAME = create("weapon_name");
    public static final NamespacedKey WEAPON_MATERIAL = create("weapon_material");
    public static final NamespacedKey WEAPON_RARITY = create("weapon_rarity");
    public static final NamespacedKey WEAPON_CHANCE = create("weapon_chance");
    public static final NamespacedKey WEAPON_BACKGROUND_LORE = create("weapon_background_lore");
    public static final NamespacedKey WEAPON_LEVEL = create("weapon_level");
    public static final NamespacedKey WEAPON_MAXIMUM_DAMAGE = create("weapon_maximum_damage");
    public static final NamespacedKey WEAPON_MINIMUM_DAMAGE = create("weapon_minimum_damage");
    public static final NamespacedKey WEAPON_GEM = create("weapon_gem");
    public static final NamespacedKey WEAPON_SCROLL = create("weapon_scroll");
    public static final NamespacedKey WEAPON_ABIILTY_COOLDOWN = create("weapon_ability_cooldown");
    public static final NamespacedKey WEAPON_ABIILTY_NAME = create("weapon_ability_name");
    public static final NamespacedKey WEAPON_ABIILTY_DESCRIPTION = create("weapon_ability_description");
    public static final NamespacedKey WEAPON_ABIILTY_IS_LEFT_ACTION = create("weapon_ability_is_left_action");
    public static final NamespacedKey WEAPON_ABIILTY_IS_RIGHT_ACTION = create("weapon_ability_is_right_action");
    public static final NamespacedKey WEAPON_ABIILTY_IS_DROP_ACTION = create("weapon_ability_is_droppable_action");
    public static final NamespacedKey WEAPON_ABIILTY_MANA_COST = create("weapon_ability_mana_cost");
    public static final NamespacedKey WEAPON_IS_SPAWNED_IN = create("weapon_is_spawned_in");

    public static final NamespacedKey CREATOR_WEAPON_NAME = create("creator_weapon_name");
    public static final NamespacedKey CREATOR_WEAPON_MATERIAL_TYPE = create("creator_material_type");
    public static final NamespacedKey CREATOR_WEAPON_BACKGROUND_MATERIAL_TYPE = create("creator_background_material_type");
    public static final NamespacedKey CREATOR_WEAPON_RARITY = create("creator_rarity_type");
    public static final NamespacedKey CREATOR_WEAPON_BACKGROUND_LORE = create("creator_weapon_background_lore");
    public static final NamespacedKey CREATOR_WEAPON_BACKGROUND_LORE_1 = create("creator_weapon_background_lore_1");
    public static final NamespacedKey CREATOR_WEAPON_BACKGROUND_LORE_2 = create("creator_weapon_background_lore_2");
    public static final NamespacedKey CREATOR_WEAPON_BACKGROUND_LORE_3 = create("creator_weapon_background_lore_3");
    public static final NamespacedKey CREATOR_WEAPON_BACKGROUND_LORE_4 = create("creator_weapon_background_lore_4");
    public static final NamespacedKey CREATOR_WEAPON_BACKGROUND_LORE_5 = create("creator_weapon_background_lore_5");
    public static final NamespacedKey CREATOR_WEAPON_LEVEL = create("creator_level");
    public static final NamespacedKey CREATOR_WEAPON_MINIMUM_DAMAGE = create("creator_minimum_damage");
    public static final NamespacedKey CREATOR_WEAPON_MAXIMUM_DAMAGE = create("creator_maximum_damage");
    public static final NamespacedKey CREATOR_WEAPON_ATTACK_COOLDOWN = create("creator_attack_cooldown");
    public static final NamespacedKey CREATOR_WEAPON_SCROLL_AMOUNT = create("creator_scroll_amount");
    public static final NamespacedKey CREATOR_WEAPON_GEM_AMOUNT = create("creator_gem_amount");
    public static final NamespacedKey CREATOR_WEAPON_ABILITY_NAME = create("creator_ability_name");
    public static final NamespacedKey CREATOR_WEAPON_GIVE_ITEM = create("creator_give_item");
    public static final NamespacedKey CREATOR_WEAPON_PLAYER = create("creator_weapon_player");

    public static final NamespacedKey ARMOR_NAME = create("armor_name");
    public static final NamespacedKey ARMOR_MATERIAL = create("armor_material");
    public static final NamespacedKey ARMOR_RARITY = create("armor_rarity");
    public static final NamespacedKey ARMOR_CHANCE = create("armor_chance");
    public static final NamespacedKey ARMOR_BACKGROUND_LORE = create("armor_background_lore");
    public static final NamespacedKey ARMOR_LEVEL = create("armor_level");
    public static final NamespacedKey ARMOR_MINIMUM_PROTECTION = create("armor_minimum_protection");
    public static final NamespacedKey ARMOR_MAXIMUM_PROTECTION = create("armor_maximum_protection");
    public static final NamespacedKey ARMOR_PROTECTION = create("armor_protection");
    public static final NamespacedKey ARMOR_MINIMUM_HEALTH = create("armor_minimum_health");
    public static final NamespacedKey ARMOR_MAXIMUM_HEALTH = create("armor_maximum_health");
    public static final NamespacedKey ARMOR_HEALTH = create("armor_health");
    public static final NamespacedKey ARMOR_MINIMUM_REGEN = create("armor_minimum_regen");
    public static final NamespacedKey ARMOR_MAXIMUM_REGEN = create("armor_maximum_regen");
    public static final NamespacedKey ARMOR_REGEN = create("armor_regen");
    public static final NamespacedKey ARMOR_GEM = create("armor_gem");
    public static final NamespacedKey ARMOR_SCROLL = create("armor_scroll");
    public static final NamespacedKey ARMOR_IS_SPAWNED_IN = create("armor_is_spawned_in");

    public static final NamespacedKey CREATOR_ARMOR_NAME = create("creator_armor_name");
    public static final NamespacedKey CREATOR_ARMOR_MATERIAL_TYPE = create("creator_material_type");
    public static final NamespacedKey CREATOR_ARMOR_BACKGROUND_MATERIAL_TYPE = create("creator_background_material_type");
    public static final NamespacedKey CREATOR_ARMOR_RARITY = create("creator_rarity_type");
    public static final NamespacedKey CREATOR_ARMOR_LEVEL = create("creator_level");
    public static final NamespacedKey CREATOR_ARMOR_MINIMUM_PROTECTION = create("creator_armor_minimum_protection");
    public static final NamespacedKey CREATOR_ARMOR_MAXIMUM_PROTECTION = create("creator_armor_maximum_protection");
    public static final NamespacedKey CREATOR_ARMOR_MINIMUM_HEALTH = create("creator_armor_minimum_health");
    public static final NamespacedKey CREATOR_ARMOR_MAXIMUM_HEALTH = create("creator_armor_maximum_health");
    public static final NamespacedKey CREATOR_ARMOR_MINIMUM_REGEN = create("creator_armor_minimum_regen");
    public static final NamespacedKey CREATOR_ARMOR_MAXIMUM_REGEN = create("creator_armor_maximum_regen");
    public static final NamespacedKey CREATOR_ARMOR_ATTACK_COOLDOWN = create("creator_attack_cooldown");
    public static final NamespacedKey CREATOR_ARMOR_SCROLL_AMOUNT = create("creator_scroll_amount");
    public static final NamespacedKey CREATOR_ARMOR_GEM_AMOUNT = create("creator_gem_amount");
    public static final NamespacedKey CREATOR_ARMOR_GIVE_ITEM = create("creator_give_armor");
    public static final NamespacedKey CREATOR_ARMOR_PLAYER = create("creator_armor_player");
    public static final NamespacedKey CREATOR_ARMOR_BACKGROUND_LORE = create("creator_armor_background_lore");
    public static final NamespacedKey CREATOR_ARMOR_BACKGROUND_LORE_1 = create("creator_armor_background_lore_1");
    public static final NamespacedKey CREATOR_ARMOR_BACKGROUND_LORE_2 = create("creator_armor_background_lore_2");
    public static final NamespacedKey CREATOR_ARMOR_BACKGROUND_LORE_3 = create("creator_armor_background_lore_3");
    public static final NamespacedKey CREATOR_ARMOR_BACKGROUND_LORE_4 = create("creator_armor_background_lore_4");
    public static final NamespacedKey CREATOR_ARMOR_BACKGROUND_LORE_5 = create("creator_armor_background_lore_5");

    public static final NamespacedKey[] ALL_ITEM_NAME_SPACED_KEYS = {
            // This is for weapons

            INamespacedKeys.WEAPON_NAME, INamespacedKeys.WEAPON_MATERIAL
            , INamespacedKeys.WEAPON_RARITY, INamespacedKeys.WEAPON_CHANCE, INamespacedKeys.WEAPON_BACKGROUND_LORE
            , INamespacedKeys.WEAPON_LEVEL, INamespacedKeys.WEAPON_MAXIMUM_DAMAGE, INamespacedKeys.WEAPON_MINIMUM_DAMAGE,
            INamespacedKeys.WEAPON_GEM, INamespacedKeys.WEAPON_SCROLL, INamespacedKeys.WEAPON_ABIILTY_COOLDOWN,
            INamespacedKeys.WEAPON_ABIILTY_NAME, INamespacedKeys.WEAPON_ABIILTY_DESCRIPTION,
            INamespacedKeys.WEAPON_ABIILTY_IS_LEFT_ACTION, INamespacedKeys.WEAPON_ABIILTY_IS_RIGHT_ACTION,
            INamespacedKeys.WEAPON_ABIILTY_IS_DROP_ACTION, INamespacedKeys.WEAPON_ABIILTY_MANA_COST,
            INamespacedKeys.WEAPON_IS_SPAWNED_IN, INamespacedKeys.CREATOR_WEAPON_PLAYER,

            // This is for armors
            INamespacedKeys.ARMOR_NAME, INamespacedKeys.ARMOR_MATERIAL, INamespacedKeys.ARMOR_RARITY,
            INamespacedKeys.ARMOR_CHANCE, INamespacedKeys.ARMOR_BACKGROUND_LORE, INamespacedKeys.ARMOR_LEVEL,
            INamespacedKeys.ARMOR_MINIMUM_PROTECTION, INamespacedKeys.ARMOR_MAXIMUM_PROTECTION, INamespacedKeys.ARMOR_PROTECTION,
            INamespacedKeys.ARMOR_MINIMUM_HEALTH, INamespacedKeys.ARMOR_MAXIMUM_HEALTH, INamespacedKeys.ARMOR_HEALTH,
            INamespacedKeys.ARMOR_MINIMUM_REGEN, INamespacedKeys.ARMOR_MAXIMUM_REGEN, INamespacedKeys.ARMOR_REGEN,
            INamespacedKeys.ARMOR_GEM, INamespacedKeys.ARMOR_SCROLL, INamespacedKeys.ARMOR_IS_SPAWNED_IN,
            INamespacedKeys.CREATOR_ARMOR_PLAYER
    };

    public static final PersistentDataType[] ALL_ITEM_DATA_TYPE = {
            // This is for weapons
            PersistentDataType.STRING, PersistentDataType.STRING,
            PersistentDataType.STRING, PersistentDataType.DOUBLE, PersistentDataType.STRING,
            PersistentDataType.INTEGER, PersistentDataType.DOUBLE, PersistentDataType.DOUBLE,
            PersistentDataType.INTEGER, PersistentDataType.INTEGER, PersistentDataType.DOUBLE,
            PersistentDataType.STRING, PersistentDataType.STRING,
            PersistentDataType.BYTE, PersistentDataType.BYTE,
            PersistentDataType.BYTE, PersistentDataType.DOUBLE,
            PersistentDataType.BYTE, PersistentDataType.STRING,

            // This is for armors
            PersistentDataType.STRING, PersistentDataType.STRING, PersistentDataType.STRING,
            PersistentDataType.DOUBLE, PersistentDataType.STRING, PersistentDataType.INTEGER,
            PersistentDataType.DOUBLE, PersistentDataType.DOUBLE, PersistentDataType.DOUBLE,
            PersistentDataType.DOUBLE, PersistentDataType.DOUBLE, PersistentDataType.DOUBLE,
            PersistentDataType.DOUBLE, PersistentDataType.DOUBLE, PersistentDataType.DOUBLE,
            PersistentDataType.INTEGER, PersistentDataType.INTEGER, PersistentDataType.BYTE,
            PersistentDataType.STRING
    };

    private INamespacedKeys() { }

    private static NamespacedKey create(String path) {
        return new NamespacedKey(PLUGIN, "savagerpg_" + path);
    }

    public static NamespacedKey[] getAllItemNamespacedKeys() {
        return ALL_ITEM_NAME_SPACED_KEYS;
    }

    public static PersistentDataType[] getAllItemDataType() {
        return ALL_ITEM_DATA_TYPE;
    }
}