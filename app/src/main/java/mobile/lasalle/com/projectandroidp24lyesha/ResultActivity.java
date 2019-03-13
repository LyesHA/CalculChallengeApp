package mobile.lasalle.com.projectandroidp24lyesha;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

import model.Operation;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {


    Button btnFinish;

    TextView textViewOperationResult, textViewTestResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        initialize();
        showResult();
    }

    private void showResult() {
        try {
            int nbQuestion=0;
            int correctAnswer=0;
            ArrayList<Operation> list;
            StringBuilder str = new StringBuilder("");
            list = (ArrayList) getIntent().getExtras().getSerializable("tagResult");
            for (Operation operation : list) {
                nbQuestion++;
                if(operation.isCorrect()){
                    str.append(operation.toString()+operation.getResultat()+"\n");
                    str.append("Your answer is correct\n");
                    str.append("----------------------\n");
                    correctAnswer++;

                }else{
                    str.append(operation.toString()+operation.getAnswer()+"\n");
                    str.append("Your answer is wrong!!\n");
                    str.append("Correct answer is : " + operation.getResultat()+"\n");
                    str.append("----------------------\n");
                }
            }
            textViewOperationResult.setText(str.toString());

            StringBuilder stringBuilder = new StringBuilder("");
            double testResult=correctAnswer*100/nbQuestion;
            stringBuilder.append(testResult+"% Correct answer\n");
            stringBuilder.append((100-testResult)+"% Wrong answer");
            textViewTestResult.setText(stringBuilder.toString());
            if(testResult<50)
                textViewTestResult.setTextColor(Color.RED);
            else
                textViewTestResult.setTextColor(Color.GREEN);

        }catch(Exception ex){
            Toast.makeText(this,ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    private void initialize() {
        textViewOperationResult = findViewById(R.id.textViewResultatOperations);
        textViewTestResult = findViewById(R.id.textViewTestResultat);
        btnFinish = findViewById(R.id.btnFinish);
        btnFinish.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        btnFinish = (Button)v;
        this.finish();
    }
}
