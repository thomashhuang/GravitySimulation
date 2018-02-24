package edu.illinois.thomash3;

/**
 * A large body in the universe like a star or a planet that exerts a gravitational force.
 */
public class CelestialBody extends PhysicsBody {

    private double mass; // kg

    public CelestialBody(double xVelocity, double yVelocity, double xPosition, double yPosition, double mass) {
        super(xVelocity, yVelocity, xPosition, yPosition);
        this.mass = mass;
    }

    /**
     * Return the mass of this body.
     *
     * @return
     */
    public double getMass() {
        return mass;
    }

}
