package demo.wardinspector.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class InspectionWithAnswerInfo(
    @Embedded val inspection: Inspection,
    val answerCount: Int,
    val totalScore: Int
)
