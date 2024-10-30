# language: pt

@regressivo
Funcionalidade: Cadastro de usuários

  Cenário: Cadastro de usuário com dados válidos
    Dado que eu tenha os seguintes dados do usuário:
      | campo      | valor                    |
      | idUsuario  | 60                       |
      | nmUsuario  | Gloria Maria           |
      | dsEmail    | gloria.maria@example.com |
      | nrCpf      | 25555588885              |
      | dtNasc     | 2012-05-18               |
    Quando eu enviar a requisisição para o endpoint "/usuarios" para cadastro de usuário
    Então o status code deve retornar 200

  Cenário: Cadastro de usuário com dados inválidos
    Dado que eu tenha os seguintes dados do usuário:
      | campo      | valor                    |
      | idUsuario  | 60                       |
      | nmUsuario  | Gloria Maria             |
      | dsEmail    | gloria.maria@example.com |
      | nrCpf      | maria                    |
      | dtNasc     | 2012-05-18_              |
    Quando eu enviar a requisisição para o endpoint "/usuarios" para cadastro de usuário
    Então o status code deve retornar 400
    E o corpo de resposta de erro da api deve conter status 400, erro "Bad Request" e path "/usuarios"


