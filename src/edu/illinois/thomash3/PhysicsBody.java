package edu.illinois.thomash3;

public abstract class PhysicsBody {

    protected double xVelocity; // m/s

    protected double yVelocity;

    protected double xPosition; // m

    protected double yPosition;

    /**
     * Parameterized constructor.
     * @param xVelocity
     * @param yVelocity
     * @param xPosition
     * @param yPosition
     */
    public PhysicsBody(double xVelocity, double yVelocity, double xPosition, double yPosition) {
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    /**
     * Apply gravitational force of another object to adjust the trajectory of this object.
     * Satellites will have no gravitational influence on PhysicsBodies because getMass() returns zero.
     *
     * @param other the body exerting the force on this object.
     */
    public void applyGravity(PhysicsBody other, double gravitationalConstant) {

        double xAcceleration = Math.cos(gravitationalConstant * other.getMass()
                / Math.pow(getDistance(other), 2));
        if (this.xPosition > other.xPosition) { //if the other body is on the left
            xAcceleration *= -1;
        }

        xVelocity += xAcceleration;

        double yAcceleration = Math.sin(gravitationalConstant * other.getMass()
                / Math.pow(getDistance(other), 2));
        if (this.yPosition > other.yPosition) { //if the other body is below
            yAcceleration *= -1;
        }

        yVelocity += yAcceleration;
    }

    /**
     * Tick the simulation by one unit of time, adjusting position according to velocity.
     */
    public void updateLocation() {
        xPosition += xVelocity;
        yPosition += yVelocity;
    }

    /**
     * Get the distance to another celestial body.
     *
     * @param other the body to get the distance to.
     * @return distance.
     */
    public double getDistance(PhysicsBody other) {

        double xDiff = other.xPosition - this.xPosition;
        double yDiff = other.yPosition - this.yPosition;

        return Math.pow(Math.pow(xDiff, 2) + Math.pow(yDiff, 2), .5);
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
