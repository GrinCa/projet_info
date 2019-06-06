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
    
    
    public Codage(ImagePNG imagePNG){
        this.imagePNG = imagePNG;
    }
    
    
    //glisse le message dans la photo
    public ImagePNG encodage(ArrayList<Integer> messageBinaire) {
        Pixel[][] copy = imagePNG.copy().getImage();
        int compteur = 0;
        for(int i=0;i<=messageBinaire.size()-4;i = i+4){
            for(int j=0;j<4;j++){
                copy[compteur/imagePNG.getHeight()]
                        [compteur%imagePNG.getHeight()].getTabBin()[8*j] = messageBinaire.get(i+j);
            }
            compteur++;
        }
        return new ImagePNG(copy);
    }
    
    public String decodage(){
        int longueurTot = imagePNG.getWidth()*imagePNG.getHeight();
        ArrayList<Integer> messageBin = new ArrayList<Integer>();
        for(int i =0;i<longueurTot;i++){
            for(int j=0;j<4;j++){
                messageBin.add(imagePNG.getImage()[i/imagePNG.getHeight()]
                        [i%imagePNG.getHeight()].getTabBin()[8*j]);
            }

        }
        
        String message = "";
        for(int i=0;i<=messageBin.size()-8;i++){
            byte element = 0;
            for(int j = 0;j<8;j++){
                element += messageBin.get(i+j)*Math.pow(2,j);
            }
            message += (char)element;
        }
        return message;
    }
    
    private ImagePNG imagePNG;
}
