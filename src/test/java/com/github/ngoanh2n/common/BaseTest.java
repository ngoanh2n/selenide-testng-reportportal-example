package com.github.ngoanh2n.common;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.epam.reportportal.testng.ReportPortalTestNGListener;
import com.github.ngoanh2n.pages.HomePage;
import com.github.ngoanh2n.pages.LoginPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.open;
import static java.lang.invoke.MethodHandles.lookup;

/**
 * BaseTest class
 * <br/>
 *
 * @author ngoanh2n@gmail.com (Ho Huu Ngoan)
 */

@Listeners({ReportPortalTestNGListener.class})
public class BaseTest {

    private final static Logger logger = LoggerFactory.getLogger(lookup().lookupClass());
    private final static String selenideProperties = "selenide.properties";

    protected HomePage homePage;

    @BeforeClass
    protected void setupClass() throws IOException {
        /*
         * Load selenide.properties file in resources
         */
        Properties props = new Properties();
        InputStream inputStream = BaseTest.class
                .getClassLoader()
                .getResourceAsStream(selenideProperties);
        props.load(inputStream);

        if (!props.isEmpty()) {
            for (Object propObj : props.keySet()) {
                String prop = String.valueOf(propObj);

                if (!System.getProperties().containsKey(prop)) {
                    System.setProperty(prop, props.getProperty(prop));
                }
            }
        }

        logger.info("Loading selenide properties as {}", selenideProperties);
    }

    @AfterClass
    protected void cleanupClass() {
        SelenideLogger.removeListener("AllureSelenide");
        if (WebDriverRunner.hasWebDriverStarted()) WebDriverRunner.closeWebDriver();
    }

    @BeforeMethod
    protected void setupTest() {
        this.homePage = open("/users/sign_in", LoginPage.class)
                .login("anonymous.automation", "ngoanh2n");
    }

    @AfterMethod
    protected void cleanupTest() {
        this.homePage.logout();
    }
}
