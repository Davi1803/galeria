package salles.cardoso.davi.galeria.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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
        LayoutInflater inflater = LayoutInflater.from(mainActivity);
        View v = inflater.inflate(R.layout.item_list,parent,false);
        return new MyViewHolder(v);
    }
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        MyItem myItem = itens.get(position);

        View v = holder.itemView;

        ImageView imvfoto = v.findViewById(R.id.imvfoto);
        imvfoto.setImageURI(myItem.title);
    }

}
