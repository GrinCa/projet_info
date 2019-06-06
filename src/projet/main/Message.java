package projet.main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author croubey
 */
public class Message {

    private String message;
    private ArrayList<Integer> msgBinaire;
    private char[] charVar;
    private String adresse;

    public Message(String adresse) {
        this.adresse = adresse;
    }
    

    public void lire() {

        StringBuilder msg = new StringBuilder();

        try {
            BufferedReader bf = new BufferedReader(new FileReader(adresse));
            while (bf.ready()) {
                msg.append(bf.readLine());
            }

        } catch (IOException e) {
        }
        message = msg.toString();

    }

    public String getMessage() {
        return message;
    }

    public void stringToChar() {
        int t = message.length();
        charVar = new char[t];
        for (int i = 0; i < t; i++) {
            //La méthode charAt trouve la position et convert en char.
            charVar[i] = message.charAt(i);
            
        }
    }

    public void binaire() {
        this.stringToChar();
        msgBinaire = new ArrayList<Integer>();
        for (int i = 0; i < charVar.length; i++) {
            char a = charVar[i];
            for (int j = 0; j < 8; j++) {
                msgBinaire.add((a>>j) & 1);
            }
        }
    }

    public char[] getCharVar() {
        return charVar;
    }
    public ArrayList<Integer> getmsgBinaire(){
        return msgBinaire;
    }

    @Override
    public String toString() {
        return "Message{" + "message=" + message + ", msgBinaire=" + msgBinaire + '}';
    }
}
