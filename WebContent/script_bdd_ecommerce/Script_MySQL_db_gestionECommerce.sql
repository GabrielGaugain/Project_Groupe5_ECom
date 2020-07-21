DROP DATABASE db_gestionECommerce ;
CREATE DATABASE db_gestionECommerce ;

use db_gestionECommerce;

CREATE TABLE db_gestionECommerce.categories (
id_categorie INT auto_increment,
nom_categorie VARCHAR (100),
description_categorie VARCHAR (200),
url_photo VARCHAR (200),
constraint pk_categories PRIMARY KEY (id_categorie)
);


CREATE TABLE db_gestionECommerce.produits (
id_produit INT auto_increment,
nom_produit VARCHAR (100),
description_produit VARCHAR (200),
prix_produit DECIMAL (10,2),
quantite INT,
selectionne BOOLEAN,
url_photo VARCHAR (200),
id_categorie INT,
constraint pk_produits PRIMARY KEY (id_produit)
);


CREATE TABLE db_gestionECommerce.commandes (
id_commande INT auto_increment,
date_commande datetime,
id_client INT,
constraint pk_commandes PRIMARY KEY (id_commande)
);


CREATE TABLE db_gestionECommerce.lignescommandes (
id_lignecommande INT auto_increment,
quantite_commande INT,
montant_commande DECIMAL(10,2),
id_produit INT,
id_commande INT,
id_panier INT,
constraint pk_lignescommandes PRIMARY KEY (id_lignecommande)
);


CREATE TABLE db_gestionECommerce.clients (
id_client INT  auto_increment,
nom_client VARCHAR (100),
adresse_client VARCHAR (200),
email_client VARCHAR (100),
telephone_client VARCHAR (15),
constraint pk_clients PRIMARY KEY (id_client)
);


CREATE TABLE db_gestionECommerce.paniers (
id_panier INT auto_increment,
constraint pk_paniers PRIMARY KEY (id_panier)
);

CREATE TABLE db_gestionECommerce.utilisateurs (
id_utilisateur INT auto_increment,
nom_utilisateur VARCHAR (100),
mdp_utilisateur  VARCHAR (100),
id_role INT,
active BOOLEAN,
constraint pk_utilisateurs PRIMARY KEY (id_utilisateur)
);


CREATE TABLE db_gestionECommerce.roles (
id_role INT auto_increment,
nom_role VARCHAR (100),
constraint pk_roles PRIMARY KEY (id_role)
);


CREATE TABLE db_gestionECommerce.photos (
url_photo VARCHAR (200),
nom_photo VARCHAR (200),
constraint pk_photos PRIMARY KEY (url_photo)
);


ALTER TABLE db_gestionECommerce.categories ADD constraint fk_categories_photos FOREIGN KEY (url_photo) REFERENCES photos(url_photo);
ALTER TABLE db_gestionECommerce.produits ADD constraint fk_produits_photos FOREIGN KEY (url_photo) REFERENCES photos(url_photo);
ALTER TABLE db_gestionECommerce.produits ADD constraint fk_produits_categories FOREIGN KEY (id_categorie) REFERENCES categories(id_categorie);
ALTER TABLE db_gestionECommerce.commandes ADD constraint fk_commandes_clients FOREIGN KEY (id_client) REFERENCES clients(id_client);
ALTER TABLE db_gestionECommerce.lignescommandes ADD constraint fk_lignescommandes_produits FOREIGN KEY (id_produit) REFERENCES produits(id_produit);
ALTER TABLE db_gestionECommerce.lignescommandes ADD constraint fk_lignescommandes_commandes FOREIGN KEY (id_commande) REFERENCES commandes(id_commande);
ALTER TABLE db_gestionECommerce.lignescommandes ADD constraint fk_lignescommandes_paniers FOREIGN KEY (id_panier) REFERENCES paniers(id_panier);
ALTER TABLE db_gestionECommerce.utilisateurs ADD constraint fk_utilisateurs_roles FOREIGN KEY (id_role) REFERENCES roles(id_role);

