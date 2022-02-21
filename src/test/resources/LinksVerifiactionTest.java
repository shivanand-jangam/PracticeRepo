package com.etouches.tests;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import com.etouches.requirements.Application;
import com.etouches.tests.categories.SmokeTestsForLinks;
import com.etouches.tests.ereg.BaseERegTest;
import net.thucydides.core.annotations.Story;

@Story(Application.Smoke.class)
public class LinksVerifiactionTest extends BaseERegTest {

  @Before
  public void setup() {
    openLoginPageAndLoginAsDefaultUser();
  }

//  @Test
//  @Category({SmokeTestsForLinks.class})
//  public void verify_Links_In_Hamburger_Menu1() throws Exception {
//    sideNavigation.openSideNavigation();
//    List<String> links = module.eReg.genericPageSteps.getAllLinks();
//    for (String link : links) {
//      module.eReg.genericPageSteps.navigateToLinkUrl(link);
//      module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl();
//    }
//    module.eReg.genericPageSteps.assertAllLinks();
//  }
  
  
  @Test
  @Category({SmokeTestsForLinks.class})
  public void verify_Links_In_Hamburger_Menu() throws Exception {
    sideNavigation.openSideNavigation();
    List<String> links = module.eReg.genericPageSteps.getAllLinks();
    for (int i=1;i<=70; i++) {
    	 module.eReg.genericPageSteps.navigateToLinkUrl(links.get(i));
         module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl();
    }
    
    webdriver.getCurrentUrl();
    sideNavigation.openSideNavigation();
    
    for (int i=71;i<=links.size(); i++) {
   	 module.eReg.genericPageSteps.navigateToLinkUrl(links.get(i));
        module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl();
   }
    module.eReg.genericPageSteps.assertAllLinks();
  }

  @Test
  @Category({SmokeTestsForLinks.class})
  public void verify_Ereg_Event_All_Tabs() throws Exception {
    sideBar.eventsPage.createEventWithRandomNameInDefaultAccount();
    generalPage.activateModulePage.openEregModule();
    generalPage.activateModulePage.clickUseStandardSettingsLink();
    mainNavigation.getERegSubmenu().openERegSettingsPage();
    module.eReg.navigation.getEventInfoTab();
    module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl();
    module.eReg.navigation.getEventInfoTab().openBasicDetailsMenuItem();
    module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl();
    module.eReg.navigation.getEventInfoTab().openEventSMSsMenuItem();
    module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl();
    module.eReg.navigation.getEventInfoTab().openEventEmailsMenuItem();
    module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl();
    module.eReg.navigation.getEventInfoTab().openRegistrantRulesMenuItem();
    module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl();
    module.eReg.navigation.getEventInfoTab().openWaitlistingMenuItem();
    module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl();
    module.eReg.navigation.getEventInfoTab().openPromotionSocialMenuItem();
    module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl();
    module.eReg.navigation.openCategoriesTab();
    module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl();
    module.eReg.navigation.openAttendeeInfoTab();
    module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl();
    module.eReg.navigation.getAttendeeInfoTab().openActiveQuestionsPage();
    module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl();
    module.eReg.navigation.getAttendeeInfoTab().openInactiveQuestionsPage();
    module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl();
    module.eReg.navigation.openAgendaAndOptionsTab();
    module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl();
    module.eReg.navigation.getAgendaAndOptionsTab().openAgendaAndSessionsMenuItem();
    module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl();
    module.eReg.navigation.getAgendaAndOptionsTab().openLocationsMenuItem();
    module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl();
    module.eReg.navigation.getAgendaAndOptionsTab().openOptionsAndMerchandiseMenuItem();
    module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl();
    module.eReg.navigation.getAgendaAndOptionsTab().openSpeakersMenuItem();
    module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl();
    module.eReg.navigation.openHotelAndTravelTab();
    module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl();
    module.eReg.navigation.getHotelAndTravelTab().openTravelSettingsMenuItem();
    module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl();
    module.eReg.navigation.getHotelAndTravelTab().openHotelSettingsMenuItem();
    module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl();
    module.eReg.navigation.openFeesAndPaymentTab();
    module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl();
    module.eReg.navigation.getFeesAndPaymentTab().openStandardFeesMenuItem();
    module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl();
    module.eReg.navigation.getFeesAndPaymentTab().openDiscountCodesMenuItem();
    module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl();
    module.eReg.navigation.getFeesAndPaymentTab().openInvoiceMenuItem();
    module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl();
    module.eReg.navigation.getFeesAndPaymentTab().openPaymentSettingsMenuItem();
    module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl();
    module.eReg.navigation.getFeesAndPaymentTab().openTaxSettingsMenuItem();
    module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl();
    module.eReg.navigation.openAdvSettingsTab();
    module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl();
    module.eReg.navigation.getAdvSettingsTab().openGeneralSettingsMenuItem();
    module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl();
    module.eReg.navigation.getAdvSettingsTab().openAdvApprovalMenuItem();
    module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl();
    module.eReg.navigation.getAdvSettingsTab().openLanguageAndWordingMenuItem();
    module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl();
    module.eReg.navigation.getAdvSettingsTab().openPreApprovedDataMenuItem();
    module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl();
    module.eReg.navigation.getAdvSettingsTab().openPreLoadDataMenuItem();
    module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl();
    module.eReg.navigation.getAdvSettingsTab().openExternalReviewMenuItem();
    module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl();
    module.eReg.navigation.getAdvSettingsTab().openIntegrationsMenuItem();
    module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl();
    module.eReg.navigation.getAdvSettingsTab().openCustomCodeMenuItem();
    module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl();
    module.eReg.navigation.getAdvSettingsTab().openGoogleAnalyticsMenuItem();
    module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl();
    module.eReg.navigation.getAdvSettingsTab().openExportSettingsMenuItem();
    module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl();
    module.eReg.navigation.openLookAndFeelTab();
    module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl();
    module.eReg.navigation.getLookAndFeelTab().openHeaderAndFootersMenuItem();
    module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl();
    module.eReg.navigation.getLookAndFeelTab().openThemeEditorMenuItem();
    module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl();
  }

