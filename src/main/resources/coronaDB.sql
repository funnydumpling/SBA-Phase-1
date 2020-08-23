--This file contains DB script for Corona Prevention Kit Order Portal--

DROP database orderPortal;

CREATE DATABASE orderPortal;

USE orderPortal;

CREATE TABLE productMaster(
		id int primary key AUTO_INCREMENT,
		pname varchar(50) not null,
		pdesc varchar(100) not null,
		pcost decimal not null
		
	);

INSERT INTO productMaster(pname,pdesc,pcost) VALUES
("Sanitizer","Contains alcohol which effectively kills bacteria and most virus",300),
("Gloves","Pair of reusable gloves",150),
("N95-Mask","Protective gear for the face. Stay safe from dust,airborne particles and viruses.",200);

CREATE TABLE kitDetail(
	id int primary key AUTO_INCREMENT,
    coronakitId int not null,
	productId int not null,
	productName varchar(20) not null,
    quantity int not null,
	amount decimal not null
);

