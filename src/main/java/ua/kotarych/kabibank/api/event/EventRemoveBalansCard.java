package ua.kotarych.kabibank.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class EventRemoveBalansCard extends Event implements Cancellable {

    public EventRemoveBalansCard(Player player, int numberCard, int amount, int atmNumber){
        this.player = player;
        this.numberCard = numberCard;
        this.amount = amount;

        this.atmNumber = atmNumber;
    }

    private static final HandlerList handlerList = new HandlerList();

    private Player player;
    private int numberCard;
    private int amount;

    private int atmNumber;

    private boolean cancel = false;

    public Player getPlayer() {
        return player;
    }

    public int getNumberCard() {
        return numberCard;
    }

    public int getAmount() {
        return amount;
    }

    public int getAtmNumber() {
        return atmNumber;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
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

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
