import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {
	private WebDriver driver;

	public DSL(WebDriver driver) {	
		this.driver = driver;
	}
	
	
	public void escreve(String id_campo, String valor_campo) {
		 driver.findElement(By.id(id_campo)).sendKeys(valor_campo);
	}
	
	public String obterValorCampo(String id_campo) {
		return driver.findElement(By.id(id_campo)).getAttribute("value");
	}
	
	public void clicarRadio(String id_campo){
		 driver.findElement(By.id(id_campo)).click();
	}
	
	public boolean isRadioMarcado(String id_campo) {
		return driver.findElement(By.id(id_campo)).isSelected();
	}
	
	public void clicarCheckBox(String id_campo) {
		 driver.findElement(By.id(id_campo)).click();
	}
	
	public void selecionarCombo(String id_campo, String id_elemento) {
		Select comboEscolaridade = new Select(driver.findElement(By.id(id_campo)));
	    comboEscolaridade.selectByVisibleText(id_elemento);		
	}
	
	public boolean isComboSelecionado(String id_campo, String id_elemento) {
		 Select comboEscolaridade = new Select(driver.findElement(By.id(id_campo)));
		 List<WebElement>  optionsEscolaridade = comboEscolaridade.getOptions();
		  
		  boolean encontrou = false;
		  
		  for (WebElement option : optionsEscolaridade) {
			  if (option.getText().equals(id_elemento)) {
				  encontrou = true;
			  }
		  }
		  return encontrou;
	}
	
	public int obterQtdeElementosSelecionadosCombo(String id_campo) {
		  Select combo = new Select(driver.findElement(By.id(id_campo)));
		  List<WebElement> options = combo.getAllSelectedOptions();
		  return options.size();
		  
	}
	
	public void clicarBotao(String id_campo) {
		 driver.findElement(By.id(id_campo)).click();
	}
	
	public void clicarLink(String id_campo) {
		 driver.findElement(By.linkText(id_campo)).click();
	}
	
	public String obterTexto(String id_campo) {
		return driver.findElement(By.id(id_campo)).getText();
	}
	
}
