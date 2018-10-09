package main.testCases;

import main.utils.TestSetup;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.testng.annotations.Test;
import static main.pageObjects.HomePage.*;
import static main.pageObjects.SupplierPage.*;
import static testCases.TestsLoginSuccess.*;


public class TestsSupplier extends TestSetup {
    @Test()
    public void scrollToSupplier(){
        login("hoant","123456a");
        setPasscode(false);
        passIntroduction();
        getHomeTutorial().click();
        getSupplierButton().click();
        getAllowButton().click();
        TouchActions action = new TouchActions(driver);
        action.singleTap(getSupplier("Bingsuya coffee"));
        action.perform();

    }
}
