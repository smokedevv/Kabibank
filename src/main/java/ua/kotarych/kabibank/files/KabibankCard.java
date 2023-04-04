package ua.kotarych.kabibank.files;

import com.google.gson.Gson;
import org.bukkit.entity.Player;
import ua.kotarych.kabibank.config.Config;
import ua.kotarych.kabibank.config.CustomConfig;
import ua.kotarych.kabibank.func.ItemGui;
import ua.kotarych.kabibank.func.ItemPhysical;
import ua.kotarych.kabibank.models.Card;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class KabibankCard {

    private static List<Card> cards = new ArrayList<>();

    public static List<Card> getCards() {
        return cards;
    }

    public static void addBalans(int numberCard, int amount){
        for (Card card : cards){
            if (card.getNumber() == numberCard){
                card.addBalans(amount);
            }
        }
    }
    public static Card createCard(Player player, String currency){
        Card card = new Card(player, currency);
        cards.add(card);
        save();

        return card;
    }

    public static void removeBalans(int numberCard, int amount){
        for (Card card : cards){
            if (card.getNumber() == numberCard){
                card.removeBalans(amount);
            }
        }
    }
    public static void deleteCard(int numberCard){
        cards.removeIf(card -> card.getNumber() == numberCard);
    }
    public static boolean isBalans(int numberCard, int amount){
        for (Card card : cards){
            if (card.getNumber() == numberCard){
                if (card.getBalans() >= amount){
                    return true;
                }
            }
        }
        return false;
    }
    public static void setCurrency(int numberCard, String currency){
        for (Card card : cards){
            if (card.getNumber() == numberCard){
                card.setCurrency(currency);
            }
        }
    }
    public static void setNumberCard(int numberCard, int newNumberCard){
        for (Card card : cards){
            if (card.getNumber() == numberCard){
                card.setNumber(newNumberCard);
            }
        }
    }
    public static void setBalansCard(int numberCard, int balans){
        for (Card card : cards){
            if (card.getNumber() == numberCard){
                card.setBalans(balans);
            }
        }
    }

    public static List<Card> getAllCardsPlayer(Player player){

        List<Card> cardPlayer = new ArrayList<>();

        if (Config.getDataPlayerCard().equalsIgnoreCase("UUID")){
            for (Card card : cards){
                if (card.getPlayerUUID().equals(player.getUniqueId())){
                    cardPlayer.add(card);
                }
            }

        } else if (Config.getDataPlayerCard().equalsIgnoreCase("NAME")) {
            for (Card card : cards){
                if (card.getPlayerName().equals(player.getName())){
                    cardPlayer.add(card);
                }
            }
        }
        return cardPlayer;
    }
    public static List<Card> getAllCardsPlayer(String name, UUID uuid){

        List<Card> cardPlayer = new ArrayList<>();

        if (Config.getDataPlayerCard().equalsIgnoreCase("UUID")){
            for (Card card : cards){
                if (card.getPlayerUUID().equals(uuid)){
                    cardPlayer.add(card);
                }
            }

        } else if (Config.getDataPlayerCard().equalsIgnoreCase("NAME")) {
            for (Card card : cards){
                if (card.getPlayerName().equals(name)){
                    cardPlayer.add(card);
                }
            }
        }
        return cardPlayer;
    }
    public static List<Card> getAllCardInventory(Player player){
        List<Card> cardList = new ArrayList<>();

        for (Card card : getAllCardsPlayer(player)){
            if (player.getInventory().contains(ItemPhysical.card(card.getNumber(), card.getCurrency(), player))){
                cardList.add(card);
            }
        }
        return cardList;
    }
    public static List<Card> getCardsCurrencyPlayer(Player player, String currency){

        List<Card> cardPlayer = new ArrayList<>();

        if (Config.getDataPlayerCard().equalsIgnoreCase("UUID")){
            for (Card card : cards){
                if (card.getPlayerUUID().equals(player.getUniqueId())){
                    if (card.getCurrency().equals(currency)) {
                        cardPlayer.add(card);
                    }
                }
            }

        } else if (Config.getDataPlayerCard().equalsIgnoreCase("NAME")) {
            for (Card card : cards){
                if (card.getPlayerName().equals(player.getName())){
                    if (card.getCurrency().equals(currency)) {
                        cardPlayer.add(card);
                    }
                }
            }
        }
        return cardPlayer;
    }
    public static int getAmountCardPlayer(Player player){

        int cardsAmount = 0;

        if (Config.getDataPlayerCard().equalsIgnoreCase("UUID")){
            for (Card card : cards){
                if (card.getPlayerUUID().equals(player.getUniqueId())){
                    cardsAmount += 1;
                }
            }

        } else if (Config.getDataPlayerCard().equalsIgnoreCase("NAME")) {
            for (Card card : cards){
                if (card.getPlayerName().equals(player.getName())){
                    cardsAmount += 1;
                }
            }
        }
        return cardsAmount;
    }
    public static int getAmountCardCurrencyPlayer(Player player, String currency){

        int cardsAmount = 0;

        if (Config.getDataPlayerCard().equalsIgnoreCase("UUID")){
            for (Card card : cards){
                if (card.getPlayerUUID().equals(player.getUniqueId())){
                    if (card.getCurrency().equals(currency)) {
                        cardsAmount += 1;
                    }
                }
            }

        } else if (Config.getDataPlayerCard().equalsIgnoreCase("NAME")) {
            for (Card card : cards){
                if (card.getPlayerName().equals(player.getName())){
                    if (card.getCurrency().equals(currency)) {
                        cardsAmount += 1;
                    }
                }
            }
        }
        return cardsAmount;
    }

    public static void save(){

        Gson gson = new Gson();

        File file = new File(CustomConfig.getPlugin().getDataFolder(), "data/card.json");

        file.getParentFile().mkdir();
        Writer writer = null;

        try {
            if (!file.exists()){
                file.createNewFile();
            }
            writer = new FileWriter(file,false);
            gson.toJson(cards, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void load() {

        Gson gson = new Gson();
        File file = new File(CustomConfig.getPlugin().getDataFolder(), "data/card.json");

        if (file.exists()) {
            Reader reader = null;
            try {
                reader = new FileReader(file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            Card[] card = gson.fromJson(reader, Card[].class);

            cards = new ArrayList<>(Arrays.asList(card));
        }
    }
}
