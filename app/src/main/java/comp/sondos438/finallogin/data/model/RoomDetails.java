package comp.sondos438.finallogin.data.model;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

import comp.sondos438.finallogin.R;

public class RoomDetails extends AppCompatActivity {
    EditText rnum;
    EditText rprice;
    EditText isOccupied;
    EditText rfloor;
    EditText rtype;
    EditText isCleaned;
    EditText bednumbers;
    Button add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_details);
        setUpViews();
    }

    public void setUpViews() {
        rnum = findViewById(R.id.RNO);
        rprice = findViewById(R.id.rprice);
        isOccupied = findViewById(R.id.isOccupied);
        rfloor = findViewById(R.id.RFLOOR);
        rtype = findViewById(R.id.RTYPE);
        isCleaned = findViewById(R.id.isCleaned);
        bednumbers = findViewById(R.id.bedsnum);
        add = findViewById(R.id.addroomtodata);
    }

    public void adddd(View view) {
        String num = rnum.getText().toString();
        int rnumber = Integer.parseInt(num);
        String p = rprice.getText().toString();
        double price = Double.parseDouble(p);
        String isoccupied = isOccupied.getText().toString();
        String rfloo = rfloor.getText().toString();
        int Roomfloor = Integer.parseInt(rfloo);
        String rtyp = rtype.getText().toString();
        String iscleaned = isCleaned.getText().toString();
        String beds = bednumbers.getText().toString();
        int bednumbers = Integer.parseInt(beds);
        add(rnumber, price, isoccupied, Roomfloor, rtyp, iscleaned, bednumbers);

    }

    public void add(int rnumber, double price, String isoccupied, int Roomfloor, String rtyp, String iscleaned, int bednumbers) {
        String url = "http://10.0.2.2:80/login-signup/addrooms.php";
        RequestQueue queue = Volley.newRequestQueue(RoomDetails.this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("TAG", "RESPONSE IS " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    // on below line we are displaying a success toast message.
                    Toast.makeText(RoomDetails.this,
                            jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(RoomDetails.this,
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
                params.put("RNO", String.valueOf(rnumber));
                params.put("rprice", String.valueOf(price));
                params.put("isOccupied", isoccupied);
                params.put("RFLOOR", String.valueOf(Roomfloor));
                params.put("RTYPE", rtyp);
                params.put("isClean", iscleaned);
                params.put("bedsNum", String.valueOf(bednumbers));

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

    }
