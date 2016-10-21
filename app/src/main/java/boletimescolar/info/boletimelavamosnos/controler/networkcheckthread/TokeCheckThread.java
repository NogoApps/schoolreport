package boletimescolar.info.boletimelavamosnos.controler.networkcheckthread;

import android.content.Context;
import android.util.Log;

import boletimescolar.info.boletimelavamosnos.model.activitymodel.MainActivityModel;
import boletimescolar.info.boletimelavamosnos.model.sharedpreferences.TokenShared;

/**
 * Created by Norb7492 on 16/10/2016.
 */

public class TokeCheckThread extends Thread {


    private Context ctx;
    private MainActivityModel mainActivityModel;

    public TokeCheckThread(Context ctx, MainActivityModel mainActivityModel) {
        this.ctx = ctx;
        this.mainActivityModel = mainActivityModel;
    }

    @Override
    public void run() {

        while(TokenShared.getToken(ctx) == ""){

            Log.d("TokeThread", "Not registered");


            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        mainActivityModel.saveTokeToDatabase();
        TokenShared.RegisterToken(ctx,"registered");


    }
}
