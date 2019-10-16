insert into tbg_role (role_description) values ('ROLE_USER');

insert into tbg_user 
    (user_birthday, user_date_creation, user_email, user_first_name, user_last_login, user_lastname, username, password, user_phone) 
values
    ('1986-06-06', CURRENT_DATE, 'wellingtonsilvamelo@gmail.com', 'Wellington', CURRENT_TIMESTAMP, 'Melo', 'wellingtonsilvamelo', '$2a$10$ts.U67GyV/HjAwlmA4e9IeHKY1FViXeTC4Zqb.3XdPfp8dOHpZuOK', '81995146111');

insert into tbg_user_role (role_id, user_id) values (1, 1);

insert into tbg_car (car_color, car_lincense_plate, car_model, user_id, car_year) values ('BRANCO NEVE', 'KYA1234', 'FIAT ARGO DRIVE 1.0 16.V FIREFLY', 1, 2019);
