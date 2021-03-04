package com.example.newfire;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RetrieveData extends AppCompatActivity {

    TextView tvMsg;
    ListView lvShow;

    ArrayList<String> myArrayList = new ArrayList<>();
    DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_data);

        final ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,myArrayList);

        tvMsg = findViewById(R.id.tvMsg);
        lvShow  = findViewById(R.id.lvShow);
        lvShow.setAdapter(myArrayAdapter);


        mRef = FirebaseDatabase.getInstance().getReference("https://newfire-f198c-default-rtdb.firebaseio.com/").child("Data");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Data value = dataSnapshot.getValue(Data.class);
                String item = value.name;
                myArrayList.add(item);
                myArrayAdapter.notifyDataSetChanged();
             // ok ise usb

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}