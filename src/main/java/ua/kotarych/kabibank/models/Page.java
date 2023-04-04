package ua.kotarych.kabibank.models;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import ua.kotarych.kabibank.config.MaterialGui;
import ua.kotarych.kabibank.func.ItemGui;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Page {

    public Page(int amount, String name) {
        this.amount = amount;
        this.name = name;

    }

    public boolean add(ItemStack itemStack) {
        return itemStacks.add(itemStack);
    }

    private int amount;
    private String name;
    private List<ItemStack> itemStacks = new ArrayList<>();

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ItemStack> getItemStacks() {
        return itemStacks;
    }

    public void setItemStacks(List<ItemStack> itemStacks) {
        this.itemStacks = itemStacks;
    }
}
