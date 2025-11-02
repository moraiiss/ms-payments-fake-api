# Pagamentos Fake

## Objetivo

> O **Pagamentos Fake** é uma plataforma de pagamentos simplificada, que permite a criação de contas de usuários e lojistas, depósitos e transferências de valores entre carteiras virtuais.  
O sistema foi projetado para simular o fluxo básico de transações financeiras, incluindo autorização externa e notificações de recebimento, com foco em boas práticas de arquitetura e consistência transacional.

---

## Requisitos

> A seguir estão algumas regras de negócio que são importantes para o funcionamento do PicPay Simplificado:

- Para ambos tipos de usuário, precisamos do Nome Completo, CPF, e-mail e Senha. CPF/CNPJ e e-mails devem ser únicos no sistema. Sendo assim, seu sistema deve permitir apenas um cadastro com o mesmo CPF ou endereço de e-mail;
- Usuários podem enviar dinheiro (efetuar transferência) para lojistas e entre usuários; 
- Lojistas só recebem transferências, não enviam dinheiro para ninguém; 
- Validar se o usuário tem saldo antes da transferência; 
- Antes de finalizar a transferência, deve-se consultar um serviço autorizador externo, use este mock https://util.devi.tools/api/v2/authorize para simular o serviço utilizando o verbo GET; 
- A operação de transferência deve ser uma transação (ou seja, revertida em qualquer caso de inconsistência) e o dinheiro deve voltar para a carteira do usuário que envia;
- No recebimento de pagamento, o usuário ou lojista precisa receber notificação (envio de email, sms) enviada por um serviço de terceiro e eventualmente este serviço pode estar indisponível/instável. Use este mock https://util.devi.tools/api/v1/notify)) para simular o envio da notificação utilizando o verbo POST; 
- Este serviço deve ser RESTFul.

---

## Requisitos Não Funcionais

_todo_

---

## Fluxos de Usuário

_todo_

---

## Critérios de Aceitação

- [ ] O sistema deve impedir o cadastro de e-mails ou CPF/CNPJ duplicados.
- [ ] Usuários sem saldo suficiente não podem transferir valores.
- [ ] Lojistas não podem iniciar transferências.
- [ ] Toda a transferência deve consultar o serviço autorizador externo.
- [ ] A operação de transferência deve ser atômica (sem inconsistências parciais).
- [ ] Notificações devem ser enviadas ao destinatário após o recebimento.
- [ ] A API deve seguir o padrão REST, com respostas JSON e status HTTP corretos.
- [ ] O sistema deve ter testes automatizados para os principais fluxos.

---

## Dependências

- **Spring Boot 3.x**
- **Spring Data JPA**
- **Spring Web**
- **Spring Security (JWT)**
- **Flyway**
- **PostgreSQL**
- **JUnit 5 / Mockito**
- **Feign Client (para mocks de serviços externos)**
