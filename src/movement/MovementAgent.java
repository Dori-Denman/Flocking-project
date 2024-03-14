package movement;

import java.util.ArrayList;

import util.Vector2d;
import main.Agent;

/**
 * Agent in a simulation
 *
 * @author Amy Larson
 * 
 * A Boid moves within a fixed sized simulation arena.
 * All positions will be relative to the origin of the arena (upper left corner)
 */
public class MovementAgent extends Agent {

    /** Unique id (for debugging) */
    static int nextId = 0;

    private static int radius = 5;
    
    static int getId() {
        return nextId++;
    }
    private int id;


    /** Default constructor */
    public MovementAgent() {
        super(new Vector2d(radius*2,radius*2));
        id = getId(); // for debugging
        setViewableType("Circle");
    }

    /** update boid based on interaction with all other boids */
    @Override 
    public void update(ArrayList<Agent> boids) {
    }

    @Override
    public void step() {
        position.add(velocity);
        checkBorders();
    }


}

