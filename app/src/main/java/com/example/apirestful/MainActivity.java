package com.example.apirestful;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.apirestful.WebService.Asynchtask;
import com.example.apirestful.WebService.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://api-uat.kushkipagos.com/transfer-subscriptions/v1/bankList",
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET","Public-Merchant-Id","84e1d0de1fbf437e9779fd6a52a9ca18");

    }

    @Override
    public void processFinish(String result) throws JSONException {
        TextView txtBancos = (TextView)findViewById(R.id.txtDatos);

        String lstBancos="";
        JSONArray JSONlista =  new JSONArray(result);
        for(int i=0; i< JSONlista.length();i++){
            JSONObject banco=  JSONlista.getJSONObject(i);
            lstBancos = lstBancos + "\n" + banco.getString("name").toString();
        }

        txtBancos.setText("Respuesta WS Lista de Bancos" +  lstBancos);
    }
}