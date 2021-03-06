package com.yixun.pettyloan.entity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yixun.pettyloan.R;
import com.yixun.pettyloan.adapter.multitype.ItemViewBinder;

/**
 * Created by zongkaili on 17-8-25.
 */
public class NewsItemViewBinder extends ItemViewBinder<Commodity, NewsItemViewBinder.ViewHolder> {
    private Context mContext;
    public NewsItemViewBinder(Context context){
        mContext = context;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_about_news, parent, false);
        return new ViewHolder(mContext,view);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Commodity mode) {
        holder.setData(mode);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        private Context context;
        private
        @NonNull
        final ImageView imageView;
        private
        @NonNull
        final TextView name;

        ViewHolder(Context context,@NonNull View itemView) {
            super(itemView);
            this.context = context;
            imageView = (ImageView) itemView.findViewById(R.id.image);
            name = (TextView) itemView.findViewById(R.id.title);
        }

        public void setData(Commodity mode) {
            name.setText(mode.name);
            Glide.with(context.getApplicationContext())
                    .load(mode.url)
                    .crossFade()
                    .fitCenter()
                    .into(imageView);
        }
    }
}
