package ua.kotarych.kabibank.event;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import ua.kotarych.kabibank.api.event.*;
import ua.kotarych.kabibank.config.Config;
import ua.kotarych.kabibank.config.MaterialGui;
import ua.kotarych.kabibank.files.CoolAtm;
import ua.kotarych.kabibank.files.KabibankCard;
import ua.kotarych.kabibank.func.FuncString;
import ua.kotarych.kabibank.func.ItemGui;
import ua.kotarych.kabibank.config.Message;
import ua.kotarych.kabibank.func.ItemPhysical;
import ua.kotarych.kabibank.models.Atm;
import ua.kotarych.kabibank.models.Card;
import ua.kotarych.kabibank.models.Gui;
import ua.kotarych.kabibank.models.SwitchingPages;
import ua.kotarych.kabibank.tempData.*;

import java.util.Objects;

public class ClickGui implements Listener {

    private static ItemStack itemClick;
    private static Player player;
    private static Gui gui;
    private static InventoryView view;

    @EventHandler
    public static void clickEvent(InventoryClickEvent e) {
        Inventory inventory = e.getClickedInventory();
        view = e.getView();
        player = (Player) e.getView().getPlayer();

        itemClick = e.getCurrentItem();
        gui = new Gui(player);

        if (inventory != null) {
            if (inventory.getType().equals(InventoryType.CHEST)) {
                if (isNamesGui()) {
                    e.setCancelled(true);

                    openPersonnel();
                    openCurrencySelectionMenuATM();
                    openMenuAddBalansATM();
                    openMenuRemoveBalansATM();
                    addBalansATM();
                    removeBalansATM();
                    openCurrencySelectionCreateCard();
                    createCard();
                    openMenuCard();
                    openSelectionCardAddRemoveMoney();
                    openActionCard();
                    addRemoveBalansCard();
                    deleteCard();
                    openMenuInfoCard();
                    openOptionTransferOnlineOrOffline();
                    openOnlineOfflineTransfer();
                    openSelectionMyCardTransfer();
                    openSelectionRecipientCardTransfer();
                    openMenuTransferMoney();
                    moneyTransfer();
                    previsionPage();
                    previsionNextPagesTransfer();
                    openConfirmationDeleteAtm();
                    deleteAtm();
                }
            }
        }
    }

    private static void openPersonnel() {
        if (isNameGui(Message.getNameMainMenu().replace("%number%", "ID-" + NumbersATM.get(player).getNumber()))) {
            if (isClickItem(ItemGui.personnel(player))) {
                if (player.hasPermission("Kabibank.personnel")) {
                    player.openInventory(gui.personnel(NumbersATM.get(player).getNumber()));

                } else {
                    player.sendMessage(FuncString.stringToComponent(Message.getErrorPermissionMenu()));
                }
            }
        }
    }

    private static void openCurrencySelectionMenuATM() {
        if (isNameGui(Message.getNamePersonnelMenu().replace("%number%", "ID-" + NumbersATM.get(player).getNumber()))) {
            if (isClickItem(ItemGui.addBalansATM()) | isClickItem(ItemGui.removeBalansATM())) {
                if (player.hasPermission("Kabibank.personnel")) {
                    player.openInventory(gui.currencySelectionMenu(Message.getNameCurrencyMenuATM()));

                    if (isClickItem(ItemGui.addBalansATM())) {
                        ActionCards.update(player, "add");

                    } else if (isClickItem(ItemGui.removeBalansATM())) {
                        ActionCards.update(player, "remove");

                    }
                } else {
                    player.sendMessage(FuncString.stringToComponent(Message.getErrorPermissionMenu()));
                }
            }
        }
    }

    private static void openCurrencySelectionCreateCard() {
        if (isNameGui(Message.getNameMainMenu().replace("%number%", "ID-" + NumbersATM.get(player).getNumber()))) {
            if (isClickItem(ItemGui.createCard())) {
                if (player.hasPermission("Kabibank.createCard")) {
                    player.openInventory(gui.currencySelectionMenu(Message.getNameCreateCard()));

                } else {
                    player.sendMessage(FuncString.stringToComponent(Message.getErrorPermissionMenu()));
                }
            }
        }
    }

    private static void createCard() {
        if (isNameGui(Message.getNameCreateCard())) {
            for (String s : Config.getCurrencyList()) {
                if (isClickItem(Gui.createItem(MaterialGui.getCurrency(), MaterialGui.getCurrencyName().replace("%currency%", s)))) {
                    if (player.hasPermission("Kabibank.createCard")) {
                        if (!isMaxCard()) {
                            if (!isMaxCard(s)) {
                                if (getFreeInventorySpace(player) >= 64) {

                                    Card card = KabibankCard.createCard(player, s);
                                    EventCreateCard createCard = new EventCreateCard(player, card.getCurrency(), card.getNumber(), card, Objects.requireNonNull(NumbersATM.get(player)).getNumber());
                                    Bukkit.getPluginManager().callEvent(createCard);

                                    if (!createCard.isCancelled()) {

                                        int cardNumber = card.getNumber();
                                        if (card.getBalans() != createCard.getBalansCard()) {
                                            KabibankCard.setBalansCard(cardNumber, createCard.getBalansCard());
                                        }
                                        if (!Objects.equals(card.getCurrency(), createCard.getCurrency())) {
                                            KabibankCard.setCurrency(cardNumber, createCard.getCurrency());
                                        }
                                        if (card.getNumber() != createCard.getNumberCard()) {
                                            KabibankCard.setNumberCard(cardNumber, createCard.getNumberCard());

                                        }

                                        KabibankCard.save();
                                        player.getInventory().addItem(ItemPhysical.card(card.getNumber(), s, player));
                                        player.sendMessage(FuncString.stringToComponent(Message.getSuccessfullyCreateCard().replace("%number%", String.valueOf(card.getNumber())).replace("%currency%", s)));

                                    } else {
                                        KabibankCard.deleteCard(card.getNumber());
                                    }

                                } else {
                                    player.sendMessage(FuncString.stringToComponent(Message.getErrorFreeInventoryCreateCard()));
                                }

                            } else {
                                player.sendMessage(FuncString.stringToComponent(Message.getErrorMaxCardCurrency()));
                            }
                        } else {
                            player.sendMessage(FuncString.stringToComponent(Message.getErrorMaxCard()));
                        }
                    } else {
                        player.sendMessage(FuncString.stringToComponent(Message.getErrorPermissionMenu()));
                    }
                }
            }
        }
    }

