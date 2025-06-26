


DROP TABLE IF EXISTS paciente;
DROP TABLE IF EXISTS especialidad;
DROP TABLE IF EXISTS rol;
DROP TABLE IF EXISTS usuario;
DROP TABLE IF EXISTS medico;
DROP TABLE IF EXISTS horario;
DROP TABLE IF EXISTS citaMedica;
DROP TABLE IF EXISTS historiaClinica;
DROP TABLE IF EXISTS proveedor;
DROP TABLE IF EXISTS medicamento;
DROP TABLE IF EXISTS ingreso_medicamento;




-- Tabla PACIENTE
CREATE TABLE paciente (
                          idPaciente INT PRIMARY KEY AUTO_INCREMENT,
                          nombre VARCHAR(100) NOT NULL,
                          apellido VARCHAR(100) NOT NULL,
                          dni VARCHAR(10) NOT NULL UNIQUE,
                          edad INT,
                          sexo VARCHAR(10),
                          telefono VARCHAR(15),
                          direccion VARCHAR(150)
);

INSERT INTO paciente (nombre, apellido, dni, edad, sexo, telefono, direccion) VALUES
                                                                                  ('Carlos', 'Ramírez', '12345678', 30, 'Masculino', '999111222', 'Av. Lima 123'),
                                                                                  ('María', 'Torres', '87654321', 25, 'Femenino', '999333444', 'Calle Sol 456');



-- Tabla ESPECIALIDAD
CREATE TABLE especialidad (
                              idEspecialidad INT PRIMARY KEY AUTO_INCREMENT,
                              nombre VARCHAR(50) NOT NULL
);

INSERT INTO especialidad (nombre) VALUES
                                      ('Pediatría'),
                                      ('Medicina General'),
                                      ('Ginecología'),
                                      ('Cardiología');


-- Tabla ROL
CREATE TABLE rol (
                     idRol INT PRIMARY KEY AUTO_INCREMENT,
                     descripcion VARCHAR(50) NOT NULL
);

INSERT INTO rol (descripcion) VALUES
                                  ('ADMIN'),
                                  ('RECEPCION'),
                                  ('MEDICO'),
                                  ('FARMACIA');




CREATE TABLE usuario (
                         idUsuario INT PRIMARY KEY AUTO_INCREMENT,
                         idRol INT NOT NULL,
                         idMedico INT, -- NULL si no es médico
                         usuario VARCHAR(50) NOT NULL UNIQUE,
                         contraseña VARCHAR(100) NOT NULL,
                         estado BOOLEAN DEFAULT TRUE,
                         FOREIGN KEY (idRol) REFERENCES rol(idRol),
                         FOREIGN KEY (idMedico) REFERENCES medico(idMedico)
);


INSERT INTO usuario (idRol, idMedico, usuario, contraseña, estado) VALUES
                                                                       (1, NULL, 'admin01', 'admin123', TRUE),           -- Administrador
                                                                       (2, NULL, 'recepcion1', 'recep123', TRUE),        -- Recepcionista
                                                                       (3, 1, 'drjuan', 'medico123', TRUE),              -- Médico Juan Pérez
                                                                       (4, NULL, 'farmacia01', 'farma123', TRUE);        -- Usuario de Farmacia



-- Tabla MEDICO
CREATE TABLE medico (
                        idMedico INT PRIMARY KEY AUTO_INCREMENT,
                        nombre VARCHAR(100) NOT NULL,
                        apellido VARCHAR(100) NOT NULL,
                        idEspecialidad INT NOT NULL,
                        telefono VARCHAR(15),
                        estado BOOLEAN DEFAULT TRUE,
                        FOREIGN KEY (idEspecialidad) REFERENCES especialidad(idEspecialidad)
);

INSERT INTO medico (nombre, apellido, idEspecialidad, telefono, estado) VALUES
                                                                            ('Juan', 'Pérez', 1, '987654321', TRUE),
                                                                            ('Lucía', 'Gómez', 2, '912345678', TRUE);



-- Tabla HORARIO (disponibilidad del médico)
CREATE TABLE horario (
                         idHorario INT PRIMARY KEY AUTO_INCREMENT,
                         idMedico INT NOT NULL,
                         fecha DATE NOT NULL,
                         hora TIME NOT NULL,
                         estado BOOLEAN DEFAULT TRUE,
                         FOREIGN KEY (idMedico) REFERENCES medico(idMedico)
);

-- Horarios para el médico Juan Pérez
INSERT INTO horario (idMedico, fecha, hora, estado) VALUES
                                                        (1, '2025-06-25', '09:00:00', TRUE),
                                                        (1, '2025-06-25', '10:00:00', TRUE),
                                                        (1, '2025-06-25', '11:00:00', TRUE);

