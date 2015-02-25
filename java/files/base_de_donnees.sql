-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Dim 08 Février 2015 à 18:50
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `geoartiste`
--

-- --------------------------------------------------------

--
-- Structure de la table `adresse`
--

CREATE TABLE IF NOT EXISTS `adresse` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'identifiant unique',
  `pays` varchar(128) NOT NULL,
  `region` varchar(128) NOT NULL,
  `departement` varchar(30) NOT NULL,
  `commune` varchar(128) NOT NULL,
  `etablissement` varchar(256) NOT NULL,
  `latitude` float DEFAULT NULL,
  `longitude` float DEFAULT NULL,
  `rayon` float DEFAULT NULL COMMENT 'rayon maximum dans lequel se trouve l''oeuvre par rapport au point de gÃ©olocalisation',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3929 ;

-- --------------------------------------------------------

--
-- Structure de la table `artiste`
--

CREATE TABLE IF NOT EXISTS `artiste` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'identifiant unique',
  `nom` varchar(64) NOT NULL,
  `prenom` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=23839 ;

-- --------------------------------------------------------

--
-- Structure de la table `mot_cle`
--

CREATE TABLE IF NOT EXISTS `mot_cle` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'identifiant unique',
  `id_artiste` int(11) NOT NULL COMMENT 'identifiant de l''artiste associÃ© Ã  ce mot clÃ©',
  `uri` varchar(128) NOT NULL COMMENT 'identifiant du mot clÃ©',
  `source` text NOT NULL COMMENT 'source de la crÃ©ation de cette relation',
  `date_maj` int(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_artiste` (`id_artiste`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `oeuvre`
--

CREATE TABLE IF NOT EXISTS `oeuvre` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'identifiant unique',
  `id_artiste` int(11) NOT NULL COMMENT 'identifiant de l''artiste',
  `id_adresse` int(11) NOT NULL COMMENT 'identifiant de l''adresse',
  `titre` varchar(256) DEFAULT NULL,
  `description` varchar(512) DEFAULT NULL,
  `periode` varchar(15) NOT NULL,
  `date_maj` date NOT NULL,
  `autre` text COMMENT 'autres informations sur l''oeuvre',
  PRIMARY KEY (`id`),
  KEY `id_artiste` (`id_artiste`,`id_adresse`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3925 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
