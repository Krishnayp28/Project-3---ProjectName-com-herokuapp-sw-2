package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {
    String baseUrl = "http://the-internet.herokuapp.com/login";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }
    @Test
    public void UserShouldLoginSuccessfullyWithValidCredentials(){
        //Find the Username filed element
        WebElement usernameField = driver.findElement(By.name("username"));
        // Type the username to username field element
        usernameField.sendKeys("tomsmith");
        //Find the password field element and sent password on password field
       driver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");
        // Find the login button element and click
        WebElement loginButton = driver.findElement(By.xpath("//button[@class ='radius']"));
        loginButton.click();
        String expectedMessage ="Secure Area";// expected text from requirement
        String actualMessage = driver.findElement(By.xpath("//h2[contains(text(),' Secure Area')]")).getText();// Finding Secure Area text element and getting the text
        Assert.assertEquals("User was not logged in successfully",expectedMessage, actualMessage);// Validating actual and expected message
    }
    @Test
    public void verifyTheUsernameErrorMessage(){
        //Find the Username filed element
        WebElement usernameField = driver.findElement(By.name("username"));
        // Type the username to username field element
        usernameField.sendKeys("tomsmith1");
        //Find the password field element and sent password on password field
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");
        // Find the login button element and click
        WebElement loginButton = driver.findElement(By.xpath("//button[@class ='radius']"));
        loginButton.click();
        String expectedMessage ="Your username is invalid!";// expected text from requirement
        String  actualTextElement =driver.findElement(By.xpath("//div[@class= 'flash error']")).getText().substring(0,25);
        Assert.assertEquals("User was unable to verify name error message",actualTextElement,expectedMessage);
    }

    @Test
    public void verifyThePasswordErrorMessage() {

        driver.findElement(By.id("username")).sendKeys("tomsmith");// Find username field and enter valid username
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword");//Find password field and enter invalid password
        driver.findElement(By.xpath("//button[@class = 'radius']")).click();// Find Login button and click on it
        String expectedMessage = "Your password is invalid!";// Expected message given in requirement
        String actualMessage = driver.findElement(By.xpath("//div[@class = 'flash error']")).getText().substring(0, 25);// Finding Error text element and getting the text removing last two unwanted characters
        Assert.assertEquals("Password error message was not verified.",expectedMessage, actualMessage);// Validating actual and expected message
    }
    @After
    public void close(){
        closeBrowser();
    }

    }



