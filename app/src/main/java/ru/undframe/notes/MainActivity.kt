package ru.undframe.notes

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf
import ru.undframe.notes.adapters.NotesAdapter
import ru.undframe.notes.contracts.MainContract
import ru.undframe.notes.data.Note
import ru.undframe.notes.view.EditNoteActivity

class MainActivity : AppCompatActivity(), MainContract.View {


    private val presenter: MainContract.Presenter by inject { parametersOf(this) }

    private lateinit var notesView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notesView = findViewById(R.id.notes_view)

        startKoin {
            androidLogger()
            androidContext(this@MainActivity)
            modules(KoinModule.loadModule())
        }
        presenter.launch()


    }

    override fun showNotes(notes: Array<Note>) {

        notesView.layoutManager = LinearLayoutManager(this)
        notesView.adapter = NotesAdapter(notes) {
            openEditNoteActivity(it)
        }
    }

    override fun openEditNoteActivity(note: Note) {
        startActivity(Intent(this,EditNoteActivity::class.java))
    }
}