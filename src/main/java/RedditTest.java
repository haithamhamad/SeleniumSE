import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.*;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public class RedditTest {

    public static void main(String [] arr){
        CharSequence email = new String("ashraf.habrumman@gmail.com" + Keys.ENTER);
        CharSequence password = new String("ashra^$?!+@nab" + Keys.ENTER);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-web-security", "--allow-running-insecure-content", "--disable-notifications");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
//        driver.get("https://accounts.google.com/ServiceLogin/identifier?hl=ar&passive=true&continue=https%3A%2F%2Fwww.google.ps%2F%3Fgws_rd%3Dssl&ec=GAZAmgQ&flowName=GlifWebSignIn&flowEntry=ServiceLogin");
//        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/span[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")).sendKeys(email);

        driver.get("https://reddit.com");
        String originalWindow = driver.getWindowHandle();
//        assert driver.getWindowHandles().size() == 1;
        //driver.findElement(By.xpath("//header/div[1]/div[2]/div[1]/div[1]/a[2]")).click(); // sign in button
        driver.findElement(By.xpath("//a[contains(text(),'Log In')]")).click();
//        WebDriverWait wait = new WebDriverWait(driver, 10);
//        wait.until(numberOfWindowsToBe(2));
//        //Loop through until we find a new window handle
//        for (String windowHandle : driver.getWindowHandles()) {
//            if(!originalWindow.contentEquals(windowHandle)) {
//                driver.switchTo().window(windowHandle);
//                break;
//            }
//        }
//        WebDriverWait wait = new WebDriverWait(driver, 10);
//        WebElement logBttn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[2]/form[1]/fieldset[1]/input[1]")));
//        logBttn.sendKeys("sdsdg");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //First finding the element using any of locator stratedgy
        WebElement iframeElement = driver.findElement(By.xpath("//body/div[@id='2x-container']/div[1]/div[2]/div[3]/div[2]/div[1]/iframe[1]"));
        //now using the switch command
        driver.switchTo().frame(iframeElement);
//        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]")).click();  // google
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[2]/form[1]/fieldset[1]/input[1]")).sendKeys("ashrafhaitham");
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[2]/form[1]/fieldset[2]/input[1]")).sendKeys("Haitham$123" + Keys.ENTER);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (String windowHandle : driver.getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

//        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/span[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")).sendKeys(email); // email field
//
//        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/span[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")).sendKeys(password); //
    }
}
