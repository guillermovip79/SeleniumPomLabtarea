package com.demoblaze.steps;

import com.demoblaze.pages.CartPage;
import com.demoblaze.pages.CatalogoPage;
import com.demoblaze.pages.ProductPage;
import com.demoblaze.utils.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddToCartSteps {

    private WebDriver driver;
    private CatalogoPage catalogoPage;
    private ProductPage productPage;
    private CartPage cartPage;

    // Método de soporte para capturar pantalla y adjuntarla directamente a Allure
    private void tomarCaptura(String nombreEvidencia) {
        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(nombreEvidencia, new ByteArrayInputStream(screenshot));
        } catch (Exception e) {
            System.err.println("Error al generar captura de evidencia: " + e.getMessage());
        }
    }

    @Given("que el usuario navega a la página de inicio de Demoblaze")
    public void queElUsuarioNavegaALaPaginaDeInicioDeDemoblaze() {
        Allure.step("Abrir el sitio web de DemoBlaze", () -> {
            driver = DriverFactory.getDriver();
            catalogoPage = new CatalogoPage(driver);
            productPage = new ProductPage(driver);
            cartPage = new CartPage(driver);
            
            catalogoPage.navegarAlCatalogo();
            tomarCaptura("Página de inicio cargada");
        });
    }

    @When("selecciona el primer producto de la lista")
    public void seleccionaElPrimerProductoDeLaLista() {
        Allure.step("Seleccionar el primer producto de las tarjetas", () -> {
            catalogoPage.clickPrimerProducto();
            tomarCaptura("Detalle del producto visible");
        });
    }

    @And("agrega el producto al carrito aceptando la alerta de confirmación")
    public void agregaElProductoAlCarritoAceptandoLaAlertaDeConfirmacion() {
        Allure.step("Hacer clic en 'Add to cart' y gestionar alerta", () -> {
            productPage.clickAddToCart();
            productPage.aceptarAlertaConfirmacion();
        });
    }

    @And("se dirige al carrito de compras")
    public void seDirigeAlCarritoDeCompras() {
        Allure.step("Acceder a la sección del carrito", () -> {
            cartPage.navigateToCart();
            tomarCaptura("Contenido de la tabla del carrito");
        });
    }

    @And("procede a realizar el pedido completando el formulario de compra")
    public void procedeARealizarElPedidoCompletandoElFormularioDeCompra() {
        Allure.step("Abrir pasarela y rellenar campos de compra", () -> {
            cartPage.abrirFormularioPedido();
            
            // Adjunto de texto con los datos de prueba tal como el script de referencia
            String datosComprador = "Nombre: Guillermo Viniegra\nPaís: Mexico\nCiudad: CDMX";
            Allure.addAttachment("Datos del Comprador", "text/plain", datosComprador);
            
            cartPage.completarFormulario(
                "Guillermo Viniegra", "Mexico", "CDMX", "1234567890123456", "12", "2028"
            );
            
            tomarCaptura("Formulario completado");
            cartPage.finalizarCompra();
        });
    }

    @Then("la orden se procesa y se muestra el mensaje {string}")
    public void laOrdenSeProcesaYSeMuestraElMensaje(String mensajeEsperado) {
        Allure.step("Validar mensaje de éxito en el modal", () -> {
            String mensajeObtenido = cartPage.obtenerMensajeConfirmacion();
            tomarCaptura("Orden completada con éxito");
            
            assertEquals(mensajeEsperado, mensajeObtenido, "El mensaje de confirmación de compra no coincide.");
            cartPage.cerrarModalExito();
        });
    }

    @After
    public void cerrarNavegador() {
        DriverFactory.quitDriver();
    }
}