package main.pageObjects;

import main.utils.TestSetup;
import org.openqa.selenium.WebElement;

public class HomePage extends TestSetup {
    public static WebElement getSupplierButton() {
        WebElement element = null;
        try {
            element = driver.findElementById("button_supplier");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return element;
    }

    public static WebElement getTransferButton() {
        WebElement element = null;
        try {
            element = driver.findElementById("button_transfer");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return element;
    }

    public static WebElement getTopUpButton() {
        WebElement element = null;
        try {
            element = driver.findElementById("button_topup");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return element;
    }

    public static WebElement getHistoryButton() {
        WebElement element = null;
        try {
            element = driver.findElementById("button_history");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return element;
    }

    public static WebElement getHomeTutorial() {
        WebElement element = null;
        try {
            element = driver.findElementById("tutorial_view");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return element;
    }
}
