package com.mysocketsapp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;


import android.os.AsyncTask;
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
	int state =  0;
	ObjectOutputStream salida;
	ObjectInputStream entrada;
	//final TextView mensaje = (TextView) findViewById(R.id.messageText);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		final TextView mensaje = (TextView) findViewById(R.id.messageText);
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
				if(state==0){
					new MakeConnectionTask().execute();
					state++;
				}else{
					list.add(mensaje.getText().toString());
				    adapter.notifyDataSetChanged();
				    listview.setSelection(listview.getAdapter().getCount()-1);
					System.out.println("Hola mundo desde console by "+mensaje.getText());
					try {
						salida.writeObject(mensaje.getText().toString());
						System.out.println("si mando mensaje");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					mensaje.setText("");
				}
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
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	class MakeConnectionTask extends AsyncTask<Void, Void, Void>{

	    @Override
	    protected Void doInBackground(Void... params) {
	        // TODO Auto-generated method stub
	    	Connect();
	    	try {
				salida = new ObjectOutputStream(cliente.getOutputStream());
				entrada = new ObjectInputStream(cliente.getInputStream());
				System.out.println("si hay salida y salida");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	while(true){
	    		String mensaje2;
				try {
					mensaje2 = (String) entrada.readObject();
					System.out.println("Servidor: " + mensaje2);
				} catch (OptionalDataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
	    	}
	    }

	}

}
