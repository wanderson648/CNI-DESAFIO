# CNI-DESAFIO

## Descrição

Projeto backend desenvolvido em **Java com Spring Boot**, que integra com APIs externas e possui proteção contra exposição de segredos sensíveis (ex: credenciais Google Cloud). A aplicação oferece endpoints REST para criação e consulta de chamados, além de analisar sentimentos via Google Cloud Natural Language API.

---

## Tecnologias Utilizadas

- **Java 17+**: linguagem robusta e amplamente utilizada em sistemas corporativos.
- **Spring Boot**: framework para desenvolvimento rápido de aplicações web e APIs REST.
- **Spring Security**: controle de acesso e segurança.
- **Google Cloud Natural Language API**: análise de sentimentos em textos dos chamados.
- **Git + GitHub**: versionamento e segurança com Push Protection para evitar exposição de segredos.
- **Maven**: gerenciamento de dependências e build.

**Justificativa da tecnologia**:  
Spring Boot foi escolhido pela sua rapidez na criação de APIs REST, facilidade de integração com diversas bibliotecas e excelente suporte para injeção de dependência, segurança e configuração. Além disso, o uso do Google Cloud Natural Language permite um processamento de linguagem natural avançado com mínimo esforço.

---

## Como Configurar e Executar a Aplicação

### 1. Preparar o ambiente

- Tenha o **Java 17+** instalado.
- Tenha o **Maven** instalado.
- Configure sua variável de ambiente com o caminho para o arquivo de credenciais do Google Cloud:

Linux/macOS:
```bash
export GOOGLE_CREDENTIALS_PATH=/caminho/para/google-credentials.json

