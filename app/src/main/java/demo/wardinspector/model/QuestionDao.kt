package demo.wardinspector.model

import androidx.room.*
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface QuestionDao {
    @Transaction
    @Query("SELECT * FROM question")
    fun selectAll(): Maybe<List<QuestionWithAnswers>>

    @Transaction
    @Query("SELECT * FROM question WHERE questionId = :id")
    fun selectById(id: Long): Maybe<QuestionWithAnswers>

    @Transaction
    @Query("SELECT * FROM question WHERE questionId = :id")
    fun selectByIds(vararg id: Long): Maybe<List<QuestionWithAnswers>>

    @Insert
    fun insert(vararg q: Question): Single<List<Long>>

    @Delete
    fun delete(q: Question): Single<Int>
}