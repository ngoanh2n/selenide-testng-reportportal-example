package com.github.ngoanh2n.scenarios;

import com.github.ngoanh2n.common.BaseTest;
import org.testng.annotations.Test;

/**
 * ExampleTest class
 * <br/>
 *
 * @author ngoanh2n
 */

public class ExampleTest extends BaseTest {

    @Test
    void homePageTest() {
        this.homePage
                .verifyHomePageEmpty();
    }
}
