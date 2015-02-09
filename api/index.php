<?php
//Initialisation de Epiphany
include_once './src/Epi.php';
Epi::setPath('base', './src');
Epi::setSetting('exceptions', true);

//Base de données
Epi::init('database');
EpiDatabase::employ('mysql', 'base_11001234', 'mysql.istic.univ-rennes1.fr', 'user_11001234', 'admin');

//Initialisation du module API
Epi::init('api');

//Route principale
getRoute()->get('/', 'showHelp');

//API
getApi()->get('/version.json', 'apiVersion', EpiApi::external);
getApi()->get('/artistes.json', 'apiArtistes', EpiApi::external);
getApi()->get('/artiste.json/(\w+)', 'apiArtiste', EpiApi::external);

//404
getRoute()->get('.*', 'show404');

//Mise en place des routes
getRoute()->run();

//Fonctions éxécutées par EpiCode basé sur les routes
function showHelp(){
	echo 'Aide';
}
function show404(){
	echo 'false';
}

function showVersion()
{
  echo 'The version of this api is: ' . getApi()->invoke('/version.json');
}

function apiVersion()
{
  return '1.0';
}

function apiArtistes(){
 return getDatabase()->all('SELECT * FROM artiste');
}

function apiArtiste($id){
 return getDatabase()->one('SELECT * FROM artiste WHERE id = :id', array(':id' => $id));
}
