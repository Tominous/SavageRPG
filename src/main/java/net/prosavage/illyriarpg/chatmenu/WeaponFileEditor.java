package net.prosavage.illyriarpg.chatmenu;

import me.tom.sparse.spigot.chat.menu.ChatMenu;
import me.tom.sparse.spigot.chat.menu.element.ButtonElement;
import me.tom.sparse.spigot.chat.menu.element.InputElement;
import me.tom.sparse.spigot.chat.menu.element.TextElement;
import net.prosavage.illyriarpg.IllyriaRPG;
import net.prosavage.illyriarpg.api.files.IWeaponFiles;
import net.prosavage.illyriarpg.utils.INumber;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WeaponFileEditor {

    private INumber INumber = new INumber();
    private net.prosavage.illyriarpg.api.files.IWeaponFiles IWeaponFiles = new IWeaponFiles();

    public void getWeaponCreateMenu(Player player, int page) {
        ChatMenu chatMenu = new ChatMenu();
        int i = 1;
        int minPage = 0;
        List<String> weaponList = new ArrayList<>();
        int c = 0;
        int startingNumber = (page * 10) - 11;
        IllyriaRPG.getInstance().sendConsole("PAGE " + page);
        IllyriaRPG.getInstance().sendConsole("SN " + startingNumber);
        IllyriaRPG.getInstance().sendConsole("C " + c);
        IllyriaRPG.getInstance().sendConsole("I " + i);
        for (String r : IWeaponFiles.getRarityNames()) {
            for (String a : IWeaponFiles.getRarityWeaponNames(r)) {
                weaponList.add(c + "|" + r + "|" + a);
                c++;
                if (c % 10 == 0){
                    i++;
                }
            }
        }
        IllyriaRPG.getInstance().sendConsole(weaponList);
        int d = 0;
        for (String s : weaponList){
            String[] split = s.split("\\|");
            if (startingNumber >= (page * 10) - 11 && startingNumber < (page * 10)){
                if ((Integer.parseInt(split[0]) >= (page * 10) - 11) && (Integer.parseInt(split[0]) < (page * 10))) {
                    chatMenu.add(basicRequirement(chatMenu, player, 0, 5 + d, "[" + (Integer.parseInt(split[0]) + 1) + "] " +
                                    split[1] + " " + split[2], split[1], split[2].replace(".yml", ""), i, page,
                            1));
                    startingNumber++;
                    d++;
                }
            }
        }
        if (page - 1 > minPage) {
            chatMenu.add(backToMenuButton(chatMenu, player, page));
        }
        if (page < i) {
            chatMenu.add(forwardToMenuButton(chatMenu, player, page));
        }
        IllyriaRPG.getInstance().sendConsole(chatMenu.getElements());
        chatMenu.add(getCloseMenu(chatMenu, player));
        chatMenu.setPauseChat(true);
        chatMenu.openFor(player);
    }

    private ButtonElement getCloseMenu(ChatMenu menu, Player player){
        return new ButtonElement(100, 18, ChatColor.RED + "[Close]", player1 -> {
            menu.setPauseChat(false);
            menu.close(player);
            for (int i = 0; i < 200; i++){
                player.sendMessage("\n");
            }
        });
    }

    private ButtonElement goBackToWeaponFileList(ChatMenu menu, Player player, int page){
        return new ButtonElement(100, 18, ChatColor.GREEN + "[File menu]", player1 -> {
            menu.setPauseChat(false);
            menu.close(player);
            getWeaponCreateMenu(player, page);
        });
    }

    private ButtonElement basicRequirement(ChatMenu menu, Player player, int x, int y, String text, String rarity,
                                           String name, int slotNumber, int page, int pageForThisMenu) {
        return new ButtonElement(x, y, ChatColor.GREEN + text, player1 -> {
            new BukkitRunnable(){
                @Override
                public void run() {
                    goToBasicRequirementMenu(menu, player, pageForThisMenu, text, rarity, name, slotNumber, page);
                }
            }.runTaskLater(IllyriaRPG.getInstance(), 1L);
        });
    }

    private void goToBasicRequirementMenu(ChatMenu menu, Player player, int pageForThisMenu, String text, String rarity, String name, int slotNumber, int page) {
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

    private ButtonElement backToMenuButton(ChatMenu menu, Player player, int page) {
        return new ButtonElement(0, 18, ChatColor.RED + "«", player1 -> {
            menu.setPauseChat(false);
            menu.close(player);
            new BukkitRunnable(){
                @Override
                public void run() {
                    getWeaponCreateMenu(player, page - 1);
                }
            }.runTaskLater(IllyriaRPG.getInstance(), 1L);
        });
    }

    private ButtonElement forwardToMenuButton(ChatMenu menu, Player player, int page) {
        return new ButtonElement(200, 18, ChatColor.RED + "»", player1 -> {
            menu.setPauseChat(false);
            menu.close(player);
            new BukkitRunnable(){
                @Override
                public void run() {
                    getWeaponCreateMenu(player, page + 1);
                }
            }.runTaskLater(IllyriaRPG.getInstance(), 1L);
        });
    }

    private InputElement weaponLevelInput(String value, String rarityName, String fileName) {
        InputElement weaponLevelInput = new InputElement(100, 10, 100, value);
        weaponLevelInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if ((currentValue != null) && INumber.isParsableAsInt(currentValue)) {
                int currentValueInt = Integer.parseInt(currentValue);
                IWeaponFiles.setMinimumLevel(rarityName, fileName, currentValueInt);
            }
        });
        return weaponLevelInput;
    }

    private InputElement minimumWeaponDamageInput(String value, String rarityName, String fileName) {
        InputElement weaponDamageInput = new InputElement(100, 12, 100, value);
        weaponDamageInput.value.setChangeCallback(state -> {
            double currentValueDouble = Double.parseDouble(Objects.requireNonNull(state.getCurrent()));
            if (currentValueDouble > -1) {
                IWeaponFiles.setMinimumDamage(rarityName, fileName, currentValueDouble);
            }
        });
        return weaponDamageInput;
    }

    private InputElement maximumWeaponDamageInput(String value, String rarityName, String fileName) {
        InputElement weaponDamageInput = new InputElement(100, 14, 100, value);
        weaponDamageInput.value.setChangeCallback(state -> {
            double currentValueDouble = Double.parseDouble(Objects.requireNonNull(state.getCurrent()));
            if (currentValueDouble > -1) {
                IWeaponFiles.setMaximumDamage(rarityName, fileName, currentValueDouble);
            }
        });
        return weaponDamageInput;
    }

    private InputElement weaponAttackCooldown(String value, String rarityName, String fileName) {
        InputElement weaponAttackCooldownInput = new InputElement(100, 16, 100, value);
        weaponAttackCooldownInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if ((currentValue != null) && INumber.isParsableAsDouble(currentValue)) {
                double currentValueDouble = Double.parseDouble(currentValue);
                IWeaponFiles.setAttackCooldown(rarityName, fileName, currentValueDouble);
            }
        });
        return weaponAttackCooldownInput;
    }

    private InputElement minimumScrollAmountInput(String value, String rarityName, String fileName) {
        InputElement scrollAmountInput = new InputElement(100, 10, 100, value);
        scrollAmountInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if ((currentValue != null) && INumber.isParsableAsInt(currentValue)) {
                int currentValueInt = Integer.parseInt(currentValue);
                IWeaponFiles.setMinimumScroll(rarityName, fileName, currentValueInt);
            }
        });
        return scrollAmountInput;
    }

    private InputElement maximumScrollAmountInput(String value, String rarityName, String fileName) {
        InputElement scrollAmountInput = new InputElement(100, 12, 100, value);
        scrollAmountInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if ((currentValue != null) && INumber.isParsableAsInt(currentValue)) {
                int currentValueInt = Integer.parseInt(currentValue);
                IWeaponFiles.setMaximumScroll(rarityName, fileName, currentValueInt);
            }
        });
        return scrollAmountInput;
    }

    private InputElement minimumGemAmountInput(String value, String rarityName, String fileName) {
        InputElement gemAmountInput = new InputElement(100, 14, 100, value);
        gemAmountInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if ((currentValue != null) && INumber.isParsableAsInt(currentValue)) {
                int currentValueInt = Integer.parseInt(currentValue);
                IWeaponFiles.setMinimumGem(rarityName, fileName, currentValueInt);
            }
        });
        return gemAmountInput;
    }

    private InputElement maximumGemAmountInput(String value, String rarityName, String fileName) {
        InputElement gemAmountInput = new InputElement(100, 16, 100, value);
        gemAmountInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if ((currentValue != null) && INumber.isParsableAsInt(currentValue)) {
                int currentValueInt = Integer.parseInt(currentValue);
                IWeaponFiles.setMaximumGem(rarityName, fileName, currentValueInt);
            }
        });
        return gemAmountInput;
    }

    private InputElement abilityNameInput(String value, String rarityName, String fileName) {
        InputElement abilityNameInput = new InputElement(100, 6, 100, value);
        abilityNameInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if (currentValue != null) {
                IWeaponFiles.setAbility(rarityName, fileName, currentValue);
            }
        });
        return abilityNameInput;
    }

    private InputElement abilityCastTypeInput(String value, String rarityName, String fileName) {
        InputElement abilityCastTypeInput = new InputElement(100, 8, 100, value);
        abilityCastTypeInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if (currentValue != null) {
                IWeaponFiles.setAbilityCastType(rarityName, fileName, currentValue);
            }
        });
        return abilityCastTypeInput;
    }

    private InputElement abilityActionTypeInput(String value, String rarityName, String fileName) {
        InputElement abilityActionTypeInput = new InputElement(100, 10, 100, value);
        abilityActionTypeInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if (currentValue != null) {
                IWeaponFiles.setAbilityActionType(rarityName, fileName, currentValue);
            }
        });
        return abilityActionTypeInput;
    }

    private InputElement abilityCooldownInput(String value, String rarityName, String fileName) {
        InputElement abilityCooldownInput = new InputElement(100, 12, 100, value);
        abilityCooldownInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if ((currentValue != null) && INumber.isParsableAsInt(currentValue)) {
                int currentValueInt = Integer.parseInt(currentValue);
                IWeaponFiles.setAbilityCooldown(rarityName, fileName, String.valueOf(currentValueInt));
            }
        });
        return abilityCooldownInput;
    }

    private InputElement abilityManaCostInput(String value, String rarityName, String fileName) {
        InputElement abilityManaCostInput = new InputElement(100, 14, 100, value);
        abilityManaCostInput.value.setChangeCallback(state -> {
            String currentValue = state.getCurrent();
            if ((currentValue != null) && INumber.isParsableAsInt(currentValue)) {
                double currentValueDouble = Double.parseDouble(currentValue);
                IWeaponFiles.setAbilityCooldown(rarityName, fileName, String.valueOf(currentValueDouble));
            }
        });
        return abilityManaCostInput;
    }

}
