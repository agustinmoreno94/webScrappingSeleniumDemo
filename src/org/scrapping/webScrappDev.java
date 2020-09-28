package org.scrapping;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class webScrappDev {

    public static void main(String[] args) throws InterruptedException {
        try {
            String searchName = "selenium";
            String baseUrl = "https://dev.to/";
            System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors", "--silent");
            WebDriver driver = new ChromeDriver(options);
            pageOk(driver,baseUrl);
            writeTextInInput(driver, searchName);
            Thread.sleep(5000);
            scrapNamesInLists(driver);
            driver.close();
        }
        catch(Exception e) {
            System.out.println(e + "fallo el proceso");
        }
    }

    public static void pageOk(WebDriver driver, String  baseUrl) {
        driver.get(baseUrl);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        boolean exists = driver.findElements( By.cssSelector("input.top-bar--search-input.crayons-textfield") ).size() != 0;
        System.out.println("ingreso a "+baseUrl);
    }

    public static void writeTextInInput(WebDriver driver, String searchName) {
        WebElement typeExample =  driver.findElement(By.cssSelector("input.top-bar--search-input.crayons-textfield"));
        typeExample.sendKeys(searchName);
        driver.findElement(By.cssSelector("input.top-bar--search-input.crayons-textfield")).sendKeys(Keys.ENTER);
    }

    public static void scrapNamesInLists(WebDriver driver) {
        int sizefromname =  driver.findElements(By.cssSelector("#substories > article > div > div > div.crayons-story__top > div > div:nth-child(2) > p > a")).size();
        for(int i=1; i<sizefromname; i++){
            WebElement element= driver.findElement(By.cssSelector("#substories > article:nth-child("+i+") > div > div > div.crayons-story__top > div > div:nth-child(2) > p > a"));
            String val=element.getAttribute("innerText");
            System.out.println(val);
        }
    }

}
