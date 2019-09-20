package com.example.lenovo.project1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MailHelp extends AppCompatActivity {

    EditText e1,e2;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_help);

        e1=findViewById(R.id.editText);
        e2=findViewById(R.id.editText2);
        btn=findViewById(R.id.button);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String to[]={"cu.17bcs1211@gmail.com"};//to send to multiple mail id
                String sub=e1.getText().toString();
                String qry=e2.getText().toString();

                sendEmail(to,sub,qry);
            }
        });

    }

    public void sendEmail(String to[],String sub,String qry)
    {
        Intent i=new Intent();
        i.setData(Uri.parse("mailto:"));//reseverd wrod like "tel:"
        i.putExtra(Intent.EXTRA_EMAIL,to);//key,value  key for all mailing apps
        i.putExtra(Intent.EXTRA_SUBJECT,sub);
        i.putExtra(Intent.EXTRA_TEXT,qry);
        startActivity(i);
        //startActivity(Intent.createChooser((i,"Email"));  picker to select where to send data
    }

}
