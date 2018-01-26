package com.androidabcd.androidarchitecturecomponents.noteitems;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidabcd.androidarchitecturecomponents.R;
import com.androidabcd.androidarchitecturecomponents.database.NoteModel;

import java.util.List;

/**
 * Created by ashok on 25/01/18.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private List<NoteModel> NoteList;
    private View.OnLongClickListener longClickListener;

    public NoteAdapter(List<NoteModel> noteList, View.OnLongClickListener longClickListener) {
        NoteList = noteList;
        this.longClickListener = longClickListener;
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NoteViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.noteitem,parent,false));
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        NoteModel noteModel = NoteList.get(position);
        holder.notetitle.setText(noteModel.getNoteTitle());
        holder.notedescription.setText(noteModel.getNoteDescription());
        holder.itemView.setTag(noteModel);
        holder.itemView.setOnLongClickListener(longClickListener);
    }

    @Override
    public int getItemCount() {
        return NoteList.size();
    }

    public void addNote(List<NoteModel> NoteList){
        this.NoteList = NoteList;
        notifyDataSetChanged();
    }


    public class NoteViewHolder extends RecyclerView.ViewHolder{
        TextView notetitle;
        TextView notedescription;

        public NoteViewHolder(View itemView) {
            super(itemView);
            notetitle = itemView.findViewById(R.id.noteTitle);
            notedescription = itemView.findViewById(R.id.noteDescription);
        }
    }
}
