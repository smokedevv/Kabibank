package ua.kotarych.kabibank.config;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class MaterialGui extends CustomConfig {

    public static void save(){
        try {
            materialGui.save(getFileMaterialGui());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void load(){
        materialGui = YamlConfiguration.loadConfiguration(getFileMaterialGui());
        personnelMenu = Material.getMaterial(Objects.requireNonNull(materialGui.getString("personnelMenu")));
        personnelName = materialGui.getString("personnelName");
        personnelLore = materialGui.getStringList("personnelLore");
        personnelAvailable = materialGui.getString("personnelAvailable");
        personnelInaccessible = materialGui.getString("personnelInaccessible");
        addBalansATM = Material.getMaterial(Objects.requireNonNull(materialGui.getString("addBalansATM")));
        addBalansAtmName = materialGui.getString("addBalansAtmName");
        addBalansAtmLore = materialGui.getStringList("addBalansAtmLore");
        currency = Material.getMaterial(Objects.requireNonNull(materialGui.getString("currency")));
        currencyName = materialGui.getString("currencyName");
        addBalans = Material.getMaterial(Objects.requireNonNull(materialGui.getString("addBalans")));
        addBalansName = materialGui.getString("addBalansName");
        addBalansLore = materialGui.getStringList("addBalansLore");
        removeBalans = Material.getMaterial(Objects.requireNonNull(materialGui.getString("removeBalans")));
        removeBalansName = materialGui.getString("removeBalansName");
        removeBalansLore = materialGui.getStringList("removeBalansLore");
        removeBalansAtmName = materialGui.getString("removeBalansAtmName");
        removeBalansAtmLore = materialGui.getStringList("removeBalansAtmLore");
        removeBalansATM = Material.getMaterial(Objects.requireNonNull(materialGui.getString("removeBalansATM")));
        createCard = Material.getMaterial(Objects.requireNonNull(materialGui.getString("createCard")));
        createCardName = materialGui.getString("createCardName");
        createCardLore = materialGui.getStringList("createCardLore");
        card = Material.getMaterial(Objects.requireNonNull(materialGui.getString("card")));
        enchantedCard = materialGui.getBoolean("enchantedCard");
        cardName = materialGui.getString("cardName");
        cardLore = materialGui.getStringList("cardLore");
        infoCreateCard = Material.getMaterial(Objects.requireNonNull(materialGui.getString("infoCreateCard")));
        infoCreateCardName = materialGui.getString("infoCreateCardName");
        menuCardName = materialGui.getString("menuCardName");
        menuCardLore = materialGui.getStringList("menuCardLore");
        menuCard = Material.getMaterial(Objects.requireNonNull(materialGui.getString("menuCard")));
        addBalansCardName = materialGui.getString("addBalansCardName");
        addBalansCardLore = materialGui.getStringList("addBalansCardLore");
        removeBalansCard = Material.getMaterial(Objects.requireNonNull(materialGui.getString("removeBalansCard")));
        removeBalansCardName = materialGui.getString("removeBalansCardName");
        removeBalansCardLore = materialGui.getStringList("removeBalansCardLore");
        selectionCardName = materialGui.getString("selectionCardName");
        selectionCardLore = materialGui.getStringList("selectionCardLore");
        addBalansCard = Material.getMaterial(Objects.requireNonNull(materialGui.getString("addBalansCard")));
        selectionCard = Material.getMaterial(Objects.requireNonNull(materialGui.getString("selectionCard")));
        deleteCard = Material.getMaterial(Objects.requireNonNull(materialGui.getString("deleteCard")));
        deleteCardName = materialGui.getString("deleteCardName");
        deleteCardLore = materialGui.getStringList("deleteCardLore");
        yesDelete = Material.getMaterial(Objects.requireNonNull(materialGui.getString("yesDelete")));
        yesDeleteName = materialGui.getString("yesDeleteName");
        yesDeleteCardLore = materialGui.getStringList("yesDeleteCardLore");
        noDelete = Material.getMaterial(Objects.requireNonNull(materialGui.getString("noDelete")));
        noDeleteName = materialGui.getString("noDeleteName");
        noDeleteCardLore = materialGui.getStringList("noDeleteCardLore");
        infoCard = Material.getMaterial(Objects.requireNonNull(materialGui.getString("infoCard")));
        infoCardLore = materialGui.getStringList("infoCardLore");
        cardInfo = Material.getMaterial(Objects.requireNonNull(materialGui.getString("cardInfo")));
        cardInfoName = materialGui.getString("cardInfoName");
        cardInfoLore = materialGui.getStringList("cardInfoLore");
        infoCardName = materialGui.getString("infoCardName");
        moneyTransferName = materialGui.getString("moneyTransferName");
        moneyTransferLore = materialGui.getStringList("moneyTransferLore");
        onlineTransfer = Material.getMaterial(Objects.requireNonNull(materialGui.getString("onlineTransfer")));
        onlineTransferName = materialGui.getString("onlineTransferName");
        onlineTransferLore = materialGui.getStringList("onlineTransferLore");
        offlineTransfer = Material.getMaterial(Objects.requireNonNull(materialGui.getString("offlineTransfer")));
        offlineTransferName = materialGui.getString("offlineTransferName");
        offlineTransferLore = materialGui.getStringList("offlineTransferLore");
        cardRecipient = Material.getMaterial(Objects.requireNonNull(materialGui.getString("cardRecipient")));
        cardRecipientName = materialGui.getString("cardRecipientName");
        cardRecipientLore = materialGui.getStringList("cardRecipientLore");
        previsionPage = Material.getMaterial(Objects.requireNonNull(materialGui.getString("previsionPage")));
        previsionPageName = materialGui.getString("previsionPageName");
        previsionPageLore = materialGui.getStringList("previsionPageLore");
        airMaterial = Material.getMaterial(Objects.requireNonNull(materialGui.getString("airMaterial")));
        nextPage = Material.getMaterial(Objects.requireNonNull(materialGui.getString("nextPage")));
        nextPadeName = materialGui.getString("nextPadeName");
        nextPageLore = materialGui.getStringList("nextPageLore");
        deleteAtm = Material.getMaterial(Objects.requireNonNull(materialGui.getString("deleteAtm")));
        deleteAtmName = materialGui.getString("deleteAtmName");
        deleteAtmLore = materialGui.getStringList("deleteAtmLore");
        yesDeleteAtmLore = materialGui.getStringList("yesDeleteAtmLore");
        noDeleteAtmLore = materialGui.getStringList("noDeleteAtmLore");
        twoAirMaterial = Material.getMaterial(Objects.requireNonNull(materialGui.getString("twoAirMaterial")));


    }
    public static void reload() {
        materialGui = YamlConfiguration.loadConfiguration(getFileMaterialGui());
        personnelMenu = Material.getMaterial(Objects.requireNonNull(materialGui.getString("personnelMenu")));
        personnelName = materialGui.getString("personnelName");
        personnelLore = materialGui.getStringList("personnelLore");
        personnelAvailable = materialGui.getString("personnelAvailable");
        personnelInaccessible = materialGui.getString("personnelInaccessible");
        addBalansATM = Material.getMaterial(Objects.requireNonNull(materialGui.getString("addBalansATM")));
        addBalansAtmName = materialGui.getString("addBalansAtmName");
        addBalansAtmLore = materialGui.getStringList("addBalansAtmLore");
        currency = Material.getMaterial(Objects.requireNonNull(materialGui.getString("currency")));
        currencyName = materialGui.getString("currencyName");
        addBalans = Material.getMaterial(Objects.requireNonNull(materialGui.getString("addBalans")));
        addBalansName = materialGui.getString("addBalansName");
        addBalansLore = materialGui.getStringList("addBalansLore");
        removeBalans = Material.getMaterial(Objects.requireNonNull(materialGui.getString("removeBalans")));
        removeBalansName = materialGui.getString("removeBalansName");
        removeBalansLore = materialGui.getStringList("removeBalansLore");
        removeBalansATM = Material.getMaterial(Objects.requireNonNull(materialGui.getString("removeBalansATM")));
        removeBalansAtmName = materialGui.getString("removeBalansAtmName");
        removeBalansAtmLore = materialGui.getStringList("removeBalansAtmLore");
        createCard = Material.getMaterial(Objects.requireNonNull(materialGui.getString("createCard")));
        createCardName = materialGui.getString("createCardName");
        createCardLore = materialGui.getStringList("createCardLore");
        card = Material.getMaterial(Objects.requireNonNull(materialGui.getString("card")));
        enchantedCard = materialGui.getBoolean("enchantedCard");
        cardName = materialGui.getString("cardName");
        cardLore = materialGui.getStringList("cardLore");
        infoCreateCard = Material.getMaterial(Objects.requireNonNull(materialGui.getString("infoCreateCard")));
        infoCreateCardName = materialGui.getString("infoCreateCardName");
        menuCard = Material.getMaterial(Objects.requireNonNull(materialGui.getString("menuCard")));
        menuCardName = materialGui.getString("menuCardName");
        menuCardLore = materialGui.getStringList("menuCardLore");
        addBalansCard = Material.getMaterial(Objects.requireNonNull(materialGui.getString("addBalansCard")));
        addBalansCardName = materialGui.getString("addBalansCardName");
        addBalansCardLore = materialGui.getStringList("addBalansCardLore");
        removeBalansCard = Material.getMaterial(Objects.requireNonNull(materialGui.getString("removeBalansCard")));
        removeBalansCardName = materialGui.getString("removeBalansCardName");
        removeBalansCardLore = materialGui.getStringList("removeBalansCardLore");
        selectionCard = Material.getMaterial(Objects.requireNonNull(materialGui.getString("selectionCard")));
        selectionCardName = materialGui.getString("selectionCardName");
        selectionCardLore = materialGui.getStringList("selectionCardLore");
        deleteCard = Material.getMaterial(Objects.requireNonNull(materialGui.getString("deleteCard")));
        deleteCardName = materialGui.getString("deleteCardName");
        deleteCardLore = materialGui.getStringList("deleteCardLore");
        yesDelete = Material.getMaterial(Objects.requireNonNull(materialGui.getString("yesDelete")));
        yesDeleteName = materialGui.getString("yesDeleteName");
        yesDeleteCardLore = materialGui.getStringList("yesDeleteCardLore");
        noDelete = Material.getMaterial(Objects.requireNonNull(materialGui.getString("noDelete")));
        noDeleteName = materialGui.getString("noDeleteName");
        noDeleteCardLore = materialGui.getStringList("noDeleteCardLore");
        infoCard = Material.getMaterial(Objects.requireNonNull(materialGui.getString("infoCard")));
        infoCardName = materialGui.getString("infoCardName");
        infoCardLore = materialGui.getStringList("infoCardLore");
        cardInfo = Material.getMaterial(Objects.requireNonNull(materialGui.getString("cardInfo")));
        cardInfoName = materialGui.getString("cardInfoName");
        cardInfoLore = materialGui.getStringList("cardInfoLore");
        moneyTransferName = materialGui.getString("moneyTransferName");
        moneyTransferLore = materialGui.getStringList("moneyTransferLore");
        onlineTransfer = Material.getMaterial(Objects.requireNonNull(materialGui.getString("onlineTransfer")));
        onlineTransferName = materialGui.getString("onlineTransferName");
        onlineTransferLore = materialGui.getStringList("onlineTransferLore");
        offlineTransfer = Material.getMaterial(Objects.requireNonNull(materialGui.getString("offlineTransfer")));
        offlineTransferName = materialGui.getString("offlineTransferName");
        offlineTransferLore = materialGui.getStringList("offlineTransferLore");
        cardRecipient = Material.getMaterial(Objects.requireNonNull(materialGui.getString("cardRecipient")));
        cardRecipientName = materialGui.getString("cardRecipientName");
        cardRecipientLore = materialGui.getStringList("cardRecipientLore");
        previsionPage = Material.getMaterial(Objects.requireNonNull(materialGui.getString("previsionPage")));
        previsionPageName = materialGui.getString("previsionPageName");
        previsionPageLore = materialGui.getStringList("previsionPageLore");
        airMaterial = Material.getMaterial(Objects.requireNonNull(materialGui.getString("airMaterial")));
        nextPage = Material.getMaterial(Objects.requireNonNull(materialGui.getString("nextPage")));
        nextPadeName = materialGui.getString("nextPadeName");
        nextPageLore = materialGui.getStringList("nextPageLore");
        deleteAtm = Material.getMaterial(Objects.requireNonNull(materialGui.getString("deleteAtm")));
        deleteAtmName = materialGui.getString("deleteAtmName");
        deleteAtmLore = materialGui.getStringList("deleteAtmLore");
        yesDeleteAtmLore = materialGui.getStringList("yesDeleteAtmLore");
        noDeleteAtmLore = materialGui.getStringList("noDeleteAtmLore");
        twoAirMaterial = Material.getMaterial(Objects.requireNonNull(materialGui.getString("twoAirMaterial")));
        save();
    }

    private static FileConfiguration materialGui;

    private static Material personnelMenu;
    private static Material addBalansATM;
    private static Material addBalans;
    private static Material currency;
    private static String personnelName;
    private static String personnelAvailable;
    private static String personnelInaccessible;
    private static String addBalansAtmName;
    private static String currencyName;
    private static String addBalansName;
    private static List<String> personnelLore;
    private static List<String> addBalansAtmLore;
    private static List<String> addBalansLore;
    private static Material removeBalans;
    private static String removeBalansName;
    private static List<String> removeBalansLore;
    private static Material removeBalansATM;
    private static String removeBalansAtmName;
    private static List<String> removeBalansAtmLore;
    private static Material createCard;
    private static String createCardName;
    private static List<String> createCardLore;
    private static Material card;
    private static boolean enchantedCard;
    private static String cardName;
    private static List<String> cardLore;
    private static Material infoCreateCard;
    private static String infoCreateCardName;
    private static Material menuCard;
    private static String menuCardName;
    private static List<String> menuCardLore;
    private static Material addBalansCard;
    private static String addBalansCardName;
    private static List<String> addBalansCardLore;
    private static Material removeBalansCard;
    private static String removeBalansCardName;
    private static List<String> removeBalansCardLore;
    private static Material selectionCard;
    private static String selectionCardName;
    private static List<String> selectionCardLore;
    private static Material deleteCard;
    private static String deleteCardName;
    private static List<String> deleteCardLore;
    private static Material yesDelete;
    private static String yesDeleteName;
    private static List<String> yesDeleteCardLore;
    private static Material noDelete;
    private static String noDeleteName;
    private static List<String> noDeleteCardLore;
    private static Material infoCard;
    private static String infoCardName;
    private static List<String> infoCardLore;
    private static Material cardInfo;
    private static String cardInfoName;
    private static List<String> cardInfoLore;
    private static String moneyTransferName;
    private static List<String> moneyTransferLore;
    private static Material onlineTransfer;
    private static String onlineTransferName;
    private static List<String> onlineTransferLore;
    private static Material offlineTransfer;
    private static String offlineTransferName;
    private static List<String> offlineTransferLore;
    private static Material cardRecipient;
    private static String cardRecipientName;
    private static List<String> cardRecipientLore;
    private static Material previsionPage;
    private static String previsionPageName;
    private static List<String> previsionPageLore;
    private static Material airMaterial;
    private static Material nextPage;
    private static String nextPadeName;
    private static List<String> nextPageLore;
    private static Material deleteAtm;
    private static String deleteAtmName;
    private static List<String> deleteAtmLore;
    private static List<String> yesDeleteAtmLore;
    private static List<String> noDeleteAtmLore;
    private static Material twoAirMaterial;


    public static Material getPersonnelMenu() {
        return personnelMenu;
    }
    public static Material getAddBalansATM() {
        return addBalansATM;
    }
    public static String getPersonnelName() {
        return personnelName;
    }
    public static String getPersonnelAvailable() {
        return personnelAvailable;
    }
    public static String getPersonnelInaccessible() {
        return personnelInaccessible;
    }
    public static String getAddBalansAtmName() {
        return addBalansAtmName;
    }
    public static List<String> getPersonnelLore() {
        return personnelLore;
    }
    public static List<String> getAddBalansAtmLore() {
        return addBalansAtmLore;
    }
    public static Material getCurrency() {
        return currency;
    }
    public static String getCurrencyName() {
        return currencyName;
    }
    public static Material getAddBalans() {
        return addBalans;
    }
    public static String getAddBalansName() {
        return addBalansName;
    }
    public static List<String> getAddBalansLore() {
        return addBalansLore;
    }
    public static Material getRemoveBalans() {
        return removeBalans;
    }
    public static String getRemoveBalansName() {
        return removeBalansName;
    }
    public static List<String> getRemoveBalansLore() {
        return removeBalansLore;
    }
    public static Material getRemoveBalansATM() {
        return removeBalansATM;
    }

    public static String getRemoveBalansAtmName() {
        return removeBalansAtmName;
    }

    public static List<String> getRemoveBalansAtmLore() {
        return removeBalansAtmLore;
    }

    public static Material getCreateCard() {
        return createCard;
    }

    public static String getCreateCardName() {
        return createCardName;
    }

    public static List<String> getCreateCardLore() {
        return createCardLore;
    }

    public static Material getCard() {
        return card;
    }

    public static boolean isEnchantedCard() {
        return enchantedCard;
    }

    public static String getCardName() {
        return cardName;
    }

    public static FileConfiguration getMaterialGui() {
        return materialGui;
    }

    public static List<String> getCardLore() {
        return cardLore;
    }

    public static Material getInfoCreateCard() {
        return infoCreateCard;
    }

    public static String getInfoCreateCardName() {
        return infoCreateCardName;
    }

    public static Material getMenuCard() {
        return menuCard;
    }

    public static String getMenuCardName() {
        return menuCardName;
    }

    public static List<String> getMenuCardLore() {
        return menuCardLore;
    }

    public static Material getAddBalansCard() {
        return addBalansCard;
    }

    public static String getAddBalansCardName() {
        return addBalansCardName;
    }

    public static List<String> getAddBalansCardLore() {
        return addBalansCardLore;
    }

    public static Material getRemoveBalansCard() {
        return removeBalansCard;
    }

    public static String getRemoveBalansCardName() {
        return removeBalansCardName;
    }

    public static List<String> getRemoveBalansCardLore() {
        return removeBalansCardLore;
    }

    public static Material getSelectionCard() {
        return selectionCard;
    }

    public static String getSelectionCardName() {
        return selectionCardName;
    }

    public static List<String> getSelectionCardLore() {
        return selectionCardLore;
    }

    public static Material getDeleteCard() {
        return deleteCard;
    }

    public static String getDeleteCardName() {
        return deleteCardName;
    }

    public static List<String> getDeleteCardLore() {
        return deleteCardLore;
    }

    public static Material getYesDelete() {
        return yesDelete;
    }

    public static String getYesDeleteName() {
        return yesDeleteName;
    }

    public static List<String> getYesDeleteCardLore() {
        return yesDeleteCardLore;
    }

    public static Material getNoDelete() {
        return noDelete;
    }

    public static String getNoDeleteName() {
        return noDeleteName;
    }

    public static List<String> getNoDeleteCardLore() {
        return noDeleteCardLore;
    }

    public static Material getInfoCard() {
        return infoCard;
    }

    public static String getInfoCardName() {
        return infoCardName;
    }

    public static List<String> getInfoCardLore() {
        return infoCardLore;
    }

    public static Material getCardInfo() {
        return cardInfo;
    }

    public static String getCardInfoName() {
        return cardInfoName;
    }

    public static List<String> getCardInfoLore() {
        return cardInfoLore;
    }

    public static String getMoneyTransferName() {
        return moneyTransferName;
    }

    public static List<String> getMoneyTransferLore() {
        return moneyTransferLore;
    }

    public static Material getOnlineTransfer() {
        return onlineTransfer;
    }

    public static String getOnlineTransferName() {
        return onlineTransferName;
    }

    public static List<String> getOnlineTransferLore() {
        return onlineTransferLore;
    }

    public static Material getOfflineTransfer() {
        return offlineTransfer;
    }

    public static String getOfflineTransferName() {
        return offlineTransferName;
    }

    public static List<String> getOfflineTransferLore() {
        return offlineTransferLore;
    }

    public static Material getCardRecipient() {
        return cardRecipient;
    }

    public static String getCardRecipientName() {
        return cardRecipientName;
    }

    public static List<String> getCardRecipientLore() {
        return cardRecipientLore;
    }

    public static Material getPrevisionPage() {
        return previsionPage;
    }

    public static String getPrevisionPageName() {
        return previsionPageName;
    }

    public static List<String> getPrevisionPageLore() {
        return previsionPageLore;
    }

    public static Material getAirMaterial() {
        return airMaterial;
    }

    public static Material getNextPage() {
        return nextPage;
    }

    public static String getNextPadeName() {
        return nextPadeName;
    }

    public static List<String> getNextPageLore() {
        return nextPageLore;
    }

    public static Material getDeleteAtm() {
        return deleteAtm;
    }

    public static String getDeleteAtmName() {
        return deleteAtmName;
    }

    public static List<String> getDeleteAtmLore() {
        return deleteAtmLore;
    }

    public static List<String> getYesDeleteAtmLore() {
        return yesDeleteAtmLore;
    }

    public static List<String> getNoDeleteAtmLore() {
        return noDeleteAtmLore;
    }

    public static Material getTwoAirMaterial() {
        return twoAirMaterial;
    }
}
