package com.example.decalv4.ui.productos;

import android.app.Notification;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.decalv4.FormActivity;
import com.example.decalv4.R;
import com.example.decalv4.adaptadores.ProductoAdapter;
import com.example.decalv4.casos_uso.CasoUsoProducto;
import com.example.decalv4.databinding.FragmentProductosBinding;
import com.example.decalv4.databinding.FragmentSucursalesBinding;
import com.example.decalv4.datos.ApiOracle;
import com.example.decalv4.datos.DBHelper;
import com.example.decalv4.modelo.Producto;

import java.util.ArrayList;


public class ProductosFragment extends Fragment {

    private String TABLE_NAME = "PRODUCTOS";
    private CasoUsoProducto casoUsoProducto;
    private GridView gridView;
    private ProgressBar progressBar;
    private DBHelper dbHelper;
    private ApiOracle apiOracle;
    private ArrayList<Producto> productos;
    private final static String CHANNEL_ID = "NOTIFICACION";
    private final static int NOTIFICATION_ID = 0;


    public void createNotification(String titulo, String contenido){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.decal_logo)
                .setContentTitle(titulo)
                .setContentText(contenido)
                .setDefaults(NotificationCompat.PRIORITY_DEFAULT)
                .setColor(Color.BLUE)
                .setLights(Color.MAGENTA, 500, 500)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000, 1000, 1000})
                .setDefaults(Notification.DEFAULT_SOUND);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getActivity().getApplicationContext());
        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());


    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_productos, container,false);
        try{

            //casoUsoProducto = new CasoUsoProducto();
            //apiOracle = new ApiOracle(root.getContext());
            //gridView = (GridView) root.findViewById(R.id.gridProductos);
            //progressBar = (ProgressBar) root.findViewById(R.id.progressBarSuc);
            //ApiOracle.getAllProductos(gridView, progressBar);


            casoUsoProducto = new CasoUsoProducto();
            dbHelper = new DBHelper(getContext());
            Cursor cursor = dbHelper.getData(TABLE_NAME);
            productos = casoUsoProducto.llenarListaProductos(cursor);
            gridView = (GridView) root.findViewById(R.id.gridProductos);
            ProductoAdapter productoAdapter = new ProductoAdapter(root.getContext(), productos);
            gridView.setAdapter(productoAdapter);


        }catch (Exception e){
            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                Intent intent = new Intent(getContext(), FormActivity.class);
                intent.putExtra("name","PRODUCTOS");
                getActivity().startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
