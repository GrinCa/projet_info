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

        /*Message message = new Message("Julien Julien Julien Julien Julien Julien Julien Julien Julien Julien Julien Julien Julien Julien Julien Julien Julien Julien Julien Julien Julien Julien Julien Julien Julien Julien Julien Julien Julien Julien Julien Julien Julien Julien Julien Julien Julien Julien Julien Julien Julien ");
        message.binaire();

        ImagePNG codage = Codage.encodage(imagePNG, message.getmsgBinaire());
        ImagePNG autre = new ImagePNG(codage.createBufferedImage());
        String msg = Codage.decodage(autre);
        System.out.println(msg.substring(0,50));*/
        
        
        //BufferedImage bf = new BufferedImage(imagePNG.getWidth(),imagePNG.getHeight(), BufferedImage.TYPE_INT_RGB);
        
        
       

        /*ImagePNG imagePNG2 = new ImagePNG(ImageIO.read(new File("image2.png")));
         ImagePNG insere = imagePNG.insertImage(imagePNG2);
        
         ImageView imageView1 = new ImageView("Inseree", insere.getInseredImage().createBufferedImage());
         imageView1.setVisible(true);
         imageView1.setDefaultCloseOperation(EXIT_ON_CLOSE);*/
    }

}
