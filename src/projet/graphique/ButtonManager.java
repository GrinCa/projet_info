/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.graphique;

import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Julien
 */
public class ButtonManager extends JFrame {

    private Container container;
    private JButton jButton;
    private Button buttonID;

    

    public enum Button {

        BUTTONIMAGE("IMAGE"), GREY("Canal de gris"), HISTOGRAMME("Histogramme");
        

        private String name;

        Button(String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }

    public ButtonManager(Container container, Button buttonID) {
        this.buttonID = buttonID;
        jButton = new JButton(buttonID.toString());
        this.container = container;
        container.add(jButton);
        ButtonListener buttonListener = new ButtonListener();

    }

    public JButton getButton() {
        return jButton;
    }

    public Button getButtonID() {
        return buttonID;
    }
    
    public void addContainer(JButton jButton){
        container.add(jButton);
    }
    
   

}
