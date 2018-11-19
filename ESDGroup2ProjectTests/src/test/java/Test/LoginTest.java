/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.io.File;
import java.util.Map;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.exec.*;

/**
 *
 * @author patin_000
 */
public class LoginTest {
    
    @Test
    public void testSimple() throws Exception {
        
        //System.setProperty(key, value)
        String filePath = new File("chromedriver.exe").getAbsolutePath();
        System.setProperty("webdriver.chrome.driver", filePath);

        // Create a new instance of the Firefox driver
        // Notice that the remainder of the code relies on the interface, 
        // not the implementation.
        WebDriver driver = new ChromeDriver();

        // And now use this to visit NetBeans
        driver.get("http://localhost:8080/ESDGroup2Project/");
        
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.netbeans.org");
        
                // Check the title of the page
        // Wait for the page to load, timeout after 10 seconds
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver d) {
                return d.getTitle().contains("AlphaCab");
            }
        });
        
        WebElement userNameInput = driver.findElement(By.xpath("//input[@name='uName']"));
        WebElement passwordInput = driver.findElement(By.xpath("//input[@name='pass']"));
        WebElement loginButton = driver.findElement(By.xpath("//input[@type='submit']"));

        userNameInput.sendKeys("aydinme");
        passwordInput.sendKeys("108752");
        loginButton.click();
        
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver d) {
                return d.getTitle().contains("Welcome");
            }
        });
        
        WebElement logoutButton = driver.findElement(By.xpath("//input[@type='submit']"));
        logoutButton.click();
        
        //Close the browser
        driver.quit();
    }
    
}
