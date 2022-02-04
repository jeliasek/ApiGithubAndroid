package com.cursoandroid.atividadeextra.api;

import com.cursoandroid.atividadeextra.model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DataService {
    @GET("users/{login}")
    Call<User> buscarDadosUsuario(@Path("login") String login);
}
