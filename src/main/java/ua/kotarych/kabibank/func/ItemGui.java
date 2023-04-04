package ua.kotarych.kabibank.func;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import ua.kotarych.kabibank.config.MaterialGui;
import ua.kotarych.kabibank.models.Gui;

import java.util.List;

public class ItemGui extends Gui {

    public ItemGui(Player player) {
        super(player);
    }

    public static ItemStack personnel(Player player){

        List<String> personnelLore;
        if (player.hasPermission("Kabibank.personnel")){
            personnelLore = FuncString.replacePersonnelLore(MaterialGui.getPersonnelAvailable());

        } else {
            personnelLore = FuncString.replacePersonnelLore(MaterialGui.getPersonnelInaccessible());
        }
        return createItem(MaterialGui.getPersonnelMenu(), MaterialGui.getPersonnelName(), personnelLore);
    }
    public static ItemStack addBalansATM(){
        return createItem(MaterialGui.getAddBalansATM(), MaterialGui.getAddBalansAtmName(), MaterialGui.getAddBalansAtmLore());
    }
    public static ItemStack removeBalansATM(){
        return createItem(MaterialGui.getRemoveBalansATM(), MaterialGui.getRemoveBalansAtmName(), MaterialGui.getRemoveBalansAtmLore());
    }
    public static ItemStack addBalans10(String balans){
        return createItem(MaterialGui.getAddBalans(), MaterialGui.getAddBalansName().replace("%amount%", String.valueOf(10)), FuncString.replaceAddBalansLore(balans));
    }
    public static ItemStack addBalans100(String balans){
        return createItem(MaterialGui.getAddBalans(), MaterialGui.getAddBalansName().replace("%amount%", String.valueOf(100)), FuncString.replaceAddBalansLore(balans));
    }
    public static ItemStack addBalans200(String balans){
        return createItem(MaterialGui.getAddBalans(), MaterialGui.getAddBalansName().replace("%amount%", String.valueOf(200)), FuncString.replaceAddBalansLore(balans));
    }
    public static ItemStack addBalans300(String balans){
        return createItem(MaterialGui.getAddBalans(), MaterialGui.getAddBalansName().replace("%amount%", String.valueOf(300)), FuncString.replaceAddBalansLore(balans));
    }
    public static ItemStack addBalans400(String balans){
        return createItem(MaterialGui.getAddBalans(), MaterialGui.getAddBalansName().replace("%amount%", String.valueOf(400)), FuncString.replaceAddBalansLore(balans));
    }
    public static ItemStack addBalans500(String balans){
        return createItem(MaterialGui.getAddBalans(), MaterialGui.getAddBalansName().replace("%amount%", String.valueOf(500)), FuncString.replaceAddBalansLore(balans));
    }
    public static ItemStack addBalans600(String balans){
        return createItem(MaterialGui.getAddBalans(), MaterialGui.getAddBalansName().replace("%amount%", String.valueOf(600)), FuncString.replaceAddBalansLore(balans));
    }
    public static ItemStack addBalans700(String balans){
        return createItem(MaterialGui.getAddBalans(), MaterialGui.getAddBalansName().replace("%amount%", String.valueOf(700)), FuncString.replaceAddBalansLore(balans));
    }
    public static ItemStack addBalans800(String balans){
        return createItem(MaterialGui.getAddBalans(), MaterialGui.getAddBalansName().replace("%amount%", String.valueOf(800)), FuncString.replaceAddBalansLore(balans));
    }
    public static ItemStack addBalans900(String balans){
        return createItem(MaterialGui.getAddBalans(), MaterialGui.getAddBalansName().replace("%amount%", String.valueOf(900)), FuncString.replaceAddBalansLore(balans));
    }
    public static ItemStack addBalans1000(String balans){
        return createItem(MaterialGui.getAddBalans(), MaterialGui.getAddBalansName().replace("%amount%", String.valueOf(1000)), FuncString.replaceAddBalansLore(balans));
    }

