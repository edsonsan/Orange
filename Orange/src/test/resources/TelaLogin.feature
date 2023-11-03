Feature: Realizar o login
  Como usuário cadastrado
  Desejo efetuar o login
  Para acessar a página principal

	@CT01
  Scenario: Login com sucesso
    Given acesso o site "https://opensource-demo.orangehrmlive.com/"
    When insiro usuario e senha validos
    Then a pagina principal e exibida
    
	@CT02
	Scenario: Login usuario invalido
    Given acesso o site "https://opensource-demo.orangehrmlive.com/"
    When insiro usuario e senha invalidos
    Then sera exibida a mensagem "Invalid credentials"
    
	@CT03
	Scenario: Login sem senha
    Given acesso o site "https://opensource-demo.orangehrmlive.com/"
    When insiro usuario sem senha
    Then sera exibida a mensagem "Password cannot be empty"
    
  @CT04
  Scenario: Click em alterar senha
    Given acesso o site "https://opensource-demo.orangehrmlive.com/"
    When clico em Forgot your password
    Then sera direcionado para a pagina de troca de senha