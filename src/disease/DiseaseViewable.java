package disease;

import java.awt.Color;
import java.awt.Graphics;

import util.Vector2d;
import viewable.Viewable;
import main.Agent;

/**
 * Circle for drawing in a JFrame
 *
 * @author Amy Larson
 */
public class DiseaseViewable extends Viewable {

    public DiseaseViewable(Agent d) {
        super(d);
        this.setSize((int)agent.getSize().x, (int)agent.getSize().y);

        // Make the box/panel on which the circle is drawn transparent
        this.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
    }

    /** Default constructor.  */
    public DiseaseViewable() {
    }

    @Override
    public void draw() {
        setSize((int)agent.getSize().x,(int)agent.getSize().y);
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(agent.getColor());
        g.fillOval(0, 0, (int)agent.getSize().x, (int)agent.getSize().y);
    }

    public Vector2d getPosition() {
        return agent.getPosition();
    }
}

