package ru.undframe.notes.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.rxjava3.disposables.CompositeDisposable
import ru.undframe.notes.App
import ru.undframe.notes.R
import ru.undframe.notes.adapters.NotesAdapter
import ru.undframe.notes.contracts.MainContract
import ru.undframe.notes.data.Note
import ru.undframe.notes.data.NotesRepository
import javax.inject.Inject


class MainActivity : AppCompatActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter

    @Inject
    lateinit var repository: NotesRepository

    private lateinit var notesView: RecyclerView
    private lateinit var notesAdapter: NotesAdapter
    private lateinit var createNoteButton: Button
    private lateinit var profileButton: ImageView

    @Inject
    lateinit var disposables: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notesView = findViewById(R.id.notes_view)
        createNoteButton = findViewById(R.id.create_note_button)
        profileButton = findViewById(R.id.profile_button)

        presenter.launch()

        createNoteButton.setOnClickListener { openCreateNoteActivity() }
        profileButton.setOnClickListener { openAuthMenu() }

    }

    override fun openAuthMenu() {
        startActivity(Intent(this, AuthorizationActivity::class.java))
    }

    override fun onResume() {
        super.onResume()
        notesAdapter.setNotes(repository.getNotes())
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }

    override fun showNotes(notes: Array<Note>) {

        notesView.layoutManager = LinearLayoutManager(this)
        notesAdapter = NotesAdapter(this, notes) {
            openEditNoteActivity(it)
        }
        notesView.adapter = notesAdapter

    }


    override fun openConfirmationWindow(note: Note) {

        AlertDialog.Builder(this)
            .setTitle("Подтверждение")
            .setMessage("Вы уверены, что ходите удалить заметку?")
            .setPositiveButton("Да") { dialog, _ ->
                presenter.deleteNote(note)
                dialog.dismiss()
            }
            .setNegativeButton("Нет") { dialog, _ ->
                dialog.dismiss()
            }
            .create().show()


    }

    override fun refreshNotes() {
        notesAdapter.notifyDataSetChanged()
    }

    override fun deleteNoteAndRefresh(note: Note) {
        notesAdapter.removeNote(note)
        refreshNotes()
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