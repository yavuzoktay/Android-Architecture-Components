package com.androidabcd.androidarchitecturecomponents.addnote;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.androidabcd.androidarchitecturecomponents.database.NoteDatabase;
import com.androidabcd.androidarchitecturecomponents.database.NoteModel;

/**
 * Created by ashok on 26/01/18.
 */

public class AddNoteViewModel extends AndroidViewModel {

    private NoteDatabase noteDatabase;

    public AddNoteViewModel(@NonNull Application application) {
        super(application);
        noteDatabase = NoteDatabase.getDatabase(this.getApplication());
    }

    public void addNote(final NoteModel noteModel){
        new addAsyncTask(noteDatabase).execute(noteModel);
    }

    private static class addAsyncTask extends AsyncTask<NoteModel, Void, Void>{

        private NoteDatabase db;

        public addAsyncTask(NoteDatabase noteDatabase) {
            db = noteDatabase;
        }

        @Override
        protected Void doInBackground(final NoteModel... noteModels) {
            db.noteItemAndNotesModel().insertNote(noteModels[0]);
            return null;
        }
    }
}
