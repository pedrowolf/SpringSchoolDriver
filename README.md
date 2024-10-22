# SpringSchoolDriver
Projeto Spring de um microservice para gerenciamento de Rotas de vans e ônibus escolares.<br>
Nesse projeto é utilziado conceitos de SOLID, além disso é aplicado o pattern de DDD, separando a aplicação em camadas.<br>
As camadas são basicamente aplicação, domínio e infraestrutura, a ideia é que toda regra de negócio fique na camada de domínio e caso precise fazer alteração na camada de aplicação e infra não afete a regra negocial do projeto, por exemplo podemos deixar de utilizar framework Spring para outro framework como Quarkus e com algumas alterações somente nas camadas de infraestrutura e aplicação conseguir deixar o projeto rodando, sem afetar o core da mesma que está na camada de domínio.<br>
Na aplicação é feito o consumo de uma API externa de consulta de CEPs, que atualmente está mockada na AWS, utilizando um API Gateway com Recurso mockado.

Funções do microserviço:
- Gerenciar o cadastro de alunos;
- Gerenciar rotas de ida e volta da escola, iniciando uma rota, incluindo e removendo alunos nessa rota.

### Tecnologias usadas
- Java 17
- Spring Boot
- Spring Web
- Spring Data | JPA
- Spring Validation
- H2 Database
- Docker
- PostgreSQL
- Lombok
- Swagger
- Mapstruct

##### Container Docker
Para subir a base PostgreSQL localmente, foi utilizada a imagem oficial PostgreSQL no docker(https://hub.docker.com/_/postgres) com a definição das seguintes ENVs:
- POSTGRES_PASSWORD=[SUA_SENHA]
- POSTGRES_USER=[SEU_USER]
- POSTGRES_DB=[NOME_BASE]

##### Possíveis evoluções
- Cadastro de foto do aluno e guardar o arquivo em um bucket AWS S3
- Sistema de gerenciamento para motoristas, um microserviço separado para cadastrar e gerenciar motoristas que vão usar o app
- Sistema de login para os motoristas
- Aviso aos responsáveis do aluno quando o mesmo for incluido em uma rota, aviso via SMS ou por notificação push utilizando o AWS SNS

##### Possível cenário de deploy
- Não utilizar ddl-auto=update, mas sim utilizar migrations ou outra ferramenta de implantação de SQLs
- Criação de um RDS PostgreSQL na AWS para servir de base de prod
- Criação de AWS EKS para gerenciamento e deploy da aplicação em um container
- Utilização de uma pipeline Jenkins para start do deploy, ou utilização de um webhook com git que dispara o build automaticamente
- Criação de um gateway para que o front chame os microservices por uma rota definida nesse gateway
- Utilização de um serviço de properties remotas na nuvem