    public static ItemStack removeBalans10(String balans){
        return createItem(MaterialGui.getRemoveBalans(), MaterialGui.getRemoveBalansName().replace("%amount%", String.valueOf(10)), FuncString.replaceRemoveBalansLore(balans));
    }
    public static ItemStack removeBalans100(String balans){
        return createItem(MaterialGui.getRemoveBalans(), MaterialGui.getRemoveBalansName().replace("%amount%", String.valueOf(100)), FuncString.replaceRemoveBalansLore(balans));
    }
    public static ItemStack removeBalans200(String balans){
        return createItem(MaterialGui.getRemoveBalans(), MaterialGui.getRemoveBalansName().replace("%amount%", String.valueOf(200)), FuncString.replaceRemoveBalansLore(balans));
    }
    public static ItemStack removeBalans300(String balans){
        return createItem(MaterialGui.getRemoveBalans(), MaterialGui.getRemoveBalansName().replace("%amount%", String.valueOf(300)), FuncString.replaceRemoveBalansLore(balans));
    }
    public static ItemStack removeBalans400(String balans){
        return createItem(MaterialGui.getRemoveBalans(), MaterialGui.getRemoveBalansName().replace("%amount%", String.valueOf(400)), FuncString.replaceRemoveBalansLore(balans));
    }
    public static ItemStack removeBalans500(String balans){
        return createItem(MaterialGui.getRemoveBalans(), MaterialGui.getRemoveBalansName().replace("%amount%", String.valueOf(500)), FuncString.replaceRemoveBalansLore(balans));
    }
    public static ItemStack removeBalans600(String balans){
        return createItem(MaterialGui.getRemoveBalans(), MaterialGui.getRemoveBalansName().replace("%amount%", String.valueOf(600)), FuncString.replaceRemoveBalansLore(balans));
    }
    public static ItemStack removeBalans700(String balans){
        return createItem(MaterialGui.getRemoveBalans(), MaterialGui.getRemoveBalansName().replace("%amount%", String.valueOf(700)), FuncString.replaceRemoveBalansLore(balans));
    }
    public static ItemStack removeBalans800(String balans){
        return createItem(MaterialGui.getRemoveBalans(), MaterialGui.getRemoveBalansName().replace("%amount%", String.valueOf(800)), FuncString.replaceRemoveBalansLore(balans));
    }
    public static ItemStack removeBalans900(String balans){
        return createItem(MaterialGui.getRemoveBalans(), MaterialGui.getRemoveBalansName().replace("%amount%", String.valueOf(900)), FuncString.replaceRemoveBalansLore(balans));
    }
    public static ItemStack removeBalans1000(String balans){
        return createItem(MaterialGui.getRemoveBalans(), MaterialGui.getRemoveBalansName().replace("%amount%", String.valueOf(1000)), FuncString.replaceRemoveBalansLore(balans));
    }
    public static ItemStack createCard(){
        return createItem(MaterialGui.getCreateCard(), MaterialGui.getCreateCardName(), MaterialGui.getCreateCardLore());
    }
    public static ItemStack infoCreateCard(){
        return createItem(MaterialGui.getInfoCreateCard(), MaterialGui.getInfoCreateCardName());
    }
    public static ItemStack myCard(){
        return createItem(MaterialGui.getMenuCard(), MaterialGui.getMenuCardName(), MaterialGui.getMenuCardLore());
    }
    public static ItemStack addBalansCard(){
        return createItem(MaterialGui.getAddBalansCard(), MaterialGui.getAddBalansCardName(), MaterialGui.getAddBalansCardLore());
    }
    public static ItemStack removeBalansCard(){
        return createItem(MaterialGui.getRemoveBalansCard(), MaterialGui.getRemoveBalansCardName(), MaterialGui.getRemoveBalansCardLore());
    }
    public static ItemStack selectionCards(int number, int balans, String currency){
        return createItem(MaterialGui.getSelectionCard(), MaterialGui.getSelectionCardName().replace("%number%",String.valueOf(number)),
                FuncString.replaceSelectionCardLore(balans, currency));
    }
    public static ItemStack deleteCard(){
        return createItem(MaterialGui.getDeleteCard(), MaterialGui.getDeleteCardName(), MaterialGui.getDeleteCardLore());
    }
    public static ItemStack yesDelete(int number){
        return createItem(MaterialGui.getYesDelete(), MaterialGui.getYesDeleteName(), FuncString.replaceYesDeleteCardLore(number));
    }
    public static ItemStack noDeleteCard(int number){
        return createItem(MaterialGui.getNoDelete(), MaterialGui.getNoDeleteName(), FuncString.replaceNoDeleteCardLore(number));
    }
    public static ItemStack infoCardItem(){
        return createItem(MaterialGui.getInfoCard(), MaterialGui.getInfoCardName(), MaterialGui.getInfoCardLore());
    }
    public static ItemStack infoCards(int numberCard, int balans, String currency){
        return createItem(MaterialGui.getCardInfo(), MaterialGui.getCardInfoName().replace("%number%",String.valueOf(numberCard)),
                FuncString.replaceInfoCardLore(balans, currency));
    }
    public static ItemStack onlineTransferItem(){
        return createItem(MaterialGui.getOnlineTransfer(), MaterialGui.getOnlineTransferName(), MaterialGui.getOnlineTransferLore());
    }
    public static ItemStack offlineTransferItem(){
        return createItem(MaterialGui.getOfflineTransfer(), MaterialGui.getOfflineTransferName(), MaterialGui.getOfflineTransferLore());
    }
    public static ItemStack moneyTransferItem(Player player){
        ItemStack itemStack = headPlayer(player);

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(FuncString.stringToComponent(MaterialGui.getMoneyTransferName()));
        itemMeta.lore(FuncString.stringListToComponent(MaterialGui.getMoneyTransferLore()));

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public static ItemStack headPlayer(Player player){

        ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta itemMeta = (SkullMeta) itemStack.getItemMeta();

        itemMeta.setOwningPlayer(Bukkit.getOfflinePlayerIfCached(player.getName()));
        itemMeta.setPlayerProfile(player.getPlayerProfile());
        itemMeta.displayName(player.displayName());

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
    public static ItemStack offlinePlayer(String name){
        return createItem(Material.PLAYER_HEAD, name);
    }
    public static ItemStack cardRecipient(int numberCard, int balans, String player, String currency){
        return createItem(MaterialGui.getCardRecipient(), MaterialGui.getCardRecipientName().replace("%number%", String.valueOf(numberCard)).replace("%player%", player),
                FuncString.replaceCardRecipientLore(currency, player, balans));
    }
    public static ItemStack previsionPage(){
        return createItem(MaterialGui.getPrevisionPage(), MaterialGui.getPrevisionPageName(), MaterialGui.getPrevisionPageLore());
    }
    public static ItemStack nextPage(){
        return createItem(MaterialGui.getNextPage(), MaterialGui.getNextPadeName(), MaterialGui.getNextPageLore());
    }
    public static ItemStack deleteAtm(){
        return createItem(MaterialGui.getDeleteAtm(), MaterialGui.getDeleteAtmName(), MaterialGui.getDeleteAtmLore());
    }
    public static ItemStack yesDeleteAtm(){
        return createItem(MaterialGui.getYesDelete(), MaterialGui.getYesDeleteName(), MaterialGui.getYesDeleteAtmLore());
    }
    public static ItemStack noDeleteAtm(){
        return createItem(MaterialGui.getNoDelete(), MaterialGui.getNoDeleteName(), MaterialGui.getNoDeleteAtmLore());
    }
}
