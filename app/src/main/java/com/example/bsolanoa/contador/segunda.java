package com.example.bsolanoa.contador;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class segunda extends AppCompatActivity {
    public SQLiteDatabase db;
    Spinner s2_usuario, s2_estacion;
    String valor_usuario = "", valor_estacion="";
    Button btn_pnp, btn_cbp,btn_concar, btn_alton, btn_boxer, btn_eulen, btn_aate, btn_linea1, btn_tihisincur, btn_otros,btn_descargar2;
    private String[] usuarios = new String[]{"Maritza Diaz Donaire","Geovana Córdova Damián","Olga Arizola Esquivel","Manuel Milla Chacón","Blanca Román Diaz","Victoria Ávila Damián","Silvana Espinoza Garay"};
    private String[] terminales = new String[]{"Torniquete 1","Torniquete 2","Torniquete 3","Torniquete 4","Torniquete 5","Torniquete 6","Torniquete 7","Torniquete 8","Torniquete 9","Torniquete 10","Torniquete 11","Torniquete 12","Torniquete 13"};
    private String[] estaciones = new String[]{"Villa El Salvador","Parque Industrial","Pumacahua","Villa Maria","Maria Auxiliadora","San Juan","Atocongo","Jorge Chavez","Ayacucho","Cabitos","San Borja","La Cultura","Nicolas Arriola","Gamarra","Miguel Grau","El Angel","Prebitero Maestro","Caja de Agua","Pirámides del Sol","Los Jardines","Los Postes","San Carlos","San Martin","Santa Rosa","Bayovar"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

        s2_usuario = (Spinner)findViewById(R.id.s2_usuario);
        s2_estacion = (Spinner)findViewById(R.id.s2_estacion);

        btn_pnp         = (Button)findViewById(R.id.btn_pnp);
        btn_cbp         = (Button)findViewById(R.id.btn_cbp);
        btn_concar      = (Button)findViewById(R.id.btn_concar);
        btn_alton       = (Button)findViewById(R.id.btn_alton);
        btn_boxer       = (Button)findViewById(R.id.btn_boxer);
        btn_eulen       = (Button)findViewById(R.id.btn_eulen);
        btn_aate        = (Button)findViewById(R.id.btn_aate);
        btn_linea1      = (Button)findViewById(R.id.btn_linea1);
        btn_tihisincur  = (Button)findViewById(R.id.btn_tihisincur);
        btn_otros       = (Button)findViewById(R.id.btn_otros);
        btn_descargar2   = (Button)findViewById(R.id.btn_descargar2);

        ArrayAdapter<String> adaptador_usuario2 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,usuarios);
        ArrayAdapter<String> adaptador_estacion2 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,estaciones);

        s2_usuario.setAdapter(adaptador_usuario2);
        s2_estacion.setAdapter(adaptador_estacion2);

        ContadorDB conexion = new ContadorDB(this,"DBContadores",null,10);
        db = conexion.getWritableDatabase();

        btn_pnp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valor_usuario   = s2_usuario.getSelectedItem().toString();
                valor_estacion  = s2_estacion.getSelectedItem().toString();
                if(db != null){
                    //OBTENEMOS EL VALOR DEACUERDO AL BOTON SELECCIONADO
                    Cursor c = db.rawQuery("Select valor FROM Marcas WHERE codigo=5", null);
                    if (c.moveToFirst()) {
                        int valor = c.getInt(0);
                        valor = valor + 1;
                        db.execSQL("INSERT INTO Exonerados(usuario,estacion,nombre) VALUES ('" + valor_usuario + "','" + valor_estacion +"','PNP')");
                        db.execSQL("UPDATE Marcas SET valor= " + valor + " WHERE codigo=5 ");

                        c = db.rawQuery("Select usuario,estacion,nombre,strftime('%d-%m-%Y %H:%M:%S', fecha_registro) FROM Exonerados WHERE codigo=5", null);
                        if (c.moveToFirst()) {
                            String usuario = c.getString(0);
                            String estacion = c.getString(1);
                            String fecha = c.getString(3);
                            Log.e("Marca", usuario +  ' ' + estacion + ' ' + fecha);
                        }else{
                            Log.e("Marca", "no se registro nada");
                        }

                        Toast.makeText(getApplicationContext(), "" + valor + "", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"NO hay Registros",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Error en la conexion",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_cbp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valor_usuario   = s2_usuario.getSelectedItem().toString();
                valor_estacion  = s2_estacion.getSelectedItem().toString();
                if(db != null){
                    //OBTENEMOS EL VALOR DEACUERDO AL BOTON SELECCIONADO
                    Cursor c = db.rawQuery("Select valor FROM Marcas WHERE codigo=6", null);
                    if (c.moveToFirst()) {
                        int valor = c.getInt(0);
                        valor = valor + 1;
                        db.execSQL("INSERT INTO Exonerados(usuario,estacion,nombre) VALUES ('" + valor_usuario + "','" + valor_estacion +"','CBP')");
                        db.execSQL("UPDATE Marcas SET valor= " + valor + " WHERE codigo=6 ");
                        Toast.makeText(getApplicationContext(), "" + valor + "", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"NO hay Registros",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Error en la conexion",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_concar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valor_usuario   = s2_usuario.getSelectedItem().toString();
                valor_estacion  = s2_estacion.getSelectedItem().toString();
                if(db != null){
                    //OBTENEMOS EL VALOR DEACUERDO AL BOTON SELECCIONADO
                    Cursor c = db.rawQuery("Select valor FROM Marcas WHERE codigo=7", null);
                    if (c.moveToFirst()) {
                        int valor = c.getInt(0);
                        valor = valor + 1;
                        db.execSQL("INSERT INTO Exonerados(usuario,estacion,nombre) VALUES ('" + valor_usuario + "','" + valor_estacion +"','CONCAR')");
                        db.execSQL("UPDATE Marcas SET valor= " + valor + " WHERE codigo=7 ");
                        Toast.makeText(getApplicationContext(), "" + valor + "", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"NO hay Registros",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Error en la conexion",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_alton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valor_usuario   = s2_usuario.getSelectedItem().toString();
                valor_estacion  = s2_estacion.getSelectedItem().toString();
                if(db != null){
                    //OBTENEMOS EL VALOR DEACUERDO AL BOTON SELECCIONADO
                    Cursor c = db.rawQuery("Select valor FROM Marcas WHERE codigo=8", null);
                    if (c.moveToFirst()) {
                        int valor = c.getInt(0);
                        valor = valor + 1;
                        db.execSQL("INSERT INTO Exonerados(usuario,estacion,nombre) VALUES ('" + valor_usuario + "','" + valor_estacion +"','ALTON')");
                        db.execSQL("UPDATE Marcas SET valor= " + valor + " WHERE codigo=8 ");
                        Toast.makeText(getApplicationContext(), "" + valor + "", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"NO hay Registros",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Error en la conexion",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_boxer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valor_usuario   = s2_usuario.getSelectedItem().toString();
                valor_estacion  = s2_estacion.getSelectedItem().toString();
                if(db != null){
                    //OBTENEMOS EL VALOR DEACUERDO AL BOTON SELECCIONADO
                    Cursor c = db.rawQuery("Select valor FROM Marcas WHERE codigo=9", null);
                    if (c.moveToFirst()) {
                        int valor = c.getInt(0);
                        valor = valor + 1;
                        db.execSQL("INSERT INTO Exonerados(usuario,estacion,nombre) VALUES ('" + valor_usuario + "','" + valor_estacion +"','BOXER')");
                        db.execSQL("UPDATE Marcas SET valor= " + valor + " WHERE codigo=9 ");
                        Toast.makeText(getApplicationContext(), "" + valor + "", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"NO hay Registros",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Error en la conexion",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_eulen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valor_usuario   = s2_usuario.getSelectedItem().toString();
                valor_estacion  = s2_estacion.getSelectedItem().toString();
                if(db != null){
                    //OBTENEMOS EL VALOR DEACUERDO AL BOTON SELECCIONADO
                    Cursor c = db.rawQuery("Select valor FROM Marcas WHERE codigo=10", null);
                    if (c.moveToFirst()) {
                        int valor = c.getInt(0);
                        valor = valor + 1;
                        db.execSQL("INSERT INTO Exonerados(usuario,estacion,nombre) VALUES ('" + valor_usuario + "','" + valor_estacion +"','EULEN')");
                        db.execSQL("UPDATE Marcas SET valor= " + valor + " WHERE codigo=10 ");
                        Toast.makeText(getApplicationContext(), "" + valor + "", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"NO hay Registros",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Error en la conexion",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_aate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valor_usuario   = s2_usuario.getSelectedItem().toString();
                valor_estacion  = s2_estacion.getSelectedItem().toString();
                if(db != null){
                    //OBTENEMOS EL VALOR DEACUERDO AL BOTON SELECCIONADO
                    Cursor c = db.rawQuery("Select valor FROM Marcas WHERE codigo=11", null);
                    if (c.moveToFirst()) {
                        int valor = c.getInt(0);
                        valor = valor + 1;
                        db.execSQL("INSERT INTO Exonerados(usuario,estacion,nombre) VALUES ('" + valor_usuario + "','" + valor_estacion +"','AATE')");
                        db.execSQL("UPDATE Marcas SET valor= " + valor + " WHERE codigo=11 ");
                        Toast.makeText(getApplicationContext(), "" + valor + "", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"NO hay Registros",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Error en la conexion",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_linea1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valor_usuario   = s2_usuario.getSelectedItem().toString();
                valor_estacion  = s2_estacion.getSelectedItem().toString();
                if(db != null){
                    //OBTENEMOS EL VALOR DEACUERDO AL BOTON SELECCIONADO
                    Cursor c = db.rawQuery("Select valor FROM Marcas WHERE codigo=12", null);
                    if (c.moveToFirst()) {
                        int valor = c.getInt(0);
                        valor = valor + 1;
                        db.execSQL("INSERT INTO Exonerados(usuario,estacion,nombre) VALUES ('" + valor_usuario + "','" + valor_estacion +"','LINEA 1')");
                        db.execSQL("UPDATE Marcas SET valor= " + valor + " WHERE codigo=12 ");
                        Toast.makeText(getApplicationContext(), "" + valor + "", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"NO hay Registros",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Error en la conexion",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_tihisincur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valor_usuario   = s2_usuario.getSelectedItem().toString();
                valor_estacion  = s2_estacion.getSelectedItem().toString();
                if(db != null){
                    //OBTENEMOS EL VALOR DEACUERDO AL BOTON SELECCIONADO
                    Cursor c = db.rawQuery("Select valor FROM Marcas WHERE codigo=13", null);
                    if (c.moveToFirst()) {
                        int valor = c.getInt(0);
                        valor = valor + 1;
                        db.execSQL("INSERT INTO Exonerados(usuario,estacion,nombre) VALUES ('" + valor_usuario + "','" + valor_estacion +"','TIHISINCUR')");
                        db.execSQL("UPDATE Marcas SET valor= " + valor + " WHERE codigo=13 ");
                        Toast.makeText(getApplicationContext(), "" + valor + "", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"NO hay Registros",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Error en la conexion",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_otros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valor_usuario   = s2_usuario.getSelectedItem().toString();
                valor_estacion  = s2_estacion.getSelectedItem().toString();
                if(db != null){
                    //OBTENEMOS EL VALOR DEACUERDO AL BOTON SELECCIONADO
                    Cursor c = db.rawQuery("Select valor FROM Marcas WHERE codigo=14", null);
                    if (c.moveToFirst()) {
                        int valor = c.getInt(0);
                        valor = valor + 1;
                        db.execSQL("INSERT INTO Exonerados(usuario,estacion,nombre) VALUES ('" + valor_usuario + "','" + valor_estacion +"','OTROS')");
                        db.execSQL("UPDATE Marcas SET valor= " + valor + " WHERE codigo=14 ");
                        Toast.makeText(getApplicationContext(), "" + valor + "", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"NO hay Registros",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Error en la conexion",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_descargar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(getApplicationContext(),"Click en el boton",Toast.LENGTH_SHORT).show();
                boolean sdDisponible = false;
                boolean sdAccesoEscritura = false;

                //Comprobamos el estado de la memoria externa (tarjeta SD)
                String estado = Environment.getExternalStorageState();

                if (estado.equals(Environment.MEDIA_MOUNTED)){
                    sdDisponible = true;
                    sdAccesoEscritura = true;
                }
                else if (estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
                    sdDisponible = true;
                    sdAccesoEscritura = false;
                }
                else{
                    sdDisponible = false;
                    sdAccesoEscritura = false;
                }

                if(sdDisponible){
                    try
                    {
                        File ruta_sd = Environment.getExternalStorageDirectory();
                        File f = new File(ruta_sd.getAbsolutePath(), "Exonerados.csv");
                        OutputStreamWriter fout =
                                new OutputStreamWriter(
                                        new FileOutputStream(f,true));

                        Cursor c = db.rawQuery("Select usuario,estacion,nombre, valor,strftime('%d-%m-%Y %H:%M:%S', fecha_registro) FROM Exonerados ", null);
                        if (c.moveToFirst()) {
                            //Recorremos el cursor hasta que no haya más registros
                            do {
                                String usuario= c.getString(0);
                                String estacion = c.getString(1);
                                String nombre = c.getString(2);
                                String valor = c.getString(3);
                                String fecha = c.getString(4);

                                fout.write(usuario + ';' +  estacion + ';' + nombre + ';' + valor + ';' + fecha + '\n');

                                db.execSQL("delete from  Exonerados");
                                db.execSQL("UPDATE Marcas SET valor= 0 WHERE codigo in (5,6,7,8,9,10,11,12,13,14) ");

                                Toast.makeText(getApplicationContext(),"Se guardo documento",Toast.LENGTH_SHORT).show();
                            } while(c.moveToNext());
                        }else{
                            Toast.makeText(getApplicationContext(),"No se encontraron registros",Toast.LENGTH_SHORT).show();
                        }
                        fout.close();


                    }
                    catch (Exception ex){
                        Toast.makeText(getApplicationContext(),"Error al descargar",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Error: No se encontro tarjeta micro SD",Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_primera) {
            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        }
        if (id == R.id.menu_segunda) {
            Intent i = new Intent(getApplicationContext(),segunda.class);
            startActivity(i);
        }
        if (id == R.id.menu_tercera) {
            Intent i = new Intent(getApplicationContext(),tercera.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}
