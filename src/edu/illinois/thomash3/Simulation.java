package edu.illinois.thomash3;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Simulation {

    /**
     * Main method for simulation.
     * Gives users the option to create their own Celestial Bodies, Satellites.
     * Users can also set the Universal Gravitational Constant, if that's what you're into.
     *
     * @param args
     */
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        Universe universe = setUpUniverse(scan);

        int tickRate;

        System.out.println("After how many seconds should the Universe display its state?");

        tickRate = scan.nextInt();

        scan.close();

        simulateUniverse(universe, tickRate);

    }

    /**
     * Continuously update the universe and print information about it every few seconds.
     *
     * @param tickRate The number of seconds in the universe between each print.
     */
    private static void simulateUniverse(Universe universe, int tickRate) {

        System.out.println("t = 0");

        universe.printInformation();

        int time = 0;

        while (true) {

            System.out.println("-------------------");

            for (int i = 0; i < tickRate; i++) {
                universe.tick();
                time++;
            }

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                System.exit(0);
            }

            System.out.println("t = " + time);
            universe.printInformation();

        }
    }

    /**
     * Set up the bodies in the universe, prompting the user for input.
     *
     * @param scan Scanner input source.
     * @return the Universe.
     */
    private static Universe setUpUniverse(Scanner scan) {

        System.out.println("(C)ustom or (D)efault Universe?");

        String input = scan.nextLine().substring(0, 1);

        Universe universe = new Universe();

        if (input.equalsIgnoreCase("d")) {

            System.out.println("(1) Earth ISS System");
            System.out.println("(2) Earth Moon System");

            int selection = scan.nextInt();
            scan.nextLine();

            if (selection == 1) {
                universe = earthIssSystem();
            } else if (selection == 2) {
                universe = earthMoonSystem();
            } else {
                System.out.println("I don't understand.");
                System.out.println("Creating empty universe.");
            }

        } else if (input.equalsIgnoreCase("c")) {

            System.out.println("Define a gravitational constant? (Y/N)");
            String gravityConstant = scan.nextLine();

            if (gravityConstant.equalsIgnoreCase("y")) { //User wants to play god.

                System.out.println("Universal Gravitational Constant: ");
                String constant = scan.nextLine();
                double g;

                try {
                    g = Double.parseDouble(constant);
                } catch (Exception e) {
                    throw new IllegalArgumentException("Illegal Universal Gravitational Constant");
                }

                universe = new Universe(g);
            }

        } else {
            System.out.println("I don't understand.");
            System.out.println("Creating empty universe.");

        }

        customizeUniverse(universe, scan);

        return universe;

    }

    /**
     * Set up Earth-ISS system.
     *
     * @return Universe with the Earth-ISS system.
     */
    private static Universe earthIssSystem() {
        Universe earthIss = new Universe();

        CelestialBody earth = new CelestialBody("Earth",0,0,0,0,
                6371000, 5.9722 * Math.pow(10, 24));

        Satellite iss = new Satellite("ISS",0, 7670,
                earth.getRadius() + 400000, 0);

        earthIss.addBody(earth, iss);

        return earthIss;
    }

    /**
     * Set up Earth-Moon system.
     *
     * @return Universe with Earth-moon system.
     */
    private static Universe earthMoonSystem() {
        Universe earthMoon = new Universe();

        CelestialBody earth = new CelestialBody("Earth",0,0,0,0,
                6371000, 5.9722 * Math.pow(10, 24));

        CelestialBody moon = new CelestialBody("The Moon",0, 1022,384399000, 0,
                1737000, 7.342 * Math.pow(10, 22));

        earthMoon.addBody(earth, moon);

        return earthMoon;
    }

    /**
     * Allow the user to add CelestialBodies or Satellites to the Universe.
     *
     * @param universe the universe to customize.
     * @param scan scanner.
     */
    private static void customizeUniverse(Universe universe, Scanner scan) {

        String input = "";

        while (!input.equalsIgnoreCase("f")) {

        System.out.println("Add a (C)elestialBody, a (S)atellite, or (F)inish?");
        input = scan.nextLine();


            if (input.equalsIgnoreCase("c")) {

                String name;
                double x;
                double y;
                double xV;
                double yV;
                double mass;
                double radius;

                System.out.println("Name: ");
                name = scan.nextLine();

                System.out.print("X Position: ");
                x = scan.nextDouble();

                System.out.print("Y Position: ");
                y = scan.nextDouble();

                System.out.print("X Velocity: ");
                xV = scan.nextDouble();

                System.out.print("Y Velocity: ");
                yV = scan.nextDouble();

                System.out.print("Mass: ");
                mass = scan.nextDouble();

                System.out.print("Radius: ");
                radius = scan.nextDouble();

                CelestialBody body = new CelestialBody(name, xV, yV, x, y, radius, mass);

                universe.addBody(body);

            } else if (input.equalsIgnoreCase("s")) {

                String name;
                double x;
                double y;
                double xV;
                double yV;

                System.out.println("Name: ");
                name = scan.nextLine();

                System.out.print("X Position: ");
                x = scan.nextDouble();

                System.out.print("Y Position: ");
                y = scan.nextDouble();

                System.out.print("X Velocity: ");
                xV = scan.nextDouble();

                System.out.print("Y Velocity: ");
                yV = scan.nextDouble();

                Satellite sat = new Satellite(name, xV, yV, x, y);

                universe.addBody(sat);
            } else if (!input.equalsIgnoreCase("f")){
                System.out.println("I don't understand.");
            }

            if (!input.equalsIgnoreCase("f")) {
                scan.nextLine();
            }

        }
    }
}
