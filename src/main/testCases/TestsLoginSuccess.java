package testCases;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import main.utils.TestSetup;

public class TestsLoginSuccess extends TestSetup {
    DateFormat dateFormat;

    @Test()
    public void sigInError() {

        WebElement btnSigin = driver.findElementById("sign_in_btn");
        WebElement tbxEmail = driver.findElementById("signin_email_et");
        tbxEmail.sendKeys("hoant");
        WebElement tbxPassword = driver.findElementById("signin_password_et");
        tbxPassword.sendKeys("123456789");
        if (driver.isKeyboardShown()) {
            driver.hideKeyboard();
        }
        btnSigin.click();
        WebElement txtError = driver.findElementById("text_warning");
        java.lang.String errorContent = txtError.getText().trim();
        Assert.assertEquals(errorContent, "Please check email or password");
    }

    private String getSourcePath() {
        File pathRoot = new File(System.getProperty("user.dir"));
        String sourcePath = pathRoot.getAbsolutePath();
        return sourcePath;
    }

    public String elemenScreenShot(WebElement element) throws IOException {
        String imgPath = "";
        try {
            Point elementPoint = element.getLocation();
            int eleWidth = element.getSize().getWidth();
            int eleHeight = element.getSize().getHeight();

            // Take a screen shot
            File scrFile = driver.getScreenshotAs(OutputType.FILE);

            // Crop specific element image
            BufferedImage fullImg = ImageIO.read(scrFile);
            BufferedImage elementScreenShot = fullImg.getSubimage(elementPoint.getX(), elementPoint.getY(), eleWidth, eleHeight);
            ImageIO.write(elementScreenShot, "png", scrFile);

            // Define screenshot name and save to screenshot folder
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String imgName = dateFormat.format(new Date()) + ".png";

            // Define screenshot folder path
            String screenShotFolderPath = String.format("%s/screenshots/", getSourcePath());
            imgPath = screenShotFolderPath + imgName;
            FileUtils.copyFile(scrFile, new File(imgPath));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return imgPath;
    }

    public String getTextFromImage(String imgPath) throws IOException {
        String result = "";
        File image = new File(imgPath);
        Tesseract instance = new Tesseract();
        try {
            if (image.exists()) {
                result = instance.doOCR(image).trim().replaceAll("\n"," ");;
            }
        } catch (TesseractException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public static void setPasscode(boolean option) {
        WebElement noPasscode = driver.findElementById("button2");
        WebElement yesPasscode = driver.findElementById("button1");
        if (option) {
            yesPasscode.click();
        } else noPasscode.click();
    }

    public static void createPassCode(String code) {
        char[] keyCode = code.toCharArray();
        for (char key : keyCode) {
            String keyString = Character.toString(key);
            WebElement btnKeyCode = driver.findElementByXPath("//*[@text = '" + keyString + "']");
            btnKeyCode.click();
        }
        //WebElement confirmPasscode = driver.findElementByLinkText("Confirm your PassCode");
        for (char key : keyCode) {
            String keyString = Character.toString(key);
            WebElement btnKeyCode = driver.findElementByXPath("//*[@text = '" + keyString + "']");
            btnKeyCode.click();
        }
    }
    public static void login(String username, String password){
        WebElement tbxEmail = driver.findElementById("signin_email_et");
        WebElement tbxPassword = driver.findElementById("signin_password_et");
        WebElement btnSigin = driver.findElementById("sign_in_btn");

        tbxEmail.sendKeys(username);
        tbxPassword.sendKeys(password);
        if (driver.isKeyboardShown()) {
            driver.hideKeyboard();
        }
        btnSigin.click();
    }

    public static void passIntroduction() {
        WebElement introductionScreen = driver.findElementById("view_pager");
        while (introductionScreen.isDisplayed()) {
            WebElement btnNext = driver.findElementById("btn_next");
            btnNext.click();
        }
    }

    @Test()
    public void validateTutorialAtHomeScreen() throws IOException {
        String expectText = "You can view your purchased vouchers and phone codes here.";
        login("hoant", "123456a");
        setPasscode(false);
        passIntroduction();
        WebElement tutorialView = driver.findElementById("image_promotion");
        String image = elemenScreenShot(tutorialView);
        String actualText = getTextFromImage(image);
        Assert.assertTrue(actualText.contains(expectText));
    }

    @Test()
    public void validateToastMessage() {
        String expectText = "You can view your purchased vouchers and phone codes here";
        login("hoant", "123456a");
        setPasscode(false);
        passIntroduction();
        WebElement toastMessage = driver.findElementById("view_pager");
        try {
            String image = elemenScreenShot(toastMessage);
            String actualText = getTextFromImage(image);
            Assert.assertTrue(actualText.contains(expectText));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test()
    public void loginAndSetPassCode() {
        String expectText = "Create PassCode success";
        login("hoant", "123456a");
        setPasscode(true);
        createPassCode("1234");
        WebElement toastMessage = driver.findElementById("view_pager");
        try {
            String image = elemenScreenShot(toastMessage);
            String actualText = getTextFromImage(image);
            //System.out.println(actualText);
            Assert.assertTrue(actualText.contains(expectText));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test()
    public void Demo(){
        WebElement dpRole = driver.findElementByClassName("android.widget.Spinner");
        driver.findElementByXPath("//*[contains(@content-desc, 'Bác sĩ ')]").click();
        driver.findElementByXPath("//*[contains(@content-desc, 'ĐĂNG NHẬP ')]").click();


    }



}