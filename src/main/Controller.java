package main;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import util.Vector2d;
import movement.*;
import disease.*;

public class Controller implements ActionListener, ItemListener, ChangeListener {

    /** The model of the circles. */
    private static Model model;

    /** The gui for the simulation. */
    private static View view;
    private static DiseaseControlPanel DCP;

    /** Default constructor to set up the viewer
     */
    public Controller() {

        ///*
        model = new DiseaseModel();
        view = new View(this,model);
        view.getContentPane().add(new DiseaseControlPanel(model,view));
        //*/

        /*
        model = new MovementModel();
        view = new View(this,model);
        model.setAgentsViewableType("Circle");
        view.getContentPane().add(new MovementControlPanel(model,view));
        */

        model.setView(view);
        view.setVisible(true);
        model.start();
    }

    public void itemStateChanged(ItemEvent e){
        //System.out.println("CHECKED "+e.getSource());

        //String agentView = ((JCheckBox)(e.getSource())).getText();
        model.setAgentsViewableType(((JCheckBox)e.getSource()).getText());
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        if (!source.getValueIsAdjusting()){
            if (source == view.countSlider) {
                // Update the count in the model
                int count = (int)source.getValue();
                model.setCount(count);
            } else if (source == view.speedSlider) {
                // Update the speed in the model
                int speed = (int)source.getValue();
                model.setSpeed(speed);
            } else if (source == view.sizeSlider) {
                // Update the size in the model
                int size = (int)source.getValue();
                for (Agent a: model.agents){
                    a.setSize(new Vector2d(size,size));
                }
            } else if (source == DCP.infectiousnessSlider) {
                // Update the infection rate  in the model
                int size = (int)source.getValue();
                for (Agent a: model.agents){
                    a.setInfectiousness(new Vector2d(size,size));
                }
            } else if (source == DCP.lethalitySlider) {
                // Update the time in the model
                int size = (int)source.getValue();
                for (Agent a: model.agents){
                    a.setLethality(new Vector2d(size,size));
                }
            } else if (source == DCP.viralitySlider) {
                // Update the size in the model
                int size = (int)source.getValue();
                for (Agent a: model.agents){
                    a.setVirality(new Vector2d(size,size));
                }
            }
            view.update();
            model.updateAgents();
        }
    }

    /**
     * Performs the actions for each of the JButtons
     * @param ae The event which occurred, identifying which button was pushed.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        //System.out.println("Action called: " + ae);
        if (ae.getActionCommand().equals("Set Up")) {

            // Set the model on pause
            model.pause();

            // Create the circles based on count
            Integer count = Integer.valueOf(view.countSlider.getValue());
            model.setCount(count);
            view.resetArena();

            // Set the size of each agent in the simulation
            Integer size = Integer.valueOf(view.sizeSlider.getValue());
            for (Agent a: model.agents){
                a.setSize(new Vector2d(size,size));
            }

            // Remake the arena with the agents having the set population and size
            //view.resetArena();

            // Enable the play/pause button
            view.enablePlayPause(true);
        }
        else if (ae.getActionCommand().equals( "Play/Pause")) {
            if (model.isPaused()){
                model.play();
            }else{
                model.pause();
            }
        }
    }
}

