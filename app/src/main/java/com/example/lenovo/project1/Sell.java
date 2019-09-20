package com.example.lenovo.project1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Sell extends AppCompatActivity {
    EditText e1,e2,e3,e5,e6;
    Button b1,b2;
    Spinner sp;
    ImageView iv;
    CheckBox c;
    ProgressDialog pd;
    RequestQueue requestQueue;
    String a[],cat;
    String url="https://project127.000webhostapp.com/p1setdata.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

        sp=findViewById(R.id.sp);
        e1=findViewById(R.id.name);
        e2=findViewById(R.id.item);
        e3=findViewById(R.id.phone);
        e5=findViewById(R.id.room);
        e6=findViewById(R.id.price);

        iv=findViewById(R.id.iv);
        c=findViewById(R.id.check);

        b1=findViewById(R.id.b1);
        b2=findViewById(R.id.b2);
        b2.setEnabled(false);

       a=getResources().getStringArray(R.array.data);

        ArrayAdapter<String> ad=new ArrayAdapter<String>(Sell.this,android.R.layout.simple_list_item_1,a);
        sp.setAdapter(ad);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position!=0)
                cat=a[position].toString();
                else
                    cat=null;
                //Toast.makeText(Sell.this, ""+cat, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        requestQueue=Volley.newRequestQueue(this);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(i,0);

            }
        });



        c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                         @Override
                                         public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                             if (c.isChecked())
                                                 b2.setEnabled(true);
                                             if (c.isChecked() == false)
                                                 b2.setEnabled(false);
                                         }
        });



        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pd=new ProgressDialog(Sell.this);
                pd.setMessage("please wait...");
                pd.show();

                if(e1.getText().toString().isEmpty())
                    e1.setError("Mention item name");
                if(e2.getText().toString().isEmpty())
                    e2.setError("Mention your name");
                if(e3.getText().toString().isEmpty())
                    e3.setError("Mention item name");
                if(e5.getText().toString().isEmpty())
                    e5.setError("Mention Hostel");
                if(e6.getText().toString().isEmpty())
                    e6.setError("Mention price");
                StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        pd.dismiss();
                        Toast.makeText(Sell.this, "congratulations! Your item is added to buy list. \n you may soon now recieve any interested customers" +
                                " call", Toast.LENGTH_LONG).show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        pd.dismiss();
                        //Toast.makeText(Sell.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                })
                {
                    //init block


                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> mydata=new HashMap<String,String>(); //key and value ; key==column name of table on host
                        mydata.put("name",e1.getText().toString());
                        mydata.put("item_name",e2.getText().toString());
                        mydata.put("phone",e3.getText().toString());
                        mydata.put("cat",cat);
                        mydata.put("room",e5.getText().toString());
                        mydata.put("price",e6.getText().toString());

                        return mydata;
                        //return super.getParams();
                    }
                };

                requestQueue.add(request);
            }
        });





    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(resultCode==RESULT_OK && data!=null)
        {
            Uri filepath=data.getData();
            iv.setImageURI(filepath);
        }
        else
        {
            Toast.makeText(this, "try again....", Toast.LENGTH_SHORT).show();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

}
