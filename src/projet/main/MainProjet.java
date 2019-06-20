package projet.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import projet.graphique.Fenetre;

public class MainProjet extends JFrame {

    public static void main(String args[]) throws IOException {
        Fenetre fen = new Fenetre();
        fen.setVisible(true);
        fen.repaint();
    }

}
