package com.example.mobileprogramming_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText TxName, TxEmail, TxPassword, TxClass, TxDOB;
    Button BtnRegister;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbHelper = new DBHelper(this);

        TxName = (EditText)findViewById(R.id.input_name);
        TxEmail = (EditText)findViewById(R.id.email);
        TxPassword = (EditText)findViewById(R.id.input_password);
        TxClass = (EditText)findViewById(R.id.Class);
        TxDOB = (EditText)findViewById(R.id.DOB);

        BtnRegister = (Button)findViewById(R.id.btn_signUp);

        TextView tvRegister = (TextView)findViewById(R.id.tvRegister);

        tvRegister.setText(Html.fromHtml("Back to " +
                "</font><font color='#3b5998'>Login</font>"));

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        BtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = TxName.getText().toString().trim();
                String password = TxPassword.getText().toString().trim();
                String Email = TxEmail.getText().toString().trim();
                String DOB = TxDOB.getText().toString().trim();
                String Class = TxClass.getText().toString().trim();

                ContentValues values = new ContentValues();

                //validasi
                //Username harus lebih dari 8
                if(username.length() <= 8 ){
                    Toast.makeText(RegisterActivity.this, "Username length must more than 8", Toast.LENGTH_SHORT).show();
                    //gak boleh empty
                }else if(username.equals("") ||password.equals("") || Email.equals("") || DOB.equals("") || Class.equals("")){
                    Toast.makeText(RegisterActivity.this, "Username, Email, Password, DOB, Class cannot be empty", Toast.LENGTH_SHORT).show();
                }else{
                    values.put(DBHelper.row_name, username);
                    values.put(DBHelper.row_email, Email);
                    values.put(DBHelper.row_password, password);
                    values.put(DBHelper.row_dob, DOB);
                    values.put(DBHelper.row_class, Class);
                    dbHelper.insertData(values);

                    Toast.makeText(RegisterActivity.this, "Register Successful", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    public void Move_to_Main(View view) {
        Intent intent = new Intent(RegisterActivity.this, MainActivity2.class);
        startActivity(intent);
    }

    public static Spanned fromHtml(String html){
        Spanned result;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        }else{
            result = Html.fromHtml(html);
        }
        return result;
    }
}