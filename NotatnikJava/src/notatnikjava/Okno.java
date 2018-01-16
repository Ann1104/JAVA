package notatnikjava;


import javax.swing.JFrame;

public class Okno extends JFrame {

    public Okno() {

        super();

        setSize(400, 200);
        setLocation(200, 200);

    }

    public static void main(String[] args) {

        Okno ok = new Okno();
        ok.setVisible(true);
        ok.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
