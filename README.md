# **My-cars** Application
API ReST Full simples para cadastro de usuários e carros.

### Front-end
Angular.io v^8.3.5
NodeJs v10.16.0

### Back-end
Java Spring boot application v2.1.9.RELEASE
Oauht2 v2.0.14

### Resumo

A aplicação consiste em uma API autenticada e autorizado com Oauth2 e JWT Token registrada num Eureka server hospedado no heroku e com banco de dados H2.

Existe também uma documentação do swagger que pode ser encontrada nesse link : https://app-my-cars.herokuapp.com/v2/api-docs 

### Executando a aplicação front-end
#### localhost
Basta [clona](https://github.com/wellingtonsilvamelo/front-my-cars.git "clona")r o repositório do git, descompactar em qualquer pasta de sua preferência, entrar no diretório raiz da aplicação e executar os seguinte comando:
```bash
    npm install
    ng serve
```

#### cloud
Basta acessar esse link: https://tw-my-cars-front.herokuapp.com/

### Executando aplicação back-end
#### localhost

[Clone](https://github.com/wellingtonsilvamelo/my-cars.git "Clone") o o projeto no repositório do git, descopacte em uma pasta de sua preferência, entre no diretório raiz do projeto e execute os seguintes comados:
```bash
mvn package
cd target
java -jar my-cars-1.0.0.jar
```
