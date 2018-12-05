
package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Price {
    int basePrice = 0;
    
    public Price(){
         int price = 0;
        Scanner s = new Scanner(Price.class.getResourceAsStream("properties.txt"));
        this.basePrice = s.nextInt();
        s.close();
        
    }
    public int getBasePrice(){
       
        return basePrice;
    }
    
    public void increaseBasePrice() throws FileNotFoundException{
        this.basePrice += 2;
         PrintWriter p = null;
        
        String file = "src/Test/properties.txt";
        
        try {      
            p = new PrintWriter(file);            
          
        } catch (IOException ex) {
            Logger.getLogger(Price.class.getName()).log(Level.SEVERE, null, ex);
        }  
        p.println(basePrice);
        p.close();  
    
    }
    
     public void decreaseBasePrice() throws FileNotFoundException{
        this.basePrice -= 2;
        PrintWriter p = null;
        
        String file = "src/Test/properties.txt";
        
        try {      
            p = new PrintWriter(file);            
          
        } catch (IOException ex) {
            Logger.getLogger(Price.class.getName()).log(Level.SEVERE, null, ex);
        }  
        p.println(basePrice);
        p.close();
    }
    
     public String createButton(){
         String s = "";
         
         if (basePrice == 10) {
             // increase price
             s+= "<form method='post' action='AdminServlet.do'>";
             s+= "<input type='submit' name='adminOption' value='Increase Price'>";
             s+= "</form>";
         }
         if (basePrice == 12) {
             // decrease price
              s+= "<form method='post' action='AdminServlet.do'>";
             s+= "<input type='submit' name='adminOption' value='Decrease Price'>";
             s+= "</form>";
             
         }
         return s;
     }
    
}
