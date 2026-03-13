# Itaú Unibanco - API de Transações e Estatísticas

Este projeto é uma API REST desenvolvida para o desafio de programação do Itaú. A aplicação recebe transações financeiras e calcula estatísticas em tempo real baseada nos últimos 60 segundos.

## 🛠️ Tecnologias Utilizadas

- **Java 21**: Versão mais recente com suporte a records e melhor performance.
- **Spring Boot 3.4.2**: Framework base para a construção da API.
- **Maven**: Gerenciador de dependências e build.
- **Lombok**: Para redução de código boilerplate (logs e getters).
- **SpringDoc OpenAPI**: Documentação interativa via Swagger UI. Disponível em: `http://localhost:8080/swagger-ui/index.html`
- **Actuator**: Endpoint para health Check da aplicação. Disponível em: `http://localhost:8080/actuator/health`
- **Docker**: Containerização da aplicação para portabilidade.

## ⚙️ Arquitetura e Decisões Técnicas

- **Armazenamento em Memória**: Seguindo as restrições do desafio, os dados não são persistidos em bancos de dados. Utilizei uma `Collections.synchronizedList` no `TransacaoService` para garantir segurança em acessos concorrentes (Thread-Safety).
- **Cálculo de Estatísticas**: Implementado utilizando `parallelStream()` da API de Streams do Java para garantir baixa latência. O cálculo é feito de forma eficiente através da classe `DoubleSummaryStatistics`.
- **Validações**: A API rejeita transações com valores negativos ou datas futuras, retornando o status `422 Unprocessable Entity` conforme o requisito.

# Funcionalidades Implementadas

- Recebimento de transações via API REST
- Cálculo de estatísticas em janela de **60 segundos**
- Validação de dados da requisição
- Tratamento de erros com respostas HTTP adequadas
- Testes automatizados
- Logs estruturados
- Observabilidade da aplicação
- Containerização com Docker
- Documentação automática da API

## 📂 Hierarquia do Projeto

Abaixo está a estrutura de pastas do projeto, organizada por domínios de negócio para facilitar a manutenção:

```text
Desafio-Itau-backend/
├── desafio-itau/
│   ├── src/
│   │   ├── main/java/br/com/lucasconceicao/desafioitau/
│   │   │   ├── Estatisticas/          # Domínio de Cálculo de Estatísticas
│   │   │   │   ├── Controller/
│   │   │   │   ├── Model/
│   │   │   │   └── Service/
│   │   │   └── Transacao/             # Domínio de Gestão de Transações
│   │   │       ├── Controller/
│   │   │       ├── Model/
│   │   │       └── Service/
│   │   └── resources/
│   │       └── application.properties # Configurações do Spring
│   ├── dockerfile                     # Instruções de Containerização
│   ├── .dockerignore                  # Filtro de arquivos para o Docker
│   └── pom.xml                        # Dependências Maven
└── DESAFIO.md                      # Regras originais do desafio
```

## 🚀 Como Executar

### Pré-requisitos
- Docker instalado em sua máquina.

### Execução via Docker (Recomendado)
Na pasta raiz do projeto, execute os seguintes comandos:

1. Construir a Imagem:
Este comando lê as instruções do seu Dockerfile, baixa as dependências e compila o código Java dentro de um ambiente Linux isolado.
```bash
docker build -t desafio-itau-api .
```
2. Iniciar o Container
Após o build terminar, este comando cria e liga o container baseado na imagem gerada.
isolado.
```bash
docker run -p 8080:8080 desafio-itau-api
```
