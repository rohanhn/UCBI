package com.ucbi.telemed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    String[] mListViewData = {"Vincom Bà Triệu", "Grand Building, Hòa Mã", "Royal City", "Times City"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Điểm kết nối");
        setContentView(R.layout.activity_main);

//    // Example of a call to a native method
//    TextView tv = (TextView) findViewById(R.id.sample_text);
//    tv.setText(stringFromJNI());

        // add Spinner data
        ListView mNodeListSpinner = (ListView) findViewById(R.id.lvNodeList);
        ArrayAdapter<String> mListViewAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mListViewData);
        mNodeListSpinner.setAdapter(mListViewAdapter);
        mNodeListSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent mIntent = new Intent(view.getContext(), ControlAcitivity.class);
                startActivity(mIntent);
            }
        });

    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
}
