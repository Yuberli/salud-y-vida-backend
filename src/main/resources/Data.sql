


DROP TABLE IF EXISTS Paciente;
DROP TABLE IF EXISTS Medico;
DROP TABLE IF EXISTS CitaMedica;
DROP TABLE IF EXISTS HistoriaClinica;
DROP TABLE IF EXISTS Medicamento;
DROP TABLE IF EXISTS Proveedor;
DROP TABLE IF EXISTS Usuario;


CREATE TABLE Paciente (
                          IdPaciente INT AUTO_INCREMENT PRIMARY KEY,
                          Nombres VARCHAR(100),
                          Apellidos VARCHAR(100),
                          DNI CHAR(8),
                          Edad CHAR(5),
                          Sexo CHAR(1),
                          Telefono VARCHAR(15),
                          Direccion VARCHAR(200)
) ENGINE=InnoDB;

INSERT INTO Paciente (Nombres, Apellidos, DNI, Edad, Sexo, Telefono, Direccion) VALUES
                                                                                    ('Juan Carlos', 'Ramirez Soto', '72639184', '32', 'M', '987654321', 'Av. Brasil 123'),
                                                                                    ('Lucía Elena', 'Flores Lazo', '84327129', '27', 'F', '912345678', 'Jr. Castilla 456'),
                                                                                    ('Pedro Miguel', 'Vargas Ruiz', '75283910', '45', 'M', '922113344', 'Av. La Marina 899'),
                                                                                    ('Ana Sofía', 'Mendoza Cueva', '80123456', '38', 'F', '934567812', 'Calle Roma 234'),
                                                                                    ('Marco Antonio', 'Peña Salas', '78945123', '50', 'M', '900111222', 'Psj. Unión 120'),
                                                                                    ('Andrea Pilar', 'Zegarra Ramos', '81345678', '23', 'F', '944555666', 'Av. Ejército 402'),
                                                                                    ('Carlos Enrique', 'Chávez Limón', '77091234', '60', 'M', '988123456', 'Jr. Ayacucho 150'),
                                                                                    ('Verónica Milagros', 'Quispe Bravo', '76543210', '29', 'F', '955222333', 'Av. Colonial 89');


CREATE TABLE Medico (
                        IdMedico INT AUTO_INCREMENT PRIMARY KEY,
                        Nombres VARCHAR(100),
                        Apellidos VARCHAR(100),
                        Especialidad VARCHAR(100),
                        Estado BOOLEAN
) ENGINE=InnoDB;

INSERT INTO Medico (Nombres, Apellidos, Especialidad, Estado) VALUES
                                                                  ('Daniel', 'Ortega Sosa', 'Cardiología', TRUE),
                                                                  ('Patricia', 'Reyes Torres', 'Pediatría', TRUE),
                                                                  ('Luis', 'Mendoza Paredes', 'Dermatología', TRUE),
                                                                  ('Karina', 'Núñez Huamán', 'Medicina General', TRUE),
                                                                  ('José', 'Ramírez Velásquez', 'Neurología', FALSE),
                                                                  ('Elena', 'Zapata Alarcón', 'Traumatología', TRUE),
                                                                  ('Martín', 'Salinas Arroyo', 'Gastroenterología', TRUE),
                                                                  ('Sandra', 'Gonzales Olivos', 'Ginecología', TRUE);

CREATE TABLE CitaMedica (
                            IdCita INT AUTO_INCREMENT PRIMARY KEY,
                            IdPaciente INT,
                            IdMedico INT,
                            FechaHora DATETIME,
                            Estado VARCHAR(50),
                            FOREIGN KEY (IdPaciente) REFERENCES Paciente(IdPaciente),
                            FOREIGN KEY (IdMedico) REFERENCES Medico(IdMedico)
) ENGINE=InnoDB;

INSERT INTO CitaMedica (IdPaciente, IdMedico, FechaHora, Estado) VALUES
                                                                     (1, 1, '2025-06-17 10:00:00', 'Confirmada'),
                                                                     (2, 2, '2025-06-17 11:30:00', 'Confirmada'),
                                                                     (3, 3, '2025-06-17 12:00:00', 'Cancelada'),
                                                                     (4, 4, '2025-06-18 09:00:00', 'Confirmada'),
                                                                     (5, 1, '2025-06-18 14:00:00', 'Pendiente'),
                                                                     (6, 6, '2025-06-19 08:30:00', 'Confirmada'),
                                                                     (7, 5, '2025-06-19 10:00:00', 'Confirmada'),
                                                                     (8, 2, '2025-06-20 11:00:00', 'Confirmada');



