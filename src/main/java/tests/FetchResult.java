package tests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class FetchResult {
	static WebDriver driver;
	public static void fetch(String startRoll, String endRoll, WebDriver getDriver) {
		driver=getDriver;
		int s_roll=Integer.parseInt(startRoll);
		int e_roll=Integer.parseInt(endRoll);
		for(int i=s_roll;i<=e_roll;i++) {
		getResult(Integer.toString(i));
		}
	}
	public static void getResult(String roll) {
		Select sel=new Select(driver.findElement(By.xpath("//Select[@id='years']")));
		sel.selectByVisibleText("2025");
		driver.findElement(By.xpath("//button[text()=' Proceed for Result']")).click();
		driver.findElement(By.xpath("//tr/td[text()='BED2']/parent::tr/td/form")).click();
		
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys(roll);
		driver.findElement(By.xpath("//button[text()=' View Marksheet']")).click();
		try {
			String getRoll=driver.findElement(By.xpath("//td[text()='Roll Number']/following-sibling::td")).getText();
			String name=driver.findElement(By.xpath("//td[text()='Name']/following-sibling::td")).getText();
			String number = driver.findElement(By.xpath("//th[text()='FINAL RESULT']/parent::tr/following-sibling::tr/td/following-sibling::td")).getText();
			String final_res = driver.findElement(By.xpath("//td[text()='Result']/following-sibling::td")).getText();
			WriteResultExcel.writeResult(getRoll,name,number,final_res);
			//System.out.println(getRoll+" "+name+" "+number+" "+final_res);
			driver.findElement(By.xpath("//a[text()='Back']")).click();
		}
		catch(Exception e) {
			driver.findElement(By.xpath("//a[text()='Back']")).click();
			//System.out.println(roll+" Roll Number Not Found");
		}
	}

}

