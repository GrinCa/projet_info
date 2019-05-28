/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.graphique;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.plaf.basic.BasicOptionPaneUI;

/**
 *
 * @author Julien
 */
public class MainWindow extends JFrame implements KeyListener {

    private Container container;

    public MainWindow() {
        setTitle("Fenetre");
        setSize(600, 400);
        container = getContentPane();
        container.setLayout(new FlowLayout());
        //on creer un bouton grace à la classe ButtonManager dépendant de la fenetre this
        addMouseListener(new MousesListener());
        //on fourni à la fentere un écouteur
        addKeyListener(this);
        
        JButton showImageButton = new JButton("Image");
        container.add(showImageButton);
        showImageButton.addActionListener(new ButtonListener());
        
        JButton filtrageButton = new JButton("Filtrage");
        container.add(filtrageButton);
        filtrageButton.addActionListener(new ButtonListener());
        
        JButton histogramme = new JButton("Histogramme");
        container.add(histogramme);
        histogramme.addActionListener(new ButtonListener());
        
        setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        System.exit(-1);

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
