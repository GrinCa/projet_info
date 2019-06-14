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
        /*BufferedImage image = ImageIO.read(new File("image.png"));
        BufferedImage image1 = ImageIO.read(new File("image2.png"));
        ImagePNG imagePNG = new ImagePNG(image);
        ImagePNG imagePNG1 = new ImagePNG(image1);
        imagePNG = imagePNG.insertImage(imagePNG1);
        imagePNG = imagePNG.decalage(4);
        ImageView imageView = new ImageView("", imagePNG.createBufferedImage());
        imageView.setVisible(true);
        imageView.setDefaultCloseOperation(EXIT_ON_CLOSE);*/
    }

}
