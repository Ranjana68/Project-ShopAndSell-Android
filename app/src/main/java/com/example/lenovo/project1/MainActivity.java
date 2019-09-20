package com.example.lenovo.project1;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button b1,b2;
    AlertDialog.Builder ab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1=findViewById(R.id.button);
        b2=findViewById(R.id.button2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(MainActivity.this,Buy.class);
                startActivity(i);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(MainActivity.this,Sell.class);
                startActivity(i);

            }
        });
    }

    @Override
    public void onBackPressed() {

        ab=new AlertDialog.Builder(MainActivity.this);
        ab.setMessage("Are you sure you want to exit??");
        ab.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        ab.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        ab.setCancelable(false);
        ab.show();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.apna_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();
        if(id==R.id.item2) {
            Toast.makeText(this, "About Shop&Sell", Toast.LENGTH_SHORT).show();

            Dialog dialog=new Dialog(MainActivity.this);
            dialog.setContentView(R.layout.about);
            dialog.show();

        }

        if(id==R.id.item3) {
            Toast.makeText(this, "Mail us your query", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(MainActivity.this,MailHelp.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }
}
