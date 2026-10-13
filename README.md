# Système de Gestion de Bibliothèque

Application web de gestion d'une bibliothèque : catalogue d'ouvrages, adhérents, emprunts, retours et réservations. Le projet suit une démarche complète, de l'analyse du besoin à l'implémentation, avec une application Spring Boot fonctionnelle et une interface web Thymeleaf.

> Auteure : **Kawtar Boughal** — [LinkedIn](https://www.linkedin.com/in/kawtar-boughal02)

---

## 🇫🇷 Français

### Présentation

Ce projet est un système d'information de gestion de bibliothèque. Il permet de gérer le catalogue des ouvrages, le cycle de vie des adhérents (inscription, validation des demandes d'adhésion), les emprunts et les retours de livres, ainsi que les réservations. L'application repose sur une architecture web en couches construite avec **Spring Boot**, **Spring Data JPA**, **Spring Security** et **Thymeleaf**.

Le rapport détaillé est disponible dans [rapport_final.md](./rapport_final.md).

### Contexte et problématique

Une bibliothèque doit suivre en permanence quels ouvrages elle possède, qui sont ses adhérents, quels livres sont empruntés, par qui, et depuis quand. Une gestion manuelle (cahiers, tableurs) devient rapidement source d'erreurs : ouvrages introuvables, emprunts non suivis, adhésions non validées, retards non détectés.

La problématique traitée est la suivante :

> Comment structurer et informatiser la gestion d'une bibliothèque afin de centraliser le catalogue, maîtriser le cycle des emprunts et des retours, et contrôler les adhésions des usagers, à travers une application web fiable et sécurisée ?

### Objectif

Concevoir et développer une application web de gestion de bibliothèque qui :

