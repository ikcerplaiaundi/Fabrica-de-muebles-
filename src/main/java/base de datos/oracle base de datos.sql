/*
IMPORTANTE
la aplicacion con esto solo funion dentro de la red de plaiaundi
no tiene SSH


select * from SYSTEM.empleados;
select * from SYSTEM.CLIENTES;
SELECT * FROM SYSTEM.CLIENTES WHERE NOMBRE_CLIENTE = 'jaimito' and REGISTRADO = 1;
SELECT * FROM SYSTEM.CLIENTES WHERE NOMBRE_CLIENTE = 'jaimito' and CLI_PASWORD ='1234';
SELECT * FROM SYSTEM.EMPLEADOS WHERE NOMBRE_EMPLEADO = 'jaimito';
SELECT * FROM SYSTEM.EMPLEADOS ;
SELECT * FROM SYSTEM.PRODUCTOS ;
alter table SYSTEM.productos DROP COLUMN   ID_pedidos cascade constraints;
alter table SYSTEM.productos DROP COLUMN   fecha_pedido cascade constraints;
*/
/*ALTER USER VENDEDOR DEFAULT TABLESPACE FabricaMuebles;
grant all PRIVILEGES on CLIENTES to AP_ADMIN;
GRANT ALL ON empleados TO AP_ADMIN;

GRANT DBA TO AP_ADMIN;
GRANT RESOURCE TO AP_ADMIN;
SELECT * FROM dba_users WHERE username = 'AP_ADMIN';
SELECT table_name FROM all_tables WHERE tablespace_name = 'FABRICAMUEBLES';*/

/*drop*/
/*
SAVEPOINT SP1;
commit;
ROLLBACK TO SAVEPOINT SP1;
*/

DROP TABLESPACE FabricaMuebles
INCLUDING CONTENTS AND DATAFILES
CASCADE CONSTRAINTS;

drop table empleados cascade CONSTRAINTS;

drop table proveedores cascade CONSTRAINTS;
drop table piezas cascade CONSTRAINTS;
drop table proveedores_piezas cascade CONSTRAINTS;
drop table productos cascade CONSTRAINTS;
drop table pedidos cascade CONSTRAINTS;
drop table facturas cascade CONSTRAINTS;
drop table clientes cascade CONSTRAINTS;
drop table productos_empleados cascade CONSTRAINTS;



/*firt set up  */


CREATE TABLESPACE FabricaMuebles  DATAFILE
'/u01/app/oracle/product/11.2.0/xe/dbs/FabricaMuebles' SIZE 10M AUTOEXTEND ON NEXT 16K
MAXSIZE UNLIMITED
LOGGING
ONLINE
EXTENT MANAGEMENT LOCAL AUTOALLOCATE
BLOCKSIZE 8K
SEGMENT SPACE MANAGEMENT MANUAL
FLASHBACK ON;

ALTER SESSION SET NLS_CALENDAR='GREGORIAN';
ALTER SESSION SET NLS_DATE_LANGUAGE = 'SPANISH';
ALTER SESSION SET NLS_TERRITORY='SPAIN';
ALTER SESSION SET NLS_DATE_FORMAT='DD/MM/YYYY';
/*create model of estructure*/
/*create TABLE*/

CREATE TABLE empleados (
    id_empleados NUMBER   primary key,
    nombre_empleado VARCHAR2(50),
    mgr NUMBER,
    EMP_PASWORD VARCHAR2(50),
    rol VARCHAR2(50)
)TABLESPACE FabricaMuebles;

CREATE TABLE proveedores (
    ID_proveedores NUMBER  primary key,
    nombre_proveedor VARCHAR2(50), 
    contact_proveedor VARCHAR2(50),
    cif_proveedor VARCHAR2(50),
    direccion_proveedor VARCHAR2(50) 
)TABLESPACE FabricaMuebles;

