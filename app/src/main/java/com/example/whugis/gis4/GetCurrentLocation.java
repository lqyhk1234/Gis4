package com.example.whugis.gis4;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.esri.android.map.FeatureLayer;
import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISDynamicMapServiceLayer;
import com.esri.android.toolkit.map.MapViewHelper;
import com.esri.core.geodatabase.ShapefileFeatureTable;
import com.esri.core.geometry.Envelope;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.FileHandler;

public class GetCurrentLocation extends AppCompatActivity {

    TextView currentLocationCheck_Textview = null;
    MapView currentLocation_Mapview = null;

    SDCardHelper sdCardHelper = SDCardHelper.getSingletonInstance();




    Bundle QRContentBundle = null;
    String QRContent = null;

    int GisLocation = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_current_location);

        currentLocationCheck_Textview = (TextView) findViewById(R.id.currentLocationCheck_TextView);
        currentLocation_Mapview = (MapView) findViewById(R.id.currentLocation_Mapview);

        // currentLocation_Mapview.addLayer(new ArcGISDynamicMapServiceLayer("" +
        //        "http://services.arcgisonline.com/ArcGIS/rest/services/World_Street_Map/MapServer"));


   /*     for (String shpPath : SDCardHelper.mapFiles) {
            String wholeshpPath = sdCardHelper.getSDPath() + shpPath;
            try {

                ShapefileFeatureTable shapefileFeatureTable = new ShapefileFeatureTable(wholeshpPath);

                FeatureLayer featureLayer = new FeatureLayer(shapefileFeatureTable);

                currentLocation_Mapview.addLayer(featureLayer);

            } catch (FileNotFoundException e) {

              currentLocationCheck_Textview.append(e.getMessage());

            }

        }*/

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
