insert into user_info (id,active, age,first_name,last_name,password,username) values (1,1, 90, 'a' , 'a', '$2a$08$giBOFT9QDoGaFdag43Mgf.HSWCSoS5WRXxTjLLi0glMnqNUnaCUFS', 'admin');
insert into user_role (user_id, roles ) values (1,'ADMIN');
insert into user_role (user_id, roles ) values (1,'USER');
