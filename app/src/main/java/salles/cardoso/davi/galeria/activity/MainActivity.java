package salles.cardoso.davi.galeria.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import salles.cardoso.davi.galeria.R;
import salles.cardoso.davi.galeria.model.MyItem;

public class MainActivity extends AppCompatActivity {

    static int NEW_ITEM_REQUEST =1;
    List<MyItem> itens = new ArrayList<>();

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode,data);
        //verificando se as variáveis atendem as condições das imagens
        if(requestCode == NEW_ITEM_REQUEST){
            if(resultCode == Activity.RESULT_OK){
                //criando uma instância de MyItem para guardar os dados do item
                MyItem myItem = new MyItem();
                //obtendo os dados de retorno e guardando em "myItem"
                myItem.title = data.getStringExtra("title");
                myItem.description = data.getStringExtra("description");
                myItem.photo = data.getData();

                //adicionando o item "myItem" em uma lista de itens (itens) já definida como atributo na classe
                itens.add(myItem);
            }
        }
    }
}