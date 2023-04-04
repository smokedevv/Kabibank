package ua.kotarych.kabibank.tempData;

import org.bukkit.entity.Player;
import ua.kotarych.kabibank.tempData.NumberATM;

import java.util.ArrayList;
import java.util.List;

public class NumbersATM {

    private static List<NumberATM> numberATMS = new ArrayList<>();

    public static void save(Player player, int number){
        NumberATM numberATM = new NumberATM(player, number);
        numberATMS.removeIf(numberATM1 -> numberATM1.getPlayer().equals(player));
        numberATMS.add(numberATM);
    }
    public static NumberATM get(Player player){
        for (NumberATM numberATM : numberATMS){
            if (numberATM.getPlayer().equals(player)){
                return numberATM;
            }
        }
        return null;
    }
}
