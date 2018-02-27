package edu.illinois.thomash3;

/**
 * An object in the universe small enough that it does not exert a meaningful gravitational force.
 */
public class Satellite extends PhysicsBody {

    public Satellite(double xVelocity, double yVelocity, double xPosition, double yPosition) {
        super(xVelocity, yVelocity, xPosition, yPosition);
    }

    /**
     * Find this satellite's altitude above a certain CelestialBody, such as a planet or moon.
     *
     * @param body the CelestialBody
     * @return altitude above the surface.
     */
    public double getAltitude(CelestialBody body) {
        double distance = this.getDistance(body);
        return distance - body.getRadius();
    }

}
