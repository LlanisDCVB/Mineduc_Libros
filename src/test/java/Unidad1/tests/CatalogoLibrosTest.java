package Unidad1.tests;

import Unidad2.utils.ClaseBase;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.nio.charset.StandardCharsets;
import java.util.Random;

public class CatalogoLibrosTest {

    private static WebDriver driver;

    @BeforeEach
    public void setUp() {
        String rutaProyecto = System.getProperty("user.dir");
        String rutaDriver = rutaProyecto + "\\src\\test\\resources\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", rutaDriver);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://catalogotextos.mineduc.cl/catalogo-textos/login/login?tipo=ee");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Order(1)
    public void CP001_CambiarAEstudiante() throws InterruptedException {
        Thread.sleep(5000); // Espera para asegurar la carga de la página

        // Hacer clic en el botón de "Estudiante"
        WebElement btnCambiaEstudiante = driver.findElement(By.xpath("//a[@data-tipo-usuario='alumno']"));
        btnCambiaEstudiante.click();
        Thread.sleep(1000);
        //mover scroll

        ClaseBase.bajarScroll(driver,500);
        Thread.sleep(2000);
    }

    @Test
    @Order(2)
    public void CP002_IngresarDatosEstudianteInvalido() throws InterruptedException {
        CP001_CambiarAEstudiante(); // Asegurarse de que estamos en el modo estudiante

        // Generar un RUT incorrecto y validar el mensaje de error
        String rutIncorrecto = generarRUT();
        ingresarDatosEstudiante(rutIncorrecto, "5° Básico", "14-06-2013");

        //mover scroll
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Scroll hacia abajo 500 píxeles
        js.executeScript("window.scrollBy(0, 500);");
        Thread.sleep(2000);

        // Verificar mensajes de error para el RUT incorrecto
        validarMensajesDeError1();
        Thread.sleep(2000);
        //Verifica si estudiante se encuentra en los registros
        validarMensajesDeError2();
        Thread.sleep(2000);
    }

    @Test
    @Order(3)
    public void CP003_IngresarAlCatalogo() throws InterruptedException {
        // Limpiar el campo y colocar el RUT correcto
        CP001_CambiarAEstudiante();
        driver.findElement(By.id("run_alumno")).clear();
        ingresarDatosEstudiante("24304457-K", "5° Básico", "14-06-2013");
//mover scroll

        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Scroll hacia abajo 500 píxeles
        js.executeScript("window.scrollBy(0, 500);");
        Thread.sleep(2000);

        // Hacer clic en el botón "Ingresar"
        WebElement btnIngresar = driver.findElement(By.xpath("//button[@id=\"ingresar\"]"));
        btnIngresar.click();
        Thread.sleep(2000);
    }

    @Test
    @Order(4)
    public void CP004_SeleccionarYDescargarLibro() throws InterruptedException {
        CP003_IngresarAlCatalogo(); // Asegurarse de que el estudiante ha ingresado al catálogo

        // Seleccionar el libro de lenguaje para ver su contenido
        WebElement enlaceLyC = driver.findElement(By.xpath("//a[@id=\"itemSeleccionable_0\"]"));
        enlaceLyC.click();
        Thread.sleep(10000);

        // Descargar el libro
        WebElement descargarLyC = driver.findElement(By.xpath("//a[@class='btn btn-default' and contains(., 'Descargar')]"));
        descargarLyC.click();
        Thread.sleep(10000);
    }

    @Test
    @Order(5)
    public void CP005_VerificarArchivoDeDescarga() {
        try {
            CP004_SeleccionarYDescargarLibro(); // Asegurarse de que el libro se intenta descargar
        } catch (InterruptedException e) {
            System.out.println("La operación fue interrumpida: " + e.getMessage());
            Thread.currentThread().interrupt(); // Restablecer el estado de interrupción del hilo
        }

        // Verificar si el enlace de descarga está presente
        try {
            WebElement descargarLyC = driver.findElement(By.xpath("//a[@class='btn btn-default' and contains(., 'Descargar')]"));
            Assertions.assertTrue(descargarLyC.isDisplayed(), "El enlace de descarga está presente.");
            System.out.println("El archivo de descarga está disponible.");
        } catch (NoSuchElementException e) {
            System.out.println("El archivo de descarga no se encuentra disponible.");
        }
    }

