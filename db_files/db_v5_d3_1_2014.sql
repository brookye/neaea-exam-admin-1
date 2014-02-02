SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `neaeaexamadmin` ;
CREATE SCHEMA IF NOT EXISTS `neaeaexamadmin` DEFAULT CHARACTER SET latin1 ;
USE `neaeaexamadmin` ;

-- -----------------------------------------------------
-- Table `neaeaexamadmin`.`budget`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `neaeaexamadmin`.`budget` ;

CREATE  TABLE IF NOT EXISTS `neaeaexamadmin`.`budget` (
  `budgetType` VARCHAR(50) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL ,
  `budgetId` INT(11) NOT NULL AUTO_INCREMENT ,
  PRIMARY KEY (`budgetId`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_bin;


-- -----------------------------------------------------
-- Table `neaeaexamadmin`.`category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `neaeaexamadmin`.`category` ;

CREATE  TABLE IF NOT EXISTS `neaeaexamadmin`.`category` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `neaeaexamadmin`.`exam`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `neaeaexamadmin`.`exam` ;

CREATE  TABLE IF NOT EXISTS `neaeaexamadmin`.`exam` (
  `name` VARCHAR(30) NOT NULL ,
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `neaeaexamadmin`.`examinee`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `neaeaexamadmin`.`examinee` ;

CREATE  TABLE IF NOT EXISTS `neaeaexamadmin`.`examinee` (
  `name` VARCHAR(50) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL ,
  `fatherName` VARCHAR(50) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL ,
  `grandFatherName` VARCHAR(50) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL ,
  `schoolCodeId` INT(11) NOT NULL ,
  `age` INT(11) NOT NULL ,
  `sex` CHAR(1) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL ,
  `Sight` VARCHAR(10) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL ,
  `nationality` VARCHAR(30) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL ,
  `category` INT(11) NOT NULL ,
  `photo` LONGBLOB NOT NULL ,
  `registrationConfirmationNo` VARCHAR(30) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL ,
  `examineeId` INT(11) NOT NULL AUTO_INCREMENT ,
  PRIMARY KEY (`examineeId`) ,
  UNIQUE INDEX `registrationConfirmationNo_UNIQUE` (`registrationConfirmationNo` ASC) ,
  INDEX `schoolCode` (`schoolCodeId` ASC) ,
  INDEX `category` (`category` ASC) ,
  CONSTRAINT `category`
    FOREIGN KEY (`category` )
    REFERENCES `neaeaexamadmin`.`category` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `schoolCode`
    FOREIGN KEY (`schoolCodeId` )
    REFERENCES `neaeaexamadmin`.`schoolcode` (`schoolCodeId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 27
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_bin;


-- -----------------------------------------------------
-- Table `neaeaexamadmin`.`exam_examinee`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `neaeaexamadmin`.`exam_examinee` ;

CREATE  TABLE IF NOT EXISTS `neaeaexamadmin`.`exam_examinee` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `examId` INT(11) NOT NULL ,
  `examineeId` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `examId` (`examId` ASC) ,
  INDEX `examineeId` (`examineeId` ASC) ,
  CONSTRAINT `examId`
    FOREIGN KEY (`examId` )
    REFERENCES `neaeaexamadmin`.`exam` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `examineeId`
    FOREIGN KEY (`examineeId` )
    REFERENCES `neaeaexamadmin`.`examinee` (`examineeId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `neaeaexamadmin`.`zone`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `neaeaexamadmin`.`zone` ;

CREATE  TABLE IF NOT EXISTS `neaeaexamadmin`.`zone` (
  `zoneId` INT(11) NOT NULL AUTO_INCREMENT ,
  `zonecode` INT(11) NOT NULL ,
  `zonename` VARCHAR(30) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL ,
  `regioncode` INT(11) NOT NULL ,
  PRIMARY KEY (`zoneId`) ,
  INDEX `regionCode` (`regioncode` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_bin;


-- -----------------------------------------------------
-- Table `neaeaexamadmin`.`woreda`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `neaeaexamadmin`.`woreda` ;

CREATE  TABLE IF NOT EXISTS `neaeaexamadmin`.`woreda` (
  `woredId` INT(11) NOT NULL AUTO_INCREMENT ,
  `woredacode` INT(11) NOT NULL ,
  `woredaname` VARCHAR(50) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL ,
  `zoneId` INT(11) NOT NULL ,
  PRIMARY KEY (`woredId`) ,
  INDEX `zoneId` (`zoneId` ASC) ,
  CONSTRAINT `zoneId`
    FOREIGN KEY (`zoneId` )
    REFERENCES `neaeaexamadmin`.`zone` (`zoneId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_bin;


-- -----------------------------------------------------
-- Table `neaeaexamadmin`.`school`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `neaeaexamadmin`.`school` ;

CREATE  TABLE IF NOT EXISTS `neaeaexamadmin`.`school` (
  `code` VARCHAR(30) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL DEFAULT '' ,
  `groupNo` INT(11) NULL DEFAULT NULL ,
  `school_name` VARCHAR(45) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL ,
  `woreda_id` INT(11) NOT NULL ,
  PRIMARY KEY (`code`) ,
  INDEX `fk_groupNo` (`groupNo` ASC) ,
  INDEX `fk_woreda_id` (`woreda_id` ASC) ,
  INDEX `fk_group_no` (`groupNo` ASC) ,
  CONSTRAINT `fk_group_no`
    FOREIGN KEY (`groupNo` )
    REFERENCES `neaeaexamadmin`.`examcenter` (`groupNo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_woreda_id`
    FOREIGN KEY (`woreda_id` )
    REFERENCES `neaeaexamadmin`.`woreda` (`woredId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_bin;


-- -----------------------------------------------------
-- Table `neaeaexamadmin`.`examcenter`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `neaeaexamadmin`.`examcenter` ;

CREATE  TABLE IF NOT EXISTS `neaeaexamadmin`.`examcenter` (
  `groupNo` INT(11) NOT NULL ,
  `school_code` VARCHAR(30) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL ,
  `no_of_classroom` INT(11) NOT NULL ,
  `distance` FLOAT NOT NULL ,
  PRIMARY KEY (`groupNo`) ,
  INDEX `schoolCodeId` (`school_code` ASC) ,
  INDEX `fk_school_code` (`school_code` ASC) ,
  CONSTRAINT `fk_school_code`
    FOREIGN KEY (`school_code` )
    REFERENCES `neaeaexamadmin`.`school` (`code` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_bin;


-- -----------------------------------------------------
-- Table `neaeaexamadmin`.`examiner`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `neaeaexamadmin`.`examiner` ;

CREATE  TABLE IF NOT EXISTS `neaeaexamadmin`.`examiner` (
  `type` VARCHAR(50) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL ,
  `examinerId` INT(11) NOT NULL ,
  PRIMARY KEY (`examinerId`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_bin;


-- -----------------------------------------------------
-- Table `neaeaexamadmin`.`examcenter_examiner`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `neaeaexamadmin`.`examcenter_examiner` ;

CREATE  TABLE IF NOT EXISTS `neaeaexamadmin`.`examcenter_examiner` (
  `groupNo` INT(11) NOT NULL ,
  `type` INT(11) NOT NULL ,
  `quantity` INT(11) NOT NULL ,
  `id` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `groupNo` (`groupNo` ASC) ,
  INDEX `type` (`type` ASC) ,
  CONSTRAINT `groupNo`
    FOREIGN KEY (`groupNo` )
    REFERENCES `neaeaexamadmin`.`examcenter` (`groupNo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `type`
    FOREIGN KEY (`type` )
    REFERENCES `neaeaexamadmin`.`examiner` (`examinerId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_bin;


-- -----------------------------------------------------
-- Table `neaeaexamadmin`.`examiner_budjet`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `neaeaexamadmin`.`examiner_budjet` ;

CREATE  TABLE IF NOT EXISTS `neaeaexamadmin`.`examiner_budjet` (
  `examinerId` INT(11) NOT NULL ,
  `budgetId` INT(11) NOT NULL ,
  `amount` DECIMAL(10,0) NOT NULL ,
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  PRIMARY KEY (`id`) ,
  INDEX `examinerId` (`examinerId` ASC) ,
  INDEX `budgetId` (`budgetId` ASC) ,
  CONSTRAINT `budgetId`
    FOREIGN KEY (`budgetId` )
    REFERENCES `neaeaexamadmin`.`budget` (`budgetId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `examinerId`
    FOREIGN KEY (`examinerId` )
    REFERENCES `neaeaexamadmin`.`examiner` (`examinerId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_bin;


-- -----------------------------------------------------
-- Table `neaeaexamadmin`.`region`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `neaeaexamadmin`.`region` ;

CREATE  TABLE IF NOT EXISTS `neaeaexamadmin`.`region` (
  `regioncode` INT(11) NOT NULL ,
  `regionName` VARCHAR(50) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL ,
  PRIMARY KEY (`regioncode`) )
ENGINE = MyISAM
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_bin;


-- -----------------------------------------------------
-- Table `neaeaexamadmin`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `neaeaexamadmin`.`role` ;

CREATE  TABLE IF NOT EXISTS `neaeaexamadmin`.`role` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `neaeaexamadmin`.`usergroup`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `neaeaexamadmin`.`usergroup` ;

CREATE  TABLE IF NOT EXISTS `neaeaexamadmin`.`usergroup` (
  `userGroupId` INT(11) NOT NULL ,
  `userGroupName` VARCHAR(30) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL ,
  PRIMARY KEY (`userGroupId`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_bin;


-- -----------------------------------------------------
-- Table `neaeaexamadmin`.`role_usergroup`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `neaeaexamadmin`.`role_usergroup` ;

CREATE  TABLE IF NOT EXISTS `neaeaexamadmin`.`role_usergroup` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `roleId` INT(11) NOT NULL ,
  `userGroupId` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `userGroupId` (`userGroupId` ASC) ,
  INDEX `roleId` (`roleId` ASC) ,
  CONSTRAINT `roleId`
    FOREIGN KEY (`roleId` )
    REFERENCES `neaeaexamadmin`.`role` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `userGroupId`
    FOREIGN KEY (`userGroupId` )
    REFERENCES `neaeaexamadmin`.`usergroup` (`userGroupId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `neaeaexamadmin`.`woredaallowance`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `neaeaexamadmin`.`woredaallowance` ;

CREATE  TABLE IF NOT EXISTS `neaeaexamadmin`.`woredaallowance` (
  `code` VARCHAR(10) NOT NULL ,
  `low_scale` DECIMAL(10,2) NOT NULL ,
  `medium_scale` DECIMAL(10,2) NOT NULL ,
  `high_scale` DECIMAL(10,2) NOT NULL ,
  `desert_allowance` DECIMAL(10,2) NOT NULL ,
  `woreda` INT(11) NOT NULL ,
  PRIMARY KEY (`code`) ,
  INDEX `woreda` (`woreda` ASC) ,
  CONSTRAINT `woreda`
    FOREIGN KEY (`woreda` )
    REFERENCES `neaeaexamadmin`.`woreda` (`woredId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
