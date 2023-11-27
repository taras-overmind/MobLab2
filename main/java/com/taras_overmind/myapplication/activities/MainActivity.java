package com.taras_overmind.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.taras_overmind.myapplication.CarModel;
import com.taras_overmind.myapplication.DataBaseHandler;
import com.taras_overmind.myapplication.R;

public class MainActivity extends AppCompatActivity {
    Button all_contacts_btn, selected_contacts_btn;
    ListView lv_contactsList;
    ArrayAdapter contactsArrayAdapter;

    TextView editAverageEngineCapacity;

    DataBaseHandler dataBaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        all_contacts_btn = findViewById(R.id.all_cars_btn);
        selected_contacts_btn = findViewById(R.id.selected_cars_btn);
        lv_contactsList = findViewById(R.id.lv_carsList);
        editAverageEngineCapacity = findViewById(R.id.editAverageEngineCapacity);

        dataBaseHandler=new DataBaseHandler(MainActivity.this);


//        dataBaseHandler.addOne(new CarModel("Mercedes", "Sedan", "White", 2100, 25000));
//        dataBaseHandler.addOne(new CarModel("Hyundai", "Universal", "Red", 1600, 13000));
//        dataBaseHandler.addOne(new CarModel("Honda", "Hatchback", "Black", 1700, 14000));
//        dataBaseHandler.addOne(new CarModel("Ford", "Universal", "Red", 2000, 30000));
//        dataBaseHandler.addOne(new CarModel("Reno", "Sedan", "Blue", 1700, 15000));

        all_contacts_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                contactsArrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, dataBaseHandler.getAll());
                lv_contactsList.setAdapter(contactsArrayAdapter);
                editAverageEngineCapacity.setText( "Average engine capacity="+String.valueOf(dataBaseHandler.getAverageEngineCapacity()));
            }
        });

        selected_contacts_btn.setOnClickListener(view -> {

            contactsArrayAdapter = new ArrayAdapter<CarModel>(MainActivity.this, android.R.layout.simple_list_item_1 , dataBaseHandler.getSelectedCars());
            lv_contactsList.setAdapter(contactsArrayAdapter);
            editAverageEngineCapacity.setText( "Average engine capacity="+String.valueOf(dataBaseHandler.getAverageEngineCapacity()));

        });


    }
}