    // Método para ingresar datos del estudiante (RUT, nivel, y fecha)
    private void ingresarDatosEstudiante(String rut, String nivel, String fechaNacimiento) throws InterruptedException {
        driver.findElement(By.id("run_alumno")).sendKeys(rut);
        Thread.sleep(3000);

        driver.findElement(By.id("nivel_alumno")).sendKeys(nivel);
        Thread.sleep(3000);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('fecha_nacimiento_alumno').value='" + fechaNacimiento + "';");
        Thread.sleep(2000);
    }

    // Método para validar mensajes de error cuando el RUT es incorrecto

    private void validarMensajesDeError1() throws InterruptedException {
        try {
            // Hacer clic en el botón "Ingresar" para verificar el error
            WebElement btnIngresar = driver.findElement(By.xpath("//button[@id='ingresar']"));
            btnIngresar.click();
            Thread.sleep(2000);
            //mover scroll

            JavascriptExecutor js = (JavascriptExecutor) driver;
            // Scroll hacia abajo 500 píxeles
            js.executeScript("window.scrollBy(0, 500);");
            Thread.sleep(2000);

            // Verificar el mensaje de error del RUT
            WebElement errorMessage = driver.findElement(By.id("login_error_alumno_run"));
            String textoRecibido = errorMessage.getText().trim().replaceAll("\\s+", " "); // Quita espacios extra
            String textoEsperado = "RUN del estudiante no es válido";

            String textoCorregido = corregirEncoding(textoRecibido);

            if (textoEsperado.equals(textoCorregido)) {
                System.out.println("El rut ingresado no es el correcto");
            } else {
                System.out.println("El mensaje de error no coincide con el esperado.");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Error: No se encontró el elemento del mensaje de error en la página.");
        }
    }

 private void validarMensajesDeError2() throws InterruptedException {
     try {
         // Verificar mensaje de advertencia de estudiante incorrecto
         WebElement warningMessage = driver.findElement(By.id("warning"));
         String textoRecibido = warningMessage.getText().trim().replaceAll("\\s+", " "); // Quita espacios extra
         String textoEsperado = "Según nuestros registros, sus datos no son válidos.";
         //mover scroll

         JavascriptExecutor js = (JavascriptExecutor) driver;
         // Scroll hacia abajo 500 píxeles
         js.executeScript("window.scrollBy(0, 500);");
         Thread.sleep(2000);

         String textoCorregido = corregirEncoding(textoRecibido);

         if (textoEsperado.equals(textoCorregido)) {
             System.out.println("Estudiante no existe en registros");
         } else {
             System.out.println("El mensaje de advertencia no coincide con el esperado.");
         }
     } catch (NoSuchElementException e) {
         System.out.println("Error: No se encontró el elemento de advertencia en la página.");
     }
 }

    // Método para generar un RUT incorrecto
    public static String generarRUT() {
        Random random = new Random();
        int numeroBase = 1000000 + random.nextInt(9000000);
        int digitoVerificador = calcularDigitoVerificador(numeroBase);
        return String.format("%,d-%d", numeroBase, digitoVerificador).replace(",", ".");
    }

    private static int calcularDigitoVerificador(int numero) {
        int suma = 0;
        int multiplicador = 2;
        while (numero > 0) {
            int digito = numero % 10;
            suma += digito * multiplicador;
            multiplicador = (multiplicador == 7) ? 2 : multiplicador + 1;
            numero /= 10;
        }
        int resto = 11 - (suma % 11);
        if (resto == 11) {
            return 0;
        } else if (resto == 10) {
            return 'K';
        } else {
            return resto;
        }
    }
    public static String corregirEncoding(String textoRecibido){
        byte[] bytes = textoRecibido.getBytes(StandardCharsets.ISO_8859_1);
        String textoCorregido = new String(bytes, StandardCharsets.UTF_8);
        return textoCorregido;
    }
}

/*
@Test
public void ejecutarSecuenciaCompleta() throws InterruptedException {
    CP001_CambiarAEstudiante(); // Paso 1
    CP002_IngresarDatosEstudianteInvalido(); // Paso 2
    CP003_IngresarAlCatalogo(); // Paso 3
    CP004_SeleccionarYDescargarLibro(); // Paso 4
    CP005_VerificarArchivoDeDescarga(); // Paso 5
}
*/