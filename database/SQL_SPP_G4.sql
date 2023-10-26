-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema spp_database
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema spp_database
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `spp_database` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `spp_database` ;

-- -----------------------------------------------------
-- Table `spp_database`.`subject`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spp_database`.`subject` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `code` TEXT NULL DEFAULT NULL,
  `name` LONGTEXT NULL DEFAULT NULL,
  `description` LONGTEXT NULL DEFAULT NULL,
  `status` BIT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `spp_database`.`class`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spp_database`.`class` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `code` TEXT NULL DEFAULT NULL,
  `subjectID` INT NULL DEFAULT NULL,
  `status` BIT(1) NULL DEFAULT NULL,
  `name` LONGTEXT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `class_subject_id_fk` (`subjectID` ASC) VISIBLE,
  CONSTRAINT `class_subject_id_fk`
    FOREIGN KEY (`subjectID`)
    REFERENCES `spp_database`.`subject` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `spp_database`.`assignment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spp_database`.`assignment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` TEXT NULL DEFAULT NULL,
  `description` LONGTEXT NULL DEFAULT NULL,
  `startdate` DATETIME NOT NULL,
  `enddate` DATETIME NULL DEFAULT NULL,
  `classID` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `assignment_class_id_fk` (`classID` ASC) VISIBLE,
  CONSTRAINT `assignment_class_id_fk`
    FOREIGN KEY (`classID`)
    REFERENCES `spp_database`.`class` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `spp_database`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spp_database`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `fullname` VARCHAR(64) NULL DEFAULT NULL,
  `address` VARCHAR(64) NULL DEFAULT NULL,
  `email` VARCHAR(64) NULL DEFAULT NULL,
  `password` VARCHAR(64) NULL DEFAULT NULL,
  `avatar` VARCHAR(64) NULL DEFAULT NULL,
  `role_id` INT NULL DEFAULT NULL,
  `verify` INT NULL DEFAULT NULL,
  `status` BIT(1) NULL DEFAULT NULL,
  `phone_number` VARCHAR(11) CHARACTER SET 'utf8mb3' NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 36
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `spp_database`.`assignment_grade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spp_database`.`assignment_grade` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `assignment_id` INT NULL DEFAULT NULL,
  `grade` INT NULL DEFAULT NULL,
  `userID` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `assignment_grade_assignment_id_fk` (`assignment_id` ASC) VISIBLE,
  INDEX `assignment_grade_user_user_id_fk` (`userID` ASC) VISIBLE,
  CONSTRAINT `assignment_grade_assignment_id_fk`
    FOREIGN KEY (`assignment_id`)
    REFERENCES `spp_database`.`assignment` (`id`),
  CONSTRAINT `assignment_grade_user_user_id_fk`
    FOREIGN KEY (`userID`)
    REFERENCES `spp_database`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `spp_database`.`class_issue_setting`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spp_database`.`class_issue_setting` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` TEXT NULL DEFAULT NULL,
  `statuses` BIT(1) NULL DEFAULT NULL,
  `workprocess` TEXT NULL DEFAULT NULL,
  `classID` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `class_issue_setting_class_id_fk` (`classID` ASC) VISIBLE,
  CONSTRAINT `class_issue_setting_class_id_fk`
    FOREIGN KEY (`classID`)
    REFERENCES `spp_database`.`class` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `spp_database`.`group`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spp_database`.`group` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `spp_database`.`class_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spp_database`.`class_user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `class_id` INT NULL DEFAULT NULL,
  `user_id` INT NULL DEFAULT NULL,
  `group` INT NULL DEFAULT NULL,
  `isLeader` BIT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `class_user_class_id_fk` (`class_id` ASC) VISIBLE,
  INDEX `class_user_user_user_id_fk` (`user_id` ASC) VISIBLE,
  INDEX `class_user___fk` (`group` ASC) VISIBLE,
  CONSTRAINT `class_user___fk`
    FOREIGN KEY (`group`)
    REFERENCES `spp_database`.`group` (`id`),
  CONSTRAINT `class_user_class_id_fk`
    FOREIGN KEY (`class_id`)
    REFERENCES `spp_database`.`class` (`id`),
  CONSTRAINT `class_user_user_user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `spp_database`.`user` (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `spp_database`.`milestone`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spp_database`.`milestone` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `startDate` DATETIME NULL DEFAULT NULL,
  `endDate` DATETIME NULL DEFAULT NULL,
  `projectID` INT NULL DEFAULT NULL,
  `status` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `spp_database`.`project`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spp_database`.`project` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `code` TEXT NULL DEFAULT NULL,
  `eng_name` TEXT NULL DEFAULT NULL,
  `vietnamese_name` TEXT NULL DEFAULT NULL,
  `status` BIT(1) NULL DEFAULT NULL,
  `groupID` INT NULL DEFAULT NULL,
  `description` LONGTEXT NULL DEFAULT NULL,
  `mentorID` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `project_group_id_fk` (`groupID` ASC) VISIBLE,
  INDEX `project_user_user_id_fk` (`mentorID` ASC) VISIBLE,
  CONSTRAINT `project_group_id_fk`
    FOREIGN KEY (`groupID`)
    REFERENCES `spp_database`.`group` (`id`),
  CONSTRAINT `project_user_user_id_fk`
    FOREIGN KEY (`mentorID`)
    REFERENCES `spp_database`.`user` (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `spp_database`.`setting`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spp_database`.`setting` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` TEXT NULL DEFAULT NULL,
  `isActive` BIT(1) NULL DEFAULT NULL,
  `description` TEXT NULL DEFAULT NULL,
  `type` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `spp_database`.`subjectsetting`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spp_database`.`subjectsetting` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` TEXT NULL DEFAULT NULL,
  `type` TEXT NULL DEFAULT NULL,
  `descrption` LONGTEXT NULL DEFAULT NULL,
  `subjectID` INT NULL DEFAULT NULL,
  `status` BIT(1) NULL DEFAULT NULL,
  `order` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `subjectsetting_subject_id_fk` (`subjectID` ASC) VISIBLE,
  CONSTRAINT `subjectsetting_subject_id_fk`
    FOREIGN KEY (`subjectID`)
    REFERENCES `spp_database`.`subject` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
