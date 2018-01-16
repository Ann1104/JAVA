package notatnikjava;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.JPanel;

public class Drukuj extends JPanel {

    public Drukuj() {
        
        PrinterJob job = PrinterJob.getPrinterJob();
        PageFormat landscape = job.defaultPage();
        landscape.setOrientation(PageFormat.LANDSCAPE);
        Book bk = new Book();
        bk.append((Printable) new DefaultPage(), job.defaultPage());
        bk.append(new PaintContent(), landscape);
        job.setPageable(bk);
        if (job.printDialog()) {
            try {
                job.print();
            } catch (Exception exc) {
            }
        }
        
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawShapes(g2);
    }

    static void drawShapes(Graphics2D g2) {
        g2.fill(new RoundRectangle2D.Double(10, 10, 200, 200, 10, 10));
    }

}

class DefaultPage implements Printable {

    Font fnt = new Font("Helvetica-Bold", Font.PLAIN, 48);

    public int print(Graphics g, PageFormat pf, int pageIndex)
            throws PrinterException {
        g.setFont(fnt);
        g.setColor(Color.black);
        g.drawString("Sample Shapes", 100, 200);
        return Printable.PAGE_EXISTS;
    }
}

class PaintContent implements Printable {

    public int print(Graphics g, PageFormat pf, int pageIndex)
            throws PrinterException {
        Drukuj.drawShapes((Graphics2D) g);
        return Printable.PAGE_EXISTS;

    }  
}

