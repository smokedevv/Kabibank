package ua.kotarych.kabibank.func;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;
import ua.kotarych.kabibank.config.MaterialGui;

import java.util.ArrayList;
import java.util.List;

public class FuncString {

    public static String componentToString(Component c) {
        return LegacyComponentSerializer.legacyAmpersand().serialize(c);
    }

    public static Component stringToComponent(String s) {
        return LegacyComponentSerializer.legacyAmpersand().deserialize(s);
    }

    public static List<Component> stringListToComponent(List<String> list) {
        List<Component> components = new ArrayList<>(list.size());

        for (String s : list) {
            components.add(stringToComponent(s));
        }
        return components;
    }
    public static List<String> replacePersonnelLore(String menuStatus){
        List<String> strings = new ArrayList<>();
        for (String s : MaterialGui.getPersonnelLore()){
            strings.add(s.replace("%menuStatus%", menuStatus));
        }
        return strings;
    }
    public static List<String> replaceAddBalansLore(String amount){
        List<String> strings = new ArrayList<>();
        for (String s : MaterialGui.getAddBalansLore()){
            strings.add(s.replace("%amount%", amount));
        }
        return strings;
    }
    public static List<String> replaceRemoveBalansLore(String amount){
        List<String> strings = new ArrayList<>();
        for (String s : MaterialGui.getRemoveBalansLore()){
            strings.add(s.replace("%amount%", amount));
        }
        return strings;
    }
    public static List<String> replaceCardLore(String currency, Player player){
        List<String> strings = new ArrayList<>();
        for (String s : MaterialGui.getCardLore()){
            strings.add(s.replace("%currency%", currency).replace("%player%", player.getName()));
        }
        return strings;
    }
    public static List<String> replaceSelectionCardLore(int balans, String currency){
        List<String> strings = new ArrayList<>();
        for (String s : MaterialGui.getSelectionCardLore()){
            strings.add(s.replace("%balans%", String.valueOf(balans)).replace("%currency%", currency));
        }

        return strings;
    }
    public static List<String> replaceYesDeleteCardLore(int number){
        List<String> strings = new ArrayList<>();
        for (String s : MaterialGui.getYesDeleteCardLore()){
            strings.add(s.replace("%number%", String.valueOf(number)));
        }

        return strings;
    }

    public static List<String> replaceNoDeleteCardLore(int number){
        List<String> strings = new ArrayList<>();
        for (String s : MaterialGui.getNoDeleteCardLore()){
            strings.add(s.replace("%number%", String.valueOf(number)));
        }

        return strings;
    }
    public static List<String> replaceInfoCardLore(int balans, String currency){
        List<String> strings = new ArrayList<>();
        for (String s : MaterialGui.getCardInfoLore()){
            strings.add(s.replace("%balans%", String.valueOf(balans)).replace("%currency%", currency));
        }

        return strings;
    }
    public static List<String> replaceCardRecipientLore(String currency, String player, int balans){
        List<String> strings = new ArrayList<>();

        for (String s : MaterialGui.getCardRecipientLore()){
            strings.add(s.replace("%currency%", currency).replace("%player%", player).replace("%balans%", String.valueOf(balans)));
        }

        return strings;
    }
}
