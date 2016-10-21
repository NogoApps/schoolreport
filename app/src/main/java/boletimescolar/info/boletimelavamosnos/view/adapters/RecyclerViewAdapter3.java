package boletimescolar.info.boletimelavamosnos.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import boletimescolar.info.boletimelavamosnos.R;
import boletimescolar.info.boletimelavamosnos.model.domain.ExameDomain;
import boletimescolar.info.boletimelavamosnos.model.recyclerviewmodel.RecyclerView1Model;


public class RecyclerViewAdapter3 extends RecyclerView.Adapter<RecyclerViewAdapter3.RecyclerViewHolder> {


    List<ExameDomain> exameArray;
    RecyclerView1Model recyclerView1Model = new RecyclerView1Model();

    public RecyclerViewAdapter3(List<ExameDomain> exameArray) {
        this.exameArray = exameArray;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_agenda, parent, false);
        RecyclerViewHolder recyclerViewHolder3 = new RecyclerViewHolder(view);

        return recyclerViewHolder3;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        ExameDomain exameDomain = exameArray.get(position);

        holder.bimestre.setText(String.valueOf(exameDomain.getBimestre()) + "º");
        recyclerView1Model.materias(holder.materia, exameDomain.getMateria());
        holder.data.setText(exameDomain.getData());




    }


    @Override
    public int getItemCount() {
        return exameArray.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView bimestre, materia, data;

        public RecyclerViewHolder(View view) {
            super(view);
            bimestre = (TextView) view.findViewById(R.id.bimestre);
            materia = (TextView) view.findViewById(R.id.materia);
            data = (TextView) view.findViewById(R.id.data);

        }

    }

}




