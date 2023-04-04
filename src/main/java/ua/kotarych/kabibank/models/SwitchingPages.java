package ua.kotarych.kabibank.models;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import ua.kotarych.kabibank.config.MaterialGui;
import ua.kotarych.kabibank.config.Message;
import ua.kotarych.kabibank.event.ClickGui;
import ua.kotarych.kabibank.func.FuncString;
import ua.kotarych.kabibank.func.ItemGui;
import ua.kotarych.kabibank.tempData.NumbersATM;
import ua.kotarych.kabibank.tempData.PagesSaves;

import java.util.ArrayList;
import java.util.List;

public class SwitchingPages implements InventoryHolder {

    private final Player player;
    private int currentPage;
    private final List<Inventory> pages;
    private final String name;
    public SwitchingPages(Player player, String name) {
        this.player = player;
        this.currentPage = 0;
        this.pages = new ArrayList<>();
        this.name = name;

        this.pages.add(defaultInv());
    }

    public Inventory defaultInv(){
        Inventory inventory = Bukkit.createInventory(player, 4 * 9, FuncString.stringToComponent(name));

        inventory.setItem(34, ItemGui.previsionPage());
        inventory.setItem(35, ItemGui.nextPage());

        return inventory;
    }

    public void addItem(ItemStack item) {

        if (this.pages.get(this.currentPage).firstEmpty() == -1) {

            this.pages.add(defaultInv());
            this.currentPage = this.pages.size() - 1;
        }
        this.pages.get(this.currentPage).addItem(item);
    }

    public void openNextPage() {

        if (this.currentPage < this.pages.size() - 1) {
            this.currentPage++;

            Inventory inventory = this.pages.get(this.currentPage);
            for (int i = 0; i < 9 * 4 - 2; i++){
                ItemStack itemStack = inventory.getItem(i);

                if (itemStack == null){
                    inventory.setItem(i, new ItemStack(MaterialGui.getAirMaterial()));
                }
            }
            this.player.openInventory(inventory);
        }
    }

    public void openPreviousPage(Gui gui) {

        if (name.equals(Message.getNameTransferOffline()) | name.equals(Message.getNameTransferOnline())) {
            if (this.currentPage - 1 == -1) {
                player.openInventory(gui.optionTransferOnlineOrOffline());
                return;
            }
        }

        if (this.currentPage > 0) {
            this.currentPage--;

            Inventory inventory = this.pages.get(this.currentPage);
            for (int i = 0; i < 9 * 4 - 2; i++){
                ItemStack itemStack = inventory.getItem(i);

                if (itemStack == null){
                    inventory.setItem(i, new ItemStack(MaterialGui.getAirMaterial()));
                }
            }
            this.player.openInventory(inventory);
        }
    }
    public boolean isItem(ItemStack itemStack){
        for (Inventory inventory : pages){
            for (ItemStack itemStack1 : inventory.getContents()){
                if (itemStack1 != null) {
                    if (itemStack1.equals(itemStack)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public Inventory getInventory() {
        this.currentPage = 0;
        return this.pages.get(this.currentPage);
    }




}
