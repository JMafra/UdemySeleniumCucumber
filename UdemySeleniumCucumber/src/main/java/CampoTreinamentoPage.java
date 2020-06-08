import org.openqa.selenium.WebDriver;

public class CampoTreinamentoPage {

	private DSL dsl;

	public CampoTreinamentoPage(WebDriver driver) {
		dsl = new DSL(driver);

	}

	public void setNome(String nome) {
		dsl.escreve("elementosForm:nome", nome);
	}

	public void setSobreNome(String sobrenome) {
		dsl.escreve("elementosForm:sobrenome", sobrenome);
	}

	public void setSexoFeminino() {
		dsl.clicarRadio("elementosForm:sexo:1");
	}

	public void setComidaCarnePizza() {
		dsl.clicarCheckBox("elementosForm:comidaFavorita:0");
		dsl.clicarCheckBox("elementosForm:comidaFavorita:2");
	}

	public void setEscolaridade(String escolaridade) {
		dsl.selecionarCombo("elementosForm:escolaridade", escolaridade);
	}

	public void setEsporte(String valor1, String valor2) {
		dsl.selecionarCombo("elementosForm:esportes", valor1);
		dsl.selecionarCombo("elementosForm:esportes", valor2);
	}
	
	public void setSugestoesAdicionais(String sugestoes) {
		 dsl.escreve("elementosForm:sugestoes", sugestoes);	
	}
	
	public void cadastrar() {
		 dsl.clicarBotao("elementosForm:cadastrar");	
	}
	
	public String obterNomeCadastro() {
		return dsl.obterTexto("descNome");
	}
	
	public String obterSobreNomeCadastro() {
		return dsl.obterTexto("descSobrenome");
	}
	
	public String obterSexoCadastro() {
		return dsl.obterTexto("descSexo");
	}
	
	
	public String obterComidaCadastro() {
		return dsl.obterTexto("descComida");
	}
	
	public String obterEscolaridadeCadastro() {
		return dsl.obterTexto("descEscolaridade");
	}


}
