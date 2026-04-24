-- phpMyAdmin SQL Dump
-- version 5.2.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Apr 24, 2026 at 08:49 AM
-- Server version: 8.4.7
-- PHP Version: 8.3.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bibliosql`
--
CREATE DATABASE IF NOT EXISTS `bibliosql` DEFAULT CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci;
USE `bibliosql`;

-- --------------------------------------------------------

--
-- Table structure for table `auteur`
--

DROP TABLE IF EXISTS `auteur`;
CREATE TABLE IF NOT EXISTS `auteur` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nationalite` varchar(30) NOT NULL,
  `nom` varchar(30) NOT NULL,
  `prenom` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `auteur`
--

INSERT INTO `auteur` (`id`, `nationalite`, `nom`, `prenom`) VALUES
(1, 'FR', 'John', 'Doe');

-- --------------------------------------------------------

--
-- Table structure for table `avis`
--

DROP TABLE IF EXISTS `avis`;
CREATE TABLE IF NOT EXISTS `avis` (
  `id` int NOT NULL AUTO_INCREMENT,
  `commentaire` varchar(255) DEFAULT NULL,
  `date` date NOT NULL,
  `note` int NOT NULL,
  `livre_id` int DEFAULT NULL,
  `livre` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkuy2rlix4tlqhrmueiyijbxtr` (`livre_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `avis`
--

INSERT INTO `avis` (`id`, `commentaire`, `date`, `note`, `livre_id`, `livre`) VALUES
(1, 'Très Très bon livre', '2026-04-23', 10, 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `collection`
--

DROP TABLE IF EXISTS `collection`;
CREATE TABLE IF NOT EXISTS `collection` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKspwuu975lse6g9u6le73pymgo` (`nom`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `collection`
--

INSERT INTO `collection` (`id`, `nom`) VALUES
(1, 'Jeunesse');

-- --------------------------------------------------------

--
-- Table structure for table `editeur`
--

DROP TABLE IF EXISTS `editeur`;
CREATE TABLE IF NOT EXISTS `editeur` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) NOT NULL,
  `pays` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `editeur`
--

INSERT INTO `editeur` (`id`, `nom`, `pays`) VALUES
(1, 'Galimard', 'FR');

-- --------------------------------------------------------

--
-- Table structure for table `livre`
--

DROP TABLE IF EXISTS `livre`;
CREATE TABLE IF NOT EXISTS `livre` (
  `id` int NOT NULL AUTO_INCREMENT,
  `annee` date NOT NULL,
  `resume` varchar(255) NOT NULL,
  `titre` varchar(30) NOT NULL,
  `auteur_id` int DEFAULT NULL,
  `collection_id` int DEFAULT NULL,
  `editeur_id` bigint DEFAULT NULL,
  `livre` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh0pb6pxv3ubtgo1s3ev4gebgj` (`auteur_id`),
  KEY `FKtdie3rsbf0cer3n22mhp4to53` (`collection_id`),
  KEY `FKgowgjbkkxnvjykexh6nlmjjd7` (`editeur_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `livre`
--

INSERT INTO `livre` (`id`, `annee`, `resume`, `titre`, `auteur_id`, `collection_id`, `editeur_id`, `livre`) VALUES
(1, '2026-04-01', 'Comment j\'ai perdu ma femme au casino', 'Addiction gambling: l\'histoire', 1, 1, 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id` int NOT NULL AUTO_INCREMENT,
  `admin` bit(1) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKkq7nt5wyq9v9lpcpgxag2f24a` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `admin`, `password`, `username`) VALUES
(1, b'1', '$2a$12$a.E/4yHj8gXfMiDllMQXR.jMzrSTLV5HpDcr9Gq5TBAX7v0W3.gEi', 'user');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `avis`
--
ALTER TABLE `avis`
  ADD CONSTRAINT `FKkuy2rlix4tlqhrmueiyijbxtr` FOREIGN KEY (`livre_id`) REFERENCES `livre` (`id`);

--
-- Constraints for table `livre`
--
ALTER TABLE `livre`
  ADD CONSTRAINT `FKgowgjbkkxnvjykexh6nlmjjd7` FOREIGN KEY (`editeur_id`) REFERENCES `editeur` (`id`),
  ADD CONSTRAINT `FKh0pb6pxv3ubtgo1s3ev4gebgj` FOREIGN KEY (`auteur_id`) REFERENCES `auteur` (`id`),
  ADD CONSTRAINT `FKtdie3rsbf0cer3n22mhp4to53` FOREIGN KEY (`collection_id`) REFERENCES `collection` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
