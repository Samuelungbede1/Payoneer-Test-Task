package com.example.payoneertesttask.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.payoneertesttask.R;
import com.example.payoneertesttask.data.Applicable;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private List<Applicable> listItems;
    public  void setListItems ( List<Applicable> listItems){
        this.listItems = listItems;

    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_method_item, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
        holder.paymentTextView.setText(listItems.get(position).getLabel());
        Glide.with(holder.paymentImageView)
                .load(listItems.get(position).getLinks().getLogo())
                .into(holder.paymentImageView);
    }



    @Override
    public int getItemCount() {
        if(listItems == null){
            return 0;
        } else {
        return listItems.size();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView paymentImageView;
        TextView paymentTextView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            paymentImageView = itemView.findViewById(R.id.iv_image);
            paymentTextView = itemView.findViewById(R.id.textView);
        }
    }
}
