package edu.illinois.thomash3;

public abstract class PhysicsBody {

    protected String name;

    protected double xVelocity; // m/s

    protected double yVelocity;

    protected double xPosition; // m

    protected double yPosition;

    /**
     * Parameterized constructor.
     *
     * @param xVelocity
     * @param yVelocity
     * @param xPosition
     * @param yPosition
     */
    public PhysicsBody(String name, double xVelocity, double yVelocity, double xPosition, double yPosition) {
        this.name = name;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    /**
     * Apply gravitational force of a CelestialBody to adjust the trajectory of this object.
     *
     * @param other the body exerting the force on this object.
     */
    public void applyGravity(CelestialBody other, double gravitationalConstant) {

        if (this.xPosition == other.xPosition && this.yPosition == other.yPosition) { //objects in same place.
            return;
        }

        double totalAcceleration = gravitationalConstant * other.getMass() /
                Math.pow(getDistance(other), 2); //Gm/r^2

        double xPositionDifference = other.xPosition - this.xPosition;

        double xAcceleration = totalAcceleration *
                (xPositionDifference / getDistance(other)); //find x component

        xVelocity += xAcceleration;

        double yPositionDifference = other.yPosition - this.yPosition;

        double yAcceleration = totalAcceleration *
                (yPositionDifference / getDistance(other)); //find y component

        yVelocity += yAcceleration;
    }

    /**
     * Tick the simulation by one second, adjusting position according to velocity.
     */
    public void updateLocation() {
        xPosition += xVelocity;
        yPosition += yVelocity;
    }

    /**
     * Get the distance to another PhysicsBody.
     *
     * @param other the body to get the distance to.
     * @return distance.
     */
    public double getDistance(PhysicsBody other) {

        double xDiff = other.xPosition - this.xPosition;
        double yDiff = other.yPosition - this.yPosition;

        return Math.pow(Math.pow(xDiff, 2) + Math.pow(yDiff, 2), .5);
    }


    public String getName() {
        return name;
    }

    /**
     * If the PhysicsBody has sufficient mass to impact the orbit of other objects, this returns its mass.
     * Bodies with inconsequentially low mass will return 0.
     *
     * @return
     */
    public double getMass() {
        return 0;
    }

    public double getRadius() {
        return  0;
    }

    public double getXPosition() {
        return xPosition;
    }

    public double getYPosition() {
        return yPosition;
    }

    public double getXVelocity() {
        return xVelocity;
    }

    public double getYVelocity() {
        return yVelocity;
    }

}
