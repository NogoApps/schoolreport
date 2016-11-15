package boletimescolar.info.boletimelavamosnos.view.popup;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import boletimescolar.info.boletimelavamosnos.R;
import boletimescolar.info.boletimelavamosnos.controler.fragmentcontroller.PopUpController;
import boletimescolar.info.boletimelavamosnos.model.domain.ProvaDomain;
import boletimescolar.info.boletimelavamosnos.view.activities.MainActivity;
import boletimescolar.info.boletimelavamosnos.view.adapters.RecyclerViewAdapter;

public class PopUp extends DialogFragment {

    private LayoutInflater inflater;
    private View v;
    private PopUpController controller;

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView.LayoutManager layoutManager;





    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        inflater = getActivity().getLayoutInflater();

        v = inflater.inflate(R.layout.popup_notas_anteriores, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        String ano = getArguments().getString("ano");
        String bimestre = getArguments().getString("bimestre");

        String bimestreF = String.valueOf(bimestre.charAt(0));
        List<ProvaDomain> provasList = new ArrayList<>();


        for(ProvaDomain p : provasList){

            Log.d("TheLastArray", "Called");

            Log.d("TheLastArray", String.valueOf(p.getNota()));

        }

        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        recyclerViewAdapter = new RecyclerViewAdapter(provasList);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);




        controller = new PopUpController();
        controller.fetchProvas(provasList,getActivity(), bimestreF, ano, recyclerView, recyclerViewAdapter);

        recyclerView.setAdapter(recyclerViewAdapter);



        builder.setView(v);


        return builder.create();
    }





}
