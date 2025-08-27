package tests;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utils.ExcelUtils;
public class ReadExcelData {
	@DataProvider(name = "singleValues")
	public Object[][] getValues() throws Exception {
	    ExcelUtils excel = new ExcelUtils("testData/Roll_Number.xlsx", "Sheet1");
	    String[][] range = excel.getRangeData(1, 590, 1, 11); // B2 → L5

	    Object[][] data = new Object[range.length * range[0].length][1];
	    int index = 0;

	    for (String[] row : range) {
	        for (String val : row) {
	            data[index++][0] = val; // each row in DataProvider has only 1 value
	        }
	    }
	    return data;
	}
	
	@Test(dataProvider = "singleValues")
	public void testRollNumber(String rollNo) {
		ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");  // headless mode
        //options.addArguments("--window-size=1920,1080"); // optional
        //options.addArguments("--disable-gpu");   // optional (Windows)
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://univindia.com/JwgResult/view-marksheet.php");
		//FetchResult.fetch(rollNo,driver);
		driver.close();
	}
}
