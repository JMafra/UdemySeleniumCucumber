

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



public class TesteUdemy {
	
	private WebDriver driver;
	private DSL dsl;
	private CampoTreinamentoPage page;
	
	@Before
	public void inicializa() {
		  System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
		  driver = new ChromeDriver();
		  driver.manage().window().maximize();
		  driver.get(System.getProperty("user.dir") + "/src/main/resources/site/componentes.html");	
		  dsl = new DSL(driver);
		  page = new CampoTreinamentoPage(driver);
	}
	
	@After
	public void finalizar() {
		// driver.quit();	
	}

	
	@Test
	public void testRealizarCadastro() {
	  page.setNome("Juliana");
	  page.setSobreNome("Mafra");  
	  page.setSexoFeminino();	 
	  page.setComidaCarnePizza();
	  page.setEscolaridade("Mestrado");	 
	  page.setEsporte("Natacao", "Corrida");	
	  page.setSugestoesAdicionais("sugestoes adicionais");
      page.cadastrar();
	 	  
	  Assert.assertTrue(dsl.isComboSelecionado("elementosForm:escolaridade", "Mestrado"));
	  Assert.assertTrue( dsl.isRadioMarcado("elementosForm:sexo:1"));
	  Assert.assertEquals(2, dsl.obterQtdeElementosSelecionadosCombo("elementosForm:esportes"));		 
	  
	  dsl.clicarLink("Voltar");	  
		
	  Assert.assertEquals("Nome: Juliana", page.obterNomeCadastro());	
	  Assert.assertEquals("Sobrenome: Mafra", page.obterSobreNomeCadastro());
	  Assert.assertEquals("Sexo: Feminino", page.obterSexoCadastro());
	  Assert.assertEquals("Comida: Carne Pizza", page.obterComidaCadastro());
	  Assert.assertEquals("Escolaridade: mestrado", page.obterEscolaridadeCadastro());	 	 
		
	}
	
	
	@Test
	public void testValidarBotoesJanelasPopUp() {	
	  // Botão CliqueMe
      dsl.clicarBotao("buttonSimple");	  
	  Assert.assertEquals("Obrigado!", dsl.obterValorCampo("buttonSimple"));    
	
	  
	  // Prompt de Alerta
	  dsl.clicarBotao("alert");	
	  Alert alerta = driver.switchTo().alert();
	  Assert.assertEquals("Alert Simples", alerta.getText());
	  alerta.accept();
	  
	  // Prompt de Confirmação
	  dsl.clicarBotao("prompt");	
	  Alert prompt = driver.switchTo().alert();
	  Assert.assertEquals("Digite um numero", prompt.getText());
	  prompt.sendKeys("12");
	  prompt.accept();
	  Assert.assertEquals("Era 12?", prompt.getText());
	  prompt.accept();
	  Assert.assertEquals(":D", prompt.getText());
	  prompt.accept(); 
			
	}
	
	@Test
	public void testValidarFrames() throws InterruptedException {	
	  driver.switchTo().frame("frame1");
	  dsl.clicarBotao("frameButton");
	  Alert alert = driver.switchTo().alert();
	  String msg = alert.getText();
	  Assert.assertEquals("Frame OK!", msg);
	  alert.accept();
	  
	  driver.switchTo().defaultContent();
	  driver.findElement(By.id("elementosForm:nome")).sendKeys(msg);

		
	}
	
	@Test
	public void testValidarJanelasComTitulo() {	
	  dsl.clicarBotao("buttonPopUpEasy");
	  driver.switchTo().window("Popup");
	  driver.findElement(By.tagName("textarea")).sendKeys("escrevendo no campo da popup");
	  driver.close();
	  driver.switchTo().window("");
	  driver.findElement(By.tagName("textarea")).sendKeys("escrevendo no campo da janela principal");
		
	}
	
	@Test
	public void testValidarJanelasSemTitulo() {	
	  dsl.clicarBotao("buttonPopUpHard");	
	  System.out.println(driver.getWindowHandle());
	  System.out.println(driver.getWindowHandles());
	  driver.switchTo().window((String)driver.getWindowHandles().toArray()[1]);
	  driver.findElement(By.tagName("textarea")).sendKeys("escrevendo no campo da popup");
	  driver.switchTo().window((String)driver.getWindowHandles().toArray()[0]);
	  driver.findElement(By.tagName("textarea")).sendKeys("escrevendo no campo da janela principal");
	  
			
	}
	
	@Test
	public void testJavaScript() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
	//	js.executeScript("alert('Testando Javascript via Selenium')");
		js.executeScript("document.getElementById('elementosForm:nome').value = 'Escrito via javascript'");
		
		WebElement element =  driver.findElement(By.id("elementosForm:nome"));
		js.executeScript("arguments[0].style.border= arguments[1]", element, "solid 4px red");
		
	}


}
