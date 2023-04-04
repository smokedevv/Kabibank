package ua.kotarych.kabibank.event;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import ua.kotarych.kabibank.api.event.*;
import ua.kotarych.kabibank.files.CoolAtm;
import ua.kotarych.kabibank.func.FuncString;
import ua.kotarych.kabibank.config.Message;
import ua.kotarych.kabibank.models.Atm;
import ua.kotarych.kabibank.models.Gui;
import ua.kotarych.kabibank.models.SwitchingPages;
import ua.kotarych.kabibank.tempData.NumbersATM;
import ua.kotarych.kabibank.tempData.PagesSaves;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class OpenGui implements Listener {

    @EventHandler
    public static void clickBlock(PlayerInteractEvent e) {
        Action action = e.getAction();
        Block block = e.getClickedBlock();
        Player player = e.getPlayer();

        if (block != null) {
            List<Atm> atms = CoolAtm.getAtms();
            if (action.equals(Action.LEFT_CLICK_BLOCK) | action.equals(Action.RIGHT_CLICK_BLOCK)) {
                for (Atm atm : atms) {

                    if (Arrays.equals(getCoordinate(block.getLocation()), atm.getCoordinate())) {
                        if (atm.getWorld().equals(player.getWorld().getName())) {
                            if (player.hasPermission("Kabibank.mainMenu")) {
                                Gui gui = new Gui(player);

                                player.openInventory(gui.main(Objects.requireNonNull(CoolAtm.getATM(atm.getCoordinate())).getNumberATM()));
                                NumbersATM.save(player, Objects.requireNonNull(CoolAtm.getATM(atm.getCoordinate())).getNumberATM());
                            } else {
                                player.sendMessage(FuncString.stringToComponent(Message.getErrorPermissionMenu()));
                            }
                        }
                    }
                }
            }
        }
    }

    public static int[] getCoordinate(Location location) {

        int[] coordinate = new int[3];
        coordinate[0] = location.getBlockX();
        coordinate[1] = location.getBlockY();
        coordinate[2] = location.getBlockZ();

        return coordinate;
    }
    public static Location getLocation(int[] coordinate, World world){

        return new Location(world, coordinate[0], coordinate[1], coordinate[2]);
    }
}
