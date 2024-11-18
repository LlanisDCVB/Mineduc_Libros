package Unidad2.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import Unidad2.utils.ClaseBase;


public class LoginPage extends ClaseBase {
    //Agrupar los localizadores

    // mensaje de error del RUT
    By byErrorMessage = By.id("login_error_alumno_run");

        // Verificar mensaje de advertencia de estudiante incorrecto
    By bywarningMessage = By.id("warning");

    // verificar mensaje seleccionar curso
    By byErrorCurso = By.id("login_error_alumno_nivel");

    // verificar mensaje seleccionar curso
    By byErrorFechaNac = By.id("login_error_alumno_fecha_nacimiento");


    // campos a llenar
    By byTxtrut = By.id("run_alumno");
    By byTxtNivel = By.id("nivel_alumno");
    By byTxtFechaNac = By.id("fecha_nacimiento_alumno");


    //Definir las acciones de la página
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void iniciarSesion(String Rut, String Nivel, String FechNac) {
        agregarTexto(esperaExplicita(byTxtrut), Rut);
        agregarTexto(esperaExplicita(byTxtNivel), Nivel);
        agregarTexto(esperaExplicita(byTxtFechaNac), FechNac);
    }

    public String obtenerErrorRut() {
        return obtenerTexto(esperaExplicita(byErrorMessage));

    }

    public String obtenerErrorEstidianteIncorrecto() {
        return obtenerTexto(esperaExplicita(bywarningMessage));

    }

    public String obtenerErrorCurso() {
        return obtenerTexto(esperaExplicita(byErrorCurso));

    }

    public String obtenerErrorFecha() {
        return obtenerTexto(esperaExplicita(byErrorFechaNac));

    }

}
