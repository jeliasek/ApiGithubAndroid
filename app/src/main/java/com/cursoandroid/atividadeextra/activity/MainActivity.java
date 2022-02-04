package com.cursoandroid.atividadeextra.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cursoandroid.atividadeextra.R;
import com.cursoandroid.atividadeextra.adapter.Adapter;
import com.cursoandroid.atividadeextra.api.DataService;
import com.cursoandroid.atividadeextra.model.User;
import com.cursoandroid.atividadeextra.retrofit.RetrofitInitializer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
//    private Retrofit retrofit;

    private Button botaoRecuperar;
    private TextView textoResultado;
    private EditText textoLogin;
    private RecyclerView recyclerUsers;

    private List<User> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoRecuperar = findViewById(R.id.button);
        textoResultado = findViewById(R.id.txtResultado);

        recyclerUsers = findViewById(R.id.recyclerUsers);

        //Configurar Adapter
        Adapter adapter = new Adapter(users);

        //Configurar Recycler View
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerUsers.setLayoutManager(layoutManager);
        recyclerUsers.setHasFixedSize(true);
        recyclerUsers.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerUsers.setAdapter(adapter);


//        retrofit = new Retrofit.Builder()
//                .baseUrl("https://api.github.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();

        botaoRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textoLogin = (EditText) findViewById(R.id.editTxtUser);

                recuperarDadosUsuario(textoLogin.getText().toString());
            }
        });

    }

    private void recuperarDadosUsuario(String login){
//        DataService service = retrofit.create(DataService.class);
//        Call<User> call = service.buscarDadosUsuario(login);
        Call<User> call = new RetrofitInitializer().getActions().buscarDadosUsuario(login);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    User user = response.body();
                    Toast.makeText(MainActivity.this, user.getName(), Toast.LENGTH_LONG).show();
                    users.add(user);
                    Adapter adapter = new Adapter(users);
                    textoResultado.setText(user.getLogin() + " - " + user.getName());
                    textoLogin.setText("");
                    closeKeyboard();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void closeKeyboard(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}
