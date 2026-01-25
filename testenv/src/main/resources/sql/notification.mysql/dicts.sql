
-- message status
delete from FWK_FDICT where id='MESSAGESTATUS';
insert into FWK_FDICT(ID,NAME,PARENT_ID,DICT_ITEM_TYPE,KEY_TYPE) values ('MESSAGESTATUS','Статус сообщения',NULL,'MESSAGESTATUS','STRING');

delete from DIC_DICTTYPE where TYPE=520;
insert into DIC_DICTTYPE(TYPE,NAME,TAG) VALUES(520,'Статус report','MESSAGESTATUS');

delete from DIC_DICTITEM where TYPE=520;
insert into DIC_DICTITEM(ID,TYPE,NAME,VALUE2) VALUES(520,520,'Новый','new');
insert into DIC_DICTITEM(ID,TYPE,NAME,VALUE2) VALUES(521,520,'Обработан','processed');
insert into DIC_DICTITEM(ID,TYPE,NAME,VALUE2) VALUES(522,520,'В архиве','archive');
