package ua.kotarych.kabibank.tempData;

import org.bukkit.entity.Player;
import ua.kotarych.kabibank.models.Card;

import java.util.UUID;

public class TransferCard {

    public TransferCard(Card recipient, Card sender, Player player){
        this.recipient = recipient;
        this.sender = sender;
        this.player = player;

    }
    public TransferCard(Card card, Player player, boolean isSender){
        if (isSender) {
            this.sender = card;

        } else {
            this.recipient = card;
        }
        this.player = player;

    }
    public TransferCard(String recipientName, UUID recipientUUID, Player player){
        this.recipientName = recipientName;
        this.player = player;
    }

    private Card recipient;
    private Card sender;

    private Player player;
    private String recipientName;
    private UUID recipientUUID;

    public Card getRecipient() {
        return recipient;
    }

    public void setRecipient(Card recipient) {
        this.recipient = recipient;
    }

    public Card getSender() {
        return sender;
    }

    public void setSender(Card sender) {
        this.sender = sender;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public UUID getRecipientUUID() {
        return recipientUUID;
    }

    public void setRecipientUUID(UUID recipientUUID) {
        this.recipientUUID = recipientUUID;
    }
}
