# TotemCore
> Software de coleta de dados via totens digitais, configurável e multiplataforma.
> 
![GitHub License](https://img.shields.io/github/license/fbvictorhugo/totem-core)
![KMP](https://img.shields.io/badge/framework-KMP-purple)

## Conceito do Software

**TotemCore** é um framework/app-base para coleta de informações através de um **totem digital**.
O projeto é pensado para funcionar de forma **multiplataforma** (Android / Desktop / iOS).

Ele permite criar rapidamente aplicações para:

* 🤝 Cadastro para doações
* 🏘️ Mapeamento de necessidades
* 📊 Pesquisa de opinião
* 🎫 Registro em eventos

A ideia central é simples:

**Um totem digital configurável que coleta dados estruturados e envia essas informações para uma base central.**

---

## 📱 Fluxo de Telas

### 1) 📮 Tela Inicial

**Objetivo:** explicar rapidamente o propósito do totem.

**Elementos:**

* Logo do projeto
* Texto explicativo
* Botão **Iniciar**

---

### 2) 👤 Tela Dados Pessoais

**Objetivo:** coletar informações básicas do usuário.

**Elementos:**

* Informações básicas: (Nome, Telefone, Bairro, Email)
* Botão **Avançar**

---

### 3) 🎯 Tela Interesses / Necessidades

**Objetivo:** mapear necessidades ou interesses conforme o contexto do projeto.

**Elementos:**

* Cards selecionáveis baseados em uma lista de necessidades configurável
* Botão **Avançar**

---

### 4) ✔️ Tela Revisão

**Objetivo:** permitir a revisão dos dados coletados antes do envio.

**Elementos:**

* Resumo de todos os dados informados
* Campo de observação (opcional)
* Botão **Concluir**

---

## 📤 Envio de Dados

O projeto busca ter uma arquitetura simples e modular para o envio das informações coletadas.

A origem/destino dos dados poderá ser configurada facilmente, permitindo diferentes formas de integração, como por exemplo:

* API REST
* Envio por email
* Envio por WhatsApp
* Exportação para arquivo (texto, CSV ou JSON)

Essa abordagem permite que o **TotemCore** seja adaptado rapidamente para diferentes tipos de projetos e necessidades.





