package main;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JSlider;

import java.awt.*;
import java.util.ArrayList;

import util.Boundary;
import util.Rectangle;
import viewable.*;

public class ViewArena extends JPanel {

    /** Agents to be viewed in arena */
    private ArrayList<Agent> agents;

    /** Wrappers for Agents used to draw them in the graphics window */
    private ArrayList<Viewable> viewables;

    /** Boundary of arena with respect to the graphics window */
    private Rectangle box = Boundary.MODEL_ARENA;

    /** Constructor for various concrete types of Viewable */
    private ViewableFactory factory = new ViewableFactory();

    /** Obstacles in the arena */
    private ArrayList<Agent> obstacles;

    public ViewArena(ArrayList<Agent> agents, JFrame frame) {
        this(agents,new ArrayList<Agent>(),frame);
    }

    /** Constructor to build arena
     * @param agents List of agents to place in arena
     * @param frame frame in which to place agents
     */
    public ViewArena(ArrayList<Agent> agents, ArrayList<Agent> objects, JFrame frame) {

        // Graphics setLocation to place in graphics window
        setLocation(box.x, box.y);
        setBackground(new Color(255,255,255));
        setBounds(box.x, box.y, box.width, box.height);

        viewables = new ArrayList<>();

        // save agents and obstacles, then add to arena
        this.agents = agents;
        addAgents(agents, frame);

        this.obstacles = objects;
        addAgents(obstacles, frame);

    } // end constructor

    /** Add agents to arean by wrapping in Viewable */
    public void addAgents(ArrayList<Agent> agents, JFrame frame) {

        // Wrap each agent in a Viewable
        for (Agent agent: agents) {
            Viewable viewable = factory.getViewable(agent);
            viewable.setLocation(
                (int)viewable.getPosition().x+box.x,
                (int)viewable.getPosition().y+box.y);
            frame.getContentPane().add(viewable); 

            viewables.add(viewable);
        }
    }

    /** Redraw agents at current location */
    public void update() {
        // Redraw agents
        for (Viewable viewable: viewables) {
            viewable.setLocation(
                    (int)viewable.getPosition().x+box.x,
                    (int)viewable.getPosition().y+box.y);
            viewable.draw();
        }
    }

    /** Reset viewables in arena */
    public void reset(ArrayList<Agent> agents, ArrayList<Agent> objects, JFrame frame) {
        removeAgents(frame);
        viewables = new ArrayList<>();
        addAgents(agents,frame);
        addAgents(objects,frame);
    }

    /** Remove all viewables from the arena */
    public void removeAgents(JFrame frame) {
        for (Viewable viewable: viewables) {
            frame.getContentPane().remove(viewable);
        }
    }
} // end simulation arena

