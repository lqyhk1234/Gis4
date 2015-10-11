package com.example.whugis.gis4;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class CaptureQR extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_qr);
    }


    String QRContentStr;

    public CaptureQR() {
        QRContentStr = Double.toString(Math.random());
    }

    @Override
    protected void onResume() {
        super.onResume();
/*
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.addExtra("SCAN_WIDTH", 640);
        integrator.addExtra("SCAN_HEIGHT", 480);
        integrator.addExtra("SCAN_MODE", "QR_CODE_MODE,PRODUCT_MODE");
        //customize the prompt message before scanning
        integrator.addExtra("PROMPT_MESSAGE", "Scanner Start!");
        integrator.initiateScan(IntentIntegrator.PRODUCT_CODE_TYPES);
*/
        scanQRCode(findViewById(R.id.button4));
    }

    public void doneScan(View view)
    {

        Bundle bundle = new Bundle();










        bundle.putString("QRContentStr", QRContentStr);
        Intent intent = new Intent();
        intent.putExtras(bundle);

        this.setResult(RESULT_OK, intent);
        this.finish();
    }


    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult result =
                IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (result != null) {
            String contents = result.getContents();
            if (contents != null) {
                showDialog(0, result.toString());
            } else {
                showDialog(0,
                        "Fail");
            }
        }
    }


    public void scanQRCode(View v) {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.initiateScan(IntentIntegrator.QR_CODE_TYPES);
    }

    private void showDialog(int title, CharSequence message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }



}
