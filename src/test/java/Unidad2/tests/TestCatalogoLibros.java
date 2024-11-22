package Unidad2.tests;

import Unidad2.utils.ClaseBase;
import Unidad2.utils.Propertiesdriven;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.ArrayList;
import Unidad2.pages.HomePage;
import Unidad2.pages.LoginPage;
import Unidad2.pages.DescargaLibros;
import Unidad2.utils.DataDriven;



import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestCatalogoLibros {
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
        homePage.cargarSitio(Propertiesdriven.obtenerProperty("url"));
        homePage.maximizarBrowser();
    }

    @AfterEach
    public void posCondiciones() {
    }

    // @Test
    public void CP001_ErrorRUTErroneo() throws InterruptedException {  //no se encuentra en la base de datos)
        ClaseBase.esperarXSegundos(5000);
        homePage.irACambiaEstudiante();
        data = DataDriven.getTestData("CP001_ErrorRUTErroneo");
        ClaseBase.bajarScroll(driver, 500);
        ClaseBase.esperarXSegundos(2000);
        ClaseBase.Calendario();
        loginPage.iniciarSesion(data.get(1), data.get(2));  //, data.get(3) //ingrese rut no existente, curso y fech nac
        homePage.irAbtnIngresar();

        assertEquals(data.get(3), loginPage.obtenerErrorEstudianteIncorrecto());    //, "El rut ingresado no se encuentra en la base de datos");
        System.out.println("El rut no se encuentra en la base de datos");
        ClaseBase.bajarScroll(driver, 500);
        ClaseBase.esperarXSegundos(2000);
        ClaseBase.cerrarBrowser();

    }

    //@Test
    public void CP002_ErrorRutInvalido() throws InterruptedException {
        ClaseBase.esperarXSegundos(5000);
        homePage.irACambiaEstudiante();
        data = DataDriven.getTestData("CP002_ErrorRutInvalido");
        ClaseBase.bajarScroll(driver, 500);
        ClaseBase.esperarXSegundos(2000);
        ClaseBase.Calendario();
        loginPage.iniciarSesion(data.get(1), data.get(2));  //, data.get(3) //ingrese rut no existente, curso y fech nac
        homePage.irAbtnIngresar();

        assertEquals(data.get(3), loginPage.obtenerErrorRut()); //, "El rut ingresado es incorrecto");
        System.out.println("El rut es incorrecto");
        ClaseBase.esperarXSegundos(2000);
        ClaseBase.cerrarBrowser();
    }
    //@Test
    public void CP003_SinCurso () throws InterruptedException {
        ClaseBase.esperarXSegundos(5000);
        homePage.irACambiaEstudiante();
        data = DataDriven.getTestData("CP003_SinCurso");
        ClaseBase.bajarScroll(driver, 500);
        ClaseBase.esperarXSegundos(2000);
        ClaseBase.Calendario();
        loginPage.iniciarSesion(data.get(1), data.get(2));
        homePage.irAbtnIngresar();

        assertEquals(data.get(3), loginPage.obtenerErrorCurso());//, "Se debe ingresar el curso del alumno");
        System.out.println("Se debe ingresar el curso del alumno");
        ClaseBase.esperarXSegundos(2000);
        ClaseBase.cerrarBrowser();
    }
    //@Test
    public void CP004_SinFechaNac () throws InterruptedException {
        ClaseBase.esperarXSegundos(5000);
        homePage.irACambiaEstudiante();
        data = DataDriven.getTestData("CP004_SinFechaNac");
        ClaseBase.bajarScroll(driver, 500);
        ClaseBase.esperarXSegundos(2000);
        loginPage.iniciarSesion(data.get(1), data.get(2));
        homePage.irAbtnIngresar();

        assertEquals(data.get(3), loginPage.obtenerErrorFechaNulo()); //, "El texto no coincide con el esperado");
        System.out.println("Se debe ingresar la fecha de nacimiento");
        ClaseBase.esperarXSegundos(2000);
        ClaseBase.cerrarBrowser();
    }


    //@Test
    public void CP005_IngresoCorrecto() throws InterruptedException {
        ClaseBase.esperarXSegundos(5000);
        homePage.irACambiaEstudiante();
        data = DataDriven.getTestData("CP005_IngresoCorrecto");
        ClaseBase.bajarScroll(driver, 500);
        ClaseBase.esperarXSegundos(2000);
        loginPage.iniciarSesion(data.get(1), data.get(2));  //, data.get(3) //ingrese rut no existente, curso y fech nac
        ClaseBase.Calendario();
        homePage.irAbtnIngresar();
        System.out.println("El alumno ingresado es correcto");

        ClaseBase.esperarXSegundos(2000);
        homePage.irACerrar();
        ClaseBase.cerrarBrowser();
    }

    @Test
    public void CP006_DescargarLibro() throws InterruptedException {
        ClaseBase.esperarXSegundos(5000);
        homePage.irACambiaEstudiante();
        data = DataDriven.getTestData("CP006_DescargarLibro");
        ClaseBase.bajarScroll(driver, 500);
        ClaseBase.esperarXSegundos(5000);
        loginPage.iniciarSesion(data.get(1), data.get(2));  //, data.get(3) //ingrese rut no existente, curso y fech nac
        ClaseBase.Calendario();
        ClaseBase.esperarXSegundos(2000);
        homePage.irAbtnIngresar();
        homePage.irAbtnenlaceLyC();
        ClaseBase.esperarXSegundos(2000);

        DescargaLibros.VerificarArchivoDeDescarga(); //se valida si el enlace a descargar esta disponible

        homePage.irAbtndescargarLyC();
        System.out.println("Libro descargado");

        ClaseBase.esperarXSegundos(2000);
        homePage.irACerrar();
        ClaseBase.cerrarBrowser();
    }
}


