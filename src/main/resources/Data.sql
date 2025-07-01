SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS ingreso_medicamento;
DROP TABLE IF EXISTS medicamento;
DROP TABLE IF EXISTS proveedor;
DROP TABLE IF EXISTS historiaclinica;
DROP TABLE IF EXISTS citamedica;
DROP TABLE IF EXISTS horario;
DROP TABLE IF EXISTS usuario;
DROP TABLE IF EXISTS medico;
DROP TABLE IF EXISTS rol;
DROP TABLE IF EXISTS especialidad;
DROP TABLE IF EXISTS paciente;

SET FOREIGN_KEY_CHECKS = 1;


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
                                                                                  ('Ana', 'Martínez', '12345678', 30, 'Femenino', '987654321', 'Av. Lima 123'),
                                                                                  ('Pedro', 'Silva', '87654321', 45, 'Masculino', '987654322', 'Jr. Cusco 456'),
                                                                                  ('Carmen', 'Torres', '11223344', 25, 'Femenino', '987654323', 'Av. Arequipa 789'),
                                                                                  ('Rudeus', 'Greyrat', '23945097', 20, 'Masculino', '985654323', 'Av. Mushoku 123'),
                                                                                  ('Eren', 'Jeager', '24945096', 22, 'Masculino', '985684323', 'Av. Paradise 183'),
                                                                                  ('Yuya', 'Tenjo', '24045096', 20, 'Masculino', '985694323', 'Av. Skill 153'),
                                                                                  ('Subaru', 'Natsuki', '23945096', 20, 'Masculino', '985654323', 'Av. ReZero 623'),
                                                                                  ('Kaguya', 'Shinomiya', '45945096', 17, 'Femenino', '975654323', 'Av. Sama 1235');


-- Tabla ESPECIALIDAD
CREATE TABLE especialidad (
                              idEspecialidad INT PRIMARY KEY AUTO_INCREMENT,
                              nombre VARCHAR(50) NOT NULL
);

INSERT INTO especialidad (nombre) VALUES
                                      ('Cardiología'),
                                      ('Pediatría'),
                                      ('Dermatología'),
                                      ('Ginecología'),
                                      ('Traumatología'),
                                      ('Radiología'),
                                      ('Odontología'),
                                      ('Oftalmología');


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
                                                                            ('Dr. Juan', 'Pérez', 1, '987654321', true),
                                                                            ('Dra. María', 'González', 2, '987654322', true),
                                                                            ('Dr. Carlos', 'Rodríguez', 3, '987654323', true),
                                                                            ('Dr. Kyotaro', 'Ichikawa', 4, '980654323', true),
                                                                            ('Dr. Ichigo', 'Kurosaki', 5, '989654327', true),
                                                                            ('Dr. Armin', 'Arlet', 6, '990654323', true);


-- Tabla USUARIO (se coloca después de que existe medico y rol)
CREATE TABLE usuario (
                         idUsuario INT PRIMARY KEY AUTO_INCREMENT,
                         idRol INT NOT NULL,
                         idMedico INT,
                         usuario VARCHAR(50) NOT NULL UNIQUE,
                         contraseña VARCHAR(100) NOT NULL,
                         estado BOOLEAN DEFAULT TRUE,
                         FOREIGN KEY (idRol) REFERENCES rol(idRol),
                         FOREIGN KEY (idMedico) REFERENCES medico(idMedico)
);

INSERT INTO usuario (idRol, idMedico, usuario, contraseña, estado) VALUES
                                                                       (1, NULL, 'admin01', 'admin123', TRUE),
                                                                       (2, NULL, 'recepcion1', 'recep123', TRUE),
                                                                       (3, 1, 'drjuan', 'medico123', TRUE),
                                                                       (4, NULL, 'farmacia01', 'farma123', TRUE);


-- Tabla HORARIO
CREATE TABLE horario (
                         idHorario INT PRIMARY KEY AUTO_INCREMENT,
                         idMedico INT NOT NULL,
                         hora TIME NOT NULL,
                         estado BOOLEAN DEFAULT TRUE,
                         FOREIGN KEY (idMedico) REFERENCES medico(idMedico)
);

