package edu.illinois.thomash3;

/**
 * An object in the universe small enough that it does not exert a meaningful gravitational force.
 */
public class Satellite extends PhysicsBody {

    public Satellite(double xVelocity, double yVelocity, double xPosition, double yPosition) {
        super(xVelocity, yVelocity, xPosition, yPosition);
    }

}
