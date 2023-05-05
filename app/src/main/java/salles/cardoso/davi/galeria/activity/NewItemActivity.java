package salles.cardoso.davi.galeria.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import org.jetbrains.annotations.Nullable;

import salles.cardoso.davi.galeria.R;
import salles.cardoso.davi.galeria.model.NewItemActivityViewModel;

public class NewItemActivity extends AppCompatActivity {

    static int PHOTO_PICKER_REQUEST = 1;
    //guardando o endereço da foto selecionada em um atributo do tipo Uri

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        //Obtendo o ViewModel de NewItemActivityViewModel
        NewItemActivityViewModel vm = new ViewModelProvider( this ).get(NewItemActivityViewModel.class);

        //Obtendo o endereço URI guardado dentro do ViewModel
        Uri selectPhotoLocation = vm.getSelectPhotoLocation();
        //Caso o endereço seja nulo (usuário escolheu a imagem antes de rotacionar a tela)...
        if(selectPhotoLocation != null){
            //Setando a imagem na tela após a condição
            ImageView imvfotoPreview = findViewById(R.id.imvfotoPreview);
            imvfotoPreview.setImageURI(selectPhotoLocation);
        }


        ImageButton imgCl = findViewById(R.id.imbCl);

        //definindo um ouvidor de cliques para o ImageButton
        imgCl.setOnClickListener(new View.OnClickListener(){
            //criando método para ser executado após o clique
            public void onClick(View V) {
                //criando um Intent implícito com ACTION, para abrir o documento
                Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                //setando o intent criando apenas em arquivos do tipo "image"
                photoPickerIntent.setType("image/*");
                //exectudando o Intent através do método startActivityForResult, e entregando a imagem selecionada (PHOTO_PICKER_REQUEST)
                startActivityForResult(photoPickerIntent, PHOTO_PICKER_REQUEST);
            }

        });
        Button btnAddItem = findViewById(R.id.btnAddItem);

        btnAddItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                //Obtendo o ViewModel de NewItemActivityViewModel
                NewItemActivityViewModel vm = new ViewModelProvider( NewItemActivity.this).get(NewItemActivityViewModel.class);
                //Obtendo o endereço URI guardado dentro do ViewModel
                Uri selectPhotoLocation = vm.getSelectPhotoLocation();


                if (selectPhotoLocation == null) {
                    Toast.makeText(NewItemActivity.this, "É necessário selecionar uma imagem!", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText etTitle = findViewById(R.id.etTitle);
                String title = etTitle.getText().toString();
                if (title.isEmpty()) {
                    Toast.makeText(NewItemActivity.this, "É necessário inserir um título", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText etDesc = findViewById(R.id.etDesc);
                String description = etDesc.getText().toString();
                if (description.isEmpty()) {
                    Toast.makeText(NewItemActivity.this, "É necessário inserir uma descrição", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent i = new Intent();
                i.setData(selectPhotoLocation);
                i.putExtra("title", title);
                i.putExtra("description", description);
                setResult(Activity.RESULT_OK, i);
                finish();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        //chamando a superclasse de onActivity com 3 parâmetros (requestCode, resultCode, data)
        NewItemActivity.super.onActivityResult(requestCode, resultCode, data);
        //veriicando se a chamada (requestCode) é referente a fornecida pela StartActivity com id de PHOTO_PICKER_REQUEST
        if(requestCode == PHOTO_PICKER_REQUEST){
            //verificando se a chamada foi bem sucedida (resultCode)
            if(resultCode == Activity.RESULT_OK);{
                //obtendo o Uri da imagem escolhida e guardando em um atributo (photoSelected)
                 Uri photoSelected = data.getData();
                //obtendo o imageView
                ImageView imvfotoPreview = findViewById(R.id.imvfotoPreview);
                //setando o Uri
                imvfotoPreview.setImageURI(photoSelected);

                //Obtendo o ViewModel de NewItemActivityViewModel
                NewItemActivityViewModel vm = new ViewModelProvider(this).get(NewItemActivityViewModel.class);
                vm.setSelectPhotoLocation(photoSelected);

            }
        }
    }


}