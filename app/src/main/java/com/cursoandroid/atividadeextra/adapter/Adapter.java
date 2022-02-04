package com.cursoandroid.atividadeextra.adapter;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cursoandroid.atividadeextra.R;
import com.cursoandroid.atividadeextra.model.User;
import com.squareup.picasso.Picasso;

import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private List<User> listUsers;

    public Adapter(List<User> users) {
        this.listUsers = users;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_lista, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        User user = listUsers.get(position);
        holder.name.setText(user.getName());
        Uri uri = Uri.parse(user.getAvatar_url());
        Picasso.get().load(uri).into(holder.avatar_url);
        holder.login.setText(user.getLogin() +" - "+ user.getId());
    }

    @Override
    public int getItemCount() {
        return listUsers.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView avatar_url;

        TextView login;

        TextView name;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            avatar_url = itemView.findViewById(R.id.imgUser);
            login = itemView.findViewById(R.id.txtLogin);
            name = itemView.findViewById(R.id.txtNome);

        }
    }

}
