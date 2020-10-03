package demo.wardinspector.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import demo.wardinspector.model.InspectionWithAnswerInfo
import demo.wardinspector.model.QuestionWithAnswers
import demo.wardinspector.repository.LocalRepository
import io.reactivex.Maybe

class InspectionsViewModel(app: Application) : AndroidViewModel(app) {

    private val repository = LocalRepository(getApplication())

    fun requestInspections(): Maybe<List<InspectionWithAnswerInfo>> {
        return repository.loadInspections()
    }

    fun requestQuestionsWithAnswers(): Maybe<List<QuestionWithAnswers>> {
        return repository.loadQuestionsWithAnswers()
    }

}
