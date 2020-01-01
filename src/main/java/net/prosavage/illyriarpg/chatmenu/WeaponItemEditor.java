package net.prosavage.illyriarpg.chatmenu;

import me.tom.sparse.spigot.chat.menu.ChatMenu;
import me.tom.sparse.spigot.chat.menu.element.BooleanElement;
import me.tom.sparse.spigot.chat.menu.element.ButtonElement;
import me.tom.sparse.spigot.chat.menu.element.InputElement;
import me.tom.sparse.spigot.chat.menu.element.TextElement;
import net.prosavage.illyriarpg.IllyriaRPG;
import net.prosavage.illyriarpg.api.ICreator;
import net.prosavage.illyriarpg.api.files.IAbilityFiles;
import net.prosavage.illyriarpg.api.keys.INamespacedKeys;
import net.prosavage.illyriarpg.builder.Weapon;
import net.prosavage.illyriarpg.utils.NullValues;
import net.prosavage.illyriarpg.utils.Color;
import net.prosavage.illyriarpg.utils.INumber;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

public class WeaponItemEditor {

    private INumber INumber = new INumber();
    private net.prosavage.illyriarpg.utils.Color Color = new Color();
    private NullValues NullValues = new NullValues();
    private IAbilityFiles IAbilityFiles = new IAbilityFiles();

    private ButtonElement goToWeaponCreateMenu(ChatMenu menu, Player player, int x, int y, String text, int page){
        return new ButtonElement(x, y, ChatColor.GREEN + text, player1 -> {
            menu.setPauseChat(false);
            menu.close(player);
            new BukkitRunnable(){
                @Override
                public void run() {
                    getWeaponCreateMenu(player, page);
                }
            }.runTaskLater(IllyriaRPG.getInstance(), 1L);
        });
    }

    public void getWeaponCreateMenu(Player player, int page){
        ICreator iCreator = new ICreator(player);
        ChatMenu chatMenu = new ChatMenu();
        chatMenu.add(closeDefaultMenu(chatMenu, player));
        chatMenu.add(new TextElement(0, 16, "Give item? "));
        chatMenu.add(giveItem(player));
        if (page <= 1) {
            chatMenu.add(new TextElement(0, 8, "Weapon name: "));
            chatMenu.add(new TextElement(0, 10, "Item rarity: "));
            chatMenu.add(new TextElement(0, 12, "Actual item type: "));
            chatMenu.add(new TextElement(0, 14, "Display item type: "));
            chatMenu.add(weaponFileNameInput(player, Color.ify((String) NullValues.replaceNullValues(iCreator.getPersistentWeaponFileName()))));
            chatMenu.add(weaponRarityInput(player, (String) NullValues.replaceNullValues(iCreator.getPersistentWeaponRarityName())));
            chatMenu.add(weaponMaterialTypeInput(player, (String) NullValues.replaceNullValues(iCreator.getPersistentWeaponMaterialType())));
            chatMenu.add(weaponDisplayMaterialTypeInput(player, (String) NullValues.replaceNullValues(iCreator.getPersistentWeaponBackgroundMaterialType())));
            chatMenu.add(goToWeaponCreateMenu(chatMenu, player, 200, 18, "»", page + 1));
        }
        if (page == 2){
            chatMenu.add(new TextElement(0, 8, "Level input: "));
            chatMenu.add(new TextElement(0, 10, "Minimum damage: "));
            chatMenu.add(new TextElement(0, 12, "Maximum damage: "));
            chatMenu.add(new TextElement(0, 14, "Cooldown: "));
            chatMenu.add(weaponLevelInput(player, String.valueOf(NullValues.replaceNullValues(iCreator.getPersistentWeaponLevelInput()))));
            chatMenu.add(minimumWeaponDamageInput(player, String.valueOf(NullValues.replaceNullValues(iCreator.getPersistentWeaponMinimumDamageInput()))));
            chatMenu.add(maximumWeaponDamageInput(player, String.valueOf(NullValues.replaceNullValues(iCreator.getPersistentWeaponMaximumDamageInput()))));
            chatMenu.add(weaponAttackCooldown(player, String.valueOf(NullValues.replaceNullValues(iCreator.getPersistentWeaponCooldownInput()))));
            chatMenu.add(goToWeaponCreateMenu(chatMenu, player, 200, 18, "»", page + 1));
            chatMenu.add(goToWeaponCreateMenu(chatMenu, player, 0, 18, "«", page - 1));
        }
        if (page == 3){
            chatMenu.add(new TextElement(0, 10, "Gem(s): "));
            chatMenu.add(new TextElement(0, 12, "Scroll(s): "));
            chatMenu.add(new TextElement(0, 14, "Ability name: "));
            chatMenu.add(abilityNameInput(player, (String) NullValues.replaceNullValues(iCreator.getPersistentWeaponAbilityNameInput())));
            chatMenu.add(gemAmountInput(player, String.valueOf(NullValues.replaceNullValues(iCreator.getPersistentWeaponGemAmountInput()))));
            chatMenu.add(scrollAmountInput(player, String.valueOf(NullValues.replaceNullValues(iCreator.getPersistentWeaponScrollAmountInput()))));
            chatMenu.add(goToWeaponCreateMenu(chatMenu, player, 200, 18, "»", page + 1));
            chatMenu.add(goToWeaponCreateMenu(chatMenu, player, 0, 18, "«", page - 1));
        }
        if (page == 4){
            chatMenu.add(new TextElement(0, 2, "Lore description"));
            chatMenu.add(new TextElement(0, 4, "Use || to for new lines i.e. a||b"));
            chatMenu.add(itemBackgroundLoreOne(player, (String) NullValues.replaceNullValues(iCreator.getPersistentWeaponBackgroundLoreOneInput())));
            chatMenu.add(itemBackgroundLoreTwo(player, (String) NullValues.replaceNullValues(iCreator.getPersistentWeaponBackgroundLoreTwoInput())));
            chatMenu.add(itemBackgroundLoreThree(player, (String) NullValues.replaceNullValues(iCreator.getPersistentWeaponBackgroundLoreThreeInput())));
            chatMenu.add(itemBackgroundLoreFour(player, (String) NullValues.replaceNullValues(iCreator.getPersistentWeaponBackgroundLoreFourInput())));
            chatMenu.add(itemBackgroundLoreFive(player, (String) NullValues.replaceNullValues(iCreator.getPersistentWeaponBackgroundLoreFiveInput())));
            chatMenu.add(goToWeaponCreateMenu(chatMenu, player, 0, 18, "«", page - 1));
        }
        chatMenu.setPauseChat(true);
        chatMenu.openFor(player);
    }

