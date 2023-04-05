package salles.cardoso.davi.galeria.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import salles.cardoso.davi.galeria.R;

public class MainActivity extends AppCompatActivity {

    static int NEW_ITEM_REQUEST =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //obtendo botão FAB
        FloatingActionButton fabAddItem = findViewById(R.id.fabAddNewItem);
        //registrando um ouvidor de cliques
        fabAddItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //navegando para NewActivity através de um intent
                Intent i = new Intent(MainActivity.this,NewItemActivity.class);
                //executando o intent a partir do método startActivityForResult
                startActivityForResult(i, NEW_ITEM_REQUEST);
            }
        });
    }
}