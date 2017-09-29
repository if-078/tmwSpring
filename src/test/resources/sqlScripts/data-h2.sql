CREATE SCHEMA IF NOT EXISTS 'tmw';
USE 'tmw';

-- -----------------------------------------------------
-- Table 'tmw'.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS 'tmw'.'User' (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `pass` CHAR(32) NULL,
  `email` VARCHAR(254) NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table 'tmw'.`Status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS 'tmw'.'Status'(
  `status_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`status_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table 'tmw'.`Priority`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS 'tmw'.'Priority' (
  `priority_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`priority_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table 'tmw'.`Task`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS 'tmw'.'Task' (
  `task_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `created_date` TIMESTAMP NULL,
  `start_date` TIMESTAMP NULL,
  `end_date` TIMESTAMP NULL,
  `estimate_time` TIME NULL,
  `assign_to` INT NULL,
  `status_id` INT NULL,
  `priority_id` INT NULL,
  `parent_id` INT NULL,
  PRIMARY KEY (`task_id`),
  CONSTRAINT `fk_Tasks_Status1`
    FOREIGN KEY (`status_id`)
    REFERENCES 'tmw'.'Status'(`status_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Tasks_Priorities1`
    FOREIGN KEY (`priority_id`)
    REFERENCES 'tmw'.'Priority' (`priority_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table 'tmw'.`Comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS 'tmw'.'Comment' (
  `comment_id` INT NOT NULL AUTO_INCREMENT,
  `comment` TEXT NOT NULL,
  `created_date` DATETIME NULL,
  `task_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`comment_id`),
  CONSTRAINT `fk_Comments_Tasks1`
    FOREIGN KEY (`task_id`)
    REFERENCES 'tmw'.'Task' (`task_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Comments_Users1`
    FOREIGN KEY (`user_id`)
    REFERENCES 'tmw'.'User' (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table 'tmw'.`Tag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS 'tmw'.'Tag' (
  `tag_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`tag_id`),
  CONSTRAINT `fk_Tags_Users1`
    FOREIGN KEY (`user_id`)
    REFERENCES 'tmw'.'User' (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table 'tmw'.`tags_tasks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS 'tmw'.'tags_tasks' (
  `tag_task_id` INT NOT NULL AUTO_INCREMENT,
  `tag_id` INT NOT NULL,
  `task_id` INT NOT NULL,
  PRIMARY KEY (`tag_task_id`),
  CONSTRAINT `fk_tags_tasks_Tags1`
    FOREIGN KEY (`tag_id`)
    REFERENCES 'tmw'.'Tag' (`tag_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_tags_tasks_Tasks1`
    FOREIGN KEY (`task_id`)
    REFERENCES 'tmw'.'Task' (`task_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table 'tmw'.`Role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS 'tmw'.'Role'(
  `role_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`role_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table 'tmw'.`users_tasks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS 'tmw'.'users_tasks' (
  `users_tasks_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `task_id` INT NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`users_tasks_id`),
  CONSTRAINT `fk_users_tasks_Users1`
    FOREIGN KEY (`user_id`)
    REFERENCES 'tmw'.'User'(`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_users_tasks_Tasks1`
    FOREIGN KEY (`task_id`)
    REFERENCES 'tmw'.'Task'(`task_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_users_tasks_Roles1`
    FOREIGN KEY (`role_id`)
    REFERENCES 'tmw'.'Role' (`role_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;