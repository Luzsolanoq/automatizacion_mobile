
package com.nttdata.screens;

import com.nttdata.driver.AppCapabilities;
import com.nttdata.driver.AppiumDriverManager;

public class AppConfigScreen {

    private final AppiumDriverManager driverManager =
            AppiumDriverManager.getInstance();

    /**
     * Inicia la aplicación utilizando su archivo de configuración.
     */
    public void iniciarAplicacion(String archivoConfiguracion) {

        System.out.println(
                "[AppConfigScreen] Iniciando aplicación: " + archivoConfiguracion
        );

        AppCapabilities capabilities =
                AppCapabilities.from(archivoConfiguracion);

        driverManager.startApp(capabilities);
    }

    /**
     * Cierra la aplicación y finaliza la sesión de Appium.
     */
    public void cerrarAplicacion(String appId) {

        System.out.println(
                "[AppConfigScreen] Solicitando cierre de aplicación: " + appId
        );

        driverManager.stopApp(appId);
    }

    /**
     * Captura una evidencia de la pantalla actual.
     */
    public byte[] tomarScreenshot() {

        System.out.println("[AppConfigScreen] Tomando screenshot...");

        return driverManager.takeScreenshot();
    }
}
