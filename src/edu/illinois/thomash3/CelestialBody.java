package edu.illinois.thomash3;

/**
 * A large body in the universe like a star or a planet that exerts a gravitational force.
 */
public class CelestialBody extends PhysicsBody {

    private double mass; // kg

    private double radius; //m

    public CelestialBody(String name, double xVelocity, double yVelocity, double xPosition, double yPosition, double radius, double mass) {
        super(name, xVelocity, yVelocity, xPosition, yPosition);
        this.radius = radius;
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

    public double getRadius() {
        return radius;
    }

}
