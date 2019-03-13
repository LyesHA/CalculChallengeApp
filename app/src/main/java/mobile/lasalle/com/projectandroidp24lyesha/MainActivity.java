package mobile.lasalle.com.projectandroidp24lyesha;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.Operation;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextResultat;
    TextView textViewOperation,textViewTimer;
    Button btnShowAll,btnEgale,btnQuit, btnGenerate, btnClear,btnUn,btnZero,btnDeux,btnTrois,btnQuatre,btnCinq,btnSix,btnSept,btnHuit,btnNeuf,btnMoins,btnPoints;
    Operation operation;
    ArrayList<Operation> listOfOperation;
    CountDownTimer time;
    boolean isRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CountDown();
        initialize();
    }

    //function that create an new object CountDownTimer
    private void CountDown() {
        textViewTimer = findViewById(R.id.textViewTimer);
        textViewTimer.setText("");
        time = new CountDownTimer(20000,1000){
            public void onTick(long millisUntilFinished) {
                textViewTimer.setText("seconds remaining: " +new SimpleDateFormat("mm:ss").format(new Date(millisUntilFinished)));
                if(millisUntilFinished<5000)
                    textViewTimer.setTextColor(Color.RED);
                else
                    textViewTimer.setTextColor(Color.BLACK);
            }
            public void onFinish() {
                verifyResult();
            }
        };
    }

    //function to start the countDown and restart it depending on the needs
    private void startCountDown() {
        if(!isRunning)
            time.start();
        else{
            time.cancel(); // cancel
            time.start();  // then restart
        }

    }


    private void initialize() {
        editTextResultat = findViewById(R.id.editTextResult);
        textViewOperation = findViewById(R.id.textViewOperation);
        btnClear = findViewById(R.id.btnClear);        btnClear.setOnClickListener(this);
        btnGenerate = findViewById(R.id.btnGenerate);        btnGenerate.setOnClickListener(this);
        btnQuit = findViewById(R.id.btnQuit);        btnQuit.setOnClickListener(this);
        btnZero = findViewById(R.id.btnZero);        btnZero.setOnClickListener(this);
        btnUn = findViewById(R.id.btnUn);        btnUn.setOnClickListener(this);
        btnDeux = findViewById(R.id.btnDeux);        btnDeux.setOnClickListener(this);
        btnTrois = findViewById(R.id.btnTrois);        btnTrois.setOnClickListener(this);
        btnQuatre = findViewById(R.id.btnQuatre);        btnQuatre.setOnClickListener(this);
        btnCinq = findViewById(R.id.btnCinq);        btnCinq.setOnClickListener(this);
        btnSix = findViewById(R.id.btnSix);        btnSix.setOnClickListener(this);
        btnSept =findViewById(R.id.btnSept);        btnSept.setOnClickListener(this);
        btnHuit =findViewById(R.id.btnHuit);        btnHuit.setOnClickListener(this);
        btnNeuf =findViewById(R.id.btnNeuf);        btnNeuf.setOnClickListener(this);
        btnMoins = findViewById(R.id.btnMoins);     btnMoins.setOnClickListener(this);
        btnPoints = findViewById(R.id.btnPoint);    btnPoints.setOnClickListener(this);
        btnEgale = findViewById(R.id.btnEgale);    btnEgale.setOnClickListener(this);
        btnShowAll = findViewById(R.id.btnShowAll);    btnShowAll.setOnClickListener(this);
        listOfOperation = new ArrayList<Operation>();
        generateOperation();
    }

    //Calls the appropriate function on button click
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnZero: editTextResultat.setText(editTextResultat.getText()+"0");break;
            case R.id.btnUn: editTextResultat.setText(editTextResultat.getText()+"1");break;
            case R.id.btnDeux: editTextResultat.setText(editTextResultat.getText()+"2");break;
            case R.id.btnTrois: editTextResultat.setText(editTextResultat.getText()+"3");break;
            case R.id.btnQuatre: editTextResultat.setText(editTextResultat.getText()+"4");break;
            case R.id.btnCinq: editTextResultat.setText(editTextResultat.getText()+"5");break;
            case R.id.btnSix: editTextResultat.setText(editTextResultat.getText()+"6");break;
            case R.id.btnSept: editTextResultat.setText(editTextResultat.getText()+"7");break;
            case R.id.btnHuit: editTextResultat.setText(editTextResultat.getText()+"8");break;
            case R.id.btnNeuf: editTextResultat.setText(editTextResultat.getText()+"9");break;
            case R.id.btnClear: editTextResultat.setText("");break;
            case R.id.btnMoins: editTextResultat.setText(editTextResultat.getText()+"-");break;
            case R.id.btnPoint: editTextResultat.setText(editTextResultat.getText()+".");break;
            case R.id.btnQuit: finish(); break;
            case R.id.btnGenerate : generateOperation();break;
            case R.id.btnEgale: verifyResult();break;
            case R.id.btnShowAll: showResult(); break;
        }
    }

    //function to show result on a new activity if the user click on ShowAll button
    private void showResult() {
        time.cancel();
        textViewTimer.setText("");
        try {
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("tagResult", listOfOperation);
            startActivity(intent);
        }catch(Exception ex){
            Toast.makeText(this,ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    //function to check different input of the user
    //and to check the result if it's true or false and add it to a list of results
    //it also calls generateOperation function
    private void verifyResult() {
        if(editTextResultat.getText().toString().equals("")) {
            operation.setAnswer(0);
        }
        else if(operation.getResultat()==Double.valueOf(editTextResultat.getText().toString())){
            Toast.makeText(this,"Bravo !!",Toast.LENGTH_LONG).show();
            operation.setAnswer(Double.valueOf(editTextResultat.getText().toString()));
            operation.setCorrect(true);
        }

        else{
            Toast.makeText(this,"Faux !!",Toast.LENGTH_LONG).show();
            operation.setAnswer(Double.valueOf(editTextResultat.getText().toString()));
        }
        listOfOperation.add(operation);
        generateOperation();
        editTextResultat.setText("");
    }

    //function that create an new object Operation that display the operation to solve
    //this function also start count down timer to make the game a little bit difficult
    public void generateOperation(){
        operation = new Operation();
        textViewOperation.setText(operation.toString());
        startCountDown();
    }

}