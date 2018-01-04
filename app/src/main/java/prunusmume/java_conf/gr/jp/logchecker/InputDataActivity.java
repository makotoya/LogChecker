package prunusmume.java_conf.gr.jp.logchecker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InputDataActivity extends AppCompatActivity {

     EditText mEditSetF;
     EditText mEditSetD;
     EditText mEditSetL;
     EditText mEditSetS;
     Button mButton;

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        setTitle("検収情報の入力");

        mEditSetF = (EditText) findViewById(R.id.editSetField);
        mEditSetD = (EditText) findViewById(R.id.editSetDestination);
        mEditSetL = (EditText) findViewById(R.id.editSetLength);
        mEditSetS = (EditText) findViewById(R.id.editSetSpecies);
        mButton = (Button) findViewById(R.id.button);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEditSetL.length() == 0) {
                    Snackbar.make(v, "「材長」を入力してください", Snackbar.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(InputDataActivity.this, MainActivity.class);
                    intent.putExtra("field", mEditSetF.getText().toString());
                    intent.putExtra("destination", mEditSetD.getText().toString());
                    intent.putExtra("length", Integer.parseInt(mEditSetL.getText().toString()));
                    intent.putExtra("species", mEditSetS.getText().toString());
                    startActivity(intent);
                }
            }
        });


    }

}
