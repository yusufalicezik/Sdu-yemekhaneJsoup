package com.yusufalicezik.jsoup_project;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.InputStream;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    public static String URL="https://w3.sdu.edu.tr/aylik-yemek-listesi";


    ArrayList<String>yemekListesi=new ArrayList<>();
    ArrayList<String>tarihListesi=new ArrayList<>();
    ArrayList<String>tarihListesiGunler=new ArrayList<>();

    ArrayList<Yemek>tumYemekler=new ArrayList<>();

    RecyclerView listem;
    CustomAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listem=findViewById(R.id.listem);

        adapter=new CustomAdapter(tumYemekler,MainActivity.this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MainActivity.this);
        linearLayoutManager.setStackFromEnd(false);
        listem.setHasFixedSize(true);
        listem.setLayoutManager(linearLayoutManager);
        listem.setAdapter(adapter);





        new FetchTitle().execute(); // başlık çekmek için
    }



    protected class FetchTitle extends AsyncTask<Void, Void, Void> {

        String title;
        String bilgi;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {

            try{

                Document doc  = Jsoup.connect(URL).get();
//                Elements info = doc.select("span#cph_body_dgYemekListesi_Label3_0");

                Elements tablom = doc.select("table.table table-hover table-bordered table-striped");

                Log.e("tablom",tablom.text());
                /*Elements links = doc.getElementsByTag("span");

                for(int i=0;i<links.size();i++){

                    String linkText = links.get(i).text();

                    Log.e("yazim",linkText);
                }
                */


                //bilgi=info.text();

                title = doc.title();  // ilgili sayfanın başlığını almak için

                for(int i=1;i<=18;i++){


                    Elements yemekler = doc.select("span#cph_body_dgYemekListesi_Label3_"+String.valueOf(i));
                    Elements tarihler = doc.select("span#cph_body_dgYemekListesi_Label1_"+String.valueOf(i));
                    Elements gunler = doc.select("span#cph_body_dgYemekListesi_Label2_"+String.valueOf(i));

                    yemekListesi.add(yemekler.first().text());
                    tarihListesi.add(tarihler.first().text());
                    tarihListesiGunler.add(gunler.first().text());
                }




            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            Log.e("baslik",title);
            //Log.e("baslik",bilgi);

            for(int i=0;i<yemekListesi.size();i++){

                Yemek yemek=new Yemek();

                yemek.setGun(tarihListesiGunler.get(i));
                yemek.setTarih(tarihListesi.get(i));
                yemek.setYemekListesi(yemekListesi.get(i));

                tumYemekler.add(yemek);




                Log.e("yemeklerim",tarihListesi.get(i)+" : "+tarihListesiGunler.get(i)+" :"+yemekListesi.get(i));
            }



            adapter.notifyDataSetChanged();

        }
    }
}