    private static void openMenuAddBalansATM() {
        if (ActionCards.get(player) != null) {
            if (ActionCards.get(player).equalsIgnoreCase("add")) {
                if (isNameGui(Message.getNameCurrencyMenuATM())) {
                    for (String s : Config.getCurrencyList()) {
                        if (isClickItem(Gui.createItem(MaterialGui.getCurrency(), MaterialGui.getCurrencyName().replace("%currency%", s)))) {
                            if (player.hasPermission("Kabibank.personnel")) {
                                player.openInventory(gui.addBalans(Objects.requireNonNull(CoolAtm.getATM(Objects.requireNonNull(NumbersATM.get(player)).getNumber())).getCurrencyBalans().get(s) + " " + s, Message.getNameCurrencyMenuATM()));

                            } else {
                                player.sendMessage(FuncString.stringToComponent(Message.getErrorPermissionMenu()));
                            }
                        }
                    }
                }
            }
        }
    }

    private static void openMenuRemoveBalansATM() {
        if (ActionCards.get(player) != null) {
            if (ActionCards.get(player).equalsIgnoreCase("remove")) {
                if (isNameGui(Message.getNameCurrencyMenuATM())) {
                    for (String s : Config.getCurrencyList()) {
                        if (isClickItem(Gui.createItem(MaterialGui.getCurrency(), MaterialGui.getCurrencyName().replace("%currency%", s)))) {
                            if (player.hasPermission("Kabibank.personnel")) {
                                player.openInventory(gui.removeBalans(Objects.requireNonNull(CoolAtm.getATM(Objects.requireNonNull(NumbersATM.get(player)).getNumber())).getCurrencyBalans().get(s) + " " + s, Message.getNameRemoveBalansATM()));

                            } else {
                                player.sendMessage(FuncString.stringToComponent(Message.getErrorPermissionMenu()));
                            }
                        }
                    }
                }
            }
        }
    }

    public static void addBalansATM() {
        if (isNameGui(Message.getNameCurrencyMenuATM())) {
            for (String s : Config.getCurrencyList()) {

                int amount = addBalansCard(Objects.requireNonNull(CoolAtm.getATM(Objects.requireNonNull(NumbersATM.get(player)).getNumber())).getCurrencyBalans().get(s) + " " + s);
                if (amount != 0) {

                    if (player.hasPermission("Kabibank.personnel")) {

                        if (isThereEnoughMoney(amount, s)) {
                            if (CoolAtm.addBalansATM(NumbersATM.get(player).getNumber(), s, amount)) {
                                CoolAtm.save();

                                player.sendMessage(FuncString.stringToComponent(Message.getSuccessfullyAddBalansATM().replace("%number%", String.valueOf(NumbersATM.get(player).getNumber())).
                                        replace("%amount%", String.valueOf(amount)).replace("%currency%", s)));
                                playerDeleteMoney(amount, s);
                                player.openInventory(gui.addBalans(Objects.requireNonNull(CoolAtm.getATM(Objects.requireNonNull(NumbersATM.get(player)).getNumber())).getCurrencyBalans().get(s) + " " + s, Message.getNameCurrencyMenuATM()));

                            } else {
                                player.sendMessage(FuncString.stringToComponent(Message.getErrorAddATM()));
                            }
                        } else {
                            player.sendMessage(FuncString.stringToComponent(Message.getErrorBalansAddATM().replace("%amount%", String.valueOf(amount))));
                        }
                    } else {
                        player.sendMessage(FuncString.stringToComponent(Message.getErrorPermissionMenu()));
                    }
                }
            }

        }
    }

    public static void removeBalansATM() {
        if (isNameGui(Message.getNameRemoveBalansATM())) {

            for (String s : Config.getCurrencyList()) {

                int amount = removeBalansCard(Objects.requireNonNull(CoolAtm.getATM(Objects.requireNonNull(NumbersATM.get(player)).getNumber())).getCurrencyBalans().get(s) + " " + s);
                if (amount != 0) {

                    if (player.hasPermission("Kabibank.personnel")) {

                        if (CoolAtm.isContainsMoney(s, amount, Objects.requireNonNull(NumbersATM.get(player)).getNumber())) {
                            if (getFreeInventorySpace(player, Config.getMaterialCurrency(s)) >= amount) {
                                if (Objects.requireNonNull(CoolAtm.getATM(Objects.requireNonNull(NumbersATM.get(player)).getNumber())).removeBalans(s, amount)) {
                                    CoolAtm.save();

                                    player.sendMessage(FuncString.stringToComponent(Message.getSuccessfullyRemoveBalansATM().replace("%number%",
                                            String.valueOf(Objects.requireNonNull(NumbersATM.get(player)).getNumber())).replace("%amount%", String.valueOf(amount)).replace("%currency%", s)));
                                    playerAddMoney(amount, s);
                                    player.openInventory(gui.removeBalans(Objects.requireNonNull(CoolAtm.getATM(Objects.requireNonNull(NumbersATM.get(player)).getNumber())).getCurrencyBalans().get(s) + " " + s, Message.getNameRemoveBalansATM()));

                                } else {
                                    player.sendMessage(FuncString.stringToComponent(Message.getErrorRemoveATM()));
                                }
                            } else {
                                player.sendMessage(FuncString.stringToComponent(Message.getErrorFreeInventoryPlayer()));
                            }
                        } else {
                            player.sendMessage(FuncString.stringToComponent(Message.getErrorBalansRemoveATM().replace("%amount%", String.valueOf(amount))));
                        }
                    } else {
                        player.sendMessage(FuncString.stringToComponent(Message.getErrorPermissionMenu()));
                    }
                }
            }
        }
    }

