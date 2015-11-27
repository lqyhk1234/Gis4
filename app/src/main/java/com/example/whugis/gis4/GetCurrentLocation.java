package com.example.whugis.gis4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GetCurrentLocation extends AppCompatActivity {

    TextView currentLocationCheck_Textview;


    Bundle QRContentBundle = null;
    String QRContent = null;

    int GisLocation = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_current_location);

        currentLocationCheck_Textview = (TextView) findViewById(R.id.currentLocationCheck_TextView);
    }


    @Override
    protected void onResume() {
        super.onResume();



    }

    public void captureQRAgain(View view)
    {
        Intent intent = new Intent(this, CaptureQRCode.class);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(resultCode == RESULT_OK)
        {
            QRContentBundle = data.getExtras();
            QRContent = QRContentBundle.getString("QRContent");

            currentLocationCheck_Textview.append("\nQRContent = " + QRContent + "\n");
        }
        else
        {
            currentLocationCheck_Textview.append("\n GetQRFailed\n");
        }

    }

    public void returnCurrentLocation(View view)
    {
        Bundle bundle = new Bundle();
        bundle.putString("QRContent", QRContent);
        bundle.putInt("GisLocation", QRContent.length());

        Intent intent = new Intent();
        intent.putExtras(bundle);

        this.setResult(RESULT_OK, intent);
        GetCurrentLocation.this.finish();
    }


}
