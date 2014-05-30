SET @user = 'config';

/* Site Config */
INSERT INTO `bees_config` (`name`, `description`, `type`, `value`, `status`) VALUES ('companyName', 'Company name, as it will appear on documentation', 'string', 'Weekly Reports', 1);
INSERT INTO `bees_config` (`name`, `description`, `type`, `value`, `status`) VALUES ('seoTitle', 'Suffix for page names that will appear in the title tabs', 'string', ' | Weeekly Reports', 1);
INSERT INTO `bees_config` (`name`, `description`, `type`, `value`, `status`) VALUES ('paginationSize', 'How many pages will show around the active pagination button. A setting of \'2\' means that 5 pages will be shown if available at all times.', 'integer', '2', 1);
INSERT INTO `bees_config` (`name`, `description`, `type`, `value`, `status`) VALUES ('sortOrders', 'JSON array for the standard sort orders used for paginated collections. E.g. products, album images, news articles.', 'map', '{\r\n  "user": {\r\n    "Name (A - Z)": "firstname_asc",\r\n    "Name (Z - A)": "firstname_desc",\r\n    "Username (A - Z)": "name_asc",\r\n    "Username (Z - A)": "name_desc",\r\n    "Authority": "authority_asc",\r\n    "Email address": "email_asc"\r\n  },\r\n  "project": {\r\n    "Name (A - Z)": "name_asc",\r\n    "Name (Z - A)": "name_desc",\r\n    "Due date (Soonest - Latest)": "dueDate_asc",\r\n    "Due date (Latest - Soonest)": "dueDate_desc",\r\n    "Test date (Soonest - Latest)": "testDate_asc",\r\n    "Test date (Latest - Soonest)": "testDate_desc"\r\n  }\r\n}', 1);

/* Basic users (pass = 'testtest') */
INSERT INTO `bees_user` (`name`, `email`, `firstname`, `password`, `surname`, `status`, `authority`) VALUES ('test1', 'aaa@bbb.com', 'aaa', '$2a$10$c7gW42kYVsjQwzve0j7c0uzGdNJG1BIEcq93AE3ZXg67D3vsqZGfy', 'bbb', 1, 3);
INSERT INTO `bees_user` (`name`, `email`, `firstname`, `password`, `surname`, `status`, `authority`) VALUES ('test2', 'bbb@bbb.com', 'bbb', '$2a$10$kO4e0.P9unwH27kZnmgLjeuuPWuupx735k2L5xWS/.3weI93Xc3D.', 'bbb', 1, 1);

/* Set datestamps */
UPDATE `bees_config` set `dateCreated` = now(), `lastEdited` = now(), `createdBy` = @user, `lastEditedBy` = @user;
UPDATE `bees_user` set `dateCreated` = now(), `lastEdited` = now(), `createdBy` = @user, `lastEditedBy` = @user;