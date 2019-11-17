package com.example.kaseerr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kaseerr.Adapter.BarangAdapter;
import com.example.kaseerr.Adapter.RecyclerViewClickListener;
import com.example.kaseerr.Database.DatabaseHandler;
import com.example.kaseerr.Model.BarangModel;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RecyclerViewClickListener {

    EditText kodeBarang;
    EditText namaBarang;
    EditText satuanBarang;
    EditText hargaBeli;
    EditText hargaJual;
    EditText diskon;
    Button simpan;
    RecyclerView recyclerViewBarang;

    private DatabaseHandler db;
    String kodeBrg;
    String namaBrg;
    String satuanBrg;
    int hrgBeli;
    int hrgJual;
    int diskonn;

    BarangAdapter barangAdapter;
    private List<BarangModel> barangList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inisializationView();
        databaseCall();
        recyclerViewInit();
        
        simpan = findViewById(R.id.btnSimpan);
        simpan.setOnClickListener(this);
    }

    private void inisializationView(){
        recyclerViewBarang = findViewById(R.id.recyclerBarang);
        kodeBarang = findViewById(R.id.kodeBarang);
        namaBarang = findViewById(R.id.namaBarang);
        satuanBarang = findViewById(R.id.SatuanBarang);
        hargaBeli = findViewById(R.id.hargaBeli);
        hargaJual = findViewById(R.id.hargaJual);
        diskon = findViewById(R.id.diskon);
    }

    private void databaseCall(){
        DatabaseHandler dbHandler = new DatabaseHandler(this);
        dbHandler.getWritableDatabase();

        db = new DatabaseHandler(this);
        Log.d("TAG", db.getAllRecord().toString());
        barangList.addAll(db.getAllRecord());
    }

    private void recyclerViewInit(){
        barangAdapter = new BarangAdapter(this, barangList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerViewBarang.setItemAnimator(new DefaultItemAnimator());
        recyclerViewBarang.setLayoutManager(mLayoutManager);
        recyclerViewBarang.setAdapter(barangAdapter);
        barangAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        if (view == simpan){
            getViewsData();

            BarangModel barangModel = new BarangModel(kodeBrg, namaBrg, satuanBrg, hrgBeli, hrgJual, diskonn);

            DatabaseHandler db = new DatabaseHandler(this);

            if (db.addRecord(barangModel) == 1){
                Toast.makeText(this, "data berhasil ditambah", Toast.LENGTH_SHORT).show();
            }
            barangAdapter.notifyDataSetChanged();
        }
    }

    private void getViewsData(){
        kodeBrg = kodeBarang.getText().toString();
        namaBrg = namaBarang.getText().toString();
        satuanBrg = satuanBarang.getText().toString();
        hrgBeli = Integer.parseInt(hargaBeli.getText().toString());
        hrgJual = Integer.parseInt(hargaJual.getText().toString());
        diskonn = Integer.parseInt(diskon.getText().toString());
    }

    @Override
    public void recyclerViewListClicked(View v, String id) {

    }
}
