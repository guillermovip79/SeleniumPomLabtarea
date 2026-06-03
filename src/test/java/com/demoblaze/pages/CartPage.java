package com.demoblaze.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    private By linkCarrito = By.id("cartur");
    private By botonProcederPedido = By.cssSelector("button[data-target='#orderModal']");
    
    // Formulario de Checkout (Modal)
    private By inputNombre = By.id("name");
    private By inputPais = By.id("country");
    private By inputCiudad = By.id("city");
    private By inputTarjeta = By.id("card");
    private By inputMes = By.id("month");
    private By inputAnio = By.id("year");
    private By botonPurchase = By.xpath("//button[text()='Purchase']");
    
    // Mensajes de éxito y confirmación
    private By mensajeExito = By.xpath("//h2[text()='Thank you for your purchase!']");
    private By botonOkExito = By.xpath("//button[text()='OK']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToCart() {
        esperarElementoClickeable(linkCarrito).click();
        // Espera explícita para asegurar la carga de la tabla del carrito
        esperarElementoVisible(By.className("success"));
    }

    public void abrirFormularioPedido() {
        esperarElementoClickeable(botonProcederPedido).click();
    }

    public void completarFormulario(String nombre, String pais, String ciudad, String tarjeta, String mes, String anio) {
        esperarElementoVisible(inputNombre).sendKeys(nombre);
        driver.findElement(inputPais).sendKeys(pais);
        driver.findElement(inputCiudad).sendKeys(ciudad);
        driver.findElement(inputTarjeta).sendKeys(tarjeta);
        driver.findElement(inputMes).sendKeys(mes);
        driver.findElement(inputAnio).sendKeys(anio);
    }

    public void finalizarCompra() {
        esperarElementoClickeable(botonPurchase).click();
    }

    public String obtenerMensajeConfirmacion() {
        return esperarElementoVisible(mensajeExito).getText().trim();
    }

    public void cerrarModalExito() {
        esperarElementoClickeable(botonOkExito).click();
    }
}