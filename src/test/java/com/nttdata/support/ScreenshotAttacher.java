package com.nttdata.support;

import com.nttdata.steps.AppConfigSteps;
import io.cucumber.java.Scenario;

/**
 * Utilidad para adjuntar screenshots a un {@link Scenario} de Cucumber.
 * Se agrupa aqui para no repetir esta logica en cada clase de step
 * definitions.
 */
public final class ScreenshotAttacher {

    private static final AppConfigSteps appConfigSteps = new AppConfigSteps();

    private static Scenario escenarioActual;

    public static void establecerEscenario(Scenario scenario) {
        escenarioActual = scenario;
    }

    public static void attach(String nombre) {
        if (escenarioActual != null) {
            attach(escenarioActual, nombre);
        }
    }

    private ScreenshotAttacher() {
        // Clase de utilidades: no se instancia
    }

    public static void attach(Scenario scenario, String nombre) {
        try {
            byte[] screenshot = appConfigSteps.tomarScreenshot();

            if (screenshot != null && screenshot.length > 0) {
                System.out.println("[ScreenshotAttacher] Adjuntando evidencia: " + nombre);
                scenario.attach(screenshot, "image/png", nombre);
            } else {
                System.out.println("[ScreenshotAttacher] No se genero evidencia para: " + nombre);
            }
        } catch (IllegalStateException e) {
            System.out.println(
                    "[ScreenshotAttacher] No hay una sesion activa para capturar: " + nombre
            );
        }
    }
}


