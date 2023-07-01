package AppiumAndroid;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class _06_LandscapeAndKeyEvents {


    @Test
    public static void LandscapeAndKeyEvents() throws MalformedURLException {

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


        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
        driver.findElement(AppiumBy.accessibilityId("3. Preference dependencies")).click();
        driver.findElement(AppiumBy.id("android:id/checkbox")).click();

        //rotation to the landscape mode and back
        DeviceRotation landscape = new DeviceRotation(0,0,90);
        DeviceRotation portrait = new DeviceRotation(0,0,0);
        driver.rotate(landscape);
        driver.rotate(portrait);

        driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
        Assert.assertEquals(driver.findElement(By.id("android:id/alertTitle")).getText(), "WiFi settings");
        driver.findElement(AppiumBy.id("android:id/edit")).sendKeys("Appium WiFi");
        driver.findElement(AppiumBy.id("android:id/button1")).click();

        //smartphone keys/buttons events
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.pressKey(new KeyEvent(AndroidKey.HOME));





        //driver.quit();
        //server.stop();

    }


}