1. centralise le **catalogue** des ouvrages (titre, auteur, catégorie, ISBN, date d'édition, quantité) ;
2. gère les **adhérents** et la validation des demandes d'adhésion ;
3. suit les **emprunts** (emprunteur, dates de demande et de retour) et la **confirmation des retours** ;
4. propose un espace de **réservation** ;
5. protège l'accès par une **authentification** et une gestion des rôles (administrateur / utilisateur).

### Solutions développées

Le projet couvre l'ensemble de la chaîne, de l'analyse à l'application livrée :

- **Analyse et conception** : identification des acteurs (administrateur, adhérent) et des cas d'utilisation, puis modélisation du domaine sous forme d'entités et de relations.
- **Modèle de données** : cinq entités métier — `Ouvrage`, `Adherent`, `Emprunt`, `Reservation` et `Administrateur` — reliées entre elles (un ouvrage peut être emprunté et réservé ; un adhérent peut réaliser plusieurs emprunts et réservations).
- **Architecture en couches** : `Controller` (routes web) → `Service` (logique métier) → `Repository` (accès aux données JPA) → `Entity` (modèle), avec des vues **Thymeleaf** pour l'affichage.
- **Sécurité** : `Spring Security` avec page de connexion, gestion des rôles et protection des routes sensibles.
- **Fonctionnalités livrées** : ajout / modification / suppression d'ouvrages et d'adhérents, recherche par titre, emprunt avec formulaire dédié, validation des retours et validation des demandes d'adhésion.

### Données et modèle métier

L'application manipule les entités suivantes (persistées via JPA/Hibernate) :

| Entité | Rôle | Attributs principaux |
|---|---|---|
| `Ouvrage` | Livre du catalogue | titre, auteur, catégorie, ISBN, date d'édition, quantité |
| `Adherent` | Usager inscrit | nom, prénom, date de naissance, email, adresse, téléphone, type, validé |
| `Emprunt` | Prêt d'un ouvrage | emprunteur, date de demande, date de retour, validé |
| `Reservation` | Réservation d'un ouvrage | liens vers l'ouvrage, l'adhérent et l'emprunt |
| `Administrateur` | Compte d'administration | identifiant, mot de passe |

Relations principales : `Ouvrage` 1—N `Emprunt` et `Reservation` ; `Adherent` 1—N `Emprunt` et `Reservation`.

### Impact et apports

- **Centralisation** : une source unique d'information remplace les suivis dispersés.
- **Fiabilité** : les emprunts, retours et adhésions sont tracés et validés explicitement.
- **Séparation des responsabilités** : l'architecture en couches rend le code maintenable et testable.
- **Socle réutilisable** : le modèle et l'API web constituent une base pour ajouter des statistiques d'emprunt, des alertes de retard ou un moteur de recommandation (voir la section *Lien avec la Data Science et l'IA*).

### Aperçu de l'interface

Quelques écrans de l'application (données de démonstration) :

**Connexion administrateur**

![Écran de connexion](./docs/screenshots/login.png)

**Catalogue des ouvrages** (ajout, modification, recherche, emprunt, réservation)

![Catalogue des ouvrages](./docs/screenshots/catalogue.png)

**Liste des adhérents**

![Liste des adhérents](./docs/screenshots/adherents.png)

**Livres empruntés** (suivi des emprunts et confirmation des retours)

![Livres empruntés](./docs/screenshots/emprunts.png)

**Formulaire d'emprunt**

![Formulaire d'emprunt](./docs/screenshots/emprunter-form.png)

### Méthodologie de réalisation

1. **Analyse** : recueil des besoins, définition des acteurs et des règles de gestion (qui peut emprunter, quand une adhésion est validée, etc.).
2. **Conception** : traduction du métier en entités JPA et en relations, puis organisation du code en couches Controller / Service / Repository.
3. **Implémentation** : développement des controllers et des vues Thymeleaf, branchement de la sécurité Spring Security.
4. **Tests et démonstration** : compilation Maven, démarrage de l'application sur un profil de démonstration (base H2 en mémoire) et vérification des parcours (catalogue, emprunts, adhérents, formulaires).

### Lien avec la Data Science et l'IA

Ce projet est avant tout une application de gestion, mais il illustre plusieurs compétences transverses directement utiles en Data Science :

- **Modélisation et structuration de données** : concevoir un schéma relationnel cohérent (entités, clés, relations) est la première étape de tout projet data ; la qualité du modèle conditionne la qualité des analyses en aval.
- **Qualité et traçabilité des données** : la validation des adhésions, le suivi des emprunts et des retours produisent des données propres et horodatées, seules exploitable pour de l'analyse.
- **Pistes d'extension data/IA réalistes** : à partir des données d'emprunt, on peut imaginer des statistiques de fréquentation, une prédiction des retards de retour, une analyse de la demande par catégorie, ou un système de recommandation d'ouvrages. Ces évolutions nécessiteraient d'enrichir le modèle (par exemple un champ de date de retour effectif) et d'ajouter une couche d'analyse.
- **Compétences d'ingénierie** : la rigueur de l'architecture en couches, la gestion des dépendances (Maven) et le versionnement sont des prérequis pour industrialiser des pipelines de données.

### Technologies et compétences

| Technologie | Utilisation dans le projet |
|---|---|
| Java 21 | Langage principal |
| Spring Boot 3.2 | Socle de l'application et démarrage |
| Spring Web MVC | Contrôleurs et routage des pages |
| Spring Data JPA / Hibernate | Persistance et mapping objet-relationnel |
| Spring Security | Authentification et gestion des rôles |
| Thymeleaf | Moteur de templates côté serveur |
| Bootstrap / Bootswatch | Interface utilisateur responsive |
| Maven | Gestion des dépendances et build |
| H2 | Base en mémoire pour la démonstration |
| MySQL | Base de production (configurable) |

### Structure du projet

```text
.
├── src/main/java/com/project/
│   ├── SystemeGestionBibliothequeApplication.java   # point d'entrée Spring Boot
│   ├── DataSeeder.java                              # jeu de démonstration (profil "demo")
│   ├── configuration/
│   │   ├── WebSecurity.java                         # sécurité de production
│   │   └── SecurityDemoConfig.java                  # sécurité assouplie (profil "demo")
│   ├── controller/                                  # Adherent, Ouvrage, Reservation
│   ├── service/                                     # logique métier
│   ├── Repository/                                  # accès aux données (JPA)
│   └── Entity/                                      # Ouvrage, Adherent, Emprunt, Reservation, Administrateur
├── src/main/resources/
│   ├── application.properties                       # configuration MySQL (production)
│   ├── application-h2.properties                    # profil H2 (démo sans MySQL)
│   ├── application-demo.properties                  # profil demo (H2 + sécurité ouverte)
│   ├── static/images/                               # illustrations de l'interface
│   └── templates/                                   # vues Thymeleaf (12 pages)
├── docs/screenshots/                                # captures d'écran de l'application
├── pom.xml
├── mvnw / mvnw.cmd                                  # Maven wrapper
└── rapport_final.md
```

### Prérequis

- **JDK 21** (le projet cible Java 21). Vérifier avec `java -version`.
- **Maven** (le wrapper `mvnw` fourni permet de se passer d'une installation globale).
- Pour la démonstration sans base de données : rien d'autre (profil `demo` intégré avec H2).
- Pour la configuration MySQL d'origine : un serveur MySQL accessible et une base `gestion_bibliotheque`.

### Lancer la démonstration (recommandé, sans MySQL)

Le profil `demo` utilise une base **H2 en mémoire**, charge un jeu de données d'exemple et ouvre l'accès pour la démonstration :

```powershell
# Depuis la racine du projet, avec le JDK 21
$env:JAVA_HOME = "C:\Program Files\Java\jdk-21"
.\mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=demo
```

Puis ouvrir <http://localhost:8989/ouvrages>.

Les comptes de démonstration sont définis dans `WebSecurity.java` (utilisateur `user`, administrateur `admin`).

### Lancer avec MySQL (configuration d'origine)

Créer la base `gestion_bibliotheque`, puis adapter `src/main/resources/application.properties` (identifiants MySQL) et lancer :

```powershell
.\mvnw.cmd spring-boot:run
```

Compiler et exécuter les tests :

```powershell
.\mvnw.cmd clean test
```

### Limites et pistes d'amélioration

- La page **Réservations** affiche l'en-tête mais le controller ne charge pas encore la liste des réservations dans le modèle : l'affichage reste à finaliser.
- La pagination visible dans les vues est statique (les boutons 1–5 ne sont pas encore reliés à une pagination serveur).
- Les mots de passe de démonstration sont en clair dans la configuration : à remplacer par un stockage sécurisé et des rôles persistés en base pour un usage réel.
- L'encodage des attributs (accents, noms de colonnes) mérite d'être harmonisé pour faciliter la maintenance.
- Le champ de date de retour est renseigné à l'emprunt : ajouter une date de retour effective renforcerait le suivi des retards.

---

## 🇬🇧 English

### Overview

A library management web application covering the book catalogue, members, loans, returns and reservations. It is built as a layered **Spring Boot** application using **Spring Data JPA**, **Spring Security** and **Thymeleaf**, and follows a full workflow from requirements analysis to a working implementation.

The detailed report is available in [rapport_final.md](./rapport_final.md).

### Context and problem

A library must constantly track which books it owns, who its members are, which books are borrowed, by whom and since when. Manual tracking (notebooks, spreadsheets) quickly leads to errors: missing books, untracked loans, unvalidated memberships and undetected overdue returns.

> How can a library's management be structured and computerized to centralize the catalogue, control the loan/return cycle and validate memberships, through a reliable and secure web application?

### Objective

Design and build a web application that: centralizes the **catalogue**; manages **members** and membership validation; tracks **loans** and **return confirmation**; provides a **reservation** area; and protects access with **authentication** and roles.

### Data model

Five JPA entities: `Ouvrage` (book), `Adherent` (member), `Emprunt` (loan), `Reservation`, `Administrateur`. An `Ouvrage` can be borrowed and reserved; an `Adherent` can have several loans and reservations.

### Architecture

Controller → Service → Repository → Entity, with server-side Thymeleaf views. Spring Security provides the login page and role-based route protection.

### Tech stack

Java 21, Spring Boot 3.2 (Web, Data JPA, Security), Thymeleaf, Bootstrap, Maven, H2 (demo) and MySQL (production).

### Run the demo (no MySQL required)

```powershell
$env:JAVA_HOME = "C:\Program Files\Java\jdk-21"
.\mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=demo
```

Open <http://localhost:8989/ouvrages>.

### Limitations

The Reservations page header renders but the list is not yet wired to the model; pagination in the views is static; demo passwords are plaintext and should be moved to secure storage for real use.

### Author

**Kawtar Boughal** — [LinkedIn](https://www.linkedin.com/in/kawtar-boughal02)



