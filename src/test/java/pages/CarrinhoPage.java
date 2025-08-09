package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CarrinhoPage {
    private WebDriver driver;

    public CarrinhoPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isNaPaginaDeCarrinho() {
        return driver.getCurrentUrl().contains("cart");
    }

    public void clicarEmCheckout() {
        driver.findElement(By.id("checkout")).click();
    }

    public int contarProdutosNoCarrinho() {
        return driver.findElements(By.className("cart_item")).size();
    }

    public String obterNomeProdutoNaPosicao(int indice) {
        List<WebElement> nomes = driver.findElements(By.className("inventory_item_name"));
        return nomes.get(indice).getText();
    }

    public String obterPrecoProdutoNaPosicao(int indice) {
        List<WebElement> precos = driver.findElements(By.className("inventory_item_price"));
        return precos.get(indice).getText();
    }
}
