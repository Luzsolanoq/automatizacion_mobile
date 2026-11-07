package com.nttdata.stepsdefinitions;

import com.nttdata.screens.SauceLabsShoppingScreen;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import com.nttdata.support.ScreenshotAttacher;

import static org.junit.Assert.assertTrue;

public class SauceLabsShoppingStepDefs {

    private final SauceLabsShoppingScreen shoppingScreen =
            new SauceLabsShoppingScreen();

    private String productoActual;
    private int unidadesActuales;

    @And("valido que carguen correctamente los productos en la galería")
    public void validoProductosEnGaleria() {
        assertTrue(
                "No se muestran los productos en la galería",
                shoppingScreen.validarCatalogoProductos()
        );

        ScreenshotAttacher.attach("Catálogo de productos validado");
    }

    @When("agrego {int} del siguiente producto {string}")
    public void agregoProductoAlCarrito(int unidades, String nombreProducto) {

        productoActual = nombreProducto;
        unidadesActuales = unidades;

        try {
            shoppingScreen.seleccionarProducto(nombreProducto);

            ScreenshotAttacher.attach(
                    "Producto seleccionado: " + nombreProducto
            );

            assertTrue(
                    "No se muestra el detalle del producto: " + nombreProducto,
                    shoppingScreen.validarDetalleProducto(nombreProducto)
            );

        } catch (RuntimeException e) {

            throw new AssertionError(
                    "ERROR AL ABRIR EL PRODUCTO: No fue posible acceder "
                            + "al detalle de '" + nombreProducto
                            + "'. La aplicación no respondió como se esperaba. "
                            + "Causa técnica: " + e.getClass().getSimpleName(),
                    e
            );
        }

        ScreenshotAttacher.attach("Detalle del producto: " + nombreProducto);

        shoppingScreen.aumentarCantidad(unidades);
        ScreenshotAttacher.attach("Cantidad seleccionada: " + unidades);

        shoppingScreen.agregarAlCarrito();
        ScreenshotAttacher.attach("Producto agregado al carrito");

        shoppingScreen.abrirCarrito();
        ScreenshotAttacher.attach("Carrito de compras abierto");
    }

    @Then("valido que el carrito de compra actualice correctamente")
    public void validoActualizacionDelCarrito() {

        shoppingScreen.validarProductoEnCarrito(
                productoActual,
                unidadesActuales
        );
    }
}