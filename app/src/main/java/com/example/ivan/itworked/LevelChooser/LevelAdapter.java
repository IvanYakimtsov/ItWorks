package com.example.ivan.itworked.LevelChooser;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ivan.itworked.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 17.11.2017.
 */

public class LevelAdapter extends RecyclerView.Adapter<LevelHolder> {
    private List<Integer> levels = new ArrayList<>();
    private Context context;

    public LevelAdapter(Context context){
        this.context = context;
    }

    @Override
    public LevelHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.level_item, parent, false);
        return new LevelHolder(view, context);
    }

    @Override
    public void onBindViewHolder(LevelHolder holder, int position) {
        holder.bind(levels.get(position));
    }

    @Override
    public int getItemCount() {
        return levels.size();
    }

    public void addLevels(List<Integer> levels) {
        int pos = getItemCount();
        this.levels.addAll(levels);
        notifyItemRangeInserted(pos, getItemCount());
    }
}
