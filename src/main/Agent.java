package main;

import java.util.Random;
import java.util.ArrayList;
import java.awt.Color;

import util.Boundary;
import util.Rectangle;
import util.Vector2d;

public abstract class Agent {

    /** flag to indicate if teleporting (pass through one side, appear on other) */
    static boolean teleportLeftRight = false;

    /** flag to indicate if teleporting (pass through top/bottom and appear on other) */
    static boolean teleportTopBottom = false;

    // The box should be set by the controller, but this is default if not
    protected static Rectangle box = Boundary.AGENT_BOUNDARY;

    protected Vector2d position = new Vector2d(0,0);

    protected Vector2d velocity = new Vector2d(0,0);

    /** Color specified in RGB */
    protected Color color = new Color(10, 10, 10);

    /** Fixed size width x height */
    protected Vector2d size = new Vector2d(10,10);

    /** Viewable type used for View */
    String viewableType = null;

    /** Agents have many random components */
    private Random random = new Random();

    public Agent() {
        reset();
    }

    public Agent(Vector2d s) {
        size = s;
        reset();
    }

    /** Reassigns member variables to the agent. */
    public void reset() {
        randomPosition();
        randomVelocity();
        randomColor();
    }
    
    /** Randomly assign the RGB components */
    public void randomColor() {
        // color randomly
        color = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }

    /** Randomly assign its location based on the fixed ranges. */
    public void randomPosition() {
        // place at random location
        position.x = (double) random.nextInt(box.width-(int)size.x) + size.x;   
        position.y = (double) random.nextInt(box.height-(int)size.y) + size.y;
    }

    /** Randomly point it in a direction with random "speed" */
    public void randomVelocity() {
        // set in a random direction
        velocity.x = (double) random.nextInt(6) - 3;
        velocity.y = (double) random.nextInt(6) - 3;
    }

    /** Move the agent the "delta" for 1 timestep */
    public abstract void update(ArrayList<Agent> agents);

    public abstract void step();


    public Vector2d getPosition() {
        return position;
    }
    public Vector2d getVelocity() {
        return velocity;
    }

    public void setColor(Color c) {
        color = c;
    }

    public Color getColor() {
        return color;
    }

    public void setSize(Vector2d s) {
        size = s;
    }

    public Vector2d getSize() {
        return size;
    }

    public String getViewableType() {
        return viewableType;
    }
    public void setViewableType(String vt) {
        viewableType = vt;
    } 
    
    /**
     * checking borders for agents.
     * If teleportXaxis = true, teleport agents that are touching the right and left border to the opposite side.
     * If teleportYaxis = true, teleport agents that are touching the top and left borders to the opposite side.
     * If teleportXaxis and teleportYaxis are both false, then have the agents bounce off of the borders. 
     */
    public void checkBorders() {

        // Checking left / right borders and reacting appropriately
        if (atLeftBorder()) {
            if (teleportLeftRight) {
                // appear on the right
                position.x = box.width - size.x;
            } else {
                // bounce off wall
                position.x = 0;
                velocity.x = -velocity.x;
            }
        } else if (atRightBorder()) {
            if (teleportLeftRight) {
                position.x = 0;
            } else {
                position.x = box.width-size.x;
                velocity.x = -velocity.x;
            }
        }
        // Checking top / bottom borders and reacting appropriately
        if (atTopBorder()) {
            if (teleportTopBottom) {
                position.y = box.height - size.y;
            } else {
                position.y = 0;
                velocity.y = -velocity.y;
            }
        } else if (atBottomBorder()) {
            if (teleportTopBottom) {
                position.y = 0;
            } else {
                position.y = box.height-size.y;
                velocity.y = -velocity.y;
            }            
        }
    }


    /**
     * Check if agents are on the left border.
     * @return true or false
     */
    public boolean atLeftBorder(){
        return position.x <= 0;
    }

    /**
     * Check if agents are on the right border.
     * @return true or false
     */
    public boolean atRightBorder(){
        return (position.x >= box.width-size.x);
    }

    /**
     * Check if agents are on the top border
     * @return true or false
     */
    public boolean atBottomBorder(){
        return (position.y >= box.height-size.y);
    }

    /**
     * Check if agents are on the bottom border
     * @return true or false
     */
    public boolean atTopBorder(){
        return (position.y <= 0);
    }

    /**
     * set teleportXaxis to the checkbox value.
     * @param tele is the value of the checkbox
     */
    public void setLeftRightTeleport(boolean tele){
        teleportLeftRight = tele;
    }

    /**
     * set teleportYaxis to the checkbox value.
     * @param tele is the value of the checkbox
     */
    public void setTopBottomTeleport(boolean tele){
        teleportTopBottom = tele;
    }
   // Getters and Setters for the sliders 
    public void setVirality(Vector2d vector2d) {
    }

    public void setLethality(Vector2d vector2d) {
    }

    public void setInfectiousness(Vector2d vector2d) {
    }
}