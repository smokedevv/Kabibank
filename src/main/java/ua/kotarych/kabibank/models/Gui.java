package ua.kotarych.kabibank.models;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import ua.kotarych.kabibank.config.Config;
import ua.kotarych.kabibank.config.MaterialGui;
import ua.kotarych.kabibank.files.KabibankCard;
import ua.kotarych.kabibank.func.FuncString;
import ua.kotarych.kabibank.func.ItemGui;
import ua.kotarych.kabibank.config.Message;
import ua.kotarych.kabibank.func.ItemPhysical;
import ua.kotarych.kabibank.tempData.PagesSaves;
import ua.kotarych.kabibank.tempData.TransferCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Gui {

    public Gui(Player player){
        this.player = player;
    }

    private Player player;
    public Inventory main(int number){
        int amount = 4;
        Inventory inventory = createInventory(amount, Message.getNameMainMenu().replace( "%number%", "ID-" + number), player);

        ItemStack personnel = ItemGui.personnel(player);

        inventory.setItem(12, personnel);
        inventory.setItem(13, ItemGui.createCard());
        inventory.setItem(14, new ItemStack(MaterialGui.getTwoAirMaterial()));


        if (cardsInventory(player).size() >= 1){
            inventory.setItem(22, ItemGui.myCard());
            inventory.setItem(21, ItemGui.moneyTransferItem(player));
            inventory.setItem(23, new ItemStack(MaterialGui.getTwoAirMaterial()));

        }
        for (int i = 0; i <= 9 * amount - 1; i++){
            ItemStack itemStack = inventory.getItem(i);

            if (itemStack == null){
                inventory.setItem(i, new ItemStack(MaterialGui.getAirMaterial()));
            }
        }

        return inventory;
    }
    public Inventory personnel(int number){
        int amount = 3;
        Inventory inventory = createInventory(amount, Message.getNamePersonnelMenu().replace( "%number%", "ID-" + number), player);

        inventory.setItem(11, ItemGui.addBalansATM());
        inventory.setItem(12, ItemGui.removeBalansATM());
        inventory.setItem(13, ItemGui.deleteAtm());
        inventory.setItem(14, ItemGui.previsionPage());

        inventory.setItem(15, new ItemStack(MaterialGui.getTwoAirMaterial()));

        for (int i = 0; i <= 9 * amount - 1; i++){
            ItemStack itemStack = inventory.getItem(i);

            if (itemStack == null) {
                inventory.setItem(i, new ItemStack(MaterialGui.getAirMaterial()));
            }
        }

        return inventory;
    }
    public Inventory currencySelectionMenu(String name){
        int size;
        int index;

        if (name.equals(Message.getNameCreateCard())){
            size = Config.getCurrencyList().size() + 2;
            index = 1;
        } else {
            size = Config.getCurrencyList().size() + 1;
            index = 0;
        }

        Inventory inventory = null;
        int amount = 1;

        if (size <= 9){
            inventory = createInventory(1, name, player);
        } else if (size <= 18) {
            inventory = createInventory(2, name, player);
            amount = 2;
        } else if (size <= 27) {
            inventory = createInventory(3, name, player);
            amount = 3;
        } else if (size <= 36) {
            inventory = createInventory(4, name, player);
            amount = 4;
        } else if (size <= 45) {
            inventory = createInventory(5, name, player);
            amount = 5;
        }

        inventory.setItem(0, ItemGui.infoCreateCard());

        for (String s : Config.getCurrencyList()){
            ItemStack itemStack = createItem(MaterialGui.getCurrency(), MaterialGui.getCurrencyName().replace("%currency%", s));

            inventory.setItem(index, itemStack);
            index += 1;
        }
        inventory.addItem(ItemGui.previsionPage(), new ItemStack(MaterialGui.getTwoAirMaterial()));

        for (int i = 0; i <= 9 * amount - 1; i++){
            ItemStack itemStack = inventory.getItem(i);

            if (itemStack == null){
                inventory.setItem(i, new ItemStack(MaterialGui.getAirMaterial()));
            }
        }

        return inventory;
    }
    public Inventory addBalans(String balans, String name){
        int amount = 4;
        Inventory inventory = createInventory(amount, name, player);

        inventory.setItem(2, ItemGui.addBalans10(balans));
        inventory.setItem(3, ItemGui.addBalans100(balans));
        inventory.setItem(4, ItemGui.addBalans200(balans));
        inventory.setItem(5, ItemGui.addBalans300(balans));
        inventory.setItem(6, ItemGui.addBalans400(balans));
        inventory.setItem(11, ItemGui.addBalans500(balans));
        inventory.setItem(12, ItemGui.addBalans600(balans));
        inventory.setItem(13, ItemGui.addBalans700(balans));
        inventory.setItem(14, ItemGui.addBalans800(balans));
        inventory.setItem(15, ItemGui.addBalans900(balans));
        inventory.setItem(22, ItemGui.addBalans1000(balans));

        inventory.setItem(23, ItemGui.previsionPage());

        inventory.setItem(21, new ItemStack(MaterialGui.getTwoAirMaterial()));

        for (int i = 0; i <= 9 * amount - 1; i++){
            ItemStack itemStack = inventory.getItem(i);

            if (itemStack == null){
                inventory.setItem(i, new ItemStack(MaterialGui.getAirMaterial()));
            }
        }

        return inventory;
    }
    public Inventory removeBalans(String balans, String name){
        int amount = 4;
        Inventory inventory = createInventory(amount, name, player);

        inventory.setItem(2, ItemGui.removeBalans10(balans));
        inventory.setItem(3, ItemGui.removeBalans100(balans));
        inventory.setItem(4, ItemGui.removeBalans200(balans));
        inventory.setItem(5, ItemGui.removeBalans300(balans));
        inventory.setItem(6, ItemGui.removeBalans400(balans));
        inventory.setItem(11, ItemGui.removeBalans500(balans));
        inventory.setItem(12, ItemGui.removeBalans600(balans));
        inventory.setItem(13, ItemGui.removeBalans700(balans));
        inventory.setItem(14, ItemGui.removeBalans800(balans));
        inventory.setItem(15, ItemGui.removeBalans900(balans));
        inventory.setItem(22, ItemGui.removeBalans1000(balans));

        inventory.setItem(23, ItemGui.previsionPage());

        inventory.setItem(21, new ItemStack(MaterialGui.getTwoAirMaterial()));

        for (int i = 0; i <= 9 * amount - 1; i++){
            ItemStack itemStack = inventory.getItem(i);

            if (itemStack == null){
                inventory.setItem(i, new ItemStack(MaterialGui.getAirMaterial()));
            }
        }

        return inventory;
    }
    public Inventory menuCard(){
        int amount = 3;
        Inventory inventory = createInventory(amount, Message.getNameMenuCard(), player);

        inventory.setItem(11, ItemGui.addBalansCard());
        inventory.setItem(12, ItemGui.removeBalansCard());
        inventory.setItem(13, ItemGui.infoCardItem());
        inventory.setItem(14, ItemGui.deleteCard());
        inventory.setItem(15, ItemGui.previsionPage());

        inventory.setItem(10, new ItemStack(MaterialGui.getTwoAirMaterial()));
        inventory.setItem(16, new ItemStack(MaterialGui.getTwoAirMaterial()));

        for (int i = 0; i <= 9 * amount - 1; i++){
            ItemStack itemStack = inventory.getItem(i);

            if (itemStack == null){
                inventory.setItem(i, new ItemStack(MaterialGui.getAirMaterial()));
            }
        }

        return inventory;
    }

    public Inventory selectionCard(){
        Inventory inventory = null;

        int size = cardsInventory(player).size() + 1;
        String name = Message.getNameSelectionCard();

        int amount = 1;

        if (size <= 9){
            inventory = createInventory(1, name, player);
        } else if (size <= 18) {
            inventory = createInventory(2, name, player);
            amount = 2;
        } else if (size <= 27) {
            inventory = createInventory(3, name, player);
            amount = 3;
        } else if (size <= 36) {
            inventory = createInventory(4, name, player);
            amount = 4;
        } else if (size <= 45) {
            inventory = createInventory(5, name,player);
            amount = 5;
        }

        for (Card card : cardsInventory(player)){
            ItemStack itemStack = ItemGui.selectionCards(card.getNumber(), card.getBalans(), card.getCurrency());
            inventory.addItem(itemStack);
        }
        inventory.addItem(ItemGui.previsionPage());

        for (int i = 0; i <= 9 * amount - 1; i++){
            ItemStack itemStack = inventory.getItem(i);

            if (itemStack == null){
                inventory.setItem(i, new ItemStack(MaterialGui.getAirMaterial()));
            }
        }

        return inventory;
    }

    public Inventory deleteCard(int numberCard){
        int amount = 1;
        Inventory inventory = createInventory(amount, Message.getNameDeleteCard(), player);

        inventory.addItem(ItemGui.yesDelete(numberCard), ItemGui.noDeleteCard(numberCard), ItemGui.previsionPage());

        for (int i = 0; i <= 9 * amount - 1; i++){
            ItemStack itemStack = inventory.getItem(i);

            if (itemStack == null){
                inventory.setItem(i, new ItemStack(MaterialGui.getAirMaterial()));
            }
        }

        return inventory;
    }
    public Inventory infoCard(){
        Inventory inventory = null;
        String name = Message.getNameInfoCard();
        int size = KabibankCard.getAllCardInventory(player).size() + 1;

        int amount = 1;

        if (size <= 9){
            inventory = createInventory(1, name, player);
        } else if (size <= 18) {
            inventory = createInventory(2, name, player);
            amount = 2;
        } else if (size <= 27) {
            inventory = createInventory(3, name, player);
            amount = 3;
        } else if (size <= 36) {
            inventory = createInventory(4, name, player);
            amount = 4;
        } else if (size <= 45) {
            inventory = createInventory(5, name, player);
            amount = 5;
        }

        for (Card card : KabibankCard.getAllCardInventory(player)){
            ItemStack itemStack = ItemGui.infoCards(card.getNumber(), card.getBalans(), card.getCurrency());

            inventory.addItem(itemStack);
        }
        inventory.addItem(ItemGui.previsionPage());

        for (int i = 0; i <= 9 * amount - 1; i++){
            ItemStack itemStack = inventory.getItem(i);

            if (itemStack == null){
                inventory.setItem(i, new ItemStack(MaterialGui.getAirMaterial()));
            }
        }

        return inventory;
    }

    public Inventory optionTransferOnlineOrOffline(){
        int amount = 1;
        Inventory inventory = createInventory(amount, Message.getNameOptionTransferOnlineOrOffline(), player);

        inventory.addItem(ItemGui.onlineTransferItem(), ItemGui.offlineTransferItem(), ItemGui.previsionPage(), new ItemStack(MaterialGui.getTwoAirMaterial()));

        for (int i = 0; i <= 9 * amount - 1; i++){
            ItemStack itemStack = inventory.getItem(i);

            if (itemStack == null){
                inventory.setItem(i, new ItemStack(MaterialGui.getAirMaterial()));
            }
        }

        return inventory;

    }
    public Inventory onlineTransfer(){

        SwitchingPages pages = new SwitchingPages(player, Message.getNameTransferOnline());

        if (Config.getDataPlayerCard().equals("UUID")) {

            for (Card card : KabibankCard.getCards()) {
                Player player1 = Bukkit.getPlayer(card.getPlayerUUID());
                if (player1 != null) {
                    if (player1.isOnline()) {

                        ItemStack itemStack = ItemGui.headPlayer(player1);
                        if (!pages.isItem(itemStack)) {
                            pages.addItem(itemStack);
                        }
                    }
                }
            }

        } else if (Config.getDataPlayerCard().equals("NAME")) {

            for (Card card : KabibankCard.getCards()) {
                Player player1 = Bukkit.getPlayer(card.getPlayerName());
                if (player1 != null) {
                    if (player1.isOnline()) {

                        ItemStack itemStack = ItemGui.headPlayer(player1);
                        if (!pages.isItem(itemStack)) {
                            pages.addItem(itemStack);
                        }
                    }
                }
            }
        }

        PagesSaves.add(player, pages);

        Inventory inventory = pages.getInventory();
        for (int i = 0; i < 9 * 4 - 2; i++){
            ItemStack itemStack = inventory.getItem(i);

            if (itemStack == null){
                inventory.setItem(i, new ItemStack(MaterialGui.getAirMaterial()));
            }
        }

        return inventory;
    }

    public Inventory offlineTransfer(){
        SwitchingPages pages = new SwitchingPages(player, Message.getNameTransferOffline());
        boolean isNull = true;

        if (Config.getDataPlayerCard().equals("UUID")) {

            for (Card card : KabibankCard.getCards()) {
                if (!Bukkit.getOfflinePlayer(card.getPlayerUUID()).isOnline()) {
                    ItemStack itemStack = ItemGui.offlinePlayer(card.getPlayerName());

                    if (!pages.isItem(itemStack)) {
                        pages.addItem(itemStack);
                        isNull = false;
                    }
                }
                PagesSaves.add(player, pages);
            }

        } else if (Config.getDataPlayerCard().equals("NAME")) {

            for (Card card : KabibankCard.getCards()) {
                if (!Objects.requireNonNull(Bukkit.getOfflinePlayerIfCached(card.getPlayerName())).isOnline()) {
                    ItemStack itemStack = ItemGui.offlinePlayer(card.getPlayerName());

                    if (!pages.isItem(itemStack)) {
                        pages.addItem(itemStack);
                        isNull = false;
                    }
                }
                PagesSaves.add(player, pages);
            }
        }
        if (!isNull){
            Inventory inventory = pages.getInventory();
            for (int i = 0; i < 9 * 4 - 2; i++){
                ItemStack itemStack = inventory.getItem(i);

                if (itemStack == null){
                    inventory.setItem(i, new ItemStack(MaterialGui.getAirMaterial()));
                }
            }
            return inventory;

        } else {
            return null;
        }
    }
    public Inventory selectionMyCardTransfer(){
        Inventory inventory = null;
        String name = Message.getNameMyCardTransfer();
        int size = KabibankCard.getAllCardInventory(player).size() + 1;

        int amount = 1;

        if (size <= 9){
            inventory = createInventory(1, name, player);
        } else if (size <= 18) {
            inventory = createInventory(2, name, player);
            amount = 2;
        } else if (size <= 27) {
            inventory = createInventory(3, name, player);
            amount = 3;
        } else if (size <= 36) {
            inventory = createInventory(4, name, player);
            amount = 4;
        } else if (size <= 45) {
            inventory = createInventory(5, name, player);
            amount = 5;
        }

        for (Card card : KabibankCard.getAllCardInventory(player)){
            ItemStack itemStack = ItemGui.selectionCards(card.getNumber(), card.getBalans(), card.getCurrency());

            inventory.addItem(itemStack);
        }

        inventory.addItem(ItemGui.previsionPage(), new ItemStack(MaterialGui.getTwoAirMaterial()));

        for (int i = 0; i <= 9 * amount - 1; i++){
            ItemStack itemStack = inventory.getItem(i);

            if (itemStack == null){
                inventory.setItem(i, new ItemStack(MaterialGui.getAirMaterial()));
            }
        }

        return inventory;

    }
    public Inventory selectionRecipientCardTransfer(TransferCard transferCard){

        int size = KabibankCard.getAllCardsPlayer(transferCard.getRecipientName(), transferCard.getRecipientUUID()).size() + 1;
        String name = Message.getNameRecipientCardTransfer();
        Inventory inventory = null;

        int amount = 1;

        if (size <= 9){
            inventory = createInventory(1, name, player);
        } else if (size <= 18) {
            inventory = createInventory(2, name, player);
            amount = 2;
        } else if (size <= 27) {
            inventory = createInventory(3, name, player);
            amount = 3;
        } else if (size <= 36) {
            inventory = createInventory(4, name, player);
            amount = 4;
        } else if (size <= 45) {
            inventory = createInventory(5, name, player);
            amount = 5;
        }

        for (Card card : KabibankCard.getAllCardsPlayer(transferCard.getRecipientName(), transferCard.getRecipientUUID())){
            ItemStack itemStack = ItemGui.cardRecipient(card.getNumber(), card.getBalans(), card.getPlayerName(), card.getCurrency());

            inventory.addItem(itemStack);
        }

        inventory.addItem(ItemGui.previsionPage(), new ItemStack(MaterialGui.getTwoAirMaterial()));

        for (int i = 0; i <= 9 * amount - 1; i++){
            ItemStack itemStack = inventory.getItem(i);

            if (itemStack == null){
                inventory.setItem(i, new ItemStack(MaterialGui.getAirMaterial()));
            }
        }

        return inventory;

    }
    public Inventory confirmationDeleteAtm(){
        int amount = 1;
        Inventory inventory = createInventory(amount, Message.getNameConfirmationDeleteAtm(), player);

        inventory.addItem(ItemGui.yesDeleteAtm(), ItemGui.noDeleteAtm(), ItemGui.previsionPage(), new ItemStack(MaterialGui.getTwoAirMaterial()));

        for (int i = 0; i <= 9 * amount - 1; i++){
            ItemStack itemStack = inventory.getItem(i);

            if (itemStack == null){
                inventory.setItem(i, new ItemStack(MaterialGui.getAirMaterial()));
            }
        }

        return inventory;
    }

    private static Inventory createInventory(int i, String name, Player player){
        return Bukkit.createInventory(player, 9 * i, FuncString.stringToComponent(name));
    }

    public static ItemStack createItem(Material material, String name, List<String> lore){
        ItemStack itemStack = new ItemStack(material);

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(FuncString.stringToComponent(name));
        itemMeta.lore(FuncString.stringListToComponent(lore));

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
    public static ItemStack createItem(Material material, String name, List<String> lore, Enchantment enchantment){
        ItemStack itemStack = new ItemStack(material);

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(FuncString.stringToComponent(name));
        itemMeta.lore(FuncString.stringListToComponent(lore));

        itemStack.setItemMeta(itemMeta);

        itemStack.addUnsafeEnchantment(enchantment, 1);

        return itemStack;
    }
    public static ItemStack createItem(Material material, String name) {
        ItemStack itemStack = new ItemStack(material);

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(FuncString.stringToComponent(name));

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }


    public static List<Card> cardsInventory(Player player){
        List<Card> cards = new ArrayList<>();
        Inventory inventory = player.getInventory();

        for (Card card : KabibankCard.getAllCardsPlayer(player)){
            if (inventory.contains(ItemPhysical.card(card.getNumber(), card.getCurrency(), player))) {
                cards.add(card);
            }
        }
        return cards;
    }

}
