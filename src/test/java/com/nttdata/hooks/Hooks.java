package com.nttdata.hooks;

import com.nttdata.steps.AppConfigSteps;
import com.nttdata.support.ScreenshotAttacher;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

/**
 * Hooks globales de Cucumber: aplican a TODOS los escenarios, sin
 * importar el feature. Utiles para dar contexto en consola al
 * aprendiz sobre en que punto va la ejecucion.
 */
public class Hooks {

    private final AppConfigSteps appConfigSteps = new AppConfigSteps();

    @Before
    public void antesDelEscenario(Scenario scenario) {
        ScreenshotAttacher.establecerEscenario(scenario);
        System.out.println("========================================");
        System.out.println("[Hooks] Iniciando escenario: " + scenario.getName());
        System.out.println("========================================");
    }

    @AfterStep
    public void despuesDeCadaPaso(Scenario scenario) {

        String nombrePaso = scenario.getName()
                + " - Evidencia del paso";

        ScreenshotAttacher.attach(scenario, nombrePaso);
    }

    @After
    public void despuesDelEscenario(Scenario scenario) {

        String estado = scenario.isFailed() ? "FALLIDO" : "EXITOSO";

        System.out.println("[Hooks] Escenario '" + scenario.getName()
                + "' finalizo: " + estado);

        String nombreEvidencia = scenario.isFailed()
                ? "Estado final (FALLO)"
                : "Estado final";

        ScreenshotAttacher.attach(scenario, nombreEvidencia);

        try {
            System.out.println("[Hooks] Cerrando sesión de Appium...");
            appConfigSteps.cerrarAplicacion(
                    "com.saucelabs.mydemoapp.android"
            );
        } catch (IllegalStateException e) {
            System.out.println(
                    "[Hooks] No hay una sesión activa para cerrar."
            );
        }
    }
}
