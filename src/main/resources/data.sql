INSERT INTO stadium (std_capacity,std_id,std_location,std_name) VALUES (36000,1,'Dhaka','Bangabandhu National Stadium');
INSERT INTO stadium (std_capacity,std_id,std_location,std_name) VALUES (30000,2,'Chittagong','M. A. Aziz Stadium');
INSERT INTO stadium (std_capacity,std_id,std_location,std_name) VALUES (18000,3,'Bogra','Shaheed Chandu Stadium');
INSERT INTO event_organizer (organizer_id, address, department, designation,email,mobile_no,organization_name,password) VALUES (1, 'Dhaka', 'Marketing', 'Chief Officer', 'rifat@gmail.com','01234','SpaceX','abcde');
INSERT INTO program (event_organizer_id, prog_id, stadium_id, date, duration,event_title,image) VALUES (1, 1,1, '2023-11-25', '1 day', 'England vs Sri Lanka','engvssri.jpg');
INSERT INTO program (event_organizer_id, prog_id, stadium_id, date, duration,event_title,image) VALUES (1, 2,2, '2023-10-18', '6 hour', 'ArboVirus Concert','concert.avif');
INSERT INTO program (event_organizer_id, prog_id, stadium_id, date, duration,event_title,image) VALUES (1, 3,3, '2024-02-08', '1 day', 'India vs Pakistan','da.jpg');
