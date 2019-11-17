package com.example.kaseerr.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kaseerr.Model.BarangModel;
import com.example.kaseerr.R;

import java.util.List;

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.MyViewHolder> {

    private List<BarangModel> barangList;
    private Context context;
    private static RecyclerViewClickListener itemListener;

    public BarangAdapter(Context context, List<BarangModel> barangList, RecyclerViewClickListener itemListener) {
        this.barangList = barangList;
        this.context = context;
        this.itemListener = itemListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_barang, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        BarangModel barangModel = barangList.get(position);

        holder.TxtKode.setText(barangModel.getKode());
        holder.TxtNama.setText(barangModel.getNama());
        holder.TxtHarga.setText(Integer.toString(barangModel.getHarga_beli()));
        holder.TxtKategori.setText(barangModel.getQty());
    }

    @Override
    public int getItemCount() {
        return barangList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView TxtKode;
        TextView TxtNama;
        TextView TxtKategori;
        TextView TxtHarga;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            TxtKode = itemView.findViewById(R.id.kode);
            TxtNama = itemView.findViewById(R.id.nama);
            TxtKategori = itemView.findViewById(R.id.kategori);
            TxtHarga = itemView.findViewById(R.id.harga);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            String id = TxtKode.getText().toString();
            itemListener.recyclerViewListClicked(view, id);
//            Toast.makeText(context, id, Toast.LENGTH_SHORT).show();
        }
    }
}
