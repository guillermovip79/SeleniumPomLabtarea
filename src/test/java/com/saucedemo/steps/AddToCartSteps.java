package com.saucedemo.steps;

import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.ProductPage;
import com.saucedemo.utils.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.*;

public class AddToCartSteps {

    private static final String BASE_URL = "https://sauce-demo.myshopify.com/products/";

    private WebDriver driver;
    private ProductPage productPage;
    private CartPage cartPage;

    @Given("que estoy en la pagina del producto {string}")
    public void queEstoyEnLaPaginaDelProducto(String nombreProducto) {
        driver = DriverFactory.getDriver();
        // Convierte "Grey jacket" -> "grey-jacket"
        String slug = nombreProducto.toLowerCase().replace(" ", "-");
        driver.get(BASE_URL + slug);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
    }

    @Then("el titulo del producto debe ser {string}")
    public void elTituloDelProductoDebeSer(String tituloEsperado) {
        String titulo = productPage.getProductTitle();
        assertEquals(tituloEsperado, titulo,
            "El titulo del producto no coincide");
    }

    @And("el precio del producto debe ser {string}")
    public void elPrecioDelProductoDebeSer(String precioEsperado) {
        String precio = productPage.getProductPrice();
        assertEquals(precioEsperado, precio,
            "El precio del producto no coincide");
    }

    @When("agrego el producto al carrito")
    public void agregoElProductoAlCarrito() {
        productPage.clickAddToCart();
    }

    @Then("el producto {string} debe aparecer en el carrito")
    public void elProductoDebeAparecerEnElCarrito(String nombreProducto) {
        cartPage.navigateToCart();
        boolean estaEnCarrito = cartPage.isProductInCart(nombreProducto);
        assertTrue(estaEnCarrito,
            "El producto '" + nombreProducto + "' no se encontro en el carrito");
    }

    @After
    public void cerrarNavegador() {
        DriverFactory.quitDriver();
    }
}
