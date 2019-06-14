/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.main;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Julien
 */
public class Codage {

    //glisse le message dans la photo
    public static ImagePNG encodage(ImagePNG imagePNG, ArrayList<Integer> messageBinaire) {
        if (messageBinaire.size() >= imagePNG.getWidth() * imagePNG.getHeight()) {
            JOptionPane.showMessageDialog(null, "Le message est trop long");
            return imagePNG.copy();
        }
        ImagePNG copy = imagePNG.copy();
        int cle = 1;
        int compteur = 0;
        for (int i = 0; i < messageBinaire.size(); i++) {
            int message = messageBinaire.get(i);
            int valeurEntierePixelModifiee = ((copy.getImage()[compteur / imagePNG.getHeight()][compteur % imagePNG.getHeight()].getValeurEntierePixel() >> 1) << 1);
            copy.getImage()[compteur / imagePNG.getHeight()][compteur % imagePNG.getHeight()]
                    .setValeurEntierePixel(valeurEntierePixelModifiee | message);
            compteur++;
        }
        return copy;
    }

    public static String decodage(ImagePNG imageADecodee) {
        int longueurTot = imageADecodee.getWidth() * imageADecodee.getHeight();
        ArrayList<Integer> messageBin = new ArrayList<Integer>();
        int longueurMessage = 0;

        for (int i = 0; i < 32; i++) {
            longueurMessage |= (imageADecodee.getImage()[i / imageADecodee.getHeight()][i % imageADecodee.getHeight()]
                    .getValeurEntierePixel() & 1) << i;
        }

        for (int i = 32; i < 32 + longueurMessage; i++) {
            messageBin.add(imageADecodee.getImage()[i / imageADecodee.getHeight()][i % imageADecodee.getHeight()]
                    .getValeurEntierePixel() & 1);
        }

        String message = "";
        for (int i = 0; i < messageBin.size(); i += 8) {
            byte element = 0;
            for (int j = 0; j < 8; j++) {
                element |= messageBin.get(i + j) << j;
            }
            message += (char) element;
        }
        return message;
    }

    private ImagePNG imagePNG;

    
}
