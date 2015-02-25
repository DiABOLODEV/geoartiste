<!DOCTYPE HTML>
<html>
<head>
<title>Aide</title>
<meta charset="utf-8"/>
</head>
<body>
<table border=true>
<tr>
<th>Paramètres</th>
<th>Méthode</th>
<th>Description</th>
<th>Entrée</th>
<th>Sortie</th>
<th>Exemple</th>
</tr>
<tr>
<td>
selectArtistes
</td>
<td>
GET
</td>
<td>
Récupére l'id, le nom et le prénom de l'ensemble des artistes
</td>
<td>
rien
</td>
<td>
[{"id","nom","prenom"}]
</td>
<td>
</td>
</tr>
<tr>
<td>
selectArtisteById
</td>
<td>
GET
</td>
<td>
Récupére l'id, le nom et le prénom de l'artiste à l'id correspondant
</td>
<td>
L'identifiant de l'artiste voulu
</td>
<td>
{"id","nom","prenom"}
</td>
<td>
</td>
</tr>
<tr>
<td>
selectArtistesByNomPrenom
</td>
<td>
GET
</td>
<td>
Récupére l'id, le nom et le prénom de l'artiste aux nom et prénom correspondants
</td>
<td>
Le nom et prénom de l'artiste voulu
</td>
<td>
[{"id","nom","prenom"}]
</td>
<td>
</td>
</tr>
<tr>
<td>
selectArtistesByNom
</td>
<td>
GET
</td>
<td>
Récupére l'id, le nom et le prénom de l'ensemble des artistes du nom correspondant
</td>
<td>
Le nom de l'artiste
</td>
<td>
[{"id","nom","prenom"}]
</td>
<td>
</td>
</tr>
<tr>
<td>
selectArtistesByPrenom
</td>
<td>
GET
</td>
<td>
Récupére l'id, le nom et le prénom de l'ensemble des artistes du prénom correspondant
</td>
<td>
Le prénom de l'artiste
</td>
<td>
[{"id","nom","prenom"}]
</td>
<td>
</td>
</tr>
<tr>
<td>
selectOeuvresByArtisteId
</td>
<td>
GET
</td>
<td>
Récupére l'id, le titre, la description, la période et les autres informations de l'ensemble des oeuvres créées par l'artiste identifié par id
</td>
<td>
L'identifiant de l'artiste
</td>
<td>
[{"id":"","nom":"","prenom":"","date_maj":"","id_artiste":"","id_adresse":"","titre":"","description":"","periode":"","autre":""}]
</td>
<td>
</td>
</tr>
<tr>
<td>
selectMot_clesByArtisteId
</td>
<td>
GET
</td>
<td>
Récupére l'id, l'uri du mot clé, la source de l'ensemble des mots clés caractérisant l'artiste identifié par id
</td>
<td>
L'identifiant de l'artiste
</td>
<td>
[{"id":"","nom":"","prenom":"","date_maj":"","id_artiste":"","uri":"","source":""}]
</td>
<td>
</td>
</tr>
<tr>
<td>
selectArtistesByMot_cleURI
</td>
<td>
GET
</td>
<td>
Récupére l'id des artistes associés à l'URI correspondante
</td>
<td>
L'URI d'un mot clé
</td>
<td>
[{"id":""}]
</td>
<td>
</td>
</tr>
<tr>
<td>
selectAdresses
</td>
<td>
GET
</td>
<td>
Récupére l'ensemble des adresses de la base de données
</td>
<td>
Rien
</td>
<td>
[{"id":"","pays":"","region":"","departement":"","commune":"","etablissement":"","latitude":"","longitude":"","rayon":"","date_maj":""}]
</td>
<td>
</td>
</tr>
<tr>
<td>
selectAdresse
</td>
<td>
GET
</td>
<td>
Récupére l'adresse de l'id correspondant
</td>
<td>
L'identifiant d'une adresse
</td>
<td>
{"id":"","pays":"","region":"","departement":"","commune":"","etablissement":"","latitude":"","longitude":"","rayon":"","date_maj":""}
</td>
<td>
</td>
</tr>
<tr>
<td>
selectOeuvres
</td>
<td>
GET
</td>
<td>
Récupére l'ensemble des oeuvres de la bases de données
</td>
<td>
rien
</td>
<td>
[{"id":"","id_artiste":"","id_adresse":"","titre":"","description":"","periode":"","date_maj":"","autre":""}]
</td>
<td>
</td>
</tr>
<tr>
<td>
selectOeuvre
</td>
<td>
GET
</td>
<td>
Récupére l'oeuvre de l'id correspondant
</td>
<td>
L'identifiant d'une oeuvre
</td>
<td>
{"id":"","id_artiste":"","id_adresse":","titre":"","description":"","periode":"","date_maj":"","autre":""}
</td>
<td>
</td>
</tr>
<tr>
<td>
selectMot_cles
</td>
<td>
GET
</td>
<td>
Récupére l'ensemble des mots clé de la base de données
</td>
<td>
rien
</td>
<td>
[{"id":"","id_artiste":"","uri":"","source":"","date_maj":""}]
</td>
<td>
</td>
</tr>
<tr>
<td>
selectAdresseByOeuvreId
</td>
<td>
GET
</td>
<td>
Récupére l'adresse de l'oeuvre correspondant à l'id
</td>
<td>
L'identifiant d'une oeuvre
</td>
<td>
{"id":"","id_artiste":"","id_adresse":"","titre":"","description":"","periode":"","date_maj":"","autre":"","pays":"","region":"","departement":"","commune":"","etablissement":"","latitude":"","longitude":"","rayon":""}
</td>
<td>
</td>
</tr>
<tr>
<td>
isExistAdresse
</td>
<td>
GET
</td>
<td>
Retourne l'identifiant de l'adresse si elle existe
</td>
<td>
Le pays, la région, le département, la commune, l'établissement de localisation de l'oeuvre
</td>
<td>
{"id":""}
</td>
<td>
</td>
</tr>
<tr>
<td>
deleteMot_cleById
</td>
<td>
POST
</td>
<td>
Efface le mot clé correspondant à l'identifiant
</td>
<td>
Le code de validation et l'identifiant du mot clé
</td>
<td>
Si le code est bon : "id"
Si le code est faux : "Bad code"
</td>
<td>
</td>
</tr>
<tr>
<td>
deleteAll
</td>
<td>
POST
</td>
<td>
Vide la base de données
</td>
<td>
Le code de validation
</td>
<td>
Si le code est bon : "true"
Si le code est faux : "Bad code"
</td>
<td>
</td>
</tr>
<tr>
<td>
updateAdresseById
</td>
<td>
POST
</td>
<td>
Met à jour la latitude, la longitude et le rayon de l'adresse correspondant à l'identifiant
</td>
<td>
Le code de validation, l'identifiant de l'adresse, la latitude, la longitude, le rayon
</td>
<td>
Si le code est bon : "true"
Si le code est faux : "Bad code"
</td>
<td>
</td>
</tr>
<tr>
<td>
createArtiste
</td>
<td>
POST
</td>
<td>
Crée un artiste dans la base de données
</td>
<td>
Le code de validation, le nom et le prénom de l'artiste
</td>
<td>
Si le code est bon : "create artiste : id"
Si le code est faux : "Bad password"
</td>
<td>
</td>
</tr>
<tr>
<td>
createAdresse
</td>
<td>
POST
</td>
<td>
Crée une adresse dans la base de données
</td>
<td>
Le code de validation, le pays, la région, le département, la commune, l'établissement, la latitude, la longitude et le rayon de l'oeuvre
</td>
<td>
Si le code est bon : "create adresse : id"
Si le code est faux : "Bad password"
</td>
<td>
</td>
</tr>
<tr>
<td>
createOeuvre
</td>
<td>
POST
</td>
<td>
Crée une oeuvre associé à un artiste et à une adresse dans la base de données
</td>
<td>
Le code de validation, l'identifiant de l'artiste qui a créé l'oeuvre, l'identifiant de l'adresse où est localisé l'oeuvre, le titre, la description, la période et les autres informations d'une oeuvre
</td>
<td>
Si le code est bon : "create oeuvre : id"
Si le code est faux : "Bad password"
</td>
<td>
</td>
</tr>
<tr>
<td>
createMotCle
</td>
<td>
POST
</td>
<td>
Crée un mot clé associé à un artiste dans la base de données
</td>
<td>
Le code de validation, l'identifiant de l'artiste, l'uri du mot clé, la source du mot clé
</td>
<td>
Si le code est bon : "create clé : id"
Si le code est faux : "Bad password"
</td>
<td>
</td>
</tr>
</table>
</body>
</html>