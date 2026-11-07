package com.nttdata.screens;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SauceLabsShoppingScreen extends BaseScreen {

    private By producto(String nombre) {
        return AppiumBy.accessibilityId(nombre);
    }

    private final By tituloProducto =
            AppiumBy.id("com.saucelabs.mydemoapp.android:id/productTV");

    private final By cantidadProducto =
            AppiumBy.id("com.saucelabs.mydemoapp.android:id/noTV");

    private final By botonAumentar =
            AppiumBy.id("com.saucelabs.mydemoapp.android:id/plusIV");

    private final By botonAgregar =
            AppiumBy.id("com.saucelabs.mydemoapp.android:id/cartBt");

    private final By botonCarrito =
            AppiumBy.id("com.saucelabs.mydemoapp.android:id/cartRL");

    private final By tituloCarrito =
            AppiumBy.id("com.saucelabs.mydemoapp.android:id/productTV");

    private final By nombreProductoCarrito =
            AppiumBy.id("com.saucelabs.mydemoapp.android:id/titleTV");

    private final By cantidadCarrito =
            AppiumBy.id("com.saucelabs.mydemoapp.android:id/noTV");

    private final By carritoVacio =
            AppiumBy.id("com.saucelabs.mydemoapp.android:id/noItemTitleTV");

    private final By catalogoProductos =
            AppiumBy.id("com.saucelabs.mydemoapp.android:id/productRV");

    public void seleccionarProducto(String nombre) {
        WebElement elemento = waitForVisible(producto(nombre));
        elemento.click();
    }

    public boolean validarDetalleProducto(String nombre) {

        System.out.println("[ShoppingScreen] Validando detalle del producto: " + nombre);

        WebDriverWait wait = new WebDriverWait(driver(), Duration.ofSeconds(20));

        return wait.until(
                ExpectedConditions.textToBePresentInElementLocated(
                        tituloProducto,
                        nombre
                )
        );
    }

    public int obtenerCantidad() {
        return Integer.parseInt(waitForVisible(cantidadProducto).getText());
    }

    public void aumentarCantidad(int unidades) {
        for (int i = 1; i < unidades; i++) {
            waitForVisible(botonAumentar).click();
        }
    }

    public void agregarAlCarrito() {
        waitForVisible(botonAgregar).click();
    }

    public void abrirCarrito() {
        waitForVisible(botonCarrito).click();

        WebDriverWait wait = new WebDriverWait(driver(), Duration.ofSeconds(20));

        wait.until(
                ExpectedConditions.textToBePresentInElementLocated(
                        tituloCarrito,
                        "My Cart"
                )
        );
    }

    public void validarProductoEnCarrito(String nombre, int cantidadEsperada) {

        String nombreEncontrado = waitForVisible(nombreProductoCarrito).getText();

        int cantidadEncontrada = Integer.parseInt(
                waitForVisible(cantidadCarrito).getText()
        );

        System.out.println("========== VALIDACIÓN DEL CARRITO ==========");
        System.out.println("Producto esperado: " + nombre);
        System.out.println("Producto encontrado: " + nombreEncontrado);
        System.out.println("Cantidad esperada: " + cantidadEsperada);
        System.out.println("Cantidad encontrada: " + cantidadEncontrada);

        if (!nombreEncontrado.equals(nombre)) {
            throw new AssertionError(
                    "[ERROR FUNCIONAL] Producto incorrecto en el carrito. "
                            + "Se esperaba: '" + nombre
                            + "', pero se encontró: '" + nombreEncontrado + "'."
            );
        }

        if (cantidadEncontrada != cantidadEsperada) {
            throw new AssertionError(
                    "[ERROR FUNCIONAL] Cantidad incorrecta en el carrito para '"
                            + nombre + "'. Se esperaban " + cantidadEsperada
                            + " unidades, pero se encontraron "
                            + cantidadEncontrada + "."
            );
        }

        System.out.println("[OK] Producto y cantidad validados correctamente.");
        System.out.println("============================================");
    }

    public boolean validarCatalogoProductos() {
        return waitForVisible(catalogoProductos).isDisplayed();
    }

}