    private static void openConfirmationDeleteAtm() {
        if (isNameGui(Message.getNamePersonnelMenu())) {
            if (isClickItem(ItemGui.deleteAtm())) {
                if (player.hasPermission("Kabibank.personnel")) {
                    player.openInventory(gui.confirmationDeleteAtm());

                } else {
                    player.sendMessage(FuncString.stringToComponent(Message.getErrorPermissionMenu()));
                }
            }
        }
    }
    private static void deleteAtm(){
        if (isNameGui(Message.getNameConfirmationDeleteAtm())){
            if (player.hasPermission("Kabibank.personnel")) {
                Atm atm = CoolAtm.getATM(NumbersATM.get(player).getNumber());

                if (atm != null) {
                    if (isClickItem(ItemGui.yesDeleteAtm())) {
                        EventDeleteATM deleteATM = new EventDeleteATM(player, atm.getCoordinate(), atm.getNumberATM(), false);

                        int amount = 0;

                        if (atm != null) {
                            for (String s : Config.getCurrencyList()) {
                                if (atm.getCurrencyBalans().get(s) != 0){
                                    amount += 1;
                                }

                            }
                            if (amount == 0) {
                                Bukkit.getPluginManager().callEvent(deleteATM);

                                if (!deleteATM.isCancelled()) {
                                    CoolAtm.delete(atm.getNumberATM());
                                    CoolAtm.save();
                                }

                            } else {
                                Bukkit.getPluginManager().callEvent(deleteATM);

                                if (!deleteATM.isCancelled()) {
                                    for (String s : Config.getCurrencyList()) {
                                        if (atm.getCurrencyBalans().get(s) != 0) {

                                            player.getWorld().dropItem(player.getLocation(), new ItemStack(Config.getMaterialCurrency(s), atm.getCurrencyBalans().get(s)));
                                        }
                                    }

                                    if (!deleteATM.isCancelled()) {
                                        CoolAtm.delete(atm.getNumberATM());
                                        CoolAtm.save();
                                    }
                                }
                            }
                            if (!deleteATM.isCancelled()) {
                                player.closeInventory();
                                player.sendMessage(FuncString.stringToComponent(Message.getSuccessfullyDeleteATM()));
                            }

                        }
                    } else if (isClickItem(ItemGui.noDeleteAtm())) {
                        player.openInventory(gui.personnel(NumbersATM.get(player).getNumber()));
                    }

                }
            } else {
                player.sendMessage(FuncString.stringToComponent(Message.getErrorPermissionMenu()));
            }
        }
    }

    private static void openMenuCard() {
        if (isNameGui(Message.getNameMainMenu().replace("%number%", "ID-" + NumbersATM.get(player).getNumber()))) {
            if (isClickItem(ItemGui.myCard())) {
                if (player.hasPermission("Kabibank.menuCard")) {
                    player.openInventory(gui.menuCard());

                } else {
                    player.sendMessage(FuncString.stringToComponent(Message.getErrorPermissionMenu()));
                }
            }
        }
    }

    private static void openSelectionCardAddRemoveMoney() {
            if (isNameGui(Message.getNameMenuCard())) {
                if (isClickItem(ItemGui.addBalansCard())) {
                    if (player.hasPermission("Kabibank.menuCard")) {
                        ActionCards.update(player, "add");
                        player.openInventory(gui.selectionCard());

                    } else {
                        player.sendMessage(FuncString.stringToComponent(Message.getErrorPermissionMenu()));
                    }

                } else if (isClickItem(ItemGui.removeBalansCard())) {
                    if (player.hasPermission("Kabibank.menuCard")) {
                        ActionCards.update(player, "remove");
                        player.openInventory(gui.selectionCard());

                    } else {
                        player.sendMessage(FuncString.stringToComponent(Message.getErrorPermissionMenu()));
                    }

                } else if (isClickItem(ItemGui.deleteCard())) {
                    if (player.hasPermission("Kabibank.menuCard")) {
                        ActionCards.update(player, "deleteCard");
                        player.openInventory(gui.selectionCard());

                    } else {
                        player.sendMessage(FuncString.stringToComponent(Message.getErrorPermissionMenu()));
                    }

                }
            }
        }

    private static void openActionCard() {
        if (isNameGui(Message.getNameSelectionCard())) {
            for (Card card : Gui.cardsInventory(player)) {
                ItemStack itemStack = ItemGui.selectionCards(card.getNumber(), card.getBalans(), card.getCurrency());

                if (isClickItem(itemStack)) {
                    if (player.hasPermission("Kabibank.menuCard")) {
                        if (ActionCards.get(player).equals("add")) {
                            CardsData.add(player, card, true);
                            player.openInventory(gui.addBalans(card.getBalans() + " " + card.getCurrency(), Message.getNameQuantitySelection()));

                        } else if (ActionCards.get(player).equals("remove")) {
                            CardsData.add(player, card, false);
                            player.openInventory(gui.removeBalans(card.getBalans() + " " + card.getCurrency(), Message.getNameRemoveBalansCard()));

                        } else if (ActionCards.get(player).equals("deleteCard")) {
                            CardsData.deleteCard(player, card, true);
                            player.openInventory(gui.deleteCard(card.getNumber()));

                        }
                    } else {
                        player.sendMessage(FuncString.stringToComponent(Message.getErrorPermissionMenu()));
                    }
                }
            }
        }
    }

