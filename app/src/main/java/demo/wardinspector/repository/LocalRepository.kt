package demo.wardinspector.repository

import demo.wardinspector.WardInspectorApp
import demo.wardinspector.model.AppDatabase
import demo.wardinspector.model.InspectionWithAnswerInfo
import demo.wardinspector.model.QuestionWithAnswers
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LocalRepository {
    val db: AppDatabase

    constructor(app: WardInspectorApp) {
        this.db = app.db
    }

    fun loadInspections(): Maybe<List<InspectionWithAnswerInfo>> {
        return db.inspectionDao()
            .selectAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun loadQuestionsWithAnswers(): Maybe<List<QuestionWithAnswers>> {
        return db.questionDao()
            .selectAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}