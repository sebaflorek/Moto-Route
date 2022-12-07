INSERT INTO roles (id, name)
VALUES (null, 'ROLE_USER'),
       (null, 'ROLE_ADMIN')
;

INSERT INTO regions (name, city)
VALUES ('ogólnopolskie', 'Warszawa'),
       ('dolnośląskie', 'Wrocław'),
       ('kujawsko-pomorskie', 'Bydgoszcz'),
       ('lubelskie', 'Lublin'),
       ('lubuskie', 'Zielona Góra'),
       ('łódzkie', 'Łódź'),
       ('małopolskie', 'Kraków'),
       ('mazowieckie', 'Wrocław'),
       ('opolskie', 'Opole'),
       ('podkarpackie', 'Rzeszów'),
       ('podlaskie', 'Białystok'),
       ('pomorskie', 'Gdańsk'),
       ('śląskie', 'Katowice'),
       ('świętokrzyskie', 'Kielce'),
       ('warmińsko-mazurskie', 'Warszawa'),
       ('wielkopolskie', 'Poznań'),
       ('zachodniopomorskie', 'Szczecin')
;

INSERT INTO types (name)
VALUES ('drogowa'),
       ('terenowa'),
       ('mieszana')
;

INSERT INTO users (username, email, password, enabled)
VALUES ('user', 'user@mr.pl', '$2a$10$vATBTwreQmUc4NmmQCjr8.q3hyIYNx582a5ukVflpx0tOdoL7EvUq', 1),
       ('admin', 'admin@mr.pl', '$2a$10$vATBTwreQmUc4NmmQCjr8.q3hyIYNx582a5ukVflpx0tOdoL7EvUq', 1),
       ('test', 'test@mr.pl', '$2a$10$vATBTwreQmUc4NmmQCjr8.q3hyIYNx582a5ukVflpx0tOdoL7EvUq', 1)
;

INSERT INTO users_roles (user_id, role_id)
VALUES (1, 1),
       (2, 1),
       (2, 2),
       (3, 1)
;

