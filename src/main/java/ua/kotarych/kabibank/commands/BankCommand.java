package ua.kotarych.kabibank.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.RayTraceResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ua.kotarych.kabibank.func.FuncString;
import ua.kotarych.kabibank.api.event.EventCreateATM;
import ua.kotarych.kabibank.api.event.EventDeleteATM;
import ua.kotarych.kabibank.config.Config;
import ua.kotarych.kabibank.config.Message;
import ua.kotarych.kabibank.files.CoolAtm;
import ua.kotarych.kabibank.models.Atm;

import java.util.List;

public class BankCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("create")) {
                    if (player.hasPermission("Kabibank.createBank")) {
                        int[] coordinate = getCoordinate(player);
                        EventCreateATM event = new EventCreateATM(player, coordinate);

                        Atm atm = new Atm(event.getCoordinate(), player.getWorld().getName());
                        if (CoolAtm.isAddATM(atm)) {
                            Bukkit.getPluginManager().callEvent(event);
                        }
                        if (!event.isCancelled()) {
                            if (CoolAtm.addATM(atm)) {
                                CoolAtm.save();

                                player.sendMessage(FuncString.stringToComponent(Message.getSuccessfullyCreateATM().replace("%coordinate%", coordinate[0] + ", " + coordinate[1] + ", " + coordinate[2])));
                            } else {
                                player.sendMessage(FuncString.stringToComponent(Message.getErrorCreateATM()));
                            }
                        }
                    } else {
                        player.sendMessage(FuncString.stringToComponent(Message.getErrorPermission()));
                    }
                } else if (args[0].equalsIgnoreCase("list")) {
                    if (player.hasPermission("Kabibank.listBank")) {
                        if (CoolAtm.getAtms().size() > 0) {
                            for (Atm atm : CoolAtm.getAtms()) {
                                int[] coordinate = atm.getCoordinate();
                                player.sendMessage(FuncString.stringToComponent(Message.getAtmListMain().replace("%number%", String.valueOf(atm.getNumberATM())).replace("%coordinate%",
                                        coordinate[0] + ", " + coordinate[1] + ", " + coordinate[2])));

                                for (String currency : Config.getCurrencyList()) {
                                    if (atm.getCurrencyBalans().containsKey(currency)) {
                                        player.sendMessage(FuncString.stringToComponent(Message.getAtmListCurrency().replace("%number%", String.valueOf(atm.getNumberATM())).
                                                replace("%currency%", currency).replace("%balans%", String.valueOf(atm.getCurrencyBalans().get(currency)))));
                                    }
                                }
                            }
                        } else {
                            player.sendMessage(FuncString.stringToComponent(Message.getErrorListATM()));
                        }
                    } else {
                        player.sendMessage(FuncString.stringToComponent(Message.getErrorPermission()));
                    }
                } else if (args[0].equalsIgnoreCase("help")) {
                    if (player.hasPermission("Kabibank.helpBank")){

                        for (String s : Message.getHelpMessageBank()){
                            player.sendMessage(FuncString.stringToComponent(s));
                        }

                    } else {
                        player.sendMessage(FuncString.stringToComponent(Message.getErrorPermission()));
                    }
                } else {
                    player.sendMessage(FuncString.stringToComponent(Message.getCommandBankError()));
                }
            } else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("delete")){
                    if (player.hasPermission("Kabibank.deleteBank")){
                        try {
                            Integer.parseInt(args[1]);
                        } catch (NumberFormatException formatException){
                            player.sendMessage(FuncString.stringToComponent(Message.getCommandBankError()));
                            return true;

                        }
                        int number = Integer.parseInt(args[1]);

                        if (CoolAtm.isCreateATM(number)) {
                            EventDeleteATM event = new EventDeleteATM(player, CoolAtm.getATM(number).getCoordinate(), number, true);
                            Bukkit.getServer().getPluginManager().callEvent(event);

                            Atm atm = CoolAtm.getATM(event.getCoordinate());

                            if (!event.isCancelled()) {
                                if (atm != null) {
                                    if (CoolAtm.delete(atm.getNumberATM())) {
                                        CoolAtm.save();
                                        player.sendMessage(FuncString.stringToComponent(Message.getSuccessfullyDeleteATM()));

                                    }
                                }
                            }
                        } else {
                            player.sendMessage(FuncString.stringToComponent(Message.getErrorCreationATM()));
                        }
                    } else {
                        player.sendMessage(FuncString.stringToComponent(Message.getErrorPermission()));
                    }
                } else {
                    player.sendMessage(FuncString.stringToComponent(Message.getErrorDeleteATM()));
                }
            } else if (args.length == 4) {
                if (player.hasPermission("Kabibank.createBank")) {
                    if (args[0].equalsIgnoreCase("create")) {
                        int[] coordinate = new int[3];

                        try {
                            Integer.parseInt(args[1]);
                            Integer.parseInt(args[2]);
                            Integer.parseInt(args[3]);
                        } catch (NumberFormatException formatException) {
                            player.sendMessage(FuncString.stringToComponent(Message.getCommandBankError()));
                            return true;
                        }

                        coordinate[0] = Integer.parseInt(args[1]);
                        coordinate[1] = Integer.parseInt(args[2]);
                        coordinate[2] = Integer.parseInt(args[3]);
                        EventCreateATM event = new EventCreateATM(player, coordinate);

                        Atm atm = new Atm(event.getCoordinate(), player.getWorld().getName());
                        if (CoolAtm.isAddATM(atm)) {
                            Bukkit.getPluginManager().callEvent(event);
                        }
                        if (!event.isCancelled()) {
                            if (CoolAtm.addATM(atm)) {
                                CoolAtm.save();

                                player.sendMessage(FuncString.stringToComponent(Message.getSuccessfullyCreateATM().replace("%coordinate%", coordinate[0] + ", " + coordinate[1] + ", " + coordinate[2])));
                            } else {
                                player.sendMessage(FuncString.stringToComponent(Message.getErrorCreateATM()));
                            }
                        }
                    } else {
                        player.sendMessage(FuncString.stringToComponent(Message.getCommandBankError()));
                    }
                } else {
                    player.sendMessage(FuncString.stringToComponent(Message.getErrorPermission()));
                }
            } else {
                player.sendMessage(FuncString.stringToComponent(Message.getCommandBankError()));
            }
        } else {
            sender.sendMessage(FuncString.stringToComponent(Message.getErrorSenderConsole()));
        }
        return true;
    }

    private static int[] getCoordinate(Player player) {
        Location location = player.getLocation();

        int[] coordinate = new int[3];
        coordinate[0] = location.getBlockX();
        coordinate[1] = location.getBlockY();
        coordinate[2] = location.getBlockZ();

        return coordinate;
    }

    private static int x = 0;
    private static int y = 0;
    private static int z = 0;

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {

        if (sender instanceof Player player) {
            RayTraceResult result = player.rayTraceBlocks(4);
            if (result != null && result.getHitBlock() != null) {
                Block targetBlock = result.getHitBlock();

                x = targetBlock.getX();
                y = targetBlock.getY();
                z = targetBlock.getZ();

            }
            if (args.length == 1) {
                return List.of("create", "list", "delete", "help");

            } else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("create")) {
                    if (x != 0) {
                        return List.of(String.valueOf(x));
                    } else {
                        return List.of("x");
                    }
                } else if (args[0].equalsIgnoreCase("delete")) {
                    return List.of("number");
                }

            } else if (args.length == 3) {
                if (args[0].equalsIgnoreCase("create")) {
                    if (y != 0) {
                        return List.of(String.valueOf(y));
                    } else {
                        return List.of("y");
                    }
                }
            } else if (args.length == 4) {
                if (args[0].equalsIgnoreCase("create")) {
                    if (z != 0) {
                        return List.of(String.valueOf(z));
                    } else {
                        return List.of("z");
                    }
                }
            }
        }
        return null;
    }
}
