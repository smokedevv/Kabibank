package ua.kotarych.kabibank.tempData;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import ua.kotarych.kabibank.models.Card;

import java.util.ArrayList;
import java.util.List;

public class CardsData {

    private static List<CardData> cardData = new ArrayList<>();

    private static boolean is(Player player){
        for (CardData cardData1 : cardData){
            if (cardData1.getPlayer().equals(player)){
                return true;
            }
        }
        return false;
    }
    public static void add(Player player, Card card, boolean add){
        if (is(player)){
            for (CardData cardData1 : cardData){
                if (cardData1.getPlayer().equals(player)){
                    cardData1.update(card);
                    cardData1.setAdd(add);
                }
            }
        } else {
            cardData.add(new CardData(player, card, add));
        }
    }
    public static void deleteCard(Player player, Card card, boolean isDeleteCard){
        if (is(player)){
            for (CardData cardData1 : cardData){
                if (cardData1.getPlayer().equals(player)){
                    cardData1.update(card);
                    cardData1.setDeleteCard(isDeleteCard);
                }
            }
        } else {
            cardData.add(new CardData(player, card, true, false));
        }
    }
    public static CardData get(Player player){
        for (CardData cardData1 : cardData){
            if (cardData1.getPlayer().equals(player)){
                return cardData1;
            }
        }
        return null;
    }
}
