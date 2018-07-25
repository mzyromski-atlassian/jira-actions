package com.atlassian.jira.test.performance.actions.page.form

import com.atlassian.jira.test.performance.actions.page.wait
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable

class TextInput(
    private val driver: WebDriver,
    private val input: WebElement
) : FormField {

    override fun fillWithAnyValue() {
        driver.wait(
            elementToBeClickable(input)
        ).click()
        input.sendKeys("Lorem ipsum")
    }

    override fun hasValue(): Boolean {
        return input
            .getAttribute("value")
            .isNullOrBlank()
            .not()
    }

    class Descriptor : FromFieldType {
        override fun isTypeOf(input: WebElement?): Boolean {
            return input != null && input.getAttribute("role") == null && input.tagName == "input"
        }

        override fun create(driver: WebDriver, fieldGroup: WebElement, input: WebElement): FormField {
            return TextInput(driver, input)
        }
    }
}