  // @Test
  // @Category({SmokeTestsForLinks.class})
  // public void verify_Ereg_Settings_SubMenu() throws Exception {
  // sideBar.eventsPage.createEventWithRandomNameInDefaultAccount();
  // generalPage.activateModulePage.openEregModule();
  // generalPage.activateModulePage.clickUseStandardSettingsLink();
  // mainNavigation.getERegSubmenu().openERegSettingsPage();
  // module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl(baseURLDomain);
  // mainNavigation.getERegSubmenu().openAdminRegistrationPage();
  // module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl(baseURLDomain);
  // module.eReg.publicSite.eventWelcomePage.closePage();
  // mainNavigation.getERegSubmenu().openPublicRegistraionPage();
  // module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl(baseURLDomain);
  // module.eReg.publicSite.eventWelcomePage.closePage();
  // mainNavigation.getERegSubmenu().openReportsAndFunctionsPage();
  // module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl(baseURLDomain);
  // mainNavigation.getERegSubmenu().openRegistrantListPage();
  // module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl(baseURLDomain);
  // mainNavigation.getERegSubmenu().openSummaryReportPage();
  // module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl(baseURLDomain);
  // mainNavigation.getERegSubmenu().openHotelReportPage();
  // module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl(baseURLDomain);
  // mainNavigation.getERegSubmenu().openTravelReportPage();
  // module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl(baseURLDomain);
  // }
  //
  // // @Test
  // @Category({SmokeTestsForLinks.class})
  // public void verify_Project_Settings_SubMenu() {
  // sideBar.eventsPage.createEventWithRandomNameInDefaultAccount();
  // generalPage.activateModulePage.openEregModule();
  // generalPage.activateModulePage.clickUseStandardSettingsLink();
  // mainNavigation.getEProjectSubmenu().openEProjectSettingsPage();
  // module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl(baseURLDomain);
  // mainNavigation.getEProjectSubmenu().openProjectTimelinePage();
  // module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl(baseURLDomain);
  // mainNavigation.getEProjectSubmenu().openReportsAndFunctionsPage();
  // module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl(baseURLDomain);
  // }
  //
  // // @Test
  // @Category({SmokeTestsForLinks.class})
  // public void verify_EHome_SubMenu() throws Exception {
  // sideBar.eventsPage.createEventWithRandomNameInDefaultAccount();
  // generalPage.activateModulePage.openEregModule();
  // generalPage.activateModulePage.clickUseStandardSettingsLink();
  // mainNavigation.getEHomeSubmenu().openWebsiteSettingsPage();
  // module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl(baseURLDomain);
  // mainNavigation.getEHomeSubmenu().openVisitSitePage();
  // module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl(baseURLDomain);
  //
  // // Onsite(EMobile)
  // mainNavigation.clickEMobile();
  // module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl(baseURLDomain);
  // }
  //
  // // @Test
  // @Category({SmokeTestsForLinks.class})
  // public void verify_EScheduler_SubMenu() throws Exception {
  // sideBar.eventsPage.createEventWithRandomNameInDefaultAccount();
  // generalPage.activateModulePage.openEregModule();
  // generalPage.activateModulePage.clickUseStandardSettingsLink();
  // mainNavigation.getESchedulerSubmenu().openESchedulerSettingsPage();
  // module.eScheduler.navigation.getInventoryTabNavigation();
  // module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl(baseURLDomain);
  // module.eScheduler.navigation.openEventDetailsTab();
  // module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl(baseURLDomain);
  // module.eScheduler.navigation.openGroupsTab();
  // module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl(baseURLDomain);
  // module.eScheduler.navigation.openScheduleUsersTab();
  // module.eReg.genericPageSteps.shouldRedirectedToSameDomainUrl(baseURLDomain);
  //
  // mainNavigation.getESchedulerSubmenu().openViewEditSchedulePage();
  // mainNavigation.getESchedulerSubmenu().openReportsAndFunctions();
  // }

}
