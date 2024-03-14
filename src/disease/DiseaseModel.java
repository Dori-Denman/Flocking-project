package disease;

/*
 * Disease Model.java
 */

import java.util.ArrayList;

import main.Agent;
import main.Model;

/**
 * Models a collection of diseases roaming about impacting other diseases.
 * 
 * @author Amy Larson
 */
public class DiseaseModel extends Model {

    private double infectivityScalar = 12;
    private double viralityScalar = 12;
    private double lethalityScalar = 12;

    /** Default constructor. */
    public DiseaseModel() {
        super();
    }
    public void populate() {
        for (int i = 0; i < count(); i++) {
            agents.add(new DiseaseAgent());
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
    }
        /* 
        for (Agent agent : agents) {
            DiseaseAgent a = (DiseaseAgent) agent;
            Point foundCenter = a.infectivity(agents);
            Point foundDirection = a.lethality(agents);
            Point spread = a.virality(agents);

            // TODO : Add weight feature here (how the sliders will change how much each rule is effective)
            // Recommended Weighting (0-2) for each slider

            // get the new X direction and new Y direction
            double newDirX = a.getDirection().getX() + infectivityScalar/12 * foundCenter.getX() + lethalityScalar/12 * foundDirection.getX() + viralityScalar/12 * spread.getX(); 
            double newDirY = a.getDirection().getY() + infectivityScalar/12 * foundCenter.getY() + lethalityScalar/12 * foundDirection.getY() + viralityScalar/12 * spread.getY();
            
            // Set the direction with the combined new X and Y directions
            a.setDirection(newDirX, newDirY);
        }
        */
    
    /* 
    public void infectAgents() {
        for (Agent agent : agents) {
            agent.update(agents);
        }
    }

    public void spreadViralAgents() {
        for (Agent agent : agents) {
            agent.update(agents);
        }
    }

    public void killAgents() {
        for (Agent agent : agents) {
            agent.update(agents);
        }
    }

    public void multiplyAgents() {
        for (Agent agent : agents) {
            agent.update(agents);
        }
    }
    */

    public void reset() {
        agents = new ArrayList<>();
        for (int i = 0; i < count(); i++) {
            agents.add(new DiseaseAgent());
        }
    }

    public void setInfectivityScalar(double scalar) {
        infectivityScalar = scalar;
    }

    public void setViralityScalar(double scalar) {
        viralityScalar = scalar;
    }

    public void setLethalityScalar(double scalar) {
        lethalityScalar = scalar;
    }
}
