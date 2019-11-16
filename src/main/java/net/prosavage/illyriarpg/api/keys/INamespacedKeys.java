package net.prosavage.illyriarpg.api.keys;

import net.prosavage.illyriarpg.IllyriaRPG;
import org.bukkit.NamespacedKey;

public final class INamespacedKeys {

    private static final IllyriaRPG PLUGIN = IllyriaRPG.getInstance();

    public static final NamespacedKey ITEM_NAME = create("item_name");
    public static final NamespacedKey ITEM_MATERIAL = create("item_material");
    public static final NamespacedKey ITEM_RARITY = create("item_rarity");
    public static final NamespacedKey ITEM_CHANCE = create("item_chance");
    public static final NamespacedKey ITEM_LORE = create("item_lore");
    public static final NamespacedKey ITEM_MAX_LEVEL = create("item_max_level");
    public static final NamespacedKey ITEM_LEVEL = create("item_level");
    public static final NamespacedKey ITEM_MIN_LEVEL = create("item_max_level");
    public static final NamespacedKey ITEM_MAX_DAMAGE = create("item_max_damage");
    public static final NamespacedKey ITEM_MIN_DAMAGE = create("item_min_damage");
    public static final NamespacedKey ITEM_MAX_GEM = create("item_max_gem");
    public static final NamespacedKey ITEM_MIN_GEM = create("item_min_gem");
    public static final NamespacedKey ITEM_GEM = create("item_gem");
    public static final NamespacedKey ITEM_MAX_SCROLL = create("item_max_scroll");
    public static final NamespacedKey ITEM_MIN_SCROLL = create("item_min_scroll");
    public static final NamespacedKey ITEM_SCROLL = create("item_scroll");
    public static final NamespacedKey ITEM_HAVE_SCROLLS = create("item_have_scroll");
    public static final NamespacedKey ITEM_ABIILTY_COOLDOWN = create("item_ability_cooldown");
    public static final NamespacedKey ITEM_ABIILTY_NAME = create("item_ability_name");
    public static final NamespacedKey ITEM_ABIILTY_DESCRIPTION = create("item_ability_description");
    public static final NamespacedKey ITEM_ABIILTY_CAST_TYPE = create("item_ability_cast_type");
    public static final NamespacedKey ITEM_ABIILTY_ACTION_TYPE = create("item_ability_action_type");
    public static final NamespacedKey ITEM_ABIILTY_MANA_COST = create("item_ability_mana_cost");

    public static final NamespacedKey CREATOR_ITEM_WEAPON_FILE_NAME = create("creator_weapon_file_name");
    public static final NamespacedKey CREATOR_ITEM_MATERIAL_TYPE = create("creator_material_type");
    public static final NamespacedKey CREATOR_ITEM_MATERIAL_LORE_TYPE = create("creator_material_lore_type");
    public static final NamespacedKey CREATOR_ITEM_RARITY = create("creator_rarity_type");
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
    public static final NamespacedKey CREATOR_ITEM_SAVE_TO_FILE = create("creator_save_to_file");
    public static final NamespacedKey CREATOR_ITEM_GIVE_ITEM = create("creator_give_item");

    public static final NamespacedKey CREATOR_FILE_WEAPON_FILE_NAME = create("file_weapon_file_name");
    public static final NamespacedKey CREATOR_FILE_MATERIAL_TYPE = create("file_material_type");
    public static final NamespacedKey CREATOR_FILE_MATERIAL_LORE_TYPE = create("file_material_lore_type");
    public static final NamespacedKey CREATOR_FILE_RARITY = create("file_rarity_type");
    public static final NamespacedKey CREATOR_FILE_LEVEL = create("file_level");
    public static final NamespacedKey CREATOR_FILE_MINIMUM_DAMAGE = create("file_minimum_damage");
    public static final NamespacedKey CREATOR_FILE_MAXIMUM_DAMAGE = create("file_maximum_damage");
    public static final NamespacedKey CREATOR_FILE_ATTACK_COOLDOWN = create("file_attack_cooldown");
    public static final NamespacedKey CREATOR_FILE_SCROLL_AMOUNT = create("file_scroll_amount");
    public static final NamespacedKey CREATOR_FILE_GEM_AMOUNT = create("file_gem_amount");
    public static final NamespacedKey CREATOR_FILE_ABILITY_NAME = create("file_ability_name");
    public static final NamespacedKey CREATOR_FILE_ABILITY_CAST_TYPE = create("file_ability_cast_type");
    public static final NamespacedKey CREATOR_FILE_ABILITY_ACTION_TYPE = create("file_ability_action_type");
    public static final NamespacedKey CREATOR_FILE_ABILITY_COOLDOWN = create("file_ability_cooldown");
    public static final NamespacedKey CREATOR_FILE_ABILITY_MANA_COST = create("file_ability_mana_cost");
    public static final NamespacedKey CREATOR_FILE_SAVE_TO_FILE = create("file_save_to_file");
    public static final NamespacedKey CREATOR_FILE_GIVE_ITEM = create("file_give_item");

    private INamespacedKeys() { }

    private static NamespacedKey create(String path) {
        return new NamespacedKey(PLUGIN, "savagerpg_" + path);
    }

}