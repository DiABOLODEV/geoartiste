<?php
//Initialisation de Epiphany
include_once './src/Epi.php';
Epi::setPath('base', './src');
Epi::setSetting('exceptions', true);

//Base de données
Epi::init('database');
EpiDatabase::employ('mysql', 'wordpress_geo-artiste', 'mysql-55.irisa.fr', 'geo-artiste', 'eSXbZ69PjDXeT7vJ');

//Initialisation du module API
Epi::init('api');

//Routes
getRoute()->get('/', 'showHelp');
getApi()->get('/test', 'showTest',  EpiApi::external);
getApi()->get('/selectArtistes', 'selectArtistes', EpiApi::external);
getApi()->get('/selectArtisteById/(\d+)', 'selectArtisteById', EpiApi::external);
getApi()->get('/selectArtistesByNomPrenom/(\w+)/(\w+)', 'selectArtistesByNomPrenom', EpiApi::external);
getApi()->get('/selectArtistesByNom/(\w+)', 'selectArtistesByNom', EpiApi::external);
getApi()->get('/selectArtistesByPrenom/(\w+)', 'selectArtistesByPrenom', EpiApi::external);
getApi()->get('/selectOeuvresByArtisteId/(\d+)', 'selectOeuvresByArtisteId', EpiApi::external);
getApi()->get('/selectMot_clesByArtisteId/(\d+)', 'selectMot_clesByArtisteId', EpiApi::external);
getApi()->get('/selectArtistesByMot_cleURI/(\w+)', 'selectArtistesByMot_cleURI', EpiApi::external);
getApi()->get('/selectAdresses', 'apiAdresses', EpiApi::external);
getApi()->get('/selectAdresse/(\w+)', 'apiAdresse', EpiApi::external);
getApi()->get('/selectOeuvres', 'apiOeuvres', EpiApi::external);
getApi()->get('/selectOeuvre/(\w+)', 'apiOeuvre', EpiApi::external);
getApi()->get('/selectMot_cles', 'apiselectMot_cles', EpiApi::external);
getApi()->get('/selectAdresseByOeuvreId/(\d+)', 'apiselectAdresseByOeuvreId', EpiApi::external);
getApi()->get('/isExistAdresse/(\w+)/(\w+)/(\w+)/(\w+)/(\w+)', 'isExistAdresse', EpiApi::external);

getRoute()->post('/deleteMot_cleById/(\w+)/(\d+)', 'deleteMot_cleById');
getRoute()->post('/deleteAll/(\w+)', 'deleteAll'); //Supprime tout
getRoute()->post('/updateAdresseById/(\w+)/(\d+)/([-]*\d+\.\d+)/([-]*\d+\.\d+)/([-]*\d+\.\d+)', 'updateAdresseById');
getRoute()->post('/createArtiste/(\w+)/(\w+)/(\w+)', 'createArtiste');
getRoute()->post('/createAdresse/(\w+)/(\w+)/(\w+)/(\w+)/(\w+)/(\w+)/([-]*\d+\.\d+)/([-]*\d+\.\d+)/([-]*\d+\.\d+)', 'createAdresse');
getRoute()->post('/createOeuvre/(\w+)/(\d+)/(\d+)/(\w+)/(\w+)/(\w+)/(\w+)', 'createOeuvre');
getRoute()->post('/createMotCle/(\w+)/(\d+)/(\w+)/(\w+)', 'createMotCle');
getRoute()->get('/*', 'show404');

//-----GET------//
function showTest(){
	return 'test';
}
function showHelp(){
	include("help.inc.php");
}

function selectArtistes(){
return getDatabase()->all('SELECT * FROM artiste');
}

function selectArtisteById($id){
return getDatabase()->one('SELECT * FROM artiste WHERE id = :id', array(':id' => $id));
}

function selectArtistesByNomPrenom($nom,$prenom){
return getDatabase()->all('SELECT id, nom, prenom, date_maj FROM artiste WHERE nom LIKE :nom AND prenom LIKE :prenom', array(':nom' => $nom.'%', ':prenom' => $prenom.'%'));
}

function selectArtistesByNom($nom){
return getDatabase()->all('SELECT id, nom, prenom, date_maj FROM artiste WHERE nom LIKE :nom', array(':nom' => $nom.'%'));
}

function selectArtistesByPrenom($prenom){
return getDatabase()->all('SELECT id, nom, prenom, date_maj FROM artiste WHERE prenom LIKE :prenom', array(':prenom' => $prenom.'%'));
}

function selectOeuvresByArtisteId($id){
return getDatabase()->all('select * from artiste, oeuvre where artiste.id = :id AND oeuvre.id_artiste = :id', array(':id' => $id));
}

function selectMot_clesByArtisteId($id){
return getDatabase()->all('select * from artiste, mot_cle where artiste.id = :id AND mot_cle.id_artiste = :id', array(':id' => $id));
}

