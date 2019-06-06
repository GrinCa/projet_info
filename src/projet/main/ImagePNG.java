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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class ImagePNG {

    private Pixel[][] image;
    private int Width;
    private int Height;

    public ImagePNG(BufferedImage bfIm) {
        this.image = this.createTab(bfIm);//remplit image à partir de bfIm
        this.Width = image.length;
        this.Height = image[0].length;
    }

    public ImagePNG(String path) {
        BufferedImage bfIm = null;
        try {
            bfIm = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.image = this.createTab(bfIm);
        this.Width = image.length;
        this.Height = image[0].length;
    }

    public ImagePNG(Pixel[][] image) {
        this.image = image;
        this.Width = image.length;
        this.Height = image[0].length;
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

        ImagePNG copy = new ImagePNG(this.getImage().clone());
        double[][] H = this.createFilter(type);
        double valeurDouble = 0;

        for (int i = 1; i < this.getImage().length - 1; i++) {
            for (int j = 1; j < this.getImage()[0].length - 1; j++) {
                for (int m = -1; m <= 1; m++) {
                    for (int n = -1; n <= 1; n++) {
                        //System.out.println(this.getImage()[i + m][j + n].getValue255("grey"));
                        valeurDouble += (H[1 + m][1 + n]) * ((this.getImage()[i + m][j + n].getValue255("grey")));
                    }
                }
                int valeur = (int) valeurDouble;
                //System.out.println(new Scanner(System.in).nextLine());
                //System.out.println(valeurDouble);
                copy.getImage()[i][j].setValeurEntierePixel(valeur << 16 | valeur << 8 | valeur);
                valeurDouble = 0;
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
    
    public ImagePNG copy(){
        return new ImagePNG(this.getImage().clone());
    }
    
    public void saveImage(String path){
        try{
            ImageIO.write(this.createBufferedImage(), "png", new File(path));
        }catch(IOException e){
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
                    tab[i][j] = ((double) 1) / ((double) 9);
                }
            }
        } else if (type.equals("contour")) {
            tab = new double[3][3];
            int den = 15;
            tab[0][0] = -1d/den;
            tab[0][1] = -1d/den;
            tab[0][2] = -1d/den;
            tab[1][0] = -1d/den;
            tab[1][1] = 8d/den;
            tab[1][2] = -1d/den;
            tab[2][0] = -1d/den;
            tab[2][1] = -1d/den;
            tab[2][2] = -1d/den;
        } else if (type.equals("nette")) {
            tab = new double[3][3];
            tab[0][0] = 0;
            tab[0][1] = 1d / 5;
            tab[0][2] = 0;
            tab[1][0] = 1d / 5;
            tab[1][1] = 1d;
            tab[1][2] = 1d / 5;
            tab[2][0] = 0;
            tab[2][1] = 1d / 5;
            tab[2][2] = 0;
        }

        return tab;
    }

    public ImagePNG insertImage(ImagePNG imagePNG) {
        ImagePNG copy = new ImagePNG(this.getImage().clone());
        for (int i = 0; i < imagePNG.getWidth(); i++) {
            for (int j = 0; j < imagePNG.getHeight(); j++) {
                for(int k=0;k<3;k++){
                    this.getImage()[i][j].getTabBin()[8*k] = 
                            imagePNG.getImage()[i][j].getTabBin()[8*(k+1)-4];
                    this.getImage()[i][j].getTabBin()[8*k+1] = 
                            imagePNG.getImage()[i][j].getTabBin()[8*(k+1)-3];
                    this.getImage()[i][j].getTabBin()[8*k+2] = 
                            imagePNG.getImage()[i][j].getTabBin()[8*(k+1)-2];
                    this.getImage()[i][j].getTabBin()[8*k+3] = 
                            imagePNG.getImage()[i][j].getTabBin()[8*(k+1)-1];
                }
            }
        }System.out.println("ok");
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
