package AppiumAndroid;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class _07_AppOpening {


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
        //options.setApp("E://SeleniumProjects//Appium//src//test//java//Resources//ApiDemos-debug.apk"); // select path and start the app on device


        // appium server - caps
        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);


        //global implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //to open a speficic page you need APP PACKAGE and APP ACTIVITY
        //to know your APP PACKAGE and APP ACTIVITY we should pass this command to cmd *** adb shell dumpsys window | find "mCurrentFocus" ***
        //output *** mCurrentFocus=Window{cb17e45 u0 io.appium.android.apis/io.appium.android.apis.app.HelloWorld} ***
        //io.appium.android.apis         APP PACKAGE
        //io.appium.android.apis.app.HelloWorld     APP ACTIVITY
        Activity activity = new Activity("io.appium.android.apis", "io.appium.android.apis.app.HelloWorld");
        driver.startActivity(activity);
        //so we can also delete options.setApp from UiAutomator2Options when using activity


        //driver.quit();
        //server.stop();

    }


}

