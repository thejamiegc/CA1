-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ca1
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ca1
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ca1` DEFAULT CHARACTER SET utf8 ;
USE `ca1` ;

-- -----------------------------------------------------
-- Table `ca1`.`Person`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ca1`.`Person` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45) NULL,
  `lastname` VARCHAR(45) NULL,
  `email` VARCHAR(255) NULL,
  `gender` VARCHAR(45) NULL,
  `relationshipstatus` VARCHAR(45) NULL,
  `Address_id` BIGINT(20) NOT NULL,
  `Phone_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ca1`.`Cityinfo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ca1`.`Cityinfo` (
  `zipcode` INT NOT NULL AUTO_INCREMENT,
  `city` VARCHAR(45) NULL,
  PRIMARY KEY (`zipcode`),
  UNIQUE INDEX `zipcode_UNIQUE` (`zipcode` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ca1`.`Address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ca1`.`Address` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `streetname` VARCHAR(100) NULL,
  `streetnumber` VARCHAR(45) NULL,
  `hometype` VARCHAR(45) NULL,
  `Person_id` BIGINT(20) NOT NULL,
  `Cityinfo_zipcode` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_Address_Person1_idx` (`Person_id` ASC) VISIBLE,
  INDEX `fk_Address_Cityinfo1_idx` (`Cityinfo_zipcode` ASC) VISIBLE,
  CONSTRAINT `fk_Address_Person1`
    FOREIGN KEY (`Person_id`)
    REFERENCES `ca1`.`Person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Address_Cityinfo1`
    FOREIGN KEY (`Cityinfo_zipcode`)
    REFERENCES `ca1`.`Cityinfo` (`zipcode`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ca1`.`Hobby`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ca1`.`Hobby` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `wikilink` VARCHAR(500) NULL,
  `category` VARCHAR(45) NULL,
  `type` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ca1`.`Work`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ca1`.`Work` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL,
  `description` VARCHAR(500) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ca1`.`Phone`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ca1`.`Phone` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `number` VARCHAR(45) NULL,
  `countrycode` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  `Person_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_Phone_Person1_idx` (`Person_id` ASC) VISIBLE,
  CONSTRAINT `fk_Phone_Person1`
    FOREIGN KEY (`Person_id`)
    REFERENCES `ca1`.`Person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ca1`.`Person_has_Hobby`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ca1`.`Person_has_Hobby` (
  `Person_id` BIGINT(20) NOT NULL,
  `Hobby_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`Person_id`, `Hobby_id`),
  INDEX `fk_Person_has_Hobby_Hobby1_idx` (`Hobby_id` ASC) VISIBLE,
  INDEX `fk_Person_has_Hobby_Person_idx` (`Person_id` ASC) VISIBLE,
  CONSTRAINT `fk_Person_has_Hobby_Person`
    FOREIGN KEY (`Person_id`)
    REFERENCES `ca1`.`Person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Person_has_Hobby_Hobby1`
    FOREIGN KEY (`Hobby_id`)
    REFERENCES `ca1`.`Hobby` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ca1`.`Person_has_Work`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ca1`.`Person_has_Work` (
  `Person_id` BIGINT(20) NOT NULL,
  `Work_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`Person_id`, `Work_id`),
  INDEX `fk_Person_has_Work_Work1_idx` (`Work_id` ASC) VISIBLE,
  INDEX `fk_Person_has_Work_Person1_idx` (`Person_id` ASC) VISIBLE,
  CONSTRAINT `fk_Person_has_Work_Person1`
    FOREIGN KEY (`Person_id`)
    REFERENCES `ca1`.`Person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Person_has_Work_Work1`
    FOREIGN KEY (`Work_id`)
    REFERENCES `ca1`.`Work` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