INSERT INTO routes (name, distance, map, description, popularity, likes, created, author_id, region_id, type_id)
VALUES ('Poznaj świętokrzyskie', 76,
        'https://maps.openrouteservice.org/#/directions/Path%20of%20Monk,Ch%C4%99ciny,SK,Polska/Ruiny%20zamku%20%22Krzy%C5%BCtop%C3%B3r,Iwaniska,SK,Polska/data/55,130,32,198,15,97,4,224,38,9,96,59,2,24,5,192,166,6,113,0,184,64,38,0,24,3,160,5,128,54,1,25,12,42,243,72,21,129,194,7,96,3,129,128,105,158,37,129,56,250,151,192,192,51,31,54,125,40,119,32,192,55,62,74,196,71,81,22,210,183,18,45,42,149,98,19,136,8,0,29,83,192,136,155,14,80,0,188,160,5,181,205,95,97,232,16,1,155,192,3,110,151,8,88,209,224,1,185,32,3,152,2,209,131,35,64,128,2,251,69,0,0',
        'Malownicza trasa z Kielc do ruin zamku Krzyżtopór w Ujeździe', 5, 4, now(), 1, 14, 1),
       ('Poznaj małopolskie', 100,
        'https://maps.openrouteservice.org/#/directions/Quattro%20Nowy%20S%C4%85cz,Nowy%20S%C4%85cz,MA,Polska/Krak%C3%B3w%20Z%C5%82ocie%C5%84,Krak%C3%B3w,MA,Polska/data/55,130,32,198,15,97,4,224,38,9,96,59,2,24,5,192,166,6,113,0,184,64,38,0,24,3,160,13,128,78,1,88,1,102,160,102,0,105,175,44,129,24,0,229,53,129,216,6,226,56,161,74,172,68,52,162,80,135,86,133,72,128,98,2,0,7,84,240,34,38,195,148,0,47,40,1,109,114,181,47,49,116,8,0,205,224,1,183,75,132,44,104,240,1,185,32,14,96,22,140,50,104,114,64,159,70,125,26,29,17,12,6,207,8,50,23,87,88,54,29,22,4,0,23,193,40,0,0',
        'Malownicza trasa po Małopolsce', 6, 5, now() - interval 1 hour, 1, 7, 1),
       ('Poznaj podkarpackie', 209,
        'https://maps.openrouteservice.org/#/directions/Atlantik%20Market%20II,Dukla,PK,Polska/Grodzisko%20%C5%9Bredniowieczne,Olszanica,PK,Polska/Jana%20III%20Sobieskiego,Cieszan%C3%B3w,PK,Polska/data/55,130,32,198,15,97,4,224,38,9,96,59,2,24,5,192,166,6,113,0,184,64,38,1,24,3,160,13,128,14,125,200,19,144,242,1,96,21,128,118,0,24,6,100,63,70,1,167,186,226,141,27,213,40,84,189,86,165,168,72,160,27,159,62,98,19,9,119,106,196,121,102,141,91,171,224,62,187,102,71,91,85,30,93,190,106,237,231,182,40,93,161,230,237,200,118,104,68,253,30,219,139,224,53,63,43,2,144,139,189,61,8,15,8,4,0,3,170,60,4,34,54,14,40,0,23,148,0,45,174,53,4,84,116,4,0,25,188,0,13,186,46,8,44,52,60,0,27,146,0,57,128,45,24,50,52,56,72,14,122,30,122,52,58,34,24,9,94,23,100,26,90,119,108,58,44,8,0,47,132,208,0',
        'Malownicza trasa po Podkarpaciu', 7, 6, now() - interval 2 hour, 1, 10, 1),
       ('Kielecki off-road', 12,
        'https://maps.openrouteservice.org/#/directions/Narciarska,Kielce,SK,Polska/Aleja%20Ksi%C4%99dza%20Jerzego%20Popie%C5%82uszki,Kielce,SK,Polska/Aleja%20Ksi%C4%99dza%20Jerzego%20Popie%C5%82uszki,Kielce,SK,Polska/Dymi%C5%84ska,Kielce,SK,Polska/Klecka%2019,Kielce,SK,Polska/Klecka%2044,Kielce,SK,Polska/Przylesie%205,Morawica%20Obszar%20Wiejski,SK,Polska/Pistacjowa%2020,Morawica%20Obszar%20Wiejski,SK,Polska/stara%20studnia%20(nieczynna),Morawica%20Obszar%20Wiejski,SK,Polska/droga%20po%C5%BCarowa%20nr%203,Morawica%20Obszar%20Wiejski,SK,Polska/data/55,130,32,198,15,97,4,224,38,9,96,59,2,24,5,192,166,6,113,0,184,64,38,0,24,3,160,13,128,22,1,25,207,214,129,217,43,191,0,56,5,103,192,26,86,78,102,162,237,41,80,128,78,110,229,88,6,226,38,64,51,33,66,229,133,212,42,219,171,25,117,89,113,227,57,157,97,249,135,52,42,88,76,221,82,74,145,153,72,175,74,75,133,28,173,184,179,25,167,106,142,111,131,76,252,228,150,100,226,26,10,238,50,70,228,228,174,238,226,6,66,132,202,164,38,65,20,162,2,148,122,148,230,250,50,49,54,182,248,202,153,134,1,169,81,194,84,254,78,172,201,170,49,248,172,217,14,201,204,66,148,172,129,210,20,228,186,50,189,97,34,164,13,134,194,132,44,2,76,170,169,236,254,69,166,148,164,98,121,220,110,248,217,34,84,235,132,212,116,169,20,69,228,158,234,157,10,174,250,236,154,94,132,188,180,164,229,74,228,116,188,108,66,116,71,49,59,42,44,29,204,194,75,204,16,7,4,1,0,0,58,161,224,16,68,54,7,10,0,1,121,64,0,182,184,108,176,52,29,0,128,0,205,224,0,27,116,46,28,0,4,243,0,226,144,0,115,0,45,52,29,6,72,2,184,227,144,208,32,72,29,30,132,199,161,169,136,48,62,47,13,76,130,35,17,232,68,44,29,11,1,0,1,125,197,64,0,0',
        'Wymagająca trasa off-road dla twardzieli', 8, 7, now() - interval 3 hour, 2, 14, 2),
       ('Kielecki mix', 68,
        'https://maps.openrouteservice.org/#/directions/Narciarska,Kielce,SK,Polska/764,Daleszyce%20Obszar%20Wiejski,SK,Polska/Zap%C5%82otnia%2018C,%C5%81ag%C3%B3w,SK,Polska/J%C3%B3zefa%20Szermentowskiego,Bodzentyn,SK,Polska/data/55,130,32,198,15,97,4,224,38,9,96,59,2,24,5,192,166,6,113,0,184,64,38,0,24,3,160,13,128,22,1,25,207,32,78,67,104,25,128,14,75,157,52,230,1,160,21,132,231,203,227,98,214,173,74,133,42,215,32,27,136,177,90,248,139,55,111,145,161,114,157,185,246,32,29,144,78,158,61,104,232,60,209,142,218,179,43,20,34,127,14,194,124,118,183,199,220,175,18,38,239,142,108,114,77,89,18,90,30,124,10,114,70,114,86,26,30,70,30,15,121,115,14,74,61,114,29,74,70,106,16,46,16,8,0,7,84,120,8,68,108,28,80,0,47,40,0,91,92,116,236,220,232,8,0,51,120,0,27,116,92,16,88,104,120,0,55,36,0,115,0,90,48,100,104,44,144,122,244,70,244,104,116,68,48,118,188,89,200,74,202,185,216,116,88,16,0,95,109,160,0,0',
        'Malownicza trasa w okolicach Kielc łącząca drogi z szutrami', 9, 8, now() - interval 4 hour, 2, 14, 3)
