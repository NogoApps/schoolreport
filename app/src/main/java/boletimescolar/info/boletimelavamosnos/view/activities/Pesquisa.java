package boletimescolar.info.boletimelavamosnos.view.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import boletimescolar.info.boletimelavamosnos.R;
import boletimescolar.info.boletimelavamosnos.view.adapters.RecyclerViewAdapter4;

public class Pesquisa extends AppCompatActivity {

    //Spinners

    Spinner spinner_ano;
    Spinner spinner_bimestre;
    ArrayAdapter<CharSequence> adapterAno;
    ArrayAdapter<CharSequence> adapterBimestre;

    //RecyclerView4 Gorick


    private RecyclerView recyclerView4;
    private RecyclerViewAdapter4 recyclerViewAdapter4;
    private RecyclerView.LayoutManager layoutManager4;
    String[] materiasAnteriores = {"Português", "Matemática", "Inglês"};
    Double[] notasAnteriores = {7.5, 10.0, 2.7};
    Integer[] faltasAnteriores = {5, 6, 3};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        //Concertar essa parte da seta de voltar
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Spinner_ano
        spinner_ano = (Spinner) findViewById(R.id.spinner_ano);
        adapterAno = ArrayAdapter.createFromResource(this, R.array.opcoes_ano, android.R.layout.simple_spinner_item);
        adapterAno.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_ano.setAdapter(adapterAno);

        //Spinner_bimestre
        spinner_bimestre = (Spinner) findViewById(R.id.spinner_bimestre);
        adapterBimestre = ArrayAdapter.createFromResource(this, R.array.opcoes_bimestre, android.R.layout.simple_spinner_item);
        adapterBimestre.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_bimestre.setAdapter(adapterBimestre);

        //Botão pesquisar

        final Button pesquisar;
        pesquisar = (Button) findViewById(R.id.pesquisar);
        pesquisar.setBackgroundResource(R.color.colorPrimary);
        pesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //RecyclerView4 Gorick

                recyclerView4 = (RecyclerView) v.findViewById(R.id.recycler_view4);
                recyclerViewAdapter4 = new RecyclerViewAdapter4(materiasAnteriores, notasAnteriores, faltasAnteriores);
                layoutManager4 = new LinearLayoutManager(Pesquisa.this);
                recyclerView4.setLayoutManager(layoutManager4);
                recyclerView4.setHasFixedSize(true);
                recyclerView4.setAdapter(recyclerViewAdapter4);
            }
        });

        //Exemplo de utilização dos Spinners
        spinner_bimestre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " Select", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_ano.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " Select", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }
}
