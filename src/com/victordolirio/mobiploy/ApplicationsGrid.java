package com.victordolirio.mobiploy;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ApplicationsGrid extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button btnExecutar = (Button) findViewById(R.id.btnExecutar);
		btnExecutar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {

				try {
					new SSHService().executeCommand();
					Toast makeText = Toast.makeText(ApplicationsGrid.this,
							"Commando executado", 10);
					makeText.show();
				} catch (RuntimeException rt) {
					Toast makeText = Toast.makeText(ApplicationsGrid.this,
							rt.getMessage(), 10);
					makeText.show();
				}
			}
		});
	}
}