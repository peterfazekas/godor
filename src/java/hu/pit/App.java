package hu.pit;

import hu.pit.controller.PitService;
import hu.pit.model.*;

import java.util.List;
import java.util.Scanner;

public class App {

    private final PitService service;
    private final Console console;
    private final FileWriter writer;

    private App() {
        DataApi dataApi = new DataApi(new FileReader(), new DataParser());
        service = new PitService(dataApi.getData("melyseg.txt"));
        console = new Console(new Scanner(System.in));
        writer = new FileWriter("godrok.txt");
    }

    public static void main(String[] args) {
        new App().run();
    }

    private void run() {
        System.out.println("1. feladat");
        System.out.println("A fájl adatainak száma: "
                + service.getDepthCount());
        System.out.println("2. feladat");
        System.out.print("Adjon meg egy távolságértéket! ");
        int distance = console.read();
        System.out.println("Ezen a helyen a felszín "
                + service.getDepthAtCertainDistance(distance - 1)
                + " méter mélyen van.");
        System.out.println("3. feladat");
        System.out.println("Az érintetlen terület aránya "
                + service.getUntouchedArea());
        List<String> pits = service.getPits();
        writer.writeAll(pits);
        System.out.println("5. feladat");
        System.out.println("A gödrök száma: " + pits.size());
        System.out.println("6. feladat");
        System.out.println(service.getPitDetails(distance - 1));
    }
}
