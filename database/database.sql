create schema fitness_management_database;
use fitness_management_database;

create table if not exists subscriptions(
	id				int 		 NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT,
    price			int			 NOT NULL,
    description		varchar(256) NOT NULL
);

create table if not exists clients(
	id 				int 		 NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT,
    firstname		varchar(20)  NOT NULL,
    lastname		varchar(20)  NOT NULL,
    email			varchar(25)  NOT NULL,
	has_active_sub	boolean 	 NOT NULL DEFAULT false,
    subscription_id	int			 NOT NULL,
    next_payment_at time,
    
    CONSTRAINT c1 FOREIGN KEY(subscription_id) REFERENCES subscriptions(id)
);

create table if not exists payments(
	id				int			NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT,
    client_id		int			NOT NULL,
    subscription_id	int			NOT NULL,
    payed_at		timestamp	NOT NULL,
    
    CONSTRAINT c2 FOREIGN KEY(subscription_id) REFERENCES subscriptions(id),
    CONSTRAINT c3 FOREIGN KEY(client_id)	   REFERENCES clients(id)
);

create table if not exists roles(
	id				int			NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT,
    title			varchar(20) NOT NULL
);

create table if not exists permissions(
	id				int			 NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT,
    action			varchar(256) NOT NULL    
);

create table if not exists permissions_for_roles(
	id				int			NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT,
    role_id			int			NOT NULL,
    permission_id	int			NOT NULL,
    
    CONSTRAINT c4 FOREIGN KEY(role_id) 		 REFERENCES roles(id),
	CONSTRAINT c5 FOREIGN KEY(permission_id) REFERENCES permissions(id)
);

create table if not exists employee(
	id 				int 		 NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT,
    firstname		varchar(20)  NOT NULL,
    lastname		varchar(20)  NOT NULL,
    email			varchar(25)  NOT NULL,
    pass 			binary(64) 	 NOT NULL,
    wage			int			 NOT NULL,
    rating			float		 NOT NULL DEFAULT 0,
    employed_at		timestamp	 NOT NULL DEFAULT NOW(),
    role_id			int 		 NOT NULL DEFAULT 1,
    
    CONSTRAINT c6 FOREIGN KEY(role_id) REFERENCES roles(id)
);

create table if not exists jurnal_events(
	id 			INT 		 NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT,
    descr		varchar(256) NOT NULL
);

create table if not exists jurnal(
	id			INT			 NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT,
    client_id	INT 		 NOT NULL,
    event_id	INT 		 NOT NULL,
	created_at	TIMESTAMP	 NOT NULL DEFAULT NOW(),
    
    CONSTRAINT c7 FOREIGN KEY(client_id)  REFERENCES clients(id),
    CONSTRAINT c8 FOREIGN KEY(event_id)   REFERENCES jurnal_events(id)
);