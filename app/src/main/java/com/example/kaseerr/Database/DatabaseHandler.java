package com.example.kaseerr.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.kaseerr.Model.BarangModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    SQLiteDatabase db;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db_kasir";
    private static final String TABLE_NAME = "tb_barang_v2";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME+"("+
                "kode_barang  varchar(20) primary key, "+
                "nama_barang varhcar(50)," +
                "qty varchar(50), "+
                "harga_beli int(20), "+
                "harga_jual int(20), "+
                "diskon int(20));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public int addRecord(BarangModel barangModels){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("kode_barang", barangModels.getKode());
        values.put("nama_barang", barangModels.getNama());
        values.put("qty", barangModels.getQty());
        values.put("harga_beli", barangModels.getHarga_beli());
        values.put("harga_jual", barangModels.getHarga_jual());
        values.put("diskon", barangModels.getDiskon());
        db.insert(TABLE_NAME, null, values);
        db.close();

        return 1;
    }

    public List<BarangModel> getAllRecord(){
        List<BarangModel> userList = new ArrayList<>();
        String getAllQuery = "SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(getAllQuery, null);
        if(cursor.moveToFirst()){
            do {
                BarangModel barangModel = new BarangModel(
                        cursor.getString(cursor.getColumnIndex("kode_barang")),
                        cursor.getString(cursor.getColumnIndex("nama_barang")),
                        cursor.getString(cursor.getColumnIndex("qty")),
                        cursor.getInt(cursor.getColumnIndex("harga_beli")),
                        cursor.getInt(cursor.getColumnIndex("harga_jual")),
                        cursor.getInt(cursor.getColumnIndex("diskon"))
                );
                userList.add(barangModel);
            }while (cursor.moveToNext());
        }
        db.close();
        return userList;
    }
}