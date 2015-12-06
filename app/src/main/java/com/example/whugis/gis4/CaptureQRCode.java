package com.example.whugis.gis4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class CaptureQRCode extends AppCompatActivity
{
    IntentIntegrator integrator = null;



    IntentResult result;
    String QRContent;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        result = null;
        QRContent = null;
    }

    @Override
    protected void onResume()
    {
        super.onResume();


            integrator = new IntentIntegrator(this);
            integrator.addExtra("SCAN_WIDTH", 640);
            integrator.addExtra("SCAN_HEIGHT", 480);
            integrator.addExtra("SCAN_MODE", "QR_CODE_MODE,PRODUCT_MODE");
            //customize the prompt message before scanning
            integrator.addExtra("PROMPT_MESSAGE", "Scanner Start!" +Double.toString( Math.random()));
            integrator.initiateScan();


    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent)
    {

        result = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (result != null)
        {
            QRContent = result.getContents();
            if (QRContent != null)
            {
                Intent intent1 = new Intent();
                intent1.putExtra("QRContent", QRContent);
                this.setResult(RESULT_OK, intent1);
                this.finish();
            }
        }

            this.setResult(RESULT_CANCELED);
            this.finish();

    }
}
