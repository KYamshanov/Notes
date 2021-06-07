package ru.undframe.notes.data

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single


@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    fun getAll(): Flowable<List<Note>>

    @Query("SELECT * FROM note WHERE id = :id")
    fun getById(id: Long): Single<Note?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(employee: Note): Completable

    @Update
    fun update(employee: Note):Completable

    @Delete
    fun delete(employee: Note):Completable

}