package ua.kotarych.kabibank.tempData;

import org.bukkit.entity.Player;
import ua.kotarych.kabibank.models.Card;

public class CardData {

    public CardData(Player player, Card card, boolean add){
        this.player = player;
        this.card = card;
        this.add = add;
    }
    public CardData(Player player, Card card, boolean deleteCard, boolean add){
        this.player = player;
        this.card = card;
        this.add = add;
        this.deleteCard = deleteCard;

    }

    private Player player;
    private Card card;
    private boolean add;
    private boolean deleteCard;

    public Player getPlayer() {
        return player;
    }

    public Card getCard() {
        return card;
    }

    public boolean isAdd() {
        return add;
    }

    public boolean isDeleteCard() {
        return deleteCard;
    }

    public void update(Card card){
        this.card = card;
    }
    public void setAdd(boolean add){
        this.add = add;
    }

    public void setDeleteCard(boolean deleteCard) {
        this.deleteCard = deleteCard;
    }
}