INSERT INTO horario (idMedico, hora, estado) VALUES
                                                 (1, '08:00:00', true),
                                                 (1, '08:30:00', true),
                                                 (1, '09:00:00', true),
                                                 (1, '09:30:00', true),
                                                 (1, '10:00:00', true),
                                                 (1, '10:30:00', true),
                                                 (1, '11:00:00', true),
                                                 (1, '11:30:00', true),
                                                 (1, '14:00:00', true),
                                                 (1, '14:30:00', true),
                                                 (1, '15:00:00', true),
                                                 (1, '15:30:00', true),
                                                 (1, '16:00:00', true),
                                                 (1, '16:30:00', true),
                                                 (2, '07:00:00', true),
                                                 (2, '07:30:00', true),
                                                 (2, '08:00:00', true),
                                                 (2, '08:30:00', true),
                                                 (2, '09:00:00', true),
                                                 (2, '09:30:00', true),
                                                 (2, '10:00:00', true),
                                                 (2, '13:00:00', true),
                                                 (2, '13:30:00', true),
                                                 (2, '14:00:00', true),
                                                 (2, '14:30:00', true),
                                                 (2, '15:00:00', true),
                                                 (3, '09:00:00', true),
                                                 (3, '09:30:00', true),
                                                 (3, '10:00:00', true),
                                                 (3, '10:30:00', true),
                                                 (3, '11:00:00', true),
                                                 (3, '11:30:00', true),
                                                 (3, '15:00:00', true),
                                                 (3, '15:30:00', true),
                                                 (3, '16:00:00', true),
                                                 (3, '16:30:00', true),
                                                 (3, '17:00:00', true),
                                                 (4, '08:00:00', true),
                                                 (4, '08:30:00', true),
                                                 (4, '09:00:00', true),
                                                 (4, '09:30:00', true),
                                                 (4, '10:00:00', true),
                                                 (4, '10:30:00', true),
                                                 (4, '11:00:00', true),
                                                 (4, '11:30:00', true),
                                                 (4, '14:00:00', true),
                                                 (4, '14:30:00', true),
                                                 (4, '15:00:00', true),
                                                 (4, '15:30:00', true),
                                                 (4, '16:00:00', true),
                                                 (4, '16:30:00', true),
                                                 (5, '07:00:00', true),
                                                 (5, '07:30:00', true),
                                                 (5, '08:00:00', true),
                                                 (5, '08:30:00', true),
                                                 (5, '09:00:00', true),
                                                 (5, '09:30:00', true),
                                                 (5, '10:00:00', true),
                                                 (5, '13:00:00', true),
                                                 (5, '13:30:00', true),
                                                 (5, '14:00:00', true),
                                                 (5, '14:30:00', true),
                                                 (5, '15:00:00', true),
                                                 (6, '09:00:00', true),
                                                 (6, '09:30:00', true),
                                                 (6, '10:00:00', true),
                                                 (6, '10:30:00', true),
                                                 (6, '11:00:00', true),
                                                 (6, '11:30:00', true),
                                                 (6, '15:00:00', true),
                                                 (6, '15:30:00', true),
                                                 (6, '16:00:00', true),
                                                 (6, '16:30:00', true),
                                                 (6, '17:00:00', true);

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

INSERT INTO citamedica (idPaciente, idMedico, fecha, hora, estado) VALUES
                                                                       (1, 1, '2025-06-25', '09:00:00', 'Confirmado'),
                                                                       (2, 1, '2025-06-25', '10:00:00', 'Pendiente'),
                                                                       (2, 2, '2025-06-25', '15:00:00', 'Confirmado'),
                                                                       (3, 1, '2025-06-29', '09:00:00', 'Confirmado'),
                                                                       (4, 1, '2025-06-28', '10:00:00', 'Pendiente'),
                                                                       (5, 2, '2025-06-27', '15:00:00', 'Confirmado');


