package ru.undframe.notes.data

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class DatabaseNotesRepository(
    private val disposable: CompositeDisposable,
    private val database: NoteDatabase
) : NotesRepository {


    private val notes: MutableMap<String, Note> = HashMap()


    fun loadNotesFromDatabase() {

        disposable.add(
            database.noteDeo().getAll().subscribeOn(Schedulers.computation())
                .subscribe {
                    for (note in it) {
                        notes[note.id] = note
                    }
                }
        )


    }


    override fun getNotes(): Array<Note> {
        return notes.values.toTypedArray()
    }

    override fun save(note: Note, successfulAction: () -> Unit) {
        notes[note.id] = note

        database.noteDeo().insert(note).subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                successfulAction.invoke()
            }
    }

    override fun delete(note: Note, successfulAction: () -> Unit) {
        notes.remove(note.id)

        database.noteDeo().delete(note).subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                successfulAction.invoke()
            }

    }

}