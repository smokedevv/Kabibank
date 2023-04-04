package ua.kotarych.kabibank;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import ua.kotarych.kabibank.commands.BankCommand;
import ua.kotarych.kabibank.commands.KabibankCommand;
import ua.kotarych.kabibank.config.Config;
import ua.kotarych.kabibank.config.CustomConfig;
import ua.kotarych.kabibank.config.MaterialGui;
import ua.kotarych.kabibank.config.Message;
import ua.kotarych.kabibank.files.CoolAtm;
import ua.kotarych.kabibank.event.ClickGui;
import ua.kotarych.kabibank.event.OpenGui;
import ua.kotarych.kabibank.files.CoolNumber;
import ua.kotarych.kabibank.files.KabibankCard;
import ua.kotarych.kabibank.models.SwitchingPages;

public final class Kabibank extends JavaPlugin {

    @Override
    public void onEnable() {

        CustomConfig.loadConfig();

        Message.load();
        Config.load();
        MaterialGui.load();

        CoolAtm.load();
        CoolNumber.load();
        KabibankCard.load();

        getCommand("kabibank").setExecutor(new KabibankCommand());
        getCommand("kabibank").setTabCompleter(new KabibankCommand());

        getCommand("bank").setExecutor(new BankCommand());
        getCommand("bank").setTabCompleter(new BankCommand());

        getServer().getPluginManager().registerEvents(new OpenGui(), this);
        getServer().getPluginManager().registerEvents(new ClickGui(), this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
