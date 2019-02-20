CREATE DATABASE PLANILLA
GO

USE PLANILLA
GO

CREATE TABLE EMPLEADOS(
ID_EMPLEADO INT IDENTITY CONSTRAINT PK_EMPLEADOS PRIMARY KEY NOT NULL,
CEDULA VARCHAR(20) NOT NULL,
NOMBRE VARCHAR(50) NOT NULL,
PRIMER_APELLIDO VARCHAR(50) NOT NULL,
SEGUNDO_APELLIDO VARCHAR(50) NOT NULL,
TELEFONO VARCHAR(20) NOT NULL,
CORREO VARCHAR(50) NULL,
NUMERO_CUENTA VARCHAR(50) NOT NULL,
FECHA_CONTRATACION DATE NOT NULL,
PRESTAMO DECIMAL(10,2) NULL,
PENSION DECIMAL(10,2) NULL,
BORRADO_LOGICO BIT NOT NULL
)

CREATE TABLE INFORMACION_ACADEMICA(
ID_INFORMACION_A INT IDENTITY CONSTRAINT PK_INFORMACION_ACADEMICA PRIMARY KEY NOT NULL,
ID_EMPLEADO INT NOT NULL,
GRADO VARCHAR(25) NOT NULL,
ESPECIALIDAD VARCHAR(100) NOT NULL,
INFORMACION VARCHAR(500) NULL,
BORRADO_LOGICO BIT NOT NULL
)

CREATE TABLE PUESTOS(
ID_PUESTO INT IDENTITY CONSTRAINT PK_PUESTOS PRIMARY KEY NOT NULL,
NOMBRE_PUESTO VARCHAR(100) NOT NULL,
CATEGORIA_PUESTO INT NOT NULL,
SALARIO_BASE DECIMAL NOT NULL,
BORRADO_LOGICO BIT NOT NULL
)

CREATE TABLE EMPLEADOS_PUESTOS(
ID_EMPLEADO_PUESTO INT IDENTITY CONSTRAINT PK_EMPLEADOS_PUESTOS PRIMARY KEY NOT NULL,
ID_EMPLEADO INT NOT NULL,
ID_PUESTO INT NOT NULL,
BORRADO_LOGICO BIT NOT NULL
)

CREATE TABLE PLANILLAS(
ID_PLANILLA INT IDENTITY CONSTRAINT PK_PLANILLAS PRIMARY KEY NOT NULL,
FECHA DATE NOT NULL,
BORRADO_LOGICO BIT NOT NULL
)

CREATE TABLE DETALLES_PLANILLAS(
ID_DETALLE_PLANILLA INT IDENTITY CONSTRAINT PK_DETALLES_PLANILLAS PRIMARY KEY NOT NULL,
ID_PLANILLA INT NOT NULL,
ID_EMPLEADO INT NOT NULL,
SALARIO_NETO DECIMAL NOT NULL,
SALARIO_BRUTO DECIMAL NOT NULL,
PRIMER_ADELANTO DECIMAL NOT NULL,
ADELANTO_FINAL_SALARIAL DECIMAL NOT NULL,
BORRADO_LOGICO BIT NOT NULL
)

CREATE TABLE PAGOS(
ID_PAGO INT IDENTITY CONSTRAINT PK_PAGOS PRIMARY KEY NOT NULL,
ID_DETALLE_PLANILLA INT NOT NULL,
CONCEPTO VARCHAR(75),
PORCENTAJE DECIMAL NULL,
MONTO DECIMAL NOT NULL
)

CREATE TABLE DEDUCCIONES(
ID_DEDUCCION INT IDENTITY CONSTRAINT PK_DEDUCCIONES PRIMARY KEY NOT NULL,
CONCEPTO VARCHAR(75),
ID_DETALLE_PLANILLA INT NOT NULL,
PORCENTAJE DECIMAL NULL,
MONTO DECIMAL NOT NULL
)

--CREATE TABLE INFORMACION(
--ID_INFORMACION INT PRIMARY KEY NOT NULL,
--ANUALIDAD DECIMAL(10,2) NOT NULL,
--ESCALAFON_1 DECIMAL(10,2) NOT NULL,
--ESCALAFON_2 DECIMAL(10,2) NOT NULL,
--DEDICACION_EXCLUSIVA DECIMAL(10,2) NOT NULL,
--MAGISTERIO DECIMAL(10,2) NOT NULL,
--BANCO_POPULAR DECIMAL(10,2) NOT NULL,
--CCSS DECIMAL(10,2) NOT NULL,
--POLIZA DECIMAL(10,2) NOT NULL,
--COLEGIO_PROFESIONAL DECIMAL(10,2) NOT NULL
--)

