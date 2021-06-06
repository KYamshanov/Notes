package ru.undframe.notes.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.undframe.notes.App
import ru.undframe.notes.R
import ru.undframe.notes.adapters.NotesAdapter
import ru.undframe.notes.contracts.MainContract
import ru.undframe.notes.data.Note
import ru.undframe.notes.data.NoteDatabase
import ru.undframe.notes.decorators.NoteItemDecorator
import javax.inject.Inject


class MainActivity : AppCompatActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter

    @Inject
    lateinit var database: NoteDatabase

    private lateinit var notesView: RecyclerView
    private lateinit var createNoteButton: Button

    @Inject
    lateinit var disposables: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notesView = findViewById(R.id.notes_view)
        createNoteButton = findViewById(R.id.create_note_button)

        presenter.launch()

        createNoteButton.setOnClickListener {
            openCreateNoteActivity()
        }

        println("AAA")

        database.noteDeo()?.let { it ->

            println("BBBBB")



            disposables.add(
                Completable.fromAction {
                    it.insert(Note.createNot("Это тесовая записка для базы данных"))

                }.subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe()
            )


            disposables.add(
                it.getAll()!!.subscribeOn(Schedulers.computation())
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        Consumer { it2 ->
                            it2!!.forEach { l ->
                                println(l!!.text)
                            }
                        })
            )


        }

    }


    override fun onResume() {
        super.onResume()
        presenter.launch()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }

    override fun showNotes(notes: Array<Note>) {

        notesView.layoutManager = LinearLayoutManager(this)
        notesView.adapter = NotesAdapter(notes) {
            openEditNoteActivity(it)
        }
        notesView.addItemDecoration(NoteItemDecorator())
    }


    override fun openCreateNoteActivity() {
        val intent = Intent(this, EditNoteActivity::class.java)
        startActivity(intent)
    }

    override fun openEditNoteActivity(note: Note) {
        val intent = Intent(this, EditNoteActivity::class.java)

        intent.putExtra("note_id", note.id)
        intent.putExtra("note_name", note.name)
        intent.putExtra("note_text", note.text)
        intent.putExtra("note_date", note.timeCreate)

        startActivity(intent)
    }
}