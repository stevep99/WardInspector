package demo.wardinspector.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(primaryKeys = ["inspectionId", "questionId"])
data class AnsweredQuestionsCrossRef(val inspectionId: Long,
                                     val questionId: Long,
                                     val answerId: Long,
                                     val score: Int)


