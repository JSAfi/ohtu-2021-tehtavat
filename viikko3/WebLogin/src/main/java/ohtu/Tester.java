package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.Random;

public class Tester {
    private static void clickLinkWithText(String text, WebDriver driver) {
        int trials = 0;
        while (trials++ < 5) {
            try {
                WebElement element = driver.findElement(By.linkText(text));
                element.click();
                break;
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
            }
        }
    }
    public static void main(String[] args) {
//        WebDriver driver = new ChromeDriver();
        WebDriver driver = new HtmlUnitDriver();

        driver.get("http://localhost:4567");
        
        sleep(2);
        System.out.println("===== ALKAA: ETUSIVUN KOODI =====");
        System.out.println(driver.getPageSource());
        System.out.println("===== PÄÄTTYY: ETUSIVUN KOODI =====");
/*
*   Alkuperäinen koodi
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        
        sleep(2);
        element.submit();

        sleep(3);

        System.out.println(driver.getPageSource());
*/
        // Testataan: Uuden käyttäjän luominen + uloskirjautuminen sovelluksesta
        // Uuden käyttäjän luominen kuten alla olevassa testissä
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        Random r = new Random();

        element = driver.findElement(By.name("username"));
        element.sendKeys("jaakko"+r.nextInt(100000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("vety1auto");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("vety1auto");
        element = driver.findElement(By.name("signup"));
        element.submit();

        clickLinkWithText("continue to application mainpage", driver);

        System.out.println("===== ALKAA: LOREM IPSUM =====");
        System.out.println(driver.getPageSource());
        System.out.println("===== PÄÄTTYY: LOREM IPSUM =====");

        clickLinkWithText("logout", driver);

        System.out.println("===== ALKAA: MAINPAGE =====");
        System.out.println(driver.getPageSource());
        System.out.println("===== PÄÄTTYY: MAINPAGE =====");

        //  Testataan: Uuden käyttäjän
/*
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        Random r = new Random();

        element = driver.findElement(By.name("username"));
        element.sendKeys("jaakko"+r.nextInt(100000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("vety1auto");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("vety1auto");
        element = driver.findElement(By.name("signup"));
        element.submit();

        System.out.println("===== ALKAA: UUDEN KÄYTTÄJÄN LUOMINEN =====");
        System.out.println(driver.getPageSource());
        System.out.println("===== PÄÄTTYY: UUDEN KÄYTTÄJÄN LUOMNEN =====");
 */
/*
Toimii, koska tulostuksessa:
  <body>
    <h1>
      Welcome to Ohtu Application!
    </h1>
    <ul>
      <li>
        <a href="ohtu">
          continue to application mainpage
        </a>
      </li>
      <li>
        <a href="https://ohjelmistotuotanto-hy.github.io/">
          course page
        </a>
      </li>
    </ul>
  </body>
 */


        // Testataan: epäonnistunut kirjautuminen, oikea käyttäjätunnus, väärä salasana
        // Käyttäjätunnus ja salasana tehty aiemmin salasanat.txt -tiedostoon
/*
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        element = driver.findElement(By.name("username"));
        element.sendKeys("jaakko");
        element = driver.findElement(By.name("password"));
        element.sendKeys("vituixmän");
        element = driver.findElement(By.name("login"));
        element.submit();

        System.out.println("===== ALKAA: EPÄONNISTUNEEN KIRJAUTUMISEN SIVUN KOODI =====");
        System.out.println(driver.getPageSource());
        System.out.println("===== PÄÄTTYY: EPÄONNISTUNEEN KIRJAUTUMISEN SIVUN KOODI =====");
*/
/*
Testi toimii koska tulostuksessa on :
        <div id="error" style="color:red">
      <p>
        <em>
                invalid username or password
                </em>
      </p>
    </div>
*/
/**/
        driver.quit();

        System.out.println("Driver quit...");
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
