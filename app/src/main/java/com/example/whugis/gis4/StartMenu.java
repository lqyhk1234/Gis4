package com.example.whugis.gis4;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StartMenu extends AppCompatActivity {

    int FORLOCATION = 1;

    String QRContentStr = null;
    int GisLocation = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void getLocation(View view) {
        Intent intent1 = new Intent(this, GetCurrentLocation.class);

        startActivityForResult(intent1, FORLOCATION);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {


        if (resultCode == Activity.RESULT_OK && requestCode == FORLOCATION)
        {
            Bundle bundle = data.getExtras();
            QRContentStr = bundle.getString("QRContentStr");
            GisLocation = bundle.getInt("GisLocation");

            TextView textView = (TextView) findViewById(R.id.textView2);
            textView.setText( Integer.toString(GisLocation) + "  " + QRContentStr);
        }

    }

}
