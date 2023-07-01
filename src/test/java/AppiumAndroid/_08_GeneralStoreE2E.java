package AppiumAndroid;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Set;

public class _08_GeneralStoreE2E {

    static UiAutomator2Options options;
    static AndroidDriver driver;

    @Test
    public static void StartDevice() throws MalformedURLException {

        // define the device specs to use as caps
        options = new UiAutomator2Options();
        options.setDeviceName("Appium"); // device name from android studio
        options.setApp("E://SeleniumProjects//Appium//src//test//java//Resources//General-Store.apk"); // select path and start/install the app on device
        options.setChromedriverExecutable("C://Users//vital//Downloads//webdriver//chromedriver_win32//chromedriver.exe"); // select the chrome driver for hybrid web usage

        //another way of installing app is using cmd
        //cd to the AppData/Local/Android/Sdk/platform-tools
        //adb install *appapkpath*


        // appium server - caps
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

    }

    @Test(dependsOnMethods = "StartDevice")
    public static void FillForm() throws MalformedURLException, InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("Vitali");
        driver.hideKeyboard();
        driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
        driver.findElement(AppiumBy.id("android:id/text1")).click();

        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Benin\"))"));
        driver.findElement(By.xpath("//android.widget.TextView[@text='Benin']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();



        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Jordan 6 Rings\"))"));

        int productCount = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();

        for(int i = 0; i < productCount; i++){

            if(driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText().equalsIgnoreCase("Jordan 6 Rings")) {

                driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();

            }

        }

        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"PG 3\"))"));

        for(int i = 0; i < productCount; i++){

            if(driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText().equalsIgnoreCase("PG 3")) {

                driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();

            }

        }

        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.textToBe(By.id("com.androidsample.generalstore:id/toolbar_title"), "Cart"));


        int cartSize = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).size();
        int total = 0;
        for(int i = 0; i < cartSize; i++){

            String priceStr = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(i).getText();
            double price = Double.parseDouble(priceStr.substring(1));
            total += (int) price;

        }
        Assert.assertEquals(total, 275);


        WebElement element = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(), "duration", 2000));

        driver.findElement(By.id("android:id/button1")).click();


        driver.findElement(By.xpath("//android.widget.CheckBox")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
        Thread.sleep(2000);


        //switch to web
        //launch appium server with this command - appium --allow-insecure chromedriver_autodownload
        //or set the chrome driver local path in options.set in UiAutomator2Options
        Set<String> contexts = driver.getContextHandles();

        for (String context : contexts) {
            System.out.println(context);
        }

        driver.context("WEBVIEW_com.androidsample.generalstore");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='KByQx']/div/div")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='L2AGLb']/div")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@name='q']")).sendKeys("Vitali");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@name='q']")).sendKeys(Keys.ENTER);
        Thread.sleep(1000);

        driver.context("NATIVE_APP");

        driver.pressKey(new KeyEvent(AndroidKey.BACK));


    }

}

