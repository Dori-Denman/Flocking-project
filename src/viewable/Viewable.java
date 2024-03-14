package viewable;

import javax.swing.JPanel;

import util.Vector2d;
import main.Agent;

/** Wraps an agent. Defines how to draw in the graphics window. */
public abstract class Viewable extends JPanel  {

    protected Agent agent;

    public Viewable() {}

    public Viewable(Agent a) {
        agent = a;
    }

    public Vector2d getPosition() {
        return agent.getPosition();
    }
    public Agent agent() {
        return agent;
    }

    public abstract void draw();
}