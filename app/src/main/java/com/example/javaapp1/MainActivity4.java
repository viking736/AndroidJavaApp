package com.example.javaapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity4 extends AppCompatActivity {
    public ListView list;
    public String[] names = new String[] {"Bob", "Alex", "Michael", "George", "John"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        list();
    }

    public void list(){
        list = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.names, names);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value  = (String) list.getItemAtPosition(position);
                Toast.makeText(MainActivity4.this, "Position " + value, Toast.LENGTH_LONG).show();
            }
        });
    }
}