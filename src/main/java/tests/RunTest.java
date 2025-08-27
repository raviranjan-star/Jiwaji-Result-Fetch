package tests;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
public class RunTest {
	
	@SuppressWarnings("null")
	@Test
	public static void run() {
		ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");  // headless mode
        //options.addArguments("--window-size=1920,1080"); // optional
        //options.addArguments("--disable-gpu");   // optional (Windows)
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://univindia.com/JwgResult/view-marksheet.php");
		Properties properties;
		String start_roll = null;
		String end_roll = null;
		//String excel = null;
		String loop = null;
		
		try {
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            properties = new Properties();
            properties.load(fis);
            start_roll=properties.getProperty("startRoll");
            end_roll=properties.getProperty("endRoll");  
            loop=properties.getProperty("loop"); 
        	} 
		catch (IOException e) {
            e.printStackTrace();
        	}
		if(loop.equalsIgnoreCase("yes")) {
			//System.out.println(start_roll+"		"+end_roll+"		"+driver);
			FetchResult.fetch(start_roll,end_roll,driver);
		}
		driver.close();
		
	}
	
	
}