package com.example.analytics;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity {
	private static final String USER_PROPERTY = "favorite_food";
	private FirebaseAnalytics mFirebaseAnalytics;
	private TextView mTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mTextView = findViewById(R.id.textview);

		mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
		mFirebaseAnalytics.setUserProperty("favorite_food", "Pizza");
		mTextView.setText(String.format("UserProperty: %s", USER_PROPERTY));
	}

	@Override
	protected void onResume() {
		super.onResume();
		// screen name must be <= 36 characters
		mFirebaseAnalytics.setCurrentScreen(this, "CurrentScreen: " + getClass().getSimpleName(), null);
	}

	public void sendPredefineEvent(View view) {
		Bundle bundle = new Bundle();
		bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "12345");
		bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Nougat");
		bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "Image");
		bundle.putString(FirebaseAnalytics.Param.CURRENCY, "THB");
		bundle.putString(FirebaseAnalytics.Param.TRANSACTION_ID, "111");
		bundle.putString(FirebaseAnalytics.Param.VALUE, "300");
		mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
		mTextView.setText(R.string.sent_predefine);
	}

	public void sendCustomEvent(View view) {
		Bundle params = new Bundle();
		params.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "Image");

		// custom parameter must be <= 24 characters
		// custom value must be <= 36 characters
		params.putString("image_name", "android.png");
		params.putString("full_text", "Android 7.0 Nougat");

		// custom event must be <= 32 characters
		mFirebaseAnalytics.logEvent("share_image", params);
		mTextView.setText(R.string.sent_custom);
	}
}