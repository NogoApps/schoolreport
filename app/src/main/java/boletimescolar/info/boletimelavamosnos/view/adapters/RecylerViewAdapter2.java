package boletimescolar.info.boletimelavamosnos.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import boletimescolar.info.boletimelavamosnos.R;
import boletimescolar.info.boletimelavamosnos.model.domain.MediaDomain;
import boletimescolar.info.boletimelavamosnos.model.domain.ProvaDomain;
import boletimescolar.info.boletimelavamosnos.model.recyclerviewmodel.RecyclerView1Model;


public class RecylerViewAdapter2 extends RecyclerView.Adapter<RecylerViewAdapter2.RecyclerViewHolder> {


    List<MediaDomain> mediaArray;
    RecyclerView1Model recyclerView1Model = new RecyclerView1Model();

    public RecylerViewAdapter2(List<MediaDomain> mediaArray) {
        this.mediaArray = mediaArray;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_precisa_tirar, parent, false);
        RecyclerViewHolder recyclerViewHolder2 = new RecyclerViewHolder(view);

        return recyclerViewHolder2;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        MediaDomain mediaDomain = mediaArray.get(position);
        recyclerView1Model.materias(holder.materia2, mediaDomain.getMateria());
        holder.nota2.setText(String.valueOf(mediaDomain.getMedia()));



    }


    @Override
    public int getItemCount() {
        return mediaArray.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView materia2,  precisaTirar, nota2;

        public RecyclerViewHolder(View view) {
            super(view);
            materia2 = (TextView) view.findViewById(R.id.materia2);
            precisaTirar = (TextView) view.findViewById(R.id.precisaTirar);
            nota2 = (TextView) view.findViewById(R.id.nota2);

        }

    }

}


