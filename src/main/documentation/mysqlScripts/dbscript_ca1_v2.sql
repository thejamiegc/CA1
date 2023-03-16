-- MySQL Workbench Synchronization
-- Generated: 2023-03-10 12:13
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: jcall

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

ALTER SCHEMA `ca1`  DEFAULT CHARACTER SET utf8  DEFAULT COLLATE utf8_general_ci ;

ALTER TABLE `ca1`.`Address` 
DROP FOREIGN KEY `fk_Address_Cityinfo1`;

ALTER TABLE `ca1`.`Phone` 
DROP FOREIGN KEY `fk_Phone_Person1`;

ALTER TABLE `ca1`.`Person_has_Hobby` 
DROP FOREIGN KEY `fk_Person_has_Hobby_Hobby1`;

ALTER TABLE `ca1`.`Person_has_Work` 
DROP FOREIGN KEY `fk_Person_has_Work_Person1`,
DROP FOREIGN KEY `fk_Person_has_Work_Work1`;

ALTER TABLE `ca1`.`Person` 
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ,
DROP COLUMN `Phone_id`,
DROP COLUMN `Address_id`;

ALTER TABLE `ca1`.`Address` 
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;

ALTER TABLE `ca1`.`Cityinfo` 
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;

ALTER TABLE `ca1`.`Hobby` 
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;

ALTER TABLE `ca1`.`Work` 
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;

ALTER TABLE `ca1`.`Phone` 
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;

ALTER TABLE `ca1`.`Person_has_Hobby` 
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;

ALTER TABLE `ca1`.`Person_has_Work` 
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;

ALTER TABLE `ca1`.`Address` 
DROP FOREIGN KEY `fk_Address_Person1`;

ALTER TABLE `ca1`.`Address` ADD CONSTRAINT `fk_Address_Person1`
  FOREIGN KEY (`Person_id`)
  REFERENCES `ca1`.`Person` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_Address_Cityinfo1`
  FOREIGN KEY (`Cityinfo_zipcode`)
  REFERENCES `ca1`.`Cityinfo` (`zipcode`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `ca1`.`Phone` 
ADD CONSTRAINT `fk_Phone_Person1`
  FOREIGN KEY (`Person_id`)
  REFERENCES `ca1`.`Person` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `ca1`.`Person_has_Hobby` 
DROP FOREIGN KEY `fk_Person_has_Hobby_Person`;

ALTER TABLE `ca1`.`Person_has_Hobby` ADD CONSTRAINT `fk_Person_has_Hobby_Person`
  FOREIGN KEY (`Person_id`)
  REFERENCES `ca1`.`Person` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_Person_has_Hobby_Hobby1`
  FOREIGN KEY (`Hobby_id`)
  REFERENCES `ca1`.`Hobby` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `ca1`.`Person_has_Work` 
ADD CONSTRAINT `fk_Person_has_Work_Person1`
  FOREIGN KEY (`Person_id`)
  REFERENCES `ca1`.`Person` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_Person_has_Work_Work1`
  FOREIGN KEY (`Work_id`)
  REFERENCES `ca1`.`Work` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
