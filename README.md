# Pagamentos Fake API

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

## Stack

- Java 21
- Spring Boot
- PostgreSQL
