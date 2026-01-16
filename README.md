# 📱 Mobile Architecture Lab — Android, KMP & Product Mindset

Projeto de estudo e consolidação técnica focado em **desenvolvimento mobile moderno**, cobrindo **Android (Kotlin + Jetpack Compose)**, **Kotlin Multiplatform (KMP)**, **arquitetura escalável**, **testes**, **cloud**, **CI/CD** e **mentalidade de produto**.

Este repositório foi estruturado como um **roteiro evolutivo do nível básico ao sênior**, simulando decisões reais enfrentadas em times de produto de alta escala.

---

## 🎯 Objetivo do Projeto

- Consolidar **fundamentos sólidos de mobile**
- Dominar **UI declarativa e gerenciamento explícito de estado**
- Aplicar **arquitetura limpa, modularização e testes com propósito**
- Explorar **Kotlin Multiplatform** de forma pragmática
- Demonstrar **visão de produto, impacto e liderança técnica**

---

## 🧭 Estrutura Evolutiva (0 → 100%)

O projeto está dividido em **5 módulos progressivos**, onde cada etapa depende da anterior.

mobile-architecture-lab/
│
├── module-1-foundations/
├── module-2-compose-state/
├── module-3-architecture-tests/
├── module-4-kmp-cloud/
└── module-5-product-ci-cd/

---

## 🔹 Módulo 1 — Fundamentos Mobile Sólidos

**Foco**
- Kotlin idiomático
- Lifecycle Android
- ViewModel + StateFlow
- Concorrência com Coroutines

**Conceitos-chave**
- Estado imutável
- Separação entre lógica e UI
- Resiliência a recriação de tela

**Entrega**
- App simples com estados explícitos (`Loading`, `Success`, `Error`)
- UI totalmente reativa ao estado

---

## 🔹 Módulo 2 — Jetpack Compose & Gerenciamento de Estado

**Foco**
- UI declarativa
- Unidirectional Data Flow (UDF)
- State Hoisting
- Recomposição e side effects

**Decisões importantes**
- Nenhuma lógica dentro de Composables
- ViewModel como fonte única de verdade
- Estado sempre explícito e previsível

---

## 🔹 Módulo 3 — Arquitetura, Modularização & Testes

**Foco**
- MVVM bem definido
- Separação clara de responsabilidades
- Modularização por feature
- Testes com propósito

**Estrutura típica**

core-domain/
core-data/
feature-home/
feature-details/


**Testes**
- Unitários: regras de negócio
- Integração: fluxo entre camadas
- UI tests: jornadas críticas
- Snapshot testing: regressão visual

---

## 🔹 Módulo 4 — Kotlin Multiplatform, Cloud & Escala

**Foco**
- KMP como estratégia (não hype)
- Compartilhamento de domínio
- UI nativa por plataforma
- Resiliência a falhas

**Integrações**
- APIs REST
- Firebase (Auth, Analytics, Push)
- Cache local e offline-first
- Tratamento explícito de erro e retry

---

## 🔹 Módulo 5 — Produto, CI/CD & Liderança Técnica

**Foco**
- Automação de builds
- Pipelines CI/CD
- Versionamento e publicação
- Métricas e analytics
- Comunicação técnica clara

**Mentalidade**
- Decisões guiadas por impacto
- Colaboração com design e produto
- Documentação de trade-offs técnicos

---

## 🧪 Stack Tecnológica

- **Linguagem:** Kotlin
- **UI Android:** Jetpack Compose
- **Estado:** StateFlow
- **Arquitetura:** MVVM + clean boundaries
- **Multiplatform:** Kotlin Multiplatform (KMP)
- **Testes:** JUnit, Espresso, Mockito, Snapshot Testing
- **Cloud:** Firebase / APIs REST
- **CI/CD:** Pipelines automatizados
- **Analytics:** Firebase / ferramentas de métricas

---

## 🧠 Princípios Aplicados

- UI não conhece infraestrutura
- Estado explícito e imutável
- Falhas são tratadas como cenário normal
- Testes existem para gerar confiança, não métricas vazias
- Arquitetura serve ao produto, não o contrário

---

**Testes**
- Unitários: regras de negócio
- Integração: fluxo entre camadas
- UI tests: jornadas críticas
- Snapshot testing: regressão visual

---

**Mentalidade**
- Decisões guiadas por impacto
- Colaboração com design e produto
- Documentação de trade-offs técnicos

---

📌 Observação Final
Este projeto não tem como objetivo ser um app comercial,
mas sim demonstrar maturidade técnica, capacidade de decisão e visão de produto,
alinhadas ao desenvolvimento mobile moderno em ambientes de alta escala.
👤 Autor
Luiz Camilo
Mobile Engineer — Android | Kotlin | Arquitetura | Multiplatform

---



