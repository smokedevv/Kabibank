package ua.kotarych.kabibank.func;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import ua.kotarych.kabibank.config.MaterialGui;
import ua.kotarych.kabibank.models.Card;
import ua.kotarych.kabibank.models.Gui;

public class ItemPhysical extends Gui {

    public ItemPhysical(Player player) {
        super(player);
    }

    public static ItemStack card(int number, String currency, Player player){
        ItemStack itemStack;
        if (MaterialGui.isEnchantedCard()){
            itemStack = createItem(MaterialGui.getCard(), MaterialGui.getCardName().replace("%number%", String.valueOf(number)),
                    FuncString.replaceCardLore(currency, player), Enchantment.OXYGEN);

        } else {
            itemStack = createItem(MaterialGui.getCard(), MaterialGui.getCardName().replace("%number%", String.valueOf(number)),
                    FuncString.replaceCardLore(currency, player));
        }
        return itemStack;
    }
}
