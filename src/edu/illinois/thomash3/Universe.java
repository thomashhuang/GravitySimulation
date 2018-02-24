package edu.illinois.thomash3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A 2D universe of stars, planets, and satellites.
 */
public class Universe {

    private ArrayList<PhysicsBody> objectsInUniverse;

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
     * Applies movement of all bodies based on their velocities.
     * applyGravity should be called immediately after.
     */
    public void tick() {
        for (PhysicsBody body : objectsInUniverse) {
            body.updateLocation();
        }
    }

    /**
     * Apply the gravitational forces between all pairs of objects in the universe.
     */
    public void applyGravity() {
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

    public List<PhysicsBody> getObjectsInUniverse() {
        return objectsInUniverse;
    }

}
