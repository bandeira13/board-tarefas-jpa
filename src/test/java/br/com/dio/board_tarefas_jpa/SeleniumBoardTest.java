package br.com.dio.board_tarefas_jpa;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SeleniumBoardTest {

    @LocalServerPort
    private int port;

    private WebDriver driver;

    @BeforeAll
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setupTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, java.util.logging.Level.ALL);
        options.setCapability("goog:loggingPrefs", logPrefs);

        options.addArguments("--headless");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            try {
                var logs = driver.manage().logs().get(LogType.BROWSER).getAll();
                if (!logs.isEmpty()) {
                    System.out.println("\n--- LOGS DO NAVEGADOR ---");
                    for (var log : logs) {
                        System.out.println(log.getLevel() + ": " + log.getMessage());
                    }
                    System.out.println("-------------------------\n");
                }
            } catch (Exception e) {
                System.out.println("Erro ao capturar logs: " + e.getMessage());
            }
            driver.quit();
        }
    }

    @Test
    public void deveCriarUmQuadroViaInterface() throws InterruptedException {
        driver.get("http://localhost:" + port);

        WebElement inputNome = driver.findElement(By.id("boardNameInput"));
        inputNome.sendKeys("Quadro Automatizado Selenium");

        WebElement botaoCriar = driver.findElement(By.id("createBoardBtn"));
        botaoCriar.click();

        WebElement mensagemSucesso = driver.findElement(By.id("resultMessage"));

        Thread.sleep(2000);

        String textoMensagem = mensagemSucesso.getText();

        assertTrue(textoMensagem.contains("Quadro Automatizado Selenium"),
                "A mensagem não apareceu. Texto encontrado: " + textoMensagem);
    }
}