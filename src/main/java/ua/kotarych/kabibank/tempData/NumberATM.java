package ua.kotarych.kabibank.tempData;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class NumberATM {
    public NumberATM(Player player, int number) {
        this.player = player;
        this.number = number;
    }
    private Player player;
    private int number;

    public Player getPlayer() {
        return player;
    }

    public int getNumber() {
        return number;
    }
}
