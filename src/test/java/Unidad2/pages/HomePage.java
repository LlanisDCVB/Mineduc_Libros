package Unidad2.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import Unidad2.utils.ClaseBase;


public class HomePage extends ClaseBase {

       //Agrupar los localizadores
    // Indentifica boton estudiante
    By btnCambiaEstudiante = By.xpath("//a[@data-tipo-usuario='alumno']");

    //identifica botones
    By btnIngresar = By.xpath("//button[@id=\"ingresar\"]");

   // Seleccionar el libro de lenguaje para ver su contenido
    By btnenlaceLyC = By.xpath("//a[@id=\"itemSeleccionable_0\"]");

    // Descargar el libro
    By btndescargarLyC = By.xpath("//a[@class='btn btn-default' and contains(., 'Descargar')]");

    // Cerrar sesión
    By btncerrar = By.xpath("//a[@class=\"btn btn-primary\"]");



    //Definir las acciones de la página
    public HomePage(WebDriver driver) {
        super(driver);
    }

    //acciones

     //hace clic en boton estudiante
    public void irACambiaEstudiante(){ click(esperaExplicita(btnCambiaEstudiante)); }

    //hace clic en boton ingresar
    public void irAbtnIngresar(){ click(esperaExplicita(btnIngresar)); }

    // hacer click en libro de lenguaje
    public void irAbtnenlaceLyC(){ click(esperaExplicita(btnenlaceLyC)); }

    //hacer clic en descargar libro
    public void irAbtndescargarLyC(){ click(esperaExplicita(btndescargarLyC)); }

    public void irACerrar(){click(esperaExplicita(btncerrar));}


}
