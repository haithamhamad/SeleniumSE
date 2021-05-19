import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.lang.*;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public class RedditTest {

    public static void main(String [] arr){
        CharSequence email = new String("haithamashraf35@gmail.com");
        CharSequence userName = new String("ashrafhaitham");
        CharSequence password = new String("Haitham$123");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-web-security", "--allow-running-insecure-content", "--disable-notifications");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        driver.get("https://reddit.com");
        logIn(driver,userName,password);

        CharSequence titleOfPost = new String("Hello Post ");
        CharSequence post = new String("Hello every one we are computer engineering students...");
        verifyCreatingPost(driver, titleOfPost, post);
        verifyGoingToNotification(driver);
        //get background color
//        WebDriverWait wait = new WebDriverWait(driver,20);
//        String backGroundColor = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[3]")).getCssValue("color");
//        System.out.println("backGroundColor "+ backGroundColor);
//        WebElement dropDownBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/header[1]/div[1]/div[2]/div[2]/div[1]/div[2]/button[1]/span[1]")));
//        dropDownBtn.click();
//        sleep(2000);
//        List<WebElement> nightModeBtn = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("_2KotRmn9DgdA58Ikji2mnV _1zZ3VDhRC38fXLLvVCHOwK")));
//        nightModeBtn.get(0).click();
//        sleep(7000);
//        backGroundColor = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[3]")).getCssValue("color");
//        System.out.println("backGroundColor after "+ backGroundColor);
//
//        if(backGroundColor.equals("rgba(255, 255, 255, 1)")){
//            System.out.println("Verify Night mode functionality");
//        }


    }


    private static void logIn(WebDriver driver, CharSequence userName, CharSequence password){
        String originalWindow = driver.getWindowHandle();
        driver.findElement(By.xpath("//a[contains(text(),'Log In')]")).click();
        sleep(5000);
        WebElement iframeElement = driver.findElement(By.xpath("//body/div[@id='2x-container']/div[1]/div[2]/div[3]/div[2]/div[1]/iframe[1]"));
        driver.switchTo().frame(iframeElement);
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[2]/form[1]/fieldset[1]/input[1]")).sendKeys(userName);
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[2]/form[1]/fieldset[2]/input[1]")).sendKeys(password +""+ Keys.ENTER);
        sleep(5000);
        String actualUserName =  driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/header[1]/div[1]/div[2]/div[2]/div[1]/div[2]/button[1]/span[1]/span[1]/span[1]/span[1]")).getText();

        if(actualUserName.equals(userName.toString()))
            System.out.println("Log in verified");
    }
    private static  void verifyCreatingPost(WebDriver driver, CharSequence titleOfPost, CharSequence post){
        // click on create post
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[3]/div[1]/div[1]/input[1]")).click();
        sleep(5000);
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[3]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/input[1]")).sendKeys("u/ashrafhaitham");
        // create a post and
        //set post title
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[3]/div[1]/div[2]/div[3]/div[2]/div[1]/div[1]/textarea[1]")).sendKeys(titleOfPost);
        // set post words
        sleep(2000);
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[3]/div[1]/div[2]/div[3]/div[2]/div[2]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]")).sendKeys(post);
        // click on post button
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[3]/div[1]/div[2]/div[3]/div[3]/div[2]/div[1]/div[1]/button[1]")).click();

        sleep(2000);
        // go to profile and check if post has posted

        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/header[1]/div[1]/div[2]/div[2]/div[1]/div[2]/button[1]/span[1]")).click();
        sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement profileBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[5]/div[1]/a[1]")));
        profileBtn.click();
        //get title
        WebElement tilteElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[4]/div[1]/div[5]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[2]/div[1]/a[1]/div[1]/h3[1]")));
        String actualTitleOfPost = tilteElement.getText();
        // get post
        WebElement postElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[4]/div[1]/div[5]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[3]/div[1]/div[1]/p[1]")));
        String actualPost = postElement.getText();
//        System.out.println("title" + actualTitleOfPost);
//        System.out.println("post" + actualPost);
        if(actualTitleOfPost.equals(titleOfPost.toString().trim()) && actualPost.equals(post.toString().trim())){
            System.out.println("Create a post verified");
        }
    }

    private static void verifyGoingToNotification(WebDriver driver){
        sleep(3000);
        WebDriverWait wait = new WebDriverWait(driver,20);
        WebElement menuBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/header[1]/div[1]/div[1]/div[2]/button[1]")));
        menuBtn.click();
        sleep(3000);
        WebElement notificationBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/header[1]/div[1]/div[1]/div[2]/div[1]/a[5]")));
        notificationBtn.click();
        WebElement notificationHeadText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/h1[1]")));
        String actualNotifyText = notificationHeadText.getText();
        if(actualNotifyText.equals("Notifications"))
            System.out.println("Go to notification verified");
    }
    private static void sleep(long duration){
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

//        for (String windowHandle : driver.getWindowHandles()) {
//            if(!originalWindow.contentEquals(windowHandle)) {
//                driver.switchTo().window(windowHandle);
//                break;
//            }
//        }

///////

//        assert driver.getWindowHandles().size() == 1;
//driver.findElement(By.xpath("//header/div[1]/div[2]/div[1]/div[1]/a[2]")).click(); // sign in button

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

//First finding the element using any of locator stratedgy
//now using the switch command

//        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]")).click();  // google

//        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/span[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")).sendKeys(email); // email field
//
//        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/span[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")).sendKeys(password); //



//        driver.get("https://accounts.google.com/ServiceLogin/identifier?hl=ar&passive=true&continue=https%3A%2F%2Fwww.google.ps%2F%3Fgws_rd%3Dssl&ec=GAZAmgQ&flowName=GlifWebSignIn&flowEntry=ServiceLogin");
//        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/span[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")).sendKeys(email);
