package com.example.corservassesment;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class AssessmentTest {
    private static final Logger log = LoggerFactory.getLogger(AssessmentTest.class);

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        List<String> switches = new ArrayList<String>();
        switches.add("enable-automation");
        options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.5615.134 Safari/537.36","ignore-certificate-errors");
        options.setExperimentalOption("excludeSwitches", switches);
        options.setExperimentalOption("useAutomationExtension", "False");
        options.setAcceptInsecureCerts(true);
        Configuration.browserCapabilities = options;
        Configuration.proxyEnabled = true;
        Configuration.proxyHost = "127.0.0.1";
        Configuration.proxyPort = 8080;
        Configuration.holdBrowserOpen=true;
        //Configuration.headless = true;

    }

    @Test
    public void checkPercntage() {
        open("https://discover.com");
        $("[class=\"slide--img credit-card\"]").click();

        $("[data-analytics-label=\"CCHP_CHECK_NOW\"]").shouldBe(visible);
        $("[data-analytics-label=\"SECURED_APPLY_NOW\"]").click();
        $("[id=\"adaptive-skip-this-step\"]").click();
        String percentageValue=$("[class=\"apr-value purchase-rate-apr apr-value-big\"]").getText();
        String parsedPercentValue =percentageValue.replace('%',' ');
        log.info(parsedPercentValue);
        Assertions.assertTrue(Integer.parseInt(parsedPercentValue)>20);

    }

}
