# language: pt

@regressivo
Funcionalidade: Validar o contrato ao realizar um cadastro bem-sucedido de usuario

  Cenario: Validar contrato do cadastro bem-sucedido de usuário
    Dado que eu tenha os seguintes dados do usuário:
      | campo      | valor                    |
      | idUsuario  | 60                       |
      | nmUsuario  | Gloria Maria             |
      | dsEmail    | gloria.maria@example.com |
      | nrCpf      | 25555588885              |
      | dtNasc     | 2012-05-18               |
    Quando eu enviar a requisisição para o endpoint "/usuarios" para cadastro de usuário
    Então o status code deve retornar 200
    E o arquivo de contrato esperado é o "Cadastro bem-sucedido de usuario"
    Então a resposta da requisição deve estar em conformidade com o contrato selecionado



