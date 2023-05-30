package com.example.m213_retrofit_post;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateDelete extends AppCompatActivity {

    EditText id,name,url;
    Button update,delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        id=findViewById(R.id.txt_id);
        name=findViewById(R.id.ed_nom_edit);
        url=findViewById(R.id.ed_url_edit);
        update = findViewById(R.id.button_edit);
        delete=findViewById(R.id.button2_edit);


        Bundle extras = getIntent().getExtras();

        int movie_ID = extras.getInt("id");

        id.setText(String.valueOf(extras.getInt("id")));
        name.setText(extras.getString("name"));
        url.setText(extras.getString("image"));

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String movie_Name = name.getText().toString();
                String movie_img = url.getText().toString();


                RemoteAPI remoteAPI = new RemoteAPI();
                Call<Movie> call = remoteAPI.getRetrofit().updateMovie(movie_ID,movie_Name,movie_img);

                call.enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(Call<Movie> call, Response<Movie> response) {
                        Toast.makeText(UpdateDelete.this, "Movie Updated", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Movie> call, Throwable t) {
                        Toast.makeText(UpdateDelete.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RemoteAPI remoteAPI = new RemoteAPI();
                Call<Movie> call = remoteAPI.getRetrofit().deleteMovie(movie_ID);

                call.enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(Call<Movie> call, Response<Movie> response) {
                        Toast.makeText(UpdateDelete.this, "Movie Deleted", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Movie> call, Throwable t) {
                        Toast.makeText(UpdateDelete.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }


}