package Unidad2.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.ArrayList;
import Unidad2.pages.HomePage;
import Unidad2.pages.LoginPage;
import Unidad2.pages.DescargaLibros;
import Unidad2.utils.DataDriven;
import Unidad2.utils.Encording;


public class TestCatalogoFinal {
    //Instanciar objetos de las page's
    private WebDriver driver;
    ArrayList<String> data;
    private HomePage homePage;
    private LoginPage loginPage;
    private DescargaLibros newAcountPage;

    @BeforeEach
    public void preCondiciones() {
        //preparo el driver y las page's
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        data = new ArrayList<String>();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(homePage.getDriver());
        newAcountPage = new DescargaLibros(homePage.getDriver());
        homePage.cargarSitio("https://catalogotextos.mineduc.cl/catalogo-textos/login/login?tipo=ee");
        homePage.maximizarBrowser();
    }

    @AfterEach
    public void posCondiciones() {
    }

    @Test
    public void CP001_ErrorRUTErroneo() {
        data = DataDriven.getTestData("CP001_ErrorRUTErroneo");
        homePage.irAbtnIngresar();
        loginPage.iniciarSesion(data.get(1), data.get(2), data.get(3)); //ingrese rut no existente, curso y fech nac
        if (loginPage.obtenerErrorRut() != null) {
            System.out.println("El rut ingresado no se encuentra en la base");

        }

    }
}

/*

    public void CP001_CambiarAEstudiante() throws InterruptedException {
        Thread.sleep(5000); // Espera para asegurar la carga de la página

        // Hacer clic en el botón de "Estudiante"

        homePage.irACambiaEstudiante();


        WebElement btnCambiaEstudiante = driver.findElement(By.xpath("//a[@data-tipo-usuario='alumno']"));
        btnCambiaEstudiante.click();

        //mover scroll

        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Scroll hacia abajo 500 píxeles
        js.executeScript("window.scrollBy(0, 500);");
        Thread.sleep(2000);
    }









        @Test
        public void CP002_CreacionCtaSpotify(){
            data = DataDriven.getTestData("CP002_CreacionCtaSpotify");
            homePage.irARegisterPage();
            newAcountPage.crearCtaSpotify(data.get(1),data.get(2),data.get(3),data.get(4),data.get(5),data.get(6), Integer.parseInt(data.get(7)), Boolean.valueOf(data.get(8)), Boolean.valueOf(data.get(9)));
            Assertions.assertEquals(Encording.corregirEncoding(data.get(10))
                    ,homePage.obtenerUsername());
        }
    }

*/

