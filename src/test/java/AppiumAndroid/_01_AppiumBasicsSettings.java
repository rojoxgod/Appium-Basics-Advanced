package AppiumAndroid;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class _01_AppiumBasicsSettings {


    @Test
    public static void WifiSettingsName() throws MalformedURLException {

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
                                         // to connect a real device to your pc the name of device should be "Android Device"
                                         // follow the instruction of how to connect the device before testing (tap 7 times of build number - enable usb debugging)
        options.setApp("E://SeleniumProjects//Appium//src//test//java//Resources//ApiDemos-debug.apk"); // select path and start the app on device


        // appium server - caps
        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);


        //global implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
        driver.findElement(AppiumBy.accessibilityId("3. Preference dependencies")).click();
        driver.findElement(AppiumBy.id("android:id/checkbox")).click();
        driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
        Assert.assertEquals(driver.findElement(By.id("android:id/alertTitle")).getText(), "WiFi settings");
        driver.findElement(AppiumBy.id("android:id/edit")).sendKeys("Appium WiFi");
        driver.findElement(AppiumBy.id("android:id/button1")).click();





        //driver.quit();
        //server.stop();

    }


}

