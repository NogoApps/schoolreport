package boletimescolar.info.boletimelavamosnos.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import boletimescolar.info.boletimelavamosnos.R;
import boletimescolar.info.boletimelavamosnos.model.domain.ExameDomain;
import boletimescolar.info.boletimelavamosnos.model.fragmentmodel.Tab2Model;
import boletimescolar.info.boletimelavamosnos.sqlite.adapter.SqliteExamesAdapter;
import boletimescolar.info.boletimelavamosnos.view.adapters.RecyclerViewAdapter3;

/**
 * Created by hp1 on 21-01-2015.
 */
public class Tab2 extends Fragment {

    //RecyclerView3 Gorick

    //RecyclerView3 Gorick

    private RecyclerView recyclerView3;
    private RecyclerViewAdapter3 recyclerViewAdapter3;
    private RecyclerView.LayoutManager layoutManager3;

    private List<ExameDomain> exameArray = new ArrayList<>();

//    String[] inicio = {"05/04 até 15/04", "10/07 até 20/07", "30/09 até 10/10", "20/11 até 05/12"};
//    String[] ate = {"e", "e", "e", "e"};
//    String[] fim = {"30/04 até 07/05.", "30/07 até 10/08.", "25/10 até 05/11.", "10/12 até 20/12."};
//    String[] bimestre = {"1º Bimestre:", "2º Bimestre:","3º Bimestre:","4º Bimestre:" };






    //Norbert Stuff
    private Tab2Model tab2Model;




    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tab2,container,false);

        //RecyclerView3 Gorick

        recyclerView3 = (RecyclerView) v.findViewById(R.id.recycler_view);
        recyclerViewAdapter3 = new RecyclerViewAdapter3(exameArray);
        layoutManager3 = new LinearLayoutManager(getActivity());
        recyclerView3.setLayoutManager(layoutManager3);
        recyclerView3.setHasFixedSize(true);


        instaceCrap();
        escolheQualMerdaChamar();
        Log.d("Exames","Here called");


        return v;
    }

    public void instaceCrap(){

        tab2Model = new Tab2Model(getActivity(), exameArray, recyclerViewAdapter3);

    }

    public void escolheQualMerdaChamar() {


        SqliteExamesAdapter sqliteAdapter = new SqliteExamesAdapter(getActivity());
        final boolean check = sqliteAdapter.notasIsEmpty();
        if (check == true) {
            tab2Model.exameFetch();
            recyclerView3.setAdapter(recyclerViewAdapter3);


        } else {


            tab2Model.listarNotas();
            recyclerView3.setAdapter(recyclerViewAdapter3);


        }

    }

}
