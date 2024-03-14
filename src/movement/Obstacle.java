package movement;

import java.awt.Color;
import java.util.ArrayList;

import util.Vector2d;
import main.Agent;


public class Obstacle extends Agent {

    public Obstacle() {
        super();
        velocity.x = 0;
        velocity.y = 0;
        setViewableType("Square");
        setSize(new Vector2d(10,10));
    }

    /** Move the agent the "delta" for 1 timestep */
    public void update(ArrayList<Agent> agents) {
        // No reaction with other agents thus far
    }

    @Override
    public void step() {
        // This is an obstacle and it does not move
    }
}

