package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    private WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isNaPaginaDeCheckout() {
        return driver.getCurrentUrl().contains("checkout-step-one");
    }

    public void preencherDados(String firstName, String lastName, String zipCode) {
        driver.findElement(By.id("first-name")).sendKeys(firstName);
        driver.findElement(By.id("last-name")).sendKeys(lastName);
        driver.findElement(By.id("postal-code")).sendKeys(zipCode);
        driver.findElement(By.id("continue")).click();
    }

    public boolean isNaPaginaDeResumo() {
        return driver.getCurrentUrl().contains("checkout-step-two");
    }

    public void finalizarCompra() {
        driver.findElement(By.id("finish")).click();
    }

    public String obterMensagemObrigado() {
        return driver.findElement(By.className("complete-header")).getText();
    }
}
