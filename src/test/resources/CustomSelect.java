package com.etouches.decorator;

import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Quotes;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;

public class CustomSelect {


  private final WebElement element;
  private final boolean isMulti;

  public CustomSelect(WebElement element) {
    String tagName = element.getTagName();
    if (null != tagName && "select".equals(tagName.toLowerCase())) {
      this.element = element;
      String value = element.getAttribute("multiple");
      this.isMulti = value != null && !"false".equals(value);
    } else {
      throw new UnexpectedTagNameException("select", tagName);
    }
  }

  public boolean isMultiple() {
    return this.isMulti;
  }

  private void setSelected(WebElement option, boolean select) {
    boolean isSelected = option.isSelected();
    if (!isSelected && select || isSelected && !select) {
      option.click();
    }
  }

  // Method to select option with containing text
  public void selectByPartialVisibleText(String text) {
    List<WebElement> options = this.element.findElements(
        By.xpath(".//option[contains(normalize-space(.) , " + Quotes.escape(text) + ")]"));
    boolean matched = false;
    for (Iterator<WebElement> subStringWithoutSpace = options.iterator(); subStringWithoutSpace
        .hasNext(); matched = true) {
      WebElement candidates = (WebElement) subStringWithoutSpace.next();
      this.setSelected(candidates, true);
      if (!this.isMultiple()) {
        return;
      }
    }
    if (!matched) {
      throw new NoSuchElementException("Cannot locate element with text: " + text);
    }

  }

}
