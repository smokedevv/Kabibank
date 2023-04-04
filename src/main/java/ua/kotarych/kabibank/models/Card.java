package ua.kotarych.kabibank.models;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import ua.kotarych.kabibank.files.CoolNumber;

import java.util.UUID;

public class Card {

    public Card(Player player, String currency){
        this.playerUUID = player.getUniqueId();
        this.playerName = player.getName();

        this.currency = currency;

        CoolNumber.getNumber().addCard();
        CoolNumber.save();
        number += CoolNumber.getNumber().getCard();

        balans = 0;
    }

    private UUID playerUUID;
    private String playerName;

    private int balans;
    private int number;
    private String currency;

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public void setPlayerUUID(UUID playerUUID) {
        this.playerUUID = playerUUID;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getBalans() {
        return balans;
    }

    public void setBalans(int balans) {
        this.balans = balans;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void addBalans(int amount){
        this.balans += amount;
    }
    public void removeBalans(int amount){
        this.balans -= amount;
    }
    public void setPlayer(Player player){
        this.playerUUID = player.getUniqueId();
        this.playerName = player.getName();
    }

}
