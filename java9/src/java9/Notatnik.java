package java9;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup; //swing.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Notatnik extends JFrame implements ActionListener{
public JTextArea textArea ;
    public Notatnik() {

        setTitle("Notatnik"); //tytuł na pasku

        Toolkit zestaw = Toolkit.getDefaultToolkit();
        Dimension rozmiarEkranu = zestaw.getScreenSize(); //wymiary naszego ekranu
        int szerEkranu = rozmiarEkranu.width; //mozna bezposrednio dzialac na tych polach ale lepiej jakby było getWidth();
        int wysEkranu = rozmiarEkranu.height;
        setBounds(szerEkranu / 4, wysEkranu / 4, szerEkranu / 2, wysEkranu / 2); //2 pierwsze gdzie zaczepiamy górny róg, 2 kolejne szer i wysokosc

        setResizable(false);//blokuje mozliwosc zmianu rozmiaru okna

        JMenuBar pasekMenu = new JMenuBar();
        setJMenuBar(pasekMenu);

//PLIK
        JMenu mPlik = new JMenu("Plik");
        mPlik.setMnemonic('P');
        mPlik.setToolTipText(" Plik ");

        JMenuItem otworz = new JMenuItem("Otworz");
        otworz.setAccelerator(KeyStroke.getKeyStroke("ctrl+O"));
        JMenuItem zapisz = new JMenuItem("Zapisz");
        zapisz.setAccelerator(KeyStroke.getKeyStroke("ctrl+Z"));
        JMenuItem zakoncz = new JMenuItem("Zakoncz");
        zakoncz.setAccelerator(KeyStroke.getKeyStroke("ctrl+K"));

        mPlik.add(otworz);
        mPlik.add(zapisz);
        mPlik.add(zakoncz);

        //EDYCJA
        JMenu mEdycja = new JMenu("Edycja");
        mEdycja.setMnemonic('E');
        mEdycja.setToolTipText(" Edycja ");

        JRadioButtonMenuItem powiekszC = new JRadioButtonMenuItem("powieksz czcionke");
        JRadioButtonMenuItem pomniejszC = new JRadioButtonMenuItem("pomniejsz czcionke");
        pomniejszC.setSelected(true);
        JMenuItem wyczysc = new JMenuItem("Wyczysc");
        wyczysc.setAccelerator(KeyStroke.getKeyStroke("ctrl+D"));

        ButtonGroup bg = new ButtonGroup();
        bg.add(powiekszC);
        bg.add(pomniejszC);

        mEdycja.add(powiekszC);
        mEdycja.add(pomniejszC);
        mEdycja.add(wyczysc);

        //POMOC
        JMenu mPomoc = new JMenu("Pomoc");
        mEdycja.setMnemonic('O');
        mPomoc.setToolTipText(" Pomoc ");

        pasekMenu.add(mPlik);
        pasekMenu.add(mEdycja);
        pasekMenu.add(mPomoc);

        //JTextArea 
                textArea = new JTextArea();
        JScrollPane sp = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        setLayout(new BorderLayout());

        add(sp, BorderLayout.CENTER);
        JPanel panelLewy = new JPanel(new FlowLayout(FlowLayout.CENTER)); //jeden za drugim
        JPanel panelPrawy = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JPanel panelPrzyciski = new JPanel(new GridLayout(1, 2)); // siatkowy, ile wierzy i kolumn

        panelPrzyciski.add(panelLewy);
        panelPrzyciski.add(panelPrawy);

        add(panelPrzyciski, BorderLayout.SOUTH);
        JButton tytul = new JButton("tytuł");
        JButton podpis = new JButton("podpis");
        panelLewy.add(tytul);
        panelLewy.add(podpis);
        
        JRadioButton bi = new JRadioButton("biały",true);
	JRadioButton zo = new JRadioButton("żółty");
	JRadioButton zi = new JRadioButton("zielony");

        bi.setActionCommand("51");
	zo.setActionCommand("52");
	zi.setActionCommand("53");
        
        bi.addActionListener(this);
	zo.addActionListener(this);
	zi.addActionListener(this);
        
	ButtonGroup bg1 = new ButtonGroup();
        
	bg1.add(bi);
	bg1.add(zo);
	bg1.add(zi);
        
	panelPrawy.add(bi);
	panelPrawy.add(zo);
	panelPrawy.add(zi);
        
        //mnemoniki lezy alt i podkreslona litere
    }

    public static void main(String[] args) throws Exception {
        //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        
        Notatnik nt = new Notatnik();
        //https://stackoverflow.com/questions/1614772/how-to-change-jframe-icon
        ImageIcon img = new ImageIcon("icon.jpg");
        nt.setIconImage(img.getImage());

        nt.setVisible(true);
        nt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
       //e.getSource()
       //sprawdzenie ktory przycisk zostal wcisniety i wykonanie odpowiedniej akcji
		
       switch (Integer.parseInt(e.getActionCommand()))
		{
			case 11:
			{
				break;
			}
			case 12:
			{
				break;
			}
			case 13:
			{
				break;
			}
			case 21:
			{
				break;
			}
			case 22:
			{
				break;
			}
			case 23:
			{
				break;
			}
			case 31:
			{
				break;
			}
			case 41:
			{
				break;
			}		
			case 42:
			{
				break;
			}			
			case 51:
			{
				textArea.setBackground(Color.WHITE);
				break;
			}
			case 52:
			{
				textArea.setBackground(Color.YELLOW);
				break;
			}
			case 53:
			{
				textArea.setBackground(Color.GREEN);
				break;
			}
		}
       
    }
}
