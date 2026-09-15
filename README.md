# API PEDIDOS

API REST para gerenciamento de pedidos, desenvolvida como trabalho da disciplina de **Sistemas Distribuídos**. A aplicação permite criar, consultar, listar e alterar o status de pedidos, utilizando **Spring Boot**, **PostgreSQL** e **Docker**.

## 📌 Sobre o projeto

O projeto expõe uma API para o gerenciamento do ciclo de vida de um pedido, com as seguintes funcionalidades:

- Criação de pedidos
- Consulta de um pedido específico
- Listagem de todos os pedidos criados
- Alteração do status de um pedido
- Endpoint de *health check* para verificar se a aplicação está no ar

## 🛠️ Tecnologias utilizadas

- **Java 17**
- **Spring Boot** (Web/MVC, Data JPA)
- **Gradle** (build via wrapper, não é necessário instalar o Gradle na máquina)
- **PostgreSQL** (banco de dados relacional)
- **Docker e Docker Compose** (containerização da API e do banco)
- **Lombok**

## 📂 Estrutura principal do repositório

```
API_PEDIDOS/
├── src/                 # Código-fonte da aplicação (controllers, entidades, services, etc.)
├── gradle/wrapper/       # Wrapper do Gradle (permite build sem instalar o Gradle)
├── build.gradle          # Configurações e dependências do projeto
├── settings.gradle        # Nome do projeto
├── Dockerfile            # Build da imagem da API
├── compose.yaml           # Orquestração da API + banco de dados via Docker Compose
├── .env.example            # Exemplo das variáveis de ambiente necessárias
├── gradlew / gradlew.bat    # Scripts para rodar o Gradle sem instalação local
└── README.md
```

## ✅ Pré-requisitos

Antes de começar, você vai precisar ter instalado na sua máquina:

- [Git](https://git-scm.com/)
- [Docker](https://www.docker.com/) e [Docker Compose](https://docs.docker.com/compose/)

## 🚀 Como executar o projeto

Essa é a forma mais simples, pois o Docker Compose já sobe tanto a API quanto o banco de dados PostgreSQL configurados e conectados entre si.

### 1. Clone o repositório

```bash
git clone https://github.com/CaioGiacon/API_PEDIDOS.git
cd API_PEDIDOS
```

### 2. Configure as variáveis de ambiente

O projeto já possui um arquivo de exemplo chamado `.env.example`. Copie-o para um arquivo `.env`:

```bash
# Linux / macOS
cp .env.example .env

# Windows (PowerShell)
copy .env.example .env
```

Abra o arquivo `.env` e, se quiser, ajuste os valores (não é obrigatório para rodar localmente):

```env
POSTGRES_DB=pedidos_db
POSTGRES_USER=usuario
POSTGRES_PASSWORD=senha
```

### 3. Suba os containers

Com o Docker em execução na sua máquina, rode:

```bash
docker compose up --build
```

Esse comando vai:
1. Buildar a imagem da API a partir do `Dockerfile`;
2. Subir um container do PostgreSQL (`postgres`);
3. Subir o container da API (`pedidos`), já conectado ao banco.

### 4. Acesse a aplicação

Após a subida dos containers, a API estará disponível em:

```
http://localhost:8000
```

Para parar a aplicação, use `Ctrl + C` no terminal e, em seguida, se quiser remover os containers:

```bash
docker compose down
```

Se quiser apagar também os dados persistidos no banco (volume `pgdata`):

```bash
docker compose down -v
```

## 🧪 Testando a API (Postman)

Com os containers em execução, você pode testar os endpoints da API utilizando o [Postman](https://www.postman.com/downloads/). Abaixo está o passo a passo para cada um dos principais endpoints.

> 💡 Dica: crie uma **Collection** no Postman (botão **New → Collection**) e vá salvando cada uma das requisições abaixo dentro dela, facilitando reutilizá-las e compartilhá-las com o restante do grupo.

### 1. Criar um pedido

1. Clique em **New → HTTP Request**.
2. Selecione o método **POST**.
3. Informe a URL:
   ```
   http://localhost:8000/pedidos
   ```
4. Na aba **Body**, selecione **raw** e o formato **JSON**, e informe o payload:
   ```json
   {
       "cliente" : "maria",
       "produto" : "sandalha",
       "quantidade" : 2,
       "valor_unitario" : 15
   }
   ```
5. Clique em **Send**. A API deve retornar o pedido criado, com o `id` gerado.

### 2. Consultar um pedido pelo ID

1. Crie uma nova requisição com método **GET**.
2. Informe a URL, substituindo `1` pelo `id` do pedido que deseja consultar:
   ```
   http://localhost:8000/pedidos/1
   ```
3. Clique em **Send**. A API retorna o pedido correspondente ao `id` informado.

### 3. Listar todos os pedidos

1. Crie uma nova requisição com método **GET**.
2. Informe a URL:
   ```
   http://localhost:8000/pedidos
   ```
3. Clique em **Send**. A API retorna a lista com todos os pedidos cadastrados.

### 4. Alterar o status de um pedido

1. Crie uma nova requisição com método **PATCH**.
2. Informe a URL, substituindo `5` pelo `id` do pedido que deseja atualizar:
   ```
   http://localhost:8000/pedidos/5/status
   ```
3. Na aba **Body**, selecione **raw** e o formato **JSON**, e informe apenas o novo status:
   ```json
   {
       "status": "cancelado"
   }
   ```
4. Clique em **Send**. A API atualiza e retorna o status do pedido informado.

### 5. Verificar se a aplicação está funcionando (health check)

1. Crie uma nova requisição com método **GET**.
2. Informe a URL:
   ```
   http://localhost:8000/health
   ```
3. Clique em **Send**. Se a aplicação estiver operacional, a resposta será:
   ```json
   {
       "mensagem": "Aplicação operacional",
       "status": "ok"
   }
   ```

## 🧑‍💻 Rodando os testes

```bash
# Linux / macOS
./gradlew test

# Windows
gradlew.bat test
```

## 🐘 Acessando o banco de dados diretamente

Com os containers em execução, você pode acessar o PostgreSQL com:

```bash
docker exec -it postgres_pedidos psql -U usuario -d pedidos_db
```

(substitua `usuario` e `pedidos_db` pelos valores definidos no seu `.env`, caso tenha alterado).

## 👤 Autor

Projeto desenvolvido por [Caio Giacon](https://github.com/CaioGiacon) para a disciplina de Sistemas Distribuídos.