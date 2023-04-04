package ua.kotarych.kabibank.config;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;
import java.util.List;

public class Message extends CustomConfig {

    public static void save(){
        try {
            mes.save(getFileMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void load(){
        mes = YamlConfiguration.loadConfiguration(getFileMessage());
        reloadMessage = mes.getString("reloadMessage");
        errorPermission = mes.getString("errorPermission");
        errorSenderConsole = mes.getString("errorSenderConsole");
        commandBankError = mes.getString("commandBankError");
        successfullyCreateATM = mes.getString("successfullyCreateATM");
        errorCreateATM = mes.getString("errorCreateATM");
        atmListMain = mes.getString("atmListMain");
        atmListCurrency = mes.getString("atmListCurrency");
        errorDeleteATM = mes.getString("errorDeleteATM");
        successfullyDeleteATM = mes.getString("successfullyDeleteATM");
        helpMessageBank = mes.getStringList("helpMessageBank");
        errorListATM = mes.getString("errorListATM");
        nameMainMenu = mes.getString("nameMainMenu");
        errorCreationATM = mes.getString("errorCreationATM");
        namePersonnelMenu = mes.getString("namePersonnelMenu");
        errorPermissionMenu = mes.getString("errorPermissionMenu");
        nameCurrencyMenu = mes.getString("nameCurrencyMenu");
        nameQuantitySelection = mes.getString("nameQuantitySelection");
        nameCurrencyMenuATM = mes.getString("nameCurrencyMenuATM");
        nameQuantitySelectionATM = mes.getString("nameQuantitySelectionATM");
        successfullyAddBalansATM = mes.getString("successfullyAddBalansATM");
        errorBalansAddATM = mes.getString("errorBalansAddATM");
        errorAddATM = mes.getString("errorAddATM");
        nameRemoveBalansATM = mes.getString("nameRemoveBalansATM");
        errorRemoveATM = mes.getString("errorRemoveATM");
        errorFreeInventoryPlayer = mes.getString("errorFreeInventoryPlayer");
        successfullyRemoveBalansATM = mes.getString("successfullyRemoveBalansATM");
        errorBalansRemoveATM = mes.getString("errorBalansRemoveATM");
        nameCreateCard = mes.getString("nameCreateCard");
        errorMaxCard = mes.getString("errorMaxCard");
        errorMaxCardCurrency = mes.getString("errorMaxCardCurrency");
        successfullyCreateCard = mes.getString("successfullyCreateCard");
        errorFreeInventoryCreateCard = mes.getString("errorFreeInventoryCreateCard");
        nameMenuCard = mes.getString("nameMenuCard");
        nameSelectionCard = mes.getString("nameSelectionCard");
        nameRemoveBalansCard = mes.getString("nameRemoveBalansCard");
        errorAddBalansCard = mes.getString("errorAddBalansCard");
        successfullyAddBalansCard = mes.getString("successfullyAddBalansCard");
        errorRemoveBalansCard = mes.getString("errorRemoveBalansCard");
        errorFreeInventoryCard = mes.getString("errorFreeInventoryCard");
        successfullyRemoveBalansCard = mes.getString("successfullyRemoveBalansCard");
        errorRemoveCardATM = mes.getString("errorRemoveCardATM");
        nameDeleteCard = mes.getString("nameDeleteCard");
        errorFreeInventoryDeleteCard = mes.getString("errorFreeInventoryDeleteCard");
        successfullyDeleteCard = mes.getString("successfullyDeleteCard");
        successfullyAddMoneyDeleteCard = mes.getString("successfullyAddMoneyDeleteCard");
        errorBalansAtmDeleteCard = mes.getString("errorBalansAtmDeleteCard");
        nameInfoCard = mes.getString("nameInfoCard");
        nameOptionTransferOnlineOrOffline = mes.getString("nameOptionTransferOnlineOrOffline");
        nameTransferOnline = mes.getString("nameTransferOnline");
        nameTransferOffline = mes.getString("nameTransferOffline");
        nameMyCardTransfer = mes.getString("nameMyCardTransfer");
        nameRecipientCardTransfer = mes.getString("nameRecipientCardTransfer");
        nameTransferMoney = mes.getString("nameTransferMoney");
        errorBalansTransferCard = mes.getString("errorBalansTransferCard");
        successfullyTransferMoneySender = mes.getString("successfullyTransferMoneySender");
        successfullyTransferMoneyRecipient = mes.getString("successfullyTransferMoneyRecipient");
        errorBalansTransfer = mes.getString("errorBalansTransfer");
        errorBalansTransferAmount = mes.getString("errorBalansTransferAmount");
        errorOpenOfflineTransfer = mes.getString("errorOpenOfflineTransfer");
        nameConfirmationDeleteAtm = mes.getString("nameConfirmationDeleteAtm");

    }
    public static void reload() {
        mes = YamlConfiguration.loadConfiguration(getFileMessage());
        reloadMessage = mes.getString("reloadMessage");
        errorPermission = mes.getString("errorPermission");
        errorSenderConsole = mes.getString("errorSenderConsole");
        commandBankError = mes.getString("commandBankError");
        successfullyCreateATM = mes.getString("successfullyCreateATM");
        errorCreateATM = mes.getString("errorCreateATM");
        atmListMain = mes.getString("atmListMain");
        atmListCurrency = mes.getString("atmListCurrency");
        errorDeleteATM = mes.getString("errorDeleteATM");
        successfullyDeleteATM = mes.getString("successfullyDeleteATM");
        helpMessageBank = mes.getStringList("helpMessageBank");
        errorListATM = mes.getString("errorListATM");
        nameMainMenu = mes.getString("nameMainMenu");
        errorCreationATM = mes.getString("errorCreationATM");
        namePersonnelMenu = mes.getString("namePersonnelMenu");
        errorPermissionMenu = mes.getString("errorPermissionMenu");
        nameCurrencyMenu = mes.getString("nameCurrencyMenu");
        nameQuantitySelection = mes.getString("nameQuantitySelection");
        nameCurrencyMenuATM = mes.getString("nameCurrencyMenuATM");
        nameQuantitySelectionATM = mes.getString("nameQuantitySelectionATM");
        successfullyAddBalansATM = mes.getString("successfullyAddBalansATM");
        errorBalansAddATM = mes.getString("errorBalansAddATM");
        errorAddATM = mes.getString("errorAddATM");
        nameRemoveBalansATM = mes.getString("nameRemoveBalansATM");
        errorBalansRemoveATM = mes.getString("errorBalansRemoveATM");
        errorRemoveATM = mes.getString("errorRemoveATM");
        errorFreeInventoryPlayer = mes.getString("errorFreeInventoryPlayer");
        successfullyRemoveBalansATM = mes.getString("successfullyRemoveBalansATM");
        nameCreateCard = mes.getString("nameCreateCard");
        errorMaxCard = mes.getString("errorMaxCard");
        errorMaxCardCurrency = mes.getString("errorMaxCardCurrency");
        successfullyCreateCard = mes.getString("successfullyCreateCard");
        errorFreeInventoryCreateCard = mes.getString("errorFreeInventoryCreateCard");
        nameMenuCard = mes.getString("nameMenuCard");
        nameSelectionCard = mes.getString("nameSelectionCard");
        nameRemoveBalansCard = mes.getString("nameRemoveBalansCard");
        errorAddBalansCard = mes.getString("errorAddBalansCard");
        successfullyAddBalansCard = mes.getString("successfullyAddBalansCard");
        errorRemoveBalansCard = mes.getString("errorRemoveBalansCard");
        errorFreeInventoryCard = mes.getString("errorFreeInventoryCard");
        successfullyRemoveBalansCard = mes.getString("successfullyRemoveBalansCard");
        errorRemoveCardATM = mes.getString("errorRemoveCardATM");
        nameDeleteCard = mes.getString("nameDeleteCard");
        errorFreeInventoryDeleteCard = mes.getString("errorFreeInventoryDeleteCard");
        successfullyDeleteCard = mes.getString("successfullyDeleteCard");
        successfullyAddMoneyDeleteCard = mes.getString("successfullyAddMoneyDeleteCard");
        errorBalansAtmDeleteCard = mes.getString("errorBalansAtmDeleteCard");
        nameInfoCard = mes.getString("nameInfoCard");
        nameOptionTransferOnlineOrOffline = mes.getString("nameOptionTransferOnlineOrOffline");
        nameTransferOnline = mes.getString("nameTransferOnline");
        nameTransferOffline = mes.getString("nameTransferOffline");
        nameMyCardTransfer = mes.getString("nameMyCardTransfer");
        nameRecipientCardTransfer = mes.getString("nameRecipientCardTransfer");
        nameTransferMoney = mes.getString("nameTransferMoney");
        errorBalansTransferCard = mes.getString("errorBalansTransferCard");
        successfullyTransferMoneySender = mes.getString("successfullyTransferMoneySender");
        successfullyTransferMoneyRecipient = mes.getString("successfullyTransferMoneyRecipient");
        errorBalansTransfer = mes.getString("errorBalansTransfer");
        errorBalansTransferAmount = mes.getString("errorBalansTransferAmount");
        errorOpenOfflineTransfer = mes.getString("errorOpenOfflineTransfer");
        nameConfirmationDeleteAtm = mes.getString("nameConfirmationDeleteAtm");
        save();
    }

    private static FileConfiguration mes;
    private static String reloadMessage;
    private static String errorPermission;
    private static String errorSenderConsole;
    private static String commandBankError;
    private static String successfullyCreateATM;
    private static String errorCreateATM;
    private static String atmListMain;
    private static String atmListCurrency;
    private static String errorDeleteATM;
    private static String successfullyDeleteATM;
    private static List<String> helpMessageBank;
    private static String errorListATM;
    private static String nameMainMenu;
    private static String errorCreationATM;
    private static String namePersonnelMenu;
    private static String errorPermissionMenu;
    private static String nameCurrencyMenu;
    private static String nameQuantitySelection;
    private static String nameCurrencyMenuATM;
    private static String nameQuantitySelectionATM;
    private static String successfullyAddBalansATM;
    private static String errorBalansAddATM;
    private static String errorAddATM;
    private static String nameRemoveBalansATM;
    private static String errorBalansRemoveATM;
    private static String errorRemoveATM;
    private static String errorFreeInventoryPlayer;
    private static String successfullyRemoveBalansATM;
    private static String nameCreateCard;
    private static String errorMaxCard;
    private static String errorMaxCardCurrency;
    private static String successfullyCreateCard;
    private static String errorFreeInventoryCreateCard;
    private static String nameMenuCard;
    private static String nameSelectionCard;
    private static String nameRemoveBalansCard;
    private static String errorAddBalansCard;
    private static String successfullyAddBalansCard;
    private static String errorRemoveBalansCard;
    private static String errorFreeInventoryCard;
    private static String successfullyRemoveBalansCard;
    private static String errorRemoveCardATM;
    private static String nameDeleteCard;
    private static String errorFreeInventoryDeleteCard;
    private static String successfullyDeleteCard;
    private static String successfullyAddMoneyDeleteCard;
    private static String errorBalansAtmDeleteCard;
    private static String nameInfoCard;
    private static String nameOptionTransferOnlineOrOffline;
    private static String nameTransferOnline;
    private static String nameTransferOffline;
    private static String nameMyCardTransfer;
    private static String nameRecipientCardTransfer;
    private static String nameTransferMoney;
    private static String errorBalansTransferCard;
    private static String successfullyTransferMoneySender;
    private static String successfullyTransferMoneyRecipient;
    private static String errorBalansTransfer;
    private static String errorBalansTransferAmount;
    private static String errorOpenOfflineTransfer;
    private static String nameConfirmationDeleteAtm;

    public static String getReloadMessage() {
        return reloadMessage;
    }
    public static String getErrorPermission() {
        return errorPermission;
    }
    public static String getErrorSenderConsole() {
        return errorSenderConsole;
    }
    public static String getCommandBankError() {
        return commandBankError;
    }
    public static String getSuccessfullyCreateATM() {
        return successfullyCreateATM;
    }
    public static String getErrorCreateATM() {
        return errorCreateATM;
    }
    public static String getAtmListMain() {
        return atmListMain;
    }
    public static String getAtmListCurrency() {
        return atmListCurrency;
    }
    public static String getErrorDeleteATM() {
        return errorDeleteATM;
    }
    public static String getSuccessfullyDeleteATM() {
        return successfullyDeleteATM;
    }
    public static List<String> getHelpMessageBank() {
        return helpMessageBank;
    }
    public static String getErrorListATM() {
        return errorListATM;
    }
    public static String getNameMainMenu() {
        return nameMainMenu;
    }
    public static String getErrorCreationATM() {
        return errorCreationATM;
    }
    public static String getNamePersonnelMenu() {
        return namePersonnelMenu;
    }
    public static String getErrorPermissionMenu() {
        return errorPermissionMenu;
    }
    public static String getNameCurrencyMenu() {
        return nameCurrencyMenu;
    }
    public static String getNameQuantitySelection() {
        return nameQuantitySelection;
    }
    public static String getNameCurrencyMenuATM() {
        return nameCurrencyMenuATM;
    }
    public static String getSuccessfullyAddBalansATM() {
        return successfullyAddBalansATM;
    }
    public static String getErrorBalansAddATM() {
        return errorBalansAddATM;
    }
    public static String getErrorAddATM() {
        return errorAddATM;
    }
    public static String getNameAddBalansATM() {
        return nameQuantitySelectionATM;
    }
    public static String getNameRemoveBalansATM() {
        return nameRemoveBalansATM;
    }
    public static String getSuccessfullyRemoveBalansATM() {
        return successfullyRemoveBalansATM;
    }

    public static String getErrorBalansRemoveATM() {
        return errorBalansRemoveATM;
    }
    public static String getErrorRemoveATM() {
        return errorRemoveATM;
    }
    public static String getErrorFreeInventoryPlayer() {
        return errorFreeInventoryPlayer;
    }

    public static String getNameCreateCard() {
        return nameCreateCard;
    }

    public static String getErrorMaxCard() {
        return errorMaxCard;
    }

    public static String getErrorMaxCardCurrency() {
        return errorMaxCardCurrency;
    }

    public static String getSuccessfullyCreateCard() {
        return successfullyCreateCard;
    }

    public static String getErrorFreeInventoryCreateCard() {
        return errorFreeInventoryCreateCard;
    }

    public static String getNameMenuCard() {
        return nameMenuCard;
    }

    public static String getNameSelectionCard() {
        return nameSelectionCard;
    }

    public static String getNameRemoveBalansCard() {
        return nameRemoveBalansCard;
    }

    public static String getErrorAddBalansCard() {
        return errorAddBalansCard;
    }

    public static String getSuccessfullyAddBalansCard() {
        return successfullyAddBalansCard;
    }

    public static String getErrorRemoveBalansCard() {
        return errorRemoveBalansCard;
    }

    public static String getErrorFreeInventoryCard() {
        return errorFreeInventoryCard;
    }

    public static String getSuccessfullyRemoveBalansCard() {
        return successfullyRemoveBalansCard;
    }

    public static String getErrorRemoveCardATM() {
        return errorRemoveCardATM;
    }

    public static String getNameDeleteCard() {
        return nameDeleteCard;
    }

    public static String getErrorFreeInventoryDeleteCard() {
        return errorFreeInventoryDeleteCard;
    }

    public static String getSuccessfullyDeleteCard() {
        return successfullyDeleteCard;
    }

    public static String getSuccessfullyAddMoneyDeleteCard() {
        return successfullyAddMoneyDeleteCard;
    }

    public static String getErrorBalansAtmDeleteCard() {
        return errorBalansAtmDeleteCard;
    }

    public static String getNameInfoCard() {
        return nameInfoCard;
    }

    public static String getNameOptionTransferOnlineOrOffline() {
        return nameOptionTransferOnlineOrOffline;
    }

    public static String getNameTransferOnline() {
        return nameTransferOnline;
    }

    public static String getNameTransferOffline() {
        return nameTransferOffline;
    }

    public static String getNameMyCardTransfer() {
        return nameMyCardTransfer;
    }

    public static String getNameRecipientCardTransfer() {
        return nameRecipientCardTransfer;
    }

    public static String getNameTransferMoney() {
        return nameTransferMoney;
    }

    public static String getErrorBalansTransferCard() {
        return errorBalansTransferCard;
    }

    public static String getSuccessfullyTransferMoneySender() {
        return successfullyTransferMoneySender;
    }

    public static String getSuccessfullyTransferMoneyRecipient() {
        return successfullyTransferMoneyRecipient;
    }

    public static String getErrorBalansTransfer() {
        return errorBalansTransfer;
    }

    public static String getErrorBalansTransferAmount() {
        return errorBalansTransferAmount;
    }

    public static String getErrorOpenOfflineTransfer() {
        return errorOpenOfflineTransfer;
    }

    public static String getNameConfirmationDeleteAtm() {
        return nameConfirmationDeleteAtm;
    }
}
