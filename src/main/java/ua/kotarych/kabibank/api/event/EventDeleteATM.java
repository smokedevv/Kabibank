package ua.kotarych.kabibank.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class EventDeleteATM extends Event implements Cancellable {
    public EventDeleteATM(Player player, int[] coordinate, int numberAtm, boolean command){
        this.player = player;
        this.coordinate = coordinate;
        this.numberAtm = numberAtm;
        this.command = command;

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

    private int numberAtm;
    private boolean cancel = false;
    private boolean command;

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

    public int getNumberAtm() {
        return numberAtm;
    }

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    public boolean isCommand() {
        return command;
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
