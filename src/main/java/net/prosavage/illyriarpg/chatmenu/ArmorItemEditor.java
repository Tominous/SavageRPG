package net.prosavage.illyriarpg.chatmenu;

import me.tom.sparse.spigot.chat.menu.ChatMenu;
import me.tom.sparse.spigot.chat.menu.element.BooleanElement;
import me.tom.sparse.spigot.chat.menu.element.ButtonElement;
import me.tom.sparse.spigot.chat.menu.element.InputElement;
import me.tom.sparse.spigot.chat.menu.element.TextElement;
import net.prosavage.illyriarpg.IllyriaRPG;
import net.prosavage.illyriarpg.api.ICreator;
import net.prosavage.illyriarpg.api.keys.INamespacedKeys;
import net.prosavage.illyriarpg.builder.Armor;
import net.prosavage.illyriarpg.utils.Color;
import net.prosavage.illyriarpg.utils.INumber;
import net.prosavage.illyriarpg.utils.NullValues;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

public class ArmorItemEditor {


    private net.prosavage.illyriarpg.utils.INumber INumber = new INumber();
    private net.prosavage.illyriarpg.utils.Color Color = new Color();
    private net.prosavage.illyriarpg.utils.NullValues NullValues = new NullValues();

    private ButtonElement goToArmorCreateMenu(ChatMenu menu, Player player, int x, int y, String text, int page){
        return new ButtonElement(x, y, ChatColor.GREEN + text, player1 -> {
            menu.setPauseChat(false);
            menu.close(player);
            new BukkitRunnable(){
                @Override
                public void run() {
                    getArmorCreateMenu(player, page);
                }
            }.runTaskLater(IllyriaRPG.getInstance(), 1L);
        });
    }

