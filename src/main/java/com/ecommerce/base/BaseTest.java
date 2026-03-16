package com.ecommerce.base;

import com.ecommerce.config.ConfigReader;
import com.ecommerce.utils.ExtentReportManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected static final Logger log = LogManager.getLogger(BaseTest.class);

    @BeforeSuite
    public void initExtent() {
        ExtentReportManager.initReport();
        log.info("Extent Report initialized");
    }

    @BeforeMethod
    public void setUp() {
        String browser = ConfigReader.get("browser");

        if (browser.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        else if (browser.equalsIgnoreCase("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        else{
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        // Add Firefox/Edge here if needed

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(
            Long.parseLong(ConfigReader.get("implicitWait"))
        ));
        driver.manage().window().maximize();
        driver.get(ConfigReader.get("baseUrl"));

        log.info("Browser launched: " + browser);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            log.info("Browser closed");
        }
    }

    @AfterSuite
    public void flushReport() {
        ExtentReportManager.flushReport();
        log.info("Report flushed");
    }
}
