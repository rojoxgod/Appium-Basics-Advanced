package AppiumAndroid;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class _03_Scroll {


    @Test
    public static void Scroll() throws MalformedURLException {

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

        // 1st way of scrolling
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"))"));

        // 2nd way of scrolling
        //https://github.com/appium/appium-uiautomator2-driver/blob/master/docs/android-mobile-gestures.md
        boolean canScrollMore;
        do{
            canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                "left", 100, "top", 100, "width", 500, "height", 500,
                "direction", "down",
                "percent", 3.0
        ));} while(canScrollMore);


        //driver.quit();
        //server.stop();

    }


}