    public void getArmorCreateMenu(Player player, int page){
        ICreator iCreator = new ICreator(player);
        ChatMenu chatMenu = new ChatMenu();
        chatMenu.add(closeDefaultMenu(chatMenu, player));
        chatMenu.add(new TextElement(0, 16, "Give item? "));
        chatMenu.add(giveItem(player));
        if (page <= 1) {
            chatMenu.add(new TextElement(0, 8, "Armor name: "));
            chatMenu.add(new TextElement(0, 10, "Item rarity: "));
            chatMenu.add(new TextElement(0, 12, "Actual item type: "));
            chatMenu.add(new TextElement(0, 14, "Display item type: "));
            chatMenu.add(armorFileNameInput(player, Color.ify((String) NullValues.replaceNullValues(iCreator.getPersistentArmorFileName()))));
            chatMenu.add(armorRarityInput(player, (String) NullValues.replaceNullValues(iCreator.getPersistentArmorRarityName())));
            chatMenu.add(armorMaterialTypeInput(player, (String) NullValues.replaceNullValues(iCreator.getPersistentArmorMaterialType())));
            chatMenu.add(armorDisplayMaterialTypeInput(player, (String) NullValues.replaceNullValues(iCreator.getPersistentArmorBackgroundMaterialType())));
            chatMenu.add(goToArmorCreateMenu(chatMenu, player, 200, 18, "»", page + 1));
        }
        if (page == 2){
            chatMenu.add(new TextElement(0, 2, "Level input: "));
            chatMenu.add(new TextElement(0, 4, "Minimum protection: "));
            chatMenu.add(new TextElement(0, 6, "Maximum protection: "));
            chatMenu.add(new TextElement(0, 8, "Minimum health: "));
            chatMenu.add(new TextElement(0, 10, "Maximum health: "));
            chatMenu.add(new TextElement(0, 12, "Minimum regen: "));
            chatMenu.add(new TextElement(0, 14, "Maximum regen: "));
            chatMenu.add(armorLevelInput(player, String.valueOf(NullValues.replaceNullValues(iCreator.getPersistentArmorLevelInput()))));
            chatMenu.add(minimumArmorProtectionInput(player, String.valueOf(NullValues.replaceNullValues(iCreator.getPersistentArmorMinimumProtectionInput()))));
            chatMenu.add(maximumArmorProtectionInput(player, String.valueOf(NullValues.replaceNullValues(iCreator.getPersistentArmorMaximumProtectionInput()))));
            chatMenu.add(minimumArmorHealthInput(player, String.valueOf(NullValues.replaceNullValues(iCreator.getPersistentArmorMinimumHealthInput()))));
            chatMenu.add(maximumArmorHealthInput(player, String.valueOf(NullValues.replaceNullValues(iCreator.getPersistentArmorMaximumHealthInput()))));
            chatMenu.add(minimumArmorRegenInput(player, String.valueOf(NullValues.replaceNullValues(iCreator.getPersistentArmorMinimumRegenInput()))));
            chatMenu.add(maximumArmorRegenInput(player, String.valueOf(NullValues.replaceNullValues(iCreator.getPersistentArmorMaximumRegenInput()))));
            chatMenu.add(goToArmorCreateMenu(chatMenu, player, 200, 18, "»", page + 1));
            chatMenu.add(goToArmorCreateMenu(chatMenu, player, 0, 18, "«", page - 1));
        }
        if (page == 3){
            chatMenu.add(new TextElement(0, 12, "Gem(s): "));
            chatMenu.add(new TextElement(0, 14, "Scroll(s): "));
            chatMenu.add(gemAmountInput(player, String.valueOf(NullValues.replaceNullValues(iCreator.getPersistentArmorGemAmountInput()))));
            chatMenu.add(scrollAmountInput(player, String.valueOf(NullValues.replaceNullValues(iCreator.getPersistentArmorScrollAmountInput()))));
            chatMenu.add(goToArmorCreateMenu(chatMenu, player, 200, 18, "»", page + 1));
            chatMenu.add(goToArmorCreateMenu(chatMenu, player, 0, 18, "«", page - 1));
        }
        if (page == 4){
            chatMenu.add(new TextElement(0, 2, "Lore description"));
            chatMenu.add(new TextElement(0, 4, "Use || to for new lines i.e. a||b"));
            chatMenu.add(itemBackgroundLoreOne(player, (String) NullValues.replaceNullValues(iCreator.getPersistentArmorBackgroundLoreOneInput())));
            chatMenu.add(itemBackgroundLoreTwo(player, (String) NullValues.replaceNullValues(iCreator.getPersistentArmorBackgroundLoreTwoInput())));
            chatMenu.add(itemBackgroundLoreThree(player, (String) NullValues.replaceNullValues(iCreator.getPersistentArmorBackgroundLoreThreeInput())));
            chatMenu.add(itemBackgroundLoreFour(player, (String) NullValues.replaceNullValues(iCreator.getPersistentArmorBackgroundLoreFourInput())));
            chatMenu.add(itemBackgroundLoreFive(player, (String) NullValues.replaceNullValues(iCreator.getPersistentArmorBackgroundLoreFiveInput())));
            chatMenu.add(goToArmorCreateMenu(chatMenu, player, 0, 18, "«", page - 1));
        }
        chatMenu.setPauseChat(true);
        chatMenu.openFor(player);
    }

