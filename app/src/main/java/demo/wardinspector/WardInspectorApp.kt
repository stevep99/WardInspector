package demo.wardinspector

import android.app.Application
import androidx.room.Room
import demo.wardinspector.model.*
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class WardInspectorApp(): Application() {
    lateinit var db: AppDatabase  //probably should use dependency injection instead

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
        Timber.d("App created")

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "ward_inspector"
        ).build()

        applicationContext.deleteDatabase("ward_inspector")

        insertDemoData()
    }

    /**
     * Populate the database with some initial example data
     */
    private fun insertDemoData() {
        val setup = arrayListOf(

            Single.fromCallable{ db.clearAllTables() },

            db.inspectionDao().insert(
                Inspection(1, "First inspection", true),
                Inspection(2, "Second inspection"),
                Inspection(3, "Third inspection")
            ),

            db.questionDao().insert(
                Question(1, "Ward appears generally clean, tidy & well maintained?"),
                Question(2, "How often does the NiC / Matron work clinically?"),
                Question(3, "How often do you join medial rounds?")
            ),

            db.answerDao().insert(
                Answer(1, 1, "Q1 answer-a"),
                Answer(2, 1, "Q1 answer-b"),
                Answer(3, 2, "Q2 answer-a"),
                Answer(4, 2, "Q2 answer-b"),
                Answer(5, 3, "Q3 answer-a"),
                Answer(6, 3, "Q3 answer-b")
            ),

            db.answeredQuestionsCrossRefDao().insert(
                AnsweredQuestionsCrossRef(1, 1, 1, 100),
                AnsweredQuestionsCrossRef(1, 2, 3, 50),
                AnsweredQuestionsCrossRef(1, 3, 5, 75),
                AnsweredQuestionsCrossRef(2, 1,2, 80)
            )
        )

        Single.concat(setup)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }
}

