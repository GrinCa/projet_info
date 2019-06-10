/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.main;

import java.util.ArrayList;

/**
 *
 * @author Julien
 */
public class Codage {

    //glisse le message dans la photo
    public static ImagePNG encodage(ImagePNG imagePNG, ArrayList<Integer> messageBinaire) {
        Pixel[][] copy = imagePNG.copy().getImage();
        int cle = 254 | 254 << 8 | 254 << 16 | 254 << 24;
        int compteur = 0;
        for (int i = 0; i <= messageBinaire.size() - 4; i = i + 4) {
            int message = messageBinaire.get(i)
                    | messageBinaire.get(i + 1) << 8
                    | messageBinaire.get(i + 2) << 16
                    | messageBinaire.get(i + 3) << 24;
            int valeurEntierePixelModifiee = copy[compteur / imagePNG.getHeight()][compteur % imagePNG.getHeight()].getValeurEntierePixel() & cle;
            copy[compteur / imagePNG.getHeight()][compteur % imagePNG.getHeight()]
                    .setValeurEntierePixel(valeurEntierePixelModifiee | message);
            compteur++;
        }
        return new ImagePNG(copy);
    }

    public static String decodage(ImagePNG imageADecodee, int diviseurLongueur) {
        int longueurTot = imageADecodee.getWidth() * imageADecodee.getHeight();
        ArrayList<Integer> messageBin = new ArrayList<Integer>();
        for (int i = 0; i < longueurTot; i++) {
            messageBin.add(imageADecodee.getImage()[i / imageADecodee.getHeight()][i % imageADecodee.getHeight()]
                    .getValeurEntierePixel() & 1);
            messageBin.add((imageADecodee.getImage()[i / imageADecodee.getHeight()][i % imageADecodee.getHeight()]
                    .getValeurEntierePixel() & 256) >> 8);
            messageBin.add((imageADecodee.getImage()[i / imageADecodee.getHeight()][i % imageADecodee.getHeight()]
                    .getValeurEntierePixel() & 65_536) >> 16);
            messageBin.add((imageADecodee.getImage()[i / imageADecodee.getHeight()][i % imageADecodee.getHeight()]
                    .getValeurEntierePixel() & 16_777_216) >> 24);

        }
        System.out.println("ok");
        
        String message = "";
        for (int i = 0; i <= messageBin.size() / diviseurLongueur - 8; i += 8) {
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