-- Horarios para la médica Lucía Gómez
INSERT INTO horario (idMedico, fecha, hora, estado) VALUES
                                                        (2, '2025-06-25', '14:00:00', TRUE),
                                                        (2, '2025-06-25', '15:00:00', TRUE),
                                                        (2, '2025-06-25', '16:00:00', TRUE);


-- Tabla CITA MEDICA
CREATE TABLE citamedica (
                            idCita INT PRIMARY KEY AUTO_INCREMENT,
                            idPaciente INT NOT NULL,
                            idMedico INT NOT NULL,
                            fecha DATE NOT NULL,
                            hora TIME NOT NULL,
                            estado VARCHAR(20) DEFAULT 'Pendiente',
                            FOREIGN KEY (idPaciente) REFERENCES paciente(idPaciente),
                            FOREIGN KEY (idMedico) REFERENCES medico(idMedico)
);

-- Carlos Ramírez agenda cita con Dr. Juan Pérez
INSERT INTO citamedica (idPaciente, idMedico, fecha, hora, estado) VALUES
    (1, 1, '2025-06-25', '09:00:00', 'Confirmado');

-- María Torres agenda cita con Dr. Juan Pérez
INSERT INTO citamedica (idPaciente, idMedico, fecha, hora, estado) VALUES
    (2, 1, '2025-06-25', '10:00:00', 'Pendiente');

-- María Torres agenda cita con Dra. Lucía Gómez
INSERT INTO citamedica (idPaciente, idMedico, fecha, hora, estado) VALUES
    (2, 2, '2025-06-25', '15:00:00', 'Confirmado');


-- Tabla HISTORIA CLINICA
CREATE TABLE historiaclinica (
                                 idHistoria INT PRIMARY KEY AUTO_INCREMENT,
                                 idPaciente INT NOT NULL,
                                 descripcion TEXT,
                                 FOREIGN KEY (idPaciente) REFERENCES paciente(idPaciente)
);

INSERT INTO historiaclinica (idPaciente, idMedico, descripcion, fechaRegistro) VALUES
                                                                                   (1, 1, 'Paciente con síntomas de gripe. Se recetó Paracetamol.'),
                                                                                   (2, 2, 'Consulta por dolor abdominal. Se indicó ecografía.');


-- Tabla PROVEEDOR
CREATE TABLE proveedor (
                           idProveedor INT PRIMARY KEY AUTO_INCREMENT,
                           razonSocial VARCHAR(100) NOT NULL,
                           ruc VARCHAR(11) NOT NULL UNIQUE,
                           telefono VARCHAR(15),
                           correo VARCHAR(100)
);


INSERT INTO proveedor (razonSocial, ruc, telefono, correo) VALUES
                                                               ('Laboratorios Genfar', '20123456789', '954123456', 'ventas@genfar.com'),
                                                               ('Distribuidora MedLife', '20456789123', '987654321', 'contacto@medlife.pe');


-- Tabla MEDICAMENTO
CREATE TABLE medicamento (
                             idMedicamento INT PRIMARY KEY AUTO_INCREMENT,
                             idProveedor INT NOT NULL,
                             nombre VARCHAR(100) NOT NULL,
                             presentacion VARCHAR(50),
                             concentracion VARCHAR(50),
                             unidad VARCHAR(20),
                             stockActual INT DEFAULT 0,
                             stockMinimo INT DEFAULT 10,
                             estado BOOLEAN DEFAULT TRUE,
                             FOREIGN KEY (idProveedor) REFERENCES proveedor(idProveedor)
);

INSERT INTO medicamento (idProveedor, nombre, presentacion, concentracion, unidad, stockActual, stockMinimo, estado) VALUES
                                                                                                                         (1, 'Paracetamol', 'Tableta', '500mg', 'mg', 100, 20, TRUE),
                                                                                                                         (2, 'Amoxicilina', 'Cápsula', '250mg', 'mg', 80, 15, TRUE);


create table ingreso_medicamento (
                                     idIngreso INT PRIMARY KEY AUTO_INCREMENT,
                                     idProveedor INT NOT NULL,
                                     idMedicamento INT NOT NULL,
                                     cantidad INT NOT NULL,
                                     fechaIngreso DATETIME NOT NULL,
                                     observaciones TEXT,
                                     FOREIGN KEY (idProveedor) REFERENCES proveedor(idProveedor),
                                     FOREIGN KEY (idMedicamento) REFERENCES medicamento(idMedicamento)
)

    INSERT INTO ingreso_medicamento (idProveedor, idMedicamento, cantidad, fechaIngreso, observaciones) VALUES
(1, 1, 50, '2025-06-01 09:00:00', 'Primer lote del mes'),
(2, 2, 80, '2025-06-02 11:30:00', 'Reposición de inventario');