--INSERT INTO INFORMACION(ID_INFORMACION,ANUALIDAD,ESCALAFON_1,ESCALAFON_2,DEDICACION_EXCLUSIVA,MAGISTERIO,BANCO_POPULAR,CCSS,POLIZA,COLEGIO_PROFESIONAL)
--VALUES(1,2,3,1,30,8,1,3,13450,5000)

CREATE TABLE DEDUCCIONES_PAGOS(
	ID_DEDUCCION_PAGO INT IDENTITY CONSTRAINT PK_DEDUCCIONES_PAGOS PRIMARY KEY NOT NULL,
	DEDUCCION_GENERAL VARCHAR(50) NOT NULL,
	DEDUCCION_DETALLADA VARCHAR(500) NULL,
	ES_DEDUCCION BIT NOT NULL DEFAULT 1,
	TIPO VARCHAR(3) DEFAULT 'POR',
	MONTO DECIMAL(10,2) NOT NULL
)

--******************************************************************************
--EMPLEADOS
GO
ALTER TABLE EMPLEADOS
ADD CONSTRAINT DF_BORRADO_LOGICO
DEFAULT 0 FOR BORRADO_LOGICO

GO
ALTER TABLE EMPLEADOS
ADD CONSTRAINT DF_PENSION
DEFAULT 0 FOR PENSION

GO
ALTER TABLE EMPLEADOS
ADD CONSTRAINT DF_PRESTAMO
DEFAULT 0 FOR PRESTAMO

--******************************************************************************
--INFORMACION_ACADEMICA
GO
ALTER TABLE INFORMACION_ACADEMICA
ADD CONSTRAINT FK_INFORMACION_ACADEMICA
FOREIGN KEY (ID_EMPLEADO) REFERENCES EMPLEADOS(ID_EMPLEADO)

GO
ALTER TABLE INFORMACION_ACADEMICA
ADD CONSTRAINT CHK_INFORMACION_ACADEMICA
CHECK (GRADO IN('BACHILLER', 'LICENCIADO', 'MAESTRIA', 'DOCTORADO', 'TECNICO', 'OTRO'))

GO
ALTER TABLE INFORMACION_ACADEMICA
ADD CONSTRAINT DF_INFORMACION_ACADEMICA_BORRADO
DEFAULT 0 FOR BORRADO_LOGICO

--******************************************************************************
--PUESTOS
GO
ALTER TABLE PUESTOS
ADD CONSTRAINT DF_BORRADO_PUESTOS
DEFAULT 0 FOR BORRADO_LOGICO

--******************************************************************************
--EMPLEADOS_PUESTOS
GO 
ALTER TABLE EMPLEADOS_PUESTOS
ADD CONSTRAINT FK1_EMPLEADOS_PUESTOS
FOREIGN KEY (ID_EMPLEADO) REFERENCES EMPLEADOS(ID_EMPLEADO)

GO
ALTER TABLE EMPLEADOS_PUESTOS
ADD CONSTRAINT FK2_EMPLEADOS_PUESTOS
FOREIGN KEY (ID_PUESTO) REFERENCES PUESTOS(ID_PUESTO)

GO
ALTER TABLE EMPLEADOS_PUESTOS
ADD CONSTRAINT DF_EMPLADOS_PUESTOS_BORRRADO
DEFAULT 0 FOR BORRADO_LOGICO

--******************************************************************************
--PLANILLAS

GO
ALTER TABLE PLANILLAS
ADD CONSTRAINT DF_BORRADO_PLANILLAS
DEFAULT 0 FOR BORRADO_LOGICO

--******************************************************************************
--DETALLES PLANILLAS

GO
ALTER TABLE DETALLES_PLANILLAS
ADD CONSTRAINT FK1_DETALLES_PLANILLAS
FOREIGN KEY (ID_PLANILLA) REFERENCES PLANILLAS(ID_PLANILLA)

GO
ALTER TABLE DETALLES_PLANILLAS
ADD CONSTRAINT FK2_DETALLES_PLANILLAS
FOREIGN KEY (ID_EMPLEADO) REFERENCES EMPLEADOS(ID_EMPLEADO)

GO
ALTER TABLE DETALLES_PLANILLAS
ADD CONSTRAINT DF_DETALLES_PLANILLAS
DEFAULT 0 FOR BORRADO_LOGICO

