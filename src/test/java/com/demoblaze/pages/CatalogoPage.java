package com.demoblaze.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CatalogoPage extends BasePage {

    private static final String URL_CATALOGO = "https://www.demoblaze.com/";
    private By primerProducto = By.cssSelector(".card-title a");

    public CatalogoPage(WebDriver driver) {
        super(driver);
    }

    public void navegarAlCatalogo() {
        driver.get(URL_CATALOGO);
    }

    public void clickPrimerProducto() {
        esperarElementoClickeable(primerProducto).click();
    }
}