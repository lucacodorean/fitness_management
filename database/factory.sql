use fitness_management_database;

INSERT INTO roles(title) VALUES ("Super-Administrator"), ("Administrator"), ("Casier"), ("Antrenor");
INSERT INTO permissions(action) VALUES 
	("create_role"), 		 ("read_role"),         ("update_role"),         ("delete_role"),
    ("create_permission"),   ("read_permission"),   ("update_permission"),   ("delete_permission"),
	("create_employee"), 	 ("read_employee"), 	("update_employee"), 	 ("delete_employee"),
    ("create_client"), 	 	 ("read_client"),   	("update_client"),       ("delete_client"),
    ("create_subscription"), ("read_subcription"),  ("update_subscription"), ("delete_subscription");

INSERT INTO permissions_for_roles(role_id, permission_id) VALUES 
(1,01),(1,02),(1,03),(1,04),(1,05),(1,06),(1,07),(1,08),(1,09),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15),(1,16),(1,17),(1,18),(1,19),(1,20),
(2,09),(2,10),(2,11),(2,12),(2,13),(2,14),(2,15),(2,16),(2,17),(2,18),(2,19),(2,20),
(3,12),(3,13),(3,14),(3,15),(3,16),(3,17),(3,18),(3,19),(3,20),
(4,14),(4,18);

INSERT INTO employee(firstname, lastname, email, pass, wage, role_id) VALUES 
	("SuperAdmin", "Dummy", "super.admin@myfitness.com", "parola", 4500, 1),
	("Admin", 	   "Dummy", "admin@myfitness.com", 		 "parola", 3500, 2),
	("Casier",     "Dummy", "cashier@myfitness.com", 	 "parola", 3000, 3),
	("Antrenor",   "Dummy", "trainer@myfitness.com", 	 "parola", 2500, 4);

INSERT INTO jurnal_events(descr) VALUES
	("Client nou."),  ("Client sters."),
	("Angajat nou."), ("Angajat sters.");

INSERT INTO subscriptions(price, description) VALUES 
	(150, "Abonament basic lunar. Acces general in sala."),
	(175, "Abonament permium lunar. Acces general in sala si la sauna."),
    (240, "Abonament all inclusive lunar. Acces la toate facilitatile salii, plus snack la inceputul antrenamentului."); 

INSERT INTO clients(firstname, lastname, email, subscription_id) VALUES 
	("Client", "Dummy", "client@myfitness.com", 1);
