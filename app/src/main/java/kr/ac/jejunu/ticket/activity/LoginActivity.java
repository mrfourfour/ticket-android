package kr.ac.jejunu.ticket.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import kr.ac.jejunu.ticket.R;
import kr.ac.jejunu.ticket.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        binding.loginBtn.setOnClickListener(this::loginBtn);

    }

    private void loginBtn(View view) {
        Intent intent = new Intent(LoginActivity.this,WebviewActivity.class);
        startActivity(intent);
    }
}
