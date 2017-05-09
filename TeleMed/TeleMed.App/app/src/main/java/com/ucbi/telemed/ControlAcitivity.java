package com.ucbi.telemed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.ucbi.libraries.RequestHTTP;

public class ControlAcitivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);
        setTitle("Application Menu");

        final Switch mSwitch1 = (Switch) findViewById(R.id.switch1);
        mSwitch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    mSwitch1.setText("ON");
                    RequestHTTP.makeGetRequest("http://10.32.2.35:8080/TeleMed.Client/start?id=1&special=123");
                } else {
                    mSwitch1.setText("OFF");
                    RequestHTTP.makeGetRequest("http://10.32.2.35:8080/TeleMed.Client/stop?id=1&special=123");
                }
            }
        });
        mSwitch1.setText("");
        //mSwitch1.setChecked(true);

        final Switch mSwitch2 = (Switch) findViewById(R.id.switch2);
        mSwitch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    mSwitch2.setText("ON");
                    RequestHTTP.makeGetRequest("http://10.32.2.35:8080/TeleMed.Client/start?id=2&special=123");
                } else {
                    mSwitch2.setText("OFF");
                    RequestHTTP.makeGetRequest("http://10.32.2.35:8080/TeleMed.Client/stop?id=2&special=123");
                }
            }
        });
        mSwitch2.setText("");
        //mSwitch2.setChecked(true);
    }
}