--********************************************************************************
--PAGOS

GO
ALTER TABLE PAGOS
ADD CONSTRAINT FK_PAGOS
FOREIGN KEY (ID_DETALLE_PLANILLA) REFERENCES DETALLES_PLANILLAS(ID_DETALLE_PLANILLA)

--*********************************************************************************
--DEDUCCIONES
GO
ALTER TABLE DEDUCCIONES
ADD CONSTRAINT FK_DEDUCCIONES
FOREIGN KEY (ID_DETALLE_PLANILLA) REFERENCES DETALLES_PLANILLAS(ID_DETALLE_PLANILLA)

GO
ALTER TABLE DEDUCCIONES_PAGOS
ADD CONSTRAINT CHK_TIPO_DEDDUCIONES_PAGOS
CHECK (TIPO IN('POR','DEC'))

--DROP DATABASE PLANILLA

--/********************************************************************************/
--/                                                                                /
--/                            PROCEDIMIENTOS ALMACENADOS                          /
--/                                                                                /
--/********************************************************************************/

GO
CREATE PROCEDURE SP_GUARDAR_EMPLEADO(@id_empleado int out,
									 @cedula varchar(20),
									 @nombre varchar(50),
									 @primer_apellido varchar(50),
									 @segundo_apellido varchar(50),
									 @telefono varchar(20),
									 @correo varchar(50),
									 @numero_cuenta varchar(50),
									 @fecha_contratacion date,
									 @msj varchar(150) out)
AS
BEGIN TRY
	IF(EXISTS(SELECT 1 FROM EMPLEADOS WHERE ID_EMPLEADO=@id_empleado))
		BEGIN
			UPDATE EMPLEADOS
			SET CEDULA=@cedula,
				NOMBRE=@nombre,
				PRIMER_APELLIDO=@primer_apellido,
				SEGUNDO_APELLIDO=@segundo_apellido,
				TELEFONO=@telefono,
				CORREO=@correo,
				NUMERO_CUENTA=@numero_cuenta,
				FECHA_CONTRATACION=@fecha_contratacion
			WHERE ID_EMPLEADO=@id_empleado
			SET @msj='Empleado actualizado de forma correcta.'
		END
	ELSE
		BEGIN
			INSERT INTO EMPLEADOS(CEDULA,NOMBRE,PRIMER_APELLIDO,SEGUNDO_APELLIDO,TELEFONO,CORREO,NUMERO_CUENTA,FECHA_CONTRATACION)
			VALUES(@cedula,@nombre,@primer_apellido,@segundo_apellido,@telefono,@correo,@numero_cuenta,@fecha_contratacion)
			SELECT @id_empleado=IDENT_CURRENT('EMPLEADOS')
			SET @msj='Empleado ingresado de forma correcta.'
		END
ENd TRY
BEGIN CATCH
	RAISERROR('Error al tratar de ingresar o actualizar empleado.',16,1)
END CATCH
--select * from empleados

GO
CREATE PROCEDURE SP_ELIMINAR_EMPLEADO(@id_empleado int,
									  @msj varchar(150) out)
AS
BEGIN TRY
	BEGIN TRAN
		IF(EXISTS(SELECT 1 FROM EMPLEADOS WHERE ID_EMPLEADO=@id_empleado))
			BEGIN
				IF(EXISTS(SELECT 1 FROM INFORMACION_ACADEMICA WHERE ID_EMPLEADO=@id_empleado))
					BEGIN
						DELETE INFORMACION_ACADEMICA WHERE ID_EMPLEADO=@id_empleado
					END

				IF(EXISTS(SELECT 1 FROM EMPLEADOS_PUESTOS WHERE ID_EMPLEADO=@id_empleado))
					BEGIN
						DELETE EMPLEADOS_PUESTOS WHERE ID_EMPLEADO=@id_empleado
					END

				IF(EXISTS(SELECT 1 FROM DETALLES_PLANILLAS WHERE ID_EMPLEADO=@id_empleado))
					BEGIN
						UPDATE EMPLEADOS
						SET BORRADO_LOGICO=1
						WHERE ID_EMPLEADO=@id_empleado
						SET @msj='Empleado eliminado de forma satisfactoria.'
					END
				ELSE
					BEGIN
						DELETE EMPLEADOS WHERE ID_EMPLEADO=@id_empleado
						set @msj='Empleado eliminado de forma satisfactoria.'
					END
			END
		ELSE
			BEGIN
				SET @msj='Empleado especificado no encontrado.'
			END
	COMMIT TRAN
