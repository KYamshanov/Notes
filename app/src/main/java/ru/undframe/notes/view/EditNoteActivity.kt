package ru.undframe.notes.view

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.disposables.CompositeDisposable
import ru.undframe.notes.App
import ru.undframe.notes.R
import ru.undframe.notes.contracts.EditNoteContract
import ru.undframe.notes.data.Note
import javax.inject.Inject

class EditNoteActivity : AppCompatActivity(), EditNoteContract.View {

    private var editMode: Boolean = false

    private lateinit var noteName: TextView
    private lateinit var noteText: TextView
    private lateinit var saveButton: Button
    private lateinit var backButton: ImageView

    @Inject
    lateinit var presenter: EditNoteContract.Presenter

    @Inject
    lateinit var disposable: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)


        val noteId = intent.getStringExtra("note_id")
        val noteName = intent.getStringExtra("note_name")
        val noteText = intent.getStringExtra("note_text")
        val noteDate = intent.getLongExtra("note_date",-1L)

        if (noteId != null && noteName != null && noteText != null && noteDate != -1L) {
            editMode = true

            println("EDIT MODE")

        }

        this.noteName = findViewById(R.id.edit_note_name)
        this.noteText = findViewById(R.id.edit_note_text)
        this.saveButton = findViewById(R.id.save_note)
        this.backButton = findViewById(R.id.back_to_main_menu)

        this.noteName.text = noteName ?: ""
        this.noteText.text = noteText ?: ""

        this.saveButton.setOnClickListener {
            println(getNoteName())
            if (editMode)
                presenter.saveNote(Note(noteId!!, getNoteName(), getNoteText(), noteDate))
            else
                presenter.saveNote(Note.createNot(getNoteName(), getNoteText()))

            finish()
        }
        this.backButton.setOnClickListener { finish() }

    }

    private fun getNoteName(): String {
        return noteName.text.toString()
    }

    private fun getNoteText(): String {
        return noteText.text.toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

}