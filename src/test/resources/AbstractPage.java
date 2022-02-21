package com.etouches.decorator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import com.etouches.entities.storages.WindowsStorage;
import com.etouches.util.PropertyLoader;
import com.google.common.base.Predicate;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;

public class AbstractPage extends PageObject {

  private static final Logger log = LoggerFactory.getLogger(AbstractPage.class);

  public static final String AVENTRI_MAIN_PAGE_TITLE = "Aventri";
  public static final int WAIT_FOR_TIMEOUT = 10;
  protected static final String BASE_INPUT_FIELD =
      "//*[text()=normalize-space('%s')]/ancestor::tr[1]//input[not (@type='hidden')]";
  protected static final String BASE_DROPDOWN =
      "//*[contains(text(),normalize-space('%s'))]/ancestor::tr[1]//select[not (@type='hidden')]";
  private static final int DEFAULT_WAIT = 20;
  private static final String JS_GET_VALUE = "return arguments[0].value";
  private static final String JS_SCROLL_TO_ELEMENT = "arguments[0].scrollIntoView(true);";
  private static final String JS_SET_ATTRIBUTE = "arguments[0].setAttribute('%s', '%s')";
  private static final String JS_SET_FOCUS_OUT = "arguments[0].blur();";
  private static final String JS_SCROLL_TO_TOP = "window.scrollTo(0, 0);";
  private static final String INNER1_FRAME = "innerframe1";
  private static final String INNER2_FRAME = "innerframe2";
  private static final String REPORT_FRAME = "report";
  private static final String EDITOR_FRAME = "editor";
  private static final String URL_FRAME = "url";
  private static final String HEADERS2_FRAME = "headers2";
  private static final String ROOMS_FRAME = "rooms";
  private static final String MANAGE_SCHEDULE_FRAME = "manageschedule";
  private static final String WIZARD_FRAME = "//div[@id='module_container']/iframe";
  private static final String TIMELINE_FRAME = "timeline";
  private static final String SURVERY_BUILDER_FRAME = "survey_builder";
  private static final String PREVIEW_FRAME = "preview";

  @FindBy(id = "eventLocationName")
  private WebElement eventNameTxt;

  private static PropertyLoader propertyLoader = new PropertyLoader();


  public void shoulseePageTitle(String actual, String url) {
    Assert.assertEquals("Aventri", actual, "Title vefification FAIL");
  }

  public static void waitForAjaxComplete(WebDriver driver) {
    String pageLoadStatus = null;
    do {
      log.info("Waiting for Ajax call to complete");
      JavascriptExecutor js = (JavascriptExecutor) driver;
      pageLoadStatus = (String) js.executeScript("return document.readyState");
    } while (!pageLoadStatus.equals("complete"));
  }
  
