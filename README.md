# Gestion des livres — Spring Boot

## Description
Application de gestion de livres avec :
- Interface web Thymeleaf
- API REST JSON
- Persistance via Spring Data JPA (H2 en mémoire)
- Documentation OpenAPI/Swagger

## Architecture
- Couche présentation :
  - Contrôleur REST : `BookController` (/api/books)
  - Contrôleur MVC : `BookWebController` (/books)
- Couche métier : `BookService` / `BookServiceImpl`
- Couche persistance : `BookRepository`
- Entité : `Book`

## Choix techniques
- Spring Boot 3.3
- Spring Data JPA + H2
- Thymeleaf pour les vues serveur
- Springdoc OpenAPI pour Swagger UI
- Validation Bean Validation (annotations sur l’entité)

## Accès utiles
- Interface web : `http://localhost:8080/books`
- API REST : `http://localhost:8080/api/books`
- Swagger UI : `http://localhost:8080/swagger-ui.html`
- Console H2 : `http://localhost:8080/h2-console`

## Lancer le projet
```bash
./mvnw spring-boot:run
```
