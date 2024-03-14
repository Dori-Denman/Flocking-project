package disease;

import java.util.ArrayList;

import util.Boundary;
import util.Rectangle;
import util.Vector2d;
import main.Agent;

/**
 * Agent in a simulation
 *
 * @author Amy Larson
 * 
 * A Disease moves within a fixed sized simulation arena.
 * All positions will be relative to the origin of the arena (upper left corner)
 */
public class DiseaseAgent extends Agent {

    // The box should be set by the controller, but this is default if not
    private static Rectangle box = Boundary.AGENT_BOUNDARY;

    /** Unique id (for debugging) */
    static int nextId = 0;
    static int getId() {
        return nextId++;
    }
    private int id;

    // These could be user options at some point
    public static final int maxspeed = 4;
    public static final double maxforce = .3;
    public static final int neighborhood = 75;
    public static final int proximity = 40;

    private int radius = 5;

    protected double infectivity = 100.0;
    protected double virality = 100.0;
    protected double lethality = 100.0;

    /** Default constructor */
    public DiseaseAgent() {
        super();
        reset();
        id = getId(); // for debugging
    }


    @Override 
    public void update(ArrayList<Agent> diseases) {
        calculateForces(diseases);
    }

    /** Calculate forces of cohesion (position), alignment (velocity), and separation 
     * @param diseases Collection of all agents interacting in the model.
    */
    private void calculateForces(ArrayList<Agent> diseases) {

        // Calculate the average position, direction (in neighborhood)
        // Calculate the average distance for separation (in proximity)
        int neighborCount = 0;
        int proximityCount = 0;

        Vector2d infectivity = new Vector2d(0.0,0.0);
        Vector2d lethality = new Vector2d(0.0,0.0);
        Vector2d virality = new Vector2d(0.0,0.0);

        for (Agent agent: diseases) {
            // Compare to another agent (as long as it is not 'this')
            DiseaseAgent other = (DiseaseAgent) agent;

            if (this==other) {
                continue;
            }

            // distance determines whether or not it is applying a force to this disease
            double distance = position.distance(other.getPosition());

            // if in the neighborhood, can apply attractive force
            if (distance <= neighborhood) {
                infectivity.add(other.getPosition());
                lethality.add(other.getVelocity());
                neighborCount++;
            }
            // if proximal, can apply repulsive force
            if (distance <= proximity) {
                // Calculate vector pointing away from neighbor
                Vector2d difference = Vector2d.subtract(position,other.getPosition());
                difference.normalize();
                difference.divide(distance);        // Weight by distance
                virality.add(difference);
                proximityCount++; 
            } // end inner for loop
        }

        // Average for position and direction in neighborhood
        if (neighborCount > 0) {
            infectivity.divide(neighborCount);
            lethality.divide(neighborCount);
        }

        // scale cohesion force
        if (neighborCount > 0) {
            infectivity.subtract(position);
            infectivity.normalize();
            infectivity.multiply(maxspeed);
            infectivity.subtract(getVelocity());
            infectivity.limit(maxforce);  // Limit to maximum steering force
        } 
        // scale alignment force
        if (neighborCount > 0) {
            // Implement Reynolds: Steering = Desired - Velocity
            lethality.normalize();
            lethality.multiply(maxspeed);
            lethality.subtract(getVelocity());
            lethality.limit(maxforce);
        }

        // Average separation
        if (proximityCount > 0) {
            virality.divide(proximityCount);
        }
        // As long as the vector is greater than 0
        if (virality.magnitude() > 0) {
            // Implement Reynolds: Steering = Desired - Velocity
            virality.normalize();
            virality.multiply(maxspeed);
            virality.subtract(getVelocity());
            virality.limit(maxforce);
        }

        // These are weights that can be set with sliders
        virality.multiply(1.5);
        lethality.multiply(1.0);
        infectivity.multiply(1.0);

        // apply those forces
        applyForces(lethality, infectivity, virality);
    }

    private void applyForces(Vector2d lethality, Vector2d infectivity, Vector2d virality) {
        velocity.add(lethality);
        velocity.add(infectivity);
        velocity.add(virality);
        velocity.limit(maxspeed);
    }

    @Override
    public void step() {
        position.add(velocity);
        checkBorders();
    }

    /* Method used to create a steering vector */
    public Vector2d seek(Vector2d target) {
        Vector2d steer = Vector2d.subtract(target, position);
        steer.normalize();
        steer.multiply(maxspeed);
        steer.subtract(velocity);
        steer.limit(maxforce);
        return steer;
    }

    public double getInfectivity() {
        return infectivity;
    }

    public double getVirality() {
        return virality;
    }

    public double getLethality() {
        return lethality;
    }

}


