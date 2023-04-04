package ua.kotarych.kabibank.models;

import org.bukkit.Material;
import org.bukkit.World;
import ua.kotarych.kabibank.config.Config;
import ua.kotarych.kabibank.files.CoolAtm;
import ua.kotarych.kabibank.files.CoolNumber;

import java.util.HashMap;
import java.util.Map;

public class Atm {

    public Atm(int[] coordinate, String world){
        this.coordinate = coordinate;

        this.blockX = coordinate[0];
        this.blockY = coordinate[1];
        this.blockZ = coordinate[2];

        this.world = world;

        currencyBalansATM();

        CoolNumber.getNumber().addAtm();
        CoolNumber.save();
        this.numberATM += CoolNumber.getNumber().getAtm();
    }
    private void currencyBalansATM(){
        for (String currency : Config.getCurrencyList()){
            this.currencyBalans.put(currency, 0);
        }
    }
    private int amountCurrency(String currency){
        return currencyBalans.get(currency);
    }
    public boolean addBalans(String currency, int amount){
        if (currencyBalans.containsKey(currency)){
            int currencyAmount = currencyBalans.get(currency);
            int newBalans = currencyAmount + amount;

            currencyBalans.remove(currency);
            currencyBalans.put(currency, newBalans);

            return true;
        }
        return false;
    }
    public boolean removeBalans(String currency, int amount){
        if (currencyBalans.containsKey(currency)){
            int currencyAmount = currencyBalans.get(currency);
            int newBalans = currencyAmount - amount;

            currencyBalans.remove(currency);
            currencyBalans.put(currency, newBalans);

            return true;
        }
        return false;
    }
    public boolean isContainsMoney(String currency, int amount){
        return amountCurrency(currency) >= amount;
    }

    private int[] coordinate;
    private int blockX;
    private int blockY;
    private int blockZ;
    private String world;

    private int numberATM;

    private Map<String, Integer> currencyBalans = new HashMap<>();

    public int[] getCoordinate() {
        return coordinate;
    }

    public int getBlockX() {
        return blockX;
    }
    public int getBlockY() {
        return blockY;
    }
    public int getBlockZ() {
        return blockZ;
    }
    public int getNumberATM() {
        return numberATM;
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public Map<String, Integer> getCurrencyBalans() {
        return currencyBalans;
    }
}
