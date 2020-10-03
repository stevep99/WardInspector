package demo.wardinspector.model

import androidx.room.*
import io.reactivex.Single

@Dao
interface AnswerDao {
    @Insert
    fun insert(vararg a: Answer): Single<List<Long>>

    @Delete
    fun delete(a: Answer): Single<Int>
}