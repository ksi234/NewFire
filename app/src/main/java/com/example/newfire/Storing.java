package com.example.newfire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Storing extends AppCompatActivity {

    EditText edtName;
    Button btnSaveData, btnShow;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storing);

        edtName = findViewById(R.id.edt_name);

        btnSaveData = findViewById(R.id.btn_save_data);
        btnShow = findViewById(R.id.btnShow);

        reference = FirebaseDatabase.getInstance().getReference().child("Data");

        btnSaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }

            public void insertData(){
                String name = edtName.getText().toString();

                Data data = new Data(name);
                reference.push().setValue(data);
                Toast.makeText(Storing.this, "Data Inserted Succesfully", Toast.LENGTH_LONG).show();
            }
        });


        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showing();
            }

            public void showing(){

                Intent inten1 = new Intent(Storing.this,RetrieveData.class);
                startActivity(inten1);
            }
        });


    }


}
