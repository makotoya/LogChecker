package prunusmume.java_conf.gr.jp.logchecker;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private int mYear, mMonth, mDay, mHour, mMinute;

    String field;

    String destination;

    String species;

    //原木の長さ
    int loglength;

    //原木の直径
    int logdia;

    //直径ごとの本数
    int num8, num9, num10, num11, num12, num13, num14, num15, num16, num18,  num20,
         num22, num24, num26, num28, num30, num32, num34, num36, num38, num40,
         num42, num44, num46, num48, num50, num52, num54, num56, num58, num60;

    //直径ごとの材積
    BigDecimal vol8, vol9, vol10, vol11, vol12, vol13, vol14, vol16, vol18, vol20,
               vol22, vol24, vol26, vol28, vol30, vol32, vol34, vol36, vol38, vol40,
               vol42, vol44, vol46, vol48, vol50, vol52, vol54, vol56, vol58, vol60;

    //合計本数
    int numsum;

    //合計材積
    BigDecimal volsum = new BigDecimal("0.000");

    //直径読み上げ音声
    private SoundPool soundPool;
    private int dia8_se, dia9_se, dia10_se, dia11_se, dia12_se, dia13_se, dia14_se, dia16_se, dia18_se, dia20_se,
                  dia22_se, dia24_se, dia26_se, dia28_se, dia30_se, dia32_se, dia34_se, dia36_se, dia38_se, dia40_se,
                  dia42_se, dia44_se, dia46_se, dia48_se, dia50_se, dia52_se, dia54_se, dia56_se, dia58_se, dia60_se;

    EditText mNum8Edit,  mNum9Edit, mNum10Edit,  mNum11Edit, mNum12Edit,  mNum13Edit, mNum14Edit,  mNum16Edit, mNum18Edit,  mNum20Edit,
             mNum22Edit,  mNum24Edit, mNum26Edit,  mNum28Edit, mNum30Edit,  mNum32Edit, mNum34Edit,  mNum36Edit, mNum38Edit,  mNum40Edit,
             mNum42Edit,  mNum44Edit, mNum46Edit,  mNum48Edit, mNum50Edit,  mNum52Edit, mNum54Edit,  mNum56Edit, mNum58Edit,  mNum60Edit;



    TextView mFieldText, mDestinationText, mLengthText, mSpeciesText;
    TextView mVol8Text, mVol9Text, mVol10Text, mVol11Text, mVol12Text, mVol13Text, mVol14Text, mVol16Text, mVol18Text, mVol20Text,
             mVol22Text, mVol24Text, mVol26Text, mVol28Text, mVol30Text, mVol32Text, mVol34Text, mVol36Text, mVol38Text, mVol40Text,
             mVol42Text, mVol44Text, mVol46Text, mVol48Text, mVol50Text, mVol52Text, mVol54Text, mVol56Text, mVol58Text, mVol60Text;
    TextView mNumSumText, mVolSumText;
    ImageButton mAdd8ImgButton, mAdd9ImgButton, mAdd10ImgButton, mAdd11ImgButton, mAdd12ImgButton, mAdd13ImgButton, mAdd14ImgButton, mAdd16ImgButton, mAdd18ImgButton, mAdd20ImgButton,
                mAdd22ImgButton, mAdd24ImgButton, mAdd26ImgButton, mAdd28ImgButton, mAdd30ImgButton, mAdd32ImgButton, mAdd34ImgButton, mAdd36ImgButton, mAdd38ImgButton, mAdd40ImgButton,
                mAdd42ImgButton, mAdd44ImgButton, mAdd46ImgButton, mAdd48ImgButton, mAdd50ImgButton, mAdd52ImgButton, mAdd54ImgButton, mAdd56ImgButton, mAdd58ImgButton, mAdd60ImgButton,
                mSub8ImgButton, mSub9ImgButton, mSub10ImgButton, mSub11ImgButton, mSub12ImgButton, mSub13ImgButton, mSub14ImgButton, mSub16ImgButton, mSub18ImgButton, mSub20ImgButton,
                mSub22ImgButton, mSub24ImgButton, mSub26ImgButton, mSub28ImgButton, mSub30ImgButton, mSub32ImgButton, mSub34ImgButton, mSub36ImgButton, mSub38ImgButton, mSub40ImgButton,
                mSub42ImgButton, mSub44ImgButton, mSub46ImgButton, mSub48ImgButton, mSub50ImgButton, mSub52ImgButton, mSub54ImgButton, mSub56ImgButton, mSub58ImgButton, mSub60ImgButton;

    private Record mRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        soundPool = new SoundPool(30, AudioManager.STREAM_MUSIC, 0);
        dia8_se = soundPool.load(this, R.raw.dia8, 1);
        dia9_se = soundPool.load(this, R.raw.dia9, 1);
        dia10_se = soundPool.load(this, R.raw.dia10, 1);
        dia11_se = soundPool.load(this, R.raw.dia11, 1);
        dia12_se = soundPool.load(this, R.raw.dia12, 1);
        dia13_se = soundPool.load(this, R.raw.dia13, 1);
        dia14_se = soundPool.load(this, R.raw.dia14, 1);
        dia16_se = soundPool.load(this, R.raw.dia16, 1);
        dia18_se = soundPool.load(this, R.raw.dia18, 1);
        dia20_se = soundPool.load(this, R.raw.dia20, 1);
        dia22_se = soundPool.load(this, R.raw.dia22, 1);
        dia24_se = soundPool.load(this, R.raw.dia24, 1);
        dia26_se = soundPool.load(this, R.raw.dia26, 1);
        dia28_se = soundPool.load(this, R.raw.dia28, 1);
        dia30_se = soundPool.load(this, R.raw.dia30, 1);
        dia32_se = soundPool.load(this, R.raw.dia32, 1);
        dia34_se = soundPool.load(this, R.raw.dia34, 1);
        dia36_se = soundPool.load(this, R.raw.dia36, 1);
        dia38_se = soundPool.load(this, R.raw.dia38, 1);
        dia40_se = soundPool.load(this, R.raw.dia40, 1);
        dia42_se = soundPool.load(this, R.raw.dia42, 1);
        dia44_se = soundPool.load(this, R.raw.dia44, 1);
        dia46_se = soundPool.load(this, R.raw.dia46, 1);
        dia48_se = soundPool.load(this, R.raw.dia48, 1);
        dia50_se = soundPool.load(this, R.raw.dia50, 1);
        dia52_se = soundPool.load(this, R.raw.dia52, 1);
        dia54_se = soundPool.load(this, R.raw.dia54, 1);
        dia56_se = soundPool.load(this, R.raw.dia56, 1);
        dia58_se = soundPool.load(this, R.raw.dia58, 1);
        dia60_se = soundPool.load(this, R.raw.dia60, 1);


        mFieldText = (TextView) findViewById(R.id.textField);
        mDestinationText = (TextView) findViewById(R.id.textDestination);
        mLengthText = (TextView) findViewById(R.id.textLength);
        mSpeciesText = (TextView) findViewById(R.id.textSpecies);
        mNumSumText = (TextView) findViewById(R.id.textView5);
        mVolSumText = (TextView) findViewById(R.id.textView6);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addRecord();
                Intent intent = new Intent(MainActivity.this, RecordActivity.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        int recordId = intent.getIntExtra(RecordActivity.EXTRA_RECORD, -1);
        Realm realm = Realm.getDefaultInstance();
        mRecord = realm.where(Record.class).equalTo("id", recordId).findFirst();
        realm.close();

        //直径8cmの入力処理
        mNum8Edit = (EditText) findViewById(R.id.eTextNum8);
        mVol8Text = (TextView) findViewById(R.id.textVol8);
        mAdd8ImgButton = (ImageButton) findViewById(R.id.iButtonAdd8);
        mSub8ImgButton = (ImageButton) findViewById(R.id.iButtonSub8);

        mAdd8ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(dia8_se, 1F, 1F, 0, 0, 1F);
                num8 += 1;
                mNum8Edit.setText(String.valueOf(num8));
                numsum += 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 8;
                vol8 = calcVol(logdia, loglength, num8);
                mVol8Text.setText(String.valueOf(vol8));
                volsum = volsum.add(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        mSub8ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num8 -= 1;
                mNum8Edit.setText(String.valueOf(num8));
                numsum -= 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 8;
                vol8 = calcVol(logdia, loglength, num8);
                mVol8Text.setText(String.valueOf(vol8));
                volsum = volsum.subtract(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        //直径9cmの入力処理
        mNum9Edit = (EditText) findViewById(R.id.eTextNum9);
        mVol9Text = (TextView) findViewById(R.id.textVol9);
        mAdd9ImgButton = (ImageButton) findViewById(R.id.iButtonAdd9);
        mSub9ImgButton = (ImageButton) findViewById(R.id.iButtonSub9);

        mAdd9ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(dia9_se, 1F, 1F, 0, 0, 1F);
                num9 += 1;
                mNum9Edit.setText(String.valueOf(num9));
                numsum += 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 9;
                vol9 = calcVol(logdia, loglength, num9);
                mVol9Text.setText(String.valueOf(vol9));
                volsum = volsum.add(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        mSub9ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num9 -= 1;
                mNum9Edit.setText(String.valueOf(num9));
                numsum -= 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 9;
                vol9 = calcVol(logdia, loglength, num9);
                mVol9Text.setText(String.valueOf(vol9));
                volsum = volsum.subtract(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        //直径10cmの入力処理
        mNum10Edit = (EditText) findViewById(R.id.eTextNum10);
        mVol10Text = (TextView) findViewById(R.id.textVol10);
        mAdd10ImgButton = (ImageButton) findViewById(R.id.iButtonAdd10);
        mSub10ImgButton = (ImageButton) findViewById(R.id.iButtonSub10);

        mAdd10ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(dia10_se, 1F, 1F, 0, 0, 1F);
                num10 += 1;
                mNum10Edit.setText(String.valueOf(num10));
                numsum += 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 10;
                vol10 = calcVol(logdia, loglength, num10);
                mVol10Text.setText(String.valueOf(vol10));
                volsum = volsum.add(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        mSub10ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num10 -= 1;
                mNum10Edit.setText(String.valueOf(num10));
                numsum -= 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 10;
                vol10 = calcVol(logdia, loglength, num10);
                mVol10Text.setText(String.valueOf(vol10));
                volsum = volsum.subtract(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        //直径11cmの入力処理
        mNum11Edit = (EditText) findViewById(R.id.eTextNum11);
        mVol11Text = (TextView) findViewById(R.id.textVol11);
        mAdd11ImgButton = (ImageButton) findViewById(R.id.iButtonAdd11);
        mSub11ImgButton = (ImageButton) findViewById(R.id.iButtonSub11);

        mAdd11ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(dia11_se, 1F, 1F, 0, 0, 1F);
                num11 += 1;
                mNum11Edit.setText(String.valueOf(num11));
                numsum += 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 11;
                vol11 = calcVol(logdia, loglength, num11);
                mVol11Text.setText(String.valueOf(vol11));
                volsum = volsum.add(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        mSub11ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num11 -= 1;
                mNum11Edit.setText(String.valueOf(num11));
                numsum -= 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 11;
                vol11 = calcVol(logdia, loglength, num11);
                mVol11Text.setText(String.valueOf(vol11));
                volsum = volsum.subtract(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        //直径12cmの入力処理
        mNum12Edit = (EditText) findViewById(R.id.eTextNum12);
        mVol12Text = (TextView) findViewById(R.id.textVol12);
        mAdd12ImgButton = (ImageButton) findViewById(R.id.iButtonAdd12);
        mSub12ImgButton = (ImageButton) findViewById(R.id.iButtonSub12);

        mAdd12ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(dia12_se, 1F, 1F, 0, 0, 1F);
                num12 += 1;
                mNum12Edit.setText(String.valueOf(num12));
                numsum += 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 12;
                vol12 = calcVol(logdia, loglength, num12);
                mVol12Text.setText(String.valueOf(vol12));
                volsum = volsum.add(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        mSub12ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num12 -= 1;
                mNum12Edit.setText(String.valueOf(num12));
                numsum -= 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 12;
                vol12 = calcVol(logdia, loglength, num12);
                mVol12Text.setText(String.valueOf(vol12));
                volsum = volsum.subtract(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        //直径13cmの入力処理
        mNum13Edit = (EditText) findViewById(R.id.eTextNum13);
        mVol13Text = (TextView) findViewById(R.id.textVol13);
        mAdd13ImgButton = (ImageButton) findViewById(R.id.iButtonAdd13);
        mSub13ImgButton = (ImageButton) findViewById(R.id.iButtonSub13);

        mAdd13ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(dia13_se, 1F, 1F, 0, 0, 1F);
                num13 += 1;
                mNum13Edit.setText(String.valueOf(num13));
                numsum += 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 13;
                vol13 = calcVol(logdia, loglength, num13);
                mVol13Text.setText(String.valueOf(vol13));
                volsum = volsum.add(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        mSub13ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num13 -= 1;
                mNum13Edit.setText(String.valueOf(num13));
                numsum -= 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 13;
                vol13 = calcVol(logdia, loglength, num13);
                mVol13Text.setText(String.valueOf(vol13));
                volsum = volsum.subtract(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        //直径14cmの入力処理
        mNum14Edit = (EditText) findViewById(R.id.eTextNum14);
        mVol14Text = (TextView) findViewById(R.id.textVol14);
        mAdd14ImgButton = (ImageButton) findViewById(R.id.iButtonAdd14);
        mSub14ImgButton = (ImageButton) findViewById(R.id.iButtonSub14);

        mAdd14ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(dia14_se, 1F, 1F, 0, 0, 1F);
                num14 += 1;
                mNum14Edit.setText(String.valueOf(num14));
                numsum += 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 14;
                vol14 = calcVol(logdia, loglength, num14);
                mVol14Text.setText(String.valueOf(vol14));
                volsum = volsum.add(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        mSub14ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num14 -= 1;
                mNum14Edit.setText(String.valueOf(num14));
                numsum -= 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 14;
                vol14 = calcVol(logdia, loglength, num14);
                mVol14Text.setText(String.valueOf(vol14));
                volsum = volsum.subtract(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        //直径16cmの入力処理
        mNum16Edit = (EditText) findViewById(R.id.eTextNum16);
        mVol16Text = (TextView) findViewById(R.id.textVol16);
        mAdd16ImgButton = (ImageButton) findViewById(R.id.iButtonAdd16);
        mSub16ImgButton = (ImageButton) findViewById(R.id.iButtonSub16);

        mAdd16ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(dia16_se, 1F, 1F, 0, 0, 1F);
                num16 += 1;
                mNum16Edit.setText(String.valueOf(num16));
                numsum += 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 16;
                vol16 = calcVol(logdia, loglength, num16);
                mVol16Text.setText(String.valueOf(vol16));
                volsum = volsum.add(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        mSub16ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num16 -= 1;
                mNum16Edit.setText(String.valueOf(num16));
                numsum -= 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 16;
                vol16 = calcVol(logdia, loglength, num16);
                mVol16Text.setText(String.valueOf(vol16));
                volsum = volsum.subtract(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        //直径18cmの入力処理
        mNum18Edit = (EditText) findViewById(R.id.eTextNum18);
        mVol18Text = (TextView) findViewById(R.id.textVol18);
        mAdd18ImgButton = (ImageButton) findViewById(R.id.iButtonAdd18);
        mSub18ImgButton = (ImageButton) findViewById(R.id.iButtonSub18);

        mAdd18ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(dia18_se, 1F, 1F, 0, 0, 1F);
                num18 += 1;
                mNum18Edit.setText(String.valueOf(num18));
                numsum += 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 18;
                vol18 = calcVol(logdia, loglength, num18);
                mVol18Text.setText(String.valueOf(vol18));
                volsum = volsum.add(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        mSub18ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num18 -= 1;
                mNum18Edit.setText(String.valueOf(num18));
                numsum -= 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 18;
                vol18 = calcVol(logdia, loglength, num18);
                mVol18Text.setText(String.valueOf(vol18));
                volsum = volsum.subtract(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        //直径20cmの入力処理
        mNum20Edit = (EditText) findViewById(R.id.eTextNum20);
        mVol20Text = (TextView) findViewById(R.id.textVol20);
        mAdd20ImgButton = (ImageButton) findViewById(R.id.iButtonAdd20);
        mSub20ImgButton = (ImageButton) findViewById(R.id.iButtonSub20);

        mAdd20ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(dia20_se, 1F, 1F, 0, 0, 1F);
                num20 += 1;
                mNum20Edit.setText(String.valueOf(num20));
                numsum += 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 20;
                vol20 = calcVol(logdia, loglength, num20);
                mVol20Text.setText(String.valueOf(vol20));
                volsum = volsum.add(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        mSub20ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num20 -= 1;
                mNum20Edit.setText(String.valueOf(num20));
                numsum -= 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 20;
                vol20 = calcVol(logdia, loglength, num20);
                mVol20Text.setText(String.valueOf(vol20));
                volsum = volsum.subtract(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        //直径22cmの入力処理
        mNum22Edit = (EditText) findViewById(R.id.eTextNum22);
        mVol22Text = (TextView) findViewById(R.id.textVol22);
        mAdd22ImgButton = (ImageButton) findViewById(R.id.iButtonAdd22);
        mSub22ImgButton = (ImageButton) findViewById(R.id.iButtonSub22);

        mAdd22ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(dia22_se, 1F, 1F, 0, 0, 1F);
                num22 += 1;
                mNum22Edit.setText(String.valueOf(num22));
                numsum += 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 22;
                vol22 = calcVol(logdia, loglength, num22);
                mVol22Text.setText(String.valueOf(vol22));
                volsum = volsum.add(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        mSub22ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num22 -= 1;
                mNum22Edit.setText(String.valueOf(num22));
                numsum -= 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 22;
                vol22 = calcVol(logdia, loglength, num22);
                mVol22Text.setText(String.valueOf(vol22));
                volsum = volsum.subtract(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        //直径24cmの入力処理
        mNum24Edit = (EditText) findViewById(R.id.eTextNum24);
        mVol24Text = (TextView) findViewById(R.id.textVol24);
        mAdd24ImgButton = (ImageButton) findViewById(R.id.iButtonAdd24);
        mSub24ImgButton = (ImageButton) findViewById(R.id.iButtonSub24);

        mAdd24ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(dia24_se, 1F, 1F, 0, 0, 1F);
                num24 += 1;
                mNum24Edit.setText(String.valueOf(num24));
                numsum += 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 24;
                vol24 = calcVol(logdia, loglength, num24);
                mVol24Text.setText(String.valueOf(vol24));
                volsum = volsum.add(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        mSub24ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num24 -= 1;
                mNum24Edit.setText(String.valueOf(num24));
                numsum -= 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 24;
                vol24 = calcVol(logdia, loglength, num24);
                mVol24Text.setText(String.valueOf(vol24));
                volsum = volsum.subtract(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        //直径26cmの入力処理
        mNum26Edit = (EditText) findViewById(R.id.eTextNum26);
        mVol26Text = (TextView) findViewById(R.id.textVol26);
        mAdd26ImgButton = (ImageButton) findViewById(R.id.iButtonAdd26);
        mSub26ImgButton = (ImageButton) findViewById(R.id.iButtonSub26);

        mAdd26ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(dia26_se, 1F, 1F, 0, 0, 1F);
                num26 += 1;
                mNum26Edit.setText(String.valueOf(num26));
                numsum += 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 26;
                vol26 = calcVol(logdia, loglength, num26);
                mVol26Text.setText(String.valueOf(vol26));
                volsum = volsum.add(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        mSub26ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num26 -= 1;
                mNum26Edit.setText(String.valueOf(num26));
                numsum -= 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 26;
                vol26 = calcVol(logdia, loglength, num26);
                mVol26Text.setText(String.valueOf(vol26));
                volsum = volsum.subtract(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        //直径28cmの入力処理
        mNum28Edit = (EditText) findViewById(R.id.eTextNum28);
        mVol28Text = (TextView) findViewById(R.id.textVol28);
        mAdd28ImgButton = (ImageButton) findViewById(R.id.iButtonAdd28);
        mSub28ImgButton = (ImageButton) findViewById(R.id.iButtonSub28);

        mAdd28ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(dia28_se, 1F, 1F, 0, 0, 1F);
                num28 += 1;
                mNum28Edit.setText(String.valueOf(num28));
                numsum += 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 28;
                vol28 = calcVol(logdia, loglength, num28);
                mVol28Text.setText(String.valueOf(vol28));
                volsum = volsum.add(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        mSub28ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num28 -= 1;
                mNum28Edit.setText(String.valueOf(num28));
                numsum -= 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 28;
                vol28 = calcVol(logdia, loglength, num28);
                mVol28Text.setText(String.valueOf(vol28));
                volsum = volsum.subtract(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        //直径30cmの入力処理
        mNum30Edit = (EditText) findViewById(R.id.eTextNum30);
        mVol30Text = (TextView) findViewById(R.id.textVol30);
        mAdd30ImgButton = (ImageButton) findViewById(R.id.iButtonAdd30);
        mSub30ImgButton = (ImageButton) findViewById(R.id.iButtonSub30);

        mAdd30ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(dia30_se, 1F, 1F, 0, 0, 1F);
                num30 += 1;
                mNum30Edit.setText(String.valueOf(num30));
                numsum += 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 30;
                vol30 = calcVol(logdia, loglength, num30);
                mVol30Text.setText(String.valueOf(vol30));
                volsum = volsum.add(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        mSub30ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num30 -= 1;
                mNum30Edit.setText(String.valueOf(num30));
                numsum -= 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 30;
                vol30 = calcVol(logdia, loglength, num30);
                mVol30Text.setText(String.valueOf(vol30));
                volsum = volsum.subtract(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        //直径32cmの入力処理
        mNum32Edit = (EditText) findViewById(R.id.eTextNum32);
        mVol32Text = (TextView) findViewById(R.id.textVol32);
        mAdd32ImgButton = (ImageButton) findViewById(R.id.iButtonAdd32);
        mSub32ImgButton = (ImageButton) findViewById(R.id.iButtonSub32);

        mAdd32ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(dia32_se, 1F, 1F, 0, 0, 1F);
                num32 += 1;
                mNum32Edit.setText(String.valueOf(num32));
                numsum += 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 32;
                vol32 = calcVol(logdia, loglength, num32);
                mVol32Text.setText(String.valueOf(vol32));
                volsum = volsum.add(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        mSub32ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num32 -= 1;
                mNum32Edit.setText(String.valueOf(num32));
                numsum -= 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 32;
                vol32 = calcVol(logdia, loglength, num32);
                mVol32Text.setText(String.valueOf(vol32));
                volsum = volsum.subtract(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        //直径34cmの入力処理
        mNum34Edit = (EditText) findViewById(R.id.eTextNum34);
        mVol34Text = (TextView) findViewById(R.id.textVol34);
        mAdd34ImgButton = (ImageButton) findViewById(R.id.iButtonAdd34);
        mSub34ImgButton = (ImageButton) findViewById(R.id.iButtonSub34);

        mAdd34ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(dia34_se, 1F, 1F, 0, 0, 1F);
                num34 += 1;
                mNum34Edit.setText(String.valueOf(num34));
                numsum += 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 34;
                vol34 = calcVol(logdia, loglength, num34);
                mVol34Text.setText(String.valueOf(vol34));
                volsum = volsum.add(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        mSub34ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num34 -= 1;
                mNum34Edit.setText(String.valueOf(num34));
                numsum -= 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 34;
                vol34 = calcVol(logdia, loglength, num34);
                mVol34Text.setText(String.valueOf(vol34));
                volsum = volsum.subtract(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        //直径36cmの入力処理
        mNum36Edit = (EditText) findViewById(R.id.eTextNum36);
        mVol36Text = (TextView) findViewById(R.id.textVol36);
        mAdd36ImgButton = (ImageButton) findViewById(R.id.iButtonAdd36);
        mSub36ImgButton = (ImageButton) findViewById(R.id.iButtonSub36);

        mAdd36ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(dia36_se, 1F, 1F, 0, 0, 1F);
                num36 += 1;
                mNum36Edit.setText(String.valueOf(num36));
                numsum += 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 36;
                vol36 = calcVol(logdia, loglength, num36);
                mVol36Text.setText(String.valueOf(vol36));
                volsum = volsum.add(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        mSub36ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num36 -= 1;
                mNum36Edit.setText(String.valueOf(num36));
                numsum -= 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 36;
                vol36 = calcVol(logdia, loglength, num36);
                mVol36Text.setText(String.valueOf(vol36));
                volsum = volsum.subtract(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        //直径38cmの入力処理
        mNum38Edit = (EditText) findViewById(R.id.eTextNum38);
        mVol38Text = (TextView) findViewById(R.id.textVol38);
        mAdd38ImgButton = (ImageButton) findViewById(R.id.iButtonAdd38);
        mSub38ImgButton = (ImageButton) findViewById(R.id.iButtonSub38);

        mAdd38ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(dia38_se, 1F, 1F, 0, 0, 1F);
                num38 += 1;
                mNum38Edit.setText(String.valueOf(num38));
                numsum += 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 38;
                vol38 = calcVol(logdia, loglength, num38);
                mVol38Text.setText(String.valueOf(vol38));
                volsum = volsum.add(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        mSub38ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num38 -= 1;
                mNum38Edit.setText(String.valueOf(num38));
                numsum -= 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 38;
                vol38 = calcVol(logdia, loglength, num38);
                mVol38Text.setText(String.valueOf(vol38));
                volsum = volsum.subtract(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        //直径40cmの入力処理
        mNum40Edit = (EditText) findViewById(R.id.eTextNum40);
        mVol40Text = (TextView) findViewById(R.id.textVol40);
        mAdd40ImgButton = (ImageButton) findViewById(R.id.iButtonAdd40);
        mSub40ImgButton = (ImageButton) findViewById(R.id.iButtonSub40);

        mAdd40ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(dia40_se, 1F, 1F, 0, 0, 1F);
                num40 += 1;
                mNum40Edit.setText(String.valueOf(num40));
                numsum += 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 40;
                vol40 = calcVol(logdia, loglength, num40);
                mVol40Text.setText(String.valueOf(vol40));
                volsum = volsum.add(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        mSub40ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num40 -= 1;
                mNum40Edit.setText(String.valueOf(num40));
                numsum -= 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 40;
                vol40 = calcVol(logdia, loglength, num40);
                mVol40Text.setText(String.valueOf(vol40));
                volsum = volsum.subtract(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        //直径42cmの入力処理
        mNum42Edit = (EditText) findViewById(R.id.eTextNum42);
        mVol42Text = (TextView) findViewById(R.id.textVol42);
        mAdd42ImgButton = (ImageButton) findViewById(R.id.iButtonAdd42);
        mSub42ImgButton = (ImageButton) findViewById(R.id.iButtonSub42);

        mAdd42ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(dia42_se, 1F, 1F, 0, 0, 1F);
                num42 += 1;
                mNum42Edit.setText(String.valueOf(num42));
                numsum += 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 42;
                vol42 = calcVol(logdia, loglength, num42);
                mVol42Text.setText(String.valueOf(vol42));
                volsum = volsum.add(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        mSub42ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num42 -= 1;
                mNum42Edit.setText(String.valueOf(num42));
                numsum -= 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 42;
                vol42 = calcVol(logdia, loglength, num42);
                mVol42Text.setText(String.valueOf(vol42));
                volsum = volsum.subtract(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        //直径44cmの入力処理
        mNum44Edit = (EditText) findViewById(R.id.eTextNum44);
        mVol44Text = (TextView) findViewById(R.id.textVol44);
        mAdd44ImgButton = (ImageButton) findViewById(R.id.iButtonAdd44);
        mSub44ImgButton = (ImageButton) findViewById(R.id.iButtonSub44);

        mAdd44ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(dia44_se, 1F, 1F, 0, 0, 1F);
                num44 += 1;
                mNum44Edit.setText(String.valueOf(num44));
                numsum += 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 44;
                vol44 = calcVol(logdia, loglength, num44);
                mVol44Text.setText(String.valueOf(vol44));
                volsum = volsum.add(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        mSub44ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num44 -= 1;
                mNum44Edit.setText(String.valueOf(num44));
                numsum -= 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 44;
                vol44 = calcVol(logdia, loglength, num44);
                mVol44Text.setText(String.valueOf(vol44));
                volsum = volsum.subtract(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        //直径46cmの入力処理
        mNum46Edit = (EditText) findViewById(R.id.eTextNum46);
        mVol46Text = (TextView) findViewById(R.id.textVol46);
        mAdd46ImgButton = (ImageButton) findViewById(R.id.iButtonAdd46);
        mSub46ImgButton = (ImageButton) findViewById(R.id.iButtonSub46);

        mAdd46ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(dia46_se, 1F, 1F, 0, 0, 1F);
                num46 += 1;
                mNum46Edit.setText(String.valueOf(num46));
                numsum += 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 46;
                vol46 = calcVol(logdia, loglength, num46);
                mVol46Text.setText(String.valueOf(vol46));
                volsum = volsum.add(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        mSub46ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num46 -= 1;
                mNum46Edit.setText(String.valueOf(num46));
                numsum -= 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 46;
                vol46 = calcVol(logdia, loglength, num46);
                mVol46Text.setText(String.valueOf(vol46));
                volsum = volsum.subtract(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        //直径48cmの入力処理
        mNum48Edit = (EditText) findViewById(R.id.eTextNum48);
        mVol48Text = (TextView) findViewById(R.id.textVol48);
        mAdd48ImgButton = (ImageButton) findViewById(R.id.iButtonAdd48);
        mSub48ImgButton = (ImageButton) findViewById(R.id.iButtonSub48);

        mAdd48ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(dia48_se, 1F, 1F, 0, 0, 1F);
                num48 += 1;
                mNum48Edit.setText(String.valueOf(num48));
                numsum += 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 48;
                vol48 = calcVol(logdia, loglength, num48);
                mVol48Text.setText(String.valueOf(vol48));
                volsum = volsum.add(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        mSub48ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num48 -= 1;
                mNum48Edit.setText(String.valueOf(num48));
                numsum -= 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 48;
                vol48 = calcVol(logdia, loglength, num48);
                mVol48Text.setText(String.valueOf(vol48));
                volsum = volsum.subtract(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        //直径50cmの入力処理
        mNum50Edit = (EditText) findViewById(R.id.eTextNum50);
        mVol50Text = (TextView) findViewById(R.id.textVol50);
        mAdd50ImgButton = (ImageButton) findViewById(R.id.iButtonAdd50);
        mSub50ImgButton = (ImageButton) findViewById(R.id.iButtonSub50);

        mAdd50ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(dia50_se, 1F, 1F, 0, 0, 1F);
                num50 += 1;
                mNum50Edit.setText(String.valueOf(num50));
                numsum += 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 50;
                vol50 = calcVol(logdia, loglength, num50);
                mVol50Text.setText(String.valueOf(vol50));
                volsum = volsum.add(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        mSub50ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num50 -= 1;
                mNum50Edit.setText(String.valueOf(num50));
                numsum -= 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 50;
                vol50 = calcVol(logdia, loglength, num50);
                mVol50Text.setText(String.valueOf(vol50));
                volsum = volsum.subtract(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        //直径52cmの入力処理
        mNum52Edit = (EditText) findViewById(R.id.eTextNum52);
        mVol52Text = (TextView) findViewById(R.id.textVol52);
        mAdd52ImgButton = (ImageButton) findViewById(R.id.iButtonAdd52);
        mSub52ImgButton = (ImageButton) findViewById(R.id.iButtonSub52);

        mAdd52ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(dia52_se, 1F, 1F, 0, 0, 1F);
                num52 += 1;
                mNum52Edit.setText(String.valueOf(num52));
                numsum += 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 52;
                vol52 = calcVol(logdia, loglength, num52);
                mVol52Text.setText(String.valueOf(vol52));
                volsum = volsum.add(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        mSub52ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num52 -= 1;
                mNum52Edit.setText(String.valueOf(num52));
                numsum -= 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 52;
                vol52 = calcVol(logdia, loglength, num52);
                mVol52Text.setText(String.valueOf(vol52));
                volsum = volsum.subtract(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        //直径54cmの入力処理
        mNum54Edit = (EditText) findViewById(R.id.eTextNum54);
        mVol54Text = (TextView) findViewById(R.id.textVol54);
        mAdd54ImgButton = (ImageButton) findViewById(R.id.iButtonAdd54);
        mSub54ImgButton = (ImageButton) findViewById(R.id.iButtonSub54);

        mAdd54ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(dia54_se, 1F, 1F, 0, 0, 1F);
                num54 += 1;
                mNum54Edit.setText(String.valueOf(num54));
                numsum += 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 54;
                vol54 = calcVol(logdia, loglength, num54);
                mVol54Text.setText(String.valueOf(vol54));
                volsum = volsum.add(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        mSub54ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num54 -= 1;
                mNum54Edit.setText(String.valueOf(num54));
                numsum -= 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 54;
                vol54 = calcVol(logdia, loglength, num54);
                mVol54Text.setText(String.valueOf(vol54));
                volsum = volsum.subtract(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        //直径56cmの入力処理
        mNum56Edit = (EditText) findViewById(R.id.eTextNum56);
        mVol56Text = (TextView) findViewById(R.id.textVol56);
        mAdd56ImgButton = (ImageButton) findViewById(R.id.iButtonAdd56);
        mSub56ImgButton = (ImageButton) findViewById(R.id.iButtonSub56);

        mAdd56ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(dia56_se, 1F, 1F, 0, 0, 1F);
                num56 += 1;
                mNum56Edit.setText(String.valueOf(num56));
                numsum += 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 56;
                vol56 = calcVol(logdia, loglength, num56);
                mVol56Text.setText(String.valueOf(vol56));
                volsum = volsum.add(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        mSub56ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num56 -= 1;
                mNum56Edit.setText(String.valueOf(num56));
                numsum -= 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 56;
                vol56 = calcVol(logdia, loglength, num56);
                mVol56Text.setText(String.valueOf(vol56));
                volsum = volsum.subtract(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        //直径58cmの入力処理
        mNum58Edit = (EditText) findViewById(R.id.eTextNum58);
        mVol58Text = (TextView) findViewById(R.id.textVol58);
        mAdd58ImgButton = (ImageButton) findViewById(R.id.iButtonAdd58);
        mSub58ImgButton = (ImageButton) findViewById(R.id.iButtonSub58);

        mAdd58ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(dia58_se, 1F, 1F, 0, 0, 1F);
                num58 += 1;
                mNum58Edit.setText(String.valueOf(num58));
                numsum += 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 58;
                vol58 = calcVol(logdia, loglength, num58);
                mVol58Text.setText(String.valueOf(vol58));
                volsum = volsum.add(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        mSub58ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num58 -= 1;
                mNum58Edit.setText(String.valueOf(num58));
                numsum -= 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 58;
                vol58 = calcVol(logdia, loglength, num58);
                mVol58Text.setText(String.valueOf(vol58));
                volsum = volsum.subtract(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        //直径60cmの入力処理
        mNum60Edit = (EditText) findViewById(R.id.eTextNum60);
        mVol60Text = (TextView) findViewById(R.id.textVol60);
        mAdd60ImgButton = (ImageButton) findViewById(R.id.iButtonAdd60);
        mSub60ImgButton = (ImageButton) findViewById(R.id.iButtonSub60);

        mAdd60ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(dia60_se, 1F, 1F, 0, 0, 1F);
                num60 += 1;
                mNum60Edit.setText(String.valueOf(num60));
                numsum += 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 60;
                vol60 = calcVol(logdia, loglength, num60);
                mVol60Text.setText(String.valueOf(vol60));
                volsum = volsum.add(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        mSub60ImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num60 -= 1;
                mNum60Edit.setText(String.valueOf(num60));
                numsum -= 1;
                mNumSumText.setText(String.valueOf(numsum));
                logdia = 60;
                vol60 = calcVol(logdia, loglength, num60);
                mVol60Text.setText(String.valueOf(vol60));
                volsum = volsum.subtract(calcVol(logdia, loglength, 1));
                mVolSumText.setText(String.valueOf(volsum));
            }
        });

        if (mRecord == null) {
            //長さを受け取る
            field = intent.getStringExtra("field");
            destination = intent.getStringExtra("destination");
            species = intent.getStringExtra("species");
            loglength = intent.getIntExtra("length", 0);
            mFieldText.setText(field);
            mDestinationText.setText(destination);
            mSpeciesText.setText(species);
            mLengthText.setText(String.valueOf(loglength));
        } else {
            field = mRecord.getRecField();
            mFieldText.setText(field);

            destination = mRecord.getRecDestination();
            mDestinationText.setText(destination);

            species= mRecord.getRecSpecies();
            mSpeciesText.setText(species);

            loglength = mRecord.getRecLength();
            mLengthText.setText(String.valueOf(loglength));

            num8 = mRecord.getRecNum8();
            mNum8Edit.setText(String.valueOf(num8));
            vol8 = calcVol(8, loglength, num8);
            mVol8Text.setText(String.valueOf(vol8));
            volsum = volsum.add(vol8);

            num9 = mRecord.getRecNum9();
            mNum9Edit.setText(String.valueOf(num9));
            vol9 = calcVol(9, loglength, num9);
            mVol9Text.setText(String.valueOf(vol9));
            volsum = volsum.add(vol9);

            num10 = mRecord.getRecNum10();
            mNum10Edit.setText(String.valueOf(num10));
            vol10 = calcVol(10, loglength, num10);
            mVol10Text.setText(String.valueOf(vol10));
            volsum = volsum.add(vol10);

            num11 = mRecord.getRecNum11();
            mNum11Edit.setText(String.valueOf(num11));
            vol11 = calcVol(11, loglength, num11);
            mVol11Text.setText(String.valueOf(vol11));
            volsum = volsum.add(vol11);

            num12 = mRecord.getRecNum12();
            mNum12Edit.setText(String.valueOf(num12));
            vol12 = calcVol(12, loglength, num12);
            mVol12Text.setText(String.valueOf(vol12));
            volsum = volsum.add(vol12);

            num13 = mRecord.getRecNum13();
            mNum13Edit.setText(String.valueOf(num13));
            vol13 = calcVol(13, loglength, num13);
            mVol13Text.setText(String.valueOf(vol13));
            volsum = volsum.add(vol13);

            num14 = mRecord.getRecNum14();
            mNum14Edit.setText(String.valueOf(num14));
            vol14 = calcVol(14, loglength, num14);
            mVol14Text.setText(String.valueOf(vol14));
            volsum = volsum.add(vol14);

            num16 = mRecord.getRecNum16();
            mNum16Edit.setText(String.valueOf(num16));
            vol16 = calcVol(16, loglength, num16);
            mVol16Text.setText(String.valueOf(vol16));
            volsum = volsum.add(vol16);

            num18 = mRecord.getRecNum18();
            mNum18Edit.setText(String.valueOf(num18));
            vol18 = calcVol(18, loglength, num18);
            mVol18Text.setText(String.valueOf(vol18));
            volsum = volsum.add(vol18);

            num20 = mRecord.getRecNum20();
            mNum20Edit.setText(String.valueOf(num20));
            vol20 = calcVol(20, loglength, num20);
            mVol20Text.setText(String.valueOf(vol20));
            volsum = volsum.add(vol20);

            num22 = mRecord.getRecNum22();
            mNum22Edit.setText(String.valueOf(num22));
            vol22 = calcVol(22, loglength, num22);
            mVol22Text.setText(String.valueOf(vol22));
            volsum = volsum.add(vol22);

            num24 = mRecord.getRecNum24();
            mNum24Edit.setText(String.valueOf(num24));
            vol24 = calcVol(24, loglength, num24);
            mVol24Text.setText(String.valueOf(vol24));
            volsum = volsum.add(vol24);

            num26 = mRecord.getRecNum26();
            mNum26Edit.setText(String.valueOf(num26));
            vol26 = calcVol(26, loglength, num26);
            mVol26Text.setText(String.valueOf(vol26));
            volsum = volsum.add(vol26);

            num28 = mRecord.getRecNum28();
            mNum28Edit.setText(String.valueOf(num28));
            vol28 = calcVol(28, loglength, num28);
            mVol28Text.setText(String.valueOf(vol28));
            volsum = volsum.add(vol28);

            num30 = mRecord.getRecNum30();
            mNum30Edit.setText(String.valueOf(num30));
            vol30 = calcVol(30, loglength, num30);
            mVol30Text.setText(String.valueOf(vol30));
            volsum = volsum.add(vol30);

            num32 = mRecord.getRecNum32();
            mNum32Edit.setText(String.valueOf(num32));
            vol32 = calcVol(32, loglength, num32);
            mVol32Text.setText(String.valueOf(vol32));
            volsum = volsum.add(vol32);

            num34 = mRecord.getRecNum34();
            mNum34Edit.setText(String.valueOf(num34));
            vol34 = calcVol(34, loglength, num34);
            mVol34Text.setText(String.valueOf(vol34));
            volsum = volsum.add(vol34);

            num36 = mRecord.getRecNum36();
            mNum36Edit.setText(String.valueOf(num36));
            vol36 = calcVol(36, loglength, num36);
            mVol36Text.setText(String.valueOf(vol36));
            volsum = volsum.add(vol36);

            num38 = mRecord.getRecNum38();
            mNum38Edit.setText(String.valueOf(num38));
            vol38 = calcVol(38, loglength, num38);
            mVol38Text.setText(String.valueOf(vol38));
            volsum = volsum.add(vol38);

            num40 = mRecord.getRecNum40();
            mNum40Edit.setText(String.valueOf(num40));
            vol40 = calcVol(40, loglength, num40);
            mVol40Text.setText(String.valueOf(vol40));
            volsum = volsum.add(vol40);

            num42 = mRecord.getRecNum42();
            mNum42Edit.setText(String.valueOf(num42));
            vol42 = calcVol(42, loglength, num42);
            mVol42Text.setText(String.valueOf(vol42));
            volsum = volsum.add(vol42);

            num44 = mRecord.getRecNum44();
            mNum44Edit.setText(String.valueOf(num44));
            vol44 = calcVol(44, loglength, num44);
            mVol44Text.setText(String.valueOf(vol44));
            volsum = volsum.add(vol44);

            num46 = mRecord.getRecNum46();
            mNum46Edit.setText(String.valueOf(num46));
            vol46 = calcVol(46, loglength, num46);
            mVol46Text.setText(String.valueOf(vol46));
            volsum = volsum.add(vol46);

            num48 = mRecord.getRecNum48();
            mNum48Edit.setText(String.valueOf(num48));
            vol48 = calcVol(48, loglength, num48);
            mVol48Text.setText(String.valueOf(vol48));
            volsum = volsum.add(vol48);

            num50 = mRecord.getRecNum50();
            mNum50Edit.setText(String.valueOf(num50));
            vol50 = calcVol(50, loglength, num50);
            mVol50Text.setText(String.valueOf(vol50));
            volsum = volsum.add(vol50);

            num52 = mRecord.getRecNum52();
            mNum52Edit.setText(String.valueOf(num52));
            vol52 = calcVol(52, loglength, num52);
            mVol52Text.setText(String.valueOf(vol52));
            volsum = volsum.add(vol52);

            num54 = mRecord.getRecNum54();
            mNum54Edit.setText(String.valueOf(num54));
            vol54 = calcVol(54, loglength, num54);
            mVol54Text.setText(String.valueOf(vol54));
            volsum = volsum.add(vol54);

            num56 = mRecord.getRecNum56();
            mNum56Edit.setText(String.valueOf(num56));
            vol56 = calcVol(56, loglength, num56);
            mVol56Text.setText(String.valueOf(vol56));
            volsum = volsum.add(vol56);

            num58 = mRecord.getRecNum58();
            mNum58Edit.setText(String.valueOf(num58));
            vol58 = calcVol(58, loglength, num58);
            mVol58Text.setText(String.valueOf(vol58));
            volsum = volsum.add(vol58);

            num60 = mRecord.getRecNum60();
            mNum60Edit.setText(String.valueOf(num60));
            vol60 = calcVol(60, loglength, num60);
            mVol60Text.setText(String.valueOf(vol60));
            volsum = volsum.add(vol60);

            numsum = num8 + num9 + num10 + num11 + num12 + num13 + num14 + num16 + num18 + num20 +
                      num22 + num24 + num26 + num28 + num30 + num32 + num34 + num36 + num38 + num40 +
                      num42 + num44 + num46 + num48 + num50 + num52 + num54 + num56 + num58 + num60;
            mNumSumText.setText(String.valueOf(numsum));
            mVolSumText.setText(String.valueOf(volsum));
        }
    }

    private BigDecimal calcVol(int dia, int length, int num) {
        double vol;
        if (length < 6) {
            vol = dia * dia * length;
            vol *= 0.0001;
            BigDecimal x = new BigDecimal(vol);
            x = x.setScale(3, BigDecimal.ROUND_HALF_UP);
            BigDecimal y = BigDecimal.valueOf(num);
            return x.multiply(y);
        } else {
            vol = (dia + (length - 4) / 2) * (dia + (length - 4) / 2) * length;
            vol *= 0.0001;
            BigDecimal x = new BigDecimal(vol);
            x = x.setScale(3, BigDecimal.ROUND_HALF_UP);
            BigDecimal y = BigDecimal.valueOf(num);
            return x.multiply(y);
        }
    }

    private void addRecord() {
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();

        if (mRecord == null) {
            // 新規作成の場合
            mRecord = new Record();

            RealmResults<Record> recordRealmResults = realm.where(Record.class).findAll();

            int identifier;
            if (recordRealmResults.max("id") != null) {
                identifier = recordRealmResults.max("id").intValue() + 1;
            } else {
                identifier = 0;
            }
            mRecord.setId(identifier);
        }

        mRecord.setRecField(field);
        mRecord.setRecDestination(destination);
        mRecord.setRecLength(loglength);
        mRecord.setRecSpecies(species);
        mRecord.setRecNumSum(numsum);
        mRecord.setRecVolSum(volsum.toString());
        mRecord.setRecNum8(num8);
        mRecord.setRecNum9(num9);
        mRecord.setRecNum10(num10);
        mRecord.setRecNum11(num11);
        mRecord.setRecNum12(num12);
        mRecord.setRecNum13(num13);
        mRecord.setRecNum14(num14);
        mRecord.setRecNum16(num16);
        mRecord.setRecNum18(num18);
        mRecord.setRecNum20(num20);
        mRecord.setRecNum22(num22);
        mRecord.setRecNum24(num24);
        mRecord.setRecNum26(num26);
        mRecord.setRecNum28(num28);
        mRecord.setRecNum30(num30);
        mRecord.setRecNum32(num32);
        mRecord.setRecNum34(num34);
        mRecord.setRecNum36(num36);
        mRecord.setRecNum38(num38);
        mRecord.setRecNum40(num40);
        mRecord.setRecNum42(num42);
        mRecord.setRecNum44(num44);
        mRecord.setRecNum46(num46);
        mRecord.setRecNum48(num48);
        mRecord.setRecNum50(num50);
        mRecord.setRecNum52(num52);
        mRecord.setRecNum54(num54);
        mRecord.setRecNum56(num56);
        mRecord.setRecNum58(num58);
        mRecord.setRecNum60(num60);

        Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        mHour = calendar.get(Calendar.HOUR_OF_DAY);
        mMinute = calendar.get(Calendar.MINUTE);
        GregorianCalendar calendar2 = new GregorianCalendar(mYear,mMonth,mDay,mHour,mMinute);
        Date date = calendar2.getTime();
        mRecord.setDate(date);

        realm.copyToRealmOrUpdate(mRecord);
        realm.commitTransaction();

        realm.close();
    }
}

