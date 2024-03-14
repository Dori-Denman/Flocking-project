package movement;

import javax.swing.JComponent;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import util.Rectangle;
import main.ControlPanel;
import main.Model;
import main.View;

public class MovementControlPanel extends ControlPanel {

        /** The model of the circles. */
        private static Model model;

        /** Controller of MVC */
        //private static ControllerModel controller;

        private static View view;
    
        // {R,G,B} values from 0-255 each
        protected Color backGroundColor = new Color(40, 40, 40);
        protected Color textColor = new Color(192, 96, 0);
        protected Color buttonColor = new Color(200, 200, 200);
        protected Color secondaryColor = new Color(51, 102, 255);
    
        // Controller GUI Components
        private final JLabel agentLabel = new JLabel("Agent View");
        protected final JCheckBox circleOption = new JCheckBox("Circle",true);
        protected final JCheckBox squareOption = new JCheckBox("Square", false);
        protected final ButtonGroup checkGroup = new ButtonGroup();
        

        // Adds component to graphics window (offset by control panel boundary)
        // Boundary value can be found in Boundary.java
        private void addComponent(JComponent jcomp, Rectangle rect) {
            jcomp.setBounds(box.x+rect.x, box.y+rect.y, rect.width, rect.height);
            view.getContentPane().add(jcomp);
        }

        public MovementControlPanel(Model m, View view) {
            this.model = m;
            this.view = view;
            ControllerMovement controller = new ControllerMovement(view);

            addComponent(agentLabel,new Rectangle(20, 100, 150, 30));
    
            addComponent(circleOption,new Rectangle( 150, 100, 100, 30));
            circleOption.addItemListener(controller);
    
            addComponent(squareOption, new Rectangle( 250, 100, 100, 30));
            squareOption.addItemListener(controller);
    
            checkGroup.add(circleOption);
            checkGroup.add(squareOption);
        }

        public class ControllerMovement implements ActionListener, ItemListener {
    
            /** Default constructor to set up the viewer
             */
            public ControllerMovement(View view) {        
            }
        
            public void itemStateChanged(ItemEvent e){
                //System.out.println("CHECKED "+((JCheckBox)e.getSource()).getText());
                
                //String agentView = ((JCheckBox)(e.getSource())).getText();
                System.out.println(model+" setting agents to "+((JCheckBox)e.getSource()).getText());
                model.setAgentsViewableType(((JCheckBox)e.getSource()).getText());        
            }
            
            /**
             * Performs the actions for each of the JButtons
             * @param ae The event which occurred, identifying which button was pushed.
             */
            @Override
            public void actionPerformed(ActionEvent ae) {

            }
        }        
}