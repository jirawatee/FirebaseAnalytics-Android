package com.example.analytics;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity {
	private FirebaseAnalytics mFirebaseAnalytics;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

		mFirebaseAnalytics.setUserProperty("favorite_food", "Pizza");
	}

	public void sendPredefineEvent(View view) {
		Bundle bundle = new Bundle();
		bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "12345");
		bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Nougat");
		bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "Image");
		mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
	}

	public void sendCustomEvent(View view) {
		Bundle params = new Bundle();
		params.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "Image");
		params.putString("image_name", "android.png");
		params.putString("full_text", "Android 7.0 Nougat");
		mFirebaseAnalytics.logEvent("share_image", params);
	}
}