END TRY
BEGIN CATCH
	ROLLBACK TRAN
	RAISERROR('Error al tratar de eliminar empleado.',16,2)
END CATCH

--select * from empleados

GO
CREATE PROCEDURE SP_GUARDAR_INFORMACION_ACADEMICA(@id_informacion_a int out,
												  @id_empleado int,
												  @grado varchar(25),
												  @especialidad varchar(100),
												  @informacion varchar(1000),
												  @msj varchar(150) out)
AS
BEGIN TRY
	IF(EXISTS(SELECT 1 FROM INFORMACION_ACADEMICA WHERE ID_INFORMACION_A=@id_informacion_a))
		BEGIN
			UPDATE INFORMACION_ACADEMICA
			SET GRADO=@grado,
				ESPECIALIDAD=@especialidad,
				INFORMACION=@informacion
			WHERE ID_INFORMACION_A=@id_informacion_a
			SET @msj='Informaci�n academica actualizada de forma correcta.'
		END
	ELSE
		BEGIN
			INSERT INTO INFORMACION_ACADEMICA(ID_EMPLEADO,GRADO,ESPECIALIDAD,INFORMACION)
			VALUES(@id_empleado,@grado,@especialidad,@informacion)
			SET @msj='Informaci�n academica insertada de forma correcta.'
			SELECT @id_informacion_a=IDENT_CURRENT('INFORMACION_ACADEMICA')
		END
END TRY
BEGIN CATCH
	RAISERROR('Error al tratar de insertar o actualizar la informaci�n academica de este usuairo.',16,3)

END CATCH

GO
CREATE PROCEDURE SP_ELIMINAR_INFORMACION_ACADEMICA(@id_informaciona_a int,
												   @msj varchar(150) out)
AS
BEGIN TRY
	IF(EXISTS(SELECT 1 FROM INFORMACION_ACADEMICA WHERE ID_INFORMACION_A=@id_informaciona_a))
		BEGIN
			DELETE INFORMACION_ACADEMICA WHERE ID_INFORMACION_A=@id_informaciona_a
			SET @msj='Informaci�n academica del empleado fue eliminada de forma correcta.'
		END
	ELSE
		BEGIN
			set @msj='Informaci�n academica del empleado no encontrada.'
		END
END TRY
BEGIN CATCH
	RAISERROR('Error al tratar de eliminar informaci�n academica de este empleado.',16,4)
END CATCH

--select * from informacion_academica

GO
CREATE PROCEDURE SP_GUARDAR_PUESTOS(@id_puesto int out,
									@nombre_puesto varchar(100),
									@categoria_puesto int,
									@salario_base decimal,
									@msj varchar(150)out)
AS
BEGIN TRY
	IF(EXISTS(SELECT 1 FROM PUESTOS WHERE ID_PUESTO=@id_puesto))
		BEGIN
			UPDATE PUESTOS
			SET NOMBRE_PUESTO=@nombre_puesto,
				CATEGORIA_PUESTO=@categoria_puesto,
				SALARIO_BASE=@salario_base
			WHERE ID_PUESTO=@id_puesto

			SET @msj='Puesto actualizado de forma correcta.'
		END
	ELSE
		BEGIN
			INSERT INTO PUESTOS(NOMBRE_PUESTO,CATEGORIA_PUESTO,SALARIO_BASE)
			VALUES(@nombre_puesto,@categoria_puesto,@salario_base)

			SET @id_puesto=IDENT_CURRENT('PUESTOS')
			set @msj='Puesto agregado de forma correcta.'
		END
END TRY

BEGIN CATCH
	RAISERROR('Error al tratar de actualizar o insertar el puesto.',16,5)
END CATCH
--select * from puestos
GO
CREATE PROCEDURE SP_ELIMINAR_PUESTO(@id_puesto int,
									@msj varchar(150) out)
AS
BEGIN TRY
	IF(EXISTS(SELECT 1 FROM EMPLEADOS_PUESTOS WHERE ID_PUESTO=@id_puesto))
		BEGIN
			UPDATE PUESTOS
			SET BORRADO_LOGICO=1
			WHERE ID_PUESTO=@id_puesto
			SET @msj='Puesto eliminado de forma satisfactoria.'
		END
	ELSE
		BEGIN
			IF(EXISTS(SELECT 1 FROM PUESTOS WHERE ID_PUESTO=@id_puesto))
				BEGIN
					DELETE PUESTOS WHERE ID_PUESTO=@id_puesto
					SET @msj='Puesto eliminado de forma correcta.'
				END
		END
