CREATE TABLE Employee (
	id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(100) NOT NULL,
	first_name VARCHAR(100) NOT NULL,
	date_of_birth DATE NOT NULL,
	CONSTRAINT uq_employee_identity UNIQUE (name, first_name, date_of_birth)
);

CREATE TABLE Contract (
	id INT AUTO_INCREMENT PRIMARY KEY,
	employee_id INT NOT NULL,
	date_of_signature DATE NOT NULL,
	duration INT NOT NULL,
	salary DECIMAL(10, 2) NOT NULL,
	CONSTRAINT fk_contract_employee FOREIGN KEY (employee_id) REFERENCES Employee(id) ON DELETE CASCADE,
	CONSTRAINT uq_contract_employee_date UNIQUE (employee_id, date_of_signature)
) ;

CREATE TABLE Family (
	id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(100) NOT NULL,
	first_name VARCHAR(100) NOT NULL,
	date_of_birth DATE NOT NULL,
	employee_id INT NOT NULL,
	CONSTRAINT fk_family_employee FOREIGN KEY (employee_id) REFERENCES Employee(id) ON DELETE CASCADE,
	CONSTRAINT uq_family_member UNIQUE (name, first_name, date_of_birth, employee_id)
);

CREATE TABLE Service (
	id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(100) NOT NULL,
	employee_id INT,
	CONSTRAINT fk_service_employee FOREIGN KEY (employee_id) REFERENCES Employee(id) ON DELETE SET NULL, -- pourquoi SET NULL, car si on supprime l'employé on ne veut pas supprimer le service
	CONSTRAINT uq_service_name UNIQUE (name)
);


CREATE TABLE Service_hierarchy (
	id INT AUTO_INCREMENT PRIMARY KEY,
	service_id INT NOT NULL,
	parent_service_id INT NOT NULL,
	CONSTRAINT fk_hierarchy_service FOREIGN KEY (service_id) REFERENCES Service(id) ON DELETE CASCADE,
	CONSTRAINT fk_hierarchy_parent FOREIGN KEY (parent_service_id) REFERENCES Service(id) ON DELETE CASCADE,
	CONSTRAINT uq_hierarchy_relation UNIQUE (service_id, parent_service_id),
	CONSTRAINT check_no_self_parent CHECK (service_id != parent_service_id) -- contrainte en plus
);
