package com.etouches.tests;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.etouches.tests.ereg.BaseERegTest;
import net.thucydides.core.annotations.findby.By;

public class URLTest extends BaseERegTest {


   public void urlTest1() throws Exception {
   ExcelApiTest eat = new ExcelApiTest("D:\\Book1 - Copy.xlsx");
   ExcelApiOutTest output = new ExcelApiOutTest("D:\\Book1 - Copy - Copy - Copy - Copy.xlsx");
   int rowsCount;
   for (rowsCount = 1; rowsCount < eat.getRowCount("Credentials"); rowsCount++) {
    System.out.println("Current row: " + rowsCount);
   webdriver.get(eat.getCellData("Credentials", "URL", rowsCount));
   Thread.sleep(1500);
   String actualURL = webdriver.getCurrentUrl();
   String expectedURL = eat.getCellData("Credentials", "Redirected Link", rowsCount);
   if (expectedURL.equals(actualURL)) {
    System.out.println("BASE URL: " + eat.getCellData("Credentials", "URL", rowsCount));
    System.out.println("EXPECTED URL: " + expectedURL);
    System.out.println("ACTUAL URL: " + actualURL);
    System.out.println("RESULT: PASS");
    System.out.println("----------------***************************----------------------");
     System.out.println("ACTUAL URL: " + eat.getCellData("Credentials", "URL", rowsCount)
    + " EXPECTED URL: " + expectedURL + " RESULT: PASS");
    System.out.println("URL TEST PASS: Redirected to is: " + actualURL);
    output.setCellData("Credentials", "Actual Redirected Url", rowsCount, actualURL);
    output.setCellData("Credentials", "Result", rowsCount, "PASS");
    output.setCellData("Credentials", "Redirecting Correctly", rowsCount, "YES");
    output.setCellData("Credentials", "Verified By", rowsCount, "Automation");
    output.setCellData("Credentials", "Remarks", rowsCount, actualURL);
   } else {
   System.out.println("BASE URL: " + eat.getCellData("Credentials", "URL", rowsCount));
   System.out.println("EXPECTED URL: " + expectedURL);
   System.out.println("ACTUAL URL: " + actualURL);
   System.out.println("RESULT: FAIL");
   System.out.println("----------------***************************----------------------");
    System.out.println("ACTUAL URL: " + eat.getCellData("Credentials", "URL", rowsCount)
    + " EXPECTED URL: " + expectedURL + " RESULT: FAIL");
   
     System.out.print(
    "Running URL test for: " + eat.getCellData("Credentials", "URL", rowsCount) + "==>");
    System.out.println("URL TEST FAIL: Redirected to is: " + actualURL);
    output.setCellData("Credentials", "Actual Redirected Url", rowsCount, actualURL);
    output.setCellData("Credentials", "Result", rowsCount, "FAIL");
    output.setCellData("Credentials", "Redirecting Correctly", rowsCount, "NO");
    output.setCellData("Credentials", "Verified By", rowsCount, "Automation");
    output.setCellData("Credentials", "Remarks", rowsCount, actualURL);
   }
  
   }
   }


  @Test
  public void urlTest2() throws Exception {
    openLoginPageAndLoginAsDefaultUser();
    sideNavigation.openSideNavigation();

  }



  @Test
  public void verify_Categoriestab() throws Exception {
    openLoginPageAndLoginAsDefaultUser();
    sideBar.eventsPage.openEvent("AutoTest742805");
    mainNavigation.getERegSubmenu().openERegSettingsPage();
    module.eReg.navigation.openCategoriesTab();
    shoulseePageTitle(webdriver.getTitle(), webdriver.getCurrentUrl());
  }

  @Test
  public void verify_EventInfoTab() throws Exception {
    openLoginPageAndLoginAsDefaultUser();
    sideBar.eventsPage.openEvent("AutoTest474523");
    mainNavigation.getERegSubmenu().openERegSettingsPage();
    module.eReg.navigation.getEventInfoTab();
    shoulseePageTitle(webdriver.getTitle(), webdriver.getCurrentUrl());
    module.eReg.navigation.getEventInfoTab().openEventEmailsMenuItem();
    shoulseePageTitle(webdriver.getTitle(), webdriver.getCurrentUrl());
    module.eReg.navigation.getEventInfoTab().openRegistrantRulesMenuItem();
    shoulseePageTitle(webdriver.getTitle(), webdriver.getCurrentUrl());
    module.eReg.navigation.getEventInfoTab().openPromotionSocialMenuItem();
    shoulseePageTitle(webdriver.getTitle(), webdriver.getCurrentUrl());
  }


