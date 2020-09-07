package allocator;

import static allocator.AppConnector.driver;
import static allocator.AppConnector.prop;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import io.cucumber.core.api.Scenario;
import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReusableMethods {
	String RunTimeValue = null;

	public void saveScreenshotsForScenario(final Scenario scenario) {
		final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		scenario.embed(screenshot, "image/png");
	}

	public void waitForPageLoad(int timeout) {
		ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";");
	}

	public boolean isWorkingDay(String meetingDate) {
		boolean isWorkingDate = false;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
		LocalDate date = LocalDate.parse(meetingDate, formatter);
		DayOfWeek day = DayOfWeek.of(date.get(ChronoField.DAY_OF_WEEK));
		switch (day) {
		case SATURDAY:
			System.out.println("Weekend - Saturday");
			isWorkingDate = false;
			break;
		case SUNDAY:
			System.out.println("Weekend - Sunday");
			isWorkingDate = false;
			break;
		default:
			System.out.println("It is a working day");
			isWorkingDate = true;
		}
		return isWorkingDate;
	}

	public String getSpecificColumnData(String FilePath, String SheetName, String ColumnName)
			throws InvalidFormatException, IOException {
		FileInputStream fis = new FileInputStream(FilePath);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet(SheetName);
		XSSFRow row = sheet.getRow(0);
		int col_num = -1;
		for (int i = 0; i < row.getLastCellNum(); i++) {
			if (row.getCell(i).getStringCellValue().trim().equals(ColumnName))
				col_num = i;
		}
		row = sheet.getRow(1);
		XSSFCell cell = row.getCell(col_num);
		String value = cell.getStringCellValue();
		fis.close();
		return value;
	}

	public By getElementWithLocator(String AppElement) throws Exception {
		String locatorTypeAndValue = prop.getProperty(AppElement);
		String[] locatorTypeAndValueArray = locatorTypeAndValue.split(",", 2);
		String locatorType = locatorTypeAndValueArray[0].trim();
		String locatorValue = locatorTypeAndValueArray[1].trim();
		if (locatorValue.contains("ObjectToken")) {
			locatorValue = locatorValue.replaceAll("ObjectToken", RunTimeValue);
		}
		switch (locatorType.toUpperCase()) {
		case "ID":
			return By.id(locatorValue);
		case "NAME":
			return By.name(locatorValue);
		case "XPATH":
			return By.xpath(locatorValue);
		case "CLASS":
			return By.className(locatorValue);
		default:
			return null;
		}
	}

	public MobileElement FindAnElement(String AppElement) throws Exception {
		return (MobileElement) driver.findElement(getElementWithLocator(AppElement));
	}

	public String getTextFromElement(String AppElement) throws Exception {
		String elementText;
		if (FindAnElement(AppElement).getText() != null) {
			elementText = FindAnElement(AppElement).getText();
		} else {
			elementText = FindAnElement(AppElement).getAttribute("text").trim();
		}
		return elementText;
	}

	public void PerformActionOnElement(String AppElement, String Action, String Text) throws Exception {
		RunTimeValue = Text;
		switch (Action) {
		case "Click":
			FindAnElement(AppElement).click();
			break;
		case "Type":
			FindAnElement(AppElement).setValue(Text);
			Thread.sleep(5000);
			break;
		case "Clear":
			FindAnElement(AppElement).clear();
			break;
		case "WaitForElementDisplay":
			waitForCondition("Presence", AppElement, 60);
			break;
		case "WaitForElementClickable":
			waitForCondition("Clickable", AppElement, 60);
			break;
		case "ElementNotDisplayed":
			waitForCondition("NotPresent", AppElement, 60);
			break;
		case "ElementDisplayed":
			waitForCondition("Presence", AppElement, 60);
			break;
		default:
			throw new IllegalArgumentException("Action \"" + Action + "\" isn't supported.");
		}
	}

	public boolean waitForCondition(String TypeOfWait, String AppElement, int Time) {
		boolean conditionMeet = false;
		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Time, TimeUnit.SECONDS)
					.pollingEvery(5, TimeUnit.SECONDS).ignoring(Exception.class);
			switch (TypeOfWait) {
			case "Clickable":
				wait.until(ExpectedConditions.elementToBeClickable(FindAnElement(AppElement)));
				conditionMeet = true;
				break;
			case "Presence":
				wait.until(ExpectedConditions.presenceOfElementLocated(getElementWithLocator(AppElement)));
				conditionMeet = true;
				break;
			case "Visibility":
				wait.until(ExpectedConditions.visibilityOfElementLocated(getElementWithLocator(AppElement)));
				conditionMeet = true;
				break;
			case "NotPresent":
				wait.until(ExpectedConditions.invisibilityOfElementLocated(getElementWithLocator(AppElement)));
				conditionMeet = true;
				break;
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(AppElement + " could not be located");
		}
		return conditionMeet;
	}
}
