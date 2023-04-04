package ua.kotarych.kabibank.tempData;

import org.bukkit.entity.Player;

public class ActionCard {

    public ActionCard(Player player, String action){
        this.player = player;
        this.action = action;

    }
    private Player player;
    private String action;

    public Player getPlayer() {
        return player;
    }
    public String get() {
        return action;
    }
    public void set(String action) {
        this.action = action;
    }
}
