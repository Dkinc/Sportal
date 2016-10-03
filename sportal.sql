-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema SportalDB
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema SportalDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `SportalDB` DEFAULT CHARACTER SET utf8 ;
USE `SportalDB` ;

-- -----------------------------------------------------
-- Table `SportalDB`.`Users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SportalDB`.`Users` (
  `idUsers` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  PRIMARY KEY (`idUsers`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SportalDB`.`Category_of_news`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SportalDB`.`Category_of_news` (
  `idCategory_of_news` INT NOT NULL,
  `category` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idCategory_of_news`),
  INDEX `category` USING BTREE (`category` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SportalDB`.`News`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SportalDB`.`News` (
  `idNews` INT NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `number_of_reads` INT NOT NULL,
  `picture_address` VARCHAR(150) NOT NULL,
  `video_address` VARCHAR(150) NULL,
  `Category_of_news_idCategory_of_news` INT NOT NULL,
  `text` LONGTEXT NOT NULL,
  PRIMARY KEY (`idNews`, `Category_of_news_idCategory_of_news`),
  INDEX `title` (`Category_of_news_idCategory_of_news` ASC),
  CONSTRAINT `fk_News_Category_of_news1`
    FOREIGN KEY (`Category_of_news_idCategory_of_news`)
    REFERENCES `SportalDB`.`Category_of_news` (`idCategory_of_news`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SportalDB`.`Comments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SportalDB`.`Comments` (
  `idComments` INT NOT NULL,
  `text` MEDIUMTEXT NOT NULL,
  `date_and_time` DATETIME NULL,
  `Users_idUsers` INT NOT NULL,
  `News_idNews` INT NOT NULL,
  `number_of_likes` INT NULL,
  `number_of_dislikes` INT NULL,
  `title_of_comment` VARCHAR(45) NULL,
  PRIMARY KEY (`idComments`, `Users_idUsers`, `News_idNews`),
  INDEX (`Users_idUsers` ASC),
  INDEX (`News_idNews` ASC),
  CONSTRAINT `fk_Comments_Users`
    FOREIGN KEY (`Users_idUsers`)
    REFERENCES `SportalDB`.`Users` (`idUsers`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Comments_News1`
    FOREIGN KEY (`News_idNews`)
    REFERENCES `SportalDB`.`News` (`idNews`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SportalDB`.`User_Profile`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SportalDB`.`User_Profile` (
  `Users_idUsers` INT NOT NULL,
  `profile_pic` BLOB NULL,
  `email` VARCHAR(45) NULL,
  PRIMARY KEY (`Users_idUsers`),
  CONSTRAINT `fk_table1_Users1`
    FOREIGN KEY (`Users_idUsers`)
    REFERENCES `SportalDB`.`Users` (`idUsers`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SportalDB`.`Like_comments_by_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SportalDB`.`Like_comments_by_user` (
  `Comments_idComments` INT NOT NULL,
  `Comments_Users_idUsers` INT NOT NULL,
  `Comments_News_idNews` INT NOT NULL,
  `users_that_like_comment` VARCHAR(45) NULL,
  PRIMARY KEY (`Comments_idComments`, `Comments_Users_idUsers`, `Comments_News_idNews`),
  INDEX (`Comments_idComments` ASC, `Comments_Users_idUsers` ASC, `Comments_News_idNews` ASC),
  CONSTRAINT `fk_Like_or_dislike_comments_by_users_Comments1`
    FOREIGN KEY (`Comments_idComments` , `Comments_Users_idUsers` , `Comments_News_idNews`)
    REFERENCES `SportalDB`.`Comments` (`idComments` , `Users_idUsers` , `News_idNews`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SportalDB`.`Dislike_comments_by_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SportalDB`.`Dislike_comments_by_user` (
  `Comments_idComments` INT NOT NULL,
  `Comments_Users_idUsers` INT NOT NULL,
  `Comments_News_idNews` INT NOT NULL,
  `users_that_dislike_comment` VARCHAR(45) NULL,
  PRIMARY KEY (`Comments_idComments`, `Comments_Users_idUsers`, `Comments_News_idNews`),
  CONSTRAINT `fk_Dislike_comments_by_user_Comments1`
    FOREIGN KEY (`Comments_idComments` , `Comments_Users_idUsers` , `Comments_News_idNews`)
    REFERENCES `SportalDB`.`Comments` (`idComments` , `Users_idUsers` , `News_idNews`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
