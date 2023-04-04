package ua.kotarych.kabibank.files;

import com.google.gson.Gson;
import ua.kotarych.kabibank.config.CustomConfig;
import ua.kotarych.kabibank.models.Atm;

import java.io.*;
import java.util.*;

public class CoolAtm {

    private static ArrayList<Atm> atms = new ArrayList<>();

    public static ArrayList<Atm> getAtms() {
        return atms;
    }

    public static boolean addATM(Atm atm){
        return atms.add(atm);
    }
    public static boolean isAddATM(Atm atm){
        if (atms.add(atm)){
            atms.remove(atm);
            return true;

        } else {
            return false;
        }
    }
    public static boolean isContainsMoney(String currency, int amount, int numberATM){
        return Objects.requireNonNull(getATM(numberATM)).isContainsMoney(currency, amount);
    }
    public static boolean delete(int numberATM){
        return atms.removeIf(atm -> atm.getNumberATM() == numberATM);
    }
    public static boolean isCreateATM(int number){
        for (Atm atm : atms){
            if (atm.getNumberATM() == number){
                return true;
            }
        }
        return false;
    }
    public static boolean addBalansATM(int number, String currency, int amount){
        for (Atm atm : atms){
            if (atm.getNumberATM() == number){
                return atm.addBalans(currency, amount);
            }
        }
        return false;
    }
    public static boolean removeBalansATM(int number, String currency, int amount){
        for (Atm atm : atms){
            if (atm.getNumberATM() == number){
                return atm.removeBalans(currency, amount);
            }
        }
        return false;
    }
    public static List<int[]> getAllCoordinate(){
        List<int[]> ints = new ArrayList<>();

        for (Atm atm : atms){
            ints.add(atm.getCoordinate());
        }
        return ints;
    }
    public static Atm getATM(int number){
        for (Atm atm : atms){
            if (atm.getNumberATM() == number){
                return atm;
            }
        }
        return null;
    }
    public static Atm getATM(int[] coordinate){
        for (Atm atm : atms){
            if (atm.getCoordinate().equals(coordinate)){
                return atm;
            }
        }
        return null;
    }

    public static void save(){

        Gson gson = new Gson();

        File file = new File(CustomConfig.getPlugin().getDataFolder(), "data/atms.json");

        file.getParentFile().mkdir();
        Writer writer = null;

        try {
            if (!file.exists()){
                file.createNewFile();
            }
            writer = new FileWriter(file,false);
            gson.toJson(atms, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void load() {

        Gson gson = new Gson();
        File file = new File(CustomConfig.getPlugin().getDataFolder(), "data/atms.json");

        if (file.exists()) {
            Reader reader = null;
            try {
                reader = new FileReader(file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            Atm[] atm = gson.fromJson(reader, Atm[].class);

            atms = new ArrayList<>(Arrays.asList(atm));
        }
    }
}
