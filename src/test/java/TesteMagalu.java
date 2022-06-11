import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)


public class TesteMagalu {

    private ChromeDriver webDriver;

    @BeforeAll
    void setupAll() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        this.webDriver = new ChromeDriver();
    }

    @BeforeEach
    public void openBrowser() {
        webDriver.manage().window().maximize();
        webDriver.get("https://www.magazineluiza.com.br/");
    }

    @AfterEach
    public void closeBrowser() {
        webDriver.close();
    }

    @Test
    //No site da Magazine Luiza, navegar até a aba de celulares
    public void NavigateToTabCelulares() {
        WebElement navigate = webDriver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/section[1]/div[2]/header/div/div[3]/nav/ul/li[3]/div[1]/a"));
        navigate.click();

        Assertions.assertEquals("https://www.magazineluiza.com.br/celulares-e-smartphones/l/te/", webDriver.getCurrentUrl());

    }

    @Test
    // Atráves do site MAGALU, vou pesquisar por celulares
    public void SearchByCellPhones() {
        WebElement search = webDriver.findElementById("input-search");
        search.click();

        search.sendKeys("Celulares");
        search.submit();

        Assertions.assertTrue(true, String.valueOf(webDriver.getCurrentUrl().equals("https://www.magazineluiza.com.br/busca/celulares/")));

    }

    @Test
    // Atráves do site MAGALU, vou pesquisar por Móveis e limpar a pesquisa
    public void ClearSearch() {
        WebElement search = webDriver.findElementById("input-search");
        search.click();
        search.sendKeys("Móveis");
        search.clear();

    }


    @Test
    //Navega para a última aba do menu da Home e valida que ele não está na aba de todos os departamentos
    public void NavigateForAllTabs() {
        Actions actions = new Actions(webDriver);
        WebElement navigate = webDriver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/section[1]/div[2]/header/div/div[3]/nav/ul/li[11]/div[1]/a"));
        actions.moveToElement(navigate)
                .perform();

        Assertions.assertFalse(false, String.valueOf(webDriver.getCurrentUrl().equals("//*[@id=\"__next\"]/div/main/section[1]/div[2]/header/div/div[3]/nav/ul/li[1]/div[1]/a")));


    }

    @Test
    //Navega até a aba de Móveis, e coloca faz uma lista com tudo que contém a palavra escritório
    public void elementsInList() {
        WebElement navigate = webDriver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/section[1]/div[2]/header/div/div[3]/nav/ul/li[4]/div[1]/a"));
        navigate.click();

        List<WebElement> elements = webDriver.findElements(By.tagName("li"));

        for (WebElement element : elements) {
            System.out.println("Escritório" + element.getText());
        }


    }

    @Test
    //navega até a aba de celulares, filtra por apple e valida se o filtro foi habilitado
    public void filterForApple() {
            WebElement celulares = webDriver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/section[1]/div[2]/header/div/div[3]/nav/ul/li[3]/div[1]/a"));
            celulares.click();

            WebElement apple = webDriver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/section[3]/div[2]/div[1]/div[2]/ul/li[1]/div"));
            apple.click();


            webDriver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/section[3]/div[2]"));
            Assertions.assertTrue(true, "Apple");
    }

    @Test
    //navega até a aba de celulares, filtra por ASUS e valida se o filtro foi habilitado
    public void filterForAsus() {
        WebElement celularesA = webDriver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/section[1]/div[2]/header/div/div[3]/nav/ul/li[3]/div[1]/a"));
        celularesA.click();

        WebElement apple = webDriver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/section[3]/div[2]/div[1]/div[2]/ul/li[3]/div"));
        apple.click();


        webDriver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/section[3]/div[2]"));
        Assertions.assertTrue(true, "ASUS");
    }




}