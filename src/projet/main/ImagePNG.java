/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.main;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class ImagePNG implements Cloneable{

    private Pixel[][] image;
    private int Width;
    private int Height;

    public ImagePNG(BufferedImage bfIm) {
        this.image = this.createTab(bfIm);//remplit image à partir de bfIm
        this.Width = image.length;
        this.Height = image[0].length;
    }


    public ImagePNG(Pixel[][] image) {
        this.Width = image.length;
        this.Height = image[0].length;
        this.image = new Pixel[Width][Height];
        for(int i =0;i<Width;i++){
            for(int j=0;j<Height;j++){
                this.image[i][j] = new Pixel(image[i][j].getValeurEntierePixel());
            }
        }
    }

    private Pixel[][] createTab(BufferedImage bfIm) {
        Pixel[][] imageTab = new Pixel[bfIm.getWidth()][bfIm.getHeight()];
        for (int i = 0; i < bfIm.getWidth(); i++) {
            for (int j = 0; j < bfIm.getHeight(); j++) {
                imageTab[i][j] = new Pixel(bfIm.getRGB(i, j));
                //stock dans image[][] un objet de type Pixel à partir d'un Integer de bfIm
            }
        }
        return imageTab;
    }

    public ImagePNG filtrage(String type) {

        ImagePNG copy = this.copy();
        double[][] H = this.createFilter(type);
        double valeurDoubleRed = 0;
        double valeurDoubleGreen = 0;
        double valeurDoubleBlue = 0;

        for (int i = 1; i < this.getWidth() - 1; i++) {
            for (int j = 1; j < this.getHeight() - 1; j++) {
                for (int m = -1; m <= 1; m++) {
                    for (int n = -1; n <= 1; n++) {
                        valeurDoubleBlue += (H[1 + m][1 + n]) * (this.getImage()[i + m][j + n].getValue255("blue"));
                        valeurDoubleGreen += (H[1 + m][1 + n]) * (this.getImage()[i + m][j + n].getValue255("green"));
                        valeurDoubleRed += (H[1 + m][1 + n]) * (this.getImage()[i + m][j + n].getValue255("red"));
                    }
                }
                int valeurBlue = (int) valeurDoubleBlue;
                int valeurGreen = (int) valeurDoubleGreen;
                int valeurRed = (int) valeurDoubleRed;
                copy.getImage()[i][j].setValeurEntierePixel(valeurRed << 16 | valeurGreen << 8 | valeurBlue);
                valeurDoubleRed = 0;
                valeurDoubleGreen = 0;
                valeurDoubleBlue = 0;
            }
        }

        return copy;
    }

    public ImagePNG getBlue() {
        ImagePNG copy = new ImagePNG(this.image.clone()); //on creer une copy de this
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                copy.image[i][j].setValeurEntierePixel(image[i][j].getBlue());
            }
        }
        return copy;
    }

    public ImagePNG getGreen() {
        ImagePNG copy = new ImagePNG(this.image.clone()); //on creer une copy de this
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                copy.image[i][j].setValeurEntierePixel(image[i][j].getGreen());
            }
        }
        return copy;
    }

    public ImagePNG getRed() {
        ImagePNG copy = new ImagePNG(this.image.clone()); //on creer une copy de this
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                copy.image[i][j].setValeurEntierePixel(image[i][j].getRed());
            }
        }
        return copy;
    }

    public ImagePNG getGrey() {
        ImagePNG copy = new ImagePNG(this.image.clone()); //on creer une copy de this
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                copy.image[i][j].setValeurEntierePixel(copy.image[i][j].getGrey());
            }
        }
        return copy;
    }

    public ImagePNG binairisation(int seuil) {
        ImagePNG copy = new ImagePNG(this.getGrey().getImage().clone()); //on crée une copy de this grisée
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                int valeur = copy.getImage()[i][j].getValue255("grey");
                if ((valeur > 0) && (valeur <= seuil)) {
                    copy.getImage()[i][j].setValeurEntierePixel(0);
                } else if ((valeur > seuil) && (valeur <= 255)) {
                    copy.getImage()[i][j].setValeurEntierePixel(255 | 255 << 8 | 255 << 16);
                }
            }
        }
        return copy;
    }
    
    public ImagePNG toNegatif(){
        ImagePNG copy = this.copy();
        for(int i=0;i<this.getWidth();i++){
            for(int j=0;j<this.getHeight();j++){
                copy.getImage()[i][j].setValeurEntierePixel((255-this.getImage()[i][j].getValue255("blue"))|
                        (255-this.getImage()[i][j].getValue255("blue"))<<8|
                        (255-this.getImage()[i][j].getValue255("blue"))<<16);
            }
        }
        return copy;
    }

    public ImagePNG copy() {
        return new ImagePNG(this.getImage().clone());
    }

    public void saveImage(String path) {
        try {
            ImageIO.write(this.createBufferedImage(), "png", new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage createBufferedImage() {
        BufferedImage bf = new BufferedImage(this.image.length, this.image[0].length, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                bf.setRGB(i, j, image[i][j].getValeurEntierePixel());
            }
        }
        return bf;
    }

    public double[][] createFilter(String type) {
        double[][] tab = null;
        if (type.equals("flou")) {
            tab = new double[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    tab[i][j] = 1d/9d;
                }
            }
        } else if(type.equals("flou_gauss")){
            tab = new double[3][3];
            int den = 16;
            tab[0][0] = 1d / den;
            tab[0][1] = 2d / den;
            tab[0][2] = 1d / den;
            tab[1][0] = 2d / den;
            tab[1][1] = 4d / den;
            tab[1][2] = 2d / den;
            tab[2][0] = 1d / den;
            tab[2][1] = 2d / den;
            tab[2][2] = 1d / den;
            
        }else if (type.equals("contour")) {
            tab = new double[3][3];
            int den = 4;
            tab[0][0] = 0d / den;
            tab[0][1] = 1d / den;
            tab[0][2] = 0d / den;
            tab[1][0] = 1d / den;
            tab[1][1] = -4d / den;
            tab[1][2] = 1d / den;
            tab[2][0] = 0d / den;
            tab[2][1] = 1d / den;
            tab[2][2] = 0d / den;
        }

        return tab;
    }

    public ImagePNG insertImage(ImagePNG imagePNG) {
        ImagePNG copy = imagePNG.copy();
        int cle = 15 << 4 | 15 << 12 | 15 << 20 | 15 << 28;
        for (int i = 0; i < imagePNG.getWidth(); i++) {
            for (int j = 0; j < imagePNG.getHeight(); j++) {
                int valeurEntiereImageBase = this.getImage()[i][j].getValeurEntierePixel() & cle;
                int valeurEntiereImageInseree = (copy.getImage()[i][j].getValeurEntierePixel() & cle) >> 4;
                copy.getImage()[i][j].setValeurEntierePixel(valeurEntiereImageInseree
                        | valeurEntiereImageBase);
            }
        }
        return copy;
    }
    
    public ImagePNG decalage(int valeur) {
        ImagePNG copy = this.copy();
        int cle = (int) (Math.pow(2, valeur) - 1);
        cle = cle | cle<<8 | cle<<16 | cle<<24;
        for (int i = 0; i < this.getWidth(); i++) {
            for (int j = 0; j < this.getHeight(); j++) {
                copy.getImage()[i][j].setValeurEntierePixel((this.getImage()[i][j].getValeurEntierePixel() & cle) << (8-valeur));
            }
        }
        return copy;
    }

    public ImagePNG getInseredImage() {
        ImagePNG copy = this.copy();
        int cle = 15 | 15 << 8 | 15 << 16 | 15 << 24;
        for (int i = 0; i < copy.getWidth(); i++) {
            for (int j = 0; j < copy.getHeight(); j++) {
                copy.getImage()[i][j].setValeurEntierePixel(
                        (this.getImage()[i][j].getValeurEntierePixel() & cle) << 4);
            }
        }
        return copy;
    }

    public ImagePNG getImageBase() {
        ImagePNG copy = this.copy();
        int cle = 240 | 240<<8 | 240<<8 | 240<<8;
        for (int i = 0; i < copy.getWidth(); i++) {
            for (int j = 0; j < copy.getHeight(); j++) {
                copy.getImage()[i][j].setValeurEntierePixel(
                        this.getImage()[i][j].getValeurEntierePixel() & cle);
            }
        }
        return copy;
    }

    public Pixel getPixel(int width, int height) {
        return this.getImage()[width][height];
    }

    public void setPixel(int width, int height, Pixel pixel) {
        this.getImage()[width][height] = pixel;
    }

    public Pixel[][] getImage() {
        return image;
    }

    public int getWidth() {
        return Width;
    }

    public int getHeight() {
        return Height;
    }

    public void save() {
        try {
            PrintWriter file = new PrintWriter(new FileWriter("sava_Data_Image.txt"));
            for (int i = 0; i < this.getImage().length; i++) {
                for (int j = 0; j < this.getImage()[0].length; j++) {
                    file.println(this.getImage()[i][j].getValue255("red") + ","
                            + this.getImage()[i][j].getValue255("green") + ","
                            + this.getImage()[i][j].getValue255("blue") + "\n");

                }
            }
            file.close();
        } catch (IOException ex) {
            Logger.getLogger(ImagePNG.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setVisible() {
        BufferedImage bf = this.createBufferedImage();
        ImageView fenetre = new ImageView("title", bf);
        fenetre.setSize(new Dimension(bf.getWidth(), bf.getHeight()));
        fenetre.setVisible(true);
    }

}
