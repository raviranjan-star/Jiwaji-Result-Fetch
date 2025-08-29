package tests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FetchResult {
	static WebDriver driver;
	static int temp = 0;
	static String collegeCap = null;
	static int college_temp = 0;
	static int temp_roll = 0;
		
	public static void fetch(String Roll, WebDriver getDriver) throws InterruptedException {
		driver=getDriver;
		int roll = Integer.parseInt(Roll);
		temp_roll = roll;
		while(temp<5) {
			getResult(Integer.toString(temp_roll++));	
		}
		temp_roll = roll-1;
		temp=0;
		while(temp<5) {
			getResult(Integer.toString(temp_roll--));	
		}
	}
	public static void getResult(String roll) throws InterruptedException {
		driver.findElement(By.xpath("//input[@type='text']")).clear();
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys(roll);
		driver.findElement(By.xpath("//button[text()=' View Marksheet']")).click();
		try {
			String getRoll=driver.findElement(By.xpath("//td[text()='Roll Number']/following-sibling::td")).getText();
			String name=driver.findElement(By.xpath("//td[text()='Name']/following-sibling::td")).getText();
			String number = driver.findElement(By.xpath("//th[text()='FINAL RESULT']/parent::tr/following-sibling::tr/td/following-sibling::td")).getText();
			String final_res = driver.findElement(By.xpath("//td[text()='Result']/following-sibling::td")).getText();
			String college = driver.findElement(By.xpath("//h5")).getText();
			
			if(collegeCap == null ) {
				collegeCap=college;
				
			}
			
			if(collegeCap.equalsIgnoreCase(college)) {
				
				WriteResultExcel.writeResult(getRoll,name,number,final_res, college);
				System.out.println(getRoll+" "+name+" "+number+" "+final_res+" "+college);
				driver.navigate().back();
				temp=0;
			}else {
				temp=10;
			}
			
		}
		catch(Exception e) {
			driver.navigate().back();
			temp++;
		}
	}

}

