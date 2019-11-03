package kr.ac.jejunu.ticket.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import kr.ac.jejunu.ticket.R;
import kr.ac.jejunu.ticket.data.DetailToFragment;

public class DetailActivity extends AppCompatActivity implements DetailToFragment{
    private static final String TAG = DetailActivity.class.getSimpleName();
    private Bundle bundle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        bundle = intent.getBundleExtra("bundle");
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home : {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public String getCate() {
        return bundle.getString("category");
    }

    @Override
    public String getArea() {
        return bundle.getString("area");
    }

    @Override
    public String getValueCate() { return bundle.getString("value"); }
}