function selectArtistesByMot_cleURI($uri){
	return getDatabase()->all('SELECT a.id FROM artiste AS a, mot_cle AS b WHERE b.id_artiste = a.id AND b.uri = :uri', array(':uri' => $uri));
}

function apiAdresses(){
return getDatabase()->all('SELECT * FROM adresse');
}
function apiAdresse($id){
return getDatabase()->one('SELECT * FROM adresse WHERE id = :id', array(':id' => $id));
}

function apiOeuvres(){
return getDatabase()->all('SELECT * FROM oeuvre');
}

function apiOeuvre($id){
return getDatabase()->one('SELECT * FROM oeuvre WHERE id = :id', array(':id' => $id));
}

function apiselectMot_cles(){
return getDatabase()->all('SELECT * from mot_cle');
}

function apiselectAdresseByOeuvreId($id){
return getDatabase()->one('SELECT * from oeuvre o, adresse ar WHERE ar.id =o.id_adresse and o.id=:id', array(':id' => $id));

}

function isExistAdresse($pays, $region, $departement, $commune, $etablissement){
	return getDatabase()->one('SELECT DISTINCT id FROM adresse WHERE pays=:pays and region=:region and departement=:departement and commune=:commune and etablissement=:etablissement',array(':pays' => $pays, ':region' => $region, ':departement' => $departement, ':commune' => $commune,':etablissement' => $etablissement));
}

//-----POST------//

function deleteMot_cleById($code, $id){
	if($code == 'geoartiste5'){
		echo getDatabase()->execute('DELETE FROM mot_cle WHERE id=:id', array(':id' => $id));
	}
	else{
		echo 'bad code';
	}
}

function deleteAll($code){
	if($code == 'geoartiste5'){
		echo getDatabase()->execute('DELETE FROM adresse').'\n';
		echo getDatabase()->execute('DELETE FROM artiste').'\n';
		echo getDatabase()->execute('DELETE FROM mot_cle').'\n';
		echo getDatabase()->execute('DELETE FROM oeuvre');

	}
	else{
		echo 'bad code';
	}
}

function updateAdresseById($code, $id, $latitude, $longitude, $rayon){
	if($code == 'geoartiste5'){
		echo getDatabase()->execute('UPDATE adresse SET latitude = :latitude, longitude = :longitude, rayon = :rayon, date_maj = :date_maj WHERE id=:id', array(':id' => $id, ':latitude' => $latitude, ':longitude' => $longitude, ':rayon' => $rayon, ':date_maj' => time()));
	}
	else{
		echo 'bad code';
	}
}

function createArtiste($code,$nom, $prenom){
	if($code == 'geoartiste5'){
		echo getDatabase()->execute('INSERT INTO artiste(nom, prenom, date_maj) VALUES (:nom, :prenom, :date_maj)', array(':nom' => $nom, ':prenom' => $prenom, ':date_maj' => time()));
	}
	else{
		echo 'Bad password';
	}
}

function createAdresse($code, $pays, $region, $departement, $commune, $etablissement, $latitude, $longitude, $rayon){
	if($code == 'geoartiste5'){
		echo getDatabase()->execute('INSERT INTO adresse(pays, region, departement, commune, etablissement, latitude, longitude, rayon, date_maj) VALUES (:pays, :region, :departement, :commune, :etablissement, :latitude, :longitude, :rayon, :date_maj)', array(':pays' => $pays, ':region' => $region, ':departement' => $departement, ':commune' => $commune, ':etablissement' => $etablissement, ':latitude' => $latitude, ':longitude' => $longitude, ':rayon' => $rayon, ':date_maj' => time()));
	}
	else{
		echo 'Bad password';
	}
}

function createOeuvre($code, $id_artiste, $id_adresse, $titre, $description, $periode, $autre){
	if($code == 'geoartiste5'){
		echo getDatabase()->execute('INSERT INTO oeuvre(id_artiste, id_adresse, titre, description, periode, date_maj, autre) VALUES (:id_artiste, :id_adresse, :titre, :description, :periode, :date_maj, :autre)', array(':id_artiste' => $id_artiste, ':id_adresse' => $id_adresse, ':titre' => $titre, ':description' => $description, ':periode' => $periode, ':date_maj' => time(), ':autre' => $autre));
	}
	else{
		echo 'Bad password';
	}
}

function createMotCle($code, $id_artiste, $uri, $source){
	if($code == 'geoartiste5'){
		echo getDatabase()->execute('INSERT INTO mot_cle(id_artiste, uri, source, date_maj) VALUES (:id_artiste, :uri, :source, :date_maj)', array(':id_artiste' => $id_artiste, ':uri' => $uri, ':source' => $source, ':date_maj' => time()));
	}
	else{
		echo 'Bad password';
	}
}

function show404(){
	echo 'false';
}

//Mise en place des routes
getRoute()->run();

//Fonctions éxécutées par EpiCode basé sur les routes
