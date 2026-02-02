# TRAVAUX PRATIQUES
## Développement d’une application Web avec Spring Boot
### Spring Data JPA • API REST • Thymeleaf

---

## 1. Contexte général

Dans ce TP, l’étudiant doit concevoir et développer une application web complète basée sur l’écosystème Spring.

L’application devra exposer :
- une **interface web serveur** basée sur **Thymeleaf**
- une **API REST** permettant l’accès aux données au format **JSON**
- une **couche de persistance** basée sur **Spring Data JPA**

L’objectif est de mettre en œuvre une **architecture multi-couches**, conforme aux bonnes pratiques du génie logiciel.

---

## 2. Objectifs pédagogiques

À l’issue de ce TP, l’étudiant devra être capable de :
- Concevoir une application Spring Boot selon une architecture MVC
- Mettre en place une couche de persistance avec Spring Data JPA
- Exposer des fonctionnalités métier via une API REST
- Développer une interface web dynamique avec Thymeleaf
- Séparer clairement les responsabilités entre les différentes couches
- Documenter et tester une API REST avec Swagger UI

---

## 3. Périmètre fonctionnel

L’application à réaliser est une **application de gestion de livres**.

Chaque livre est caractérisé par :
- un identifiant unique
- un titre
- un auteur
- un prix

Les données devront être persistées dans une base de données relationnelle.

---

## 4. Fonctionnalités attendues

### 4.1 Interface Web (Thymeleaf)

L’interface web devra permettre :
- l’affichage de la liste complète des livres
- l’ajout d’un nouveau livre via un formulaire
- la modification des informations d’un livre existant
- la suppression d’un livre

---

### 4.2 API REST

L’application devra exposer une API REST permettant :
- la récupération de la liste des livres
- la récupération d’un livre par son identifiant
- la création d’un nouveau livre
- la mise à jour d’un livre existant
- la suppression d’un livre

Les échanges devront respecter les principes REST et utiliser les méthodes HTTP appropriées.

---

## 5. Architecture logicielle attendue

L’application devra respecter une **architecture en couches** :

- **Couche présentation**  
  - Contrôleurs MVC pour les vues Thymeleaf  
  - Contrôleurs REST pour l’API

- **Couche métier**  
  - Services applicatifs contenant la logique métier

- **Couche persistance**  
  - Repositories basés sur Spring Data JPA

---

## 6. Persistance des données (Spring Data JPA)

- Les entités doivent être correctement modélisées
- Les identifiants doivent être générés automatiquement
- Les opérations CRUD doivent être réalisées via Spring Data JPA
- Une console de gestion de la base de données doit être accessible

---

## 7. Contraintes API REST

- Les URLs doivent être orientées ressources
- Les méthodes HTTP doivent être utilisées de manière cohérente :
  - GET : lecture
  - POST : création
  - PUT : mise à jour
  - DELETE : suppression
- Les codes de retour HTTP doivent être pertinents
- Les erreurs doivent être gérées proprement

---

## 8. Interface Thymeleaf – Contraintes

- Les pages doivent être générées côté serveur
- Les données doivent être injectées dynamiquement
- Les formulaires doivent être liés aux objets métiers
- Aucune logique métier ne doit se trouver dans les vues

---

## 9. Documentation et test de l’API (Swagger UI)

L’API REST devra être documentée et testable via **Swagger UI (OpenAPI)**.

Swagger UI devra permettre :
- la visualisation de l’ensemble des endpoints
- la description des ressources exposées
- le test interactif des opérations CRUD
- l’observation des réponses et des codes HTTP

---

## 10. Tests et validation

Les étudiants devront :
- vérifier le bon fonctionnement de l’interface web
- tester l’ensemble des endpoints REST via Swagger UI
- vérifier la cohérence des données persistées
- tester les cas d’erreurs simples

---

## 11. Livrables attendus

Les livrables à fournir sont :
- le code source complet de l’application - sur Github ou Bitbucket
- un court document ( dans le readme.md ) décrivant l’architecture et les choix techniques

---

## 12. Critères d’évaluation (indicatif)

| Critère | Pondération |
|-------|-------------|
| Interface Web (Thymeleaf) | 30 % |
| API REST + Swagger UI | 35 % |
| Persistance (Spring Data JPA) | 20 % |
| Architecture et bonnes pratiques | 10 % |
| Clarté et documentation | 5 % |

---

## 13. Extensions possibles (bonus)

- Validation des données
- Pagination et tri
- Gestion centralisée des erreurs
- Sécurisation de l’application

---

## 14. Modalités de rendu

- **Date limite** : **7 Fevrier 2026 à 23h59**
- Tout retard pourra entraîner une pénalité conformément au règlement pédagogique
