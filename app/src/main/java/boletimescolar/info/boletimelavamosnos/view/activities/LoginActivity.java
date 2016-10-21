package boletimescolar.info.boletimelavamosnos.view.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import boletimescolar.info.boletimelavamosnos.R;
import boletimescolar.info.boletimelavamosnos.model.activitymodel.LoginModel;
import boletimescolar.info.boletimelavamosnos.model.detector.ConnectionDetector;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {


    //Instanciar com dependeny

    @BindView(R.id.ra_edit_login)
    EditText ra_edit;


    private LoginModel loginModel;
    private ConnectionDetector cd;


    //Progress Dialog

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        //Instanciar classes
        instanceCrap();
        //Checkar se alguem esta logado
        loginModel.checkAlunoLogged(LoginActivity.this);

    }

    //Onclick para entrar
    @OnClick(R.id.submit_btn_login)
    public void submitUser(View v){


        if(loginModel.checkFields(ra_edit) == true) {


            if(cd.isConnectingToInternet()==true) {

                loginModel.loginAut(ra_edit.getText().toString(), LoginActivity.this);
            }else{

                Toast.makeText(this, R.string.not_connected, Toast.LENGTH_LONG).show();

            }
        }else{

            Toast.makeText(LoginActivity.this, R.string.fields, Toast.LENGTH_SHORT).show();


        }


//        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//        startActivity(intent);
//        finish();



    }

    //Instanciar as classes
    public void instanceCrap(){

        //Instanciar Dependency Injection
        ButterKnife.bind(this);
        loginModel = new LoginModel(this,progressDialog);
        cd = new ConnectionDetector(this);


    }
}
