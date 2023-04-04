package ua.kotarych.kabibank.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class EventTransferMoney extends Event implements Cancellable {

    public EventTransferMoney(Player player, int removeAmount, int addAmount, int numberCardSender, int numberCardRecipient, String addCurrency, String removeCurrency, String nameSender, String nameRecipient, UUID uuidSender, UUID uuidRecipient){
        this.player = player;
        this.removeAmount = removeAmount;
        this.addAmount = addAmount;
        this.numberCardSender = numberCardSender;
        this.numberCardRecipient = numberCardRecipient;
        this.addCurrency = addCurrency;
        this.removeCurrency = removeCurrency;
        this.nameSender = nameSender;
        this.nameRecipient = nameRecipient;
        this.uuidSender = uuidSender;
        this.uuidRecipient = uuidRecipient;

    }

    private static HandlerList handlerList = new HandlerList();
    private Player player;

    private int removeAmount;
    private int addAmount;
    private int numberCardSender;
    private int numberCardRecipient;

    private String addCurrency;
    private String removeCurrency;

    private String nameSender;
    private String nameRecipient;

    private UUID uuidSender;
    private UUID uuidRecipient;

    public void setAddAmount(int addAmount) {
        this.addAmount = addAmount;
    }

    public void setRemoveAmount(int removeAmount) {
        this.removeAmount = removeAmount;
    }

    private boolean cancel = false;

    public Player getPlayer() {
        return player;
    }

    public int getRemoveAmount() {
        return removeAmount;
    }

    public int getAddAmount() {
        return addAmount;
    }

    public int getNumberCardSender() {
        return numberCardSender;
    }

    public int getNumberCardRecipient() {
        return numberCardRecipient;
    }

    public String getAddCurrency() {
        return addCurrency;
    }

    public String getRemoveCurrency() {
        return removeCurrency;
    }

    public String getNameSender() {
        return nameSender;
    }

    public String getNameRecipient() {
        return nameRecipient;
    }

    public UUID getUuidSender() {
        return uuidSender;
    }

    public UUID getUuidRecipient() {
        return uuidRecipient;
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
}
