-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema ssafytrip
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ssafytrip
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ssafytrip` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `ssafytrip` ;

-- -----------------------------------------------------
-- Table `ssafytrip`.`sidos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafytrip`.`sidos` ;

CREATE TABLE IF NOT EXISTS `ssafytrip`.`sidos` (
                                                   `no` INT NOT NULL AUTO_INCREMENT,
                                                   `sido_code` INT NULL DEFAULT NULL,
                                                   `sido_name` VARCHAR(20) NULL DEFAULT NULL,
                                                   PRIMARY KEY (`no`),
                                                   UNIQUE INDEX `sido_code_UNIQUE` (`sido_code` ASC) VISIBLE)
    ENGINE = InnoDB
    AUTO_INCREMENT = 18
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafytrip`.`guguns`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafytrip`.`guguns` ;

CREATE TABLE IF NOT EXISTS `ssafytrip`.`guguns` (
                                                    `no` INT NOT NULL AUTO_INCREMENT,
                                                    `sido_code` INT NULL DEFAULT NULL,
                                                    `gugun_code` INT NULL DEFAULT NULL,
                                                    `gugun_name` VARCHAR(20) NULL DEFAULT NULL,
                                                    PRIMARY KEY (`no`),
                                                    INDEX `guguns_sido_to_sidos_cdoe_fk_idx` (`sido_code` ASC) VISIBLE,
                                                    INDEX `gugun_code_idx` (`gugun_code` ASC) VISIBLE,
                                                    CONSTRAINT `guguns_sido_to_sidos_cdoe_fk`
                                                        FOREIGN KEY (`sido_code`)
                                                            REFERENCES `ssafytrip`.`sidos` (`sido_code`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 235
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafytrip`.`contenttypes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafytrip`.`contenttypes` ;

