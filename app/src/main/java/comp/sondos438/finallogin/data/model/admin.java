package comp.sondos438.finallogin.data.model;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import comp.sondos438.finallogin.R;

public class admin extends AppCompatActivity {
private Button btnadd;
private Button btnroom_amenties;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        btnadd = findViewById(R.id.addroom);
        btnroom_amenties = findViewById(R.id.button2);
    }

    public void ADDROOM(View view) {
        Intent intent = new Intent(this,RoomDetails.class);
        startActivity(intent);
    }

    public void goToAmenties(View view) {
        Intent intent = new Intent(this,room_amenties.class);
        startActivity(intent);
    }
}