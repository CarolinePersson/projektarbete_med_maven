package se.caroline.aventyrspel;


import se.caroline.aventyrspel.model.Burglar;
import se.caroline.aventyrspel.model.Resident;

import java.util.Scanner;

public class Main {  //konstanter för de lika rummen i spelet, final går ej att ändra
        private static final String KOKET = "Köket";
        private static final String HALL = "Hallen";
        private static final String SOVRUM = "Sovrummet";
        private static final String KONTORET = "Kontoret";
        private static final String VARDAGSRUMMET = "Vardagsrummet";
//VARIABLER UNDER
        private static boolean running = true; //Styr om spelet ska fortsätta eller inte
        private static boolean fryingPanFound = false; //håller koll på om spelaren har hittat en stekpanna i köket.
        private static Resident resident = new Resident(); //spelaren
        private static Burglar burglar = new Burglar(); //inbrottsjuven
        private static String currentLocation = VARDAGSRUMMET; // visar var spelaren är just nu, och startar i vardagsrummet.

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.println(" *** VÄLKOMMEN TILL ÄVENTYRSPELET ***");
            System.out.println(" I spelet kan du röra dig genom att gå in i olika rum, du når alla rum från Vardagsrummet. Avsluta spelet med quit");
            System.out.println("Det är natt och du vaknar i vardagsrummet och hör ett ljud från hallen...");

            while (running && resident.isConscious()) { // En while-loop kör spelet så länge det är aktivt (running är true) och spelaren är vid medvetande.
                showOptions();
                String action = scanner.nextLine().toLowerCase();
                handleAction(action);
            }
            scanner.close();
        }

        private static void showOptions() {
            System.out.println("\nDu är i " + currentLocation + ". Välj att: gå till köket, hallen, sovrummet, kontoret, vardagsrummet eller quit.");
            if (currentLocation.equals(KOKET)) {
                if (!fryingPanFound) {
                    System.out.println("Du ser en stekpanna, du väljer att ta upp den. Din attackskada har nu ökat!");
                    resident.setDamage(6);
                    fryingPanFound = true;
                } else {
                    System.out.println("Du kollar runt i köket men ser ingenting.");
                }
            } else if (currentLocation.equals(HALL)) {
                System.out.println("Du ser inbrottstjuven! Vill du fightas? skriv fight");
            } else if (currentLocation.equals(KONTORET)) {
                if (burglar.isConscious()) {    // om inbrottsjuven är vid medvetande
                    System.out.println("Du är för rädd för att göra något just nu.");
                } else {
                    System.out.println("Du ringer polisen. Spelet är över, du är säker nu."); //Om inbbrottsjuven inte är medvetande
                    running = false;
                }
            }
        }

        private static void handleAction(String action) { //Denna metod tolkar spelarens inmatningar och utför rätt åtgärd.
            switch (action) {
                case "gå till köket" -> moveToRoom(KOKET);
                case "gå till hallen" -> moveToRoom(HALL);
                case "gå till sovrummet" -> moveToRoom(SOVRUM);
                case "gå till kontoret" -> moveToRoom(KONTORET);
                case "gå till vardagsrummet" -> moveToRoom(VARDAGSRUMMET);
                case "quit" -> quitGame();
                case "fight" -> initiateFight();
                default -> System.out.println("Ogiltig handling, försök igen.");
            }
        }

        private static void moveToRoom(String room) { //Denna metod låter spelaren förflytta sig mellan rummen, men spelaren kan
            if (currentLocation.equals(VARDAGSRUMMET) || room.equals(VARDAGSRUMMET)) { // bara gå mellan vardagsrummet och andra rum (vardagsrummet fungerar som en "central punkt" i huset).
                currentLocation = room;
                System.out.println("Du är nu i " + room + ".");
            } else {
                System.out.println("Du kan bara röra dig mellan " + VARDAGSRUMMET + " och de andra rummen.");
            }
        }

        private static void initiateFight() { //Om spelaren är i hallen, där tjuven finns, kan de välja att slåss. En kamp startar då mellan spelaren (resident) och tjuven (burglar).
            if (!currentLocation.equals(HALL)) {
                System.out.println("Det finns ingen att slåss med här.");
                return; //avslutas men retunerar ingenting i void metod
            }
            System.out.println("Striden börjar mellan " + resident.getRole() + " och " + burglar.getRole() + "!");

            while (resident.isConscious() && burglar.isConscious()) { //om resident och burglar är medvetslösa = sant
                resident.punch(burglar);
                if (burglar.isConscious()) {
                    burglar.punch(resident);
                } else {
                    System.out.println("Du har knockat tjuven, YESSS!");
                    //running = false;
                    break;
                }
            }
            if (!resident.isConscious()) {
                System.out.println("Du blev besegrad. Spelet är slut.");
                running = false;
            }
        }

        private static void quitGame() {
            System.out.println("Spelet avslutas nu. Hej då! :P");
            running = false;
        }
    }

