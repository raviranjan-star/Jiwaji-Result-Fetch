package tests;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
public class RunTest {
	
	@Test
	public static void run() throws InterruptedException {
		Properties properties;
		ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");  // headless mode
        options.addArguments("--window-size=1920,1080"); // optional
        options.addArguments("--disable-gpu");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		String rangeRoll = null;
		
		try {
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            properties = new Properties();
            properties.load(fis);
            rangeRoll = properties.getProperty("RollN0") ;
            String url_website = properties.getProperty("Website");
            driver.get(url_website);
    		Select sel=new Select(driver.findElement(By.xpath("//Select[@id='years']")));
    		String exam_year = properties.getProperty("year");
    		sel.selectByVisibleText(exam_year);
    		driver.findElement(By.xpath("//button[text()=' Proceed for Result']")).click();
    		String course_string = "//tr/td[text()='"+properties.getProperty("course")+"']/parent::tr/td/form";
    		WebElement element= driver.findElement(By.xpath(course_string));
    		JavascriptExecutor js = (JavascriptExecutor) driver;
    		js.executeScript("arguments[0].scrollIntoView(true);", element);
    		Thread.sleep(1000);
    		element.click();
            }catch (IOException e) {
            	e.printStackTrace();
        		}
			FetchResult.fetch(rangeRoll,driver);
			driver.close();
	}
	
}