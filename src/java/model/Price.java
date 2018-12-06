
package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Price {
    private int basePrice = 0;
    
    public Price(){
         int price = 0;
        Scanner s = new Scanner(Price.class.getResourceAsStream("properties.txt"));
        this.basePrice = s.nextInt();
        s.close();
        
    }
    public int getBasePrice(){
       
        return basePrice;
    }
    
    public void setNewPrice(int price) throws FileNotFoundException{
       this.basePrice = price;
         PrintWriter p = null;
        
        String file = "H:\\Personal\\NetBeansProjects\\ESDGroup2Project\\src\\java\\model\\properties.txt";
        
        try {      
            p = new PrintWriter(file);            
          
        } catch (IOException ex) {
            Logger.getLogger(Price.class.getName()).log(Level.SEVERE, null, ex);
        }  
        p.println(basePrice);
        p.close();  
    
    }   
    
}
