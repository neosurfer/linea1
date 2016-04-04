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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class tercera extends AppCompatActivity {
    Spinner s_estacion;
    private String[] estaciones = new String[]{"Villa El Salvador","Parque Industrial","Pumacahua","Villa Maria","Maria Auxiliadora","San Juan","Atocongo","Jorge Chavez","Ayacucho","Cabitos","San Borja","La Cultura","Nicolas Arriola","Gamarra","Miguel Grau","El Angel","Prebitero Maestro","Caja de Agua","Pirámides del Sol","Los Jardines","Los Postes","San Carlos","San Martin","Santa Rosa","Bayovar"};
    Button btn_guardar,btn_descargar3;
    String valor_estacion="",descripcion="";
    public SQLiteDatabase db;
    EditText et_descripcion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tercera);

        s_estacion = (Spinner)findViewById(R.id.s3_estacion);
        et_descripcion = (EditText)findViewById(R.id.et_descripcion);
        btn_guardar = (Button)findViewById(R.id.btn_guardar);
        btn_descargar3 = (Button)findViewById(R.id.btn_descargar3);

        ArrayAdapter<String> adaptador_estacion = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,estaciones);
        s_estacion.setAdapter(adaptador_estacion);

        ContadorDB conexion = new ContadorDB(this,"DBContadores",null,10);
        db = conexion.getWritableDatabase();


        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valor_estacion  = s_estacion.getSelectedItem().toString();
                descripcion = et_descripcion.getText().toString();
                Log.e("Valores:" , valor_estacion + ' ' + descripcion);
                if(db != null){
                    db.execSQL("INSERT INTO Discapacitados(estacion,descripcion) VALUES ('" + valor_estacion + "','" + descripcion + "')");
                    Toast.makeText(getApplicationContext(),"Se guardo el registro",Toast.LENGTH_SHORT).show();
                    /*
                    Cursor c = db.rawQuery("Select estacion,descripcion,strftime('%d-%m-%Y %H:%M:%S', fecha_registro) FROM Discapacitados WHERE codigo=2", null);
                    if (c.moveToFirst()) {
                        String estacion = c.getString(0);
                        String descripcion = c.getString(1);
                        String fecha = c.getString(2);
                        Log.e("Marca",  estacion + ' ' + descripcion + ' ' +fecha);
                    }else{
                        Log.e("Marca", "no se registro nada");
                    }
                    */
                }
            }
        });

        btn_descargar3.setOnClickListener(new View.OnClickListener() {
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
                        File f = new File(ruta_sd.getAbsolutePath(), "Discapacitados.csv");
                        OutputStreamWriter fout =
                                new OutputStreamWriter(
                                        new FileOutputStream(f,true));

                        Cursor c = db.rawQuery("Select estacion, descripcion, strftime('%d-%m-%Y %H:%M:%S', fecha_registro) FROM Discapacitados ", null);
                        if (c.moveToFirst()) {
                            //Recorremos el cursor hasta que no haya más registros
                            do {
                                String estacion = c.getString(0);
                                String descripcion= c.getString(1);
                                String fecha = c.getString(2);

                                fout.write(estacion + ';' + descripcion + ';' + fecha + '\n');

                                db.execSQL("delete from  Discapacitados");
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
