package in.askdial.mrr.ccAvenueImplementation.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class StatusActivity extends Activity {
	
	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(com.avenues.lib.activity.R.layout.activity_status);

		Intent mainIntent = getIntent();
		TextView tv4 = (TextView) findViewById(com.avenues.lib.activity.R.id.textView1);
		tv4.setText(mainIntent.getStringExtra("transStatus"));
	}
	
	public void showToast(String msg) {
		Toast.makeText(this, "Toast: " + msg, Toast.LENGTH_LONG).show();
	}
} 