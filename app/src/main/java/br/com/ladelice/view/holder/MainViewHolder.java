package br.com.ladelice.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.ladelice.R;

public class MainViewHolder extends RecyclerView.ViewHolder {
    public TextView title, description;
    public ImageView image;

    public MainViewHolder(View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.tvTitle);
        description = itemView.findViewById(R.id.tvDescription);
        image = itemView.findViewById(R.id.ivMenu);
    }
}