-- Tabla HISTORIA CLINICA
CREATE TABLE historiaclinica (
                                 idHistoria INT PRIMARY KEY AUTO_INCREMENT,
                                 idPaciente INT NOT NULL,
                                 descripcion TEXT,
                                 fechaRegistro DATE,
                                 FOREIGN KEY (idPaciente) REFERENCES paciente(idPaciente)
);

INSERT INTO historiaclinica (idPaciente, descripcion, fechaRegistro) VALUES
                                                          (1, 'Paciente con síntomas de gripe. Se recetó Paracetamol.', '2025-06-20'),
                                                          (2, 'Consulta por dolor abdominal. Se indicó ecografía.','2025-06-25'),
                                                          (3, 'Revisión de resultados de laboratorio. Todo en rangos normales.', '2025-06-12'),
                                                          (4, 'Tratamiento por bronquitis. Se inició antibióticos.', '2025-06-13'),
                                                          (5, 'Chequeo preoperatorio realizado.', '2025-06-14'),
                                                          (6, 'Consulta por insomnio. Se sugiere higiene del sueño.', '2025-06-15'),
                                                          (1, 'Vacunación anual contra la influenza.', '2025-06-16'),
                                                          (2, 'Dolor en rodilla derecha. Se indica fisioterapia.', '2025-06-17'),
                                                          (3, 'Examen físico completo. Presión arterial elevada.', '2025-06-18'),
                                                          (4, 'Síntomas de alergia estacional. Se receta antihistamínico.', '2025-06-19'),
                                                          (5, 'Consulta por ansiedad. Se refiere a psicología.', '2025-06-20'),
                                                          (6, 'Evaluación nutricional. Se recomienda dieta hipocalórica.', '2025-06-21'),
                                                          (1, 'Paciente con fiebre persistente. Se ordena hemograma.', '2025-06-22'),
                                                          (2, 'Dolor lumbar. Se sugiere radiografía.', '2025-06-23'),
                                                          (3, 'Consulta odontológica. Requiere extracción dental.', '2025-06-24'),
                                                          (4, 'Paciente refiere mareo. Se indicó control de glucosa.', '2025-06-25'),
                                                          (5, 'Resultado positivo para infección urinaria. Se inicia tratamiento.', '2025-06-26'),
                                                          (6, 'Paciente en seguimiento post-operatorio. Evolución favorable.', '2025-06-27'),
                                                          (1, 'Consulta por dermatitis. Se prescribe crema tópica.', '2025-06-28'),
                                                          (2, 'Revisión de presión arterial. Sin cambios terapéuticos.', '2025-06-29');



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
                                                               ('Distribuidora MedLife', '20456789123', '987654321', 'contacto@medlife.pe'),
                                                               ('FarmaCorp Perú SAC', '20567891234', '951234567', 'ventas@farmacorp.com'),
                                                               ('Droguería Santa Rosa EIRL', '20678912345', '952345678', 'info@santarosa.pe'),
                                                               ('Salud y Vida Distribuciones SAC', '20789123456', '953456789', 'contacto@saludyvida.com'),
                                                               ('Laboratorios Andinos S.A.', '20891234567', '954567890', 'atencion@andinoslab.pe'),
                                                               ('Biomedic Perú SAC', '20912345678', '955678901', 'ventas@biomedic.pe'),
                                                               ('Farmacéutica del Norte S.A.C.', '20923456789', '956789012', 'contacto@farnorte.pe'),
                                                               ('Laboratorios VitaSalud EIRL', '20934567890', '957890123', 'info@vitasalud.pe'),
                                                               ('Distribuciones Médicas San Marcos', '20945678901', '958901234', 'ventas@sanmarcosmed.pe'),
                                                               ('Droguería Horizonte SAC', '20956789012', '959012345', 'atencion@horizontesac.com'),
                                                               ('Importadora PharmaGlobal S.A.C.', '20967890123', '960123456', 'contacto@pharmaglobal.pe'),
                                                               ('Laboratorios NovaPharm Perú', '20978901234', '961234567', 'info@novapharm.pe'),
                                                               ('Corporación Médica Integra SAC', '20989012345', '962345678', 'ventas@integramed.pe'),
                                                               ('Red de Suministros Hospitalarios', '20990123456', '963456789', 'contacto@suministroshosp.pe'),
                                                               ('Droguería y Farmacia Universo', '21001234567', '964567890', 'info@farmaciauniverso.pe'),
                                                               ('BioHealth Proveedores Médicos SAC', '21012345678', '965678901', 'atencion@biohealth.pe');



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
                                                                                                                         (2, 'Amoxicilina', 'Cápsula', '250mg', 'mg', 80, 15, TRUE),
                                                                                                                         (3, 'Ibuprofeno', 'Tableta', '400mg', 'mg', 120, 30, TRUE),
                                                                                                                         (4, 'Omeprazol', 'Cápsula', '20mg', 'mg', 90, 20, TRUE),
                                                                                                                         (5, 'Azitromicina', 'Tableta', '500mg', 'mg', 75, 15, TRUE),
                                                                                                                         (6, 'Loratadina', 'Tableta', '10mg', 'mg', 60, 10, TRUE),
                                                                                                                         (7, 'Metformina', 'Tableta', '850mg', 'mg', 200, 40, TRUE),
                                                                                                                         (8, 'Furosemida', 'Tableta', '40mg', 'mg', 150, 30, TRUE),
                                                                                                                         (9, 'Cetirizina', 'Solución', '5mg/5ml', 'ml', 100, 25, TRUE),
                                                                                                                         (10, 'Diclofenaco', 'Inyectable', '75mg/3ml', 'ml', 80, 20, TRUE),
                                                                                                                         (11, 'Salbutamol', 'Inhalador', '100mcg', 'mcg', 50, 10, TRUE),
                                                                                                                         (12, 'Clorfenamina', 'Jarabe', '2mg/5ml', 'ml', 120, 20, TRUE),
                                                                                                                         (13, 'Naproxeno', 'Tableta', '500mg', 'mg', 130, 25, TRUE),
                                                                                                                         (14, 'Prednisona', 'Tableta', '20mg', 'mg', 90, 15, TRUE),
                                                                                                                         (15, 'Dexametasona', 'Inyectable', '8mg/2ml', 'ml', 70, 10, TRUE),
                                                                                                                         (16, 'Hidroxicloroquina', 'Tableta', '200mg', 'mg', 45, 10, TRUE),
                                                                                                                         (17, 'Ranitidina', 'Tableta', '150mg', 'mg', 55, 15, TRUE),
                                                                                                                         (1, 'Clonazepam', 'Tableta', '0.5mg', 'mg', 60, 10, TRUE),
                                                                                                                         (2, 'Enalapril', 'Tableta', '10mg', 'mg', 100, 20, TRUE),
                                                                                                                         (3, 'Losartán', 'Tableta', '50mg', 'mg', 90, 15, TRUE),
                                                                                                                         (4, 'Mebendazol', 'Suspensión', '100mg/5ml', 'ml', 70, 10, TRUE),
                                                                                                                         (5, 'Albendazol', 'Tableta masticable', '400mg', 'mg', 80, 20, TRUE);



-- Tabla INGRESO MEDICAMENTO
CREATE TABLE ingreso_medicamento (
                                     idIngreso INT PRIMARY KEY AUTO_INCREMENT,
                                     idProveedor INT NOT NULL,
                                     idMedicamento INT NOT NULL,
                                     cantidad INT NOT NULL,
                                     fechaIngreso DATETIME NOT NULL,
                                     observaciones TEXT,
                                     FOREIGN KEY (idProveedor) REFERENCES proveedor(idProveedor),
                                     FOREIGN KEY (idMedicamento) REFERENCES medicamento(idMedicamento)
);

INSERT INTO ingreso_medicamento (idProveedor, idMedicamento, cantidad, fechaIngreso, observaciones) VALUES
                                                                                                        (1, 1, 50, '2025-06-01 09:00:00', 'Primer lote del mes'),
                                                                                                        (2, 2, 80, '2025-06-02 11:30:00', 'Reposición de inventario');
