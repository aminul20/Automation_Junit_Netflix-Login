import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class checkNetflixLogin {
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void setup(){
        System.setProperty("webdriver.gecko.driver", "./src/test/resources/geckodriver.exe");
        FirefoxOptions ops=new FirefoxOptions();
//        ops.addArguments("--headless");//run test without opening browser
        ops.addArguments("--headed");
        driver=new FirefoxDriver(ops);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void loginWithValidEmailPassword() throws InterruptedException {
        driver.get("https://www.netflix.com/bd/login");
        driver.findElement(By.xpath("//input[@id='id_userLoginId']")).sendKeys("unicjlshumbfjxyotk@tbbyt.net");
        driver.findElement(By.xpath("//input[@id='id_password']")).sendKeys("flixnexT");
        driver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();
        String text= driver.findElement(By.xpath("//a[@class='authLinks redButton']")).getText();
        Thread.sleep(2000);
        Assert.assertTrue(text.contains("Sign Out"));
    }

    @Test
    public void loginWithBlankEmailPassword(){
        boolean flag = false;
        driver.get("https://www.netflix.com/bd/login");
//        driver.findElement(By.xpath("//input[@id='id_userLoginId']")).sendKeys("");
//        driver.findElement(By.xpath("//input[@id='id_password']")).sendKeys("");
        driver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();
        String error_email = driver.findElement(By.xpath("//div[contains(text(),'Please enter a valid email or phone number.')]")).getText();
        String error_password = driver.findElement(By.xpath("//div[contains(text(),'Your password must contain between 4 and 60 charac')]")).getText();
        if (error_email.equals("Please enter a valid email or phone number.") && error_password.equals("Your password must contain between 4 and 60 characters."))
            flag = true;
        Assert.assertTrue(flag);
    }

    @After
    public void finishTest(){
        driver.close();
    }
}

