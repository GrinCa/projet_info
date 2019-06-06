package projet.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import projet.graphique.Fenetre;




public class MainProjet extends JFrame {

    public static void main(String args[]) throws IOException {

        BufferedImage image = ImageIO.read(new File("image.png"));
        ImagePNG imagePNG = new ImagePNG(image);
        /*ImagePNG imageBleu = new ImagePNG(image).getBlue();
        ImageView imageView = new ImageView("", imageRouge.createBufferedImage());
        imageView.setVisible(true);
        imageView = new ImageView("", imageBleu.createBufferedImage());
        imageView.setVisible(true);*/
        Fenetre fen = new Fenetre();
        fen.setVisible(true);
        fen.repaint();
        

        /*Message message = new Message("texte.txt");
        message.lire();
        message.binaire();
        
        Codage codage = new Codage(imagePNG);
        codage.encodage(message.getmsgBinaire());
        System.out.println(codage.decodage());*/

        
        
    }

}
