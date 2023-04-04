package ua.kotarych.kabibank.tempData;

import org.bukkit.entity.Player;
import ua.kotarych.kabibank.models.SwitchingPages;

import java.util.HashMap;
import java.util.Map;

public class PagesSaves {

    private static Map<Player, SwitchingPages> switchingPagesMap = new HashMap<>();

    public static void add(Player player, SwitchingPages pages){
        switchingPagesMap.remove(player);
        switchingPagesMap.put(player, pages);

    }
    public static SwitchingPages get(Player player){
        return switchingPagesMap.get(player);
    }
}
