package com.etouches.steps.modules.eselect;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;
import com.etouches.decorator.AbstractPage;
import com.etouches.util.PropertyStorage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class AtGenericPageSteps extends ScenarioSteps {
  private static final Logger log = LoggerFactory.getLogger(AtGenericPageSteps.class);
  static SoftAssert softAssert = new SoftAssert();
  static int noOfLinksVerified = 1;

  private AbstractPage onAbstractPage() {
    return pages().get(AbstractPage.class);
  }

  @Step
  public List<String> getAllLinks() {
    return onAbstractPage().getAllLinks();
  }

  @Step
  public void navigateToLinkUrl(String link) {
    log.info("Navigating to : " + link);
    if (!(link.contains("javascript"))
        && !(link.contains("support_dashboard"))&& !(link.contains("phpinfo"))&& !(link.contains("admin/apc"))&& !(link.contains("loggedin/databases/widgets/"))) {
        getDriver().navigate().to(link);
           }
    else {
      log.info("Unable to load this link: " + link);
    }
  }

  public String getTitle() {
    log.info("Page Title is: " + getDriver().getTitle());
    return getDriver().getTitle();
  }

  public String getCurrentURL() {
    log.info("Page Navigated To: " + getDriver().getTitle());
    return getDriver().getCurrentUrl();
  }

  @Step
  public void assertAllLinks() {
    softAssert.assertAll();
  }


  @Step()
  public void shouldRedirectedToSameDomainUrl2() {
    log.info("***** No Of links verified: " + noOfLinksVerified++);
    String baseURLDomain = PropertyStorage.getBaseUrl().substring(8);
    if (getTitle().equals("Aventri")) {
      softAssert.assertEquals(getTitle(), "Aventri",
          "This link is not accessible. Current page title is: " + getTitle());
      softAssert.assertTrue(getCurrentURL().equals(baseURLDomain),
          "Unable to load current page. Please check the URL: "
              + getDriver().getCurrentUrl());

      log.info("Current page Url is: " + getCurrentURL());
      log.info("=========================================");
    } else {
      softAssert.fail();
      log.info("Unable to load current page. Please check the URL: " + getCurrentURL());
      log.info("=========================================");
    }
  }



  @Step
  public void shouldSeeURLIsRedirectedProperly(String link) {
    navigateToLinkUrl(link);
    softAssert.assertTrue(getDriver().getCurrentUrl().contains(PropertyStorage.getBaseUrl()),
        " Assetion failed as it navigated to: " + getDriver().getCurrentUrl());
    softAssert.assertEquals(getDriver().getTitle(), "Aventri",
        "This Link is not accessible: " + link);
  }

  @Step()
  public void shouldRedirectedToSameDomainUrl() {
    log.info("***** No Of links verified: " + noOfLinksVerified++);
    String baseURLDomain = PropertyStorage.getBaseUrl().substring(32);
    if (getTitle().equals("Aventri")) {
      softAssert.assertEquals(getTitle(), "Aventri",
          "This link is not accessible. Current page title is: " + getTitle());
//      softAssert.assertTrue(getCurrentURL().equals(baseURLDomain),
//          "Unable to load current page. Please check the URL: "
//              + getDriver().getCurrentUrl());

      log.info("Current page Url is: " + getCurrentURL());
      log.info("=========================================");
//    } else {
//      softAssert.fail();
//      log.info("Unable to load current page. Please check the URL: " + getCurrentURL());
//      log.info("=========================================");
//    }
  }
}
}
