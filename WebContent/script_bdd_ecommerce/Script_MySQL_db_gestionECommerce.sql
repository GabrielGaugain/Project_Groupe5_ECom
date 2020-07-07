DROP DATABASE db_gestionECommerce ;
CREATE DATABASE db_gestionECommerce ;


CREATE TABLE db_gestionECommerce.categories (
id_categorie INT NOT NULL auto_increment,
nom_categorie VARCHAR (100),
description_categorie VARCHAR (200),
id_photo INT,
constraint pk_categories PRIMARY KEY (id_categorie)
);
ALTER TABLE db_gestionECommerce.categories ADD constraint fk_categories_photos FOREIGN KEY (id_photo) REFERENCES photos(id_photo);

CREATE TABLE db_gestionECommerce.produits (
id_produit INT NOT NULL auto_increment,
nom_produit VARCHAR (100),
description_produit VARCHAR (200),
prix_produit DECIMAL (10,2),
selectionne BOOLEAN,
id_photo INT,
id_categorie INT,
constraint pk_produits PRIMARY KEY (id_produit)
);
ALTER TABLE db_gestionECommerce.produits ADD constraint fk_produits_photos FOREIGN KEY (id_photo) REFERENCES photos(id_photo);
ALTER TABLE db_gestionECommerce.produits ADD constraint fk_produits_categories FOREIGN KEY (id_categorie) REFERENCES categories(id_categorie);

CREATE TABLE db_gestionECommerce.commandes (
id_commande INT NOT NULL auto_increment,
date_commande datetime,
id_client INT,
constraint pk_commandes PRIMARY KEY (id_commande)
);
ALTER TABLE db_gestionECommerce.commandes ADD constraint fk_commandes_clients FOREIGN KEY (id_client) REFERENCES clients(id_client);

CREATE TABLE db_gestionECommerce.lignescommandes (
id_lignecommande INT NOT NULL auto_increment,
quantite_commande INT,
montant_commande DECIMAL(10,2),
id_produit INT,
id_commande INT,
id_panier INT,
constraint pk_lignescommandes PRIMARY KEY (id_lignecommande)
);
ALTER TABLE db_gestionECommerce.lignescommandes ADD constraint fk_lignescommandes_produits FOREIGN KEY (id_produit) REFERENCES produits(id_produit);
ALTER TABLE db_gestionECommerce.lignescommandes ADD constraint fk_lignescommandes_commandes FOREIGN KEY (id_commande) REFERENCES commandes(id_commande);
ALTER TABLE db_gestionECommerce.lignescommandes ADD constraint fk_lignescommandes_paniers FOREIGN KEY (id_panier) REFERENCES paniers(id_panier);

CREATE TABLE db_gestionECommerce.clients (
id_client INT NOT NULL auto_increment,
nom_client VARCHAR (100),
adresse_client VARCHAR (200),
email_client VARCHAR (100),
telephone_client VARCHAR (15),
constraint pk_clients PRIMARY KEY (id_client)
);


CREATE TABLE db_gestionECommerce.paniers (
id_panier INT NOT NULL auto_increment,
constraint pk_paniers PRIMARY KEY (id_panier)
);

CREATE TABLE db_gestionECommerce.utilisateurs (
id_utilisateur INT NOT NULL auto_increment,
nom_utilisateur VARCHAR (100),
mdp_utilisateur  VARCHAR (100),
id_role INT,
active BOOLEAN,
constraint pk_utilisateurs PRIMARY KEY (id_utilisateur)
);
ALTER TABLE db_gestionECommerce.utilisateurs ADD constraint fk_utilisateurs_roles FOREIGN KEY (id_role) REFERENCES roles(id_role);

CREATE TABLE db_gestionECommerce.roles (
id_role INT NOT NULL auto_increment,
nom_role VARCHAR (100),
constraint pk_roles PRIMARY KEY (id_role)
);


CREATE TABLE db_gestionECommerce.photos (
id_photo INT NOT NULL auto_increment,
url_photo VARCHAR (200),
nom_photo VARCHAR (200),
constraint pk_photos PRIMARY KEY (id_photo)
);

<<<<<<< HEAD


-- TEST DAO CLIENTS



-- TEST DAO UTILSATEUR
=======
INSERT INTO photos (url_photo, nom_photo) VALUE ('C:\Users\IN-ST-028\Documents\INTI Cours JAVA\Cours MEHDI_YOUNES\Devoirs\Project_Groupe5_ECom\WebContent\resources\images\test.jpg','test');

INSERT INTO categories (nom_categorie,description_categorie,id_photo) VALUES ('Categorie_Test','Cette catégorie a été inventée pour réaliser des tests sur la bdd',1) ;

INSERT INTO produits (nom_produit,description_produit,prix_produit,id_photo,id_categorie) VALUES ('Produit_Test','Ce produit a été inventé pour réaliser des tests sur la base de données',3.50,1,1);


SELECT * FROM photos ;

SELECT * FROM produits WHERE nom_produit LIKE '%test%' AND description_produit LIKE '%test%';

>>>>>>> 9f3e767... bdd

