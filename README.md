# CNI-DESAFIO

## Descrição

Projeto backend desenvolvido em **Java com Spring Boot**, que integra com APIs externas e possui proteção contra exposição de segredos sensíveis (ex: credenciais Google Cloud). A aplicação oferece endpoints REST para criação e consulta de chamados, além de analisar sentimentos via Google Cloud Natural Language API.

---

## Tecnologias Utilizadas

- **Java 17+**: linguagem robusta e amplamente utilizada em sistemas corporativos.
- **Spring Boot**: framework para desenvolvimento rápido de aplicações web e APIs REST.
- **Google Cloud Natural Language API**: análise de sentimentos em textos dos chamados.
- **Git + GitHub**: versionamento de códigos.
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
````

### 2. Configurar o application.properties

```bash
  google.credentials.path=${GOOGLE_CREDENTIALS_PATH}
  server.port=8080
```

### 3. Rodar a aplicação
 ```bash
  mvn clean spring-boot:run
  A aplicação estará disponível em http://localhost:8080.
```

### 4. Exemplos de Chamadas aos Endpoints
```bash
  curl -X GET http://localhost:8080/api/tickets
  
  curl -X POST http://localhost:8080/api/tickets \
  -H "Content-Type: application/json" \
  -d '{"titulo": "Problema no sistema", "descricao": "Erro ao enviar formulário"}'
  
  curl -X PUT http://localhost:8080/api/tickets/{id} \
  -H "Content-Type: application/json" \
  -d '{"status": "CONCLUIDO"}'
```
