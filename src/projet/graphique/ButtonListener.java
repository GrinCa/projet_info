/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.graphique;

import java.awt.event.*;
import projet.graphique.WindowManager.Window;

/**
 *
 * @author Julien
 */
public class ButtonListener implements ActionListener {


    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getActionCommand().equalsIgnoreCase("Image")){
            WindowManager windowManager = new WindowManager();
            windowManager.createWindow(Window.SHOWIMAGE);
        }else if(e.getActionCommand().equalsIgnoreCase("filtrage")){
            WindowManager windowManager = new WindowManager();
            windowManager.createWindow(Window.FILTRAGE);
        }else if(e.getActionCommand().equalsIgnoreCase("histogramme")){
            WindowManager windowManager = new WindowManager();
            windowManager.createWindow(Window.HISTOGRAMME);
        }
    }

}
