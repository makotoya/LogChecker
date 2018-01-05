package prunusmume.java_conf.gr.jp.logchecker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.text.TextWatcher;

public class InputDataActivity extends AppCompatActivity {

     EditText mEditSetF;
     EditText mEditSetY;
     EditText mEditSetD;
     EditText mEditSetL;
     EditText mEditSetS;
     EditText mEditSetI;

     TextView mTextSetF;
     TextView mTextSetY;
     TextView mTextSetD;
     TextView mTextSetL;
     TextView mTextSetS;
     TextView mTextSetI;
     TextView mTextSetNumSum;
     TextView mTextSetVolSum;

     Button mButton;
     Button mButton2;

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        setTitle("検収情報の入力");

        mEditSetF = (EditText) findViewById(R.id.editSetField);
        mEditSetY = (EditText) findViewById(R.id.editSetYard);
        mEditSetD = (EditText) findViewById(R.id.editSetDestination);
        mEditSetS = (EditText) findViewById(R.id.editSetSpecies);
        mEditSetL = (EditText) findViewById(R.id.editSetLength);
        mEditSetI = (EditText) findViewById(R.id.editSetInvestigator);
        mButton = (Button) findViewById(R.id.setInfoButton);
        mButton2 = (Button) findViewById(R.id.clearInfoButton);


        // ナビゲーションドロワーの設定
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, mToolbar, R.string.app_name, R.string.app_name);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View navHeaderView = navigationView.getHeaderView(0);
        mTextSetF = (TextView) navHeaderView.findViewById(R.id.textHeadField);
        mTextSetY = (TextView) navHeaderView.findViewById(R.id.textHeadYard);
        mTextSetD = (TextView) navHeaderView.findViewById(R.id.textHeadDestination);
        mTextSetS = (TextView) navHeaderView.findViewById(R.id.textHeadSpecies);
        mTextSetL = (TextView) navHeaderView.findViewById(R.id.textHeadLength);
        mTextSetI = (TextView) navHeaderView.findViewById(R.id.textHeadInvestigator);
        mTextSetNumSum = (TextView) navHeaderView.findViewById(R.id.textHeadNumSum);
        mTextSetVolSum = (TextView) navHeaderView.findViewById(R.id.textHeadVolSum);

        mTextSetF.setText("未設定");
        mTextSetY.setText("未設定");
        mTextSetD.setText("未設定");
        mTextSetS.setText("未設定");
        mTextSetL.setText("0");
        mTextSetI.setText("未設定");
        mTextSetNumSum.setText("0");
        mTextSetVolSum.setText("0.000");


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.nav_input) {
                    Intent intent = new Intent(getApplicationContext(), RecordActivity.class);
                    startActivity(intent);
                } else if (id == R.id.nav_favourite) {
                } else if (id == R.id.nav_fav_sync) {
                } else if (id == R.id.nav_cheking) {
                }

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEditSetL.length() == 0) {
                    Snackbar.make(v, "「材長」を入力してください", Snackbar.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(InputDataActivity.this, MainActivity.class);
                    intent.putExtra("field", mEditSetF.getText().toString());
                    intent.putExtra("yard", mEditSetY.getText().toString());
                    intent.putExtra("destination", mEditSetD.getText().toString());
                    intent.putExtra("length", Integer.parseInt(mEditSetL.getText().toString()));
                    intent.putExtra("species", mEditSetS.getText().toString());
                    intent.putExtra("investigator", mEditSetI.getText().toString());

                    mTextSetF.setText(mEditSetF.getText().toString());
                    mTextSetY.setText(mEditSetY.getText().toString());
                    mTextSetD.setText(mEditSetD.getText().toString());
                    mTextSetS.setText(mEditSetS.getText().toString());
                    mTextSetL.setText(mEditSetL.getText().toString());
                    mTextSetI.setText(mEditSetI.getText().toString());

                    startActivity(intent);
                }
            }
        });

        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditSetF.setText("");
                mEditSetY.setText("");
                mEditSetD.setText("");
                mEditSetS.setText("");
                mEditSetL.setText("");
                mEditSetI.setText("");
                mTextSetF.setText("未設定");
                mTextSetY.setText("未設定");
                mTextSetD.setText("未設定");
                mTextSetS.setText("未設定");
                mTextSetL.setText("0");
                mTextSetI.setText("未設定");
                mTextSetNumSum.setText("0");
                mTextSetVolSum.setText("0.000");
                Snackbar.make(v, "入力内容を消去しました", Snackbar.LENGTH_LONG).show();
            }
        });
    }

}