    private ButtonElement closeDefaultMenu(ChatMenu menu, Player player){
        return new ButtonElement(100, 18, ChatColor.RED + "[Close]", player1 -> {
            menu.setPauseChat(false);
            menu.close(player);
            player.getPersistentDataContainer().set(INamespacedKeys.CREATOR_WEAPON_PLAYER, PersistentDataType.STRING, player.getUniqueId().toString());
            ICreator iCreator = new ICreator(player);
            if (iCreator.getPersistentWeaponGiveItem() == (byte) 1){
                Weapon weapon = new Weapon(new ItemStack(Material.valueOf(iCreator.getPersistentWeaponMaterialType())))
                        .setName(Color.ify(iCreator.getPersistentWeaponFileName()))
                        .setRarity(iCreator.getPersistentWeaponRarityName())
                        .setLevel(iCreator.getPersistentWeaponLevelInput())
                        .setMaterial(iCreator.getPersistentWeaponBackgroundMaterialType())
                        .setMinimumDamage(iCreator.getPersistentWeaponMinimumDamageInput())
                        .setMaximumDamage(iCreator.getPersistentWeaponMaximumDamageInput())
                        .setAttackCooldown(iCreator.getPersistentWeaponCooldownInput());
                if (iCreator.getPersistentWeaponScrollAmountInput() > 0){
                    weapon.setScrolls(iCreator.getPersistentWeaponScrollAmountInput());
                }
                if (iCreator.getPersistentWeaponGemAmountInput() > 0){
                    weapon.setGems(iCreator.getPersistentWeaponGemAmountInput());
                }
                if (!NullValues.checkForNullValues(iCreator.getPersistentWeaponAbilityNameInput())){
                    String abilityName = IAbilityFiles.getAbilityName(iCreator.getPersistentWeaponAbilityNameInput());
                    if (abilityName != null) {
                        weapon.setAbility(abilityName);
                    }
                }
                if (!(NullValues.checkForNullValues(iCreator.getPersistentWeaponBackgroundLoreOneInput())
                        || NullValues.checkForNullValues(iCreator.getPersistentWeaponBackgroundLoreTwoInput())
                        || NullValues.checkForNullValues(iCreator.getPersistentWeaponBackgroundLoreThreeInput())
                        || NullValues.checkForNullValues(iCreator.getPersistentWeaponBackgroundLoreFourInput())
                        || NullValues.checkForNullValues(iCreator.getPersistentWeaponBackgroundLoreFiveInput()))) {
                    String lineOne = (String) NullValues.replaceNullValues(iCreator.getPersistentWeaponBackgroundLoreOneInput());
                    String lineTwo = (String) NullValues.replaceNullValues(iCreator.getPersistentWeaponBackgroundLoreTwoInput());
                    String lineThree = (String) NullValues.replaceNullValues(iCreator.getPersistentWeaponBackgroundLoreThreeInput());
                    String lineFour = (String) NullValues.replaceNullValues(iCreator.getPersistentWeaponBackgroundLoreFourInput());
                    String lineFive = (String) NullValues.replaceNullValues(iCreator.getPersistentWeaponBackgroundLoreFiveInput());
                    String description = (lineOne + "||" + lineTwo + "||" + lineThree + "||" + lineFour + "||" + lineFive).replaceAll("\\|\\| \\|\\|", "");
                    weapon.setBackgroundLore(description);
                }
                weapon.setSpawnedIn(Boolean.TRUE);
                weapon.setItemCreator(player);
                ItemStack itemStack = weapon.build();
                player.getInventory().addItem(itemStack);
                player.getPersistentDataContainer().set(INamespacedKeys.CREATOR_WEAPON_GIVE_ITEM, PersistentDataType.BYTE, (byte)0);
            }
            for (int i = 0; i < 200; i++){
                player.sendMessage("\n");
            }
        });
    }

