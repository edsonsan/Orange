Feature: Realizar gerenciamento de usuário para acesso 
  Como usuário cadastrado
  Desejo efetuar o gerenciamento do acesso
  Para acessar o sistema


	@CT05
 	Scenario: Acessar Tela de Administracao
    Given acesso o menu ADM
    When escolho gerenciamento
    Then a pagina de gerenciamento e aberta
    
  @CT06
  Scenario: Adicionar usuario de acesso
  	Given que seja em Systen Users, seja usuario habilitado clico no botao add
  	When quando preencho os dados clico em save
  	Then o sistema retorna a página System Users e o usuário aparece na tabela
  	
  @CT07
  Scenario: busca de usuário com status All
  	Given que seja em Systen Users com usuario existente
  	When quando preencho usuario com Status All
  	Then a pagina exibe o usuario com status All
  
  @CT08	
  Scenario: Realizar busca de usuário com status Enabled
  	Given que seja em Systen Users com usuario existente
  	When quando preencho usuario com Status Enabled
  	Then a pagina exibe o usuario com status Enabled
  	
  @CT09	
  Scenario: Realizar busca de usuário com status Disabled
  	Given que seja em Systen Users com usuario existente
  	When quando preencho usuario com Status Disabled
  	Then a pagina exibe o usuario com status Disabled
  
  @CT10 
  Scenario: Deletar usuário de acesso
    Given que seja em Systen Users com usuario existente
  	When seleciono o usuario e confirmo
  	Then o registro e apagado
  	
  	
  	
  	