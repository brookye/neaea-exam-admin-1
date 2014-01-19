SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';


DROP SCHEMA IF EXISTS `neaeaexamadmin` ;
CREATE SCHEMA IF NOT EXISTS `neaeaexamadmin` DEFAULT CHARACTER SET latin1 ;


-- -----------------------------------------------------
-- Table `neaeaexamadmin`.`exam`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `neaeaexamadmin`.`exam` ;

CREATE  TABLE IF NOT EXISTS `neaeaexamadmin`.`exam` (
  `name` VARCHAR(30) NOT NULL ,
  `id` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `neaeaexamadmin`.`schoolcodebook`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `neaeaexamadmin`.`schoolcodebook` ;

CREATE  TABLE IF NOT EXISTS `neaeaexamadmin`.`schoolcodebook` (
  `schoolCode` VARCHAR(30) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL ,
  `groupNo` INT(11) NOT NULL ,
  PRIMARY KEY (`schoolCode`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_bin;


-- -----------------------------------------------------
-- Table `neaeaexamadmin`.`examinee`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `neaeaexamadmin`.`examinee` ;

CREATE  TABLE IF NOT EXISTS `neaeaexamadmin`.`examinee` (
  `name` VARCHAR(50) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL ,
  `fatherName` VARCHAR(50) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL ,
  `grandFatherName` VARCHAR(50) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL ,
  `schoolCode` VARCHAR(30) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL ,
  `age` INT(11) NOT NULL ,
  `sex` CHAR(1) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL ,
  `Sight` VARCHAR(10) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL ,
  `nationaliy` VARCHAR(30) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL ,
  `catagory` CHAR(1) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL ,
  `photo` BLOB NOT NULL ,
  `registrationConfirmationnumber` VARCHAR(30) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL ,
  PRIMARY KEY (`registrationConfirmationnumber`) ,
  INDEX `schoolCode` (`schoolCode` ASC) ,
  CONSTRAINT `schoolCode`
    FOREIGN KEY (`schoolCode` )
    REFERENCES `neaeaexamadmin`.`schoolcodebook` (`schoolCode` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_bin;


-- -----------------------------------------------------
-- Table `neaeaexamadmin`.`exam_examinee`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `neaeaexamadmin`.`exam_examinee` ;

CREATE  TABLE IF NOT EXISTS `neaeaexamadmin`.`exam_examinee` (
  `id` INT(11) NOT NULL ,
  `examId` INT(11) NOT NULL ,
  `examinee` VARCHAR(30) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `examId` (`examId` ASC) ,
  INDEX `examinee` () ,
  CONSTRAINT `examId`
    FOREIGN KEY (`examId` )
    REFERENCES `neaeaexamadmin`.`exam` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `examinee`
    FOREIGN KEY ()
    REFERENCES `neaeaexamadmin`.`examinee` ()
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `neaeaexamadmin` ;

-- -----------------------------------------------------
-- Table `neaeaexamadmin`.`budget`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `neaeaexamadmin`.`budget` ;

CREATE  TABLE IF NOT EXISTS `neaeaexamadmin`.`budget` (
  `budgetType` VARCHAR(50) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL ,
  `budgetId` INT(11) NOT NULL ,
  PRIMARY KEY (`budgetId`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_bin;


-- -----------------------------------------------------
-- Table `neaeaexamadmin`.`examcenter`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `neaeaexamadmin`.`examcenter` ;

CREATE  TABLE IF NOT EXISTS `neaeaexamadmin`.`examcenter` (
  `groupNo` INT(11) NOT NULL ,
  `schoolCode` VARCHAR(50) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NULL ,
  `noofClassRooms` INT(11) NOT NULL ,
  `distance` FLOAT NOT NULL ,
  PRIMARY KEY (`groupNo`) ,
  INDEX `schoolCode` (`schoolCode` ASC) ,
  CONSTRAINT `schoolCode`
    FOREIGN KEY (`schoolCode` )
    REFERENCES `neaeaexamadmin`.`schoolcodebook` (`schoolCode` )
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
  `id` INT(11) NOT NULL ,
  INDEX `examinerId` (`examinerId` ASC) ,
  INDEX `budgetId` (`budgetId` ASC) ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `examinerId`
    FOREIGN KEY (`examinerId` )
    REFERENCES `neaeaexamadmin`.`examiner` (`examinerId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `budgetId`
    FOREIGN KEY (`budgetId` )
    REFERENCES `neaeaexamadmin`.`budget` (`budgetId` )
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
  `Regioncode` INT(11) NOT NULL ,
  `regionName` VARCHAR(50) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL ,
  PRIMARY KEY (`Regioncode`) )
ENGINE = MyISAM
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_bin;


-- -----------------------------------------------------
-- Table `neaeaexamadmin`.`zone`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `neaeaexamadmin`.`zone` ;

CREATE  TABLE IF NOT EXISTS `neaeaexamadmin`.`zone` (
  `ZoneId` INT(11) NOT NULL ,
  `Zonecode` INT(11) NOT NULL ,
  `Zonename` VARCHAR(30) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL ,
  `Regioncode` INT(11) NOT NULL ,
  PRIMARY KEY (`ZoneId`) ,
  INDEX `regionCode` (`Regioncode` ASC) ,
  CONSTRAINT `regionCode`
    FOREIGN KEY (`Regioncode` )
    REFERENCES `neaeaexamadmin`.`region` (`Regioncode` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_bin;


-- -----------------------------------------------------
-- Table `neaeaexamadmin`.`woreda`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `neaeaexamadmin`.`woreda` ;

CREATE  TABLE IF NOT EXISTS `neaeaexamadmin`.`woreda` (
  `woredId` INT(11) NOT NULL ,
  `woredacode` INT(11) NOT NULL ,
  `woredaname` VARCHAR(50) NOT NULL ,
  `ZoneId` INT(11) NOT NULL ,
  PRIMARY KEY (`woredId`) ,
  INDEX `zoneId` (`ZoneId` ASC) ,
  CONSTRAINT `zoneId`
    FOREIGN KEY (`ZoneId` )
    REFERENCES `neaeaexamadmin`.`zone` (`ZoneId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_bin;


-- -----------------------------------------------------
-- Table `neaeaexamadmin`.`school`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `neaeaexamadmin`.`school` ;

CREATE  TABLE IF NOT EXISTS `neaeaexamadmin`.`school` (
  `schoolCode` VARCHAR(30) NOT NULL ,
  `schoolName` VARCHAR(50) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL ,
  `woredaId` INT(11) NOT NULL ,
  `schoolId` INT(11) NOT NULL ,
  PRIMARY KEY (`schoolId`) ,
  INDEX `woredaId` (`woredaId` ASC) ,
  INDEX `schoolCode` () ,
  CONSTRAINT `woredaId`
    FOREIGN KEY (`woredaId` )
    REFERENCES `neaeaexamadmin`.`woreda` (`woredId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `schoolCode`
    FOREIGN KEY ()
    REFERENCES `neaeaexamadmin`.`schoolcodebook` ()
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_bin;


-- -----------------------------------------------------
-- Table `neaeaexamadmin`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `neaeaexamadmin`.`user` ;

CREATE  TABLE IF NOT EXISTS `neaeaexamadmin`.`user` (
  `UserID` INT(11) NOT NULL ,
  `firstName` VARCHAR(50) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL ,
  `lastName` VARCHAR(50) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL ,
  `userName` VARCHAR(50) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL ,
  `Password` VARCHAR(50) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL ,
  `email` VARCHAR(50) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL ,
  `isActive` BIT NOT NULL ,
  `GroupID` INT(11) NOT NULL ,
  PRIMARY KEY (`UserID`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_bin;


-- -----------------------------------------------------
-- Table `neaeaexamadmin`.`usergroup`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `neaeaexamadmin`.`usergroup` ;

CREATE  TABLE IF NOT EXISTS `neaeaexamadmin`.`usergroup` (
  `UserGroupID` INT(11) NOT NULL ,
  `UserGroupName` VARCHAR(30) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL ,
  `Role1` VARCHAR(30) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL ,
  PRIMARY KEY (`UserGroupID`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_bin;


-- -----------------------------------------------------
-- Table `neaeaexamadmin`.`woredaallowance`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `neaeaexamadmin`.`woredaallowance` ;

CREATE  TABLE IF NOT EXISTS `neaeaexamadmin`.`woredaallowance` (
  `lowScale` DECIMAL(10,0) NOT NULL ,
  `meddiumScale` DECIMAL(10,0) NOT NULL ,
  `highScale` DECIMAL(10,0) NOT NULL ,
  `woredaID` INT(11) NOT NULL ,
  `woredaallowanceId` INT(11) NOT NULL ,
  PRIMARY KEY (`woredaallowanceId`) ,
  INDEX `woredaId` (`woredaID` ASC) ,
  CONSTRAINT `woredaId`
    FOREIGN KEY (`woredaID` )
    REFERENCES `neaeaexamadmin`.`woreda` (`woredId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_bin;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
