/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.main;

/**
 *
 * @author Julien
 */
public class Binaire {
    
    static void affiche(int n){
        String bin = "";
        for(int i =0;i<32;i++){
            bin+=""+((n>>i)&1);
        }
        System.out.println(bin);
    }
}
