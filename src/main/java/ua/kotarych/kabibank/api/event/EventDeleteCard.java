package ua.kotarych.kabibank.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class EventDeleteCard extends Event implements Cancellable {

    public EventDeleteCard(String playerName, UUID playerUUID, int numberCard, int balansCard, String currencyCard, Player player){
        this.playerName = playerName;
        this.playerUUID = playerUUID;
        this.numberCard = numberCard;
        this.balansCard = balansCard;
        this.currencyCard = currencyCard;

        this.player = player;
    }

    private static HandlerList handlerList = new HandlerList();
    private boolean cancel = false;

    private String playerName;
    private UUID playerUUID;

    private Player player;

    private int numberCard;
    private int balansCard;

    private String currencyCard;

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    public String getPlayerName() {
        return playerName;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public int getNumberCard() {
        return numberCard;
    }

    public int getBalansCard() {
        return balansCard;
    }

    public String getCurrencyCard() {
        return currencyCard;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlerList;
    }

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }
}
