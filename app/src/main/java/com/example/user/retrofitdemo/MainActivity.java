package com.example.user.retrofitdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerAdapter adapter;
    private List<Trains> trains;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<Trains>> call = apiInterface.getTrains();
        call.enqueue(new Callback<List<Trains>>() {
            @Override
            public void onResponse(Call<List<Trains>> call, Response<List<Trains>> response) {
                trains = response.body();
                adapter = new RecyclerAdapter(trains);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Trains>> call, Throwable t) {

            }
        });
    }
}
