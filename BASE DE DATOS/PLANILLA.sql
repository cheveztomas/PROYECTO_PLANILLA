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

CREATE TABLE INFORMACION(
ID_INFORMACION INT PRIMARY KEY NOT NULL,
ANUALIDAD DECIMAL(10,2) NOT NULL,
ESCALAFON_1 DECIMAL(10,2) NOT NULL,
ESCALAFON_2 DECIMAL(10,2) NOT NULL,
DEDICACION_EXCLUSIVA DECIMAL(10,2) NOT NULL,
MAGISTERIO DECIMAL(10,2) NOT NULL,
BANCO_POPULAR DECIMAL(10,2) NOT NULL,
CCSS DECIMAL(10,2) NOT NULL,
POLIZA DECIMAL(10,2) NOT NULL,
COLEGIO_PROFESIONAL DECIMAL(10,2) NOT NULL
)

INSERT INTO INFORMACION(ID_INFORMACION,ANUALIDAD,ESCALAFON_1,ESCALAFON_2,DEDICACION_EXCLUSIVA,MAGISTERIO,BANCO_POPULAR,CCSS,POLIZA,COLEGIO_PROFESIONAL)
VALUES(1,2,3,1,30,8,1,3,13450,5000)

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
			SET @msj='Información academica actualizada de forma correcta.'
		END
	ELSE
		BEGIN
			INSERT INTO INFORMACION_ACADEMICA(ID_EMPLEADO,GRADO,ESPECIALIDAD,INFORMACION)
			VALUES(@id_empleado,@grado,@especialidad,@informacion)
			SET @msj='Información academica insertada de forma correcta.'
			SELECT @id_informacion_a=IDENT_CURRENT('INFORMACION_ACADEMICA')
		END
END TRY
BEGIN CATCH
	RAISERROR('Error al tratar de insertar o actualizar la información academica de este usuairo.',16,3)

END CATCH

GO
CREATE PROCEDURE SP_ELIMINAR_INFORMACION_ACADEMICA(@id_informaciona_a int,
												   @msj varchar(150) out)
AS
BEGIN TRY
	IF(EXISTS(SELECT 1 FROM INFORMACION_ACADEMICA WHERE ID_INFORMACION_A=@id_informaciona_a))
		BEGIN
			DELETE INFORMACION_ACADEMICA WHERE ID_INFORMACION_A=@id_informaciona_a
			SET @msj='Información academica del empleado fue eliminada de forma correcta.'
		END
	ELSE
		BEGIN
			set @msj='Información academica del empleado no encontrada.'
		END
END TRY
BEGIN CATCH
	RAISERROR('Error al tratar de eliminar información academica de este empleado.',16,4)
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
			SET @msj='Información del empleado actualizada a la planilla de forma correcta.'
		END
	ELSE
		BEGIN
			INSERT INTO DETALLES_PLANILLAS(ID_PLANILLA,ID_EMPLEADO,SALARIO_BRUTO,SALARIO_NETO,PRIMER_ADELANTO,ADELANTO_FINAL_SALARIAL)
			VALUES (@id_planilla,@id_empleado,@salario_bruto,@salario_neto,@primer_adelanto_salario,@adelanto_salarial_final)
			SELECT @id_detalle_planilla=IDENT_CURRENT('DETALLES_PLANILLAS')
			SET @msj='Información del empleado agragada a la planilla de forma correcta.'
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

			SET @msj='Monto de pensión agregado de forma correcta.'
		END
	ELSE
		BEGIN
			SET @msj='No se puede agregar monto a pensión, no se encuentra el empleado.'
		END
END TRY
BEGIN CATCH
	RAISERROR('Error al tratar de agregar una pensión al empleado',16,11)
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

CREATE PROCEDURE SP_ACTUALIZAR_INFORMACION(
										   @anualidad decimal,
										   @escalafon1 decimal,
										   @escalafon2 decimal,
										   @dedicacion_exclusiva decimal,
										   @magisterio decimal,
										   @banco_popular decimal,
										   @ccss decimal,
										   @poliza decimal,
										   @colegio_profesional decimal,
										   @msj varchar(150) out)
AS
BEGIN TRY
	UPDATE INFORMACION
	SET ANUALIDAD=@anualidad,
		ESCALAFON_1=@escalafon1,
		ESCALAFON_2=@escalafon2,
		DEDICACION_EXCLUSIVA=@dedicacion_exclusiva,
		MAGISTERIO=@magisterio,
		BANCO_POPULAR=@banco_popular,
		CCSS=@ccss,
		POLIZA=@poliza,
		COLEGIO_PROFESIONAL=@colegio_profesional
	WHERE ID_INFORMACION=1

	SET @msj='La información se actualizo de forma correcta.'
END TRY
BEGIN CATCH
	RAISERROR('Error al trarar de actualizar la información.',16,12)
END CATCH