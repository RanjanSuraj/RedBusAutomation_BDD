package page;

import base.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class RedBusHomePage {

    WebDriver driver;
    WebDriverWait wait;

    public RedBusHomePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10000));
    }

    By fromInput = By.id("srcinput");
    By fromSuggestions = By.xpath("//div[contains(@class,'searchCategory')]//div[@role='heading']");

    By toInput = By.id("destinput");
    By toSuggestions = By.xpath("//div[contains(@class,'searchCategory')]//div[@role='heading']");


    By dateInput = By.xpath("//*[@id=\"root\"]/main/div/div/div[2]/search/div/div/div[1]/div[1]/div[3]");
    By date = By.xpath("//span[normalize-space()='27']");

    By searchButton = By.xpath("//*[@id=\"root\"]/main/div/div/div[2]/search/div/div/button");
    By firstBusViewSeats = By.xpath("//*[@id=\"40774582\"]/button");
    By firstAvailableSeat = By.xpath("//*[@id=\"L5\"]");

    By booking = By.xpath("//*[@id=\"leaner-funnel-popup\"]/div[3]/div/div[2]/div/div/div/div[1]/div/div/div[2]/button");

    public void openRedBus() {
        driver.get("https://www.redbus.in/");
    }

    public void enterSource(String from) {
        System.out.println(from);
        wait.until(ExpectedConditions.visibilityOfElementLocated(fromInput));
        WebElement source = driver.findElement(fromInput);
        source.sendKeys(from);
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        List<WebElement> sourceSuggestions = driver.findElements(fromSuggestions);
        wait.until(ExpectedConditions.visibilityOfAllElements(sourceSuggestions));
        sourceSuggestions.getFirst().click();
    }

    public void enterDestination(String to) {
        System.out.println(to);
        wait.until(ExpectedConditions.visibilityOfElementLocated(toInput));
        WebElement destination = driver.findElement(toInput);
        destination.sendKeys(to);
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        List<WebElement> destinationSuggestions = driver.findElements(toSuggestions);
        wait.until(ExpectedConditions.visibilityOfAllElements(destinationSuggestions));
        destinationSuggestions.getFirst().click();
    }

    public void selectDate() {
        driver.findElement(dateInput).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(date));
        driver.findElement(date).click();
    }

    public void clickSearch() {
        driver.findElement(searchButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstBusViewSeats));

    }

    public void chooseBus() {
        driver.findElement(firstBusViewSeats).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[6]/div/div[1]/div[2]/button")));
        driver.findElement(By.xpath("/html/body/div[6]/div/div[1]/div[2]/button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstAvailableSeat));
    }

      //  ONLY FOR SELECTING THE SEAT
//    public void selectSeat() {
//        driver.findElement(firstAvailableSeat).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(booking));
//        System.out.println(driver.findElement(booking).getText());
//    }
//}


    // for finding the Available Seat
//    public void selectSeat() {
//        List<WebElement> totalSeats = driver.findElements(By.xpath("//div[@role='group' and contains(@aria-label,\"deck seats\")]//span[contains(@id,'L') or contains(@id,'U')]"));
//        System.out.println("Total number of available seats are:- " + totalSeats.size());
//
//        System.out.println("Available Seats numbers are:-");
//        for (WebElement element : totalSeats) {
//            if (element.getAttribute("aria-label").contains("unreserved")) {
//
//                System.out.print(element.getAttribute("id") + ", ");
//            }
//
//        }


        // fOR finding both available and sold seat
    public void selectSeat() {

        List<WebElement> totalSeats = driver.findElements(
                By.xpath("//div[@role='group' and contains(@aria-label,'deck seats')]//span[contains(@id,'L') or contains(@id,'U')]")
        );

        System.out.println("Total number of seats are:- " + totalSeats.size());

        System.out.println("\nAvailable Seats numbers are:-");
        int totalavailable=0;
        for (WebElement element : totalSeats) {
            if (element.getAttribute("aria-label").contains("seat status available"))
            {
                System.out.print(element.getAttribute("id") + ", ");
                totalavailable++;
            }
        }
        System.out.println("\n Total Available Seats are:-" +totalavailable);


            int totalSold=0;
        System.out.println("\n\nBooked Seats numbers are:-");
        for (WebElement element : totalSeats) {
            if (element.getAttribute("aria-label").contains("seat status sold"))
            {
                System.out.print(element.getAttribute("id") + ", ");
                totalSold++;
            }
        }
        System.out.println("\n\nTotal Sold Seats are:-"+totalSold);

        
        driver.findElement(firstAvailableSeat).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(booking));
        System.out.println(driver.findElement(booking).getText());
    }
}

