package comp.sondos438.finallogin.data.model;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import comp.sondos438.finallogin.R;

public class profile extends AppCompatActivity {
TextView full_name;
TextView EMail;
TextView Phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        full_name=findViewById(R.id.tvfullname);
        EMail=findViewById(R.id.tvemail);
        Phone=findViewById(R.id.tvphonenumber);
        String username = getIntent().getStringExtra("cname");
        String email = getIntent().getStringExtra("email");
        String phone = getIntent().getStringExtra("phone");
        full_name.setText(username);
        EMail.setText(email);
        Phone.setText(phone);
    }

    public void Dologout(View view) {
    SharedPreferences preferences = getSharedPreferences("edtrem",MODE_PRIVATE);
    SharedPreferences.Editor edt = preferences.edit();
    edt.putInt("StayLogIN",-1);
    edt.apply();
    Intent intent = new Intent(profile.this,MainActivity.class);
    startActivity(intent);
    }

    public void update(View view) {
    }

    public void deleteprofile(View view) {
    }
}