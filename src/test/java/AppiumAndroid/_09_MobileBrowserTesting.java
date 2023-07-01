package AppiumAndroid;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class _09_MobileBrowserTesting {

    static UiAutomator2Options options;
    static AndroidDriver driver;

    @Test
    public static void StartDeviceMobileBrowserTesting() throws MalformedURLException, InterruptedException {

        // define the device specs to use as caps
        options = new UiAutomator2Options();
        options.setDeviceName("Appium"); // device name from android studio
        options.setChromedriverExecutable("C://Users//vital//Downloads//webdriver//chromedriver_win32//chromedriver.exe"); // select the chrome driver for hybrid web usage and mobile browser testing
        options.setCapability("browserName", "Chrome");

        //another way of installing app is using cmd
        //cd to the AppData/Local/Android/Sdk/platform-tools
        //adb install *appapkpath*


        // appium server - caps
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

    }

    @Test(dependsOnMethods = "StartDeviceMobileBrowserTesting")
    public static void MobileBrowserTesting() throws InterruptedException {

        //no appium in mobile browser testing, only selenium
        driver.get("https://google.com");
        driver.findElement(By.xpath("//input[@name='q']")).sendKeys("Vitali");
        driver.findElement(By.xpath("//input[@name='q']")).sendKeys(Keys.ENTER);

    }

}

