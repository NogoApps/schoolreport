package boletimescolar.info.boletimelavamosnos.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import boletimescolar.info.boletimelavamosnos.R;
import boletimescolar.info.boletimelavamosnos.model.domain.MediaDomain;
import boletimescolar.info.boletimelavamosnos.model.domain.ProvaDomain;
import boletimescolar.info.boletimelavamosnos.model.fragmentmodel.Tab1Model;
import boletimescolar.info.boletimelavamosnos.sqlite.adapter.SqliteNotasAdapter;
import boletimescolar.info.boletimelavamosnos.view.adapters.RecyclerViewAdapter;
import boletimescolar.info.boletimelavamosnos.view.adapters.RecylerViewAdapter2;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by hp1 on 21-01-2015.
 */
public class Tab1 extends Fragment {


    private Tab1Model tab1Model;


    //RecyclerView1 Gorick

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<ProvaDomain> provaArray = new ArrayList<>();


    //RecyclerView2 Gorick


    private RecyclerView recyclerView2;
    private RecylerViewAdapter2 recyclerViewAdapter2;
    private RecyclerView.LayoutManager layoutManager2;


    private List<MediaDomain> mediaArray = new ArrayList<>();


    //Spinner
    private Spinner spinnerBimestre;
    private ArrayAdapter<CharSequence> spinnerAdapter;


    //Button
    private Button pesquisar;
    private PopupWindow popUpWindowPesquisa;
    private LayoutInflater layoutInflaterPesquisa;
    private RelativeLayout relativeLayoutPesquisa;


    //ProgressBar
    private ProgressBar progressBar;


    private TextView precisa_tirar;


    private TextView sem_provas;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tab1, container, false);


        instanceCrap(v);


//        escolheQualMerdaChamar();


//        tab1Model.calcularMedia(provaArray, mediaArray);


        //Botão pesquisar


        return v;
    }

    public void instanceCrap(View v) {
        precisa_tirar = (TextView) v.findViewById(R.id.precisaTirar);
        sem_provas = (TextView) v.findViewById(R.id.semProvas);


        //RecyclerView1 Gorick

        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        recyclerViewAdapter = new RecyclerViewAdapter(provaArray);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
//        recyclerView.setAdapter(recyclerViewAdapter);

        //Fim do RecyclerView1 Gorick

        //RecyclerView2 Gorick

        recyclerView2 = (RecyclerView) v.findViewById(R.id.recycler_view2);
        recyclerViewAdapter2 = new RecylerViewAdapter2(mediaArray);
        layoutManager2 = new LinearLayoutManager(getActivity());
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView2.setHasFixedSize(true);
//        recyclerView2.setAdapter(recyclerViewAdapter2);

        progressBar = (ProgressBar) v.findViewById(R.id.progress_bar);

        //Instanciar o model

        tab1Model = new Tab1Model(provaArray, mediaArray, getActivity(), recyclerViewAdapter, recyclerViewAdapter2, recyclerView, recyclerView2, progressBar);


        SqliteNotasAdapter sqliteAdapter = new SqliteNotasAdapter(getActivity());
        sqliteAdapter.openDB();


        //Button pesquisar and popup
        pesquisar = (Button) v.findViewById(R.id.pesquisar);
        relativeLayoutPesquisa = (RelativeLayout) v.findViewById(R.id.relative);
        pesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutInflaterPesquisa = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                ViewGroup containerPesquisa = (ViewGroup) layoutInflaterPesquisa.inflate(R.layout.activity_pesquisa, null);
                popUpWindowPesquisa = new PopupWindow(containerPesquisa, 600, 800, true);
                popUpWindowPesquisa.showAtLocation(relativeLayoutPesquisa, Gravity.NO_GRAVITY, 500, 500);
                containerPesquisa.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        popUpWindowPesquisa.dismiss();
                        return true;

                    }
                });
            }
        });

        //SpinnerBimestre
        spinnerBimestre = (Spinner) v.findViewById(R.id.bimestre_spinner);
        spinnerAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.opcoes_bimestre, R.layout.spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBimestre.setAdapter(spinnerAdapter);
        spinnerBimestre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                int bimestre;

                switch (i) {

                    case 0:

                        chooseItemBimestre(bimestre = 1);
                        tab1Model.calcularMedia(provaArray, mediaArray);

                        break;
                    case 1:

                        chooseItemBimestre(bimestre = 2);
                        tab1Model.calcularMedia(provaArray, mediaArray);

                        break;
                    case 2:

                        chooseItemBimestre(bimestre = 3);
                        tab1Model.calcularMedia(provaArray, mediaArray);


                        break;
                    case 3:

                        chooseItemBimestre(bimestre = 4);
                        tab1Model.calcularMedia(provaArray, mediaArray);

                        break;


                }


            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }


    public void chooseItemBimestre(int bimestre) {


        SqliteNotasAdapter sqliteAdapter = new SqliteNotasAdapter(getActivity());
        final boolean check = sqliteAdapter.notasIsEmpty();
        if (check == true) {
            tab1Model.provaFetch(bimestre);


        } else {


            tab1Model.listarNotasWhere(bimestre, provaArray);


        }

    }

    public void pequenoCheckDeArray() {


        for (ProvaDomain provaDomain : provaArray) {

            Toast.makeText(getActivity(), String.valueOf(provaDomain.getBimestre()), Toast.LENGTH_LONG).show();


        }


    }
}

