package disease;

import javax.swing.JComponent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSlider;
import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import util.Rectangle;
import main.Model;
import main.ControlPanel;



public class DiseaseControlPanel extends ControlPanel {

        /** The model of the circles. */
        private static Model model; 

        private static JFrame view;
    
        // {R,G,B} values from 0-255 each
        protected Color backGroundColor = new Color(40, 40, 40);
        protected Color textColor = new Color(192, 96, 0);
        protected Color buttonColor = new Color(200, 200, 200);
        protected Color secondaryColor = new Color(51, 102, 255);

        private final JLabel infectiousnessLabel = new JLabel("Infectiveness (0 - 100%): ");
        public final JSlider infectiousnessSlider = new JSlider(0, 100);
    
        private final JLabel lethalityLabel = new JLabel("Lethality (0 - 100%): ");
        public final JSlider lethalitySlider = new JSlider(0, 100);
    
        private final JLabel viralityLabel = new JLabel("Virality (0 - 100%): ");
        public final JSlider viralitySlider = new JSlider(0, 100);
    


        public void addComponent(JComponent jcomp, Rectangle rect) {
            jcomp.setBounds(box.x+rect.x, box.y+rect.y, rect.width, rect.height);
            view.getContentPane().add(jcomp);
        }

        public DiseaseControlPanel(/*ControllerModel control, */Model m, JFrame view) {

            this.model = m;
            ControllerDisease controller = new ControllerDisease();
            this.view = view;

        // __________________ SIMULATION INFECTIVITY (in %) ___________________
        // place the sim infectivity label and text box
        addComponent(infectiousnessLabel, new Rectangle(20, 50, 150, 30));
        addComponent(infectiousnessSlider, new Rectangle(160, 50, 150, 30));
         // __________________  SIMULATION LETHALITY (in %) ___________________
        // place the sim lethality label and text box
        addComponent(lethalityLabel, new Rectangle(20, 80, 150, 30));
        addComponent(lethalitySlider, new Rectangle(150, 80, 150, 30));
        // __________________ SIMULATION VIRALITY (in %) ___________________
        // place the sim lethality label and text box
        addComponent(viralityLabel, new Rectangle(20, 110, 150, 30));
        addComponent(viralitySlider, new Rectangle(150, 110, 150, 30));
    }

    public class ControllerDisease implements ActionListener, ItemListener  /*extends ControllerModel*/ {
        /**
         * Default constructor to set up the viewer
         */
        public ControllerDisease() {

        }

        public void itemStateChanged(ItemEvent e) {
            System.out.println("CHECKED " + e.getSource());

            // String agentView = ((JCheckBox)(e.getSource())).getText();
            // view.setAgentView(((JCheckBox)e.getSource()).getText());
        }

        /**
         * Performs the actions for each of the JButtons
         * 
         * @param ae The event which occurred, identifying which button was pushed.
         */
        @Override
        public void actionPerformed(ActionEvent ae) {
            /*/
            // These were for testing the ControlPanel. They are not functional!
            if (ae.getActionCommand().equals("Does")) {
                System.out.println("Does");
                // model.pause();
            }
            else if (ae.getActionCommand().equals("Nothing")) {
                System.out.println("Nothing");
                // model.play();
            }*/
        }
    }
}