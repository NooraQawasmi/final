package comp.sondos438.finallogin.data.model;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import comp.sondos438.finallogin.R;

public class Register extends AppCompatActivity {
    private EditText ID;
    private EditText Name;
    private EditText email;
    private EditText password;
    private Spinner gender;
    private EditText Nationality;
    private EditText phoneNumber;
    private Spinner type;
    private Button Register;
    private TextView txtResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setUpViews();
    }

    public void setUpViews() {
        ID = findViewById(R.id.edtID);
        Name = findViewById(R.id.edtName);
        email = findViewById(R.id.edtemail);
        password = findViewById(R.id.edtpassword);
        gender = findViewById(R.id.spinnergender);
        Nationality = findViewById(R.id.edtnationality);
        phoneNumber = findViewById(R.id.edtphonenumber);
        type = findViewById(R.id.spinnertype);
        Register = findViewById(R.id.btnregDone);
        txtResult = findViewById(R.id.txtResult);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(adapter);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.type, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(adapter2);

    }

    private void AddUser(int id,String NameC,String Email,String password,String National,String G,String phone,String Type)
    {
//
        String url = "http://10.0.2.2:80/login-signup/signup.php";
        RequestQueue queue = Volley.newRequestQueue(Register.this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("TAG", "RESPONSE IS " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    // on below line we are displaying a success toast message.
                    Toast.makeText(Register.this,
                            jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(Register.this,
                        "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public String getBodyContentType() {
                // as we are passing data in the form of url encoded
                // so we are passing the content type below
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("CID", String.valueOf(id));
                params.put("CNAME", NameC);
                params.put("email", Email);
                params.put("password", password);
                params.put("CNATIONALITY", National);
                params.put("CGENDER", G);
                params.put("CPHONE", phone);
                params.put("type", Type);

                // at last we are returning our params.
                return params;
            }
            /*
            * @OverRide
public Map<String, String> getParams() throws AuthFailureError{
Map<String, String> params = new HashMap<>();
params.put("Content-Type", "application/json");
params.put("Authorization", "Bearer " + Utils.readSharedSetting(context, "access_token", ""));
return params;
}*/
        };
        // below line is to make
        // a json object request.
        queue.add(request);
    }



    public void GoToRegister(View view) {

        String id = ID.getText().toString();
        int ID = Integer.parseInt(id);
        String NameC = Name.getText().toString();
        String Email = email.getText().toString();
        String Password = password.getText().toString();
        String National = Nationality.getText().toString();
        String G = gender.getSelectedItem().toString();
        String phone = phoneNumber.getText().toString();
        String Type = type.getSelectedItem().toString();
        AddUser(ID,NameC,Email,Password,National,G,phone,Type);
//        if(String.valueOf(id).length()!=10){
//            Toast.makeText(Register.this, "not valid ID",
//                    Toast.LENGTH_SHORT).show();
//        }
//        else if(phone.length()!=10){
//            Toast.makeText(Register.this, "not valid phone number",
//                    Toast.LENGTH_SHORT).show();
//        }
//        else{
//
//        }


    }

}
