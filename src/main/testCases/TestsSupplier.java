package main.testCases;

import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import main.utils.TestSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static main.pageObjects.HomePage.getHomeTutorial;
import static main.pageObjects.HomePage.getSupplierButton;
import static main.pageObjects.SupplierPage.getAllowButton;
import static main.pageObjects.SupplierPage.getTutorialView;
import static testCases.TestsLoginSuccess.*;


public class TestsSupplier extends TestSetup {

    public void scrollAndClick(String visibleText) {
        String command = "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+visibleText+"\").instance(0))";
        driver.findElementByAndroidUIAutomator(command).click();
    }

    public void scrollDown(){
        Dimension dimension = driver.manage().window().getSize();
        int scrollHeightSatrt = (int)(dimension.getHeight() * 0.8);
        int scrollHeightEnd = (int)(dimension.getHeight() * 0.2);
        new TouchAction((PerformsTouchActions)driver).press(PointOption.point(0,scrollHeightSatrt))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(0,scrollHeightEnd))
                .release().perform();
    }

    public List<WebElement> elements(){
        String command = "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().resourceId('supplier_name_tv')";
        List<WebElement> elements = (List<WebElement>) driver.findElementByAndroidUIAutomator(command);
        return elements;
    }

    public  List<WebElement> elementList(String visibleText){
        List<WebElement> list = null;
        String command = "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+visibleText+"\").instance(0))";
        while (!driver.findElementByAndroidUIAutomator(command).isDisplayed()){
//             list = driver.findElementsByAndroidUIAutomator("new UiScrollable(new UiSelector()."
//                    + "scrollable(true)).scrollIntoView(new UiSelector().resourceId(\"com.evolable.benepay:id/supplier_name_tv\"))");
            list = driver.findElements(By.id("supplier_name_tv"));
            if(driver.findElementByAndroidUIAutomator(command).isDisplayed())
                break;
        }
        return list;
    }

//    public void jsScrollDown() throws Exception {
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        HashMap<String, String> scrollObject = new HashMap<String, String>();
//        scrollObject.put("direction", "down");
//        js.executeScript("mobile: scroll", scrollObject);
//
//    }
    @Test()
    public void scrollToSupplier() throws Exception {
        login("hoant","P@ssw0rd");
        setPasscode(false);
        passIntroduction();
        //TouchActions action = new TouchActions(driver);
        getHomeTutorial().click();
        getSupplierButton().click();
        getAllowButton().click();
        getTutorialView().click();
        scrollDown();
        }
        //scrollAndClick("Ashima");
}
