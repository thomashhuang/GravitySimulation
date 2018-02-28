package edu.illinois.thomash3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * A 2D universe of stars, planets, and satellites.
 */
public class Universe {

    private List<PhysicsBody> objectsInUniverse;

    private final double GRAVITATIONAL_CONSTANT;

    /**
     * Default constructor sets gravitational constant to the real-life gravitational constant.
     * Universe is empty by default.
     */
    public Universe() {
        this(6.674 * Math.pow(10, -11));
    }

    /**
     * Parameterized constructor which can set the Universal Gravitational Constant.
     * Universe is empty by default.
     *
     * @param gravitationalConstant the gravitational constant.
     */
    public Universe(double gravitationalConstant) {

        objectsInUniverse = new ArrayList<>();

        GRAVITATIONAL_CONSTANT = gravitationalConstant;
    }

    /**
     * Add a body to the Universe.
     *
     * @param body the body to add.
     */
    public void addBody(PhysicsBody body) {
        objectsInUniverse.add(body);
    }

    /**
     * Add a collection of objects to the Universe.
     *
     * @param bodies Variadic collection of PhysicsBodies.
     */
    public void addBody(PhysicsBody... bodies) {
        objectsInUniverse.addAll(Arrays.asList(bodies));
    }

    /**
     * Tick the universe forward by one second.
     * Applies movement of all bodies based on their velocities then adjusts their velocities based on gravity.
     */
    public void tick() {
        for (PhysicsBody body : objectsInUniverse) {
            body.updateLocation();
        }
        this.applyGravity();
        this.checkCollisions();
    }

    /**
     * Apply the gravitational forces between all pairs of objects in the universe.
     */
    private void applyGravity() {
        for (int i = 0; i < objectsInUniverse.size() - 1; i++) {
            for (int j = i + 1; j < objectsInUniverse.size(); j++) {
                applyGravity(objectsInUniverse.get(i), objectsInUniverse.get(j));
            }
        }

    }

    /**
     * Update the velocities of two bodies in this universe due to their gravitational interaction.
     *
     * @param first the first body.
     * @param second the second body.
     */
    private void applyGravity(PhysicsBody first, PhysicsBody second) {

        if (first.getClass() == CelestialBody.class) {
            second.applyGravity((CelestialBody) first, GRAVITATIONAL_CONSTANT);
        }
        if (second.getClass() == CelestialBody.class) {
            first.applyGravity((CelestialBody) second, GRAVITATIONAL_CONSTANT);
        }
    }

    /**
     * Find out if any satellites crashed into celestial bodies.
     * If so, remove them from the List of objects in the universe and print that they crashed.
     */
    private void checkCollisions() {

        HashSet<PhysicsBody> satellitesToRemove = new HashSet<>();

        for (PhysicsBody body : objectsInUniverse) {

            if (body.getClass() == Satellite.class) {

                for (PhysicsBody celestialBody : objectsInUniverse) {

                    if (celestialBody.getClass() == Satellite.class) {
                        continue;
                    }

                    Satellite satellite = (Satellite) body;
                    if (satellite.getAltitude((CelestialBody) celestialBody) <= 0) {
                        System.out.println(body.getName() + " crashed into " + celestialBody.getName());
                        satellitesToRemove.add(body);
                    }
                }
            }
        }

        objectsInUniverse.removeAll(satellitesToRemove);
    }

    /**
     * Print information about the Physics Bodies in this universe.
     */
    public void printInformation() {
        for (PhysicsBody body : objectsInUniverse) {

            System.out.println(body.getName() + ":");

            if (body.getClass() == CelestialBody.class) {

                System.out.println("Mass: " + body.getMass());
                System.out.println("Radius: " + body.getRadius());

            } else {
                System.out.println("Satellite:");
            }

            System.out.println("X: " + body.getXPosition());
            System.out.println("Y: " + body.getYPosition());
            System.out.println("X Velocity: " + body.getXVelocity());
            System.out.println("Y Velocity: " + body.getYVelocity());
            System.out.println();
        }
    }

    public List<PhysicsBody> getObjectsInUniverse() {
        return objectsInUniverse;
    }

}
