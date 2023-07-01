package AppiumIOS;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public class _06_InvokingApp_Swipe_iOS {


    @Test
    public static void InvokeApp_Swipe() throws MalformedURLException {

        //start manually appium

        // define the device specs to use as caps
        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName("iPhone 13 Pro");//device name from xcode
        options.setApp("app_path");//select path and start the app on device
        options.setPlatformVersion("15.5");//mandatory
        options.setWdaLaunchTimeout(Duration.ofSeconds(20));//IOS work flow - Appium -> WebDriver Agent -> IOS apps
                                                           //Android work flow - Appium -> Android apps

        // appium server - caps
        IOSDriver driver = new IOSDriver(new URL("http://127.0.0.1:4723"), options);


        //global implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.findElement(AppiumBy.accessibilityId("Picker View")).click();

        //invoking existing ios app using BundleID
        Map<String,String> params = new HashMap<String,String>();
        params.put("bundleId","com.apple.mobileslideshow");

        driver.executeScript("mobile: launchApp", params);


        driver.findElement(AppiumBy.iOSNsPredicateString("label == 'All photos'")).click();
        List<WebElement> allPhotos = driver.findElements(AppiumBy.iOSClassChain("**/XCUIElementTypeCell"));
        System.out.println(allPhotos.size());

        //swiping images
        driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeCell[1]")).click();

        Map<String,Object> params1 = new HashMap<String,Object>();
        params1.put("direction", "left");
        //params1.put("element", ((RemoteWebElement)element).getId() )
        //by default it's swiping in the center, instaed we should pass the webelement where to swipe, in this case we can let it without it

        driver.executeScript("mobile: swipe", params1);




    }


}

