package ua.kotarych.kabibank.api.event;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import ua.kotarych.kabibank.commands.BankCommand;

public class EventCreateATM extends Event implements Cancellable {

    public EventCreateATM(Player player, int[] coordinate){
        this.player = player;
        this.coordinate = coordinate;

        blockX = coordinate[0];
        blockY = coordinate[1];
        blockZ = coordinate[2];
    }

    private static final HandlerList handlerList = new HandlerList();

    private Player player;
    private int[] coordinate;
    private int blockX;
    private int blockY;
    private int blockZ;

    private boolean cancel = false;

    public Player getPlayer() {
        return player;
    }

    public int[] getCoordinate() {
        return coordinate;
    }

    public int getBlockX() {
        return blockX;
    }

    public int getBlockY() {
        return blockY;
    }

    public int getBlockZ() {
        return blockZ;
    }

    public void setCoordinate(int[] coordinate) {
        blockX = coordinate[0];
        blockY = coordinate[1];
        blockZ = coordinate[2];
        this.coordinate = coordinate;
    }

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;

    }
    @Override
    public @NotNull HandlerList getHandlers() {
        return handlerList;
    }
    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
