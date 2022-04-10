-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema sd_tema2
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema sd_tema2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sd_tema2` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `sd_tema2` ;

-- -----------------------------------------------------
-- Table `sd_tema2`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sd_tema2`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(255) NOT NULL,
  `last_name` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `type` INT NOT NULL,
  `username` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sd_tema2`.`restaurant`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sd_tema2`.`restaurant` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `location` VARCHAR(255) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `admin_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_i6u3x7opncroyhd755ejknses` (`name` ASC) VISIBLE,
  INDEX `FKj37ybdgkcgg0xg4hgw2jmdoql` (`admin_id` ASC) VISIBLE,
  CONSTRAINT `FKj37ybdgkcgg0xg4hgw2jmdoql`
    FOREIGN KEY (`admin_id`)
    REFERENCES `sd_tema2`.`user` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sd_tema2`.`item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sd_tema2`.`item` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `category` INT NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `price` DOUBLE NOT NULL,
  `restaurant_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_lcsp6a1tpwb8tfywqhrsm2uvg` (`name` ASC) VISIBLE,
  INDEX `FK2ip7t8cv2p1ghfi1e796yet7d` (`restaurant_id` ASC) VISIBLE,
  CONSTRAINT `FK2ip7t8cv2p1ghfi1e796yet7d`
    FOREIGN KEY (`restaurant_id`)
    REFERENCES `sd_tema2`.`restaurant` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sd_tema2`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sd_tema2`.`orders` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `order_status` INT NOT NULL,
  `customer_id` INT NOT NULL,
  `restaurant_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK14n2jkmoyhpimhracvcdy7sst` (`customer_id` ASC) VISIBLE,
  INDEX `FKi7hgjxhw21nei3xgpe4nnpenh` (`restaurant_id` ASC) VISIBLE,
  CONSTRAINT `FK14n2jkmoyhpimhracvcdy7sst`
    FOREIGN KEY (`customer_id`)
    REFERENCES `sd_tema2`.`user` (`id`),
  CONSTRAINT `FKi7hgjxhw21nei3xgpe4nnpenh`
    FOREIGN KEY (`restaurant_id`)
    REFERENCES `sd_tema2`.`restaurant` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sd_tema2`.`order_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sd_tema2`.`order_item` (
  `order_id` INT NOT NULL,
  `item_id` INT NOT NULL,
  INDEX `FKija6hjjiit8dprnmvtvgdp6ru` (`item_id` ASC) VISIBLE,
  INDEX `FKt4dc2r9nbvbujrljv3e23iibt` (`order_id` ASC) VISIBLE,
  CONSTRAINT `FKija6hjjiit8dprnmvtvgdp6ru`
    FOREIGN KEY (`item_id`)
    REFERENCES `sd_tema2`.`item` (`id`),
  CONSTRAINT `FKt4dc2r9nbvbujrljv3e23iibt`
    FOREIGN KEY (`order_id`)
    REFERENCES `sd_tema2`.`orders` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sd_tema2`.`zone`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sd_tema2`.`zone` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_27s7q3vqft9a76yi9k7e7rroi` (`name` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sd_tema2`.`restaurant_zones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sd_tema2`.`restaurant_zones` (
  `zone_id` INT NOT NULL,
  `restaurant_id` INT NOT NULL,
  INDEX `FK1r9lobw3b9bor1nlgi91d2bgs` (`restaurant_id` ASC) VISIBLE,
  INDEX `FKm85meeoipkiicbt56t4uiwc8t` (`zone_id` ASC) VISIBLE,
  CONSTRAINT `FK1r9lobw3b9bor1nlgi91d2bgs`
    FOREIGN KEY (`restaurant_id`)
    REFERENCES `sd_tema2`.`restaurant` (`id`),
  CONSTRAINT `FKm85meeoipkiicbt56t4uiwc8t`
    FOREIGN KEY (`zone_id`)
    REFERENCES `sd_tema2`.`zone` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
