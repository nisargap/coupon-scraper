package com.nisarga.coupons;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.phantomjs.PhantomJSDriver;
//import org.openqa.selenium.phantomjs.PhantomJSDriverService;
//import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CouponsApp
{
    // created by Nisarga

    public static void mainMenu() {
        // menu
        System.out.println("Choose an option: ");
        System.out.println("1. Coupon Deals");
        System.out.println("2. Search Keywords");
        System.out.println("3. Top Deals");
        System.out.print("Enter 1,2,3: ");
    }

    public static void couponMenu() {
        System.out.println("Choose a category: ");
        System.out.println("1. Hotels");
        System.out.print("Enter 1,2,3: ");
    }

    public static void hotelSearch(String zipcode) throws IOException {

//        DesiredCapabilities dcap = DesiredCapabilities.phantomjs();
//
//        String[] phantomArgs = new  String[] {
//                "--webdriver-loglevel=NONE"
//        };
//        dcap.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, phantomArgs);
//
//        PhantomJSDriver browser = new PhantomJSDriver(dcap);
        WebDriver browser = new FirefoxDriver();

        browser.get("http://www.retailmenot.com/coupons/hotel");
        WebElement zipCodeBox = browser.findElement(By.cssSelector(".travel-location"));
        zipCodeBox.sendKeys(zipcode);
        zipCodeBox.sendKeys(Keys.RETURN);
        ArrayList<String> tabs = new ArrayList<>(browser.getWindowHandles());
        browser.switchTo().window(tabs.get(1));
        //WebDriverWait wait = new WebDriverWait(browser, 10);
        WebElement e = (new WebDriverWait(browser, 10)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#listings")));

        String results = e.getText().replaceAll( "([\\ud800-\\udbff\\udc00-\\udfff])", "");
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("results/filename.txt"), "utf-8"))) {
            writer.write(results);
        }

        browser.quit();

    }
    public static void main( String[] args ) throws InterruptedException, IOException {


        mainMenu();
        String userInput;
        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();
        // strip newline characters
        //userInput = userInput.replace("\n", "").replace("\r", "");
        switch (userInput) {
            case "1":

                // initialize browser
                couponMenu();
                String category;
                category = in.nextLine();


                if (category.equals("1")) {

                    System.out.print("Enter your zip code: ");
                    String zipcode;
                    zipcode = in.next();
                    if (!zipcode.isEmpty()) {

                        hotelSearch(zipcode);
                    }


                }

                break;
            case "2":

                // TODO: keyword search here
                System.out.println("Keyword Search");
                break;
            case "3":

                // TODO: Top Deals grab here
                System.out.println("Top Deals");
                break;
            default:

                System.out.println("Invalid Input!");
                break;
        }

    }

}
