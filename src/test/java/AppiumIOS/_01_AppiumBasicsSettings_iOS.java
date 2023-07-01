package AppiumIOS;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class _01_AppiumBasicsSettings_iOS {


    @Test
    public static void IOSBasicTest() throws MalformedURLException {

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
        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName("iPhone 13 Pro");//device name from xcode
        options.setApp("app_path");//select path and start the app on device
                                   //connect real device - lesson 72 - https://appium.readthedocs.io/en/stable/en/drivers/ios-xcuitest-real-devices/
        options.setPlatformVersion("15.5");//mandatory
        options.setWdaLaunchTimeout(Duration.ofSeconds(20));//IOS work flow - Appium -> WebDriver Agent -> IOS apps
                                                            //Android work flow - Appium -> Android apps

        // appium server - caps
        IOSDriver driver = new IOSDriver(new URL("http://127.0.0.1:4723"), options);


        //global implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


        driver.findElement(AppiumBy.accessibilityId("Alert Views")).click();

        //xpath too slow in ios, instead use IOSClassChain
        //driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='Text Entry']")).click();
        driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`name == 'Text Entry'`]")).click();
        driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeCell")).sendKeys("Hello");
        driver.findElement(AppiumBy.accessibilityId("OK")).click();

        //another way to find element is using IOSNsPredicateString
        driver.findElement(AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeStaticText' AND value == 'Confirm / Cancel'")).click();
        driver.findElement(AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeStaticText' AND value BEGINSWITH[c] 'Confirm'")).click(); //[c] - key sensitive
        driver.findElement(AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeStaticText' AND value ENDSWITH[c] 'Confirm'")).click();
        System.out.println(driver.findElement(AppiumBy.iOSNsPredicateString("name BEGINSWITH[c] 'A message'")).getText());
        driver.findElement(AppiumBy.accessibilityId("Confirm")).click();


        //driver.quit();
        //server.stop();

    }


}

