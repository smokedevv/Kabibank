package ua.kotarych.kabibank.files;

import com.google.gson.Gson;
import ua.kotarych.kabibank.config.CustomConfig;
import ua.kotarych.kabibank.models.Atm;
import ua.kotarych.kabibank.models.Number;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CoolNumber {

    private static Number number = new Number(0, 1111);


    public static Number getNumber() {
        return number;
    }

    public static void save() {

        Gson gson = new Gson();

        File file = new File(CustomConfig.getPlugin().getDataFolder(), "data/number.json");

        file.getParentFile().mkdir();
        Writer writer = null;

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            writer = new FileWriter(file, false);
            gson.toJson(number, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void load() {

        Gson gson = new Gson();
        File file = new File(CustomConfig.getPlugin().getDataFolder(), "data/number.json");

        if (file.exists()) {
            Reader reader = null;
            try {
                reader = new FileReader(file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            Number numbers = gson.fromJson(reader, Number.class);

            number = numbers;
        }
    }
}
