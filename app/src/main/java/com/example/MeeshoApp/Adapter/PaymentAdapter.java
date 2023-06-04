package com.example.MeeshoApp.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MeeshoApp.Fragment.PaymentFragment;
import com.example.MeeshoApp.Model.MenModel;
import com.example.MeeshoApp.Model.PaymentModel;
import com.example.MeeshoApp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.ViewHolder> {

    Context context;
    ArrayList<PaymentModel> paylist;
    public static int selected = -1;

    PaymentAdapter.OnAdapterclickListner listner;
    public interface OnAdapterclickListner {
        void onItemClick( int position);
        void onDeleteClick( int position);
        void onEditClick( int position);


    }

    public PaymentAdapter(Context context, ArrayList<PaymentModel> paylist,  PaymentAdapter.OnAdapterclickListner listne) {
        this.context = context;
        this.paylist = paylist;
        this.listner = listne;
    }

    @NonNull
    @Override
    public PaymentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items_pay,null);
        return  new PaymentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentAdapter.ViewHolder holder, int position) {
       PaymentModel paymentModel = paylist.get(position);

        holder.iv_pay_logo.setImageResource(paymentModel.getIv_pay_logo());
        holder.pay_name.setText(paymentModel.getTv_pay_name());
        holder.upi_options.setOnCheckedChangeListener(null);
        holder.upi_options.setChecked(position == selected);
        holder.upi_options.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                      listner.onItemClick(position);
                        selected = holder.getAdapterPosition();
                        notifyDataSetChanged();

            }
        });



    }

    @Override
    public int getItemCount() {
        return paylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_pay_logo;
        TextView pay_name;

        RadioButton upi_options;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_pay_logo = itemView.findViewById(R.id.iv_pay);
            pay_name = itemView.findViewById(R.id.tv_pay);
            upi_options = itemView.findViewById(R.id.pay_op_selection);


        }
    }
}
