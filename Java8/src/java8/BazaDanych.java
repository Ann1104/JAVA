package java8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class BazaDanych {

    public static void main(String[] args) throws Exception {

        Class.forName("com.mysql.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/osoby";
       // String url = "jdbc:mysql://localhost:3306/mysql";
        String login = "root";
        String password = "";
        Connection conn = DriverManager.getConnection(url, login, password);

        Statement st = conn.createStatement();
        //ResultSet rs = st.executeQuery("SELECT * FROM studenci"); //tabela, wiersze kolejny kolumny, kolumny
       ResultSet rs = st.executeQuery("SELECT * FROM przedmioty");
        /* rs.next(); //przesuniecie na pierwszy wiersz, boolean 
        System.out.println(rs.getString("imie"));
           rs.next();
        System.out.println(rs.getString("imie")); //itd
        */
        ResultSetMetaData rsmd = rs.getMetaData();
        
        for (int i = 1; i <=rsmd.getColumnCount(); i++) {
            System.out.print(rsmd.getColumnName(i)+ "\t");
        }
        System.out.println("");
       while(rs.next())
       {
           //System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
           for (int i = 1; i <=rsmd.getColumnCount(); i++) {
               System.out.print(rs.getString(i)+"\t");
           }
           System.out.println(" ");
       }
       
       //st.executeUpdate("CREATE TABLE przedmioty(przedmiot VARCHAR(20),semestr INT);");
       //st.executeUpdate("INSERT INTO przedmioty VALUES ('matematyka','2')");
       st.executeUpdate("INSERT INTO przedmioty VALUES ('fizyka','1')");
       
       rs.close();
       st.close();
       conn.close();
       
       
       
       
    }

}
