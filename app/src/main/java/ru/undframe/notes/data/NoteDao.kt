package ru.undframe.notes.data

import androidx.room.*
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single


@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    fun getAll(): Flowable<List<Note?>>?

    @Query("SELECT * FROM note WHERE id = :id")
    fun getById(id: Long): Single<Note?>

    @Insert
    fun insert(employee: Note?)

    @Update
    fun update(employee: Note?)

    @Delete
    fun delete(employee: Note?)

}