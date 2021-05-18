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
        JoinCommunity(driver,"Memes");
        Comment(driver,"ashrafhaithamm","nice post");
        verifyCreatingPost(driver, titleOfPost, post);

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
    WebElement cBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div._1VP69d9lk-Wk9zokOaylL.isNotInIcons2020 div.hciOr5UGrnYrZxB11tX9s:nth-child(2) div._1nxEQl5D2Bx2jxDILRHemb div.qYj03fU5CXf5t2Fc5iSvg.ListingLayout-outerContainer div._3ozFtOe6WpJEMUtxDOIvtU div._1vyLCp-v-tE5QvZovwrASa:nth-child(3) div._1OVBBWLtHoSPfGCRaPzpTf._3nSp9cdBpqL13CqjdMr2L_.PaJBYLqPf_Gie2aZntVQ7 div.rpBJOHq2PR60pnwJlUyP0:nth-child(4) div._1oQyIsiPHYt6nx7VOmd1sz.bE7JgM2ex7W3aF3zci5bm.D3IyhBGwXo9jPwz-Ka0Ve._23tSz_ar8phNRBBW1afkYr.scrollerItem.Post.t3_nfm9no div._1poyrkZ7g36PawDueRza-J div._2XDITKxlj4y3M99thqyCsO div._1Y6dfr4zLlrygH-FLmr8x- div._36kpXQ-z7Hr61j8878uRkP div._3-miAEojrCvx_4FQ8x3P-s.ssgs3QQidkqeycI33hlBa > a._1UoeAeSRhOKSNdY_h3iS1O._1Hw7tY9pMr-T1F4P1C-xNU._3U_7i38RDPV5eBv7m4M-9J._2qww3J5KKzsD7e5DO0BvvU:nth-child(1)")));
    cBtn.click();
    WebElement cmnt = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div._1VP69d9lk-Wk9zokOaylL.isNotInIcons2020 div.hciOr5UGrnYrZxB11tX9s:nth-child(2) div.subredditvars-r-ashrafhaithamm:nth-child(6) div._2DJXORCrmcNpPTSq0LqL6i._26pbaCw90eAC80WRqUfICJ div._2mIbM_6nl-2OnOGEbEMRXa div._29IbETWb5VVDcfk_-GumWz div._1npCwF50X2J7Wt82SZi6J0:nth-child(2) div.u35lf2ynn4jHsVUwPmNU.Dx3UxiK86VcfkFQVHNXNi div.uI_hDmU5GSiudtABRz_37:nth-child(2) div._1r4smTyOEZFO91uFIdWW6T.aUM8DQ_Nz5wL0EJc_wte6 div._3YZ-jFfccqhepgq1dDuLEv div._3MknXZVbkWU8JL9XGlzASi._3wl1bRnSzxHkKJfvqakrNI._1UhKfcyzvaWRtDdXZmzg6D div._2baJGEALPiEMZpWB2iWQs7:nth-child(2) div._13Sj3UMDKkCCJTq88berCB:nth-child(2) div:nth-child(1) div.DraftEditor-root div.DraftEditor-editorContainer > div.notranslate.public-DraftEditor-content")));
    cmnt.sendKeys(comment);
    WebElement prss = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Comment')]")));
    prss.click();

    driver.findElement(By.xpath("//header/div[1]/div[1]/a[1]/*[1]")).click();


}














}














