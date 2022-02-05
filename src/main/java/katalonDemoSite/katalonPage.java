package katalonDemoSite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

public class katalonPage {

    private WebDriver driver;

    @BeforeSuite
    public void initializeSuit(){
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://katalon-demo-cura.herokuapp.com/");
        driver.manage().window().maximize();
    }

    @AfterSuite
    public void dropDownSuit(){
        //Matamos el driver y cerramos el browser
        driver.close();
        driver.quit();
    }

    @BeforeMethod
    public void initializeMethod(){
        driver.manage().deleteAllCookies();
    }

    @AfterMethod
    public void dropDownMethod(){
        System.out.println("Esta linea se imprime desde el @afterMethod");

    }

    @Test
    public void simpleTest() {

        //Hacemos click en "Make Appointment button".
        WebElement makeAppointmentButton = driver.findElement(By.id("btn-make-appointment"));
        makeAppointmentButton.click();

        //Login con credenciales válidas.
        WebElement inputUser = driver.findElement(By.id("txt-username"));
        inputUser.sendKeys("John Doe");

        WebElement inputPassword = driver.findElement(By.id("txt-password"));
        inputPassword.sendKeys("ThisIsNotAPassword");

        WebElement loginButton = driver.findElement(By.id("btn-login"));
        loginButton.click();

        //Make appointment
        Select picklist = new Select(driver.findElement(By.id("combo_facility")));
        picklist.selectByValue("Hongkong CURA Healthcare Center");

        WebElement applyForHospitalReadmission = driver.findElement(By.id("chk_hospotal_readmission"));
        applyForHospitalReadmission.click();

        WebElement healthcareProgram = driver.findElement(By.id("radio_program_medicaid"));
        healthcareProgram.click();

        WebElement chooseDateInCalendar = driver.findElement(By.id("txt_visit_date"));
        chooseDateInCalendar.click();

        WebElement pickDate = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/table[1]/tbody[1]/tr[4]/td[4]"));
        pickDate.click();

        WebElement commentBox = driver.findElement(By.id("txt_comment"));
        commentBox.sendKeys("Aqui va un comentario del largo que desees.");

        WebElement bookAppointmentButton = driver.findElement(By.id("btn-book-appointment"));
        bookAppointmentButton.click();

        WebElement showsConfirmation = driver.findElement(By.xpath("//*[@id=\"summary\"]/div/div/div[1]/h2"));

        if (showsConfirmation.getText().equals("Appointment Confirmation")){
            WebElement goToHomePageButton = driver.findElement(By.className("btn-default"));
            goToHomePageButton.click();
            System.out.println("El registro se completó debidamente");
        }else
            System.out.println("Algo en tu registro no salio bien.");


    }
}
