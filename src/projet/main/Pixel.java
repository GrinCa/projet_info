/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.main;

import java.util.ArrayList;

/**
 *
 * @author jecobich
 */
class Pixel {

    private int valeurEntierePixel;
    
    public Pixel(int valeurEntierePixel) {
        this.valeurEntierePixel = valeurEntierePixel;
    }
    
    public Pixel(){
        this.valeurEntierePixel = 0;
    }
    
    public Pixel copy(){
        return new Pixel(this.getValeurEntierePixel());
    }


    public int getBlue() {
        int cle = 255;
        int blueValue = valeurEntierePixel & cle;
        return blueValue;
    }

    public int getGreen() {
        int cle = 255 << 8;
        int greenValue = valeurEntierePixel & cle;
        return greenValue;
    }

    public int getRed() {
        int cle = 255 << 16;
        int redValue = valeurEntierePixel & cle;
        return redValue;
    }

    public int getGrey() {
        int red255 = getValue255("red");
        int green255 = getValue255("green");
        int blue255 = getValue255("blue");
        int grey255 = (red255 + green255 + blue255) / 3;
        return grey255 | grey255 << 8 | grey255 << 16;
    }

    public int getValue255(String canal) {
        int value255 = 0;
        if (canal.equalsIgnoreCase("red")) {
            value255 = getRed() >> 16;
        } else if (canal.equalsIgnoreCase("green")) {
            value255 = getGreen() >> 8;
        } else if (canal.equalsIgnoreCase("blue")) {
            value255 = getBlue();
        } else if (canal.equalsIgnoreCase("grey")) {
            value255 = getBlue(); //renvoie la valeur grise si l'image est deja filtrée en grise
        }
        return value255;
    }

    public int getValeurEntierePixel() {
        return valeurEntierePixel;
    }

    public void setValeurEntierePixel(int valeurEntierePixel) {
        this.valeurEntierePixel = valeurEntierePixel;
    }

    public String toString() {
        String affichage = "";
        affichage += "valeur entiere : " + this.getValeurEntierePixel() + "\nvaleur binaire : ";
        return affichage;
    }

}
