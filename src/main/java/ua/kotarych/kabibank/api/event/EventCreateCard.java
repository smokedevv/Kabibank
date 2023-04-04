package ua.kotarych.kabibank.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import ua.kotarych.kabibank.models.Card;

public class EventCreateCard extends Event implements Cancellable {

    public EventCreateCard(Player player, String currency, int numberCard, Card card, int numberAtm){
        this.player = player;
        this.currency = currency;
        this.numberCard = numberCard;
        this.card = card;

        this.numberAtm = numberAtm;
    }
    private Player player;
    private String currency;
    private int numberCard;
    private int balansCard;

    private Card card;
    private int numberAtm;

    private boolean cancel = false;

    public Player getPlayer() {
        return player;
    }

    public String getCurrency() {
        return currency;
    }

    public int getNumberCard() {
        return numberCard;
    }

    public int getBalansCard() {
        return balansCard;
    }

    public Card getCard() {
        return card;
    }

    public int getNumberAtm() {
        return numberAtm;
    }

    public void setCurrency(String currency) {
        this.card.setCurrency(currency);
        this.currency = currency;
    }

    public void setNumberCard(int numberCard) {
        this.card.setNumber(numberCard);
        this.numberCard = numberCard;
    }

    public void setBalansCard(int balansCard) {
        this.card.setBalans(balansCard);
        this.balansCard = balansCard;
    }

    private static final HandlerList handlerList = new HandlerList();

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlerList;
    }
    public static HandlerList getHandlerList(){
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
