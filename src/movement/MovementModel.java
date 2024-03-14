package movement;

/*
 * Circle Model.java
 */

import java.util.ArrayList;
import java.awt.Color;

import main.Model;
import main.Agent;

/**
 * Models a collection of circles roaming about impacting other circles.
 * 
 * @author Amy Larson
 */
public class MovementModel extends Model {

    /** Default constructor. */
    public MovementModel() {
        super();
    }

    /** Create all agents in the population */
    public void populate() {

        for (int i = 0; i < count(); i++) {
            agents.add(new MovementAgent());
        }
        for (int i=0; i<5; i++) {
            Obstacle o = new Obstacle();
            o.setColor(new Color(0,0,0));
            objects.add(o);

        }
    }

    @Override
    public void advanceAgents() {
        for (Agent agent : agents) {
            agent.step();
        }
    }

    @Override
    public void updateAgents() {
        // This is a model of simple movement, therefore agents do not affect other agents.
    }

    /** New setup. Replace population. */
    public void reset() {
        agents = new ArrayList<>();
        objects = new ArrayList<>();
        populate();
    }
}
