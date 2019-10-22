package kr.ac.jejunu.ticket.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import kr.ac.jejunu.ticket.R;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = DetailActivity.class.getSimpleName();
//    private String cate;
//
//    public String getCate() {
//        return cate;
//    }
//
//    public String getArea() {
//        return area;
//    }
//
//    private String area;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
//        Intent intent = getIntent();
//        Bundle bundle = intent.getBundleExtra("bundle");
//        cate = bundle.getString("category");
//        area = bundle.getString("area");
//        Log.d("!!!", cate + "/" + area);


    }
}
