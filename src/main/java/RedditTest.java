import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.io.OutputStream;
import java.lang.*;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public class RedditTest {

    public static void main(String [] arr){
        CharSequence email = "ashrafhaitham391@gmail.com"; // "Aggravating-Bat3581"
        CharSequence userName = "ashrafhaitham";
        CharSequence password = "Haitham$123";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-web-security", "--allow-running-insecure-content", "--disable-notifications");
        WebDriverManager.chromedriver().setup();
        WebDriver signUpDriver = new ChromeDriver(options);
        signUpDriver.manage().window().maximize();

        signUpDriver.get("https://reddit.com");
        //sign up
        signUp(signUpDriver, email);
        signUpDriver.close();

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        driver.get("https://reddit.com");
        // verify title
        String actualTitle = driver.getTitle();
        String expectedTitle = "reddit: the front page of the internet";
        if(actualTitle.trim().equals(expectedTitle.trim()))
            System.out.println("Title verified");

        // login
        sleep(20000);
        logIn(driver,userName,password);

        // task one
        CharSequence titleOfPost = new String("Hello Post ");
        CharSequence post = new String("Hello every one we are computer engineering students...");
        verifyCreatingPost(driver, userName, titleOfPost, post);
        //task two
        Comment(driver,userName,"nice post");
        //task three
        verifyGoingToNotification(driver);

        //
//       JoinCommunity(driver,"Memes");
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

    private static String signUp(WebDriver driver, CharSequence email){

        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/header[1]/div[1]/div[2]/div[1]/div[1]/a[2]")).click();

        sleep(5000);
        WebElement iframeSignUpElement = driver.findElement(By.xpath("//body/div[@id='2x-container']/div[1]/div[2]/div[3]/div[2]/div[1]/iframe[1]"));
        driver.switchTo().frame(iframeSignUpElement);

        WebDriverWait wait = new WebDriverWait(driver,20);
        WebElement emailTextField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[2]/form[1]/fieldset[1]/input[2]")));
        emailTextField.sendKeys(email+""+Keys.ENTER);

        //click on suggested usernames
        WebElement userNameTextField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/a[1]")));
        userNameTextField.click();

        String chosenUserName = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/a[1]")).getText();
        sleep(5000);
        System.out.println(chosenUserName+"hi");
        // set password as userName ;
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[2]/div[1]/div[1]/div[2]/div[1]/form[1]/fieldset[2]/input[2]")).sendKeys(chosenUserName);
        // click on any div
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[2]/div[1]/div[1]/div[2]/div[1]")).click();

        sleep(5000);
        // click on I'm not robot button
//        driver.findElement(By.xpath("")).click();
        WebElement notRobotBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[2]/div[1]/div[1]/div[2]/div[1]/form[1]/fieldset[1]/input[2]")));
        notRobotBtn.click();
        // click on sign up button one more time
        sleep(100000);
//        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[2]/div[1]/div[1]/div[3]/button[1]")).click();
//        sleep(3000);
//        WebElement finishBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[6]/div[1]/div[1]/div[3]/button[1]")));
//        finishBtn.click();
        System.out.println("Signed up be email: "+ email +" and username: "+ chosenUserName);
        return chosenUserName;
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
    private static  void verifyCreatingPost(WebDriver driver,CharSequence username, CharSequence titleOfPost, CharSequence post){
        // click on create post
        WebDriverWait wait = new WebDriverWait(driver,20);
        // click on create post
        WebElement createPostElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[3]/div[1]/div[1]/input[1]")));
        createPostElement.click();
        sleep(5000);
        // fill username
        WebElement usernameElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[3]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/input[1]")));
        usernameElement.sendKeys("u/"+username);
        // create a post and
        //set post title
        WebElement titleElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[3]/div[1]/div[2]/div[3]/div[2]/div[1]/div[1]/textarea[1]")));
        titleElement.sendKeys(titleOfPost);
        // set post words
        sleep(2000);
        WebElement postElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[3]/div[1]/div[2]/div[3]/div[2]/div[2]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]")));
        postElement.sendKeys(post);
        // click on post button
        WebElement postElementBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[3]/div[1]/div[2]/div[3]/div[3]/div[2]/div[1]/div[1]/button[1]")));
        postElementBtn.click();

        sleep(8000);
        // go to profile and check if post has posted

        WebElement menuBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/header[1]/div[1]/div[2]/div[2]/div[1]/div[2]/button[1]/span[1]/span[1]")));
        menuBtn.click();
        sleep(5000);

        WebElement profileBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[5]/div[1]/a[1]")));
        profileBtn.click();
        //get title
        WebElement titleElement2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[4]/div[1]/div[5]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[2]/div[1]/a[1]/div[1]/h3[1]")));
        String actualTitleOfPost = titleElement2.getText();
        // get post
        WebElement postElement2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[4]/div[1]/div[5]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[3]/div[1]/div[1]/p[1]")));
        String actualPost = postElement2.getText();
//        System.out.println("title" + actualTitleOfPost);
//        System.out.println("post" + actualPost);
        if(actualTitleOfPost.equals(titleOfPost.toString().trim()) && actualPost.equals(post.toString().trim())){
            System.out.println("Create a post verified");
        }
        sleep(5000);
    }

    private static void verifyGoingToNotification(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver,20);

        sleep(3000);
        WebElement redditBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//header/div[1]/div[1]/a[1]/*[1]")));
        redditBtn.click();
        sleep(3000);

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


private static void  JoinCommunity(WebDriver driver , CharSequence communityname) {

    driver.findElement(By.cssSelector("#header-search-bar")).sendKeys(communityname +""+ Keys.ENTER);
    WebDriverWait wait = new WebDriverWait(driver,10);
    WebElement profileBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Join')]")));
    profileBtn.click();
    try {
        Thread.sleep(5000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
      //driver.findElement(By.xpath("//body/div[@id='2x-container']/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[3]/div[1]/div[4]/div[1]/div[2]/div[1]/div[1]/a[1]/div[3]/button[1]")).click();
    if(driver.findElement(By.xpath("//button[contains(text(),'Join')]")).getCssValue("background-color")== "rgba(0, 121, 211, 1)")
    {
        System.out.println("Joined successfully");
    }


     try {
        Thread.sleep(5000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
     driver.findElement(By.xpath("//header/div[1]/div[1]/a[1]/*[1]")).click();


}

public static void Comment(WebDriver driver ,CharSequence username ,CharSequence comment){

    driver.findElement(By.xpath("//header/div[1]/div[1]/a[1]/*[1]")).click();
    driver.findElement(By.cssSelector("#header-search-bar")).sendKeys(username +""+ Keys.ENTER);
    WebDriverWait wait = new WebDriverWait(driver,10);
    driver.navigate().refresh();
    sleep(5000);
    //click on do a comment

//    WebElement sortedByElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[3]/div[1]/div[2]/div[1]/div[1]/div[2]")));
//    sortedByElement.click();
//
//    WebElement sortedByNewElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[12]/div[1]/a[4]/button[1]")));
//    sortedByNewElement.click();

    WebElement cBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[3]/div[1]/div[4]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[3]/div[3]/a[1]")));
    cBtn.click();
    // comment field
    WebElement cmnt = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[3]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]")));
    cmnt.sendKeys(comment);
    WebElement prss = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Comment')]")));
    prss.click();
    sleep(4000);
//    wait.until(ExpectedConditions.alertIsPresent());
//    Alert alert = driver.switchTo().alert();
//    //Press the Cancel button
//    alert.dismiss();
    driver.navigate().refresh();

    driver.findElement(By.xpath("//header/div[1]/div[1]/a[1]/*[1]")).click();

    sleep(5000);
    //go back and check if comment has been commented
    driver.findElement(By.cssSelector("#header-search-bar")).sendKeys(username +""+ Keys.ENTER);
    sleep(5000);
    driver.navigate().refresh();
    sleep(5000);
    WebElement cBtn2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[3]/div[1]/div[4]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[3]/div[3]/a[1]")));
    cBtn2.click();

//    WebElement sortedByBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[3]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[3]/button[1]/span[1]")));
//    sortedByBtn.click();
    sleep(3000);
//    WebElement newBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[3]/div[1]/div[1]/div[1]/div[6]/div[1]/a[3]/button[1]")));
//    newBtn.click();

    WebElement lastPost = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[3]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[4]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[2]/div[1]/p[1]")));
    String actualPostText = lastPost.getText();

    if(actualPostText.equals(comment.toString().trim()))
        System.out.println("Do a comment verified");

}














}














