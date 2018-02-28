package edu.illinois.thomash3;

import org.junit.*;

import org.junit.Before;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class NBodyTest {

    Universe earthMoonSystem;

    CelestialBody earth;

    CelestialBody moon;

    Satellite l1EarthSideSatellite;

    Satellite l1MoonSideSatellite;

    @Before
    public void setUp() {
        /*
         */
        earthMoonSystem = new Universe();
        earth = new CelestialBody("Earth", 0,0,0,0,
                6371000, 5.9722 * Math.pow(10, 24));

        moon = new CelestialBody("The Moon",0, 1022,384399000, 0,
                1737000, 7.342 * Math.pow(10, 22));

        earthMoonSystem.addBody(earth, moon);
    }

    @Test
    public void nBodyInteraction() {
        //Check whether the earth and moon exert gravity on each other.

        moon.yVelocity = 0;

        for (int i = 0; i < 100; i++) {
            earthMoonSystem.tick();
        }

        //Bodies move towards each other.
        assertTrue(earth.getXPosition() > 0);
        assertTrue(moon.getXPosition() < 384399000);
    }

    @Test
    public void massDifferenceAffectsGravitationalForce() {

        //Smaller celestial body is displaced more than larger body.

        moon.yVelocity = 0;

        double moonInitialPosition = moon.getXPosition();
        double earthInitialPosition = earth.getXPosition();

        for (int i = 0; i < 100; i++) {
            earthMoonSystem.tick();
        }

        double moonDisplacement = Math.abs(moon.getXPosition() - moonInitialPosition);
        double earthDisplacement = Math.abs(earth.getXPosition() - earthInitialPosition);

        assertTrue(moonDisplacement > earthDisplacement);
    }



}
