package ua.kotarych.kabibank.config;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import ua.kotarych.kabibank.Kabibank;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CustomConfig {

    private static final Plugin plugin = Bukkit.getPluginManager().getPlugin("Kabibank");

    private static final File fileConfig = new File(plugin.getDataFolder(), "config.yml");
    private static final FileConfiguration config = YamlConfiguration.loadConfiguration(fileConfig);

    private static final String language = config.getString("language");
    private static final String currentLan = config.getString("currentLan");

    private static final File fileConfigEn = new File(plugin.getDataFolder(), "config_en.yml");
    private static final File fileConfigLan = new File(plugin.getDataFolder(), "config_" + language + ".yml");

    private static File fileMessage = new File(plugin.getDataFolder(), "message.yml");
    private static final File fileMessageEn = new File(plugin.getDataFolder(), "message_en.yml");
    private static final File fileMessageLan = new File(plugin.getDataFolder(), "message_" + language + ".yml");

    private static final File fileMaterialGui = new File(plugin.getDataFolder(), "materialGui.yml");
    private static final File fileMaterialGuiEn = new File(plugin.getDataFolder(), "materialGui_en.yml");
    private static final File fileMaterialGuiLan = new File(plugin.getDataFolder(), "materialGui_" + language + ".yml");


    public static Plugin getPlugin() {
        return plugin;
    }

    public static File getFileConfig() {
        return fileConfig;
    }

    public static File getFileMessage() {
        return fileMessage;
    }

    public static File getFileMaterialGui() {
        return fileMaterialGui;
    }

    public static void loadConfig(){
        if (fileConfig.exists()){
            if (!language.equals(currentLan)){
                if (language.equals("en") | language.equals("ru") | language.equals("ua")){
                    fileConfig.delete();
                    fileMessage.delete();
                    fileMaterialGui.delete();

                    plugin.saveResource("config_" + language + ".yml", false);
                    plugin.saveResource("message_" + language + ".yml", false);
                    plugin.saveResource("materialGui_" + language + ".yml", false);

                    fileConfigLan.renameTo(fileConfig);
                    fileMessageLan.renameTo(fileMessage);
                    fileMaterialGuiLan.renameTo(fileMaterialGui);
                }
            } else {
                getPlugin().getConfig().options().copyDefaults();
                getPlugin().saveDefaultConfig();
            }
        } else {
            plugin.saveResource("config_en.yml", false);
            plugin.saveResource("message_en.yml", false);
            plugin.saveResource("materialGui_en.yml", false);

            fileConfigEn.renameTo(fileConfig);
            fileMessageEn.renameTo(fileMessage);
            fileMaterialGuiEn.renameTo(fileMaterialGui);
        }
    }
}