    private static void addRemoveBalansCard() {

        if (isNameGui(Message.getNameQuantitySelection()) | isNameGui(Message.getNameRemoveBalansCard())) {
            Card card = Objects.requireNonNull(CardsData.get(player)).getCard();

            if (card != null) {
                Atm atm = CoolAtm.getATM(NumbersATM.get(player).getNumber());
                if (atm != null) {

                    if (Objects.requireNonNull(CardsData.get(player)).isAdd()) {

                        int amountAdd = addBalansCard(card.getBalans() + " " + card.getCurrency());
                        if (amountAdd != 0) {

                            if (player.hasPermission("Kabibank.menuCard")) {

                                if (isThereEnoughMoney(amountAdd, card.getCurrency())) {

                                    EventAddBalansCard eventAddBalansCard = new EventAddBalansCard(player, card.getNumber(), amountAdd, atm.getNumberATM());
                                    Bukkit.getPluginManager().callEvent(eventAddBalansCard);
                                    boolean cancel = eventAddBalansCard.isCancelled();
                                    int amount = eventAddBalansCard.getAmount();

                                    if (!cancel) {

                                        playerDeleteMoney(amount, card.getCurrency());
                                        KabibankCard.addBalans(card.getNumber(), amount);
                                        KabibankCard.save();
                                        CoolAtm.addBalansATM(atm.getNumberATM(), card.getCurrency(), amount);
                                        CoolAtm.save();

                                        player.sendMessage(FuncString.stringToComponent(Message.getSuccessfullyAddBalansCard().replace("%number%", String.valueOf(card.getNumber())).replace("%amount%",
                                                String.valueOf(amount)).replace("%currency%", card.getCurrency())));
                                        player.openInventory(gui.addBalans(card.getBalans() + " " + card.getCurrency(), Message.getNameQuantitySelection()));
                                    }
                                } else {
                                    player.sendMessage(FuncString.stringToComponent(Message.getErrorAddBalansCard().replace("%amount%", String.valueOf(amountAdd))));
                                }
                            } else {
                                player.sendMessage(FuncString.stringToComponent(Message.getErrorPermissionMenu()));
                            }
                        }
                    } else {

                        int amountRemove = removeBalansCard(card.getBalans() + " " + card.getCurrency());
                        if (amountRemove != 0) {
                            if (player.hasPermission("Kabibank.menuCard")) {

                                if (KabibankCard.isBalans(card.getNumber(), amountRemove)) {
                                    if (getFreeInventorySpace(player, Config.getMaterialCurrency(card.getCurrency())) >= amountRemove) {
                                        if (CoolAtm.isContainsMoney(card.getCurrency(), amountRemove, atm.getNumberATM())) {

                                            EventRemoveBalansCard removeBalansCard = new EventRemoveBalansCard(player, card.getNumber(), amountRemove, atm.getNumberATM());
                                            Bukkit.getPluginManager().callEvent(removeBalansCard);
                                            boolean cansel = removeBalansCard.isCancelled();
                                            int amount = removeBalansCard.getAmount();

                                            if (!cansel) {

                                                KabibankCard.removeBalans(card.getNumber(), amount);
                                                KabibankCard.save();
                                                playerAddMoney(amount, card.getCurrency());
                                                CoolAtm.removeBalansATM(atm.getNumberATM(), card.getCurrency(), amount);
                                                CoolAtm.save();

                                                player.sendMessage(FuncString.stringToComponent(Message.getSuccessfullyRemoveBalansCard()));
                                                player.openInventory(gui.removeBalans(card.getBalans() + " " + card.getCurrency(), Message.getNameRemoveBalansCard()));

                                            }

                                        } else {
                                            player.sendMessage(FuncString.stringToComponent(Message.getErrorRemoveCardATM()));
                                        }
                                    } else {
                                        player.sendMessage(FuncString.stringToComponent(Message.getErrorFreeInventoryCard()));
                                    }
                                } else {
                                    player.sendMessage(FuncString.stringToComponent(Message.getErrorRemoveBalansCard().replace("%amount%", String.valueOf(amountRemove)).replace("%balans%", String.valueOf(card.getBalans()))));
                                }
                            } else {
                                player.sendMessage(FuncString.stringToComponent(Message.getErrorPermissionMenu()));
                            }

                        }
                    }
                }
            }
        }
    }
    private static void deleteCard() {
        if (isNameGui(Message.getNameDeleteCard())) {
            CardData cardData = CardsData.get(player);
            Card card = cardData.getCard();

            Atm atm = CoolAtm.getATM(NumbersATM.get(player).getNumber());

            ItemStack itemCard = ItemPhysical.card(card.getNumber(), card.getCurrency(), player);

            if (cardData.isDeleteCard()) {

                if (isClickItem(ItemGui.yesDelete(card.getNumber()))) {
                    if (player.hasPermission("Kabibank.menuCard")) {
                        if (card.getBalans() != 0) {

                            if (!Config.isIgnoreBalansATDeleteCard()) {
                                if (getFreeInventorySpace(player, Config.getMaterialCurrency(card.getCurrency())) <= card.getBalans()) {
                                    player.sendMessage(FuncString.stringToComponent(Message.getErrorFreeInventoryDeleteCard().replace("%amount%", String.valueOf(card.getBalans()))));

                                } else {
                                    if (!CoolAtm.isContainsMoney(card.getCurrency(), card.getBalans(), NumbersATM.get(player).getNumber())) {
                                        player.sendMessage(FuncString.stringToComponent(Message.getErrorBalansAtmDeleteCard()));
                                    } else {
                                        EventDeleteCard deleteCard = new EventDeleteCard(card.getPlayerName(), card.getPlayerUUID(), card.getNumber(), card.getBalans(), card.getCurrency(), player);
                                        Bukkit.getPluginManager().callEvent(deleteCard);

                                        if (!deleteCard.isCancelled()) {

                                            deleteCardNullBalans(card.getNumber(), itemCard);

                                            playerAddMoney(card.getBalans(), card.getCurrency());
                                            CoolAtm.removeBalansATM(atm.getNumberATM(), card.getCurrency(), card.getBalans());
                                            CoolAtm.save();

                                            player.sendMessage(FuncString.stringToComponent(Message.getSuccessfullyAddMoneyDeleteCard().replace("%amount%", String.valueOf(card.getBalans())).
                                                    replace("%currency%", card.getCurrency())));

                                            player.closeInventory();

                                        }
                                    }
                                }
                            } else {
                                if (getFreeInventorySpace(player, Config.getMaterialCurrency(card.getCurrency())) >= card.getBalans()) {

                                    EventDeleteCard deleteCard = new EventDeleteCard(card.getPlayerName(), card.getPlayerUUID(), card.getNumber(), card.getBalans(), card.getCurrency(), player);
                                    Bukkit.getPluginManager().callEvent(deleteCard);

                                    if (!deleteCard.isCancelled()) {

                                        deleteCardNullBalans(card.getNumber(), itemCard);

                                        playerAddMoney(card.getBalans(), card.getCurrency());
                                        CoolAtm.removeBalansATM(atm.getNumberATM(), card.getCurrency(), card.getBalans());
                                        CoolAtm.save();

                                        player.sendMessage(FuncString.stringToComponent(Message.getSuccessfullyAddMoneyDeleteCard().replace("%amount%", String.valueOf(card.getBalans())).
                                                replace("%currency%", card.getCurrency())));

                                        player.closeInventory();
                                    }
                                } else {
                                    player.sendMessage(FuncString.stringToComponent(Message.getErrorFreeInventoryDeleteCard().replace("%amount%", String.valueOf(card.getBalans()))));
                                }

                            }
                        } else {
                            EventDeleteCard deleteCard = new EventDeleteCard(card.getPlayerName(), card.getPlayerUUID(), card.getNumber(), card.getBalans(), card.getCurrency(), player);
                            Bukkit.getPluginManager().callEvent(deleteCard);

                            if (!deleteCard.isCancelled()) {
                                deleteCardNullBalans(card.getNumber(), itemCard);
                                player.closeInventory();
                            }
                        }
                    } else {
                        player.sendMessage(FuncString.stringToComponent(Message.getErrorPermissionMenu()));
                    }
                } else if (isClickItem(ItemGui.noDeleteCard(card.getNumber()))) {
                    if (player.hasPermission("Kabibank.menuCard")) {
                        player.openInventory(gui.menuCard());

                    } else {
                        player.sendMessage(FuncString.stringToComponent(Message.getErrorPermissionMenu()));
                    }
                }
            }
        }
    }

