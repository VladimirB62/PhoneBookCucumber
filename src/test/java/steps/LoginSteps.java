package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class LoginSteps {

    WebDriver driver;

    @Given("Navigate to Page Phone Book")
    public void navigateToLoginPage(){
        driver =new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://contacts-app.tobbymarshall815.vercel.app");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @When("Click on Login Tab")
    public void clickOnLoginTab() {
        click(By.xpath("//*[.='LOGIN']"));
    }



    @Then("Appear LoginRegistration form")
    public void isLoginRegFormPresent() {
        Assert.assertTrue(isElementPresent(By.cssSelector(".login_login__3EHKB")));
    }

    @And("Enter the Valid Data")
    public void enterValidData() {
        type(By.cssSelector("[placeholder='Email']"),"gushiddink@gmail.com");
        type(By.cssSelector("[placeholder='Password']"),"12345678Aa$");
    }

    @And("Click on Login button")
    public void clickOnLoginButton() {
        click(By.xpath("//button[.=' Login']"));
    }

    @Then("Sign out button displayed")
    public void isSignOutButtonPresent() {
        Assert.assertTrue(isElementPresent(By.xpath("//button[.='Sign Out']")));
    }


    @And("Click on Add Tab")
    public void clickOnAddButton(){
        click(By.xpath("//a[contains(.,'ADD')]"));
    }

    @Then("Enter new contact data")
    public void enterNewContactData(DataTable table) {
        List<Map<String,String>> dataTable = table.asMaps();
        String Name = dataTable.get(0).get("Name");
        String LastName = dataTable.get(0).get("LastName");
        String Phone = dataTable.get(0).get("Phone");
        String email = dataTable.get(0).get("email");
        String Address = dataTable.get(0).get("Address");
        String description = dataTable.get(0).get("description");

        type(By.cssSelector("[placeholder='Name']"),Name);
        type(By.cssSelector("[placeholder='Last Name']"),LastName);
        type(By.cssSelector("[placeholder='Phone']"),Phone);
        type(By.cssSelector("[placeholder='email']"),email);
        type(By.cssSelector("[placeholder='Address']"),Address);
        type(By.cssSelector("[placeholder='description']"),description);
    }

    @And("Click on Save button")
    public void clickOnSaveButton() {
       click(By.xpath("//b[contains(text(),'Save')]"));
    }

    @Then("Assert contact appears")
    public void newContactAppears() {
        Assert.assertTrue(sizeofContacts()>0);
    }


    @And("Enter invalid  Data")
    public void enterInwalidPassword(DataTable table) {
        List<Map<String,String>> dataTable = table.asMaps();
        String email = dataTable.get(0).get("email");
        String password = dataTable.get(0).get("password");

        type(By.cssSelector("[placeholder='Email']"),email);
        type(By.cssSelector("[placeholder='Password']"),password);

    }

    @Then("Allert appeared")
    public void isAllertAppeared() {
        pause(1000);
        Assert.assertTrue(isAlertPresent());
    }

    private boolean isAlertPresent() {
        Alert alert = new WebDriverWait(driver,10).until(ExpectedConditions.alertIsPresent());
        if (alert==null) {
            return false;
        }else{
            driver.switchTo().alert();
            alert.accept();
            return true;
        }
    }

    public void pause(int millis) {
        try{
            Thread.sleep(millis);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }


    private void type(By locator, String text) {
        if (text!= null){
            click(locator);
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(text);
        }
    }

    private void click(By locator) {
        driver.findElement(locator).click();
    }

    private boolean isElementPresent(By locator) {
        return driver.findElements(locator).size()>0;
    }

    public void clickWithAction(By locator) {
        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(locator);
        actions.moveToElement(element).perform();
        element.click();
    }

    public int sizeofContacts() {
        return Math.max(driver.findElements(By.cssSelector("div.contact-item_card__2SOIM")).size(), 0);
    }


}
