package com.example.whugis.gis4;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StartMenu extends AppCompatActivity {

    enum Request {GET_CURRENT_LOCATION}


    String QRContentStr = null;
    int GisLocation = 0;

    TextView currentLocation_Textview = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentLocation_Textview = (TextView) findViewById(R.id.currentLocation_Textview);
    }


    public void getLocation(View view)
    {
        Intent intent_GetCurrentLocation = new Intent(this, GetCurrentLocation.class);
        startActivityForResult(intent_GetCurrentLocation, Request.GET_CURRENT_LOCATION.ordinal());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        if (resultCode == Activity.RESULT_OK && requestCode == Request.GET_CURRENT_LOCATION.ordinal())
        {
            Bundle bundle = data.getExtras();
            QRContentStr = bundle.getString("QRContent");
            GisLocation = bundle.getInt("GisLocation");

            currentLocation_Textview.append("\nQRContent = " + QRContentStr + "\n");
            currentLocation_Textview.append("GisLocation = " + Integer.toString(GisLocation) + "\n");
        }
        else if(resultCode == Activity.RESULT_CANCELED && requestCode == Request.GET_CURRENT_LOCATION.ordinal())
        {
            currentLocation_Textview.append("GetCurrentLocation Failed\n");
        }

    }

}
