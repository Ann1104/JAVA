package java9;

import javax.swing.JFrame;

public class Okienko extends JFrame {

    public Okienko() {
        super(); //szablon nadklasy
        setSize(400, 200);//w pikselach szerokosc,. wysokosc
        setLocation(200, 200);

        //.------x
        //|
        //|
        //|y+
    }

    public static void main(String[] args) {
        Okienko ok = new Okienko();
        ok.setVisible(true);//widocznosc okienka
        ok.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //umozliwienie zamykania okienka
    }

}
