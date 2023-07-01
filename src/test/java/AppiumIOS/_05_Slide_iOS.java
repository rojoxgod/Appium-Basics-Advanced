package AppiumIOS;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class _05_Slide_iOS {


    @Test
    public static void Slide() throws MalformedURLException {

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

        //sliding button gesture
        WebElement slider = driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeSlider[`label == 'AppElem'`]"));
        slider.sendKeys("1%");
        slider.sendKeys("0%"); //range is between 0 and 1 (0% - 100%)


    }


}

