package com.androidabcd.androidarchitecturecomponents.noteitems;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.androidabcd.androidarchitecturecomponents.database.NoteDatabase;
import com.androidabcd.androidarchitecturecomponents.database.NoteModel;

import java.util.List;

/**
 * Created by ashok on 26/01/18.
 */

public class NotesListViewModel extends AndroidViewModel {

    private final LiveData<List<NoteModel>> NoteList;

    private NoteDatabase noteDatabase;

    public NotesListViewModel(@NonNull Application application) {
        super(application);
        noteDatabase = NoteDatabase.getDatabase(this.getApplication());
        NoteList = noteDatabase.noteItemAndNotesModel().getAllnotes();
    }

    public LiveData<List<NoteModel>> getNoteList(){
        return NoteList;
    }

    public void deleteNote(NoteModel noteModel){
        new deleteAsyncTask(noteDatabase).execute(noteModel);
    }


    private static class deleteAsyncTask extends AsyncTask <NoteModel, Void, Void> {

        private NoteDatabase noteDatabase;

        deleteAsyncTask(NoteDatabase notedb){
            noteDatabase = notedb;
        }

        @Override
        protected Void doInBackground(final NoteModel... noteModels) {
            noteDatabase.noteItemAndNotesModel().deleteNote(noteModels[0]);
            return null;
        }
    }
}
