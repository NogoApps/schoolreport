package boletimescolar.info.boletimelavamosnos.controler.networkcheckthread;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import boletimescolar.info.boletimelavamosnos.model.detector.ConnectionDetector;


/**
 * Created by Norb7492 on 16/10/2016.
 */

public class Networkcheck extends Thread {

    private Context ctx;
    private TextView showText;
    private boolean threadFlag = false;


    public Networkcheck(Context ctx, TextView showText) {
        this.ctx = ctx;
        this.showText = showText;

    }


    public void setFlag(boolean threadFlag){


        this.threadFlag = threadFlag;

    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            ConnectionDetector cd = new ConnectionDetector(ctx);

            if(cd.isConnectingToInternet() == false) {
                showText.setVisibility(View.VISIBLE);
            }else{

                showText.setVisibility(View.GONE);

            }
        }
    };



    @Override
    public void run() {
        ConnectionDetector cd = new ConnectionDetector(ctx);



        while(!threadFlag){


            Log.d("Threads", "Ruunning");
            handler.sendEmptyMessage(0);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }


//
//            if(cd.isConnectingToInternet() == false){
//
//                Log.d("Threads", "No connection");
//
//            }else{
//
//
//                Log.d("Threads", "Connected");
//
//            }

//        handler.sendEmptyMessage(0);
//        handler.postDelayed(this, 10000);




    }





}
