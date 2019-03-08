package com.yusufalicezik.jsoup_project;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class CustomAdapter extends  RecyclerView.Adapter<CustomAdapter.CustomAdapterViewHolder>  {


    private ArrayList<Yemek> tumYemekler;
    Context mContext;


    public CustomAdapter(ArrayList<Yemek>tumYemekler,Context context)
    {
        this.tumYemekler=tumYemekler;
        this.mContext=context;
    }



    @NonNull
    @Override
    public CustomAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {


        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list,parent,false);
        return new CustomAdapterViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapterViewHolder holder, int i) {

        holder.gun.setText(tumYemekler.get(i).getGun());
        holder.tarih.setText(tumYemekler.get(i).getTarih());
        holder.yemek.setText(tumYemekler.get(i).getYemekListesi());

        final Calendar takvim = Calendar.getInstance();
        int yil = takvim.get(Calendar.YEAR);
        int ay = takvim.get(Calendar.MONTH);
        int gun = takvim.get(Calendar.DAY_OF_MONTH)-1;

        GregorianCalendar GregorianCalendar = new GregorianCalendar(yil, ay, gun);

        int dayOfWeek=GregorianCalendar.get(takvim.DAY_OF_WEEK);

        String currentGun;
        switch (dayOfWeek){
            case 1:currentGun="Pazartesi";
                break;
            case 2:currentGun="Salı";
                break;
            case 3:currentGun="Çarşamba";
                break;
            case 4:currentGun="Perşembe";
                break;
            case 5:currentGun="Cuma";
                break;
            default:
                currentGun="";
                break;
        }


        //eğer bugünse arkaplanı değiştir.
        if(currentGun.equalsIgnoreCase(tumYemekler.get(i).getGun())){
            holder.allLayout.setBackgroundColor(Color.RED);
        }



    }

    @Override
    public int getItemCount() {
        return tumYemekler.size();
    }

    public class CustomAdapterViewHolder extends RecyclerView.ViewHolder {
        public TextView tarih;
        public TextView gun;
        public TextView yemek;
        public ConstraintLayout allLayout;

        public CustomAdapterViewHolder(View itemView){
            super(itemView);
            tarih=itemView.findViewById(R.id.customTextTarih);
            gun=itemView.findViewById(R.id.customTextGun);
            yemek=itemView.findViewById(R.id.customTextYemekListesi);
            allLayout=itemView.findViewById(R.id.allLayout);

        }
    }
}
