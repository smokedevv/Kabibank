package ua.kotarych.kabibank.tempData;

import org.bukkit.entity.Player;
import ua.kotarych.kabibank.models.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TransferCards {

    private static List<TransferCard> cardList = new ArrayList<>();

    private static boolean isCreate(Player player){
        for (TransferCard transferCard : cardList){
            if (transferCard.getPlayer().equals(player)){
                return true;
            }
        }
        return false;
    }
    public static void addSender(Player player, Card card){
        if (isCreate(player)){
            for (TransferCard transferCard : cardList){
                if (transferCard.getPlayer().equals(player)){
                    transferCard.setSender(card);
                }
            }
        } else {
            cardList.add(new TransferCard(card, player, true));
        }
    }
    public static void addRecipient(Player player, Card card){
        if (isCreate(player)){
            for (TransferCard transferCard : cardList){
                if (transferCard.getPlayer().equals(player)){
                    transferCard.setRecipient(card);
                }
            }
        } else {
            cardList.add(new TransferCard(card, player, false));
        }
    }
    public static void addNameRecipient(String name, UUID uuid, Player player){
        if (isCreate(player)) {
            for (TransferCard transferCard : cardList){
                if (transferCard.getPlayer().equals(player)){
                    transferCard.setRecipientName(name);
                    transferCard.setRecipientUUID(uuid);
                }
            }
        } else {
            cardList.add(new TransferCard(name, uuid, player));
        }
    }
    public static TransferCard get(Player player){
        for (TransferCard transferCard : cardList){
            if (transferCard.getPlayer().equals(player)){
                return transferCard;
            }
        }
        return null;
    }
}
