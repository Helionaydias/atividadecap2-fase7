# SmartCityWasteManagement - Testes Automatizados

Este repositório contém uma suíte de testes automatizados para o sistema de gerenciamento de resíduos urbanos. A API é responsável por gerenciar usuários, e os testes foram criados com foco em validar as operações de cadastro e exclusão de usuários, além de assegurar que o contrato de resposta esteja de acordo com as especificações.

## Pré-requisitos

- **Docker** e **Docker Compose**
- **Java JDK 17+**
- **Maven**

## Configuração do Ambiente

### 1. Clonar o Repositório

Clone o repositório do projeto em seu ambiente local:

```bash
git clone https://github.com/Helionaydias/atividadecap2-fase7.git
cd smartcity-waste-management

2. Construir e Executar o Contêiner Docker
Execute o seguinte comando para iniciar o contêiner Docker da API:

docker run -p 8082:8080 smartcity-waste-management:previous

Verifique a API no Swagger para assegurar que está em execução: http://localhost:8082/swagger-ui/index.html

Estrutura do Projeto
hooks: Configurações globais dos testes.
services: Métodos de requisição da API.
steps: Passos dos testes em Gherkin.
features: Cenários de teste em Gherkin.
schemas: Contratos JSON Schema para validar respostas.

Executando os Testes
Para rodar os testes localmente, execute o seguinte comando:

mvn clean test

Cenários de Teste
Principais cenários implementados em Gherkin:

Cadastro de Usuário com Dados Válidos
Cadastro de Usuário com Dados Inválidos
Deleção de Usuário

Validação de Contrato
Os contratos JSON Schema garantem a conformidade das respostas da API. Eles estão localizados em src/test/resources/schemas/.
