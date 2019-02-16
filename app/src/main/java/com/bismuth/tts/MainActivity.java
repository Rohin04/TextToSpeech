package com.bismuth.tts;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextToSpeech mTTs;
    private WebView webview;
   private Button speak;
   private Button browser;
   private EditText enter;
   private EditText url;
   private EditText urlget;
private Button scan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        speak = (Button) findViewById(R.id.dictate);
        speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dictate();
            }
        });



        browser = (Button)findViewById(R.id.browser);
        browser.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                openWebView();
            }
        });

        scan = (Button) findViewById(R.id.scanmainbtn);
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScan();

            }
        });



        enter = (EditText)findViewById(R.id.entertext);
        url = (EditText)findViewById(R.id.urledit);

        mTTs = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status==TextToSpeech.SUCCESS)
                {
                    int result = mTTs.setLanguage(Locale.ENGLISH);
                    if(result==TextToSpeech.LANG_MISSING_DATA || result==TextToSpeech.LANG_NOT_SUPPORTED)
                    {

                        Log.e("TTS","Language not supported");
                    }
                    else
                    {

                        Log.e("TTS","Initialization failed");
                    }
                }
            }
        });
    }

    public  void dictate()
    {

        String text = enter.getText().toString();

        mTTs.setPitch(10/50);
        mTTs.setSpeechRate(10/50);
        mTTs.speak(text,TextToSpeech.QUEUE_FLUSH,null);


        //Diction goes here
    }

    public void openWebView()
    {
        Intent i = new Intent(this, webView.class);
        startActivity(i);
        finish();

    }

    public void openScan()
    {
        Intent i = new Intent(this, scantext.class);
        startActivity(i);
        finish();

    }



    protected  void onDestroy()
    {

        if(mTTs!=null)
        {
            mTTs.stop();
            mTTs.shutdown();
        }
        super.onDestroy();
    }







}