CREATE TABLE HistoriaClinica (
                                 IdHistoria INT AUTO_INCREMENT PRIMARY KEY,
                                 IdPaciente INT,
                                 Alergias VARCHAR(50),
                                 Descripcion VARCHAR(500),
                                 FOREIGN KEY (IdPaciente) REFERENCES Paciente(IdPaciente)
) ENGINE=InnoDB;

INSERT INTO HistoriaClinica (IdPaciente, Alergias, Descripcion) VALUES
                                                                    (1, 'Penicilina', 'Paciente hipertenso, en tratamiento desde hace 5 años.'),
                                                                    (2, 'Ninguna', 'Antecedente de asma leve controlada.'),
                                                                    (3, 'Ibuprofeno', 'Cirugía de vesícula en 2021.'),
                                                                    (4, 'Látex', 'Control ginecológico anual.'),
                                                                    (5, 'Ninguna', 'Paciente diabético tipo 2.'),
                                                                    (6, 'Paracetamol', 'Revisión general previa a cirugía.'),
                                                                    (7, 'Ninguna', 'Consulta por dolores articulares.'),
                                                                    (8, 'Gluten', 'Alergia alimentaria controlada.');


CREATE TABLE Medicamento (
                             IdMedicamento INT AUTO_INCREMENT PRIMARY KEY,
                             Nombre VARCHAR(100),
                             Presentacion VARCHAR(100),
                             Concentracion VARCHAR(50),
                             Unidad VARCHAR(20),
                             StockActual INT,
                             Estado BOOLEAN
) ENGINE=InnoDB;

INSERT INTO Medicamento (Nombre, Presentacion, Concentracion, Unidad, StockActual, Estado) VALUES
                                                                                               ('Paracetamol', 'Tabletas', '500mg', 'mg', 150, TRUE),
                                                                                               ('Ibuprofeno', 'Cápsulas', '400mg', 'mg', 120, TRUE),
                                                                                               ('Amoxicilina', 'Tabletas', '500mg', 'mg', 80, TRUE),
                                                                                               ('Metformina', 'Tabletas', '850mg', 'mg', 100, TRUE),
                                                                                               ('Omeprazol', 'Cápsulas', '20mg', 'mg', 60, TRUE),
                                                                                               ('Salbutamol', 'Inhalador', '100mcg', 'mcg', 40, TRUE),
                                                                                               ('Diclofenaco', 'Ampolla', '75mg', 'mg', 30, TRUE),
                                                                                               ('Loratadina', 'Jarabe', '10mg/5ml', 'ml', 90, TRUE);

CREATE TABLE Proveedor (
                           IdProveedor INT AUTO_INCREMENT PRIMARY KEY,
                           RazonSocial VARCHAR(200),
                           RUC VARCHAR(11),
                           Telefono VARCHAR(20)
) ENGINE=InnoDB;

INSERT INTO Proveedor (RazonSocial, RUC, Telefono) VALUES
                                                       ('Farmacéutica Andina S.A.', '20512345678', '012345678'),
                                                       ('Distribuidora PharmaPlus', '20654321890', '017654321'),
                                                       ('Laboratorios VITA', '20456789321', '013456789'),
                                                       ('Medic Perú SAC', '20987654321', '016789012'),
                                                       ('Biosalud S.A.C.', '20876543210', '012398765'),
                                                       ('Laboratorios Salk', '20765432109', '019876543'),
                                                       ('Salud y Vida EIRL', '20345678901', '018765432'),
                                                       ('NutraFarm EIRL', '20234567890', '017654321');

CREATE TABLE Usuario (
                         IdUsuario INT AUTO_INCREMENT PRIMARY KEY,
                         Usuario VARCHAR(50),
                         Contraseña VARCHAR(100),
                         Rol VARCHAR(50)
) ENGINE=InnoDB;

INSERT INTO Usuario (Usuario, Contraseña, Rol) VALUES
                                                   ('admin', 'admin123', 'Administrador'),
                                                   ('recepcion', 'recep123', 'Recepcionista'),
                                                   ('medico', 'med123', 'Medico');

