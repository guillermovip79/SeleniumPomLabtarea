package com.demoblaze.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends BasePage {

    private By botonAgregarCarrito = By.cssSelector("a.btn-success");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void clickAddToCart() {
        esperarElementoClickeable(botonAgregarCarrito).click();
    }

    public void aceptarAlertaConfirmacion() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }
}