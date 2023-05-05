package salles.cardoso.davi.galeria.model;

import android.net.Uri;

import androidx.lifecycle.ViewModel;

public class NewItemActivityViewModel extends ViewModel {
    //guardando endereço da foto escolhida pelo usuário
    Uri selectPhotoLocation = null;

    //obtendo a lista de itens por um método getter
    public Uri getSelectPhotoLocation(){
        return selectPhotoLocation;
    }

    //seletando o endereço do URI dentro do ViewModel por um setter
    public void setSelectPhotoLocation(Uri selectPhotoLocation){
        this.selectPhotoLocation = selectPhotoLocation;
    }
}
