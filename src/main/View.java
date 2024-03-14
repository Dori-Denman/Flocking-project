package main;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.ChangeListener;
import javax.swing.JSlider;

import java.awt.Color;

import util.Boundary;

/**
 * The "VIEW" of Model-View-Controller
 * An instance of this gui contains a reference to the Controller and the Model.
 * @author Amy Larson
 */
public class View extends JFrame {

    /** The model of the circles. */
    private static Model model;

    /** Controller of MVC */
    private static Controller controller;

    ViewArena arena;
    ControlPanel controllerForModel;

    // {R,G,B} values from 0-255 each
    protected Color backGroundColor = new Color(40, 40, 40);
    protected Color textColor = new Color(192, 96, 0);
    protected Color buttonColor = new Color(200, 200, 200);
    protected Color secondaryColor = new Color(51, 102, 255);

    // Controller GUI Components
    private final JLabel countLabel = new JLabel("Agents (2-100): ");
    protected final JSlider countSlider = new JSlider(2,100);

    private final JLabel speedLabel = new JLabel("Speed (10-100 ms): ");
    protected final JSlider speedSlider = new JSlider(10,100);

    private final JLabel sizeLabel = new JLabel("Size: (10-40): ");
    protected final JSlider sizeSlider = new JSlider(10,40);

    private final JLabel agentLabel = new JLabel("Agent View");

    private final JButton playPause = new JButton("Play/Pause");
    private final JButton setup = new JButton("Set Up");   


    /** Default constructor. */
    public View() {}

    public void addModelControls(ControlPanel modelControls){
        controllerForModel = modelControls;
        getContentPane().add(controllerForModel);
    }

    /**
     * Creates a Simulation GUI application.
     * Sets the components and their positions in the gui.
     * Sets the Controller as the buttons' action listener.
     */
    public View(Controller control, Model m/* , ControllerModel controlModel*/) {

        // Initialize the graphics window
        super("Simulation");

        this.model = m;
        this.controller = control;
        //this.controllerForModel = controlModel;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(Boundary.GRAPHICS_WINDOW.width, Boundary.GRAPHICS_WINDOW.height);
   
        // Layout is manual -- not set by swing graphics
        this.getContentPane().setLayout(null);

        // Designated section of graphics window for agents to move about in
        arena = new ViewArena(model.getAgents(),model.getObjects(),this);
        getContentPane().add(arena);

        // __________________  POPULATION SIZE ___________________
        // Control the size of the agent population used in the model
        countLabel.setBounds(20,20,150,30);
        getContentPane().add(this.countLabel);

        countSlider.setBounds(150,20,150,30);
        this.countSlider.addChangeListener(controller);
        getContentPane().add(countSlider);
 
        // __________________  SIMULATION SPEED (in ms) ___________________
        // place the sim speed label and text box
        this.speedLabel.setBounds(20,50,150,30);
        getContentPane().add(this.speedLabel);
        
        this.speedSlider.setBounds(150,50,150,30);
        this.speedSlider.setInverted(true);
        this.speedSlider.addChangeListener(controller);
        getContentPane().add(this.speedSlider);
        speedSlider.addChangeListener(controller);

        // __________________  SIZE OF BOIDS (in ???) ___________________
        this.sizeLabel.setBounds(20,80,150,30);
        getContentPane().add(this.sizeLabel);

        this.sizeSlider.setBounds(150,80,150,30);
        this.sizeSlider.addChangeListener(controller);
        getContentPane().add(this.sizeSlider);
        sizeSlider.addChangeListener(controller);

        // __________________  PLAY/PAUSE, RESET ___________________
        // bounds are col, row, width, height

        // place the play/pause button
        this.playPause.setBounds(40, 150, 120, 30);
        this.playPause.addActionListener(controller);
        getContentPane().add(this.playPause);
        this.playPause.setEnabled(false);

        // place the setup button 
        this.setup.setBounds(160, 150, 120, 30);
        this.setup.addActionListener(controller);
        getContentPane().add(this.setup);
    }

    /** Time to redraw the agents */
    //  (it has "subscribed" to the model in a way)
    public void update() {
        arena.update();
        getContentPane().repaint();
    }

    /** Reset the arena to reestablish agents in simulation */
    public void resetArena() {
        arena.reset(model.getAgents(),model.getObjects(),this);
        getContentPane().add(arena);
    }

    /** Enables or disables the play/pause button
     * @param b Whether the play/pause button will be enabled
     */
    public void enablePlayPause(boolean b){
        this.playPause.setEnabled(b);
    }
}