/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.main;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JFrame;

/**
 *
 * @author Julien
 */
public class ImageView extends JFrame{
    
    private Image image;
    
    public ImageView(String title, Image image){
        this.image = image;
        setTitle(title);
        setSize(image.getWidth(rootPane), image.getHeight(rootPane));
    }
    @Override
    public void paint(Graphics g){
        g.drawImage(image, 0, 0, rootPane);
    }
}