;

INSERT INTO users_routes (user_id, route_id)
VALUES (1, 4),
       (1, 5)
;

INSERT INTO trips (name, description, created, user_id)
VALUES ('Baba Jaga Tour', 'Wycieczka po krainie scyzoryków', now(), 1)
;

INSERT INTO routes (name, distance, map, description, popularity, likes, created, author_id, region_id, type_id)
VALUES ('Test_1', 76,
        'https://maps.openrouteservice.org/#/directions/Path%20of%20Monk,Ch%C4%99ciny,SK,Polska/Ruiny%20zamku%20%22Krzy%C5%BCtop%C3%B3r,Iwaniska,SK,Polska/data/55,130,32,198,15,97,4,224,38,9,96,59,2,24,5,192,166,6,113,0,184,64,38,0,24,3,160,5,128,54,1,25,12,42,243,72,21,129,194,7,96,3,129,128,105,158,37,129,56,250,151,192,192,51,31,54,125,40,119,32,192,55,62,74,196,71,81,22,210,183,18,45,42,149,98,19,136,8,0,29,83,192,136,155,14,80,0,188,160,5,181,205,95,97,232,16,1,155,192,3,110,151,8,88,209,224,1,185,32,3,152,2,209,131,35,64,128,2,251,69,0,0',
        'Test', 0, 0, now(), 3, 14, 1),
       ('Test_2', 76,
        'https://maps.openrouteservice.org/#/directions/Path%20of%20Monk,Ch%C4%99ciny,SK,Polska/Ruiny%20zamku%20%22Krzy%C5%BCtop%C3%B3r,Iwaniska,SK,Polska/data/55,130,32,198,15,97,4,224,38,9,96,59,2,24,5,192,166,6,113,0,184,64,38,0,24,3,160,5,128,54,1,25,12,42,243,72,21,129,194,7,96,3,129,128,105,158,37,129,56,250,151,192,192,51,31,54,125,40,119,32,192,55,62,74,196,71,81,22,210,183,18,45,42,149,98,19,136,8,0,29,83,192,136,155,14,80,0,188,160,5,181,205,95,97,232,16,1,155,192,3,110,151,8,88,209,224,1,185,32,3,152,2,209,131,35,64,128,2,251,69,0,0',
        'Test', 0, 0, now() - interval 1 hour, 3, 14, 1),
       ('Test_3', 76,
        'https://maps.openrouteservice.org/#/directions/Path%20of%20Monk,Ch%C4%99ciny,SK,Polska/Ruiny%20zamku%20%22Krzy%C5%BCtop%C3%B3r,Iwaniska,SK,Polska/data/55,130,32,198,15,97,4,224,38,9,96,59,2,24,5,192,166,6,113,0,184,64,38,0,24,3,160,5,128,54,1,25,12,42,243,72,21,129,194,7,96,3,129,128,105,158,37,129,56,250,151,192,192,51,31,54,125,40,119,32,192,55,62,74,196,71,81,22,210,183,18,45,42,149,98,19,136,8,0,29,83,192,136,155,14,80,0,188,160,5,181,205,95,97,232,16,1,155,192,3,110,151,8,88,209,224,1,185,32,3,152,2,209,131,35,64,128,2,251,69,0,0',
        'Test', 0, 0, now() - interval 2 hour, 3, 14, 1),
       ('Test_4', 76,
        'https://maps.openrouteservice.org/#/directions/Path%20of%20Monk,Ch%C4%99ciny,SK,Polska/Ruiny%20zamku%20%22Krzy%C5%BCtop%C3%B3r,Iwaniska,SK,Polska/data/55,130,32,198,15,97,4,224,38,9,96,59,2,24,5,192,166,6,113,0,184,64,38,0,24,3,160,5,128,54,1,25,12,42,243,72,21,129,194,7,96,3,129,128,105,158,37,129,56,250,151,192,192,51,31,54,125,40,119,32,192,55,62,74,196,71,81,22,210,183,18,45,42,149,98,19,136,8,0,29,83,192,136,155,14,80,0,188,160,5,181,205,95,97,232,16,1,155,192,3,110,151,8,88,209,224,1,185,32,3,152,2,209,131,35,64,128,2,251,69,0,0',
        'Test', 0, 0, now() - interval 3 hour, 3, 14, 1),
       ('Test_5', 76,
        'https://maps.openrouteservice.org/#/directions/Path%20of%20Monk,Ch%C4%99ciny,SK,Polska/Ruiny%20zamku%20%22Krzy%C5%BCtop%C3%B3r,Iwaniska,SK,Polska/data/55,130,32,198,15,97,4,224,38,9,96,59,2,24,5,192,166,6,113,0,184,64,38,0,24,3,160,5,128,54,1,25,12,42,243,72,21,129,194,7,96,3,129,128,105,158,37,129,56,250,151,192,192,51,31,54,125,40,119,32,192,55,62,74,196,71,81,22,210,183,18,45,42,149,98,19,136,8,0,29,83,192,136,155,14,80,0,188,160,5,181,205,95,97,232,16,1,155,192,3,110,151,8,88,209,224,1,185,32,3,152,2,209,131,35,64,128,2,251,69,0,0',
        'Test', 0, 0, now() - interval 4 hour, 3, 14, 1)
