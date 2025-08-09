import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ComprarProdutoTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private ProdutosPage productsPage;
    private CarrinhoPage cartPage;
    private CheckoutPage checkoutPage;

    @BeforeAll
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

        loginPage = new LoginPage(driver);
        productsPage = new ProdutosPage(driver);
        cartPage = new CarrinhoPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    @AfterAll
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Fluxo de compra completo com dados genéricos")
    public void testFluxoCompra() {
        loginPage.acessar();
        loginPage.loginComo("standard_user", "secret_sauce");

        // Verifica que o carrinho está vazio
        Assertions.assertEquals("0", productsPage.obterQuantidadeCarrinho());

        // Adiciona 4 produtos fictícios
        productsPage.adicionarProdutoAoCarrinho("Sauce Labs Backpack");
        productsPage.adicionarProdutoAoCarrinho("Sauce Labs Bolt T-Shirt");
        productsPage.adicionarProdutoAoCarrinho("Sauce Labs Bike Light");
        productsPage.adicionarProdutoAoCarrinho("Sauce Labs Fleece Jacket");

        // Valida o número de itens no badge do carrinho
        Assertions.assertEquals("4", productsPage.obterQuantidadeCarrinho());

        // Vai para o carrinho e valida quantidade
        productsPage.acessarCarrinho();
        Assertions.assertEquals(4, cartPage.contarProdutosNoCarrinho());

        // validar os nomes/preços antes de adicionar ao carrinho:
        Assertions.assertEquals("Sauce Labs Backpack", productsPage.obterNomeProdutoNaPosicao(0));
        Assertions.assertEquals("$29.99", productsPage.obterPrecoProdutoNaPosicao(0));
        Assertions.assertEquals("Sauce Labs Bolt T-Shirt", productsPage.obterNomeProdutoNaPosicao(1));
        Assertions.assertEquals("$15.99", productsPage.obterPrecoProdutoNaPosicao(1));
        Assertions.assertEquals("Sauce Labs Bike Light", productsPage.obterNomeProdutoNaPosicao(2));
        Assertions.assertEquals("$9.99", productsPage.obterPrecoProdutoNaPosicao(2));
        Assertions.assertEquals("Sauce Labs Fleece Jacket", productsPage.obterNomeProdutoNaPosicao(3));
        Assertions.assertEquals("$49.99", productsPage.obterPrecoProdutoNaPosicao(3));

        // Inicia checkout
        cartPage.clicarEmCheckout();
        checkoutPage.preencherDados("Teste", "Usuario", "12345");
        checkoutPage.isNaPaginaDeResumo();
        checkoutPage.finalizarCompra();

        // Valida mensagem de finalização
        Assertions.assertEquals("Thank you for your order!", checkoutPage.obterMensagemObrigado());
    }
}
