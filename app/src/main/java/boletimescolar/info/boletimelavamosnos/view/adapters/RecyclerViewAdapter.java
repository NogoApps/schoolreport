package boletimescolar.info.boletimelavamosnos.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import boletimescolar.info.boletimelavamosnos.R;
import boletimescolar.info.boletimelavamosnos.model.domain.ProvaDomain;
import boletimescolar.info.boletimelavamosnos.model.recyclerviewmodel.RecyclerView1Model;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    List<ProvaDomain> provaArray;
    RecyclerView1Model recyclerView1Model = new RecyclerView1Model();


    public RecyclerViewAdapter(List<ProvaDomain> provaArray) {

        this.provaArray = provaArray;

    }


    public void showArray() {


        for (ProvaDomain provaDomain : provaArray) {

            Log.d("Array", String.valueOf(provaDomain.getNota()));

        }

    }


    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_recentes, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);

        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        ProvaDomain provaDomain = provaArray.get(position);

        recyclerView1Model.materias(holder.materia, provaDomain.getMateria());
        recyclerView1Model.corNota(holder.nota, provaDomain.getNota());
        holder.falta.setText(String.valueOf(provaDomain.getFaltas()));

    }


    @Override
    public int getItemCount() {
        return provaArray.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView materia, nota, falta;

        public RecyclerViewHolder(View view) {
            super(view);
            materia = (TextView) view.findViewById(R.id.materia);
            nota = (TextView) view.findViewById(R.id.nota);
            falta = (TextView) view.findViewById(R.id.falta);

        }

    }

}