  @Test
  public void verify_AttendeeInforTab() throws Exception {
    openLoginPageAndLoginAsDefaultUser();
    sideBar.eventsPage.openEvent("AutoTest474523");
    mainNavigation.getERegSubmenu().openERegSettingsPage();
    module.eReg.navigation.openAttendeeInfoTab();
    shoulseePageTitle(webdriver.getTitle(), webdriver.getCurrentUrl());
    module.eReg.navigation.getAttendeeInfoTab().openActiveQuestionsPage();
    shoulseePageTitle(webdriver.getTitle(), webdriver.getCurrentUrl());
    module.eReg.navigation.getAttendeeInfoTab().openInactiveQuestionsPage();
  }

  @Test
  public void verify_AgendaAndOptionTab() throws Exception {
    openLoginPageAndLoginAsDefaultUser();
    sideBar.eventsPage.openEvent("AutoTest742805");
    mainNavigation.getERegSubmenu().openERegSettingsPage();
    module.eReg.navigation.openAgendaAndOptionsTab();
    shoulseePageTitle(webdriver.getTitle(), webdriver.getCurrentUrl());
    module.eReg.navigation.openAgendaAndOptionsTab().openAgendaAndSessionsMenuItem();
    shoulseePageTitle(webdriver.getTitle(), webdriver.getCurrentUrl());
    module.eReg.navigation.openAgendaAndOptionsTab().openLocationsMenuItem();
    shoulseePageTitle(webdriver.getTitle(), webdriver.getCurrentUrl());
    module.eReg.navigation.openAgendaAndOptionsTab().openOptionsAndMerchandiseMenuItem();
    shoulseePageTitle(webdriver.getTitle(), webdriver.getCurrentUrl());
    module.eReg.navigation.openAgendaAndOptionsTab().openSpeakersMenuItem();
    shoulseePageTitle(webdriver.getTitle(), webdriver.getCurrentUrl());
  }


  @Test
  public void verify_HotelAndTravel() throws Exception {
    openLoginPageAndLoginAsDefaultUser();
    sideBar.eventsPage.openEvent("AutoTest742805");
    mainNavigation.getERegSubmenu().openERegSettingsPage();
    module.eReg.navigation.openHotelAndTravelTab();
    shoulseePageTitle(webdriver.getTitle(), webdriver.getCurrentUrl());
    module.eReg.navigation.openHotelAndTravelTab().openTravelSettingsMenuItem();
    shoulseePageTitle(webdriver.getTitle(), webdriver.getCurrentUrl());
  }

  @Test
  public void verify_FeesAndPayment() throws Exception {
    openLoginPageAndLoginAsDefaultUser();
    sideBar.eventsPage.openEvent("AutoTest742805");
    mainNavigation.getERegSubmenu().openERegSettingsPage();
    module.eReg.navigation.openFeesAndPaymentTab();
    shoulseePageTitle(webdriver.getTitle(), webdriver.getCurrentUrl());
    module.eReg.navigation.openFeesAndPaymentTab().openStandardFeesMenuItem();
    shoulseePageTitle(webdriver.getTitle(), webdriver.getCurrentUrl());
    module.eReg.navigation.openFeesAndPaymentTab().openDiscountCodesMenuItem();
    shoulseePageTitle(webdriver.getTitle(), webdriver.getCurrentUrl());
    module.eReg.navigation.openFeesAndPaymentTab().openInvoiceMenuItem();
    shoulseePageTitle(webdriver.getTitle(), webdriver.getCurrentUrl());
    module.eReg.navigation.openFeesAndPaymentTab().openPaymentSettingsMenuItem();
    shoulseePageTitle(webdriver.getTitle(), webdriver.getCurrentUrl());
    module.eReg.navigation.openFeesAndPaymentTab().openTaxSettingsMenuItem();
    shoulseePageTitle(webdriver.getTitle(), webdriver.getCurrentUrl());
  }

