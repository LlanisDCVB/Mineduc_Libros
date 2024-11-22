package Unidad2.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ClaseBase {
    //Atributos
    protected static WebDriver driver;
    private WebDriverWait wait;


    //Métodos
    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public void setWait(WebDriverWait wait) {
        this.wait = wait;
    }

    public ClaseBase(WebDriver driver) {
        this.driver = driver;
    }


    //funciones genericas para el uso de la libreria de selenium
    public static WebElement buscarElementoWeb(By localizador){
        return driver.findElement(localizador);
    }

    public List<WebElement> buscarElementosWeb(By localizador){
        return driver.findElements(localizador);
    }

    public void cargarSitio(String url){
        this.driver.get(url);
    }

    //definir una espera explicita de 30 seg consultado cada .5 segs
    public WebElement esperaExplicita(By localizador){
        wait = new WebDriverWait(this.driver,30);
        return wait.until(ExpectedConditions.presenceOfElementLocated(localizador));
    }

    //Esperar X Segundos
    public static void esperarXSegundos(int miliSegundos) {
        try {
            Thread.sleep(miliSegundos);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //clicks
    public void click(By localizador){
        this.driver.findElement(localizador).click();
    }

    public void click(WebElement elemento){
        elemento.click();
    }

    //inserciones de texto
    public void agregarTexto(By localizador,String texto){
        this.driver.findElement(localizador).sendKeys(texto);
    }

    public void agregarTexto(WebElement elemento,String texto){
        elemento.sendKeys(texto);
    }


    public void agregarCombinacionTeclas(WebElement elemento, Keys key){
        elemento.sendKeys(key);
    }

    public static void cerrarBrowser(){
        driver.close();
    }

    public void maximizarBrowser(){
        this.driver.manage().window().maximize();
    }

    //Conectar a el webdriver, sin importar que navegador sea
    public WebDriver conexionDriver(String browser,String ruta,String propertyDriver){
        if(browser.equalsIgnoreCase("chrome")){
            System.setProperty(propertyDriver,ruta);
            this.driver = new ChromeDriver();
        }else if (browser.equalsIgnoreCase("firefox")){
            System.setProperty(propertyDriver,ruta);
            this.driver = new FirefoxDriver();
        }else if (browser.equalsIgnoreCase("edge")){
            System.setProperty(propertyDriver,ruta);
            this.driver = new EdgeDriver();
        }
        return this.driver;
    }


    public String obtenerTexto(WebElement elemento){
        return elemento.getText();
    }

    public String obtenerAtributoWebElement(By localizador,String atributo){
        return this.driver.findElement(localizador).getAttribute(atributo);
    }

    public String obtenerAtributoWebElement(WebElement elemento,String atributo){
        return elemento.getAttribute(atributo);
    }

    //mover scroll
    public static void scrollByPixels(WebDriver driver, int xPixels, int yPixels) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Ejecuta el script para mover el scroll
        js.executeScript("window.scrollBy(arguments[0], arguments[1]);", xPixels, yPixels);
    }

    // Método para mover el scroll hacia abajo una cantidad específica de píxeles
    public static void bajarScroll(WebDriver driver, int pixels) {
        scrollByPixels(driver, 0, pixels);
    }

    public static void Calendario(){

      WebElement datePickerButton = driver.findElement(By.id("fecha_nacimiento_alumno"));
      datePickerButton.click(); // Abre el calendario

      WebElement yearDropdown = driver.findElement(By.className("ui-datepicker-year"));
      Select yearSelect = new Select(yearDropdown);
      yearSelect.selectByValue("2013"); // Selecciona el año 2013

      WebElement monthDropdown = driver.findElement(By.className("ui-datepicker-month"));
      Select monthSelect = new Select(monthDropdown);
      monthSelect.selectByValue("5"); // Selecciona Junio (0 = Enero, 5 = Junio)

      WebElement day = driver.findElement(By.xpath("//a[text()='14']")); // Encuentra el enlace con el texto '14'
      day.click(); // Selecciona el día


    }

}