END TRY
BEGIN CATCH
	RAISERROR('Error al eliminar el puesto.',16,6)
END CATCH

GO
CREATE PROCEDURE SP_GUARDAR_PLANILLA(@id_planilla int OUT,
									  @fecha date)
AS
BEGIN TRY
	IF(EXISTS(SELECT 1 FROM PLANILLAS WHERE ID_PLANILLA=@id_planilla))
		BEGIN
			UPDATE PLANILLAS
			SET FECHA=@fecha
			WHERE ID_PLANILLA=@id_planilla
		END
	ELSE
		BEGIN
			INSERT INTO PLANILLAS(FECHA)
			VALUES(@fecha)
			SELECT @id_planilla=IDENT_CURRENT('PLANILLAS')
		END
END TRY
BEGIN CATCH
	RAISERROR('Error al tratar de ingresar el refistro de planilla.',16,7)
END CATCH

--select * from planillas

GO
CREATE PROCEDURE SP_CUARDAR_DETALLES_PLANILLAS(@id_detalle_planilla int out,
									   @id_planilla int,
									   @id_empleado int,
									   @salario_neto decimal(10,2),
									   @salario_bruto decimal(10,2),
									   @primer_adelanto_salario decimal(10,2),
									   @adelanto_salarial_final decimal(10,2),
									   @msj varchar(150) out)
AS
BEGIN TRY
	IF(EXISTS(SELECT 1 FROM DETALLES_PLANILLAS WHERE ID_DETALLE_PLANILLA=@id_detalle_planilla))
		BEGIN
			UPDATE DETALLES_PLANILLAS
			SET SALARIO_NETO=@salario_neto,
				SALARIO_BRUTO=@salario_bruto,
				PRIMER_ADELANTO=@primer_adelanto_salario,
				ADELANTO_FINAL_SALARIAL=@adelanto_salarial_final
			WHERE ID_DETALLE_PLANILLA=@id_detalle_planilla
			SET @msj='Informaci�n del empleado actualizada a la planilla de forma correcta.'
		END
	ELSE
		BEGIN
			INSERT INTO DETALLES_PLANILLAS(ID_PLANILLA,ID_EMPLEADO,SALARIO_BRUTO,SALARIO_NETO,PRIMER_ADELANTO,ADELANTO_FINAL_SALARIAL)
			VALUES (@id_planilla,@id_empleado,@salario_bruto,@salario_neto,@primer_adelanto_salario,@adelanto_salarial_final)
			SELECT @id_detalle_planilla=IDENT_CURRENT('DETALLES_PLANILLAS')
			SET @msj='Informaci�n del empleado agragada a la planilla de forma correcta.'
		END
END TRY
BEGIN CATCH
	RAISERROR('Error al tratar de agregar empleado a la planilla,',16,8)
END CATCH

GO
CREATE PROCEDURE SP_GUARDAR_PAGOS(@id_pago int out,
								  @id_detalle_planilla int,
								  @concepto varchar(75),
								  @porcentaje decimal,
								  @monto decimal)
AS
BEGIN TRY
	IF(EXISTS(SELECT 1 FROM PAGOS WHERE ID_PAGO=@id_pago))
		BEGIN
			UPDATE PAGOS
			SET PORCENTAJE=@porcentaje,
				CONCEPTO=@concepto,
				MONTO=@monto
			WHERE ID_PAGO=@id_pago
		END
	ELSE
		BEGIN
			INSERT INTO PAGOS(ID_DETALLE_PLANILLA,CONCEPTO,PORCENTAJE,MONTO)
			VALUES (@id_detalle_planilla,@concepto,@porcentaje,@monto)
			SELECT @id_pago=IDENT_CURRENT('PAGOS')
		END
END TRY
BEGIN CATCH
	RAISERROR('Error al tratar de insertar el pago.',16,9)
END CATCH

GO
CREATE PROCEDURE SP_GUARDAR_DEDUCCIONES(@id_deduccion int out,
								  @id_detalle_planilla int,
								  @concepto varchar(75),
								  @porcentaje decimal,
								  @monto decimal)
