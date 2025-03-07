package main.utils;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestSetup {
    public static AndroidDriver driver;
    @BeforeTest
    public void beforeTest() {
        File pathRoot = new File(System.getProperty("user.dir"));
        //File appDir = new File(pathRoot,"/");
        File app = new File(pathRoot, "/apk/app-prod-release.apk");

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("deviceName", "Galaxy J2");
        cap.setCapability("platformVersion", "6.0.1");
        cap.setCapability("platformName", "Android");
        cap.setCapability("app", app.getAbsolutePath());
        //cap.setCapability("appPackage", "com.evolable.benepay.debug");
        cap.setCapability("automationName", "appium");
        //cap.setCapability("appWaitActivity", "com.evolable.benepay.MainActivity");
        cap.setCapability("clearSystemFiles", true);
        cap.setCapability("networkSpeed","full");

        try {
            driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), cap) ;
            Assert.assertNotNull(driver);

        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }
}
