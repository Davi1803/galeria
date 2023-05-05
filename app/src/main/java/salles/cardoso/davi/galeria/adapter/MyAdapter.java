package salles.cardoso.davi.galeria.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import salles.cardoso.davi.galeria.R;
import salles.cardoso.davi.galeria.activity.MainActivity;
import salles.cardoso.davi.galeria.model.MyItem;

public class MyAdapter  extends RecyclerView.Adapter{

    MainActivity mainActivity;
    List<MyItem> itens;

    public MyAdapter(MainActivity mainActivity, List<MyItem> itens){

        this.mainActivity = mainActivity;
        this.itens = itens;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        //Obtendo inflador de layouts
        LayoutInflater inflater = LayoutInflater.from(mainActivity);
        //Utilizando o inflater para criar os elementos de interface e os guardando em uma View
        View v = inflater.inflate(R.layout.item_list,parent,false);
        //A View é guardada dentro de um MyViewHolder e retornado na função
        return new MyViewHolder(v);
    }
    @Override
    //criando método para preencher a UI com os dados de um item, ele recebe os parâmetros holder e position
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        //Obtendo item que preencherá a UI
        MyItem myItem = itens.get(position);

        //Pegando a View dentro da ViewHolder
        View v = holder.itemView;

        //Preenchendo a UI com os dados do Item, e os localizando com um findViewById
        ImageView imvphoto = v.findViewById(R.id.imvphoto);
        imvphoto.setImageBitmap(myItem.photo);

        TextView tvTitle = v.findViewById(R.id.tvTitle);
        tvTitle.setText(myItem.title);

        TextView tvdesc = v.findViewById(R.id.tvdesc);
        tvdesc.setText(myItem.description);

    }
    @Override
    public int getItemCount(){
        return itens.size();
    }

}
