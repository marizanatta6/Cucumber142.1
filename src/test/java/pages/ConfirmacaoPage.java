package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmacaoPage {
    private WebDriver driver;

    public ConfirmacaoPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isNaPaginaDeConfirmacao() {
        return driver.getCurrentUrl().contains("checkout-complete");
    }

    public String getMensagemFinal() {
        return driver.findElement(By.className("complete-header")).getText();
    }
}