    private BooleanElement giveItem(Player player) {
        BooleanElement weaponGiveItemInput = new BooleanElement(75, 16, false);
        weaponGiveItemInput.value.setChangeCallback(state -> {
            byte byteValue = 0;
            Boolean currentValue = state.getCurrent();
            if (currentValue == Boolean.TRUE) {
                byteValue = 1;
            }
            player.getPersistentDataContainer().set(INamespacedKeys.CREATOR_WEAPON_GIVE_ITEM, PersistentDataType.BYTE, byteValue);
        });
        return weaponGiveItemInput;
    }

    private InputElement weaponFileNameInput(Player player, String value) {
        InputElement weaponFile = new InputElement(100, 8, 200, value);
        weaponFile.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if (currentValue != null) {
                player.getPersistentDataContainer().set(INamespacedKeys.CREATOR_WEAPON_NAME, PersistentDataType.STRING, currentValue);
            }
        });
        return weaponFile;
    }

    private InputElement weaponMaterialTypeInput(Player player, String value) {
        InputElement weaponMaterialInput = new InputElement(100, 12, 200, value);
        weaponMaterialInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if (currentValue != null) {
                player.getPersistentDataContainer().set(INamespacedKeys.CREATOR_WEAPON_MATERIAL_TYPE, PersistentDataType.STRING, currentValue);
            }
        });
        return weaponMaterialInput;
    }

    private InputElement weaponDisplayMaterialTypeInput(Player player, String value) {
        InputElement weaponMaterialInput = new InputElement(100, 14, 200, value);
        weaponMaterialInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if (currentValue != null) {
                player.getPersistentDataContainer().set(INamespacedKeys.CREATOR_WEAPON_BACKGROUND_MATERIAL_TYPE, PersistentDataType.STRING, currentValue);
            }
        });
        return weaponMaterialInput;
    }

    private InputElement weaponRarityInput(Player player, String value) {
        InputElement weaponRarityInput = new InputElement(100, 10, 200, value);
        weaponRarityInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if (currentValue != null) {
                player.getPersistentDataContainer().set(INamespacedKeys.CREATOR_WEAPON_RARITY, PersistentDataType.STRING, currentValue);
            }
        });
        return weaponRarityInput;
    }

    private InputElement weaponLevelInput(Player player, String value) {
        InputElement weaponLevelInput = new InputElement(100, 8, 100, value);
        weaponLevelInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if ((currentValue != null) && INumber.isParsableAsInt(currentValue)) {
                int currentValueInt = Integer.parseInt(currentValue);
                player.getPersistentDataContainer().set(INamespacedKeys.CREATOR_WEAPON_LEVEL, PersistentDataType.INTEGER, currentValueInt);
            }
        });
        return weaponLevelInput;
    }

    private InputElement minimumWeaponDamageInput(Player player, String value) {
        InputElement weaponDamageInput = new InputElement(100, 10, 100, value);
        weaponDamageInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if (currentValue != null) {
                player.getPersistentDataContainer().set(INamespacedKeys.CREATOR_WEAPON_MINIMUM_DAMAGE, PersistentDataType.DOUBLE, Double.parseDouble(currentValue));
            }
        });
        return weaponDamageInput;
    }

    private InputElement maximumWeaponDamageInput(Player player, String value) {
        InputElement maximumWeaponDamageInput = new InputElement(100, 12, 100, value);
        maximumWeaponDamageInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if (currentValue != null) {
                player.getPersistentDataContainer().set(INamespacedKeys.CREATOR_WEAPON_MAXIMUM_DAMAGE, PersistentDataType.DOUBLE, Double.parseDouble(currentValue));
            }
        });
        return maximumWeaponDamageInput;
    }

    private InputElement weaponAttackCooldown(Player player, String value) {
        InputElement weaponAttackCooldownInput = new InputElement(100, 14, 100, value);
        weaponAttackCooldownInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if ((currentValue != null) && INumber.isParsableAsDouble(currentValue)) {
                double currentValueDouble = Double.parseDouble(currentValue);
                player.getPersistentDataContainer().set(INamespacedKeys.CREATOR_WEAPON_ATTACK_COOLDOWN, PersistentDataType.DOUBLE, currentValueDouble);
            }
        });
        return weaponAttackCooldownInput;
    }

    private InputElement gemAmountInput(Player player, String value) {
        InputElement gemAmountInput = new InputElement(100, 10, 100, value);
        gemAmountInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if ((currentValue != null) && INumber.isParsableAsInt(currentValue)) {
                int currentValueInt = Integer.parseInt(currentValue);
                player.getPersistentDataContainer().set(INamespacedKeys.CREATOR_WEAPON_GEM_AMOUNT, PersistentDataType.INTEGER, currentValueInt);
            }
        });
        return gemAmountInput;
    }

    private InputElement scrollAmountInput(Player player, String value) {
        InputElement scrollAmountInput = new InputElement(100, 12, 100, value);
        scrollAmountInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if ((currentValue != null) && INumber.isParsableAsInt(currentValue)) {
                int currentValueInt = Integer.parseInt(currentValue);
                player.getPersistentDataContainer().set(INamespacedKeys.CREATOR_WEAPON_SCROLL_AMOUNT, PersistentDataType.INTEGER, currentValueInt);
            }
        });
        return scrollAmountInput;
    }

    private InputElement abilityNameInput(Player player, String value) {
        InputElement abilityNameInput = new InputElement(100, 14, 100, value);
        abilityNameInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if (currentValue != null) {
                player.getPersistentDataContainer().set(INamespacedKeys.CREATOR_WEAPON_ABILITY_NAME, PersistentDataType.STRING, currentValue);
            }
        });
        return abilityNameInput;
    }

    private InputElement itemBackgroundLoreOne(Player player, String value) {
        InputElement itemBackgroundLoreOneInput = new InputElement(0, 6, 250, value);
        itemBackgroundLoreOneInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if (currentValue != null) {
                player.getPersistentDataContainer().set(INamespacedKeys.CREATOR_WEAPON_BACKGROUND_LORE_1, PersistentDataType.STRING, currentValue);
            }
        });
        return itemBackgroundLoreOneInput;
    }

    private InputElement itemBackgroundLoreTwo(Player player, String value) {
        InputElement itemBackgroundLoreTwoInput = new InputElement(0, 8, 250, value);
        itemBackgroundLoreTwoInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if (currentValue != null) {
                player.getPersistentDataContainer().set(INamespacedKeys.CREATOR_WEAPON_BACKGROUND_LORE_2, PersistentDataType.STRING, currentValue);
            }
        });
        return itemBackgroundLoreTwoInput;
    }

    private InputElement itemBackgroundLoreThree(Player player, String value) {
        InputElement itemBackgroundLoreThreeInput = new InputElement(0, 10, 250, value);
        itemBackgroundLoreThreeInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if (currentValue != null) {
                player.getPersistentDataContainer().set(INamespacedKeys.CREATOR_WEAPON_BACKGROUND_LORE_3, PersistentDataType.STRING, currentValue);
            }
        });
        return itemBackgroundLoreThreeInput;
    }

    private InputElement itemBackgroundLoreFour(Player player, String value) {
        InputElement itemBackgroundLoreFourInput = new InputElement(0, 12, 250, value);
        itemBackgroundLoreFourInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if (currentValue != null) {
                player.getPersistentDataContainer().set(INamespacedKeys.CREATOR_WEAPON_BACKGROUND_LORE_4, PersistentDataType.STRING, currentValue);
            }
        });
        return itemBackgroundLoreFourInput;
    }

    private InputElement itemBackgroundLoreFive(Player player, String value) {
        InputElement itemBackgroundLoreFiveInput = new InputElement(0, 14, 250, value);
        itemBackgroundLoreFiveInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if (currentValue != null) {
                player.getPersistentDataContainer().set(INamespacedKeys.CREATOR_WEAPON_BACKGROUND_LORE_5, PersistentDataType.STRING, currentValue);
            }
        });
        return itemBackgroundLoreFiveInput;
    }

}