AS
BEGIN TRY
	IF(EXISTS(SELECT 1 FROM DEDUCCIONES WHERE ID_DEDUCCION=@id_deduccion))
		BEGIN
			UPDATE DEDUCCIONES
			SET PORCENTAJE=@porcentaje,
				CONCEPTO=@concepto,
				MONTO=@monto
			WHERE ID_DEDUCCION=@id_deduccion
		END
	ELSE
		BEGIN
			INSERT INTO DEDUCCIONES(ID_DETALLE_PLANILLA,CONCEPTO,PORCENTAJE,MONTO)
			VALUES (@id_detalle_planilla,@concepto,@porcentaje,@monto)
			SELECT @id_deduccion=IDENT_CURRENT('DEDUCCIONES')
		END
END TRY
BEGIN CATCH
	RAISERROR('Error al tratar de insertar el pago.',16,10)
END CATCH

--DROP DATABASE PLANILLA

GO
CREATE PROCEDURE SP_AGREGAR_PENSION(@id_empleado int,
									@pension decimal(10,2),
									@msj varchar(150) out)
AS
BEGIN TRY
	IF(EXISTS(SELECT 1 FROM EMPLEADOS WHERE ID_EMPLEADO=@id_empleado))
		BEGIN
			UPDATE EMPLEADOS
			SET PENSION=@pension
			WHERE ID_EMPLEADO=@id_empleado

			SET @msj='Monto de pensi�n agregado de forma correcta.'
		END
	ELSE
		BEGIN
			SET @msj='No se puede agregar monto a pensi�n, no se encuentra el empleado.'
		END
END TRY
BEGIN CATCH
	RAISERROR('Error al tratar de agregar una pensi�n al empleado',16,11)
END CATCH

GO
CREATE PROCEDURE SP_AGREGAR_PRESTAMO(@id_empleado int,
									 @prestamo decimal(10,2),
									 @msj varchar(150) out)
AS
BEGIN TRY
	IF(EXISTS(SELECT 1 FROM EMPLEADOS WHERE ID_EMPLEADO=@id_empleado))
		BEGIN
			UPDATE EMPLEADOS
			SET PRESTAMO=@prestamo
			WHERE ID_EMPLEADO=@id_empleado

			SET @msj='Monto de prestamo agregado de forma correcta.'
		END
	ELSE
		BEGIN
			SET @msj='No se puede agregar monto a prestamo, no se encuentra el empleado.'
		END
END TRY
BEGIN CATCH
	RAISERROR('Error al tratar de agregar una prestamo al empleado',16,11)
END CATCH

--go
--CREATE PROCEDURE SP_ACTUALIZAR_INFORMACION(
--										   @anualidad decimal,
--										   @escalafon1 decimal,
--										   @escalafon2 decimal,
--										   @dedicacion_exclusiva decimal,
--										   @magisterio decimal,
--										   @banco_popular decimal,
--										   @ccss decimal,
--										   @poliza decimal,
--										   @colegio_profesional decimal,
--										   @msj varchar(150) out)
--AS
--BEGIN TRY
--	UPDATE INFORMACION
--	SET ANUALIDAD=@anualidad,
--		ESCALAFON_1=@escalafon1,
--		ESCALAFON_2=@escalafon2,
--		DEDICACION_EXCLUSIVA=@dedicacion_exclusiva,
--		MAGISTERIO=@magisterio,
--		BANCO_POPULAR=@banco_popular,
--		CCSS=@ccss,
--		POLIZA=@poliza,
--		COLEGIO_PROFESIONAL=@colegio_profesional
--	WHERE ID_INFORMACION=1

--	SET @msj='La informaci�n se actualizo de forma correcta.'
--END TRY
--BEGIN CATCH
--	RAISERROR('Error al trarar de actualizar la informaci�n.',16,12)
--END CATCH

GO
CREATE PROCEDURE SP_ASIGANAR_PUESTO_EMPLEADO(@id_emplado_puesto int,
											 @id_empleado int,
											 @id_puesto int,
											 @id_informacion_a int,
											 @msj varchar(150) out)
