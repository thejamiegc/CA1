-- MySQL Workbench Synchronization
-- Generated: 2023-03-14 10:42
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: Helen

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

ALTER TABLE `ca1`.`Person_has_Hobby` 
DROP FOREIGN KEY `fk_Person_has_Hobby_Hobby1`,
DROP FOREIGN KEY `fk_Person_has_Hobby_Person`;

ALTER TABLE `ca1`.`Person_has_Hobby` 
DROP COLUMN `Hobby_id`,
DROP COLUMN `Person_id`,
ADD COLUMN `Person_id` BIGINT(20) NOT NULL FIRST,
ADD COLUMN `Hobby_id` BIGINT(20) NOT NULL AFTER `Person_id`,
ADD INDEX `fk_Person_has_Hobby_Person1_idx` (`Person_id` ASC) VISIBLE,
ADD INDEX `fk_Person_has_Hobby_Hobby1_idx` (`Hobby_id` ASC) VISIBLE,
DROP INDEX `fk_Person_has_Hobby_Person_idx` ,
DROP INDEX `fk_Person_has_Hobby_Hobby1_idx` ,
DROP PRIMARY KEY;
;

DROP TABLE IF EXISTS `ca1`.`Work` ;

DROP TABLE IF EXISTS `ca1`.`Person_has_Work` ;

ALTER TABLE `ca1`.`Person_has_Hobby` 
ADD CONSTRAINT `fk_Person_has_Hobby_Person1`
  FOREIGN KEY (`Person_id`)
  REFERENCES `ca1`.`Person` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_Person_has_Hobby_Hobby1`
  FOREIGN KEY (`Hobby_id`)
  REFERENCES `ca1`.`Hobby` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
