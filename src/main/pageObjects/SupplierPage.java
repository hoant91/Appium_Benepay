package main.pageObjects;

import main.utils.TestSetup;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class SupplierPage extends TestSetup {
    public static WebElement locationPopUp() {
        WebElement element = null;
        try {
            element = driver.findElementById("dialog_container");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return element;
    }
    //permission_allow_button
    public static WebElement getAllowButton() {
        WebElement element = null;
        try {
            element = driver.findElementById("com.android.packageinstaller:id/permission_allow_button");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return element;
    }

    public static WebElement getTutorialView() {
        WebElement element = null;
        try {
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            element = driver.findElementById("tutorial_view");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return element;
    }

    public static WebElement getSupplier(String supplierName) {
        WebElement element = null;
        try {
            element = driver.findElementByXPath("//*[@text='"+supplierName+"']");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return element;
    }

}
