package salles.cardoso.davi.galeria.model;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends ViewModel {
    //guardando a lista de itens cadastrados em um ArrayList
    List<MyItem> itens = new ArrayList<>();

    //obtendo a lista de itens pelo método
    public List<MyItem> getItens(){
        return itens;
    }
}