    private static void openMenuInfoCard() {
        if (isNameGui(Message.getNameMenuCard())) {
            if (isClickItem(ItemGui.infoCardItem())) {
                if (player.hasPermission("Kabibank.menuCard")) {
                    player.openInventory(gui.infoCard());

                } else {
                    player.sendMessage(FuncString.stringToComponent(Message.getErrorPermissionMenu()));
                }
            }
        }
    }
    private static void openOptionTransferOnlineOrOffline() {
        if (isNameGui(Message.getNameMainMenu())) {
            if (isClickItem(ItemGui.moneyTransferItem(player))) {
                if (player.hasPermission("Kabibank.transferMoney")) {
                    player.openInventory(gui.optionTransferOnlineOrOffline());

                } else {
                    player.sendMessage(FuncString.stringToComponent(Message.getErrorPermissionMenu()));
                }
            }

        }
    }
    private static void openOnlineOfflineTransfer() {

        if (isNameGui(Message.getNameOptionTransferOnlineOrOffline())) {
            if (isClickItem(ItemGui.onlineTransferItem())) {
                if (player.hasPermission("Kabibank.transferMoney")) {
                    player.openInventory(gui.onlineTransfer());

                } else {
                    player.sendMessage(FuncString.stringToComponent(Message.getErrorPermissionMenu()));
                }

            } else if (isClickItem(ItemGui.offlineTransferItem())) {
                if (player.hasPermission("Kabibank.transferMoney")) {
                    if (gui.offlineTransfer() != null) {
                        player.openInventory(gui.offlineTransfer());

                    } else {
                        player.sendMessage(FuncString.stringToComponent(Message.getErrorOpenOfflineTransfer()));
                    }

                } else {
                    player.sendMessage(FuncString.stringToComponent(Message.getErrorPermissionMenu()));
                }
            }
        }
    }
    private static void openSelectionMyCardTransfer() {

        for (Card card : KabibankCard.getCards()) {
            if (isNameGui(Message.getNameTransferOnline())) {

                if (Config.getDataPlayerCard().equals("UUID")) {
                    Player playerOnline = Bukkit.getPlayer(card.getPlayerUUID());

                    if (playerOnline != null) {
                        if (isClickItem(ItemGui.headPlayer(playerOnline))) {
                            if (player.hasPermission("Kabibank.transferMoney")) {

                                player.openInventory(gui.selectionMyCardTransfer());
                                TransferCards.addNameRecipient(playerOnline.getName(), playerOnline.getUniqueId(), player);

                            } else {
                                player.sendMessage(FuncString.stringToComponent(Message.getErrorPermissionMenu()));
                            }

                        }

                    }

                } else if (Config.getDataPlayerCard().equals("NAME")) {
                    Player playerOnline = Bukkit.getPlayer(card.getPlayerName());

                    if (playerOnline != null) {
                        if (isClickItem(ItemGui.headPlayer(playerOnline))) {
                            if (player.hasPermission("Kabibank.transferMoney")) {

                                player.openInventory(gui.selectionMyCardTransfer());
                                TransferCards.addNameRecipient(playerOnline.getName(), playerOnline.getUniqueId(), player);

                            } else {
                                player.sendMessage(FuncString.stringToComponent(Message.getErrorPermissionMenu()));
                            }
                        }
                    }
                }
            } else if (isNameGui(Message.getNameTransferOffline())) {
                if (isClickItem(ItemGui.offlinePlayer(card.getPlayerName()))) {
                    if (player.hasPermission("Kabibank.transferMoney")) {
                        player.openInventory(gui.selectionMyCardTransfer());
                        TransferCards.addNameRecipient(card.getPlayerName(), card.getPlayerUUID(), player);

                    } else {
                        player.sendMessage(FuncString.stringToComponent(Message.getErrorPermissionMenu()));
                    }
                }
            }
        }
    }
    private static void openSelectionRecipientCardTransfer() {
        if (isNameGui(Message.getNameMyCardTransfer())) {
            for (Card card : KabibankCard.getAllCardInventory(player)) {
                if (isClickItem(ItemGui.selectionCards(card.getNumber(), card.getBalans(), card.getCurrency()))) {
                    if (player.hasPermission("Kabibank.transferMoney")) {

                        TransferCards.addSender(player, card);
                        player.openInventory(gui.selectionRecipientCardTransfer(TransferCards.get(player)));

                    } else {
                        player.sendMessage(FuncString.stringToComponent(Message.getErrorPermissionMenu()));
                    }
                }
            }
        }
    }
    private static void openMenuTransferMoney() {
        if (isNameGui(Message.getNameRecipientCardTransfer())) {
            TransferCard transferCard = TransferCards.get(player);
            if (transferCard != null) {

                for (Card card : KabibankCard.getAllCardsPlayer(transferCard.getRecipientName(), transferCard.getRecipientUUID())) {
                    ItemStack itemStack = ItemGui.cardRecipient(card.getNumber(), card.getBalans(), card.getPlayerName(), card.getCurrency());

                    if (isClickItem(itemStack)) {
                        if (player.hasPermission("Kabibank.transferMoney")) {
                            TransferCards.addRecipient(player, card);

                            player.openInventory(gui.addBalans(transferCard.getSender().getBalans() + " " + transferCard.getSender().getCurrency(), Message.getNameTransferMoney()));

                        } else {
                            player.sendMessage(FuncString.stringToComponent(Message.getErrorPermissionMenu()));
                        }
                    }
                }
            }
        }
    }
    private static void moneyTransfer() {
        if (isNameGui(Message.getNameTransferMoney())) {
            TransferCard transferCard = TransferCards.get(player);
            Atm atm = CoolAtm.getATM(NumbersATM.get(player).getNumber());
            if (transferCard != null) {
                if (atm != null) {

                    Card sender = transferCard.getSender();
                    Card recipient = transferCard.getRecipient();

                    int amount = addBalansCard(transferCard.getSender().getBalans() + " " + transferCard.getSender().getCurrency());

                    if (amount != 0) {

                        if (player.hasPermission("Kabibank.transferMoney")) {

                            if (sender.getCurrency().equals(recipient.getCurrency())) {
                                if (sender.getBalans() >= amount) {

                                    EventTransferMoney transferMoney = new EventTransferMoney(player, amount, amount, sender.getNumber(), recipient.getNumber(), recipient.getCurrency(), sender.getCurrency(),
                                            sender.getPlayerName(), recipient.getPlayerName(), sender.getPlayerUUID(), recipient.getPlayerUUID());
                                    Bukkit.getPluginManager().callEvent(transferMoney);

                                    if (!transferMoney.isCancelled()) {

                                        int addAmount = transferMoney.getAddAmount();
                                        int removeAmount = transferMoney.getRemoveAmount();

                                        KabibankCard.removeBalans(sender.getNumber(), removeAmount);
                                        KabibankCard.addBalans(recipient.getNumber(), addAmount);
                                        KabibankCard.save();

                                        player.sendMessage(FuncString.stringToComponent(Message.getSuccessfullyTransferMoneySender().replace("%amountRecipient%", String.valueOf(addAmount)).
                                                replace("%amountSender%", String.valueOf(removeAmount)).replace("%currencyRecipient%", recipient.getCurrency()).replace("%currencySender%", sender.getCurrency())));
                                        player.openInventory(gui.addBalans(transferCard.getSender().getBalans() + " " + transferCard.getSender().getCurrency(), Message.getNameTransferMoney()));

                                        if (Config.getDataPlayerCard().equals("NAME")) {

                                            Player playerRecipient = Bukkit.getPlayer(recipient.getPlayerName());
                                            if (playerRecipient != null) {
                                                if (playerRecipient.isOnline()) {
                                                    playerRecipient.sendMessage(FuncString.stringToComponent(Message.getSuccessfullyTransferMoneyRecipient().replace("%amount%", String.valueOf(addAmount)).replace("%currency%", recipient.getCurrency())));
                                                }
                                            }

                                        } else if (Config.getDataPlayerCard().equals("UUID")) {

                                            Player playerRecipient = Bukkit.getPlayer(recipient.getPlayerUUID());
                                            if (playerRecipient != null) {
                                                if (playerRecipient.isOnline()) {
                                                    playerRecipient.sendMessage(FuncString.stringToComponent(Message.getSuccessfullyTransferMoneyRecipient().replace("%amount%", String.valueOf(addAmount)).replace("%currency%", recipient.getCurrency()).
                                                            replace("%player%", player.getName())));
                                                }
                                            }

                                        }
                                    }

                                } else {
                                    player.sendMessage(FuncString.stringToComponent(Message.getErrorBalansTransferCard().replace("%amount%", String.valueOf(amount))));
                                }
                            } else {
                                int exchangeRate = Config.exchangeRate(sender.getCurrency(), recipient.getCurrency());
                                int exchangeRate1 = Config.exchangeRate(recipient.getCurrency(), sender.getCurrency());

                                if (exchangeRate != 0) {

                                    if (sender.getBalans() >= amount) {

                                        int addBalans = exchangeRate * amount;

                                        EventTransferMoney transferMoney = new EventTransferMoney(player, amount, addBalans, sender.getNumber(), recipient.getNumber(), recipient.getCurrency(), sender.getCurrency(),
                                                sender.getPlayerName(), recipient.getPlayerName(), sender.getPlayerUUID(), recipient.getPlayerUUID());
                                        Bukkit.getPluginManager().callEvent(transferMoney);

                                        if (!transferMoney.isCancelled()) {

                                            int addAmount = transferMoney.getAddAmount();
                                            int removeAmount = transferMoney.getRemoveAmount();

                                            KabibankCard.addBalans(recipient.getNumber(), addAmount);
                                            KabibankCard.removeBalans(sender.getNumber(), amount);
                                            KabibankCard.save();

                                            CoolAtm.removeBalansATM(atm.getNumberATM(), sender.getCurrency(), removeAmount);
                                            CoolAtm.addBalansATM(atm.getNumberATM(), recipient.getCurrency(), addAmount);
                                            CoolAtm.save();

                                            player.sendMessage(FuncString.stringToComponent(Message.getSuccessfullyTransferMoneySender().replace("%amountRecipient%", String.valueOf(addAmount)).
                                                    replace("%amountSender%", String.valueOf(removeAmount)).replace("%currencyRecipient%", recipient.getCurrency()).replace("%currencySender%", sender.getCurrency())));
                                            player.openInventory(gui.addBalans(transferCard.getSender().getBalans() + " " + transferCard.getSender().getCurrency(), Message.getNameTransferMoney()));

                                            if (Config.getDataPlayerCard().equals("NAME")) {

                                                Player playerRecipient = Bukkit.getPlayer(recipient.getPlayerName());
                                                if (playerRecipient != null) {
                                                    if (playerRecipient.isOnline()) {
                                                        playerRecipient.sendMessage(FuncString.stringToComponent(Message.getSuccessfullyTransferMoneyRecipient().replace("%amount%", String.valueOf(addAmount)).replace("%currency%", recipient.getCurrency())));
                                                    }
                                                }

                                            } else if (Config.getDataPlayerCard().equals("UUID")) {

                                                Player playerRecipient = Bukkit.getPlayer(recipient.getPlayerUUID());
                                                if (playerRecipient != null) {
                                                    if (playerRecipient.isOnline()) {
                                                        playerRecipient.sendMessage(FuncString.stringToComponent(Message.getSuccessfullyTransferMoneyRecipient().replace("%amount%", String.valueOf(addAmount)).replace("%currency%", recipient.getCurrency()).
                                                                replace("%player%", player.getName())));
                                                    }
                                                }

                                            }
                                        }

                                    } else {
                                        player.sendMessage(FuncString.stringToComponent(Message.getErrorBalansTransferCard().replace("%amount%", String.valueOf(amount))));
                                    }

                                } else if (exchangeRate1 != 0) {

                                    int removeBalans = exchangeRate1 * amount;
                                    int addBalans = exchangeRate1 * amount / amount;

                                    if (sender.getBalans() >= removeBalans) {
                                        if (addBalans > 0) {

                                            EventTransferMoney transferMoney = new EventTransferMoney(player, removeBalans, addBalans, sender.getNumber(), recipient.getNumber(), recipient.getCurrency(), sender.getCurrency(),
                                                    sender.getPlayerName(), recipient.getPlayerName(), sender.getPlayerUUID(), recipient.getPlayerUUID());
                                            Bukkit.getPluginManager().callEvent(transferMoney);

                                            if (!transferMoney.isCancelled()) {

                                                int addAmount = transferMoney.getAddAmount();
                                                int removeAmount = transferMoney.getRemoveAmount();

                                                KabibankCard.addBalans(recipient.getNumber(), addAmount);
                                                KabibankCard.removeBalans(sender.getNumber(), removeAmount);
                                                KabibankCard.save();

                                                CoolAtm.addBalansATM(atm.getNumberATM(), recipient.getCurrency(), addAmount);
                                                CoolAtm.removeBalansATM(atm.getNumberATM(), sender.getCurrency(), removeAmount);
                                                CoolAtm.save();

                                                player.sendMessage(FuncString.stringToComponent(Message.getSuccessfullyTransferMoneySender().replace("%amountRecipient%", String.valueOf(addAmount)).
                                                        replace("%amountSender%", String.valueOf(removeAmount)).replace("%currencyRecipient%", recipient.getCurrency()).replace("%currencySender%", sender.getCurrency())));
                                                player.openInventory(gui.addBalans(transferCard.getSender().getBalans() + " " + transferCard.getSender().getCurrency(), Message.getNameTransferMoney()));

                                                if (Config.getDataPlayerCard().equals("NAME")) {

                                                    Player playerRecipient = Bukkit.getPlayer(recipient.getPlayerName());
                                                    if (playerRecipient != null) {
                                                        if (playerRecipient.isOnline()) {
                                                            playerRecipient.sendMessage(FuncString.stringToComponent(Message.getSuccessfullyTransferMoneyRecipient().replace("%amount%", String.valueOf(addAmount)).replace("%currency%", recipient.getCurrency()).
                                                                    replace("%player%", player.getName())));
                                                        }
                                                    }

                                                } else if (Config.getDataPlayerCard().equals("UUID")) {

                                                    Player playerRecipient = Bukkit.getPlayer(recipient.getPlayerUUID());
                                                    if (playerRecipient != null) {
                                                        if (playerRecipient.isOnline()) {
                                                            playerRecipient.sendMessage(FuncString.stringToComponent(Message.getSuccessfullyTransferMoneyRecipient().replace("%amount%", String.valueOf(addAmount)).replace("%currency%", recipient.getCurrency()).
                                                                    replace("%player%", player.getName())));
                                                        }
                                                    }

                                                }
                                            }
                                        } else {
                                            player.sendMessage(FuncString.stringToComponent(Message.getErrorBalansTransferAmount()));
                                        }
                                    } else {
                                        player.sendMessage(FuncString.stringToComponent(Message.getErrorBalansTransferCard().replace("%amount%", String.valueOf(removeBalans))));
                                    }

                                } else {
                                    player.sendMessage(FuncString.stringToComponent(Message.getErrorBalansTransfer()));
                                }
                            }
                        } else {
                            player.sendMessage(FuncString.stringToComponent(Message.getErrorPermissionMenu()));
                        }
                    }
                }
            }

        }
    }
    private static void previsionPage(){
        if (isClickItem(ItemGui.previsionPage())){

            int number = NumbersATM.get(player).getNumber();

            if (isNameGui(Message.getNamePersonnelMenu())){
                player.openInventory(gui.main(number));
                
            } else if (isNameGui(Message.getNameCurrencyMenuATM())) {
                player.openInventory(gui.personnel(number));
                
            } else if (isNameGui(Message.getNameRemoveBalansATM()) | isNameGui(Message.getNameAddBalansATM())) {
                player.openInventory(gui.currencySelectionMenu(Message.getNameCurrencyMenuATM()));
                
            } else if (isNameGui(Message.getNameCreateCard())) {
                player.openInventory(gui.main(number));

            } else if (isNameGui(Message.getNameMenuCard())) {
                player.openInventory(gui.main(number));

            } else if (isNameGui(Message.getNameSelectionCard())) {
                player.openInventory(gui.menuCard());

            } else if (isNameGui(Message.getNameQuantitySelection()) | isNameGui(Message.getNameRemoveBalansCard())) {
                player.openInventory(gui.selectionCard());

            } else if (isNameGui(Message.getNameInfoCard())) {
                player.openInventory(gui.menuCard());

            } else if (isNameGui(Message.getNameDeleteCard())) {
                player.openInventory(gui.selectionCard());

            } else if (isNameGui(Message.getNameOptionTransferOnlineOrOffline())) {
                player.openInventory(gui.main(number));

            } else if (isNameGui(Message.getNameConfirmationDeleteAtm())) {
                player.openInventory(gui.personnel(NumbersATM.get(player).getNumber()));
                
            } else if (isNameGui(Message.getNameMyCardTransfer())) {
                player.openInventory(gui.optionTransferOnlineOrOffline());

            } else if (isNameGui(Message.getNameRecipientCardTransfer())) {
                player.openInventory(gui.optionTransferOnlineOrOffline());

            } else if (isNameGui(Message.getNameTransferMoney())) {
                player.openInventory(gui.optionTransferOnlineOrOffline());
            }
        }
    }
    private static void previsionNextPagesTransfer() {
        if (isNameGui(Message.getNameTransferOnline()) | isNameGui(Message.getNameTransferOffline())) {

            SwitchingPages switchingPages = PagesSaves.get(player);


            if (isClickItem(ItemGui.previsionPage())) {
                if (player.hasPermission("Kabibank.transferMoney")) {
                    switchingPages.openPreviousPage(gui);

                } else {
                    player.sendMessage(FuncString.stringToComponent(Message.getErrorPermissionMenu()));
                }

            } else if (isClickItem(ItemGui.nextPage())) {
                if (player.hasPermission("Kabibank.transferMoney")) {
                    switchingPages.openNextPage();

                } else {
                    player.sendMessage(FuncString.stringToComponent(Message.getErrorPermissionMenu()));
                }
            }
        }
    }



