package com.mysocketsapp;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button sendText;
		sendText = (Button) findViewById(R.id.btnSend);
		final ListView listview = (ListView) findViewById(R.id.messageList);
	    String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
	        "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
	        "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
	        "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
	        "Android", "iPhone", "WindowsMobile" };
	    final TextView mensaje = (TextView) findViewById(R.id.messageText);
	    final ArrayList<String> list = new ArrayList<String>();
	    for (int i = 0; i < values.length; ++i) {
	      list.add(values[i]);
	    }
	    
	    final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
	    listview.setAdapter(adapter);
        sendText.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				list.add(mensaje.getText().toString());
			    adapter.notifyDataSetChanged();
			    listview.setSelection(listview.getAdapter().getCount()-1);
				System.out.println("Hola mundo desde console by "+mensaje.getText());
				mensaje.setText("");
			}
		});
        listview.setSelection(listview.getAdapter().getCount()-1);
	   /* listview.setOnItemClickListener(new OnItemClickListener(){
	    	@Override
	    	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
	    		Toast.makeText(getApplicationContext(), "Ha pulsado el item " + position, Toast.LENGTH_SHORT).show();
	    		}
	    	}); */
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
