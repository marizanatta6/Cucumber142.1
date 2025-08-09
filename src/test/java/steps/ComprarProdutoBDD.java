package steps;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Entao;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.ProdutosPage;
import pages.CarrinhoPage;
import pages.CheckoutPage;
import pages.ConfirmacaoPage;

public class ComprarProdutoBDD {
    
   WebDriver driver;
   private ProdutosPage produtosPage;
   private CarrinhoPage carrinhoPage;
   private CheckoutPage checkoutPage;
   private ConfirmacaoPage confirmacaoPage;

   @Before
   public void iniciar(){
       driver = new ChromeDriver();
       driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
       driver.manage().window().maximize();
   }

   @After
   public void finalizar(){
    driver.quit();
   }

 @Dado("que acesso o site {string}")
    public void que_acesso_o_site(String url) {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);

        // Login direto com dados fake de teste
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        produtosPage = new ProdutosPage(driver);
        assertTrue(produtosPage.NaPaginaDeProdutos(), "Não está na página de produtos");
    }

    @Quando("seleciono {string}")
    public void seleciono(String produto) {
        produtosPage.adicionarProdutoAoCarrinho(produto);
    }

    @Quando("clico no botao Add to cart")
    public void clico_no_botao_add_to_cart() {
        produtosPage.acessarCarrinho();
    }

    @Entao("visualiza a lista de compras")
    public void visualiza_a_lista_de_compras() {
        carrinhoPage = new CarrinhoPage(driver);
        assertTrue(carrinhoPage.isNaPaginaDeCarrinho(), "Não está na página do carrinho");
        carrinhoPage.clicarEmCheckout();
    }

    @Então("o nome do produto no carrinho deve ser {string}")
    public void verificarNomeProdutoNoCarrinho(String nomeEsperado) {
        carrinhoPage.assertNomeProduto(nomeEsperado);
    }

    @Então("o preço do produto no carrinho deve ser {string}")
    public void verificarPrecoProdutoNoCarrinho(String precoEsperado) {
        carrinhoPage.assertPrecoProduto(precoEsperado);
    }

    @Então("a quantidade do produto no carrinho deve ser {string}")
    public void verificarQuantidadeProdutoNoCarrinho(String quantidadeEsperada) {
        carrinhoPage.assertQuantidadeProduto(quantidadeEsperada);
    }

    @Quando("preencho {string}, {string} e {string}")
public void preencho_e(String firstName, String lastName, String zipCode) {
    checkoutPage = new CheckoutPage(driver);
    assertTrue(checkoutPage.isNaPaginaDeCheckout(), "Não é a tela de checkout inicial");

    checkoutPage.preencherDados(firstName, lastName, zipCode);
    assertTrue(checkoutPage.isNaPaginaDeResumo(), "Não é a tela de resumo do pedido");
}

@Quando("aperto o botao Finish")
public void aperto_o_botao_finish() {
    checkoutPage.finalizarCompra();

    confirmacaoPage = new ConfirmacaoPage(driver);
    assertTrue(confirmacaoPage.isNaPaginaDeConfirmacao(), "Não é a página de confirmação");

    String mensagem = confirmacaoPage.getMensagemFinal();
    assertTrue(mensagem.contains("Thank you"), "Compra não encontrada");
}

}
