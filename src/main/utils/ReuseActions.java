package main.utils;

import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ReuseActions extends TestSetup {

    public static void scrollDown() {
        Dimension dimension = driver.manage().window().getSize();
        int scrollHeightSatrt = (int) (dimension.getHeight() * 0.8);
        int scrollHeightEnd = (int) (dimension.getHeight() * 0.2);
        try {
            new TouchAction((PerformsTouchActions) driver).press(PointOption.point(0, scrollHeightSatrt))
                    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                    .moveTo(PointOption.point(0, scrollHeightEnd))
                    .release().perform();
        } catch (Exception e) {

        }
    }

    public static void scrollAndClick(String visibleText) {
        String command = "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" + visibleText + "\").instance(0))";
        driver.findElementByAndroidUIAutomator(command).click();
    }

    public static List<String> getListElementByScroll(String endElementText) {
        //String a = "Hoàng Yến Group"; /// last element in the list
        Boolean found_result = false;
        List<String> listElementText = new ArrayList<String>();
        while (!found_result) {
            List<WebElement> temp = driver.findElements(By.id("supplier_name_tv"));
            for (int i = 0; i < temp.size(); i++) {
                String s = temp.get(i).getText();
                if (!listElementText.contains(s))
                    listElementText.add(s);
                if (s.equals(endElementText)) {
                    found_result = true;
                    break;
                }
            }
            if (!found_result) {
                scrollDown();
            }

        }
        return listElementText;
    }

}
