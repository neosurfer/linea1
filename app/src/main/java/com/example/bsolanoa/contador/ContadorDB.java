package com.example.bsolanoa.contador;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by bsolanoa on 31/03/2016.
 */
public class ContadorDB extends SQLiteOpenHelper {
    String sql = "CREATE TABLE Marcas (codigo INTEGER, nombre TEXT, valor INTEGER)";
    String sql1 = "CREATE TABLE Torniquetes (codigo INTEGER PRIMARY KEY AUTOINCREMENT, usuario TEXT,  terminal TEXT, estacion TEXT, nombre TEXT, valor INTEGER DEFAULT 1,fecha_registro DATETIME DEFAULT CURRENT_TIMESTAMP)";
    String sql2 = "CREATE TABLE Exonerados (codigo INTEGER PRIMARY KEY AUTOINCREMENT, usuario TEXT,  estacion TEXT, nombre TEXT, valor INTEGER DEFAULT 1,fecha_registro DATETIME DEFAULT CURRENT_TIMESTAMP)";
    String sql3 = "CREATE TABLE Discapacitados (codigo INTEGER PRIMARY KEY AUTOINCREMENT, estacion TEXT,  descripcion TEXT, fecha_registro DATETIME DEFAULT CURRENT_TIMESTAMP)";

    public ContadorDB(Context contexto, String nombre, SQLiteDatabase.CursorFactory factory, int version){
        super(contexto,nombre,factory,version);
    }
    public void onCreate(SQLiteDatabase db){
        db.execSQL(sql);
        db.execSQL(sql1);
        db.execSQL(sql2);
        db.execSQL(sql3);
        //Ejecutamos las cargas iniciales
        db.execSQL("INSERT INTO Marcas (codigo, nombre, valor) VALUES (1,'Adulto',0)");
        db.execSQL("INSERT INTO Marcas (codigo, nombre, valor) VALUES (2,'Universitario',0)");
        db.execSQL("INSERT INTO Marcas (codigo, nombre, valor) VALUES (3,'Escolar',0)");
        db.execSQL("INSERT INTO Marcas (codigo, nombre, valor) VALUES (4,'Instituto',0)");

        db.execSQL("INSERT INTO Marcas (codigo, nombre, valor) VALUES (5,'PNP',0)");
        db.execSQL("INSERT INTO Marcas (codigo, nombre, valor) VALUES (6,'CBP',0)");
        db.execSQL("INSERT INTO Marcas (codigo, nombre, valor) VALUES (7,'CONCAR',0)");
        db.execSQL("INSERT INTO Marcas (codigo, nombre, valor) VALUES (8,'ALTON',0)");
        db.execSQL("INSERT INTO Marcas (codigo, nombre, valor) VALUES (9,'BOXER',0)");
        db.execSQL("INSERT INTO Marcas (codigo, nombre, valor) VALUES (10,'EULEN',0)");
        db.execSQL("INSERT INTO Marcas (codigo, nombre, valor) VALUES (11,'AATE',0)");
        db.execSQL("INSERT INTO Marcas (codigo, nombre, valor) VALUES (12,'LINEA 1',0)");
        db.execSQL("INSERT INTO Marcas (codigo, nombre, valor) VALUES (13,'TIHISINCUR',0)");
        db.execSQL("INSERT INTO Marcas (codigo, nombre, valor) VALUES (14,'OTROS',0)");


    }
    public void onUpgrade(SQLiteDatabase db, int anterior,int nueva){
        db.execSQL("DROP TABLE IF EXISTS Marcas");
        db.execSQL("DROP TABLE IF EXISTS Torniquetes");
        db.execSQL("DROP TABLE IF EXISTS Exonerados");
        db.execSQL("DROP TABLE IF EXISTS Discapacitados");
        //Se crea la nueva versión de la tabla
        db.execSQL(sql);
        db.execSQL(sql1);
        db.execSQL(sql2);
        db.execSQL(sql3);

        db.execSQL("INSERT INTO Marcas (codigo, nombre, valor) VALUES (1,'Adulto',0)");
        db.execSQL("INSERT INTO Marcas (codigo, nombre, valor) VALUES (2,'Universitario',0)");
        db.execSQL("INSERT INTO Marcas (codigo, nombre, valor) VALUES (3,'Escolar',0)");
        db.execSQL("INSERT INTO Marcas (codigo, nombre, valor) VALUES (4,'Instituto',0)");

        db.execSQL("INSERT INTO Marcas (codigo, nombre, valor) VALUES (5,'PNP',0)");
        db.execSQL("INSERT INTO Marcas (codigo, nombre, valor) VALUES (6,'CBP',0)");
        db.execSQL("INSERT INTO Marcas (codigo, nombre, valor) VALUES (7,'CONCAR',0)");
        db.execSQL("INSERT INTO Marcas (codigo, nombre, valor) VALUES (8,'ALTON',0)");
        db.execSQL("INSERT INTO Marcas (codigo, nombre, valor) VALUES (9,'BOXER',0)");
        db.execSQL("INSERT INTO Marcas (codigo, nombre, valor) VALUES (10,'EULEN',0)");
        db.execSQL("INSERT INTO Marcas (codigo, nombre, valor) VALUES (11,'AATE',0)");
        db.execSQL("INSERT INTO Marcas (codigo, nombre, valor) VALUES (12,'LINEA 1',0)");
        db.execSQL("INSERT INTO Marcas (codigo, nombre, valor) VALUES (13,'TIHISINCUR',0)");
        db.execSQL("INSERT INTO Marcas (codigo, nombre, valor) VALUES (14,'OTROS',0)");
    }
}