  @Test
  public void verify_LookAndFeel() throws Exception {
    openLoginPageAndLoginAsDefaultUser();
    sideBar.eventsPage.openEvent("AutoTest742805");
    mainNavigation.getERegSubmenu().openERegSettingsPage();
    module.eReg.navigation.openLookAndFeelTab();
    shoulseePageTitle(webdriver.getTitle(), webdriver.getCurrentUrl());
    module.eReg.navigation.openLookAndFeelTab().openHeaderAndFootersMenuItem();
    shoulseePageTitle(webdriver.getTitle(), webdriver.getCurrentUrl());
  }


  @Test
  public void verify_AdvSettings() throws Exception {
    openLoginPageAndLoginAsDefaultUser();
    sideBar.eventsPage.openEvent("AutoTest742805");
    mainNavigation.getERegSubmenu().openERegSettingsPage();
    module.eReg.navigation.openAdvSettingsTab();
    shoulseePageTitle(webdriver.getTitle(), webdriver.getCurrentUrl());
    module.eReg.navigation.openAdvSettingsTab().openGeneralSettingsMenuItem();
    shoulseePageTitle(webdriver.getTitle(), webdriver.getCurrentUrl());
    module.eReg.navigation.openAdvSettingsTab().openAdvApprovalMenuItem();
    shoulseePageTitle(webdriver.getTitle(), webdriver.getCurrentUrl());
    module.eReg.navigation.openAdvSettingsTab().openLanguageAndWordingMenuItem();
    shoulseePageTitle(webdriver.getTitle(), webdriver.getCurrentUrl());
    module.eReg.navigation.openAdvSettingsTab().openPreApprovedDataMenuItem();
    shoulseePageTitle(webdriver.getTitle(), webdriver.getCurrentUrl());
    module.eReg.navigation.openAdvSettingsTab().openPreLoadDataMenuItem();
    shoulseePageTitle(webdriver.getTitle(), webdriver.getCurrentUrl());
  }

  @Test
  public void urlTest3() throws Exception {
    openLoginPageAndLoginAsDefaultUser();
    sideBar.eventsPage.openEvent("AutoTest742805");
    mainNavigation.getERegSubmenu().openERegSettingsPage();

    module.eReg.navigation.openCategoriesTab();
    System.out.println("Current URL is: " + webdriver.getTitle());
    System.out.println("Current URL is: " + webdriver.getCurrentUrl());
    System.out.println("=========================================");
    module.eReg.navigation.openEventInfoTab();
    System.out.println("Current URL is: " + webdriver.getTitle());
    System.out.println("Current URL is: " + webdriver.getCurrentUrl());
    System.out.println("=========================================");
    module.eReg.navigation.openAttendeeInfoTab();
    System.out.println("Current URL is: " + webdriver.getTitle());
    System.out.println("Current URL is: " + webdriver.getCurrentUrl());
    System.out.println("=========================================");
    module.eReg.navigation.openAgendaAndOptionsTab();
    System.out.println("Current URL is: " + webdriver.getTitle());
    System.out.println("Current URL is: " + webdriver.getCurrentUrl());
    System.out.println("=========================================");
    module.eReg.navigation.openHotelAndTravelTab();
    System.out.println("Current URL is: " + webdriver.getTitle());
    System.out.println("Current URL is: " + webdriver.getCurrentUrl());
    System.out.println("=========================================");
    module.eReg.navigation.openFeesAndPaymentTab();
    System.out.println("Current URL is: " + webdriver.getTitle());
    System.out.println("Current URL is: " + webdriver.getCurrentUrl());
    System.out.println("=========================================");
    module.eReg.navigation.openLookAndFeelTab();
    System.out.println("Current URL is: " + webdriver.getTitle());
    System.out.println("Current URL is: " + webdriver.getCurrentUrl());
    System.out.println("=========================================");
    module.eReg.navigation.openAdvSettingsTab();
    System.out.println("Current URL is: " + webdriver.getTitle());
    System.out.println("Current URL is: " + webdriver.getCurrentUrl());
    System.out.println("=========================================");

  }


