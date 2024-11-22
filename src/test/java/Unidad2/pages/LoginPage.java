package Unidad2.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import Unidad2.utils.ClaseBase;


public class LoginPage extends ClaseBase {
    //Agrupar los localizadores

    // mensaje de error del RUT
    By byErrorMessage = By.xpath("//span[@id='login_error_alumno_run' and @class='error-from']");//By.id("login_error_alumno_run");

        // Verificar mensaje de advertencia de estudiante incorrecto
    By bywarningMessage = By.xpath("//div[@id='warning']//div[contains(@class, 'tab-content')]"); //id("warning");

    // verificar mensaje seleccionar curso
    By byErrorCurso = By.id("login_error_alumno_nivel");

    // verificar mensaje seleccionar curso
    By byErrorFechaNac = By.id("login_error_alumno_fecha_nacimiento");

    By bySinFecha = By.xpath("//span[@id='login_error_alumno_fecha_nacimiento' and @class='error-from']");

    // campos a llenar
    By byTxtrut = By.id("run_alumno");
    By byTxtNivel = By.id("nivel_alumno");
    By byTxtFechaNac = By.id("fecha_nacimiento_alumno");


    //Definir las acciones de la página
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void iniciarSesion(String Rut, String Nivel) throws InterruptedException {

        //agregarTexto(esperaExplicita(byTxtFechaNac), FechNac);
        agregarTexto(esperaExplicita(byTxtrut), Rut);
        agregarTexto(esperaExplicita(byTxtNivel), Nivel);
        ClaseBase.esperarXSegundos(2000);



    }

    public String obtenerErrorRut() {
        return obtenerTexto(esperaExplicita(byErrorMessage));

    }

    public String obtenerErrorEstudianteIncorrecto() {
        return obtenerTexto(esperaExplicita(bywarningMessage));

    }

    public String obtenerErrorCurso() {
        return obtenerTexto(esperaExplicita(byErrorCurso));

    }

    public String obtenerErrorFecha() {
        return obtenerTexto(esperaExplicita(byErrorFechaNac));

    }

    public String obtenerErrorFechaNulo() {
        return obtenerTexto(esperaExplicita(bySinFecha));

    }

}
