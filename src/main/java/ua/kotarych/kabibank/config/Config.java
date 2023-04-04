package ua.kotarych.kabibank.config;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.*;

public class Config extends CustomConfig {

    public static void load() {
        currencyList = config.getStringList("currencyList");
        loadMaterial(config);
        dataPlayerCard = config.getString("dataPlayerCard");
        maxCardCreate = config.getInt("maxCardCreate");
        ignoreBalansATDeleteCard = config.getBoolean("ignoreBalansATDeleteCard");
        loadMaxCardCurrency();
    }

    public static void reload() {
        getPlugin().reloadConfig();
        FileConfiguration newConfig = getPlugin().getConfig();

        currencyList = newConfig.getStringList("currencyList");
        loadMaterial(newConfig);
        dataPlayerCard = newConfig.getString("dataPlayerCard");
        maxCardCreate = newConfig.getInt("maxCardCreate");
        ignoreBalansATDeleteCard = newConfig.getBoolean("ignoreBalansATDeleteCard");
        loadMaxCardCurrency();

        getPlugin().saveConfig();
    }

    private static FileConfiguration config = getPlugin().getConfig();
    private static List<String> currencyList;
    private static Map<String, Material> materialCurrency = new HashMap<>();
    private static Map<String , Integer> maxCardCurrency = new HashMap<>();
    private static String dataPlayerCard;

    private static int maxCardCreate;

    private static boolean ignoreBalansATDeleteCard;


    public static List<String> getCurrencyList() {
        return currencyList;
    }
    public static void loadMaterial(FileConfiguration config){
        for (String s : getCurrencyList()){
            String material = config.getString("material" + s);
            materialCurrency.put(s, Material.getMaterial(material));
        }
    }
    public static void loadMaxCardCurrency(){
        for (String s : getCurrencyList()){
            if (config.getInt("maxCardCreate" + s) != 0) {
                maxCardCurrency.put(s, config.getInt("maxCardCreate" + s));
            }
        }
    }
    public static Material getMaterialCurrency(String currency) {
        return materialCurrency.get(currency);
    }
    public static int getMaxCardCurrency(String currency){
        return maxCardCurrency.getOrDefault(currency, 0);
    }
    public static int getMaxCardPlayer(Player player){
        getPlugin().saveConfig();
        return Config.config.getInt("maxCardCreate" + player.getName());
    }
    public static int getMaxCardCurrencyPlayer(Player player, String currency){
        getPlugin().saveConfig();
        return Config.config.getInt("maxCardCreate" + currency + player.getName());
    }
    public static int exchangeRate(String smallerCurrency, String greaterCurrency){
        getPlugin().saveConfig();

        int exchangeRate = getPlugin().getConfig().getInt("exchangeRate" + smallerCurrency + "To" + greaterCurrency);
        return Math.max(exchangeRate, 0);
    }

    public static String getDataPlayerCard() {
        return dataPlayerCard;
    }

    public static int getMaxCardCreate() {
        return maxCardCreate;
    }

    public static boolean isIgnoreBalansATDeleteCard() {
        return ignoreBalansATDeleteCard;
    }
}