CREATE TABLE IF NOT EXISTS `ssafytrip`.`contenttypes` (
                                                          `content_type_id` INT NOT NULL,
                                                          `content_type_name` VARCHAR(45) NULL DEFAULT NULL,
                                                          PRIMARY KEY (`content_type_id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafytrip`.`attractions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafytrip`.`attractions` ;

CREATE TABLE IF NOT EXISTS `ssafytrip`.`attractions` (
                                                         `no` INT NOT NULL AUTO_INCREMENT,
                                                         `content_id` INT NULL DEFAULT NULL,
                                                         `title` VARCHAR(500) NULL DEFAULT NULL,
                                                         `content_type_id` INT NULL DEFAULT NULL,
                                                         `area_code` INT NULL DEFAULT NULL,
                                                         `si_gun_gu_code` INT NULL DEFAULT NULL,
                                                         `first_image1` VARCHAR(100) NULL DEFAULT NULL,
                                                         `first_image2` VARCHAR(100) NULL DEFAULT NULL,
                                                         `map_level` INT NULL DEFAULT NULL,
                                                         `latitude` DECIMAL(20,17) NULL DEFAULT NULL,
                                                         `longitude` DECIMAL(20,17) NULL DEFAULT NULL,
                                                         `tel` VARCHAR(20) NULL DEFAULT NULL,
                                                         `addr1` VARCHAR(100) NULL DEFAULT NULL,
                                                         `addr2` VARCHAR(100) NULL DEFAULT NULL,
                                                         `homepage` VARCHAR(1000) NULL DEFAULT NULL,
                                                         `overview` VARCHAR(10000) NULL DEFAULT NULL,
                                                         PRIMARY KEY (`no`),
                                                         INDEX `attractions_typeid_to_types_typeid_fk_idx` (`content_type_id` ASC) VISIBLE,
                                                         INDEX `attractions_sido_to_sidos_code_fk_idx` (`area_code` ASC) VISIBLE,
                                                         INDEX `attractions_sigungu_to_guguns_gugun_fk_idx` (`si_gun_gu_code` ASC) VISIBLE,
                                                         CONSTRAINT `attractions_area_to_sidos_code_fk`
                                                             FOREIGN KEY (`area_code`)
                                                                 REFERENCES `ssafytrip`.`sidos` (`sido_code`),
                                                         CONSTRAINT `attractions_sigungu_to_guguns_gugun_fk`
                                                             FOREIGN KEY (`si_gun_gu_code`)
                                                                 REFERENCES `ssafytrip`.`guguns` (`gugun_code`),
                                                         CONSTRAINT `attractions_typeid_to_types_typeid_fk`
                                                             FOREIGN KEY (`content_type_id`)
                                                                 REFERENCES `ssafytrip`.`contenttypes` (`content_type_id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 56644
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
-- Users table
CREATE TABLE IF NOT EXISTS `ssafytrip`.`users` (
                                                   `user_id` INT NOT NULL AUTO_INCREMENT,
                                                   `username` VARCHAR(50) NOT NULL,
                                                   `password` VARCHAR(255) NOT NULL,
                                                   `email` VARCHAR(100) NOT NULL,
                                                   PRIMARY KEY (`user_id`)
);

-- Board Likes table
CREATE TABLE IF NOT EXISTS `ssafytrip`.`attraction_likes` (
                                                              `like_id` INT NOT NULL AUTO_INCREMENT,
                                                              `attraction_no` INT NOT NULL,
                                                              `user_id` INT NOT NULL,
                                                              PRIMARY KEY (`like_id`),
    -- Prevent duplicate likes from the same user on the same board
                                                              INDEX `idx_attraction_likes_user` (`user_id` ASC) VISIBLE,
    -- Foreign key to boards table
                                                              CONSTRAINT `fk_attraction_likes_attraction`
                                                                  FOREIGN KEY (`attraction_no`)
                                                                      REFERENCES `attractions` (`no`)
                                                                      ON DELETE CASCADE
                                                                      ON UPDATE CASCADE,
    -- Foreign key to users table
                                                              CONSTRAINT `fk_attraction_likes_user`
                                                                  FOREIGN KEY (`user_id`)
                                                                      REFERENCES `users` (`user_id`)
                                                                      ON DELETE CASCADE
                                                                      ON UPDATE CASCADE
);


drop table reviews;
-- auto-generated definition
create table reviews
(
    id            bigint auto_increment
        primary key,
    attraction_no bigint        not null,
    content       varchar(1000) null,
    created_at    datetime(6)   null,
    rating        int           not null,
    updated_at    datetime(6)   null,
    useful_votes  int           null,
    user_name     varchar(255)  not null
);

-- Trips table
CREATE TABLE IF NOT EXISTS `ssafytrip`.`trips` (
                                                   `trip_id` INT NOT NULL AUTO_INCREMENT,
                                                   `user_id` INT NOT NULL,
                                                   `content` VARCHAR(1000) NOT NULL,
                                                   `sido_code` INT NOT NULL,
                                                   `start_date` DATE NOT NULL,
                                                   `end_date` DATE NOT NULL,
                                                   PRIMARY KEY (`trip_id`),
                                                   FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
                                                   CONSTRAINT `check_date_range` CHECK (`end_date` >= `start_date`)  -- 종료일이 시작일보다 이후인지 확인
);

-- Trip Attractions table
create table trip_attractions
(
    trip_attraction_id int auto_increment
        primary key,
    trip_id            int not null,
    attraction_no      int not null,
    day               int not null,
    sequence          int not null,    -- 각 날짜별 방문 순서
    foreign key (trip_id) references trips (trip_id),
    foreign key (attraction_no) references attractions (no)
);


ALTER TABLE `ssafytrip`.`trips`
    ADD COLUMN `is_public` BOOLEAN NOT NULL DEFAULT FALSE COMMENT '여행 계획 공개 여부',
    ADD COLUMN `copied_count` INT NOT NULL DEFAULT 0 COMMENT '복사된 횟수';

ALTER table 'ssafytrip'.'users'
    ADD COLUMN 'preference' bigint not null default 0;

SET FOREIGN_KEY_CHECKS = 0;

-- contenttypes 테이블 업데이트
UPDATE contenttypes
SET content_type_id =
        CASE content_type_id
            WHEN 12 THEN 1  -- 관광지
            WHEN 14 THEN 2  -- 문화시설
            WHEN 15 THEN 3  -- 축제공연행사
            WHEN 25 THEN 4  -- 여행코스
            WHEN 28 THEN 5  -- 레포츠
            WHEN 32 THEN 6  -- 숙박
            WHEN 38 THEN 7  -- 쇼핑
            WHEN 39 THEN 8  -- 음식점
            END
WHERE content_type_id IN (12, 14, 15, 25, 28, 32, 38, 39);

-- attractions 테이블 업데이트 (외래키로 연결된 경우)
UPDATE attractions
SET content_type_id =
        CASE content_type_id
            WHEN 12 THEN 1
            WHEN 14 THEN 2
            WHEN 15 THEN 3
            WHEN 25 THEN 4
            WHEN 28 THEN 5
            WHEN 32 THEN 6
            WHEN 38 THEN 7
            WHEN 39 THEN 8
            END
WHERE content_type_id IN (12, 14, 15, 25, 28, 32, 38, 39);

-- 외래키 제약조건 다시 활성화
SET FOREIGN_KEY_CHECKS = 1;