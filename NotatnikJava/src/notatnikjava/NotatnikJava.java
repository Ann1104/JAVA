package notatnikjava;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

public class NotatnikJava extends JFrame implements ActionListener {

    public JTextArea textArea;
    public String stringStylCzcionki = "Arial";
    public String stringKrojCzcionki = "x";
    public int intRozmiarCzcionki = 10;
    private JColorChooser paletaColor;
    public JComboBox czcionki;
    public SpinnerNumberModel NSM = new SpinnerNumberModel();
    public JSpinner NS = new JSpinner();
    public JTextArea szukajText;
    public JButton szukajButton;
    public JLabel liczbaZnakowLabel;
    public JCheckBox zawijanieCheckBox;
    public int lbZnakow;
    public JCheckBox B;
    public boolean isB;
    public JCheckBox I;
    public boolean isI;

    public NotatnikJava() {

        setTitle("Notatnik");

        Toolkit zestaw = Toolkit.getDefaultToolkit();
        Dimension rozmiarEkranu = zestaw.getScreenSize();
        int szerEkranu = rozmiarEkranu.width;
        int wysEkranu = rozmiarEkranu.height;
        setBounds(szerEkranu / 4, wysEkranu / 4, szerEkranu / 2, wysEkranu / 2);

        setResizable(false);
        /*
         JPanel panelGórnyLewy = new JPanel(new FlowLayout(FlowLayout.LEFT));
         JPanel panelGórnyPrawy = new JPanel(new FlowLayout(FlowLayout.RIGHT));

         JPanel panelGórny = new JPanel(new GridLayout(1, 2));
         panelGórny.add(panelGórnyLewy);
         panelGórny.add(panelGórnyPrawy);
         add(panelGórny, BorderLayout.NORTH);
         */

        // PASEK MENU
        JMenuBar pasekMenu = new JMenuBar();
        
        zawijanieCheckBox = new JCheckBox("Zawijanie tekstu");
        
        Plik(pasekMenu);
        Edycja(pasekMenu);
        Pomoc(pasekMenu);

        setJMenuBar(pasekMenu);
        B = new JCheckBox("B");
        I = new JCheckBox("I");
        

        
        JMenu stylCzcionki = new JMenu();
        menuStyleCzcionek(stylCzcionki);
        pasekMenu.add(B);
        pasekMenu.add(I);
        pasekMenu.add(zawijanieCheckBox);
        menuRozmiaryCzcionek(pasekMenu);
        pasekMenu.add(czcionki);

        

        // POLE TEKSTOWE
        textArea = new JTextArea();
        JScrollPane sp = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        setLayout(new BorderLayout());
        add(sp, BorderLayout.CENTER);

        //PANEL DOLNY
        JPanel panelLewy = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel panelPrawy = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JPanel panelPrzyciski = new JPanel(new GridLayout(1, 2));
        panelPrzyciski.add(panelLewy);
        panelPrzyciski.add(panelPrawy);
        add(panelPrzyciski, BorderLayout.SOUTH);

        ImageIcon ikonaPrint = new ImageIcon("print.png");
        JButton printButton = new JButton();//(ikonaPrint);
        printButton.setIcon(ikonaPrint);
        printButton.setBounds(50, 50, 100, 100);
        
panelLewy.add(printButton);
printButton.addActionListener(this);
        printButton.setActionCommand("0");
        
        ZnakiPanel(panelLewy);

        textArea.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void removeUpdate(DocumentEvent e) {
                lbZnakow = textArea.getText().length();
                liczbaZnakowLabel.setText(Integer.toString(lbZnakow));
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                lbZnakow = textArea.getText().length();
                liczbaZnakowLabel.setText(Integer.toString(lbZnakow));
            }

            @Override
            public void changedUpdate(DocumentEvent arg0) {
                lbZnakow = textArea.getText().length();
                liczbaZnakowLabel.setText(Integer.toString(lbZnakow));
            }
        });
        SzukajPanel(panelPrawy);

        B.addActionListener(this);
        I.addActionListener(this);
        zawijanieCheckBox.addActionListener(this);
        B.setActionCommand("41");
        I.setActionCommand("51");
        zawijanieCheckBox.setActionCommand("61");

        zastosujZmianyCzcionki();
    }

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        NotatnikJava nt = new NotatnikJava();
        ImageIcon img = new ImageIcon("ikona.png");
        nt.setIconImage(img.getImage());
        nt.setVisible(true);
        nt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void Plik(JMenuBar pasekMenu) {
        JMenu mPlik = new JMenu("Plik");
        mPlik.setMnemonic('P');

        JMenuItem otworz = new JMenuItem("Otwórz");
        JMenuItem zapisz = new JMenuItem("Zapisz");
        JMenuItem zakoncz = new JMenuItem("Zakończ");

        otworz.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
        zapisz.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
        zakoncz.setAccelerator(KeyStroke.getKeyStroke("ctrl K"));

        pasekMenu.add(mPlik);

        mPlik.add(otworz);
        mPlik.add(zapisz);
        mPlik.addSeparator();
        mPlik.add(zakoncz);

        otworz.addActionListener(this);
        zapisz.addActionListener(this);
        zakoncz.addActionListener(this);

        otworz.setActionCommand("11");
        zapisz.setActionCommand("12");
        zakoncz.setActionCommand("13");

    }

    private void Edycja(JMenuBar pasekMenu) {

        JMenu mEdycja = new JMenu("Edycja");
        mEdycja.setMnemonic('E');

        //--------------------KOLORY TLA I CZCIONKI--------------------
        Kolory(mEdycja);
        //--------------------DUZE I MALE LITERY---------------------
        JMenuItem duzeLitery = new JMenuItem("WIELKIE LITERY");
        JMenuItem maleLitery = new JMenuItem("male litery");
        duzeLitery.addActionListener(this);
        maleLitery.addActionListener(this);
        duzeLitery.setActionCommand("23");
        maleLitery.setActionCommand("24");
        mEdycja.add(duzeLitery);
        mEdycja.add(maleLitery);

        //--------------------TYTUL I PODPIS--------------------
        JMenu dodaj = new JMenu("Dodaj");
        menuDodaj(dodaj);

        //--------------------WYCZYSC--------------------------
        JMenuItem wyczysc = new JMenuItem("Wyczyść");
        wyczysc.setAccelerator(KeyStroke.getKeyStroke("ctrl D"));

        wyczysc.addActionListener(this);
        wyczysc.setActionCommand("27");

        pasekMenu.add(mEdycja);

        mEdycja.add(dodaj);
        mEdycja.addSeparator();
        mEdycja.add(wyczysc);

    }

    private void zastosujZmianyCzcionki() {

        Font f;

        if (B.isSelected() && I.isSelected()) {
            f = new Font(stringStylCzcionki, Font.BOLD + Font.ITALIC, intRozmiarCzcionki);
        } else if (B.isSelected() && (I.isSelected() == false)) {
            f = new Font(stringStylCzcionki, Font.PLAIN + Font.BOLD, intRozmiarCzcionki);
        } else if (I.isSelected() && (B.isSelected() == false)) {
            f = new Font(stringStylCzcionki, Font.PLAIN + Font.ITALIC, intRozmiarCzcionki);
        } else {
            f = new Font(stringStylCzcionki, Font.PLAIN, intRozmiarCzcionki);
        }

        textArea.setFont(f);

    }

    private void menuStyleCzcionek(JMenu stylCzcionki) {

        String[] rodzaje = {"Arial", "Calibri", "TimesNewRoman", "Goudy Stout", "Verdana"};
        czcionki = new JComboBox(rodzaje);
        czcionki.setEditable(false);

        czcionki.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String rodzajCzcionki = (String) czcionki.getSelectedItem();

                switch (rodzajCzcionki) {
                    case "Arial":
                        stringStylCzcionki = "Arial";
                        zastosujZmianyCzcionki();
                        break;

                    case "Calibri":
                        stringStylCzcionki = "Calibri";
                        zastosujZmianyCzcionki();
                        break;

                    case "TimesNewRoman":
                        stringStylCzcionki = "Times New Roman";
                        zastosujZmianyCzcionki();
                        break;

                    case "Goudy Stout":
                        stringStylCzcionki = "Goudy Stout";
                        zastosujZmianyCzcionki();
                        break;

                    case "Verdana":
                        stringStylCzcionki = "Verdana";
                        zastosujZmianyCzcionki();
                        break;
                }
            }
        });
    }

    private void menuRozmiaryCzcionek(JMenuBar pasekMenu) {

        //pasekMenu.setToolTipText("Rozmiar");
        int start = 12;
        int min = 4;
        int max = 80;
        int step = 2;

        NSM = new SpinnerNumberModel(start, min, max, step);
        NS = new JSpinner(NSM);

        ChangeListener listener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                intRozmiarCzcionki = (int) NS.getValue();
                zastosujZmianyCzcionki();
            }
        };
        NS.addChangeListener(listener);

        pasekMenu.add(NS);

    }

    private void pasekKolorCzcionki() {

        JFrame paletaKoloruCzcionki = new JFrame();
        paletaKoloruCzcionki.setSize(500, 420);
        paletaKoloruCzcionki.setVisible(true);
        paletaKoloruCzcionki.setTitle("Kolor Czcionki");

        paletaColor = new JColorChooser();
        paletaKoloruCzcionki.add(paletaColor, BorderLayout.PAGE_END);

        paletaColor.getSelectionModel().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent zdarzenie) {
                textArea.setForeground(paletaColor.getColor());
            }
        });
    }

    private void pasekKolorTla() {

        JFrame paletaKoloruTla = new JFrame();
        paletaKoloruTla.setSize(700, 420);
        paletaKoloruTla.setVisible(true);
        paletaKoloruTla.setTitle("Kolor Tła");

        paletaColor = new JColorChooser();
        paletaKoloruTla.add(paletaColor, BorderLayout.PAGE_END);

        paletaColor.getSelectionModel().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent zdarzenie) {
                textArea.setBackground(paletaColor.getColor());
            }
        });
    }

    private void menuDodaj(JMenu dodaj) {
        JMenuItem tytul = new JMenuItem("Tytul");
        JMenuItem podpis = new JMenuItem("Podpis");
        dodaj.add(tytul);
        dodaj.add(podpis);

        tytul.addActionListener(this);
        podpis.addActionListener(this);
        tytul.setActionCommand("25");
        podpis.setActionCommand("26");
    }

    private void Pomoc(JMenuBar pasekMenu) {
        JMenu mPomoc = new JMenu("Pomoc");
        mPomoc.setMnemonic('C');
        JMenuItem oAutorze = new JMenuItem("OAutorze");

        pasekMenu.add(mPomoc);
        mPomoc.add(oAutorze);

        oAutorze.addActionListener(this);

        oAutorze.setActionCommand("31");
    }

    private void Kolory(JMenu mEdycja) {

        JMenuItem paletaKoloruTla = new JMenuItem("Kolor Tła");
        JMenuItem paletaKoloruCzcionki = new JMenuItem("Kolor Czcionki");
        mEdycja.add(paletaKoloruTla);
        mEdycja.add(paletaKoloruCzcionki);

        paletaKoloruTla.addActionListener(this);
        paletaKoloruCzcionki.addActionListener(this);
        paletaKoloruTla.setActionCommand("21");
        paletaKoloruCzcionki.setActionCommand("22");
    }

    public void ZnakiPanel(JPanel panelLewy) {
        JLabel znakiLabel = new JLabel();
        znakiLabel.setText("Ilość znaków : ");
        panelLewy.add(znakiLabel);

        liczbaZnakowLabel = new JLabel();
        panelLewy.add(liczbaZnakowLabel);
    }

    public void SzukajPanel(JPanel panelPrawy) {
        szukajText = new JTextArea();
        szukajText.setLineWrap(true);
        Font f = new Font("Arial", Font.PLAIN, 11);
        szukajText.setFont(f);
        //JLabel szukajLabel = new JLabel();
        //szukajLabel.setText("Wpisz co chcesz wyszukać: ");
        //szukajText.setText("Tutaj wpisz tekst, który chcesz wyszukać");

        szukajText.setForeground(Color.DARK_GRAY);
        szukajText.setBackground(Color.LIGHT_GRAY);

        szukajButton = new JButton("Szukaj");
        //panelPrawy.add(szukajLabel);
        panelPrawy.add(szukajText);
        panelPrawy.add(szukajButton);
        szukajButton.addActionListener(this);
        szukajButton.setActionCommand("71");

    }

    private void Szukaj() {

        String text = textArea.getText();
        String szText = szukajText.getText();

        textArea.getHighlighter().removeAllHighlights();
        int indeks = 0;
        int indeksWystapienia = 0;
        int liczbaWystapien = 0;
        Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);

        int length = szText.length();

        if (text.equals("") || szText.equals("")) {
            indeksWystapienia = -1;
        }
        while (indeksWystapienia != -1) {
            indeksWystapienia = text.indexOf(szText, indeks);
            if (indeksWystapienia != -1) {
                try {
                    indeks = indeksWystapienia + 1;
                    liczbaWystapien++;
                    textArea.getHighlighter().addHighlight(indeksWystapienia, indeksWystapienia + length, painter);
                } catch (BadLocationException ex) {
                    Logger.getLogger(Okno.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        JOptionPane.showMessageDialog(this, "Liczba wystąpien: "
                + liczbaWystapien, "Liczba wystąpień szukanego tekstu", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (Integer.parseInt(e.getActionCommand())) {
            case 0: {
                Drukuj d = new Drukuj();
                break;
            }
            case 11: { //otworz
                JFileChooser pliki = new JFileChooser(".");
                if (JFileChooser.APPROVE_OPTION == pliki.showOpenDialog(this)) {
                    try {
                        File f = pliki.getSelectedFile();
                        setTitle(f.getAbsolutePath() + " Notatnik");
                        BufferedReader br = new BufferedReader(new FileReader(f));
                        String temp = "";
                        while (br.ready()) {
                            temp += br.readLine() + "\n";
                        }
                        textArea.setText(temp);
                    } catch (IOException ex) {
                        System.out.println("Brak pliku");
                    }
                }
                break;
            }
            case 12: { //zapisz
                JFileChooser pliki = new JFileChooser(".");
                if (JFileChooser.APPROVE_OPTION == pliki.showSaveDialog(this)) {
                    try {
                        File f = pliki.getSelectedFile();
                        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
                        bw.write(textArea.getText());
                        bw.flush();
                        bw.close();
                    } catch (IOException ee) {
                        System.out.println("Problemy z zapisem");
                    }
                }

                break;
            }
            case 13: { //zakoncz
                System.exit(0);
                break;
            }
            case 21: {
                pasekKolorTla();
                break;
            }
            case 22: {
                pasekKolorCzcionki();
                break;
            }
            case 23: {
                textArea.setText(textArea.getText().toUpperCase());

                break;
            }
            case 24: {
                textArea.setText(textArea.getText().toLowerCase());

                break;
            }
            case 25: { // tytul
                textArea.setText("Szanowny Panie \n\n" + textArea.getText());

                break;
            }
            case 26: { //podpis
                textArea.setText(textArea.getText() + "\n\n Z poważaniem: \n");
                break;
            }
            case 27: { // wyczysc
                textArea.setText("");
                break;
            }
            case 31: { //oAutorze
                JOptionPane.showMessageDialog(this, "Autor: Anna Gliszczyńska");
                break;
            }
            case 41: {
                zastosujZmianyCzcionki();
                break;
            }
            case 51: {
                zastosujZmianyCzcionki();
                break;
            }
            case 61: {
                if (zawijanieCheckBox.isSelected()) {
                    textArea.setLineWrap(true);
                } else {
                    textArea.setLineWrap(false);
                }
                break;
            }
            case 71: {
                Szukaj();
                break;
            }
        }
    }

}
