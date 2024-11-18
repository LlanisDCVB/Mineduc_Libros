package Unidad2.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import Unidad2.utils.ClaseBase;

import java.util.List;

public class DescargaLibros extends ClaseBase {
    //Agrupar los localizadores

    public void SeleccionarYDescargarLibro() throws InterruptedException {

        // Seleccionar el libro de lenguaje para ver su contenido
        WebElement enlaceLyC = driver.findElement(By.xpath("//a[@id=\"itemSeleccionable_0\"]"));
        enlaceLyC.click();
        esperarXSegundos(10000);


        // Descargar el libro
        WebElement descargarLyC = driver.findElement(By.xpath("//a[@class='btn btn-default' and contains(., 'Descargar')]"));
        descargarLyC.click();
        esperarXSegundos(10000);
    }

    public void VerificarArchivoDeDescarga() {

        // Verificar si el enlace de descarga está presente
        try {
            WebElement descargarLyC = driver.findElement(By.xpath("//a[@class='btn btn-default' and contains(., 'Descargar')]"));
            Assertions.assertTrue(descargarLyC.isDisplayed(), "El enlace de descarga está presente.");
            System.out.println("El archivo de descarga está disponible.");
        } catch (NoSuchElementException e) {
            System.out.println("El archivo de descarga no se encuentra disponible.");
        }
    }



    public DescargaLibros(WebDriver driver) {
        super(driver);
    }


}