    private ButtonElement closeDefaultMenu(ChatMenu menu, Player player){
        return new ButtonElement(100, 18, ChatColor.RED + "[Close]", player1 -> {
            menu.setPauseChat(false);
            menu.close(player);
            player.getPersistentDataContainer().set(INamespacedKeys.CREATOR_ARMOR_PLAYER, PersistentDataType.STRING, player.getUniqueId().toString());
            ICreator iCreator = new ICreator(player);
            if (iCreator.getPersistentArmorGiveItem() == (byte) 1){
                Armor armor = new Armor(new ItemStack(Material.valueOf(iCreator.getPersistentArmorMaterialType())))
                        .setName(Color.ify(iCreator.getPersistentArmorFileName()))
                        .setRarity(iCreator.getPersistentArmorRarityName())
                        .setLevel(iCreator.getPersistentArmorLevelInput())
                        .setMaterial(iCreator.getPersistentArmorBackgroundMaterialType())
                        .setMinimumProtection(iCreator.getPersistentArmorMinimumProtectionInput())
                        .setMaximumProtection(iCreator.getPersistentArmorMaximumProtectionInput())
                        .setMinimumHealth(iCreator.getPersistentArmorMinimumHealthInput())
                        .setMaximumHealth(iCreator.getPersistentArmorMaximumHealthInput())
                        .setMinimumRegen(iCreator.getPersistentArmorMinimumRegenInput())
                        .setMaximumRegen(iCreator.getPersistentArmorMaximumRegenInput());
                if (iCreator.getPersistentArmorScrollAmountInput() > 0){
                    armor.setScrolls(iCreator.getPersistentArmorScrollAmountInput());
                }
                if (iCreator.getPersistentArmorGemAmountInput() > 0){
                    armor.setGems(iCreator.getPersistentArmorGemAmountInput());
                }
                if (!(NullValues.checkForNullValues(iCreator.getPersistentArmorBackgroundLoreOneInput())
                        || NullValues.checkForNullValues(iCreator.getPersistentArmorBackgroundLoreTwoInput())
                        || NullValues.checkForNullValues(iCreator.getPersistentArmorBackgroundLoreThreeInput())
                        || NullValues.checkForNullValues(iCreator.getPersistentArmorBackgroundLoreFourInput())
                        || NullValues.checkForNullValues(iCreator.getPersistentArmorBackgroundLoreFiveInput()))) {
                    String lineOne = (String) NullValues.replaceNullValues(iCreator.getPersistentArmorBackgroundLoreOneInput());
                    String lineTwo = (String) NullValues.replaceNullValues(iCreator.getPersistentArmorBackgroundLoreTwoInput());
                    String lineThree = (String) NullValues.replaceNullValues(iCreator.getPersistentArmorBackgroundLoreThreeInput());
                    String lineFour = (String) NullValues.replaceNullValues(iCreator.getPersistentArmorBackgroundLoreFourInput());
                    String lineFive = (String) NullValues.replaceNullValues(iCreator.getPersistentArmorBackgroundLoreFiveInput());
                    String description = (lineOne + "||" + lineTwo + "||" + lineThree + "||" + lineFour + "||" + lineFive).replaceAll("\\|\\| \\|\\|", "");
                    armor.setBackgroundLore(description);
                }
                armor.setSpawnedIn((byte) 1);
                armor.setItemCreator(String.valueOf(player.getUniqueId()));
                ItemStack itemStack = armor.build();
                player.getInventory().addItem(itemStack);
                player.getPersistentDataContainer().set(INamespacedKeys.CREATOR_ARMOR_GIVE_ITEM, PersistentDataType.BYTE, (byte)0);
            }
            for (int i = 0; i < 200; i++){
                player.sendMessage("\n");
            }
        });
    }

    private BooleanElement giveItem(Player player) {
        BooleanElement armorGiveItemInput = new BooleanElement(75, 16, false);
        armorGiveItemInput.value.setChangeCallback(state -> {
            byte byteValue = 0;
            Boolean currentValue = state.getCurrent();
            if (currentValue == Boolean.TRUE) {
                byteValue = 1;
            }
            player.getPersistentDataContainer().set(INamespacedKeys.CREATOR_ARMOR_GIVE_ITEM, PersistentDataType.BYTE, byteValue);
        });
        return armorGiveItemInput;
    }

