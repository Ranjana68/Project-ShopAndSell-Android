package com.example.lenovo.project1;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Buy extends AppCompatActivity {

    ListView lv;
    String phone;
    String url = "https://project127.000webhostapp.com/p1getdata.php";
    ArrayList<String> arrayList1,tel,naam,rate,item;
    ArrayAdapter<String> arrayAdapter1;
    ProgressDialog pd;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
       lv = findViewById(R.id.lv);
        pd = new ProgressDialog(this);
        pd.setMessage("please wait!");
        pd.show();

       arrayList1=new ArrayList<String>();
       tel=new ArrayList<String>();
        naam=new ArrayList<String>();
        rate=new ArrayList<String>();
        item=new ArrayList<String>();


        arrayAdapter1=new ArrayAdapter<String>(Buy.this,android.R.layout.simple_list_item_1,arrayList1);

        lv.setAdapter(arrayAdapter1);

        requestQueue=Volley.newRequestQueue(this);

        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

              try
               {

                   JSONObject job=new JSONObject(response);
                   JSONArray jArray=job.getJSONArray("result");

                  for(int i=0;i<jArray.length();i++)
                   {
                       JSONObject obj=jArray.getJSONObject(i);
                       final String name=obj.getString("name");
                       String item_name=obj.getString("item_name");
                       phone=obj.getString("phone");
                       String cat=obj.getString("cat");
                       String room=obj.getString("room");
                       String price=obj.getString("price");
                           arrayList1.add(item_name);
                           tel.add(phone);
                           item.add(item_name);
                           naam.add(name);
                           rate.add(price);

                           arrayAdapter1.notifyDataSetChanged();
                   }
                }

                catch (Exception e)
                {}

                pd.dismiss();

          //Toast.makeText(Buy.this, ""+response, Toast.LENGTH_SHORT).show();




            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                pd.dismiss();
                Toast.makeText(Buy.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {



                    final Dialog dialog=new Dialog(Buy.this);
                    dialog.setContentView(R.layout.dialog);
                    dialog.setCanceledOnTouchOutside(false);//enables back button   but dialog.setCancelable(false); do not

                    final TextView t1=dialog.findViewById(R.id.t1);
                final TextView t2=dialog.findViewById(R.id.t2);
                final TextView t3=dialog.findViewById(R.id.t3);

                t1.setText(item.get(position));
                t2.setText("SELLER NAME: "+naam.get(position));
                t3.setText("PRICE: "+rate.get(position));


                Button btn=dialog.findViewById(R.id.btn);

                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent i=new Intent(Intent.ACTION_CALL);
                            i.setData(Uri.parse("tel:"+tel.get(position)));

                            if(ActivityCompat.checkSelfPermission(Buy.this,Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED)
                            {
                                ActivityCompat.requestPermissions(Buy
                                        .this,new String[]{Manifest.permission.CALL_PHONE},0);
                                return; //return status of permission
                            }
                            startActivity(i);



                            dialog.dismiss();
                        }
                    });

                    dialog.show();




            }
        });



        requestQueue.add(request);
    }
}