CREATE TABLE piezas (
    ID_piezas NUMBER  primary key,
    fecha_entrada date, 
    nombre_pieza VARCHAR2(50),
    descripcion_pieza VARCHAR2(50),
    stock_pieza NUMBER,
    ID_productos NUMBER
);

CREATE TABLE proveedores_piezas (
    ID_proveedores NUMBER ,
    ID_piezas NUMBER ,
    fecha_esperada date,
    PRIMARY KEY ( ID_proveedores, ID_piezas, fecha_esperada)
)TABLESPACE FabricaMuebles;

CREATE TABLE productos (
    ID_productos NUMBER  primary key,
    nombre_prod VARCHAR2(50),
    decripcion_prod VARCHAR2(50),
    stock_prod NUMBER,
    precio_prod numeric(12,2),
    id_empleados NUMBER
)TABLESPACE FabricaMuebles;

CREATE TABLE pedidos (
    ID_pedidos NUMBER ,
    fecha_pedido date,
    ID_clientes NUMBER,
    direccion_clientes VARCHAR2(50),
    costo_pedido numeric(12,2),
    ID_facturas NUMBER,
    PRIMARY KEY ( ID_pedidos, fecha_pedido)
)TABLESPACE FabricaMuebles;

CREATE TABLE facturas (
    ID_facturas NUMBER  primary key,
    fecha_factura date,
    ID_clientes NUMBER,
    direccion_clientes VARCHAR2(50)
)TABLESPACE FabricaMuebles;

CREATE TABLE clientes (
    ID_clientes NUMBER  ,
    direccion_clientes VARCHAR2(50),
    nombre_cliente VARCHAR2(50),
    contacto_cliente VARCHAR2(50),
    dni_cliente VARCHAR2(50),
    CLI_PASWORD VARCHAR2(50),
    registrado NUMBER,
    PRIMARY KEY ( ID_clientes, direccion_clientes)
)TABLESPACE FabricaMuebles;

CREATE TABLE productos_empleados (
    ID_productos NUMBER ,
    ID_empleados NUMBER,
    PRIMARY KEY ( ID_productos, ID_empleados)
)TABLESPACE FabricaMuebles;

CREATE TABLE productos_pedidos (
    ID_productos NUMBER,
    ID_pedidos NUMBER ,
    fecha_pedido date,
    stock_prod NUMBER,
PRIMARY KEY ( ID_productos, ID_pedidos,fecha_pedido)
)TABLESPACE FabricaMuebles;


/* SEQUENCE id attribute in tables*/
CREATE SEQUENCE id_empleados_seq
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE
;

CREATE SEQUENCE id_proveedores_seq
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;

CREATE SEQUENCE id_piezas_seq
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;

CREATE SEQUENCE id_productos_seq
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;


CREATE SEQUENCE id_pedidos_seq
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;


CREATE SEQUENCE id_facturas_seq
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;

CREATE SEQUENCE id_clientes_seq
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;
/*alters FK*/
ALTER TABLE SYSTEM.productos_pedidos add constraint fk_productos_pedidos1
foreign key (ID_productos)
references SYSTEM.productos (ID_productos);

ALTER TABLE SYSTEM.productos_pedidos add constraint fk_productos_pedidos2
foreign key (ID_pedidos,fecha_pedido)
references SYSTEM.pedidos (ID_pedidos,fecha_pedido);

ALTER TABLE piezas add constraint fk_piezas_ID_producto
foreign key (ID_productos)
references productos (ID_productos);


ALTER TABLE proveedores_piezas add constraint fk_proveedores_piezas1
foreign key (ID_proveedores)
references proveedores (ID_proveedores);
ALTER TABLE proveedores_piezas add constraint fk_proveedores_piezas2
foreign key (ID_piezas)
references piezas (ID_piezas);


ALTER TABLE productos add constraint fk_productos_pedidos_fecha
foreign key (ID_pedidos,fecha_pedido)
references pedidos (ID_pedidos,fecha_pedido);

ALTER TABLE productos add constraint fk_productos_id_emp
foreign key (ID_empleados)
references SYSTEM.empleados (ID_empleados);



