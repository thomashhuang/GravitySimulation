package edu.illinois.thomash3;

import com.sun.tools.classfile.Opcode;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * A 2D universe of stars, planets, and satellites.
 */
public class Universe {

    private ArrayList<PhysicsBody> objectsInUniverse;

    final double GRAVITATIONAL_CONSTANT;

    /**
     * Default constructor sets gravitational constant to the real-life gravitational constant.
     * Universe is empty by default.
     */
    public Universe() {
        this(6.67 * Math.pow(10, -11));
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
     * Apply the gravitational forces between two objects.
     *
     * @param first the first body.
     * @param second the second body.
     */
    public void applyGravity(PhysicsBody first, PhysicsBody second) {

    }

}
