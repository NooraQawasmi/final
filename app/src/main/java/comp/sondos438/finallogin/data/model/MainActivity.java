package comp.sondos438.finallogin.data.model;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.lang.reflect.Array;
import java.util.ArrayList;
import comp.sondos438.finallogin.R;

public class MainActivity extends AppCompatActivity {
    TextView Signup;
    EditText edtemail;
    EditText edtpass;
    CheckBox Remember;
    Button Sign_In;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    static final String EMAIL ="EMAIL";
    static  final String PASS = "PASS";
    static final String FLAG = "FLAG";
    RequestQueue queue;

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences pref = getSharedPreferences("edtrem",MODE_PRIVATE);
        int StayLogIN = pref.getInt("StayLogIN",-1);
        Toast.makeText(this, "Staylogin"+StayLogIN, Toast.LENGTH_SHORT).show();

        String type = pref.getString("type","");
        if(StayLogIN != -1){

            if(type.equals("admin")){
                Intent intent= new Intent(this,admin.class);
                startActivity(intent);
            }else{
                Intent intent= new Intent(this,client.class);
                startActivity(intent);
            }


        }
    }
public void setUpSharedPrefrences(){
prefs = PreferenceManager.getDefaultSharedPreferences(this);
editor = prefs.edit();
}
public void CheckPrefrence(){
boolean flag = prefs.getBoolean(FLAG,false);
if(flag){
    String email = prefs.getString(EMAIL,"");
    String password = prefs.getString(PASS,"");
    edtemail.setText(email);
    edtpass.setText(password);
    Remember.setChecked(true);
}
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Signup = findViewById(R.id.Sign_up);
        setUpViews();
        queue = Volley.newRequestQueue(this);
        setUpSharedPrefrences();
        CheckPrefrence();
    }
    public void setUpViews(){
            edtemail = findViewById(R.id.edtemaillogin);
            edtpass = findViewById(R.id.edtpasslogin);
            Remember = findViewById(R.id.checkBox);
            Sign_In = findViewById(R.id.SIGN_IN);
    }
    public void GoToRigister(View view) {
        Intent go = new Intent(this, Register.class);
        startActivity(go);
    }
    public void signInuser(View view){
        String email = edtemail.getText().toString();
        String password = edtpass.getText().toString();
        if(email.isEmpty()){
            Toast.makeText(this, "fill the email ", Toast.LENGTH_SHORT).show();
        }
        else if(password.isEmpty()){
            Toast.makeText(this, "fill the password", Toast.LENGTH_SHORT).show();
        }
        else{
            String url = "http://10.0.2.2:80/login-signup/login.php?email=" + email +"&password=" + password;
            StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String output) {
                            String CNAME;
                            String cemail;
                            String phone;
                            String type;
                            try {
                                JSONObject jsonObject = new JSONObject(output);

                                if(jsonObject.getString("ERROR").equals("T")){
                                    Toast.makeText(MainActivity.this, "Something wrong", Toast.LENGTH_SHORT).show();
                                    Toast.makeText(MainActivity.this, jsonObject.getString("note"), Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    CNAME = jsonObject.getString("CNAME");
                                    cemail= jsonObject.getString("email");
                                    phone = jsonObject.getString("CPHONE");
                                    type = jsonObject.getString("type");

                                    Intent in = new Intent(MainActivity.this,profile.class);
                                    in.putExtra("cname",CNAME);
                                    in.putExtra("email",cemail);
                                    in.putExtra("phone",phone);
                                    in.putExtra("type",type);
                                    startActivity(in);
                                    Toast.makeText(MainActivity.this, "Hello "+jsonObject.getString("CNAME"), Toast.LENGTH_SHORT).show();
                                    //Toast.makeText(MainActivity.this, jsonObject.getString("type"), Toast.LENGTH_SHORT).show();
                                    if(Remember.isChecked()){
                                        SharedPreferences info = getSharedPreferences("edtrem",MODE_PRIVATE);
                                        SharedPreferences.Editor edt = info.edit();
                                        edt.putInt("StayLogIN",1);
                                        edt.putString("type",type);
                                        edt.apply();
                                        editor.putString(EMAIL,email);
                                        editor.putString(PASS,password);
                                        editor.putBoolean(FLAG,true);
                                        editor.commit();
                                        if(type.equals("admin")){
                                            Intent i1 = new Intent(MainActivity.this,admin.class);
                                            startActivity(i1);
                                        }
                                        else if(type.equals("client")){
                                            Intent i2 = new Intent(MainActivity.this,profile.class);
                                            startActivity(i2);
                                        }

                                    }
                                    else{
                                        SharedPreferences info = getSharedPreferences("edtrem",MODE_PRIVATE);
                                        SharedPreferences.Editor edt = info.edit();
                                        edt.putString("StayLogIN","false");
                                        edt.apply();
                                        if(type.equals("admin")){
                                            Intent i1 = new Intent(MainActivity.this,admin.class);
                                            startActivity(i1);
                                        }
                                        else if(type.equals("client")){
                                            Intent i2 = new Intent(MainActivity.this,client.class);
                                            startActivity(i2);
                                        }
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText(MainActivity.this, error.toString(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
            queue.add(request);
        }


    }

}