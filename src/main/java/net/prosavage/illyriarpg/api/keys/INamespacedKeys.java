package net.prosavage.illyriarpg.api.keys;

import net.prosavage.illyriarpg.IllyriaRPG;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataType;

public final class INamespacedKeys {

    private static final IllyriaRPG PLUGIN = IllyriaRPG.getInstance();

    public static final NamespacedKey ITEM_NAME = create("item_name");
    public static final NamespacedKey ITEM_MATERIAL = create("item_material");
    public static final NamespacedKey ITEM_RARITY = create("item_rarity");
    public static final NamespacedKey ITEM_CHANCE = create("item_chance");
    public static final NamespacedKey ITEM_BACKGROUND_LORE = create("item_background_lore");
    public static final NamespacedKey ITEM_LEVEL = create("item_level");
    public static final NamespacedKey ITEM_MAXIMUM_DAMAGE = create("item_maximum_damage");
    public static final NamespacedKey ITEM_MINIMUM_DAMAGE = create("item_minimum_damage");
    public static final NamespacedKey ITEM_GEM = create("item_gem");
    public static final NamespacedKey ITEM_SCROLL = create("item_scroll");
    public static final NamespacedKey ITEM_ABIILTY_COOLDOWN = create("item_ability_cooldown");
    public static final NamespacedKey ITEM_ABIILTY_NAME = create("item_ability_name");
    public static final NamespacedKey ITEM_ABIILTY_DESCRIPTION = create("item_ability_description");
    public static final NamespacedKey ITEM_ABIILTY_CAST_TYPE = create("item_ability_cast_type");
    public static final NamespacedKey ITEM_ABIILTY_ACTION_TYPE = create("item_ability_action_type");
    public static final NamespacedKey ITEM_ABIILTY_MANA_COST = create("item_ability_mana_cost");
    public static final NamespacedKey ITEM_IS_SPAWNED_IN = create("item_is_spawned_in");

    public static final NamespacedKey CREATOR_ITEM_WEAPON_FILE_NAME = create("creator_weapon_file_name");
    public static final NamespacedKey CREATOR_ITEM_MATERIAL_TYPE = create("creator_material_type");
    public static final NamespacedKey CREATOR_ITEM_BACKGROUND_MATERIAL_TYPE = create("creator_background_material_type");
    public static final NamespacedKey CREATOR_ITEM_RARITY = create("creator_rarity_type");
    public static final NamespacedKey CREATOR_ITEM_BACKGROUND_LORE = create("creator_item_background_lore");
    public static final NamespacedKey CREATOR_ITEM_BACKGROUND_LORE_1 = create("creator_item_background_lore_1");
    public static final NamespacedKey CREATOR_ITEM_BACKGROUND_LORE_2 = create("creator_item_background_lore_2");
    public static final NamespacedKey CREATOR_ITEM_BACKGROUND_LORE_3 = create("creator_item_background_lore_3");
    public static final NamespacedKey CREATOR_ITEM_BACKGROUND_LORE_4 = create("creator_item_background_lore_4");
    public static final NamespacedKey CREATOR_ITEM_BACKGROUND_LORE_5 = create("creator_item_background_lore_5");
    public static final NamespacedKey CREATOR_ITEM_LEVEL = create("creator_level");
    public static final NamespacedKey CREATOR_ITEM_MINIMUM_DAMAGE = create("creator_minimum_damage");
    public static final NamespacedKey CREATOR_ITEM_MAXIMUM_DAMAGE = create("creator_maximum_damage");
    public static final NamespacedKey CREATOR_ITEM_ATTACK_COOLDOWN = create("creator_attack_cooldown");
    public static final NamespacedKey CREATOR_ITEM_SCROLL_AMOUNT = create("creator_scroll_amount");
    public static final NamespacedKey CREATOR_ITEM_GEM_AMOUNT = create("creator_gem_amount");
    public static final NamespacedKey CREATOR_ITEM_ABILITY_NAME = create("creator_ability_name");
    public static final NamespacedKey CREATOR_ITEM_ABILITY_CAST_TYPE = create("creator_ability_cast_type");
    public static final NamespacedKey CREATOR_ITEM_ABILITY_ACTION_TYPE = create("creator_ability_action_type");
    public static final NamespacedKey CREATOR_ITEM_ABILITY_COOLDOWN = create("creator_ability_cooldown");
    public static final NamespacedKey CREATOR_ITEM_ABILITY_MANA_COST = create("creator_ability_mana_cost");
    public static final NamespacedKey CREATOR_ITEM_ABILITY_DESCRIPTION = create("creator_ability_description");
    public static final NamespacedKey CREATOR_ITEM_ABILITY_DESCRIPTION_1 = create("creator_ability_description_1");
    public static final NamespacedKey CREATOR_ITEM_ABILITY_DESCRIPTION_2 = create("creator_ability_description_2");
    public static final NamespacedKey CREATOR_ITEM_ABILITY_DESCRIPTION_3 = create("creator_ability_description_3");
    public static final NamespacedKey CREATOR_ITEM_ABILITY_DESCRIPTION_4 = create("creator_ability_description_5");
    public static final NamespacedKey CREATOR_ITEM_ABILITY_DESCRIPTION_5 = create("creator_ability_description_4");
    public static final NamespacedKey CREATOR_ITEM_GIVE_ITEM = create("creator_give_item");
    public static final NamespacedKey CREATOR_ITEM_PLAYER = create("creator_weapon_player");

    public static final NamespacedKey[] ALL_ITEM_NAME_SPACED_KEYS = {INamespacedKeys.ITEM_NAME, INamespacedKeys.ITEM_MATERIAL,
            INamespacedKeys.ITEM_RARITY, INamespacedKeys.ITEM_CHANCE, INamespacedKeys.ITEM_BACKGROUND_LORE, INamespacedKeys.ITEM_LEVEL,
            INamespacedKeys.ITEM_MAXIMUM_DAMAGE, INamespacedKeys.ITEM_MINIMUM_DAMAGE, INamespacedKeys.ITEM_SCROLL, INamespacedKeys.ITEM_GEM,
            INamespacedKeys.ITEM_ABIILTY_COOLDOWN, INamespacedKeys.ITEM_ABIILTY_NAME, INamespacedKeys.ITEM_ABIILTY_DESCRIPTION,
            INamespacedKeys.ITEM_ABIILTY_CAST_TYPE, INamespacedKeys.ITEM_ABIILTY_MANA_COST, INamespacedKeys.ITEM_ABIILTY_ACTION_TYPE,
            INamespacedKeys.ITEM_IS_SPAWNED_IN, INamespacedKeys.CREATOR_ITEM_PLAYER};

    public static final PersistentDataType[] ALL_ITEM_DATA_TYPE = {PersistentDataType.STRING, PersistentDataType.STRING,
            PersistentDataType.STRING, PersistentDataType.DOUBLE, PersistentDataType.STRING, PersistentDataType.INTEGER,
            PersistentDataType.DOUBLE, PersistentDataType.DOUBLE, PersistentDataType.INTEGER, PersistentDataType.INTEGER,
            PersistentDataType.DOUBLE, PersistentDataType.STRING, PersistentDataType.STRING, PersistentDataType.STRING,
            PersistentDataType.DOUBLE, PersistentDataType.STRING, PersistentDataType.BYTE, PersistentDataType.STRING};

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