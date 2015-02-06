
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
  `commune` varchar(128) NOT NULL,
  `etablissement` varchar(256) NOT NULL,
  `latitude` float NOT NULL,
  `longitude` float NOT NULL,
  `rayon` float NOT NULL COMMENT 'rayon maximum dans lequel se trouve l''oeuvre par rapport au point de géolocalisation',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `artiste`
--

CREATE TABLE IF NOT EXISTS `artiste` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'identifiant unique',
  `nom` varchar(64) NOT NULL,
  `preom` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `mot_cle`
--

CREATE TABLE IF NOT EXISTS `mot_cle` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'identifiant unique',
  `id_artiste` int(11) NOT NULL COMMENT 'identifiant de l''artiste associé à ce mot clé',
  `uri` varchar(128) NOT NULL COMMENT 'identifiant du mot clé',
  `source` text NOT NULL COMMENT 'source de la création de cette relation',
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
  `date_maj` int(32) NOT NULL,
  `autre` text COMMENT 'autres informations sur l''oeuvre',
  PRIMARY KEY (`id`),
  KEY `id_artiste` (`id_artiste`,`id_adresse`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;
