package com.example.cantina;

import com.example.cantina.R.id;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ListaAluno extends Activity implements android.hardware.SensorEventListener {

	private SensorManager  sensor;
	private long lastUpdate;
	private boolean color = false;
	private View view;
	private ImageView imagem;

	Sensor myProximitySensor;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_aluno);

		view = findViewById(R.id.editText1);
		view.setBackgroundColor(Color.GREEN);

		imagem = (ImageView)findViewById(id.imageView1);

		sensor = (SensorManager) getSystemService(SENSOR_SERVICE);
		lastUpdate = System.currentTimeMillis();

		myProximitySensor = sensor.getDefaultSensor(Sensor.TYPE_PROXIMITY);



		if (myProximitySensor == null){
			EditText text1 = (EditText)findViewById(R.id.editText1);
			text1.setText("No Proximity Sensor!");
		}
		else
		{
			sensor.registerListener(proximitySensorEventListener, myProximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
		}


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lista_aluno, menu);
		return true;
	}

	public void funcao1(View view){
		//Intent intent = new Intent(this,);
		EditText text1 = (EditText)findViewById(R.id.editText1);
		Button botao = (Button)findViewById(R.id.button1);

		//text1.setTag("seila");
		//Toast.makeText(this, "seila",Toast.LENGTH_LONG).show();

		//		Log.d("seila", "fsdlfkldsfkldsfkdslkfds");

		switch (view.getId()) {
		case R.id.button1:
			break;

		default:
			break;
		}
	}


	SensorEventListener proximitySensorEventListener = new SensorEventListener() {  

		@Override  
		public void onAccuracyChanged(Sensor sensor, int accuracy) {  
			// TODO Auto-generated method stub  
			// gerei

		}  

		@Override  
		public void onSensorChanged(SensorEvent event) {  
			if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {  
				EditText text1 = (EditText)findViewById(R.id.editText1);
				if (event.values[0]>1)
					   text1.setText("vem pra mim");
				else
					   text1.setText("Uhhhhhhh");
					
			}  


		}  
	};  	


	@Override
	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			getAccelerometer(event);
		}

		if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
			EditText text = (EditText)findViewById(id.editText1);
			text.setText(String.valueOf(event.values[0]));
			Log.d("sensor",String.valueOf(event.values[0]));
		}
		Log.d("sensor",String.valueOf(event.sensor.getType()));



	}

	


	private void getAccelerometer(SensorEvent event) {
		float[] values = event.values;
		// Movement
		float x = values[0];
		float y = values[1];
		float z = values[2];

		float accelationSquareRoot = (x * x + y * y + z * z)
				/ (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
		long actualTime = System.currentTimeMillis();

		//EditText text = (EditText)findViewById(id.editText1);
		//text.setText(String.valueOf(x));


		/* if (accelationSquareRoot >= 2) //
		{
			if (actualTime - lastUpdate < 200) {
				return;
			}
			lastUpdate = actualTime;
			Toast.makeText(this, "Device was shuffed", Toast.LENGTH_SHORT)
			.show();
			if (color) {
				view.setBackgroundColor(Color.GREEN);

			} else {
				view.setBackgroundColor(Color.RED);
			}
			color = !color;
		}*/

		if (actualTime - lastUpdate < 200) {
		}
		else
		{
			lastUpdate = actualTime;

			if ( Math.abs(x)>=0 && Math.abs(x)<1  ) {
				imagem.setImageResource(R.drawable.img1);
			} else if ( Math.abs(x)>=1 && Math.abs(x)<2  ) {
				imagem.setImageResource(R.drawable.img2);
			} else if ( Math.abs(x)>=2 && Math.abs(x)<3  ) {
				imagem.setImageResource(R.drawable.img3);
			} else if ( Math.abs(x)>=3 && Math.abs(x)<4  ) {
				imagem.setImageResource(R.drawable.img4);
			} else if ( Math.abs(x)>=4 && Math.abs(x)<5  ) {
				imagem.setImageResource(R.drawable.img5);
			} else if ( Math.abs(x)>=5 && Math.abs(x)<6  ) {
				imagem.setImageResource(R.drawable.img6);
			} else if ( Math.abs(x)>=6 && Math.abs(x)<7  ) {
				imagem.setImageResource(R.drawable.img7);
			} else if ( Math.abs(x)>=7 && Math.abs(x)<8  ) {
				imagem.setImageResource(R.drawable.img8);
			} else if ( Math.abs(x)>=8 && Math.abs(x)<9  ) {
				imagem.setImageResource(R.drawable.img9);
			} else if ( Math.abs(x)>=9  ) {
				imagem.setImageResource(R.drawable.img9);
			}
			color = !color;
		}
	}




	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		//gerei
		

	}
	@Override
	protected void onResume() {
		super.onResume();
		// register this class as a listener for the orientation and
		// accelerometer sensors
		sensor.registerListener(this,
				sensor.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onPause() {
		// unregister listener
		super.onPause();
		sensor.unregisterListener(this);
	}

}
