package main;

import javax.swing.JFrame;
import javax.swing.JPanel;

import util.Boundary;
import util.Rectangle;

/** Parent class for creating a Model Control Panel.
 * Both the GUI containers and the controller
 * will be defined in a concrete class.
  */
public abstract class ControlPanel extends JPanel {

    /** Boundary of arena with respect to the graphics window */
    protected Rectangle box = Boundary.CONTROL_PANEL_MODEL;

    public ControlPanel() {}

    /** Constructor to build arena
     * @param agents List of agents to place in arena
     * @param graphics frame in which to place agents
     */
    public ControlPanel(JFrame frame) {
    } // end constructor

} // end ModelControls

