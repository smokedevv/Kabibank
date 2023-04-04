package ua.kotarych.kabibank.tempData;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ActionCards {

    private static List<ActionCard> actionCardList = new ArrayList<>();

    private static boolean isContains(Player player){
        for (ActionCard actionCard : actionCardList){
            if (actionCard.getPlayer().equals(player)){
                return true;
            }
        }
        return false;
    }
    public static void update(Player player, String action){
        if (isContains(player)){
            for (ActionCard actionCard1 : actionCardList){
                if (actionCard1.getPlayer().equals(player)){
                    actionCard1.set(action);
                }
            }

        } else {
            actionCardList.add(new ActionCard(player, action));
        }
    }
    public static String get(Player player){
        for (ActionCard actionCard : actionCardList){
            if (actionCard.getPlayer().equals(player)){
                return actionCard.get();
            }
        }
        return null;
    }
}
