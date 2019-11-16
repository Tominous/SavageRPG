package net.prosavage.illyriarpg.chatmenu;

import me.tom.sparse.spigot.chat.menu.ChatMenu;
import me.tom.sparse.spigot.chat.menu.element.ButtonElement;
import me.tom.sparse.spigot.chat.menu.element.InputElement;
import me.tom.sparse.spigot.chat.menu.element.TextElement;
import net.prosavage.illyriarpg.IllyriaRPG;
import net.prosavage.illyriarpg.api.files.IWeaponFiles;
import net.prosavage.illyriarpg.utils.Color;
import net.prosavage.illyriarpg.utils.Number;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class WeaponFileEditor {

    net.prosavage.illyriarpg.utils.Number Number = new Number();
    net.prosavage.illyriarpg.api.files.IWeaponFiles IWeaponFiles = new IWeaponFiles();
    net.prosavage.illyriarpg.utils.Color Color = new Color();

    public void getWeaponCreateMenu(Player player, int page) {
        ChatMenu chatMenu = new ChatMenu();
        int i = 1;
        int maxNumber = page * 10;
        int minNumber = page * 10 - 11;
        ArrayList<String> weaponNames = new ArrayList<>();
        for (String r : IWeaponFiles.getRarityNames()){
            for (String a : IWeaponFiles.getRarityWeaponNames(r)){
                weaponNames.add("[" + i + "] [" + r + "] [" + a.replace(".yml", "") + "]");
                i = i + 1;
            }
        }
        IllyriaRPG.getInstance().sendConsole(String.valueOf(weaponNames));
        int maxPage = (int) Math.floor(Math.floor(i) / 10);
        int minPage = 0;
        i = 0;
        for (int ab = 0; ab < minNumber + 11 ; ab++) {
            if ((minNumber < maxNumber) && (ab > minNumber)) {
                if ((ab < weaponNames.size() - 1) && (weaponNames.size() < 11)) {
                    i = i + 1;
                    String[] split = weaponNames.get(ab).split("] \\[");
                    int rankNumb = Integer.parseInt(split[0].replace("[", "").replace("]", ""));
                    String rarity = split[1].replace("[", "").replace("]", "");
                    String name = split[2].replace("[", "").replace("]", "");
                    chatMenu.add(basicRequirement(chatMenu, player, 0, 5 + i, weaponNames.get(ab), rarity, name, i, page, 1));
                }else if (weaponNames.size() > 10) {
                    i = i + 1;
                    String[] split = weaponNames.get(ab).split("] \\[");
                    int rankNumb = Integer.parseInt(split[0].replace("[", "").replace("]", ""));
                    String rarity = split[1].replace("[", "").replace("]", "");
                    String name = split[2].replace("[", "").replace("]", "");
                    chatMenu.add(basicRequirement(chatMenu, player, 0, 5 + i, weaponNames.get(ab), rarity, name, i, page, 1));
                }
            }
        }
        chatMenu.add(getCloseMenu(chatMenu, player));
        if (page < maxPage) {
            chatMenu.add(forwardToMenuButton(chatMenu, player, page));
        }
        if (page - 1 > minPage) {
            chatMenu.add(backToMenuButton(chatMenu, player, page));
        }
        chatMenu.setPauseChat(true);
        chatMenu.openFor(player);
    }

    public ButtonElement getCloseMenu(ChatMenu menu, Player player){
        return new ButtonElement(100, 18, ChatColor.RED + "[Close]", player1 -> {
            menu.setPauseChat(false);
            menu.close(player);
            for (int i = 0; i < 200; i++){
                player.sendMessage("\n");
            }
        });
    }

    public ButtonElement goBackToWeaponFileList(ChatMenu menu, Player player, int page){
        return new ButtonElement(100, 18, ChatColor.GREEN + "[File menu]", player1 -> {
            menu.setPauseChat(false);
            menu.close(player);
            getWeaponCreateMenu(player, page);
        });
    }

    public ButtonElement basicRequirement(ChatMenu menu, Player player, int x, int y, String text, String rarity, String name, int slotNumber, int page, int pageForThisMenu) {
        return new ButtonElement(x, y, ChatColor.GREEN + text, player1 -> {
            new BukkitRunnable(){
                @Override
                public void run() {
                    goToBasicRequirementMenu(menu, player, pageForThisMenu, text, rarity, name, slotNumber, page);
                }
            }.runTaskLater(IllyriaRPG.getInstance(), 1L);
        });
    }

    public void goToBasicRequirementMenu(ChatMenu menu, Player player, int pageForThisMenu, String text, String rarity, String name, int slotNumber, int page) {
        menu.setPauseChat(false);
        menu.close(player);
        ChatMenu menu2 = new ChatMenu();
        if (pageForThisMenu <= 1) {
            menu2.add(new TextElement(0, 10, "Level input: "));
            menu2.add(new TextElement(0, 12, "Minimum damage: "));
            menu2.add(new TextElement(0, 14, "Maximum damage: "));
            menu2.add(new TextElement(0, 16, "Cooldown: "));
            menu2.add(weaponLevelInput("" + IWeaponFiles.getMinimumLevel(rarity, name), rarity, name));
            menu2.add(minimumWeaponDamageInput("" + IWeaponFiles.getMinimumDamage(rarity, name), rarity, name));
            menu2.add(maximumWeaponDamageInput("" + IWeaponFiles.getMaximumDamage(rarity, name), rarity, name));
            menu2.add(weaponAttackCooldown("" + IWeaponFiles.getCooldown(rarity, name), rarity, name));
            menu2.add(basicRequirement(menu2, player, 200, 18, "»", rarity, name, slotNumber, page, pageForThisMenu + 1));
        }
        if (pageForThisMenu == 2) {
            menu2.add(new TextElement(0, 10, "Minimum Scrolls: "));
            menu2.add(new TextElement(0, 12, "Maximum Scrolls: "));
            menu2.add(new TextElement(0, 14, "Minimum Gems: "));
            menu2.add(new TextElement(0, 16, "Maximum Gems: "));
            menu2.add(minimumScrollAmountInput("" + IWeaponFiles.getMinimumScroll(rarity, name), rarity, name));
            menu2.add(minimumGemAmountInput("" + IWeaponFiles.getMinimumGem(rarity, name), rarity, name));
            menu2.add(maximumScrollAmountInput("" + IWeaponFiles.getMaximumScroll(rarity, name), rarity, name));
            menu2.add(maximumGemAmountInput("" + IWeaponFiles.getMaximumGem(rarity, name), rarity, name));
            menu2.add(basicRequirement(menu2, player, 200, 18, "»", rarity, name, slotNumber, page, pageForThisMenu + 1));
            menu2.add(basicRequirement(menu2, player, 0, 18, "«", rarity, name, slotNumber, page, pageForThisMenu - 1));
        }
        if (pageForThisMenu == 3) {
            menu2.add(new TextElement(0, 8, "Ability name: "));
            menu2.add(new TextElement(0, 10, "Ability cast type: "));
            menu2.add(new TextElement(0, 12, "Ability action type: "));
            menu2.add(new TextElement(0, 14, "Ability cooldown: "));
            menu2.add(new TextElement(0, 16, "Ability mana cost: "));
            menu2.add(abilityNameInput("" + IWeaponFiles.getAbility(rarity, name), rarity, name));
            menu2.add(abilityCastTypeInput("" + IWeaponFiles.getAbilityCastType(rarity, name), rarity, name));
            menu2.add(abilityActionTypeInput("" + IWeaponFiles.getAbilityActionType(rarity, name), rarity, name));
            menu2.add(abilityCooldownInput("" + IWeaponFiles.getAbilityCooldown(rarity, name), rarity, name));
            menu2.add(abilityManaCostInput("" + IWeaponFiles.getAbilityManaCost(rarity, name), rarity, name));
            menu2.add(basicRequirement(menu2, player, 0, 18, "«", rarity, name, slotNumber, page, pageForThisMenu - 1));
        }
        if (page > 0){
            menu2.add(goBackToWeaponFileList(menu2, player, page));
        }
        menu2.setPauseChat(true);
        new BukkitRunnable(){
            @Override
            public void run() {
                menu2.openFor(player);
            }
        }.runTaskLater(IllyriaRPG.getInstance(), 1L);
    }

    public ButtonElement backToMenuButton(ChatMenu menu, Player player, int page) {
        return new ButtonElement(0, 18, ChatColor.RED + "«", player1 -> {
            menu.setPauseChat(false);
            menu.close(player);
            new BukkitRunnable() {
                @Override
                public void run() {
                    getWeaponCreateMenu(player, page - 1);
                }
            }.runTaskLater(IllyriaRPG.getInstance(), 1L);
        });
    }

    public ButtonElement forwardToMenuButton(ChatMenu menu, Player player, int page) {
        return new ButtonElement(200, 18, ChatColor.RED + "»", player1 -> {
            menu.setPauseChat(false);
            menu.close(player);
            new BukkitRunnable() {
                @Override
                public void run() {
                    getWeaponCreateMenu(player, page + 1);
                }
            }.runTaskLater(IllyriaRPG.getInstance(), 1L);
        });
    }

    public InputElement weaponLevelInput(String value, String rarityName, String fileName) {
        InputElement weaponLevelInput = new InputElement(100, 10, 100, value);
        weaponLevelInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if ((currentValue != null) && Number.isParsableAsInt(currentValue)) {
                int currentValueInt = Integer.parseInt(currentValue);
                IWeaponFiles.setMinimumLevel(rarityName, fileName, currentValueInt);
            }
            System.out.println("Input Element changed! " + state.getPrevious() + " -> " + state.getCurrent());
        });
        return weaponLevelInput;
    }

    public InputElement minimumWeaponDamageInput(String value, String rarityName, String fileName) {
        InputElement weaponDamageInput = new InputElement(100, 12, 100, value);
        weaponDamageInput.value.setChangeCallback(state -> {
            double currentValueDouble = Double.parseDouble(state.getCurrent());
            if (currentValueDouble > -1) {
                IWeaponFiles.setMinimumDamage(rarityName, fileName, currentValueDouble);
            }
            System.out.println("Input Element changed! " + state.getPrevious() + " -> " + state.getCurrent());
        });
        return weaponDamageInput;
    }

    public InputElement maximumWeaponDamageInput(String value, String rarityName, String fileName) {
        InputElement weaponDamageInput = new InputElement(100, 14, 100, value);
        weaponDamageInput.value.setChangeCallback(state -> {
            double currentValueDouble = Double.parseDouble(state.getCurrent());
            if (currentValueDouble > -1) {
                IWeaponFiles.setMaximumDamage(rarityName, fileName, currentValueDouble);
            }
            System.out.println("Input Element changed! " + state.getPrevious() + " -> " + state.getCurrent());
        });
        return weaponDamageInput;
    }

    public InputElement weaponAttackCooldown(String value, String rarityName, String fileName) {
        InputElement weaponAttackCooldownInput = new InputElement(100, 16, 100, value);
        weaponAttackCooldownInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if ((currentValue != null) && Number.isParsableAsDouble(currentValue)) {
                double currentValueDouble = Double.parseDouble(currentValue);
                IWeaponFiles.setAttackCooldown(rarityName, fileName, currentValueDouble);
            }
            System.out.println("Input Element changed! " + state.getPrevious() + " -> " + state.getCurrent());
        });
        return weaponAttackCooldownInput;
    }

    public InputElement minimumScrollAmountInput(String value, String rarityName, String fileName) {
        InputElement scrollAmountInput = new InputElement(100, 10, 100, value);
        scrollAmountInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if ((currentValue != null) && Number.isParsableAsInt(currentValue)) {
                int currentValueInt = Integer.parseInt(currentValue);
                IWeaponFiles.setMinimumScroll(rarityName, fileName, currentValueInt);
            }
            System.out.println("Input Element changed! " + state.getPrevious() + " -> " + state.getCurrent());
        });
        return scrollAmountInput;
    }

    public InputElement maximumScrollAmountInput(String value, String rarityName, String fileName) {
        InputElement scrollAmountInput = new InputElement(100, 12, 100, value);
        scrollAmountInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if ((currentValue != null) && Number.isParsableAsInt(currentValue)) {
                int currentValueInt = Integer.parseInt(currentValue);
                IWeaponFiles.setMaximumScroll(rarityName, fileName, currentValueInt);
            }
            System.out.println("Input Element changed! " + state.getPrevious() + " -> " + state.getCurrent());
        });
        return scrollAmountInput;
    }

    public InputElement minimumGemAmountInput(String value, String rarityName, String fileName) {
        InputElement gemAmountInput = new InputElement(100, 14, 100, value);
        gemAmountInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if ((currentValue != null) && Number.isParsableAsInt(currentValue)) {
                int currentValueInt = Integer.parseInt(currentValue);
                IWeaponFiles.setMinimumGem(rarityName, fileName, currentValueInt);
            }
            System.out.println("Input Element changed! " + state.getPrevious() + " -> " + state.getCurrent());
        });
        return gemAmountInput;
    }

    public InputElement maximumGemAmountInput(String value, String rarityName, String fileName) {
        InputElement gemAmountInput = new InputElement(100, 16, 100, value);
        gemAmountInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if ((currentValue != null) && Number.isParsableAsInt(currentValue)) {
                int currentValueInt = Integer.parseInt(currentValue);
                IWeaponFiles.setMaximumGem(rarityName, fileName, currentValueInt);
            }
            System.out.println("Input Element changed! " + state.getPrevious() + " -> " + state.getCurrent());
        });
        return gemAmountInput;
    }

    public InputElement abilityNameInput(String value, String rarityName, String fileName) {
        InputElement abilityNameInput = new InputElement(100, 6, 100, value);
        abilityNameInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if (currentValue != null) {
                IWeaponFiles.setAbility(rarityName, fileName, currentValue);
            }
        });
        return abilityNameInput;
    }

    public InputElement abilityCastTypeInput(String value, String rarityName, String fileName) {
        InputElement abilityCastTypeInput = new InputElement(100, 8, 100, value);
        abilityCastTypeInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if (currentValue != null) {
                IWeaponFiles.setAbilityCastType(rarityName, fileName, currentValue);
            }
        });
        return abilityCastTypeInput;
    }

    public InputElement abilityActionTypeInput(String value, String rarityName, String fileName) {
        InputElement abilityActionTypeInput = new InputElement(100, 10, 100, value);
        abilityActionTypeInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if (currentValue != null) {
                IWeaponFiles.setAbilityActionType(rarityName, fileName, currentValue);
            }
        });
        return abilityActionTypeInput;
    }

    public InputElement abilityCooldownInput(String value, String rarityName, String fileName) {
        InputElement abilityCooldownInput = new InputElement(100, 12, 100, value);
        abilityCooldownInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if ((currentValue != null) && Number.isParsableAsInt(currentValue)) {
                int currentValueInt = Integer.parseInt(currentValue);
                IWeaponFiles.setAbilityCooldown(rarityName, fileName, String.valueOf(currentValueInt));
            }
        });
        return abilityCooldownInput;
    }

    public InputElement abilityManaCostInput(String value, String rarityName, String fileName) {
        InputElement abilityManaCostInput = new InputElement(100, 14, 100, value);
        abilityManaCostInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if ((currentValue != null) && Number.isParsableAsInt(currentValue)) {
                double currentValueDouble = Double.parseDouble(currentValue);
                IWeaponFiles.setAbilityCooldown(rarityName, fileName, String.valueOf(currentValueDouble));
            }
        });
        return abilityManaCostInput;
    }


}
