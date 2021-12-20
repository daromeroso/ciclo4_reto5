package com.example.decalv4.ui.servicios;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import com.example.decalv4.databinding.FragmentServiciosBinding;

public class ServiciosFragment extends Fragment {

    private FragmentServiciosBinding binding;
    private Button botonser1, botonser2, botonser3;

    public void createDialog(String titulo, String contenido){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(titulo);
        builder.setMessage(contenido)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "En breve uno de nuestros asesores le llamará", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "Estamos a su servicio", Toast.LENGTH_SHORT).show();
                    }
                }).show();

    }


    public ServiciosFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentServiciosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        botonser1 = (Button) binding.botonser1;
        botonser1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(), "Gracias por contactarnos", Toast.LENGTH_LONG ).show();
                createDialog("Mayor información", "¿Desea recibir atención personalizada?");
            }
        });

        botonser2 = (Button) binding.botonser2;
        botonser2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(), "Gracias por contactarnos", Toast.LENGTH_LONG ).show();
                createDialog("Mayor información", "¿Desea recibir atención personalizada?");
            }
        });

        botonser3 = (Button) binding.botonser3;
        botonser3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Gracias por contactarnos", Toast.LENGTH_LONG ).show();
            }
        });


        return root;
    }
}