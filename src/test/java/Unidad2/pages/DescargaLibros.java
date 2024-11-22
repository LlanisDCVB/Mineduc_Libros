package Unidad2.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import Unidad2.utils.ClaseBase;



public class DescargaLibros extends ClaseBase {
    //Agrupar los localizadores

    public static void SeleccionarYDescargarLibro() throws InterruptedException {

        // Seleccionar el libro de lenguaje para ver su contenido
        ClaseBase.esperarXSegundos(5000);
        WebElement enlaceLyC = driver.findElement(By.xpath("//a[@id=\"itemSeleccionable_0\"]"));
        enlaceLyC.click();
        esperarXSegundos(5000);


        // Descargar el libro
        WebElement descargarLyC = driver.findElement(By.xpath("//a[@class='btn btn-default' and contains(., 'Descargar')]"));
        descargarLyC.click();
        esperarXSegundos(5000);
    }

    public static void VerificarArchivoDeDescarga() {

        // Verificar si el enlace de descarga está presente
        try {
            WebElement descargarLyC = driver.findElement(By.xpath("//a[@class='btn btn-default' and contains(., 'Descargar')]"));
            Assertions.assertTrue(descargarLyC.isDisplayed(), "El enlace de descarga está presente.");
            System.out.println("El archivo de descarga esta disponible.");
        } catch (NoSuchElementException e) {
            System.out.println("El archivo de descarga no se encuentra disponible.");
            driver.close();
        }
    }



    public DescargaLibros(WebDriver driver) {
        super(driver);
    }


}
