package com.github.ngoanh2n.pages;

import com.codeborne.selenide.Selenide;
import com.github.ngoanh2n.common.BasePage;

import static com.codeborne.selenide.Selenide.$;

/**
 * LoginPage class
 * <br/>
 *
 * @author ngoanh2n
 */

public class LoginPage extends BasePage<LoginPage> {

    public HomePage login(String username, String password) {
        this.screenshotEntryPage();
        $("#user_login").setValue(username);
        $("#user_password").setValue(password);
        $("input[type='submit'][value='Sign in']").click();
        return Selenide.page(HomePage.class);
    }
}
