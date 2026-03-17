# TotemCore
> Software de coleta de dados via totens digitais, configurável e multiplataforma.

![Status](https://img.shields.io/badge/status-in%20development-yellow)
![GitHub License](https://img.shields.io/github/license/fbvictorhugo/totem-core)
![Open Source](https://img.shields.io/badge/Open%20Source-Yes-green)
![KMP](https://img.shields.io/badge/framework-Kotlin%20Multiplatform-purple)
![Platform](https://img.shields.io/badge/platform-Android%20%7C%20Desktop%20%7C%20iOS-lightblue)
![AI Assisted](https://img.shields.io/badge/AI-Assisted-0A66C2)

## Conceito do Software

**TotemCore** é um framework/app-base para coleta de informações através de um **totem digital**.
O projeto é pensado para funcionar de forma **multiplataforma** (Android / Desktop / iOS).

Ele permite criar rapidamente aplicações para:

* 🤝 Cadastro para doações
* 🏘️ Mapeamento de necessidades
* 📊 Pesquisa de opinião
* 🎫 Registro em eventos

A ideia central é simples:

**Um totem digital configurável que coleta dados estruturados e envia/exporta essas informações para uma base central.**


---

## 📱 Fluxo de Telas

Inicialmente, as telas foram geradas utilizando o *Figma Make*, com base no fluxo descrito abaixo. 
A ferramenta de IA do Figma foi utilizada para criar a prototipagem inicial e estruturar o primeiro esboço das interfaces do aplicativo.

O link a seguir contém um live preview funcional, permitindo visualizar e navegar pelas telas geradas.
- [TotemCore on Figma Make](https://www.figma.com/make/hGeaRCde7dqU4BwYlDLShN/TotemCore-by-fbvictorhugo?p=f&t=7EKOSJttadCD0zB4-0&fullscreen=1)

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