   public void waitForPageToLoad(WebDriver driver) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(pageLoadCondition);
  }

  public static boolean isElementHiddenNow(WebDriver driver, By elementLocator) {
    turnOffImplicitWaits(driver);
    boolean result = ExpectedConditions.invisibilityOfElementLocated(elementLocator).apply(driver);
    turnOnImplicitWaits(driver);
    return result;
  }

  private static void turnOffImplicitWaits(WebDriver driver) {
    driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
  }

  private static void turnOnImplicitWaits(WebDriver driver) {
    driver.manage().timeouts().implicitlyWait(
        new Long(propertyLoader.getProperty("webdriver.timeouts.implicitlywait")),
        TimeUnit.MILLISECONDS);
  }

  public String getElementTextWithJS(WebElement element) {
    return evaluateJavascript(JS_GET_VALUE, element).toString();
  }

  public AbstractPage setElementFocusOut(WebElement element) {
    ((JavascriptExecutor) getDriver()).executeScript(JS_SET_FOCUS_OUT, element);
    return this;
  }

  public void setAttribute(WebElement element, String key, String value) {
    ((JavascriptExecutor) getDriver()).executeScript(String.format(JS_SET_ATTRIBUTE, key, value),
        element);
  }

  public void scrollIntoView(WebElement element) {
    ((JavascriptExecutor) getDriver()).executeScript(JS_SCROLL_TO_ELEMENT, element);
    log.info("----------Scrolled element into view-----------");
  }

  public void scrollIntoCenterView(WebElement element) {
    Point p = element.getLocation();
    ((JavascriptExecutor) getDriver()).executeScript(
        String.format("window.scroll(%s,%s - (window.innerHeight / 2));", p.getX(), p.getY()));
    log.info("----------Scrolled element into center view-----------");
  }

  public void scrollToTop() {
    ((JavascriptExecutor) getDriver()).executeScript(JS_SCROLL_TO_TOP);
    log.info("----------Scrolled page to top-----------");
  }

  public void scrollToEventName() {
    scrollIntoView(eventNameTxt);
    log.info("----------Scrolled Page To Event Text-----------");
  }

  public void moveCursorOutOfElement(WebElement element) {
    Actions actions = new Actions(getDriver());
    actions.moveByOffset(element.getLocation().getX(), element.getLocation().getY() + 400).build()
        .perform();
  }

  public void switchToFrame(final String frame) {
    getDriver().switchTo().frame(frame);
    log.info("Switched to: " + frame);
  }

  public void switchToWindow(final String window) {
    getDriver().switchTo().window(window);
    log.info("Switched to: " + window);
  }

  public void switchToFrame(final int frame) {
    getDriver().switchTo().frame(frame);
    log.info("Switched to: " + frame);
  }

  public void switchToFrame(final WebElement frame) {
    getDriver().switchTo().frame(frame);
    log.info("Switched to: " + frame);
  }

  public void switchToDefaultContent() {
    getDriver().switchTo().defaultContent();
    log.info("Switched to Default Content");
  }

  public void confirmJSPopup() {
    getDriver().switchTo().alert().accept();
  }

  public boolean isAlertPresent() {
    try {
      getDriver().switchTo().alert();
      return true;
    } catch (NoAlertPresentException Ex) {
      return false;
    }
  }

  public String clickOnElementAndWaitForPopUp(WebElement elementToClick, long timeOutInSeconds) {
    String newWindowHandle;
    final Set<String> openedWindows = getDriver().getWindowHandles();
    if (openedWindows.size() == 1) {
      WindowsStorage.addWindow(getDriver().getWindowHandle());
    }
    clickWithJS(elementToClick);
    log.info("----------Clicked on Event URL----------");
    newWindowHandle = new WebDriverWait(getDriver(), timeOutInSeconds)
        .until(newWindowHandleIsPresent(openedWindows));
    WindowsStorage.addWindow(newWindowHandle);
    return newWindowHandle;
  }

  public String clickOnElementAndWaitForPopUp(String frame, String xpathOrCssSelector,
      long timeOutInSeconds) {
    String newWindowHandle;
    final Set<String> openedWindows = getDriver().getWindowHandles();
    if (openedWindows.size() == 1) {
      WindowsStorage.addWindow(getDriver().getWindowHandle());
    }
    switchToFrame(frame);
    $(xpathOrCssSelector).click();
    newWindowHandle = new WebDriverWait(getDriver(), timeOutInSeconds)
        .until(newWindowHandleIsPresent(openedWindows));
    WindowsStorage.addWindow(newWindowHandle);
    return newWindowHandle;
  }

  public String openNewWindow(long timeOutInSeconds) {
    final Set<String> openedWindows = getDriver().getWindowHandles();
    ((JavascriptExecutor) getDriver()).executeScript("window.open()");
    return new WebDriverWait(getDriver(), timeOutInSeconds)
        .until(newWindowHandleIsPresent(openedWindows));
  }

  public void waitForWindowsOpened(final int windowsAmount, long timeOutInSeconds) {
    new WebDriverWait(getDriver(), timeOutInSeconds).until(openedWindowsAmount(windowsAmount));
  }

  public void waitForNonEmptyList(final String locator, long timeOutInSeconds) {
    new WebDriverWait(getDriver(), timeOutInSeconds).until(isListNonEmpty(locator));
  }

  public void waitForElementEnabled(final String locator, long timeOutInSeconds) {
    new WebDriverWait(getDriver(), timeOutInSeconds).until(isElementEnabled(locator));
  }

  public boolean currentUrlContains(String url) {
    String currentUrl = getDriver().getCurrentUrl();
    return currentUrl != null && currentUrl.contains(url);
  }

  private ExpectedCondition<Integer> openedWindowsAmount(final int windowsAmount) {
    return new ExpectedCondition<Integer>() {
      @Override
      public Integer apply(WebDriver input) {
        int openedWindowsAmount = getDriver().getWindowHandles().size();
        if (openedWindowsAmount == windowsAmount) {
          return openedWindowsAmount;
        }
        return null;
      }
    };
  }

  private ExpectedCondition<String> newWindowHandleIsPresent(
      final Set<String> currentWindowHandles) {
    return new ExpectedCondition<String>() {
      @Override
      public String apply(WebDriver input) {
        Iterator<String> iterator = getDriver().getWindowHandles().iterator();
        while (iterator.hasNext()) {
          String next = iterator.next();
          if (!currentWindowHandles.contains(next)) {
            return next;
          }
        }
        return null;
      }
    };
  }

  private ExpectedCondition<Boolean> isListNonEmpty(final String locator) {
    return new ExpectedCondition<Boolean>() {
      @Override
      public Boolean apply(WebDriver webDriver) {
        return $$(locator).size() > 0;
      }
    };
  }

  private ExpectedCondition<Boolean> isElementEnabled(final String locator) {
    return new ExpectedCondition<Boolean>() {
      @Override
      public Boolean apply(WebDriver webDriver) {
        return getDriver().findElement(By.xpath(locator)).isEnabled();
      }
    };
  }

  public boolean isElementDisplayed(String locator) {
    try {
      getDriver().findElement(By.xpath(locator));
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  public boolean isElementDisplayedBySize(String locator) {
    getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
    boolean exist = $$(locator).size() > 0;
    getDriver().manage().timeouts().implicitlyWait(DEFAULT_WAIT, TimeUnit.MILLISECONDS);
    return exist;
  }

  public Actions useActions() {
    return new Actions(getDriver());
  }

  public void clickWithJS(WebElement element) {
    evaluateJavascript("arguments[0].click();", element);
    log.info("Clicked on " + element + " with Javascript");
  }

  public String getHtmlSource(WebElement element) {
    return $(element).getAttribute("innerHTML");
  }

  public List<WebElement> $$(String xpath) {
    return getDriver().findElements(By.xpath(xpath));
  }

  public int getElementAmount(String xpath) {
    int timeout = (int) implicitTimoutMilliseconds();
    setImplicitTimeout(0, TimeUnit.MILLISECONDS);
    int size = $$(xpath).size();
    setImplicitTimeout(timeout, TimeUnit.MILLISECONDS);
    return size;
  }

  protected void closeActivePageAndSwitchToMainPage() {
    getDriver().close();
    for (String winHandle : getDriver().getWindowHandles()) {
      if (getDriver().switchTo().window(winHandle).getTitle().equals(AVENTRI_MAIN_PAGE_TITLE)) {
        break;
      }
    }
    WindowsStorage.clear();
  }

  public void shouldSeeWindowWithTitle(String title) {
    boolean isWindowWithTitleExist = false;
    for (String winHandle : getDriver().getWindowHandles()) {
      if (getDriver().switchTo().window(winHandle).getTitle().contains(title)) {
        isWindowWithTitleExist = true;
      }
    }
    Assert.assertTrue(isWindowWithTitleExist, "There are no window with title " + title);
  }

  protected void closeActivePageAndSwitchToPreviousPage() {
    getDriver().close();
    WindowsStorage.removeActiveWindow();
    getDriver().switchTo().window(WindowsStorage.getActiveWindow());
  }

  public void clearField(String fieldName) {
    $(String.format(BASE_INPUT_FIELD, fieldName)).clear();
  }

  public void fillInField(String fieldName, String text) {
    scrollIntoCenterView($(String.format(BASE_INPUT_FIELD, fieldName)));
    $(String.format(BASE_INPUT_FIELD, fieldName)).type(text);
  }

  public void clickElem(String elementName) {
    scrollIntoCenterView($(String.format(BASE_INPUT_FIELD, elementName)));
    $(String.format(BASE_INPUT_FIELD, elementName)).click();
  }

  public boolean isElementSelected(String elementName) {
    scrollIntoCenterView($(String.format(BASE_INPUT_FIELD, elementName)));
    return $(String.format(BASE_INPUT_FIELD, elementName)).isSelected();
  }

  public boolean isElementSelected(WebElement elementName) {
    scrollIntoCenterView($(elementName));
    return $(elementName).isSelected();
  }

  public void selectDropdownValueByText(String elementName, String text) {
    scrollIntoCenterView($(String.format(BASE_DROPDOWN, elementName)));
    $(String.format(BASE_DROPDOWN, elementName)).selectByVisibleText(text);
  }

  public void switchToId0Frame() {
    switchToFrame(0);
  }

  public void switchToInner1Frame() {
    switchToFrame(INNER1_FRAME);
  }

  public void switchToInner2Frame() {
    switchToFrame(INNER2_FRAME);
  }

  public void switchToReportFrame() {
    switchToFrame(REPORT_FRAME);
  }

  public void switchToEditorFrame() {
    switchToFrame(EDITOR_FRAME);
  }

  public void switchToUrlFrame() {
    switchToFrame(URL_FRAME);
  }

  public void switchToHeaders2Frame() {
    switchToFrame(HEADERS2_FRAME);
  }

  public void switchToRoomsFrame() {
    switchToFrame(ROOMS_FRAME);
  }

  public void switchToManageScheduleFrame() {
    switchToFrame(MANAGE_SCHEDULE_FRAME);
  }

  public void switchToWizardIFrame() {
    switchToFrame((WebElement) $(WIZARD_FRAME));
  }

  public void switchToTimelineFrame() {
    switchToFrame(TIMELINE_FRAME);
  }

  public void switchToSurveyFrame() {
    switchToFrame(SURVERY_BUILDER_FRAME);
  }

  public void switchToPreviewFrame() {
    switchToFrame(PREVIEW_FRAME);
  }

  public void waitForTimeOutInSec(int waitTime) {
    try {
      Thread.sleep(waitTime * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void waitForInnerFrame1PopUpToLoad() {
    waitForTimeOutInSec(1);
    if (containsElements(By.id("innerframe1"))) {
      log.info("InnerFrame1 pop up is present, trying to fetch the style attribute");
      while (true) {
        if (element(By.id("innerframe1")).getAttribute("style").contains("display: none")) {
          log.info("---------------Waiting For InnerFrame1 pop-up to load---------------");
          break;
        }
      }
      log.info("InnerFrame1 pop up is loaded successfully");
    }
  }

  public void waitForInnerFrame1PopUpToDisappear() {
    waitForTimeOutInSec(1);
    if (containsElements(By.id("innerframe1"))) {
      while (true) {
        if (!element(By.id("innerframe1")).getAttribute("style").contains("display: none")) {
          log.info("---------------Waiting For InnerFrame1 pop-up to disappear---------------");
          break;
        }
      }
      log.info("InnerFrame1 pop up disappeared");
    }
    log.info("Frame is not present");
  }

  public void waitForPopUpToDisappear() {
    if (containsElements(By.xpath("//*[@id='overlay' or @id='outerdiv1' or @id='innerframe1']"))) {
      while (true) {
        if (!(element(By.id("innerframe1")).getAttribute("style").contains("display: none")
            && element(By.id("outerdiv1")).getAttribute("style").contains("display: none")
            && element(By.id("overlay")).getAttribute("style").contains("display: none"))) {
          log.info("---------------Waiting For Pop Up To Disappear---------------");
          break;
        }
      }
      log.info("Pop Up Disappeared");
    }
    log.info("Pop Up is not present");
  }

  public void highlightElement(WebElement element) {
    JavascriptExecutor js = (JavascriptExecutor) getDriver();
    js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
        "color: yellow; border: 5px solid yellow;");
    waitForTimeOutInSec(4);
    js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
  }

  public void forcedScrollTopOfPage() {
    try {
      ((JavascriptExecutor) getDriver())
          .executeScript("$('html, body').animate({scrollTop: 0,scrollLeft:0},'fast');");
      log.info("Scrolled to top of the page in the current browser view ");
    } catch (Exception exception) {
      exception.getMessage();
    }

  }

  public void forcedScrollBottomOfPage() {
    try {
      ((JavascriptExecutor) getDriver()).executeScript(
          "$('html, body').animate({scrollTop: $(window).height(),scrollLeft:$(window).width()});;");
      log.info("Scrolled to top of the page in the current browser view ");
    } catch (Exception exception) {

      exception.getMessage();
    }
  }

  public String getPopUpTxtMsg() {
    return getDriver().switchTo().alert().getText().trim();
  }

  public boolean isInnerFrame1Present() {
    List<WebElement> iframes = getDriver().findElements(By.tagName("iframe"));
    if (iframes.isEmpty()) {
      log.info("No Frames Present");
      return false;
    } else {
      for (int i = 0; i < iframes.size(); i++) {
        if (iframes.get(i).getAttribute("id").contentEquals(INNER1_FRAME)
            || iframes.get(i).getAttribute("name").contentEquals(INNER1_FRAME)) {
          log.info(INNER1_FRAME + " is present");
          return true;
        }
      }
    }
    return false;
  }

  public void waitForLoaderInsideInnerFrame1() {
    waitFor(ExpectedConditions.invisibilityOfElementLocated(By.id("overlay")));
  }

  private void waitForPopUpToDisappear(int timeOut) {
    switchToDefaultContent();
    Calendar startTime = Calendar.getInstance();
    boolean isTimeoutReached = false;
    // String selector = "#overlay,#outerdiv1,#innerframe1";
    String loaders = "//*[@id='overlay' or @id='outerdiv1' or @id='innerframe1']";
    List<WebElement> loaderElements = getDriver().findElements(By.cssSelector(loaders));
    for (int i = 0, size = loaderElements.size(); i < size && !isTimeoutReached; i++) {
      log.info("Waiting for Loader To Disappear");
      waitForElementToDisappear(loaderElements.get(i), timeOut);
      isTimeoutReached = isTimeOutReached(startTime, timeOut);
    }
  }

  private void waitForPopUpToAppear(int timeOut) {
    switchToDefaultContent();
    Calendar startTime = Calendar.getInstance();
    boolean isTimeoutReached = false;
    // String selector = "#outerdiv1,#innerframe1,#overlay";
    String loaders = "//*[@id='overlay' or @id='outerdiv1' or @id='innerframe1']";
    List<WebElement> loaderElements;
    loaderElements = getDriver().findElements(By.xpath(loaders));
    for (int i = 0, size = loaderElements.size(); i < size && !isTimeoutReached; i++) {
      log.info("Waiting for Loader To Disappear");
      waitForElementToAppear(loaderElements.get(i), timeOut);
      // isTimeoutReached = isTimeOutReached(startTime, timeOut);
    }
  }

  private boolean isTimeOutReached(Calendar startTime, int timeOutPeriod) {
    Calendar currentTime = Calendar.getInstance();
    int timeDiff = (int) ((currentTime.getTimeInMillis() - startTime.getTimeInMillis()) / 1000);
    return timeDiff >= timeOutPeriod;
  }

  private void waitForElementToDisappear(final WebElement element, int timeOutPeriod) {
    FluentWait<WebElement> fluentWait = new FluentWait<WebElement>(element);
    fluentWait.pollingEvery(10, TimeUnit.MICROSECONDS);
    fluentWait.withTimeout(timeOutPeriod, TimeUnit.SECONDS);
    fluentWait.until(new Predicate<WebElement>() {
      public boolean apply(WebElement element) {
        try {
          return element.getAttribute("style").contains("display: none");
        } catch (NoSuchElementException ex) {
          return true;
        } catch (StaleElementReferenceException ex) {
          return true;
        }
      }
    });
  }

  private void waitForElementToAppear(final WebElement element, int timeOutPeriod) {
    FluentWait<WebElement> fluentWait = new FluentWait<WebElement>(element);
    fluentWait.pollingEvery(10, TimeUnit.MICROSECONDS);
    fluentWait.withTimeout(timeOutPeriod, TimeUnit.SECONDS);
    fluentWait.until(new Predicate<WebElement>() {
      public boolean apply(WebElement element) {
        try {
          return !element.getAttribute("style").contains("display: none");
        } catch (NoSuchElementException ex) {
          return true;
        } catch (StaleElementReferenceException ex) {
          return true;
        }
      }
    });
  }

  private void aSyncRequest() {
    try {
      int i = 0;
      int j = 0;
      String isJqueryPresent = null;

      JavascriptExecutor js = (JavascriptExecutor) getDriver();
      if (getDriver() != null) {
        log.info("Current Page Title : '" + getDriver().getTitle());
      } else {
        log.info("################## DRIVER IS NULL ##################");
      }

      long loadEventEnd = 0;
      long navigationStart = 0;
      navigationStart = (Long) js.executeScript("return performance.timing.navigationStart");
      try {
        log.info("||::waiting for pageload to complete::||");
        System.out.println("||::waiting for pageload to complete::||");
        while (loadEventEnd == 0 && j < 3000) {
          loadEventEnd = (Long) js.executeScript("return performance.timing.loadEventEnd");

          Thread.sleep(100);
          j = j + 1;
        }
        if (j > 3000) {
          log.info(
              "||::Page load was not completed in specified timeout of 300 seconds. Kindly refer attached screenshot for more clearification::||");
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      double pageLoadTime = loadEventEnd - navigationStart;

      try {
        try {
          isJqueryPresent = (String) js.executeScript("jQuery.noConflict;return jQuery.fn.jquery;");
        } catch (Exception e) {
          log.info("||::No jQuery available::||");
        }
        if (isJqueryPresent != null) {

          // frameworkServices.logMessage("||::waiting for ajax::||", logger);
          System.out.println("||::waiting for ajax::||");
          while (!js.executeScript("return window.parent.jQuery.active").toString()
              .equalsIgnoreCase("0") && i < 100)

          {
            Thread.sleep(100);
            i = i + 1;
          }

          pageLoadTime = (pageLoadTime + (i * 100));

          if (i > 1000) {
            log.info(
                "||::Asynchronized request (AJAX) was not completed in specified timeout of 100 seconds. Kindly refer attached screenshot for more clearification::||");
          }
        }
      } catch (Exception e) {
        log.info(
            "||::Asynchronized request (AJAX) was not completed in specified timeout of 100 seconds. Kindly refer attached screenshot for more clearification::||");
      }
      log.info("||::Time for '" + js.executeScript("return window.parent.document.title").toString()
          + "' page to be user interactive: " + pageLoadTime / 1000 + " Seconds::||");
      System.out.println(
          "||::Time for '" + js.executeScript("return window.parent.document.title").toString()
              + "' page to be user interactive: " + pageLoadTime / 1000 + " Seconds::||");
    } catch (Exception e) {
      log.info("Can not verify completion of aSync request. Error: " + e.getStackTrace());
      e.printStackTrace();
    }

  }
  
  public List<String> getAllLinks() {
	  List<WebElement> refList =getDriver().findElements(By.xpath("//ul[@class='accordion']//descendant::a"));
		 List<String> links = new ArrayList<>();
		 for(WebElement link: refList) {
			 links.add(link.getAttribute("href"));
		 }
		return links;
  }
}
