-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 17, 2017 at 10:44 AM
-- Server version: 10.1.19-MariaDB
-- PHP Version: 5.5.38

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `zisprojekat`
--

-- --------------------------------------------------------

--
-- Table structure for table `korisnici`
--

CREATE TABLE `korisnici` (
  `IDKorisnik` int(8) NOT NULL,
  `Username` varchar(35) NOT NULL,
  `Password` varchar(64) NOT NULL,
  `Email` varchar(35) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `korisnici`
--

INSERT INTO `korisnici` (`IDKorisnik`, `Username`, `Password`, `Email`) VALUES
(1, 'test', '9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08', 'test@test.com'),
(2, 'aca', '7f457b6fe2481be342a12bedc100a25bfe3262a5d21e9879ad2f4840bb4fb981', 'ajct@test.com'),
(3, 'meho', '24b60f396a6e8ab4a57e9269ca1b2039cb0025e3a6704297f9b44a7c465d219d', 'meho@meho.com'),
(4, 'meho2', '5f249dfcbfbd48ded9f4bd571b1d6485ab64ccb8498ec30a1d423b4f106c04fd', 'meho2@meho2.com'),
(5, 'bane', '0fd441bf0e65724cfcd5084d02132e9e8e84f101e195ec1416575d0d743d9cc0', 'bane@bane.com'),
(6, 'a', 'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb', 'a'),
(7, 'b', '3e23e8160039594a33894f6564e1b1348bbd7a0088d42c4acb73eeaed59c009d', 'b'),
(8, 'f', '252f10c83610ebca1a059c0bae8255eba2f95be4d1d7bcfa89d7248a82d9f111', 'f'),
(9, 'q', '8e35c2cd3bf6641bdb0e2050b76932cbb2e6034a0ddacc1d9bea82a6ba57f7cf', 'q'),
(10, 'h', 'aaa9402664f1a41f40ebbc52c9993eb66aeb366602958fdfaa283b71e64db123', 'h'),
(11, 'y', 'a1fce4363854ff888cff4b8e7875d600c2682390412a8cf79b37d0b11148b0fa', 'y'),
(12, 'nn', '1b16b1df538ba12dc3f97edbb85caa7050d46c148134290feba80f8236c83db9', 'n'),
(13, 'v', '4c94485e0c21ae6c41ce1dfe7b6bfaceea5ab68e40a2476f50208e526f506080', 'v'),
(14, 'p', '148de9c5a7a44d19e56cd9ae1a554bf67847afb0c58f6e12fa29ac7ddfca9940', 'p'),
(15, '.', 'cdb4ee2aea69cc6a83331bbe96dc2caa9a299d21329efb0336fc02a82e1839a8', '.'),
(16, 'z', '594e519ae499312b29433b7dd8a97ff068defcba9755b6d5d00e84c524d67b06', 'z'),
(17, 't', 'e3b98a4da31a127d4bde6e43033f66ba274cab0eb7eb1c70ec41402bf6273dd8', 't'),
(18, 'š', '5ccde93a7741eebe66772604237d08415b1c1d54ec84a5b5342c7e1054115b0e', 'š'),
(19, 'r', '454349e422f05297191ead13e21d3db520e5abef52055e4964b82fb213f593a1', 'r'),
(20, 'u', '0bfe935e70c321c7ca3afc75ce0d0ca2f98b5422e008bb31c00c6d7f1f1c0ad6', 'u'),
(21, '7', '7902699be42c8a8e46fbbb4501726517e86b22c56a189f7625a6da49081b2451', '7'),
(22, 'c', '2e7d2c03a9507ae265ecf5b5356885a53393a2029d241394997265a1a25aefc6', 'c'),
(23, '5', 'ef2d127de37b942baad06145e54b0c619a1f22327b2ebbcfbec78f5564afe39d', '5'),
(24, 'af', '503126878d17fcd6bde7df320ff6eb7c278a1c42f30014a03b17f3dd0c023c1d', 'af'),
(25, 'caka', '207e42d6396bc09ff85301ff5a1f6dd2d00842223db8c06aa1173fe09806b922', 'caka@caka.com');

-- --------------------------------------------------------

--
-- Table structure for table `poruke`
--

CREATE TABLE `poruke` (
  `IDPoruka` int(11) NOT NULL,
  `Username` varchar(100) NOT NULL,
  `Poruka` text NOT NULL,
  `SatKreiranja` int(4) NOT NULL,
  `TTL` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `korisnici`
--
ALTER TABLE `korisnici`
  ADD PRIMARY KEY (`IDKorisnik`);

--
-- Indexes for table `poruke`
--
ALTER TABLE `poruke`
  ADD PRIMARY KEY (`IDPoruka`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `korisnici`
--
ALTER TABLE `korisnici`
  MODIFY `IDKorisnik` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
--
-- AUTO_INCREMENT for table `poruke`
--
ALTER TABLE `poruke`
  MODIFY `IDPoruka` int(11) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
