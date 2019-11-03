package kr.ac.jejunu.ticket.activity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.gson.Gson;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import kr.ac.jejunu.ticket.R;
import kr.ac.jejunu.ticket.application.AppApplication;
import kr.ac.jejunu.ticket.data.TokenData;
import kr.ac.jejunu.ticket.databinding.LoginWebviewBinding;

public class WebviewActivity extends AppCompatActivity {
    private LoginWebviewBinding binding;
    private WebSettings webSettings;
    private static final String TAG = WebviewActivity.class.getSimpleName();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.login_webview);

        String url = "https://ticket-user.auth.ap-northeast-2.amazoncognito.com/login?response_type=code&scope_id=openid+email&client_id=56mqnd8185kq2vn6u2qtahd73b&redirect_uri=https://gilson1.woochan.pe.kr/auth/token";


        webSettings = binding.webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        binding.webview.addJavascriptInterface(new JavaScriptInterface(),"Android");
        binding.webview.loadUrl(url);

        binding.webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                view.loadUrl("javascript:window.Android.getToken(document.getElementsByTagName('html')[0].textContent);");
//                view.loadUrl("javascript:window.Android.getToken(document.querySelector(\"#formattedJson > span > span.blockInner > span:nth-child(1) > span.s > span\"))");
                super.onPageFinished(view, url);
                Toast.makeText(getApplicationContext(),"로그인 성공 했습니다.",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }
        });

        webSettings.setSupportMultipleWindows(false);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(false);
        webSettings.setLoadWithOverviewMode(true); // 메타태그 허용 여부
        webSettings.setUseWideViewPort(true); // 화면 사이즈 맞추기 허용 여부
        webSettings.setSupportZoom(false); // 화면 줌 허용 여부
        webSettings.setBuiltInZoomControls(false); // 화면 확대 축소 허용 여부
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); // 컨텐츠 사이즈 맞추기
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE); // 브라우저 캐시 허용 여부
        webSettings.setDomStorageEnabled(true); // 로컬저장소 허용 여부




    }

    private class JavaScriptInterface {
        @JavascriptInterface
        public void getToken(String token) {
            Gson gson = new Gson();
            Document doc = Jsoup.parse(token);

            TokenData tokenData = gson.fromJson(doc.select("body").text(),TokenData.class);
            ((AppApplication)getApplication()).setTokenData(tokenData);
            Intent intent = new Intent(WebviewActivity.this,HomeActivity.class);
            startActivity(intent);
        }
    }
}
