package demo.wardinspector.model

import androidx.room.*
import io.reactivex.Single

@Dao
interface AnsweredQuestionsCrossRefDao {
    @Transaction
    @Insert
    fun insert(vararg data: AnsweredQuestionsCrossRef): Single<List<Long>>

    @Delete
    fun delete(data: AnsweredQuestionsCrossRef): Single<Int>
}