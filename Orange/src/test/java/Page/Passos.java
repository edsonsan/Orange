package Page;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select; 
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Passos {
	ChromeOptions opcaoChrome = new ChromeOptions();
//  options.addArguments("--window-size=1366,768"); 
	// "--headless", "--disable-gpu","--ignore-certificate-errors"
	WebDriver driver = new ChromeDriver(
			opcaoChrome.addArguments("--disable-dev-shm-usage", "test-type", "--disable-gpu",
									"--window-size=1366,768"));
//	WebDriver driver = new ChromeDriver();

	// CT-01
	@Given("acesso o site {string}")
	public void acesso_o_site(String url) {
		driver.get(url);
	} 

	@When("insiro usuario e senha validos")
	public void insiro_usuario_e_senha_validos() throws InterruptedException {
		Thread.sleep(1000);
		//driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin"); //Atualizado xpath
		//driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("admin123");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");//Atualizado xpath
		//driver.findElement(By.xpath("//input[@id='btnLogin']")).click();
		driver.findElement(By.xpath("//button[@type=\"submit\"]")).click(); //Atualizado xpath 
		
	}

	@Then("a pagina principal e exibida")
	public void a_pagina_principal_e_exibida() throws InterruptedException {
		Thread.sleep(2000);
		Assert.assertEquals("Dashboard", driver.findElement(By.xpath("//h1[contains(text(),'Dashboard')]")).getText());

	}

	// CT-02
	@When("insiro usuario e senha invalidos")
	public void usuarioInvalido() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys("Admin2");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("admin123");
		driver.findElement(By.xpath("//input[@id='btnLogin']")).click();
	}

	@Then("sera exibida a mensagem {string}")
	public void mensagemUsuarioInvalido(String msgInvalido) throws InterruptedException {
		Thread.sleep(1000);
		Assert.assertEquals(msgInvalido, driver.findElement(By.xpath("//span[@id='spanMessage']")).getText());

	}

	// CT-03
	@When("insiro usuario sem senha")
	public void campoSemSenha() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='btnLogin']")).click();
	}

	// CT-04
	@When("clico em Forgot your password")
	public void linkTrocarSenha() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[contains(text(),'Forgot your password?')]")).click();
	}

	@Then("sera direcionado para a pagina de troca de senha")
	public void paginaTrocarSenha() throws InterruptedException {
		Thread.sleep(1000);
		Assert.assertEquals("Forgot Your Password?",
				driver.findElement(By.xpath("//h1[contains(text(),'Forgot Your Password?')]")).getText());

	}

	// CT-05
	@Given("acesso o menu ADM")
	public void acessoAdm() throws InterruptedException {
		driver.get("https://opensource-demo.orangehrmlive.com/");
		insiro_usuario_e_senha_validos();
	}

	@When("escolho gerenciamento")
	public void acessoGerenciamento() {
		driver.findElement(By.xpath("//b[contains(text(),'Admin')]")).click();
	}

	@Then("a pagina de gerenciamento e aberta")
	public void paginaGerenciamento() throws InterruptedException {

	}

	// CT-06
	@Given("que seja em Systen Users, seja usuario habilitado clico no botao add")
	public void addUserLogin() throws InterruptedException {
		driver.get("https://opensource-demo.orangehrmlive.com/");
		insiro_usuario_e_senha_validos();
		acessoGerenciamento();
		paginaGerenciamento();
		driver.findElement(By.xpath("//input[@id='btnAdd']")).click();

	}

	@When("quando preencho os dados clico em save")
	public void criarUserLogin() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='systemUser_employeeName_empName']")).sendKeys("Edson Santos");
		driver.findElement(By.xpath("//input[@id='systemUser_userName']")).sendKeys("Edson2");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='systemUser_password']")).sendKeys("Athenas02");
		driver.findElement(By.xpath("//input[@id='systemUser_confirmPassword']")).sendKeys("Athenas02");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='btnSave']")).click();
	}

	@Then("o sistema retorna a página System Users e o usuário aparece na tabela")
	public void confirmaUserLogin() throws InterruptedException {
		Thread.sleep(2000);
		Assert.assertEquals("Edson2", driver.findElement(By.xpath("//a[contains(text(),'Edson2')]")).getText());
	}

	// @CT07
	@Given("que seja em Systen Users com usuario existente")
	public void acessoSystemUser() throws InterruptedException {
		driver.get("https://opensource-demo.orangehrmlive.com/");
		insiro_usuario_e_senha_validos();
		acessoGerenciamento();
		paginaGerenciamento();
	}

	@When("quando preencho usuario com Status All")
	public void buscaStatus() throws InterruptedException {
		Thread.sleep(1000);
		String status = "Enabled";
		driver.findElement(By.xpath("//input[@id='searchSystemUser_userName']")).sendKeys("Edson2");
		WebElement escolha = driver.findElement(By.xpath("//select[@id='searchSystemUser_status']"));
		Select opcao = new Select(escolha);
		opcao.selectByVisibleText(status);
		driver.findElement(By.xpath("//input[@id='searchBtn']")).click();
	}

	@Then("a pagina exibe o usuario com status All")
	public void Status() throws InterruptedException {
		Thread.sleep(1000);
		String status = "Enabled";
		String caminho = "//td[contains(text(),'" + status + "')]";
		Assert.assertEquals("Edson2", driver.findElement(By.xpath("//a[contains(text(),'Edson2')]")).getText());
		Assert.assertEquals("Enabled", driver.findElement(By.xpath(caminho)).getText());
	}

	// @CT08
	@When("quando preencho usuario com Status Enabled")
	public void buscaStatusEnabled() throws InterruptedException {
		Thread.sleep(1000);
		String status = "Enabled";
		driver.findElement(By.xpath("//input[@id='searchSystemUser_userName']")).sendKeys("Edson2");
		WebElement escolha = driver.findElement(By.xpath("//select[@id='searchSystemUser_status']"));
		Select opcao = new Select(escolha);
		opcao.selectByVisibleText(status);
		driver.findElement(By.xpath("//input[@id='searchBtn']")).click();
	}

	@Then("a pagina exibe o usuario com status Enabled")
	public void statusEnabled() throws InterruptedException {
		Thread.sleep(1000);
		String status = "Enabled";
		String caminho = "//td[contains(text(),'" + status + "')]";
		Assert.assertEquals("Edson2", driver.findElement(By.xpath("//a[contains(text(),'Edson2')]")).getText());
		Assert.assertEquals("Enabled", driver.findElement(By.xpath(caminho)).getText());
	}

	// @CT09
	@When("quando preencho usuario com Status Disabled")
	public void buscaStatusDisabled() throws InterruptedException {
		Thread.sleep(1000);
		String status = "Disabled";
		driver.findElement(By.xpath("//input[@id='searchSystemUser_userName']")).sendKeys("jhaey");
		WebElement escolha = driver.findElement(By.xpath("//select[@id='searchSystemUser_status']"));
		Select opcao = new Select(escolha);
		opcao.selectByVisibleText(status);
		driver.findElement(By.xpath("//input[@id='searchBtn']")).click();
	}

	@Then("a pagina exibe o usuario com status Disabled")
	public void statusDisabled() throws InterruptedException {
		Thread.sleep(1000);
		String status = "Disabled";
		String caminho = "//td[contains(text(),'" + status + "')]";
		Assert.assertEquals("jhaey", driver.findElement(By.xpath("//a[contains(text(),'jhaey')]")).getText());
		Assert.assertEquals("Disabled", driver.findElement(By.xpath(caminho)).getText());
	}

	// @CT10
	@When("seleciono o usuario e confirmo")
	public void selecionaRegistroTabela() throws InterruptedException {
		Thread.sleep(1000);
		String usuario = "Edson2";
		driver.findElement(By.xpath("//input[@id='searchSystemUser_userName']")).sendKeys(usuario);
		driver.findElement(By.xpath("//input[@id='searchBtn']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='btnDelete']")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Edson2')]")).click();
		String elementval = driver.getCurrentUrl();
		System.out.println("O valor do Href e: " + elementval);
		elementval = elementval
				.replace("https://opensource-demo.orangehrmlive.com/index.php/admin/saveSystemUser?userId=", "");
		driver.navigate().back();
		driver.findElement(By.xpath("//input[@value=" + elementval + "]")).click();
		driver.findElement(By.xpath("//input[@id='btnDelete']")).click();

		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='dialogDeleteBtn']")).click();
	}

	@Then("o registro e apagado")
	public void apagaRegistro() throws InterruptedException {
	
	}

	@After
	public void screenshot(Scenario scenario) throws IOException, InterruptedException {
		File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Collection<String> cenario = scenario.getSourceTagNames();
		String nome = cenario.toString();
		nome = nome.replace("[@", "");
		nome = nome.replace("]", "");
		try {
			FileUtils.copyFile(file, new File("target/evidencias/" + nome + "-" + scenario.getName() + ".jpg"));
			// FileUtils.copyFile(file, new File("target/evidencias/" + nome + "-"
			// +".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Thread.sleep(1000);
		driver.quit();
	}

}
