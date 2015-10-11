package com.example.whugis.gis4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GetCurrentLocation extends AppCompatActivity {

    public static final int FORQR = 1;

    Bundle QRContent = null;
    String QRContentStr = null;

    int GisLocation = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_current_location);
    }


    @Override
    protected void onResume() {
        super.onResume();

        if(QRContent == null)
        {
            captureQRAgain(findViewById(R.id.captureQRAgain));
        }


    }

    public void captureQRAgain(View view)
    {
        Intent intent = new Intent(this, CaptureQR.class);

        startActivityForResult(intent, FORQR);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode)
        {
            case FORQR:
                if(resultCode == RESULT_OK)
                {
                    QRContent = data.getExtras();
                    QRContentStr = QRContent.getString("QRContentStr");



                    TextView textView = (TextView) findViewById(R.id.currentLocation_TextView);
                    textView.setText( QRContentStr);
                }
                break;
        }
    }

    public void returnCurrentLocation(View view)
    {
        Bundle bundle = new Bundle();
        bundle.putString("QRContentStr", QRContentStr);
        bundle.putInt("GisLocation", QRContentStr.length());

        Intent intent = new Intent();
        intent.putExtras(bundle);

        this.setResult(RESULT_OK, intent);
        GetCurrentLocation.this.finish();
    }


}
