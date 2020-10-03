package demo.wardinspector.model

import androidx.room.*
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface InspectionDao {
    @Query("""
            SELECT i.*, 
            (SELECT COUNT(*) FROM AnsweredQuestionsCrossRef a WHERE i.inspectionId = a.inspectionId) as answerCount,
            (SELECT SUM(score) FROM AnsweredQuestionsCrossRef a WHERE i.inspectionId = a.inspectionId) as totalScore
             FROM Inspection i 
            """)
    fun selectAll(): Maybe<List<InspectionWithAnswerInfo>>

    @Transaction
    @Insert
    fun insert(vararg inspection: Inspection): Single<List<Long>>

    @Delete
    fun delete(inspection: Inspection): Single<Int>
}