ALTER TABLE pedidos add constraint fk_pedidos_direccion_clientes
foreign key (ID_clientes, direccion_clientes)
references clientes (ID_clientes, direccion_clientes);
ALTER TABLE pedidos add constraint fk_pedidos_ID_facturas
foreign key (ID_facturas)
references facturas (ID_facturas);


ALTER TABLE facturas add constraint fk_facturas_ID_clientes
foreign key (ID_clientes, direccion_clientes)
references clientes (ID_clientes, direccion_clientes);


ALTER TABLE productos_empleados add constraint fk_prod_emp_ID_productos
foreign key (ID_productos)
references productos (ID_productos);
ALTER TABLE productos_empleados add constraint fk_prod_emp_id_empleados
foreign key (id_empleados)
references empleados (ID_empleados);


/*create users*/
create user ap_Admin IDENTIFIED BY "123";
GRANT CONNECT TO ap_Admin;
GRANT all ON EMPLEADOS TO ap_Admin;
GRANT DBA TO AP_ADMIN;
GRANT RESOURCE TO AP_ADMIN;

create user Gestor IDENTIFIED BY "123";
GRANT CONNECT TO Gestor;
GRANT SELECT, INSERT, UPDATE, DELETE ON FabricaMuebles TO Gestor;
        
create user Vendedor IDENTIFIED BY "123";
GRANT CONNECT TO  Vendedor;
GRANT SELECT, INSERT, UPDATE, DELETE ON pedidos TO Vendedor;
GRANT SELECT, INSERT, UPDATE, DELETE ON clientes TO  Vendedor;
GRANT SELECT, INSERT, UPDATE, DELETE ON productos TO  Vendedor;
        
create user Operario IDENTIFIED BY "123";
GRANT CONNECT TO Operario;
GRANT SELECT, INSERT, UPDATE, DELETE ON proveedores TO Operario;
GRANT SELECT, INSERT, UPDATE, DELETE ON piezas TO Operario;
GRANT SELECT, INSERT, UPDATE, DELETE ON productos TO Operario;
GRANT SELECT, INSERT, UPDATE, DELETE ON productos_empleados TO  Operario;
    
create user registrado_Cliente IDENTIFIED BY "123";
GRANT CONNECT TO registrado_Cliente;
GRANT SELECT, INSERT, UPDATE, DELETE ON clientes TO registrado_Cliente;
GRANT SELECT,UPDATE ON productos TO registrado_Cliente;
GRANT SELECT, INSERT, UPDATE, DELETE ON pedidos TO registrado_Cliente;

create user Cliente IDENTIFIED BY "123";
GRANT CONNECT TO Cliente;
GRANT SELECT,INSERT ON clientes TO Cliente;
GRANT SELECT,UPDATE ON productos TO Cliente;
GRANT INSERT,UPDATE ON pedidos TO Cliente;
/*triguer*/
CREATE OR REPLACE TRIGGER tr_stock_product
BEFORE UPDATE ON FabricaMuebles.productos
FOR EACH ROW
BEGIN
    IF :new.stock_prod < 0 THEN
    RAISE_APPLICATION_ERROR(-20001, 'El stock debe ser mayor o igual a cero');
  END IF;
END;

CREATE OR REPLACE TRIGGER tr_precio_product
BEFORE UPDATE ON FabricaMuebles.productos
FOR EACH ROW
BEGIN
    IF :new.precio_prod < 0 THEN
    RAISE_APPLICATION_ERROR(-20001, 'El precio debe ser mayor o igual a cero');
  END IF;
END;


/*insert*/


INSERT INTO empleados (id_empleados, nombre_empleado, mgr, EMP_PASWORD, rol) 
VALUES (id_empleados_seq.NEXTVAL, 'dari', 0, '1', 'Gestor');

INSERT INTO empleados (id_empleados, nombre_empleado, mgr, EMP_PASWORD, rol) 
VALUES (id_empleados_seq.NEXTVAL, 'Juan Perez', 0, 'contraseña1', 'Gestor');

