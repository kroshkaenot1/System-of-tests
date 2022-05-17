-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               8.0.28 - MySQL Community Server - GPL
-- Операционная система:         Win64
-- HeidiSQL Версия:              11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Дамп структуры базы данных system_of_tests
CREATE DATABASE IF NOT EXISTS `system_of_tests` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `system_of_tests`;

-- Дамп структуры для таблица system_of_tests.answer
CREATE TABLE IF NOT EXISTS `answer` (
  `id` bigint NOT NULL,
  `answer` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `question_id` bigint DEFAULT NULL,
  `score` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_answer_question` (`question_id`),
  CONSTRAINT `FK_answer_question` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Дамп данных таблицы system_of_tests.answer: ~4 rows (приблизительно)
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
INSERT INTO `answer` (`id`, `answer`, `question_id`, `score`) VALUES
	(11, '1533–1598 гг.', 10, 0),
	(12, '1598–1600 гг.', 10, 0),
	(13, '1598–1613 гг.', 10, 1),
	(15, 'усиление царской власти', 14, 0),
	(16, 'пресечение законной династии Рюриковичей', 14, 1),
	(17, 'польская интервенция', 14, 0);
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;

-- Дамп структуры для таблица system_of_tests.hibernate_sequence
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Дамп данных таблицы system_of_tests.hibernate_sequence: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` (`next_val`) VALUES
	(18);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;

-- Дамп структуры для таблица system_of_tests.invitation
CREATE TABLE IF NOT EXISTS `invitation` (
  `id` bigint NOT NULL,
  `test_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_invitation_test` (`test_id`),
  KEY `FK_invitation_user` (`user_id`),
  CONSTRAINT `FK_invitation_test` FOREIGN KEY (`test_id`) REFERENCES `test` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_invitation_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Дамп данных таблицы system_of_tests.invitation: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `invitation` DISABLE KEYS */;
/*!40000 ALTER TABLE `invitation` ENABLE KEYS */;

-- Дамп структуры для таблица system_of_tests.question
CREATE TABLE IF NOT EXISTS `question` (
  `id` bigint NOT NULL,
  `question_text` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `test_id` bigint DEFAULT NULL,
  `filename` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_question_test` (`test_id`),
  CONSTRAINT `FK_question_test` FOREIGN KEY (`test_id`) REFERENCES `test` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Дамп данных таблицы system_of_tests.question: ~2 rows (приблизительно)
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` (`id`, `question_text`, `test_id`, `filename`) VALUES
	(10, 'Период Смутного времени относится к', 9, NULL),
	(14, 'Одной из причин Смуты стало:', 9, NULL);
/*!40000 ALTER TABLE `question` ENABLE KEYS */;

-- Дамп структуры для таблица system_of_tests.test
CREATE TABLE IF NOT EXISTS `test` (
  `id` bigint NOT NULL,
  `date` datetime(6) DEFAULT NULL,
  `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `is_private` bit(1) DEFAULT NULL,
  `filename` varchar(255) DEFAULT NULL,
  `is_limited` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Дамп данных таблицы system_of_tests.test: ~1 rows (приблизительно)
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
INSERT INTO `test` (`id`, `date`, `description`, `title`, `is_private`, `filename`, `is_limited`) VALUES
	(9, '2022-05-17 19:28:02.494207', 'Материал подготовлен совместно с учителем высшей категории Александровой Екатериной Валерьевной.', 'Тест Смутное время (10 класс)', b'0', NULL, b'0');
/*!40000 ALTER TABLE `test` ENABLE KEYS */;

-- Дамп структуры для таблица system_of_tests.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint NOT NULL,
  `activation_code` varchar(255) DEFAULT NULL,
  `active` bit(1) NOT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `patronymic` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `date_of_birth` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Дамп данных таблицы system_of_tests.user: ~1 rows (приблизительно)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `activation_code`, `active`, `firstname`, `lastname`, `password`, `patronymic`, `username`, `date_of_birth`) VALUES
	(1, NULL, b'1', 'Сергей', 'Левин', 'qwerty', '', 'sergey.levin3@gmail.com', '');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Дамп структуры для таблица system_of_tests.user_role
CREATE TABLE IF NOT EXISTS `user_role` (
  `user_id` bigint NOT NULL,
  `roles` varchar(255) DEFAULT NULL,
  KEY `FK859n2jvi8ivhui0rl0esws6o` (`user_id`),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Дамп данных таблицы system_of_tests.user_role: ~4 rows (приблизительно)
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` (`user_id`, `roles`) VALUES
	(1, 'USER'),
	(1, 'ADMIN');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
