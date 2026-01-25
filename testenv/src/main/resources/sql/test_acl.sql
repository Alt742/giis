-- users
DELETE FROM `acl_user`;
insert into ACL_USER(ID,LOGIN,PASSWORD) values(1,'admin','d592ccb070e57252d1fd095fc8acb7c1');
INSERT INTO `acl_user` (`ID`, `LOGIN`, `PASSWORD`, `CREATE_TIME`, `LAST_LOGIN_TIME`) VALUES ('100', 'test1@test.com', 'hash', '2017-10-25 22:41:44', '2017-10-25 22:41:44');
INSERT INTO `acl_user` (`ID`, `LOGIN`, `PASSWORD`, `CREATE_TIME`, `LAST_LOGIN_TIME`) VALUES ('110', 'test2@test.com', 'hash', '2017-10-25 22:41:44', '2017-10-25 22:41:44');
INSERT INTO `acl_user` (`ID`, `LOGIN`, `PASSWORD`, `CREATE_TIME`, `LAST_LOGIN_TIME`) VALUES ('120', 'test3@test.com', 'hash', '2017-10-25 22:41:44', '2017-10-25 22:41:44');
INSERT INTO `acl_user` (`ID`, `LOGIN`, `PASSWORD`, `CREATE_TIME`, `LAST_LOGIN_TIME`) VALUES ('130', 'test4@test.com', 'hash', '2017-10-25 22:41:44', '2017-10-25 22:41:44');



-- groups
DELETE FROM `acl_group`;
INSERT INTO `acl_group` (`ID`, `NAME`, `DESCRIPTION`) VALUES ('10', 'admin', 'admin');
INSERT INTO `acl_group` (`ID`, `NAME`, `DESCRIPTION`) VALUES ('20', 'user', 'user');

-- user groups
DELETE FROM `acl_user_group`;
INSERT INTO `acl_user_group` (`USER_ID`, `GROUP_ID`) VALUES ('100', '10');
INSERT INTO `acl_user_group` (`USER_ID`, `GROUP_ID`) VALUES ('110', '20');
INSERT INTO `acl_user_group` (`USER_ID`, `GROUP_ID`) VALUES ('120', '20');
INSERT INTO `acl_user_group` (`USER_ID`, `GROUP_ID`) VALUES ('130', '20');

-- objects
DELETE FROM `acl_object_identity`;
INSERT INTO `acl_object_identity` (`ID`, `PARENT_ID`, `TAG`, `COMPONENT`, `NAME`) VALUES ('10', null, 'notification', 'fw', 'notification');
INSERT INTO `acl_object_identity` (`ID`, `PARENT_ID`, `TAG`, `COMPONENT`, `NAME`) VALUES ('100', '10', 'id', 'fw', 'id');
INSERT INTO `acl_object_identity` (`ID`, `PARENT_ID`, `TAG`, `COMPONENT`, `NAME`) VALUES ('110', '10', 'toId', 'fw', 'toId');
INSERT INTO `acl_object_identity` (`ID`, `PARENT_ID`, `TAG`, `COMPONENT`, `NAME`) VALUES ('120', '10', 'title', 'fw', 'title');
INSERT INTO `acl_object_identity` (`ID`, `PARENT_ID`, `TAG`, `COMPONENT`, `NAME`) VALUES ('130', '10', 'description', 'fw', 'description');
INSERT INTO `acl_object_identity` (`ID`, `PARENT_ID`, `TAG`, `COMPONENT`, `NAME`) VALUES ('140', '10', 'status', 'fw', 'status');
INSERT INTO `acl_object_identity` (`ID`, `PARENT_ID`, `TAG`, `COMPONENT`, `NAME`) VALUES ('150', '10', 'added', 'fw', 'added');
INSERT INTO `acl_object_identity` (`ID`, `PARENT_ID`, `TAG`, `COMPONENT`, `NAME`) VALUES ('160', '10', 'systemField', 'fw', 'systemField');
-- acl entry
DELETE FROM `acl_entry`;
INSERT INTO `acl_entry` (`ID`, `TYPE`, `OBJECT_ID`, `SID_ID`, `SID_TYPE`, `PERMISSIONS`) VALUES ('1', 'fw', '10', '10', 'group', '5');
INSERT INTO `acl_entry` (`ID`, `TYPE`, `OBJECT_ID`, `SID_ID`, `SID_TYPE`, `PERMISSIONS`) VALUES ('3', 'fw', '10', '20', 'group', '11');