    private InputElement armorFileNameInput(Player player, String value) {
        InputElement armorFile = new InputElement(100, 8, 200, value);
        armorFile.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if (currentValue != null) {
                player.getPersistentDataContainer().set(INamespacedKeys.CREATOR_ARMOR_NAME, PersistentDataType.STRING, currentValue);
            }
        });
        return armorFile;
    }

    private InputElement armorMaterialTypeInput(Player player, String value) {
        InputElement armorMaterialInput = new InputElement(100, 12, 200, value);
        armorMaterialInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if (currentValue != null) {
                player.getPersistentDataContainer().set(INamespacedKeys.CREATOR_ARMOR_MATERIAL_TYPE, PersistentDataType.STRING, currentValue);
            }
        });
        return armorMaterialInput;
    }

    private InputElement armorDisplayMaterialTypeInput(Player player, String value) {
        InputElement armorMaterialInput = new InputElement(100, 14, 200, value);
        armorMaterialInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if (currentValue != null) {
                player.getPersistentDataContainer().set(INamespacedKeys.CREATOR_ARMOR_BACKGROUND_MATERIAL_TYPE, PersistentDataType.STRING, currentValue);
            }
        });
        return armorMaterialInput;
    }

    private InputElement armorRarityInput(Player player, String value) {
        InputElement armorRarityInput = new InputElement(100, 10, 200, value);
        armorRarityInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if (currentValue != null) {
                player.getPersistentDataContainer().set(INamespacedKeys.CREATOR_ARMOR_RARITY, PersistentDataType.STRING, currentValue);
            }
        });
        return armorRarityInput;
    }

    private InputElement armorLevelInput(Player player, String value) {
        InputElement armorLevelInput = new InputElement(100, 2, 100, value);
        armorLevelInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if ((currentValue != null) && INumber.isParsableAsInt(currentValue)) {
                int currentValueInt = Integer.parseInt(currentValue);
                player.getPersistentDataContainer().set(INamespacedKeys.CREATOR_ARMOR_LEVEL, PersistentDataType.INTEGER, currentValueInt);
            }
        });
        return armorLevelInput;
    }

    private InputElement minimumArmorProtectionInput(Player player, String value) {
        InputElement minimumArmorProtectionInput = new InputElement(100, 4, 100, value);
        minimumArmorProtectionInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if (currentValue != null) {
                player.getPersistentDataContainer().set(INamespacedKeys.CREATOR_ARMOR_MINIMUM_PROTECTION, PersistentDataType.DOUBLE, Double.parseDouble(currentValue));
            }
        });
        return minimumArmorProtectionInput;
    }

    private InputElement maximumArmorProtectionInput(Player player, String value) {
        InputElement maximumArmorProtectionInput = new InputElement(100, 6, 100, value);
        maximumArmorProtectionInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if (currentValue != null) {
                player.getPersistentDataContainer().set(INamespacedKeys.CREATOR_ARMOR_MAXIMUM_PROTECTION, PersistentDataType.DOUBLE, Double.parseDouble(currentValue));
            }
        });
        return maximumArmorProtectionInput;
    }

    private InputElement minimumArmorHealthInput(Player player, String value) {
        InputElement minimumArmorHealthInput = new InputElement(100, 8, 100, value);
        minimumArmorHealthInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if (currentValue != null) {
                player.getPersistentDataContainer().set(INamespacedKeys.CREATOR_ARMOR_MINIMUM_HEALTH, PersistentDataType.DOUBLE, Double.parseDouble(currentValue));
            }
        });
        return minimumArmorHealthInput;
    }

    private InputElement maximumArmorHealthInput(Player player, String value) {
        InputElement maximumArmorHealthInput = new InputElement(100, 10, 100, value);
        maximumArmorHealthInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if (currentValue != null) {
                player.getPersistentDataContainer().set(INamespacedKeys.CREATOR_ARMOR_MAXIMUM_HEALTH, PersistentDataType.DOUBLE, Double.parseDouble(currentValue));
            }
        });
        return maximumArmorHealthInput;
    }

    private InputElement minimumArmorRegenInput(Player player, String value) {
        InputElement minimumArmorRegenInput = new InputElement(100, 12, 100, value);
        minimumArmorRegenInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if (currentValue != null) {
                player.getPersistentDataContainer().set(INamespacedKeys.CREATOR_ARMOR_MINIMUM_REGEN, PersistentDataType.DOUBLE, Double.parseDouble(currentValue));
            }
        });
        return minimumArmorRegenInput;
    }

    private InputElement maximumArmorRegenInput(Player player, String value) {
        InputElement maximumArmorRegenInput = new InputElement(100, 14, 100, value);
        maximumArmorRegenInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if (currentValue != null) {
                player.getPersistentDataContainer().set(INamespacedKeys.CREATOR_ARMOR_MAXIMUM_REGEN, PersistentDataType.DOUBLE, Double.parseDouble(currentValue));
            }
        });
        return maximumArmorRegenInput;
    }

    private InputElement gemAmountInput(Player player, String value) {
        InputElement gemAmountInput = new InputElement(100, 12, 100, value);
        gemAmountInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if ((currentValue != null) && INumber.isParsableAsInt(currentValue)) {
                int currentValueInt = Integer.parseInt(currentValue);
                player.getPersistentDataContainer().set(INamespacedKeys.CREATOR_ARMOR_GEM_AMOUNT, PersistentDataType.INTEGER, currentValueInt);
            }
        });
        return gemAmountInput;
    }

    private InputElement scrollAmountInput(Player player, String value) {
        InputElement scrollAmountInput = new InputElement(100, 14, 100, value);
        scrollAmountInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if ((currentValue != null) && INumber.isParsableAsInt(currentValue)) {
                int currentValueInt = Integer.parseInt(currentValue);
                player.getPersistentDataContainer().set(INamespacedKeys.CREATOR_ARMOR_SCROLL_AMOUNT, PersistentDataType.INTEGER, currentValueInt);
            }
        });
        return scrollAmountInput;
    }

    private InputElement itemBackgroundLoreOne(Player player, String value) {
        InputElement itemBackgroundLoreOneInput = new InputElement(0, 6, 250, value);
        itemBackgroundLoreOneInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if (currentValue != null) {
                player.getPersistentDataContainer().set(INamespacedKeys.CREATOR_ARMOR_BACKGROUND_LORE_1, PersistentDataType.STRING, currentValue);
            }
        });
        return itemBackgroundLoreOneInput;
    }

    private InputElement itemBackgroundLoreTwo(Player player, String value) {
        InputElement itemBackgroundLoreTwoInput = new InputElement(0, 8, 250, value);
        itemBackgroundLoreTwoInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if (currentValue != null) {
                player.getPersistentDataContainer().set(INamespacedKeys.CREATOR_ARMOR_BACKGROUND_LORE_2, PersistentDataType.STRING, currentValue);
            }
        });
        return itemBackgroundLoreTwoInput;
    }

    private InputElement itemBackgroundLoreThree(Player player, String value) {
        InputElement itemBackgroundLoreThreeInput = new InputElement(0, 10, 250, value);
        itemBackgroundLoreThreeInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if (currentValue != null) {
                player.getPersistentDataContainer().set(INamespacedKeys.CREATOR_ARMOR_BACKGROUND_LORE_3, PersistentDataType.STRING, currentValue);
            }
        });
        return itemBackgroundLoreThreeInput;
    }

    private InputElement itemBackgroundLoreFour(Player player, String value) {
        InputElement itemBackgroundLoreFourInput = new InputElement(0, 12, 250, value);
        itemBackgroundLoreFourInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if (currentValue != null) {
                player.getPersistentDataContainer().set(INamespacedKeys.CREATOR_ARMOR_BACKGROUND_LORE_4, PersistentDataType.STRING, currentValue);
            }
        });
        return itemBackgroundLoreFourInput;
    }

    private InputElement itemBackgroundLoreFive(Player player, String value) {
        InputElement itemBackgroundLoreFiveInput = new InputElement(0, 14, 250, value);
        itemBackgroundLoreFiveInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if (currentValue != null) {
                player.getPersistentDataContainer().set(INamespacedKeys.CREATOR_ARMOR_BACKGROUND_LORE_5, PersistentDataType.STRING, currentValue);
            }
        });
        return itemBackgroundLoreFiveInput;
    }

}
