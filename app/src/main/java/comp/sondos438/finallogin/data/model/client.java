package comp.sondos438.finallogin.data.model;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import comp.sondos438.finallogin.R;

public class client extends AppCompatActivity {
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        button = findViewById(R.id.goToexternal);
    }

    public void showMovies(View view) {
        Intent intent = new Intent(client.this,MainActivity2.class);
        startActivity(intent);
    }
}