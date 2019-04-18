-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema bestfurnituredeals
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bestfurnituredeals
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bestfurnituredeals` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `bestfurnituredeals` ;

-- -----------------------------------------------------
-- Table `bestfurnituredeals`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bestfurnituredeals`.`user` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `mail` VARCHAR(30) NULL DEFAULT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  `userType` VARCHAR(255) NULL DEFAULT NULL,
  `username` VARCHAR(30) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_kcjrru401600vrhfiuwvkilc2` (`mail` ASC) VISIBLE,
  UNIQUE INDEX `UK_jreodf78a7pl5qidfh43axdfb` (`username` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bestfurnituredeals`.`clientorder`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bestfurnituredeals`.`clientorder` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `approved` BIT(1) NULL DEFAULT NULL,
  `finished` BIT(1) NULL DEFAULT NULL,
  `paymentMethod` VARCHAR(255) NULL DEFAULT NULL,
  `totalPrice` DECIMAL(19,2) NULL DEFAULT NULL,
  `userId` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKtqkbnhfoyj7f70s0igirdce6d` (`userId` ASC) VISIBLE,
  CONSTRAINT `FKtqkbnhfoyj7f70s0igirdce6d`
    FOREIGN KEY (`userId`)
    REFERENCES `bestfurnituredeals`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bestfurnituredeals`.`furniture`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bestfurnituredeals`.`furniture` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bestfurnituredeals`.`boughtfurniture`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bestfurnituredeals`.`boughtfurniture` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `boughtQuantity` INT(11) NULL DEFAULT NULL,
  `price` DECIMAL(19,2) NULL DEFAULT NULL,
  `orderId` BIGINT(20) NULL DEFAULT NULL,
  `furnitureId` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKm3tqj4nge954bv6e4oi4gu779` (`orderId` ASC) VISIBLE,
  INDEX `FKtpoe74quijjcpgr9o1dotrg3i` (`furnitureId` ASC) VISIBLE,
  CONSTRAINT `FKm3tqj4nge954bv6e4oi4gu779`
    FOREIGN KEY (`orderId`)
    REFERENCES `bestfurnituredeals`.`clientorder` (`id`),
  CONSTRAINT `FKtpoe74quijjcpgr9o1dotrg3i`
    FOREIGN KEY (`furnitureId`)
    REFERENCES `bestfurnituredeals`.`furniture` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bestfurnituredeals`.`deal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bestfurnituredeals`.`deal` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `available` BIT(1) NULL DEFAULT NULL,
  `availableQuantity` INT(11) NULL DEFAULT NULL,
  `dealType` VARCHAR(255) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `price` DECIMAL(19,2) NULL DEFAULT NULL,
  `furnitureId` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK6ug1pidatp99l9udu7a4k7i7u` (`furnitureId` ASC) VISIBLE,
  CONSTRAINT `FK6ug1pidatp99l9udu7a4k7i7u`
    FOREIGN KEY (`furnitureId`)
    REFERENCES `bestfurnituredeals`.`furniture` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bestfurnituredeals`.`orderhistory`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bestfurnituredeals`.`orderhistory` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `orderPlaceDateTime` DATETIME NULL DEFAULT NULL,
  `orderState` VARCHAR(255) NULL DEFAULT NULL,
  `orderId` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKhtfawme1kl4ixk4biir7q5mg3` (`orderId` ASC) VISIBLE,
  CONSTRAINT `FKhtfawme1kl4ixk4biir7q5mg3`
    FOREIGN KEY (`orderId`)
    REFERENCES `bestfurnituredeals`.`clientorder` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bestfurnituredeals`.`feedbackmessage`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bestfurnituredeals`.`feedbackmessage` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `messageBody` VARCHAR(255) NULL DEFAULT NULL,
  `title` VARCHAR(255) NULL DEFAULT NULL,
  `orderHistoryId` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK8mo1e0q7xhviwfknch18cq1c6` (`orderHistoryId` ASC) VISIBLE,
  CONSTRAINT `FK8mo1e0q7xhviwfknch18cq1c6`
    FOREIGN KEY (`orderHistoryId`)
    REFERENCES `bestfurnituredeals`.`orderhistory` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
