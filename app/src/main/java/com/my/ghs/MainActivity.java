package com.my.ghs;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;

public class MainActivity extends AppCompatActivity {
	
	 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(this, Myvoice.class));
    }
}



/*
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

	private WebView myWebView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		myWebView = (WebView) findViewById(R.id.webView);

		// Remember that you should never show the action bar if the
		// status bar is hidden, so hide that too if necessary.
		getSupportActionBar().hide();
		// Enable JavaScript
		WebSettings webSettings = myWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);

		// Enable Web Speech API
		webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

		// Set WebView client
		myWebView.setWebViewClient(new MyWebViewClient());

		// Set WebChrome client
		myWebView.setWebChromeClient(new MyWebChromeClient());

		//	String data = "<html><body><h1>Hello, Javatpoint!</h1></body></html>";
		//	myWebView.loadData(data, "text/html", "UTF-8");
		// Load URL
		myWebView.loadUrl("http://127.0.0.1:8000/");
		//myWebView.loadUrl("file:///android_asset/myresource.html");
	}

	// Custom WebView client
	private class MyWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
	}

	// Custom WebChrome client
	private class MyWebChromeClient extends WebChromeClient {
		@Override
		public void onProgressChanged(WebView view, int newProgress) {
			// Handle progress changes
		}
	}
}
*/

/*


//public class MainActivity extends AppCompatActivity {
import android.os.Bundle;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		WebView mywebview = (WebView) findViewById(R.id.webView);
		// mywebview.loadUrl("https://www.javatpoint.com/");

		String data = "<html><body><h1>Hello, Javatpoint!</h1></body></html>";
		mywebview.loadData(data, "text/html", "UTF-8");

		//mywebview.loadUrl("file:///android_asset/myresource.html");
	}
}
*/