AS
BEGIN TRY
	IF(NOT EXISTS(SELECT 1 FROM EMPLEADOS WHERE ID_EMPLEADO=@id_empleado))
		BEGIN
			IF(EXISTS(SELECT 1 FROM PUESTOS WHERE ID_PUESTO=@id_puesto AND CATEGORIA_PUESTO=2))
				BEGIN
					IF(EXISTS(SELECT 1 FROM INFORMACION_ACADEMICA WHERE ID_INFORMACION_A=@id_informacion_a AND NOT(GRADO='OTRO') AND NOT(GRADO='TECNICO')))
						BEGIN
							INSERT INTO EMPLEADOS_PUESTOS(ID_EMPLEADO,ID_PUESTO)
							VALUES (@id_empleado,@id_puesto)
							set @msj='Se a asignado de forma correcta el puesto al empleado.'
						END
					ELSE
						BEGIN
							SET @msj='Error no se puede asignar un este puesto a al empleado ya que no cumple con el requesito.'
						END
				END
			ELSE
				BEGIN
					INSERT INTO EMPLEADOS_PUESTOS(ID_EMPLEADO,ID_PUESTO)
					VALUES (@id_empleado,@id_puesto)
					set @msj='Se a asignado de forma correcta el puesto al empleado.'
				END
		END

	ELSE
		BEGIN 
			IF(EXISTS(SELECT 1 FROM PUESTOS WHERE ID_PUESTO=@id_puesto AND CATEGORIA_PUESTO=2))
				BEGIN
					IF(EXISTS(SELECT 1 FROM INFORMACION_ACADEMICA WHERE ID_INFORMACION_A=@id_informacion_a AND NOT(GRADO='OTRO') AND NOT(GRADO='TECNICO')))
						BEGIN
							UPDATE EMPLEADOS_PUESTOS
							SET ID_EMPLEADO=@id_empleado,
								ID_PUESTO=@id_puesto
								WHERE ID_EMPLEADO_PUESTO=@id_emplado_puesto
							set @msj='Se a asignado de forma correcta el puesto al empleado.'
						END
					ELSE
						BEGIN
							SET @msj='Error no se puede asignar un este puesto a al empleado ya que no cumple con el requesito.'
						END
				END
			ELSE
				BEGIN
					INSERT INTO EMPLEADOS_PUESTOS(ID_EMPLEADO,ID_PUESTO)
					VALUES (@id_empleado,@id_puesto)
					set @msj='Se a asignado de forma correcta el puesto al empleado.'
				END
		END
END TRY

BEGIN CATCH
	RAISERROR('Error al tratar de asignar un puesto al empleado.',16,13)
END CATCH

GO
CREATE PROCEDURE SP_GUARDAR_DEDUCCIONES_PAGOS(@id_deduccion_pago int out,
										 @deduccion_general varchar(50),
										 @deducciones_detallada varchar(500),
										 @es_deduccion bit,
										 @tipo varchar(3),
										 @monto decimal(10,2),
										 @msj varchar(150) out)
AS
BEGIN TRY
	IF(NOT EXISTS(SELECT 1 FROM DEDUCCIONES_PAGOS WHERE ID_DEDUCCION_PAGO=@id_deduccion_pago))
		BEGIN
			INSERT INTO DEDUCCIONES_PAGOS(DEDUCCION_GENERAL,DEDUCCION_DETALLADA,ES_DEDUCCION,TIPO,MONTO)
			VALUES(@deduccion_general,@deducciones_detallada,@es_deduccion,@tipo,@monto)

			SET @msj='Se agreg� de forma correcta el registro.'
			SELECT @id_deduccion_pago=IDENT_CURRENT('DEDUCCIONES_PAGOS')
		END
	ELSE
		BEGIN
			UPDATE DEDUCCIONES_PAGOS
			SET DEDUCCION_GENERAL=@deduccion_general,
				DEDUCCION_DETALLADA=@deducciones_detallada,
				ES_DEDUCCION=@es_deduccion,
				TIPO=@tipo,
				MONTO=@monto
			WHERE ID_DEDUCCION_PAGO=@id_deduccion_pago
			SET @msj='Se actualiz� el registro de forma correcta.'
		END
END TRY
BEGIN CATCH
	RAISERROR('Error al tratar de guardar registro.',16,14)
END CATCH

GO
CREATE PROCEDURE SP_ELIMINAR_DEDDUCIONES_PAGOS(@id_deducciones_pago int,
											   @msj varchar(150) out)
AS
BEGIN TRY
	IF(EXISTS(SELECT 1 FROM DEDUCCIONES_PAGOS WHERE ID_DEDUCCION_PAGO=@id_deducciones_pago))
		BEGIN
			DELETE DEDUCCIONES_PAGOS WHERE ID_DEDUCCION_PAGO=@id_deducciones_pago
			SET @msj='Se elimin� de forma correcta las deducciones.'
		END
END TRY
BEGIN CATCH
	RAISERROR('Error al eliminar el registro.',16,15)
END CATCH

