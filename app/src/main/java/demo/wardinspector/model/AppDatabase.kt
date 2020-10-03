package demo.wardinspector.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities =
    [Inspection::class, Question::class, Answer::class, AnsweredQuestionsCrossRef::class],
    version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun inspectionDao(): InspectionDao
    abstract fun questionDao(): QuestionDao
    abstract fun answerDao(): AnswerDao
    abstract fun answeredQuestionsCrossRefDao(): AnsweredQuestionsCrossRefDao
}
