package com.example.booklibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class VoirlivresActivity extends AppCompatActivity {

    List<Livre> livres;
    LivreDao l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voirlivres);
        l=new LivreDao(this);
        livres=new ArrayList<>();

        livres=l.getAllLivres();

        RecyclerView recyclerView=findViewById(R.id.recyclerView_id);
        RecyclerViewAdapter adapter=new RecyclerViewAdapter(this,livres);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setAdapter(adapter);

    }
}
