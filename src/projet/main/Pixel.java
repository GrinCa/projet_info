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
    private int[] tabBinaire;

    public Pixel(int valeurEntierePixel) {
        this.valeurEntierePixel = valeurEntierePixel;
        tabBinaire = new int[32];

        for (int i = 0; i < 32; i++) {
            tabBinaire[i] = (byte) ((this.valeurEntierePixel >> i) & 1);
        }
    }
    
    

    public void tabToInt() {
        int valeurEntiere = 0;
        for (int i = 0; i < 32; i++) {
            valeurEntiere += this.tabBinaire[i] * Math.pow(2, i);
        }
        this.valeurEntierePixel = valeurEntiere;
    }

    public void reloadTab() {
        for (int i = 0; i < 32; i++) {
            tabBinaire[i] = (byte) ((this.valeurEntierePixel >> i) & 1);
        }
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

    public void setRedCanal(int value) {//la valeur ne doit pas depasser un octet 
        if (value > 255) {
            System.err.println("ERREUR(setRedCanal) : valeur trop grande");
        }
        for (int i = 0; i < 8; i++) {
            tabBinaire[16 + i] |= ((value >> i) & 1);
        }
        this.tabToInt();
    }

    public void setGreenCanal(int value) {//la valeur ne doit pas depasser un octet 
        if (value > 255) {
            System.err.println("ERREUR(setGreenCanal) : valeur trop grande");
        }
        for (int i = 0; i < 8; i++) {
            tabBinaire[8 + i] |= ((value >> i) & 1);
        }
        this.tabToInt();
    }

    public void setBlueCanal(int value) {//la valeur ne doit pas depasser un octet 
        if (value > 255) {
            System.err.println("ERREUR(setBlueCanal) : valeur trop grande");
        }
        for (int i = 0; i < 8; i++) {
            tabBinaire[i] |= ((value >> i) & 1);
        }
        this.tabToInt();
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

    public void insert(int[] mots) {

        for (int i = 0; i < 4; i++) {
            if (mots[i] == 1) {
                tabBinaire[8 * i] = 1;
            } else if (mots[i] == 0) {
                tabBinaire[8 * i] = 0;
            } else if (mots[8 * i] == -1) {
                break;
            }
        }

    }
    
    public int[] getTabBin(){
        return tabBinaire;
    }

    public int getValeurEntierePixel() {
        return valeurEntierePixel;
    }

    public void setValeurEntierePixel(int valeurEntierePixel) {
        this.valeurEntierePixel = valeurEntierePixel;
        this.reloadTab();
    }

    public Pixel pixelSum(Pixel other) {
        return new Pixel(this.getValeurEntierePixel() | other.getValeurEntierePixel());
    }

    public String toString() {
        String affichage = "";
        affichage += "valeur entiere : " + this.getValeurEntierePixel() + "\nvaleur binaire : ";
        for (int i = 0; i < 32; i++) {
            affichage += "" + tabBinaire[31 - i];
        }
        return affichage;
    }

}
