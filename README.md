# Rotina-API

## Descrição
**Rotina-API** é um sistema desenvolvido em Java utilizando Spring Framework para gerenciar hábitos e rotinas de usuários. A aplicação inclui autenticação segura via JWT, integração com PostgreSQL e operações CRUD completas para o gerenciamento de hábitos.

## Funcionalidades Principais

### Autenticação de Usuários
- Implementada com Spring Security e JWT.
- Registro de novos usuários.
- Login seguro.
- Recuperação de senha.

### Gestão de Hábitos
- Cadastro, edição, exclusão e visualização de hábitos.
- Associação de hábitos aos usuários cadastrados.

### Interface Intuitiva
- APIs RESTful para comunicação eficiente.

## Tecnologias Utilizadas
- **Java & Spring Boot**: Backend e lógica de negócios.
- **JWT**: Autenticação segura.
- **PostgreSQL**: Banco de dados relacional.
- **Spring Security**: Segurança da aplicação.

## Como Executar
1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/Rotina-API.git

2. Configure o banco de dados no arquivo application.properties.
3. Execute o comando
  ```bash
    mvn spring-boot:run

