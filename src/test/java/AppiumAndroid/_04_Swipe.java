package AppiumAndroid;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class _04_Swipe {


    @Test
    public static void Swipe() throws MalformedURLException {

        //auto start of appium server
        /*
        AppiumDriverLocalService server = new AppiumServiceBuilder()
                .withAppiumJS(new File("C://Users//vital//AppData//Roaming//npm//node_modules//appium//build//lib//main.js"))
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .build();
         */

        //server.start();


        // define the device specs to use as caps
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Appium"); // device name from android studio
        options.setApp("E://SeleniumProjects//Appium//src//test//java//Resources//ApiDemos-debug.apk"); // select path and start the app on device


        // appium server - caps
        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);


        //global implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
        driver.findElement(AppiumBy.accessibilityId("1. Photos")).click();

        //swipe gesture
        //https://github.com/appium/appium-uiautomator2-driver/blob/master/docs/android-mobile-gestures.md
        WebElement element = driver.findElement(By.xpath("(//android.widget.ImageView)[1]"));
        Assert.assertEquals(driver.findElement(By.xpath("(//android.widget.ImageView)[1]")).getAttribute("focusable"), "true");

        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "direction", "left",
                "percent", 0.75
        ));

        Assert.assertEquals(driver.findElement(By.xpath("(//android.widget.ImageView)[1]")).getAttribute("focusable"), "false");

        //driver.quit();
        //server.stop();

    }


}