    private static int addBalansCard(String balans){
        if (isClickItem(ItemGui.addBalans10(balans))){
            return 10;
        } else if (isClickItem(ItemGui.addBalans100(balans))) {
            return 100;
        } else if (isClickItem(ItemGui.addBalans200(balans))) {
            return 200;
        } else if (isClickItem(ItemGui.addBalans300(balans))) {
            return 300;
        } else if (isClickItem(ItemGui.addBalans400(balans))) {
            return 400;
        } else if (isClickItem(ItemGui.addBalans500(balans))) {
            return 500;
        } else if (isClickItem(ItemGui.addBalans600(balans))) {
            return 600;
        } else if (isClickItem(ItemGui.addBalans700(balans))) {
            return 700;
        } else if (isClickItem(ItemGui.addBalans800(balans))) {
            return 800;
        } else if (isClickItem(ItemGui.addBalans900(balans))) {
            return 900;
        } else if (isClickItem(ItemGui.addBalans1000(balans))) {
            return 1000;
        } else {
            return 0;
        }
    }
    private static int removeBalansCard(String balans){
        if (isClickItem(ItemGui.removeBalans10(balans))){
            return 10;
        } else if (isClickItem(ItemGui.removeBalans100(balans))) {
            return 100;
        } else if (isClickItem(ItemGui.removeBalans200(balans))) {
            return 200;
        } else if (isClickItem(ItemGui.removeBalans300(balans))) {
            return 300;
        } else if (isClickItem(ItemGui.removeBalans400(balans))) {
            return 400;
        } else if (isClickItem(ItemGui.removeBalans500(balans))) {
            return 500;
        } else if (isClickItem(ItemGui.removeBalans600(balans))) {
            return 600;
        } else if (isClickItem(ItemGui.removeBalans700(balans))) {
            return 700;
        } else if (isClickItem(ItemGui.removeBalans800(balans))) {
            return 800;
        } else if (isClickItem(ItemGui.removeBalans900(balans))) {
            return 900;
        } else if (isClickItem(ItemGui.removeBalans1000(balans))) {
            return 1000;
        } else {
            return 0;
        }
    }
    private static boolean isNamesGui(){
        Component title = view.title();
        for (Atm atm : CoolAtm.getAtms()){
            int number = atm.getNumberATM();

            Component mainMenu = FuncString.stringToComponent(Message.getNameMainMenu().replace("%number%","ID-" + number));
            Component personnelMenu = FuncString.stringToComponent(Message.getNamePersonnelMenu().replace("%number%","ID-" + number));


            if (title.equals(mainMenu)){
                return true;
            } else if (title.equals(personnelMenu)) {
                return true;
            }
        }
        if (title.equals(FuncString.stringToComponent(Message.getNameCurrencyMenu()))) {
            return true;
        } else if (title.equals(FuncString.stringToComponent(Message.getNameCurrencyMenuATM()))) {
            return true;
        } else if (title.equals(FuncString.stringToComponent(Message.getNameAddBalansATM()))) {
            return true;
        } else if (title.equals(FuncString.stringToComponent(Message.getNameRemoveBalansATM()))) {
            return true;
        } else if (title.equals(FuncString.stringToComponent(Message.getNameCreateCard()))) {
            return true;
        } else if (title.equals(FuncString.stringToComponent(Message.getNameMenuCard()))) {
            return true;
        } else if (title.equals(FuncString.stringToComponent(Message.getNameRemoveBalansCard()))) {
            return true;
        } else if (title.equals(FuncString.stringToComponent(Message.getNameSelectionCard()))) {
            return true;
        } else if (title.equals(FuncString.stringToComponent(Message.getNameDeleteCard()))) {
            return true;
        } else if (title.equals(FuncString.stringToComponent(Message.getNameInfoCard()))) {
            return true;
        } else if (title.equals(FuncString.stringToComponent(Message.getNameOptionTransferOnlineOrOffline()))) {
            return true;
        } else if (title.equals(FuncString.stringToComponent(Message.getNameTransferOffline()))) {
            return true;
        } else if (title.equals(FuncString.stringToComponent(Message.getNameTransferOnline()))) {
            return true;
        } else if (title.equals(FuncString.stringToComponent(Message.getNameMyCardTransfer()))) {
            return true;
        } else if (title.equals(FuncString.stringToComponent(Message.getNameRecipientCardTransfer()))) {
            return true;
        } else if (title.equals(FuncString.stringToComponent(Message.getNameTransferMoney()))) {
            return true;
        } else if (title.equals(FuncString.stringToComponent(Message.getNameConfirmationDeleteAtm()))) {
            return true;
        } else return title.equals(FuncString.stringToComponent(Message.getNameQuantitySelection()));
    }
    private static boolean isNameGui(String string) {
        for (Atm atm : CoolAtm.getAtms()) {
            int number = atm.getNumberATM();
            Component title = view.title();

            Component name = FuncString.stringToComponent(string.replace("%number%", "ID-" + number));

            if (title.equals(name)) {
                return true;
            }
        }
        return false;
    }
    private static boolean isClickItem(ItemStack itemStack){
        return itemStack.equals(itemClick);
    }
    private static boolean isThereEnoughMoney(int amount, String currency){
        Inventory inventory = player.getInventory();
        Material currencyMaterial = Config.getMaterialCurrency(currency);

        return inventory.contains(currencyMaterial, amount);
    }
    private static void playerDeleteMoney(int amount, String currency){
        Inventory inventory = player.getInventory();
        Material currencyMaterial = Config.getMaterialCurrency(currency);

        inventory.removeItem(new ItemStack(currencyMaterial, amount));
    }
    private static void playerAddMoney(int amount, String currency){
        Inventory inventory = player.getInventory();
        Material currencyMaterial = Config.getMaterialCurrency(currency);

        inventory.addItem(new ItemStack(currencyMaterial, amount));
    }
    private static boolean hasItemInOffHand(Player player) {
        PlayerInventory inventory = player.getInventory();
        ItemStack itemInOffHand = inventory.getItemInOffHand();

        return itemInOffHand != null && !itemInOffHand.getType().equals(Material.AIR);
    }
    private static int getFreeInventorySpace(Player player) {
        int freeSpace = 0;

        PlayerInventory inventory = player.getInventory();
        ItemStack[] armorContents = inventory.getArmorContents();

        if (armorContents.length != 0) {
            freeSpace -= armorContents.length * 64;
        }
        if (!hasItemInOffHand(player)){
            freeSpace -= 64;
        }

        for (ItemStack item : inventory.getContents()) {
            if (item == null) {
                freeSpace += 64;

            } else {
                if (item.getAmount() != 64){
                    freeSpace += 64 - item.getAmount();
                }
            }
        }
        return freeSpace;
    }
    private static int getFreeInventorySpace(Player player, Material material) {
        int freeSpace = 0;

        PlayerInventory inventory = player.getInventory();
        ItemStack[] armorContents = inventory.getArmorContents();

        if (armorContents.length != 0) {
            freeSpace -= armorContents.length * 64;
        }
        if (!hasItemInOffHand(player)){
            freeSpace -= 64;
        }

        for (ItemStack item : inventory.getContents()) {
            if (item == null) {
                freeSpace += 64;

            } else {
                if (item.getType().equals(material)){
                    if (item.getAmount() != 64){
                        freeSpace += 64 - item.getAmount();
                    }
                }
            }
        }
        return freeSpace;
    }
    private static void deleteCardNullBalans(int numberCard, ItemStack card){

        KabibankCard.deleteCard(numberCard);
        KabibankCard.save();
        player.getInventory().remove(card);

        player.sendMessage(FuncString.stringToComponent(Message.getSuccessfullyDeleteCard().replace("%number%", String.valueOf(numberCard))));
    }
    private static boolean isMaxCard(){
        if (Config.getMaxCardPlayer(player) != 0){
            return KabibankCard.getAmountCardPlayer(player) >= Config.getMaxCardPlayer(player);

        } else {
            return KabibankCard.getAmountCardPlayer(player) >= Config.getMaxCardCreate();

        }
    }
    private static boolean isMaxCard(String currency){
        if (Config.getMaxCardCurrencyPlayer(player, currency) != 0){
            return KabibankCard.getAmountCardCurrencyPlayer(player, currency) >= Config.getMaxCardCurrencyPlayer(player, currency);

        } else {
            if (Config.getMaxCardCurrency(currency) != 0) {
                return KabibankCard.getAmountCardCurrencyPlayer(player, currency) >= Config.getMaxCardCurrency(currency);
            } else {
                return false;
            }

        }
    }
}
