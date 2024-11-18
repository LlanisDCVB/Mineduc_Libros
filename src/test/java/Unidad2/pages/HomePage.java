package Unidad2.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import Unidad2.utils.ClaseBase;
import org.openqa.selenium.WebElement;

public class HomePage extends ClaseBase {

       //Agrupar los localizadores
    // Indentifica boton estudiante
    By btnCambiaEstudiante = By.xpath("//a[@data-tipo-usuario='alumno']");

    //identifica boton ingresar
    By btnIngresar = By.xpath("//button[@id=\"ingresar\"]");

   // Seleccionar el libro de lenguaje para ver su contenido
    By btnenlaceLyC = By.xpath("//a[@id=\"itemSeleccionable_0\"]");

    // Descargar el libro
    By btndescargarLyC = By.xpath("//a[@class='btn btn-default' and contains(., 'Descargar')]");





    //Definir las acciones de la página
    public HomePage(WebDriver driver) {
        super(driver);
    }

    //acciones
    //public void irARegisterPage(){ click(esperaExplicita(btnRegistrase)); }
     //hace clic en boton estudiante
    public void irACambiaEstudiante(){ click(esperaExplicita(btnCambiaEstudiante)); }

    //hace clic en boton ingresar
    public void irAbtnIngresar(){ click(esperaExplicita(btnIngresar)); }

    // hacer click en libro de lenguaje
    public void irAbtnenlaceLyC(){ click(esperaExplicita(btnenlaceLyC)); }

    //hacer clic en descargar libro
    public void irAbtndescargarLyC(){ click(esperaExplicita(btndescargarLyC)); }

    /* public void irALoginPage(){
        click(esperaExplicita(btnIngresar));
    }

   public String obtenerUsername(){
        return obtenerAtributoWebElement(esperaExplicita(byBtnTextUsername),"aria-label");
    }

     */
}