;

INSERT INTO trips (name, description, created, user_id)
VALUES ('Trip_1', 'Opis wycieczki nr 1', now(), 3),
       ('Trip_2', 'Opis wycieczki nr 2', now() - interval 1 hour, 3),
       ('Trip_3', 'Opis wycieczki nr 3', now() - interval 2 hour, 3),
       ('Trip_4', 'Opis wycieczki nr 4', now() - interval 3 hour, 3),
       ('Trip_5', 'Opis wycieczki nr 5', now() - interval 4 hour, 3)
;

INSERT INTO users (username, email, password, enabled)
VALUES ('del1', 'del1@mr.pl', '$2a$10$vATBTwreQmUc4NmmQCjr8.q3hyIYNx582a5ukVflpx0tOdoL7EvUq', 1),
       ('del2', 'del2@mr.pl', '$2a$10$vATBTwreQmUc4NmmQCjr8.q3hyIYNx582a5ukVflpx0tOdoL7EvUq', 1),
       ('del3', 'del3@mr.pl', '$2a$10$vATBTwreQmUc4NmmQCjr8.q3hyIYNx582a5ukVflpx0tOdoL7EvUq', 1),
       ('del4', 'del4@mr.pl', '$2a$10$vATBTwreQmUc4NmmQCjr8.q3hyIYNx582a5ukVflpx0tOdoL7EvUq', 1),
       ('del5', 'del5@mr.pl', '$2a$10$vATBTwreQmUc4NmmQCjr8.q3hyIYNx582a5ukVflpx0tOdoL7EvUq', 1)
;

INSERT INTO users_roles (user_id, role_id)
VALUES (4, 1),
       (5, 1),
       (6, 1),
       (7, 1),
       (8, 1)
;
