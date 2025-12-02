TP 5 : Héritage   
Architecture du Projet Atelier_auto
Structure réelle du projet
Atelier_auto/
│
├── .idea/                              # Configuration IntelliJ IDEA
│   └── libraries/                      # Dépendances IDEA
│
├── src/
│   ├── app/
│   │   └── AppTest.java
│   │
│   ├── connexion/
│   │   ├── ConnectionManager.java
│   │   ├── DatabaseConfig.java
│   │   └── JDBCConnection.java
│   │
│   ├── dao/
│   │   ├── ClientDAO.java
│   │   ├── VehiculeDAO.java
│   │   ├── ReparationDAO.java
│   │   ├── UtilisateurDAO.java
│   │   └── BaseDAO.java
│   │
│   ├── entities/
│   │   ├── Client.java
│   │   ├── Vehicule.java
│   │   ├── Reparation.java
│   │   └── Utilisateur.java
│   │
│   ├── services/
│   │   ├── ClientService.java
│   │   ├── VehiculeService.java
│   │   ├── ReparationService.java
│   │   ├── UtilisateurService.java
│   │   └── ServiceFactory.java
│   │
│   ├── ui/
│   │   ├── Main.java
│   │   ├── LoginFrame.java
│   │   ├── DashboardFrame.java
│   │   ├── ClientFrame.java
│   │   ├── VehiculeFrame.java
│   │   ├── ReparationFrame.java
│   │   ├── UtilityUI.java
│   │   └── Panels/
│   │       ├── ClientPanel.java
│   │       ├── VehiculePanel.java
│   │       └── ReparationPanel.java
│   │
│   └── images/
│       ├── logo.png
│       ├── icon_client.png
│       ├── icon_vehicule.png
│       ├── icon_reparation.png
│       └── icon_user.png
│
├── build/                              # 🔨 Fichiers de compilation
│   ├── classes/                        # Classes compilées (.class)
│   │   ├── app/
│   │   ├── connexion/
│   │   ├── dao/
│   │   ├── entities/
│   │   ├── images/
│   │   ├── services/
│   │   └── ui/
│   ├── empty/
│   └── generated-sources/
│       └── ap-source-output/
│
├── dist/                               # 📦 Distribution
│   ├── Atelier_auto.jar                # JAR exécutable
│   └── lib/                            # Dépendances JAR
│       └── mysql-connector-java-*.jar
│
├── out/                                # Sortie compilée (IntelliJ)
│   └── production/
│       └── Atelier_auto/
│           ├── app/
│           ├── connexion/
│           ├── dao/
│           ├── entities/
│           └── services/
│
├── nbproject/                          # Configuration NetBeans
│   └── private/                        # Fichiers privés
│
├── Atelier_auto.iml                    # Configuration IntelliJ IDEA
├── build.xml                           # Configuration Ant
├── manifest.mf                         # Manifest JAR
└── README.md                           # Documentation
Architecture en couches (3-Tier)
┌──────────────────────────────────────────────────────────┐
│            COUCHE PRÉSENTATION (Swing UI)                │
│  Main.java → LoginFrame → DashboardFrame                 │
│  ClientFrame, VehiculeFrame, ReparationFrame             │
│  (Gère affichage et interaction utilisateur)             │
└────────────────────┬─────────────────────────────────────┘
                     │ Appels métier
┌────────────────────▼─────────────────────────────────────┐
│           COUCHE MÉTIER (Services)                       │
│  ClientService, VehiculeService, ReparationService      │
│  (Validation, logique applicative, règles métier)        │
└────────────────────┬─────────────────────────────────────┘
                     │ Requêtes CRUD
┌────────────────────▼─────────────────────────────────────┐
│      COUCHE ACCÈS AUX DONNÉES (DAO)                      │
│  ClientDAO, VehiculeDAO, ReparationDAO                   │
│  (Requêtes SQL, transformation objet/BD)                 │
└────────────────────┬─────────────────────────────────────┘
                     │ Requêtes JDBC
┌────────────────────▼─────────────────────────────────────┐
│         COUCHE CONNEXION (JDBC)                          │
│  ConnectionManager, DatabaseConfig                       │
│  (Gère les connexions à la BD)                           │
└────────────────────┬─────────────────────────────────────┘
                     │ SQL
