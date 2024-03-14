package viewable;

import java.awt.Color;
import java.awt.Graphics;

import util.Vector2d;
import main.Agent;

/**
 * Circle for drawing in a JFrame
 *
 * @author Amy Larson
 */
public class ViewableCircle extends Viewable {

    public ViewableCircle(Agent agent) {
        super(agent);
        this.setSize((int)agent.getSize().x, (int)agent.getSize().x);

        // Make the box/panel on which the circle is drawn transparent
        this.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
    }

    /** Default constructor.  */
    public ViewableCircle() {
    }

    @Override
    public void draw() {
        setSize((int)agent.getSize().x, (int)agent.getSize().y);
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.setColor(agent.getColor());
        g.fillOval(0, 0, (int)agent.getSize().x, (int)agent.getSize().x);
    }

    public Vector2d getPosition() {
        return agent.getPosition();
    }
}

