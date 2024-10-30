# language: pt

@regressivo
Funcionalidade: Deletar usuário

  Contexto: Cadastro de usuário com dados válidos
    Dado que eu tenha os seguintes dados do usuário:
      | campo      | valor                    |
      | idUsuario  | 60                       |
      | nmUsuario  | Gloria Maria             |
      | dsEmail    | gloria.maria@example.com |
      | nrCpf      | 25555588885              |
      | dtNasc     | 2012-05-18               |
    Quando eu enviar a requisisição para o endpoint "/usuarios" para cadastro de usuário
    Então o status code deve retornar 200

  Cenário: Deve ser possível deletar um usuario
    Dado que eu recupere o ID do usuario criado no contexto
    Quando eu enviar a requisisição com o ID para o endpoint "/usuarios" de delecao de usuario
    Então o status code deve retornar 200