INSERT INTO `acl_entry` (`ID`, `TYPE`, `OBJECT_ID`, `SID_ID`, `SID_TYPE`, `PERMISSIONS`) VALUES ('6', 'fw', '100', '10', 'group', '5');
INSERT INTO `acl_entry` (`ID`, `TYPE`, `OBJECT_ID`, `SID_ID`, `SID_TYPE`, `PERMISSIONS`) VALUES ('7', 'fw', '110', '10', 'group', '5');
INSERT INTO `acl_entry` (`ID`, `TYPE`, `OBJECT_ID`, `SID_ID`, `SID_TYPE`, `PERMISSIONS`) VALUES ('8', 'fw', '120', '10', 'group', '5');
INSERT INTO `acl_entry` (`ID`, `TYPE`, `OBJECT_ID`, `SID_ID`, `SID_TYPE`, `PERMISSIONS`) VALUES ('9', 'fw', '130', '100', 'user', '5');
INSERT INTO `acl_entry` (`ID`, `TYPE`, `OBJECT_ID`, `SID_ID`, `SID_TYPE`, `PERMISSIONS`) VALUES ('10', 'fw', '140', '10', 'group', '5');
INSERT INTO `acl_entry` (`ID`, `TYPE`, `OBJECT_ID`, `SID_ID`, `SID_TYPE`, `PERMISSIONS`) VALUES ('11', 'fw', '150', '10', 'group', '5');
INSERT INTO `acl_entry` (`ID`, `TYPE`, `OBJECT_ID`, `SID_ID`, `SID_TYPE`, `PERMISSIONS`) VALUES ('12', 'fw', '160', '10', 'group', '5');
INSERT INTO `acl_entry` (`ID`, `TYPE`, `OBJECT_ID`, `SID_ID`, `SID_TYPE`, `PERMISSIONS`) VALUES ('20', 'fw', '100', '20', 'group', '1');
INSERT INTO `acl_entry` (`ID`, `TYPE`, `OBJECT_ID`, `SID_ID`, `SID_TYPE`, `PERMISSIONS`) VALUES ('21', 'fw', '110', '20', 'group', '1');
INSERT INTO `acl_entry` (`ID`, `TYPE`, `OBJECT_ID`, `SID_ID`, `SID_TYPE`, `PERMISSIONS`) VALUES ('22', 'fw', '120', '20', 'group', '3');
INSERT INTO `acl_entry` (`ID`, `TYPE`, `OBJECT_ID`, `SID_ID`, `SID_TYPE`, `PERMISSIONS`) VALUES ('23', 'fw', '130', '110', 'user', '3');
INSERT INTO `acl_entry` (`ID`, `TYPE`, `OBJECT_ID`, `SID_ID`, `SID_TYPE`, `PERMISSIONS`) VALUES ('24', 'fw', '130', '120', 'user', '3');
INSERT INTO `acl_entry` (`ID`, `TYPE`, `OBJECT_ID`, `SID_ID`, `SID_TYPE`, `PERMISSIONS`) VALUES ('25', 'fw', '130', '130', 'user', '3');
INSERT INTO `acl_entry` (`ID`, `TYPE`, `OBJECT_ID`, `SID_ID`, `SID_TYPE`, `PERMISSIONS`) VALUES ('26', 'fw', '140', '20', 'group', '1');
INSERT INTO `acl_entry` (`ID`, `TYPE`, `OBJECT_ID`, `SID_ID`, `SID_TYPE`, `PERMISSIONS`) VALUES ('27', 'fw', '150', '20', 'group', '1');



INSERT INTO `acl_user` (`ID`, `LOGIN`, `PASSWORD`, `CREATE_TIME`, `LAST_LOGIN_TIME`) VALUES ('121', 'test@test.test', 'fa6e95efcc49c18f17a970c8633b36cd', '2017-10-25 22:44:49', '2018-08-08 15:45:06');