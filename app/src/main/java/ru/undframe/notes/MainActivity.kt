package ru.undframe.notes

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

open class ViewModel()

class MyViewModel(val repo: MainActivity) : ViewModel()


class MainActivity : AppCompatActivity(), MainContract.View {


    private val mPresenter: MainContract.Presenter by inject { parametersOf(this) }

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
        mPresenter.launch()


    }

    override fun showNotes(notes: Array<Note>) {

        notesView.layoutManager = LinearLayoutManager(this)
        notesView.adapter = NotesAdapter(notes) {

        }
    }
}