GO
INSERT INTO EMPLEADOS(CEDULA, NOMBRE, PRIMER_APELLIDO, SEGUNDO_APELLIDO,TELEFONO, CORREO, NUMERO_CUENTA,FECHA_CONTRATACION,PRESTAMO,PENSION)
VALUES('207420846','TOMAS','CHEVEZ','ELIZONDO','89677620','CORREO','654654654','20180205','0','0')

INSERT INTO EMPLEADOS(CEDULA, NOMBRE, PRIMER_APELLIDO, SEGUNDO_APELLIDO,TELEFONO, CORREO, NUMERO_CUENTA,FECHA_CONTRATACION,PRESTAMO,PENSION)
VALUES('165465465','ANA','ROJAS','PEREZ','12324565','COREO','654654654','20170506','20000','15000')

INSERT INTO EMPLEADOS(CEDULA, NOMBRE, PRIMER_APELLIDO, SEGUNDO_APELLIDO,TELEFONO, CORREO, NUMERO_CUENTA,FECHA_CONTRATACION,PRESTAMO,PENSION)
VALUES('116545545','MARIO','MONGE','SOTO','12324565','COREO','654654654','20170506','20000','0')

INSERT INTO EMPLEADOS(CEDULA, NOMBRE, PRIMER_APELLIDO, SEGUNDO_APELLIDO,TELEFONO, CORREO, NUMERO_CUENTA,FECHA_CONTRATACION,PRESTAMO,PENSION)
VALUES('454564564','CARLOS','MATA','MENDEZ','12324565','COREO','654654654','20170506','0','15000')

--SELECT * FROM EMPLEADOS

INSERT INTO INFORMACION_ACADEMICA(ID_EMPLEADO,GRADO,ESPECIALIDAD,INFORMACION)
VALUES('1','BACHILLER','PROFESOR SOCIALES',NULL)

INSERT INTO INFORMACION_ACADEMICA(ID_EMPLEADO,GRADO,ESPECIALIDAD,INFORMACION)
VALUES('2','LICENCIADO','PROFESOR MATEMATICA',NULL)

INSERT INTO INFORMACION_ACADEMICA(ID_EMPLEADO,GRADO,ESPECIALIDAD,INFORMACION)
VALUES('3','MAESTRIA','PROFESOR ARTES',NULL)

INSERT INTO INFORMACION_ACADEMICA(ID_EMPLEADO,GRADO,ESPECIALIDAD,INFORMACION)
VALUES('4','DOCTORADO','PROFESOR CULTURA',NULL)

--INSERT INTO INFORMACION_ACADEMICA(ID_EMPLEADO,GRADO,ESPECIALIDAD,INFORMACION)
--VALUES('5','OTRO','JARDIMERO',NULL)

--SELECT * FROM INFORMACION_ACADEMICA

INSERT INTO PUESTOS(NOMBRE_PUESTO,CATEGORIA_PUESTO,SALARIO_BASE)
VALUES('PROFESIONA DE APOYO','2','250000')

INSERT INTO PUESTOS(NOMBRE_PUESTO,CATEGORIA_PUESTO,SALARIO_BASE)
VALUES('DOCENTE LICENCIADO','2','620000')

INSERT INTO PUESTOS(NOMBRE_PUESTO,CATEGORIA_PUESTO,SALARIO_BASE)
VALUES('DOCENTE MASTER','2','750000')

INSERT INTO PUESTOS(NOMBRE_PUESTO,CATEGORIA_PUESTO,SALARIO_BASE)
VALUES('DIRECTOR','2','1500000')

INSERT INTO PUESTOS(NOMBRE_PUESTO,CATEGORIA_PUESTO,SALARIO_BASE)
VALUES('PERSONAL DE APOYO','1','250000')

--SELECT * FROM PUESTOS

INSERT INTO EMPLEADOS_PUESTOS(ID_EMPLEADO,ID_PUESTO)
VALUES('1','1')

INSERT INTO EMPLEADOS_PUESTOS(ID_EMPLEADO,ID_PUESTO)
VALUES('2','2')

INSERT INTO EMPLEADOS_PUESTOS(ID_EMPLEADO,ID_PUESTO)
VALUES('3','3')

INSERT INTO EMPLEADOS_PUESTOS(ID_EMPLEADO,ID_PUESTO)
VALUES('4','4')

--INSERT INTO EMPLEADOS_PUESTOS(ID_EMPLEADO,ID_PUESTO)
--VALUES('5','5')

--SELECT * FROM EMPLEADOS_PUESTOS
select * from PAGOS;