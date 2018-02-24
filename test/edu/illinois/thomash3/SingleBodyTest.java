package edu.illinois.thomash3;

import org.junit.*;

import javax.swing.text.SimpleAttributeSet;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class SingleBodyTest {

    Universe earthIssSystem;

    CelestialBody earth;

    Satellite iss;

    @Before
    public void setUp() {
        /*
        Set up a counter-clockwise orbiting satellite around the earth.
        Values are very close to real world values for the Earth-ISS system.
        ISS orbits at roughly 400km above earth's surface.
         */
        earthIssSystem = new Universe();
        earth = new CelestialBody(0,0,0,0, 6371000, 5.9722 * Math.pow(10, 24));
        iss = new Satellite(0, 7670,
                earth.getRadius() + 400000, 0);
        earthIssSystem.addBody(earth, iss);
    }

    @Test
    public void universeCreation() {

        assertEquals(2, earthIssSystem.getObjectsInUniverse().size());

        for (PhysicsBody body : earthIssSystem.getObjectsInUniverse()) {
            if (body != earth && body != iss) {
                fail("Body in universe does not match inputs");
            }
        }
    }

    @Test
    public void staticCelestialBody() {
        for (int i = 0; i < 1000; i++) {
            earthIssSystem.tick();
            earthIssSystem.applyGravity();
        }
        assertEquals(0.0, earth.getXPosition(), .000001);
        assertEquals(0.0, earth.getYPosition(), .000001);
        assertEquals(0.0, earth.getXVelocity(), .000001);
        assertEquals(0.0, earth.getYVelocity(), .000001);
    }

    @Test
    public void earthFreefall() {

        //Free falling body that starts at the height of the ISS.
        PhysicsBody fallingObject = new Satellite(0,0,0,
                earth.getRadius() + 400000);

        earthIssSystem.addBody(fallingObject);

        earthIssSystem.applyGravity();
        earthIssSystem.tick();

        assertTrue(fallingObject.getYVelocity() < 0
                && fallingObject.getYVelocity() > -10);
        assertTrue(fallingObject.getYPosition() < earth.getRadius() + 400000);
        assertEquals(0.0, fallingObject.getXPosition(), .000001);
        assertEquals(0.0, fallingObject.getXVelocity(), .000001);

    }

    @Test
    public void satelliteInitialMovement() {

        for (int i = 0; i < 60; i++) {
            earthIssSystem.tick();
            earthIssSystem.applyGravity();
        }


        assertTrue(iss.xPosition < earth.getRadius() + 400000);
        assertTrue(iss.xVelocity < 0);
        assertTrue(iss.yPosition > 0);
        assertTrue(iss.yVelocity < 7670);
    }

    @Test
    public void issSingleOrbit() {

        //After roughly one orbit, the ISS returns to the same place
        //with the same velocity.

        //The orbital period of the ISS in this simulation is SLIGHTLY under 5540 seconds

        final int ORBITAL_PERIOD = 5540;

        for (int i = 0; i < ORBITAL_PERIOD; i++) {
            earthIssSystem.tick();
            earthIssSystem.applyGravity();
        }

        assertEquals(earth.getRadius() + 400000, iss.getXPosition(), 10);
        assertEquals(7670, iss.getYVelocity(), 200);
        assertEquals(0, iss.getXVelocity(), 200);
        assertEquals(0, iss.getYPosition(), 10000);
    }

    @Test
    public void issManyOrbits() {

        //After about 20 orbits, the ISS returns to the same place
        //with the same velocity.

        //The -6 adjusts for the orbit being a little under 5540 seconds,
        //stopping the error from accumulating over time.

        final int ORBITAL_PERIOD = 5540 * 20 - 6;

        for (int i = 0; i < ORBITAL_PERIOD; i++) {
            earthIssSystem.tick();
            earthIssSystem.applyGravity();
        }

        assertEquals(earth.getRadius() + 400000, iss.getXPosition(), 10);
        assertEquals(7670, iss.getYVelocity(), 200);
        assertEquals(0, iss.getXVelocity(), 200);
        assertEquals(0, iss.getYPosition(), 10000);
    }

}