  @Test
  public void finaltest() throws Exception {
    openLoginPageAndLoginAsDefaultUser();
    List<WebElement> refList =
        webdriver.findElements(By.xpath("//ul[@class='accordion']//descendant::a"));
    List<String> links = new ArrayList<>();
    for (WebElement link : refList) {
      links.add(link.getAttribute("href"));
      System.out.println(links);
    }
    for (String link : links) {
      if (link.contains("https")) {
        webdriver.navigate().to(link);
        String pageTitle = webdriver.getTitle();
        String pageURL = webdriver.getCurrentUrl();

        if (pageTitle.equals("Aventri")) {
          System.out.println("***** ACTUAL URL: " + pageURL);
          System.out.println("***** RESULT: PASS");
        } else {
          System.out.println("***** ACTUAL URL: " + pageURL);
          System.out.println("***** RESULT: FAIL");
        }

      }
    }

  }


  @Test
  public void urlTes11t() throws Exception {
    openLoginPageAndLoginAsDefaultUser();
    sideNavigation.openSideNavigation();
    // module.eReg.genericPageSteps.verifyLinks();
  }

  @Test
  public void urlTest() throws Exception {
    List<String> href = new ArrayList<>();
    openLoginPageAndLoginAsDefaultUser();
    sideNavigation.openSideNavigation();
    List<WebElement> refList =
        webdriver.findElements(By.xpath("//ul[@class='accordion']//descendant::a"));

    for (int i = 0; i <= refList.size(); i++) {
      String textname = refList.get(i).getAttribute("href");
      href.add(textname);
      System.out.println("Added: "+textname);
    }

      //
      // String QUESTION_CHECKBOX ="//a[text()='%s']";
      // System.out.println(String.format(QUESTION_CHECKBOX, refList.get(i).getText()));
      // String link=webdriver.findElement(By.xpath(String.format(QUESTION_CHECKBOX,
      // refList.get(i).getText()))).getAttribute("href");
    
    for (int i = 0; i <= href.size(); i++) {
      String text= href.get(i);
      if (text.contains("https")) {
        webdriver.navigate().to(text);
        String pageTitle = webdriver.getTitle();
        String pageURL = webdriver.getCurrentUrl();

        if (pageTitle.equals("Aventri")) {
          System.out.println("***** ACTUAL URL: " + pageURL);
          System.out.println("***** RESULT: PASS");
          // webdriver.navigate().refresh();
          sideNavigation.openSideNavigation();
        } else {
          System.out.println("***** ACTUAL URL: " + pageURL);
          System.out.println("***** RESULT: FAIL");
          webdriver.navigate().refresh();
          sideNavigation.openSideNavigation();
        }
      }

      else {
        System.out.println("This is not a link : " + text);
      }

    }


    System.out.println("Links: " + refList);
    for (int i = 0; i <= refList.size(); i++) {
      sideNavigation.openSideNavigation();
      String linkName = refList.get(i).getText();
      System.out.println("Verifying link for: " + linkName);

      // String xpath=""//ul[@class='accordion']//descendant::a
      // webdriver.findElement(By.xpath("


      // System.out.println("URL: "+refList.get(i).getAttribute("href"));
      String url = refList.get(i).getAttribute("href");
      webdriver.navigate().to(url);
      String pageTitle = webdriver.getTitle();
      String pageURL = webdriver.getCurrentUrl();

      if (pageTitle.equals("Aventri")) {
        System.out.println("***** ACTUAL URL: " + pageURL);
        System.out.println("***** RESULT: PASS");
      } else {
        System.out.println("***** ACTUAL URL: " + pageURL);
        System.out.println("***** RESULT: FAIL");
      }
      continue;
    }
  }


  public void shoulseePageTitle(String actual, String url) {
    Assert.assertEquals("Aventri", actual, "Title vefification FAIL");
    System.out.println("Title vefification PASS");
    System.out.println("Page Current Url is: " + url);
    System.out.println("=========================================");
  }

}
