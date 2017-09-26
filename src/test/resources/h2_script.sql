CREATE SCHEMA IF NOT EXISTS "task management wizard" ;
USE "task management wizard" ;

-- -----------------------------------------------------
-- Table `task management wizard`.`Users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS "task management wizard"."Users" (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `pass` CHAR(32) NULL,
  `email` VARCHAR(254) NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table `task management wizard`.`Status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS "task management wizard"."Status" (
  `status_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`status_id`))     
ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table `task management wizard`.`Priorities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS "task management wizard"."Priorities" (
  `priority_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`priority_id`))
ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table `task management wizard`.`Tasks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS "task management wizard"."Tasks" (
  `task_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `created_date` TIMESTAMP NULL,
  `start_date` TIMESTAMP NULL,
  `end_date` TIMESTAMP NULL,
  `estimate_time` TIMESTAMP NULL,
  `assign_to` INT NULL,
  `status_id` INT NULL,
  `priority_id` INT NULL,
  PRIMARY KEY (`task_id`),
    FOREIGN KEY (`status_id`)
    REFERENCES "task management wizard"."Status" (`status_id`),  
    FOREIGN KEY (`priority_id`)
    REFERENCES "task management wizard"."Priorities" (`priority_id`))   
ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table `task management wizard`.`Comments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS "task management wizard"."Comments" (
  `comment_id` INT NOT NULL AUTO_INCREMENT,
  `comment` TEXT NOT NULL,
  `created_date` TIMESTAMP NULL,
  `task_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`comment_id`),
     FOREIGN KEY (`task_id`)
    REFERENCES "task management wizard"."Tasks" (`task_id`),
    FOREIGN KEY (`user_id`)
    REFERENCES "task management wizard"."Users" (`user_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `task management wizard`.`Tags`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS "task management wizard"."Tags" (
  `tag_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`tag_id`),
    FOREIGN KEY (`user_id`)
    REFERENCES "task management wizard"."Users" (`user_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `task management wizard`.`tags_tasks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS "task management wizard"."tags_tasks" (
  `tag_task_id` INT NOT NULL AUTO_INCREMENT,
  `tag_id` INT NOT NULL,
  `task_id` INT NOT NULL,
  PRIMARY KEY (`tag_task_id`, `tag_id`, `task_id`),
    FOREIGN KEY (`tag_id`)
    REFERENCES "task management wizard"."Tags" (`tag_id`), 
    FOREIGN KEY (`task_id`)
    REFERENCES "task management wizard"."Tasks" (`task_id`))
ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table `task management wizard`.`Roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS "task management wizard"."Roles" (
  `role_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`role_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `task management wizard`.`users_tasks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS "task management wizard"."users_tasks" (
  `users_tasks_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `task_id` INT NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`users_tasks_id`),
    FOREIGN KEY (`user_id`)
    REFERENCES "task management wizard"."Users" (`user_id`),    
    FOREIGN KEY (`task_id`)
    REFERENCES "task management wizard"."Tasks" (`task_id`),
    FOREIGN KEY (`role_id`)
    REFERENCES "task management wizard"."Roles" (`role_id`))
ENGINE = InnoDB;