┌────────────────────▼─────────────────────────────────────┐
│          BASE DE DONNÉES (MySQL/MariaDB)                 │
│  atelier_reparation                                      │
│  Tables: client, vehicule, reparation, utilisateur       │
└──────────────────────────────────────────────────────────┘
Flux d'exécution détaillé
DÉMARRAGE DE L'APPLICATION
        │
        ▼
    Main.java
        │
        ▼
    ┌─────────────────────────┐
    │  ConnectionManager      │ ◄─── Initialise connexion
    │  getConnection()        │      à la BD (Singleton)
    └────────────┬────────────┘
                 │
                 ▼
        ┌─────────────────────────┐
        │  LoginFrame.java        │ ◄─── Fenêtre login
        └────────────┬────────────┘
                     │ Données login
                     ▼
        ┌─────────────────────────┐
        │ UtilisateurService      │ ◄─── Validation login
        │ validateLogin()         │
        └────────────┬────────────┘
                     │
                     ▼
        ┌─────────────────────────┐
        │  UtilisateurDAO         │ ◄─── Recherche utilisateur
        │  findByEmail()          │      en BD
        └────────────┬────────────┘
                     │ ✓ Login OK
                     ▼
        ┌─────────────────────────┐
        │ DashboardFrame.java     │ ◄─── Menu principal
        └────────────┬────────────┘
                     │
        ┌────────────┼────────────┬──────────────┐
        │            │            │              │
        ▼            ▼            ▼              ▼
   ClientFrame  VehiculeFrame ReparationFrame  SettingsFrame
        │            │            │              │
        ▼            ▼            ▼              ▼
   ClientService VehiculeService ReparationService UserService
        │            │            │              │
        ▼            ▼            ▼              ▼
   ClientDAO    VehiculeDAO   ReparationDAO  UtilisateurDAO
        │            │            │              │
        └────────────┼────────────┼──────────────┘
                     │
                     ▼
         ConnectionManager (JDBC)
                     │
                     ▼
              MySQL Database
Flux CRUD (Exemple: Ajouter un client)
UI Layer (ClientFrame)
    │
    ├─ Saisir données client (nom, prénom, téléphone)
    ├─ Clic bouton "Ajouter"
    │
    ▼
Service Layer (ClientService)
    │
    ├─ Valider données (non vide, format téléphone, etc.)
    ├─ Appel: clientDAO.save(client)
    │
    ▼
DAO Layer (ClientDAO)
    │
    ├─ Générer requête SQL INSERT
    ├─ Exécuter via ConnectionManager
    │
    ▼
JDBC Layer (ConnectionManager)
    │
    ├─ Obtenir connexion
    ├─ Exécuter PreparedStatement
    │
    ▼
Database
    │
    ├─ INSERT INTO client(...) VALUES(...)
    │
    ▼
Retour (Success/Error) → DAO → Service → UI (Afficher résultat)
Dépendances entre modules
Main.java
    │
    ├─► ConnectionManager (Singleton)
    │        │
    │        └─► MySQL Driver
    │
    ├─► LoginFrame
    │        │
    │        └─► UtilisateurService
    │             └─► UtilisateurDAO
    │                  └─► Utilisateur (Entity)
    │
    └─► DashboardFrame
         ├─► ClientFrame ──► ClientService ──► ClientDAO ──► Client (Entity)
         ├─► VehiculeFrame ──► VehiculeService ──► VehiculeDAO ──► Vehicule (Entity)
         └─► ReparationFrame ──► ReparationService ──► ReparationDAO ──► Reparation (Entity)
Technologies et dépendances
Composant	Technologie	Version
Langage	Java	8+
IDE	NetBeans / IntelliJ IDEA	-
GUI	Swing	JDK natif
Base de données	MySQL/MariaDB	10.4+
Driver JDBC	mysql-connector-java	8.0+
Build	Apache Ant	-
Compilation	javac	JDK 8+
Patterns et principes utilisés
Pattern	Utilisation
MVC	Séparation Model-View-Controller
DAO (Data Access Object)	Abstraction de l'accès aux données
Service Layer	Logique métier centralisée
Singleton	ConnectionManager (une seule instance)
Factory	ServiceFactory pour création services
Entity	Classes modèle (Client, Vehicule, etc.)
Points d'entrée
Main.java (ui/) : Point d'entrée principal de l'application
LoginFrame.java (ui/) : Première écran - authentification
DashboardFrame.java (ui/) : Menu après authentification
Fichiers importants de configuration
build.xml : Configuration Ant pour compilation
manifest.mf : Manifest JAR (classe principale, version)
Atelier_auto.iml : Configuration IntelliJ IDEA


Exercice 1:
![image alt](https://github.com/laouysalma/Tp5Java/blob/main/Ex1.png?raw=true)

Exercice 2:
![image alt](https://github.com/laouysalma/Tp5Java/blob/main/Ex2.png?raw=true) 

Exercice 3: 
![image alt](https://github.com/laouysalma/Tp5Java/blob/main/Ex3.png?raw=true)

Exercice 4:
![image alt](https://github.com/laouysalma/Tp5Java/blob/main/Ex4.png?raw=true)

