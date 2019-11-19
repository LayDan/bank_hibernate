insert into user_info (id,active, age,first_name,last_name,password,username) values (1,1, 90, 'a' , 'a', 'xladik18', 'admin');
insert into user_role (user_id, roles ) values (1,'ADMIN');
insert into user_role (user_id, roles ) values (1,'USER');

--Проблема с шифрованием пароля администратора
update user_info set password = encrypt('XTEA', '00', STRINGTOUTF8(password));
