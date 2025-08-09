package pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProdutosPage {
    private WebDriver driver;

    public ProdutosPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean NaPaginaDeProdutos() {
        return driver.getCurrentUrl().contains("inventory");
    }

    public void adicionarProdutoAoCarrinho(String nomeProduto) {
        WebElement botaoAdd = driver.findElement(By.xpath("//div[text()='" + nomeProduto + "']/ancestor::div[@class='inventory_item']//button"));
        botaoAdd.click();
    }

    public void acessarCarrinho() {
        driver.findElement(By.className("shopping_cart_link")).click();
    }

    public String obterQuantidadeCarrinho() {
        List<WebElement> badges = driver.findElements(By.className("shopping_cart_badge"));
        return badges.isEmpty() ? "0" : badges.get(0).getText();
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
