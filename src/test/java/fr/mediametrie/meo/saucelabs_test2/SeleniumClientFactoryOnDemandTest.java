package fr.mediametrie.meo.saucelabs_test2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import com.saucelabs.selenium.client.factory.SeleniumFactory;

public class SeleniumClientFactoryOnDemandTest {

    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
//        System.setProperty("SELENIUM_STARTING_URL", "http://dvsaap03.mediametrie.fr:8122/ihm-deploiement");
        driver = SeleniumFactory.createWebDriver();
        DesiredCapabilities capabilities = (DesiredCapabilities)((RemoteWebDriver) driver).getCapabilities();
        capabilities.setCapability("prerun", "http://get.videolan.org/vlc/2.0.6/win32/vlc-2.0.6-win32.exe");
    }

    @After
    public void tearDown() throws Exception {
        driver.close();
        driver.quit();
    }

    @Test
    public void webDriver() throws Exception {
        driver.get("http://dvsaap03.mediametrie.fr:8122/ihm-deploiement/app/home.jsf");
        driver.findElement(By.id("home:login")).click();
        driver.findElement(By.id("j_username")).click();
        driver.findElement(By.id("j_username")).clear();
        driver.findElement(By.id("j_username")).sendKeys("admin");
        driver.findElement(By.id("j_password")).click();
        driver.findElement(By.id("j_password")).clear();
        driver.findElement(By.id("j_password")).sendKeys("admin");
        driver.findElement(By.id("j_idt21")).click();
        driver.findElement(By.linkText("Déployer")).click();
        Select select = new Select(driver.findElement(By.name("hometab:deployform:toselect:0:j_idt191")));
        select.selectByValue("0.0.0");
    }

}
