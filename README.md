# 💸 Pagamentos Fake API

![Badge em Desenvolvimento](http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=GREEN&style=for-the-badge)
![API RESTful](https://img.shields.io/badge/API-RESTful-0078D4?style=for-the-badge)

## Sobre o Projeto

O **Pagamentos Fake** é uma plataforma de pagamentos simplificada, projetada para simular o fluxo básico de transações financeiras em um ambiente de microserviços. O objetivo é implementar as funcionalidades de cadastro de usuários e lojistas, depósitos e transferências de valores entre carteiras virtuais, com foco em:

* **Consistência Transacional (Atomicidade):** Garantia de que as operações de débito e crédito sejam atômicas.
* **Integração Externa:** Simulação de serviços externos de autorização e notificação.
* **Boas Práticas de Arquitetura:** Implementação de uma API **RESTful** limpa e desacoplada.

## Regras de Negócio

- O sistema não deve permitir o cadastro de um novo usuário se o CPF/CNPJ ou o E-mail já estiverem em uso.
- Somente Usuários Comuns podem iniciar transferências. Lojistas não podem iniciar transferências.
- O saldo atual do pagador deve ser maior ou igual ao valor da transferência.
- A transferência só deve ser concluída após autorização (sistema autorizador externo).
- Em caso de falha em qualquer etapa (não-autorização, falha no crédito, erro de sistema), a transação completa deve ser revertida (rollback) e o valor deve voltar integralmente para a carteira do usuário pagador.
- Sistema deve notificar o usuário recebedor do crédito em sua carteira.

## 🚀 User Stories para a Plataforma Pagamentos Fake

| ID         | User Story                                                                                                                                                                     | Critérios de Aceite (AC)                                                                                                                                                                                                                                                                                                                                                                             |
|:-----------|:-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|:-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **US-001** | **Como** um novo Usuário (Comum ou Lojista), **eu quero** me cadastrar na plataforma fornecendo minhas informações, **para que** eu possa ter uma conta e acessar os serviços. | **Informações Obrigatórias:** Nome Completo, E-mail, Senha. <br/>**Usuário Comum:** Deve fornecer um **CPF** válido. <br/>**Usuário Lojista:** Deve fornecer um **CNPJ** válido. <br/>**Unicidade:** O sistema **não** deve permitir o cadastro de um novo usuário se o CPF/CNPJ ou o E-mail já estiverem em uso. <br/>**Segurança:** A senha deve ser armazenada de forma segura (e.g., *hashing*). |
| **US-002** | **Como** um Usuário Comum, **eu quero** poder transferir fundos da minha carteira para outro Usuário Comum ou Lojista, **para** efetuar pagamentos e movimentar valores.       | **Regra de Negócio:** Somente Usuários Comuns podem iniciar transferências. Lojistas **não** podem iniciar transferências. <br/>**Ação:** O valor deve ser debitado do pagador e creditado no recebedor. <br/>**API:** Deve haver um *endpoint* RESTful específico para esta operação.                                                                                                               |
| **US-003** | **Como** o sistema, **eu devo** garantir que o Usuário Comum tenha saldo suficiente em sua carteira, **antes** de autorizar uma transferência, **para** evitar saldo negativo. | **Validação:** O saldo atual do pagador deve ser maior ou igual ao valor da transferência. <br/>**Rejeição:** Se o saldo for insuficiente, a transferência deve ser negada com uma mensagem clara.                                                                                                                                                                                                   |
| **US-004** | **Como** o sistema, **eu devo** consultar um Serviço Autorizador Externo **antes** de finalizar a transferência, **para** simular a validação de uma instituição de pagamento. | **Integração:** O sistema deve fazer uma requisição `GET` para um **serviço *mock*** de autorização. <br/>**Condição:** A transferência só deve ser concluída se a resposta do *mock* for de **autorização**. <br/>**Rejeição:** Se o *mock* retornar **não-autorizado**, a transação deve ser abortada e revertida.                                                                                 |
| **US-005** | **Como** o sistema, **eu devo** garantir a atomicidade da operação de transferência, **para que** o dinheiro nunca se perca (garantia de consistência transacional).           | **Transação Atômica:** A operação de débito e crédito (e a consulta ao autorizador) deve ser executada como uma **transação única**. <br/>**Reversão:** Em caso de falha em qualquer etapa (não-autorização, falha no crédito, erro de sistema), a transação completa deve ser **revertida (rollback)** e o valor deve voltar integralmente para a carteira do usuário pagador.                      |
| **US-006** | **Como** o sistema, **eu quero** enviar uma notificação para o Usuário (Comum ou Lojista) recebedor de um pagamento, **para** informá-lo sobre o crédito em sua carteira.      | **Integração:** O sistema deve invocar um **serviço *mock* de notificação** (e-mail/SMS) com uma requisição `POST` após a transferência ser concluída com sucesso. <br/>**Tolerância a Falhas:** A falha ou instabilidade no serviço de notificação **NÃO** deve impedir a conclusão da transação de transferência de fundos. O processo de notificação deve ser **assíncrono** ou desacoplado.      |
| **US-007** | **Como** o sistema, **eu devo** expor todas as funcionalidades de forma que sigam o padrão **RESTFul**, **para** garantir interoperabilidade e boas práticas de arquitetura.   | **Padrão:** Todos os *endpoints* (e.g., `/users`, `/transactions`) devem seguir os princípios REST (uso correto de verbos HTTP, *stateless*, recursos identificáveis).                                                                                                                                                                                                                               |


## Tecnologias Utilizadas

Liste as principais tecnologias, frameworks e ferramentas utilizadas no projeto.

* **Linguagem de Programação:** `Java 21`
* **Framework Web/API:** `Spring Boot`
* **Banco de Dados:** `PostgreSQL, H2 (para testes)`
* **Ferramentas:** `Docker, Docker Compose, Flyway`

### Instalação e Execução

1.  **Clone o Repositório:**
    ```bash
    git clone git@github.com:moraiiss/ms-payments-fake-api.git
    cd ms-payments-fake-api
    ```

2.  **Configuração de Variáveis de Ambiente:**
    Crie um arquivo `.env`  na raiz do projeto, configurando as variáveis essenciais.
    ```bash
      make copy-env
    ```

3.  **Iniciando com Docker (Recomendado):**
    Utilize o Docker Compose para subir a aplicação e o banco de dados.
    ```bash
      make up
    ```
    *A aplicação estará disponível em `http://localhost:8081/`.*

4.  **Execução Local (Alternativa):**
    ```bash
      make start
    ```

## Endpoints da API RESTful

_todo_

## Desenvolvedores

* [Seu Nome/GitHub] - [Seu Perfil no LinkedIn]

## Licença

Este projeto está licenciado sob a Licença [Nome da Licença, ex: MIT] - veja o arquivo [LICENSE.md] para detalhes.