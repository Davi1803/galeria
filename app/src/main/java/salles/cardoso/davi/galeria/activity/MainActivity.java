package salles.cardoso.davi.galeria.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import salles.cardoso.davi.galeria.R;
import salles.cardoso.davi.galeria.adapter.MyAdapter;
import salles.cardoso.davi.galeria.model.MyItem;
import salles.cardoso.davi.galeria.util.Util;

public class MainActivity extends AppCompatActivity {

    static int NEW_ITEM_REQUEST =1;
    List<MyItem> itens = new ArrayList<>();
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //obtendo botão FAB
        FloatingActionButton fabAddItem = findViewById(R.id.fabAddNewItem);

        //Obtendo um recycleView
        RecyclerView rvItens = findViewById(R.id.rvItens);

        //Criando um myAdapter
        myAdapter = new MyAdapter(this,itens);
        //Setando adapter no RecycleView
        rvItens.setAdapter(myAdapter);

        //Indicando ao RecycleView que não há variação de tamanho entre os itens da lista
        rvItens.setHasFixedSize(true);

        //Criando um gerenciador de layout do tipo linear
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        //Setando o gerenciador no RecycleView
        rvItens.setLayoutManager(layoutManager);

        //Criando um divisor de linhas para decorar a lista
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvItens.getContext(), DividerItemDecoration.VERTICAL);
        //Setando o divisor no RecycleView
        rvItens.addItemDecoration(dividerItemDecoration);

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
                Uri selectedPhotoURI = data.getData();

                //Utilizando um try-catch para verificar se a imagem foi encontrada
                try{
                    //Utilizando função do Uri para carregar uma imagem e guardá-lo em um Bitmap
                    Bitmap photo = Util.getBitmap(MainActivity.this, selectedPhotoURI, 100, 100);
                    //Guardando o Bitmap da imagem dentro de um objeto do tipo MyItem
                    myItem.photo = photo;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                //adicionando o item "myItem" em uma lista de itens (itens) já definida como atributo na classe
                itens.add(myItem);
                //Notificando o Adapter para que seja mostrado no RecycleView
                myAdapter.notifyItemInserted(itens.size()-1);
            }
        }
    }

}