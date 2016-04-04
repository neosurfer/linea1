package com.example.bsolanoa.contador;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

public class MainActivity extends AppCompatActivity {
    Spinner s_usuario,s_terminal, s_estacion;
    Button btn_adulto,btn_universitario, btn_escolar, btn_instituto, btn_descargar1;
    public SQLiteDatabase db;
    String valor_usuario  = "";
    String valor_terminal = "";
    String valor_estacion = "";
    private String[] usuarios = new String[]{"Maritza Diaz Donaire","Geovana Córdova Damián","Olga Arizola Esquivel","Manuel Milla Chacón","Blanca Román Diaz","Victoria Ávila Damián","Silvana Espinoza Garay"};
    private String[] terminales = new String[]{"Torniquete 1","Torniquete 2","Torniquete 3","Torniquete 4","Torniquete 5","Torniquete 6","Torniquete 7","Torniquete 8","Torniquete 9","Torniquete 10","Torniquete 11","Torniquete 12","Torniquete 13"};
    private String[] estaciones = new String[]{"Villa El Salvador","Parque Industrial","Pumacahua","Villa Maria","Maria Auxiliadora","San Juan","Atocongo","Jorge Chavez","Ayacucho","Cabitos","San Borja","La Cultura","Nicolas Arriola","Gamarra","Miguel Grau","El Angel","Prebitero Maestro","Caja de Agua","Pirámides del Sol","Los Jardines","Los Postes","San Carlos","San Martin","Santa Rosa","Bayovar"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        s_usuario = (Spinner)findViewById(R.id.s_usuario);
        s_terminal = (Spinner)findViewById(R.id.s_terminal);
        s_estacion = (Spinner)findViewById(R.id.s_estacion);

        btn_adulto = (Button)findViewById(R.id.btn_adulto);
        btn_universitario = (Button)findViewById(R.id.btn_universitario);
        btn_escolar = (Button)findViewById(R.id.btn_escolar);
        btn_instituto = (Button)findViewById(R.id.btn_instituto);
        btn_descargar1 = (Button)findViewById(R.id.btn_descargar1);

        ArrayAdapter<String> adaptador_usuario = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,usuarios);
        ArrayAdapter<String> adaptador_terminal = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,terminales);
        ArrayAdapter<String> adaptador_estacion = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,estaciones);

        s_usuario.setAdapter(adaptador_usuario);
        s_terminal.setAdapter(adaptador_terminal);
        s_estacion.setAdapter(adaptador_estacion);

        ContadorDB conexion = new ContadorDB(this,"DBContadores",null,10);
        db = conexion.getWritableDatabase();



        btn_adulto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(db != null){
                    //OBTENEMOS EL VALOR DEACUERDO AL BOTON SELECCIONADO
                    valor_usuario   = s_usuario.getSelectedItem().toString();
                    valor_terminal  = s_terminal.getSelectedItem().toString();
                    valor_estacion  = s_estacion.getSelectedItem().toString();

                    Cursor c = db.rawQuery("Select valor FROM Marcas WHERE codigo=1", null);
                    if (c.moveToFirst()) {
                        int valor = c.getInt(0);
                        valor = valor + 1;
                        db.execSQL("UPDATE Marcas SET valor= " + valor + " WHERE codigo=1 ");
                        db.execSQL("INSERT INTO Torniquetes(usuario,terminal,estacion,nombre) VALUES ('" + valor_usuario + "','" + valor_terminal + "','" + valor_estacion +"','ADULTO')");

                        /*
                        c = db.rawQuery("Select usuario,terminal,estacion,nombre,strftime('%d-%m-%Y %H:%M:%S', fecha_registro) FROM Torniquetes WHERE codigo=3", null);
                        if (c.moveToFirst()) {
                            String usuario = c.getString(0);
                            String terminal = c.getString(1);
                            String estacion = c.getString(2);
                            String fecha = c.getString(4);
                            Log.e("Marca", usuario + ' ' + terminal + ' ' + ' ' + estacion + ' ' + fecha);
                        }else{
                            Log.e("Marca", "no se registro nada");
                        }
                        */
                        Toast.makeText(getApplicationContext(),""+  valor + "" ,Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"NO hay Registros",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Error en la conexion",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_universitario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(db != null){
                    //OBTENEMOS EL VALOR DEACUERDO AL BOTON SELECCIONADO
                    valor_usuario   = s_usuario.getSelectedItem().toString();
                    valor_terminal  = s_terminal.getSelectedItem().toString();
                    valor_estacion  = s_estacion.getSelectedItem().toString();
                    Cursor c = db.rawQuery("Select valor FROM Marcas WHERE codigo=2", null);
                    if (c.moveToFirst()) {
                        int valor = c.getInt(0);
                        valor = valor + 1;
                        db.execSQL("INSERT INTO Torniquetes(usuario,terminal,estacion,nombre) VALUES ('" + valor_usuario + "','" + valor_terminal + "','" + valor_estacion +"','UNIVERSITARIO')");
                        db.execSQL("UPDATE Marcas SET valor= " + valor + " WHERE codigo=2 ");
                        Toast.makeText(getApplicationContext(),""+  valor + "" ,Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"NO hay Registros",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Error en la conexion",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_escolar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(db != null){
                    //OBTENEMOS EL VALOR DEACUERDO AL BOTON SELECCIONADO
                    valor_usuario   = s_usuario.getSelectedItem().toString();
                    valor_terminal  = s_terminal.getSelectedItem().toString();
                    valor_estacion  = s_estacion.getSelectedItem().toString();
                    Cursor c = db.rawQuery("Select valor FROM Marcas WHERE codigo=3", null);
                    if (c.moveToFirst()) {
                        int valor = c.getInt(0);
                        valor = valor + 1;
                        db.execSQL("INSERT INTO Torniquetes(usuario,terminal,estacion,nombre) VALUES ('" + valor_usuario + "','" + valor_terminal + "','" + valor_estacion +"','ESCOLAR')");
                        db.execSQL("UPDATE Marcas SET valor= " + valor + " WHERE codigo=3 ");
                        Toast.makeText(getApplicationContext(),""+  valor + "" ,Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"NO hay Registros",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Error en la conexion",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_instituto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(db != null){
                    //OBTENEMOS EL VALOR DEACUERDO AL BOTON SELECCIONADO
                    valor_usuario   = s_usuario.getSelectedItem().toString();
                    valor_terminal  = s_terminal.getSelectedItem().toString();
                    valor_estacion  = s_estacion.getSelectedItem().toString();
                    Cursor c = db.rawQuery("Select valor FROM Marcas WHERE codigo=4", null);
                    if (c.moveToFirst()) {
                        int valor = c.getInt(0);
                        valor = valor + 1;
                        db.execSQL("INSERT INTO Torniquetes(usuario,terminal,estacion,nombre) VALUES ('" + valor_usuario + "','" + valor_terminal + "','" + valor_estacion +"','INSTITUTO')");
                        db.execSQL("UPDATE Marcas SET valor= " + valor + " WHERE codigo=4 ");
                        Toast.makeText(getApplicationContext(),""+  valor + "" ,Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"NO hay Registros",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Error en la conexion",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_descargar1.setOnClickListener(new View.OnClickListener() {
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
                        File f = new File(ruta_sd.getAbsolutePath(), "torniquetes.csv");
                        OutputStreamWriter fout =
                                new OutputStreamWriter(
                                        new FileOutputStream(f,true));

                        Cursor c = db.rawQuery("Select usuario,terminal,estacion,nombre, valor,strftime('%d-%m-%Y %H:%M:%S', fecha_registro) FROM Torniquetes ", null);
                        if (c.moveToFirst()) {
                            //Recorremos el cursor hasta que no haya más registros
                            do {
                                String usuario= c.getString(0);
                                String terminal = c.getString(1);
                                String estacion = c.getString(2);
                                String nombre = c.getString(3);
                                String valor = c.getString(4);
                                String fecha = c.getString(5);

                                fout.write(usuario + ';' + terminal +  ';' + estacion + ';' + nombre + ';' + valor + ';' + fecha + '\n');

                                db.execSQL("delete from  Torniquetes");
                                db.execSQL("UPDATE Marcas SET valor= 0 WHERE codigo in (1,2,3,4) ");

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

    @Override
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
