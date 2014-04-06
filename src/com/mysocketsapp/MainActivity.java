package com.mysocketsapp;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	Socket cliente;
	//final TextView mensaje = (TextView) findViewById(R.id.messageText);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		final TextView mensaje = (TextView) findViewById(R.id.messageText);
		if(Connect()){
			mensaje.setText("si conecta");
			System.out.print("conecto");
		}else{
			mensaje.setText("no conecta");
			System.out.print("No conecto");
		}
		Button sendText;
		sendText = (Button) findViewById(R.id.btnSend);
		final ListView listview = (ListView) findViewById(R.id.messageList);
	    String[] values = new String[] {};
	    
	    final ArrayList<String> list = new ArrayList<String>();
	    for (int i = 0; i < values.length; ++i) {
	      list.add(values[i]);
	    }
	    
	    final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
	    listview.setAdapter(adapter);
        sendText.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
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
	public boolean Connect() {
		String IP ="192.168.1.73";
		int PORT = 3500;
		try {
			cliente = new Socket(IP, PORT);
			return cliente.isConnected();
		} catch (Exception e) {
			System.out.println("nooooooo");
			return false;
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
