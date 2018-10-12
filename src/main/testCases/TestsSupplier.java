package main.testCases;

import main.utils.TestSetup;
import org.testng.annotations.Test;

import static main.pageObjects.HomePage.getHomeTutorial;
import static main.pageObjects.HomePage.getSupplierButton;
import static main.pageObjects.SupplierPage.getAllowButton;
import static main.pageObjects.SupplierPage.getTutorialView;
import static testCases.TestsLoginSuccess.*;

public class TestsSupplier extends TestSetup {
    @Test()
    public void verifySupplierList(){
        login("hoant", "P@ssw0rd");
        setPasscode(false);
        passIntroduction();
        getHomeTutorial().click();
        getSupplierButton().click();
        getAllowButton().click();
        getTutorialView().click();

    }

}