INSERT INTO empleados (id_empleados, nombre_empleado, mgr, EMP_PASWORD, rol) 
VALUES (id_empleados_seq.NEXTVAL, 'Maria Rodriguez', 1, 'contraseña2', 'Vendedor');

INSERT INTO empleados (id_empleados, nombre_empleado, mgr, EMP_PASWORD, rol) 
VALUES (id_empleados_seq.NEXTVAL, 'Pedro Sanchez', 1, 'contraseña3', 'Operario');



INSERT INTO proveedores (ID_proveedores, nombre_proveedor, contact_proveedor, cif_proveedor, direccion_proveedor)
VALUES (id_proveedores_seq.NEXTVAL, 'Proveedor1', 'contacto1', 'cif1', 'direccion1');

INSERT INTO proveedores (ID_proveedores, nombre_proveedor, contact_proveedor, cif_proveedor, direccion_proveedor)
VALUES (id_proveedores_seq.NEXTVAL, 'Proveedor2', 'contacto2', 'cif2', 'direccion2');

INSERT INTO proveedores (ID_proveedores, nombre_proveedor, contact_proveedor, cif_proveedor, direccion_proveedor)
VALUES (id_proveedores_seq.NEXTVAL, 'Proveedor3', 'contacto3', 'cif3', 'direccion3');

INSERT INTO clientes (ID_clientes, direccion_clientes, nombre_cliente, contacto_cliente, dni_cliente,CLI_PASWORD,registrado)
VALUES (id_clientes_seq.NEXTVAL,'laland', 'jaimito', '937844548', '12354465a','1234',1);

INSERT INTO clientes (ID_clientes, direccion_clientes,nombre_cliente, contacto_cliente, dni_cliente, CLI_PASWORD, registrado)
VALUES (ID_clientes_seq.NEXTVAL,'direccliente','cliente1', 'contacto1','12345678A','1234',1);

INSERT INTO productos (ID_productos, nombre_prod, decripcion_prod, stock_prod, precio_prod, id_empleados )
VALUES (id_productos_seq.NEXTVAL,'Mesa de comedor','Mueble para comer en familia', 8, 600, null);

INSERT INTO piezas (ID_piezas, fecha_entrada, nombre_pieza, descripcion_pieza, stock_pieza, ID_productos)
VALUES (id_piezas_seq.NEXTVAL, TO_DATE('2022/01/01', 'yyyy/mm/dd'), 'Pieza1', 'descripcion1', 100, null);

INSERT INTO piezas (ID_piezas, fecha_entrada, nombre_pieza, descripcion_pieza, stock_pieza, ID_productos)
VALUES (id_piezas_seq.NEXTVAL, TO_DATE('2022/02/01', 'yyyy/mm/dd'), 'Pieza2', 'descripcion2', 200, null);

INSERT INTO piezas (ID_piezas, fecha_entrada, nombre_pieza, descripcion_pieza, stock_pieza, ID_productos)
VALUES (id_piezas_seq.NEXTVAL, TO_DATE('2022/03/01', 'yyyy/mm/dd'), 'Pieza3', 'descripcion3', 300, null);


INSERT INTO proveedores_piezas (ID_proveedores, ID_piezas, fecha_esperada)
VALUES ((select ID_proveedores from proveedores where nombre_proveedor = 'Proveedor1'), (select ID_piezas from piezas where nombre_pieza = 'Pieza1'), TO_DATE('2022/02/01', 'yyyy/mm/dd'));


INSERT INTO productos_empleados (ID_productos, ID_empleados)
VALUES ((select ID_productos from productos where nombre_prod = 'Mesa de comedor'),(select ID_empleados from empleados where nombre_empleado = 'Pedro Sanchez'));




/*no necesitaba un procedure pero como se exige...*/
CREATE PROCEDURE mostrar_empleados
AS
BEGIN
  SELECT * FROM ap_Admin.empleados;
END;
