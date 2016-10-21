package boletimescolar.info.boletimelavamosnos.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import boletimescolar.info.boletimelavamosnos.R;

/**
 * Created by sg-0036936 on 07/10/2016.
 */

public class RecyclerViewAdapter4 extends RecyclerView.Adapter<RecyclerViewAdapter4.RecyclerViewHolder> {

    //Arrays
    String[] materiasAnteriores;
    Double[] notasAnteriores;
    Integer[] faltasAnteriores;

    public RecyclerViewAdapter4(String[] materiasAnteriores, Double[] notasAnteriores, Integer[] faltasAnteriores) {
        this.materiasAnteriores = materiasAnteriores;
        this.notasAnteriores = notasAnteriores;
        this.faltasAnteriores = faltasAnteriores;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_anteriores, parent, false);
        RecyclerViewHolder recyclerViewHolder4 = new RecyclerViewHolder(view);

        return recyclerViewHolder4;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.materia_anterior.setText(materiasAnteriores[position]);
        holder.nota_anterior.setText(String.valueOf(notasAnteriores[position]));
        holder.falta_anterior.setText(faltasAnteriores[position]);

    }


    @Override
    public int getItemCount() {
        return materiasAnteriores.length;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView materia_anterior, nota_anterior, falta_anterior;

        public RecyclerViewHolder(View view) {
            super(view);
            materia_anterior = (TextView) view.findViewById(R.id.materia_anterior);
            nota_anterior = (TextView) view.findViewById(R.id.nota_anterior);
            falta_anterior = (TextView) view.findViewById(R.id.falta_anterior);

        }

    }

}