-- inserts :
INSERT INTO photos (url_photo, nom_photo) VALUE ('test.jpg','test');
INSERT INTO photos (url_photo, nom_photo) VALUE ('asus_rog.jpg','asus');
INSERT INTO photos (url_photo, nom_photo) VALUE ('macbook.jpg','macbook');
INSERT INTO photos (url_photo, nom_photo) VALUE ('testbleu.jpg','testbleu');

INSERT INTO categories (nom_categorie,description_categorie,url_photo) VALUES ('Categorie_Test','Cette catégorie a été inventée pour réaliser des tests sur la bdd','testbleu.jpg') ;
INSERT INTO categories (nom_categorie,description_categorie,url_photo) VALUES ('Categorie_Test2','Cette catégorie a été inventée ','testbleu.jpg') ;
insert into categories (nom_categorie,description_categorie,url_photo) VALUES ('Ordinateurs portables','Categorie regroupant les laptops ','testbleu.jpg') ;


INSERT INTO produits (nom_produit,description_produit,prix_produit,quantite,url_photo,id_categorie) VALUES ('Produit_Test','Ce produit a été inventé pour réaliser des tests sur la base de données',3.50,2,'test.jpg',1);
INSERT INTO produits (nom_produit,description_produit,prix_produit,quantite,url_photo,id_categorie) VALUES ('Produit_test','Ce produit a été inventé ',3.50,2,'test.jpg',2);
INSERT INTO produits (nom_produit,description_produit,prix_produit,quantite,url_photo,id_categorie) VALUES ('BLABLA','blablaZZZ',3.50,4,'test.jpg',1);
INSERT INTO produits (nom_produit,description_produit,prix_produit,quantite,url_photo,id_categorie) VALUES ('Laptop Asus','Laptot asus de gaming ',899.99,4,'asus_rog.jpg',3);
INSERT INTO produits (nom_produit,description_produit,prix_produit,quantite,url_photo,id_categorie) VALUES ('Macbook pro','Laptot apple de la game pro ',1499.99,8,'macbook.jpg',3);
insert into roles (id_role,nom_role) values (1,'admin');
insert into roles (id_role,nom_role) values (2,'client');

insert into paniers (id_panier) values (1);



INSERT into utilisateurs (nom_utilisateur , mdp_utilisateur, id_role, active) values ('admin','admin',1,true);
INSERT into utilisateurs (nom_utilisateur , mdp_utilisateur, id_role, active) values ('Gabydu14','123',2,true); 
INSERT into clients (id_client) values (0);

INSERT INTO clients (nom_client, adresse_client, email_client, telephone_client) values  ("Michelle","12 rue du bout perdu","mich@gmail.com","0202020202");
INSERT INTO commandes (date_commande ,id_client) values ('10.01.2020',1);
INSERT INTO lignescommandes (quantite_commande ,montant_commande,id_produit ,id_commande ,id_panier ) values (3,300.22, 1, 1, 1);

insert into lignescommandes (quantite_commande ,montant_commande,id_produit ,id_commande ,id_panier) values (3,300.00, 5 ,null,2);

delete from produits where id_produit =5;

-- tests

SELECT * FROM photos ;

SELECT * FROM produits WHERE nom_produit LIKE '%test%' OR description_produit LIKE '%test%';

SELECT COUNT(id_utilisateur) utilisateur FROM utilisateurs LEFT JOIN roles ON (utilisateurs.id_role = roles.id_role ) WHERE utilisateurs.nom_utilisateur=? AND roles.nom_role=? ;



DELETE lignescommandes.* FROM lignescommandes LEFT JOIN commandes ON (lignescommandes.id_commande = commandes.id_commande ) WHERE commandes.id_client=2  ;

select * from paniers;
select * from roles;
select * from utilisateurs;
select * from clients;
select * from categories;
select * from produits;
select * from commandes;
select * from lignescommandes;
select * from photos;
-- TEST DAO CLIENTS
insert into paniers () values ();


select * from paniers order by id_panier DESC limit 1 ;

-- test commande
update produits set nom_produit = 'macbook pro', description_produit= 'ordi trop cher avec une pomme' , prix_produit=1500.00, id_categorie=3
			    where id_produit = 5;

-- TEST DAO UTILSATEUR

