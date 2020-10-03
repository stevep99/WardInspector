package demo.wardinspector.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import demo.wardinspector.R
import demo.wardinspector.model.QuestionWithAnswers
import demo.wardinspector.view_model.InspectionsViewModel
import kotlinx.android.synthetic.main.fragment_questions.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class QuestionsFragment : BaseFragment() {

    lateinit var viewModel: InspectionsViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_questions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadingProgress.visibility = View.VISIBLE

        viewModel = ViewModelProvider(requireActivity()).get(InspectionsViewModel::class.java)
        subscribers.add(
            viewModel.requestQuestionsWithAnswers()
                .doFinally{
                    loadingProgress.visibility = View.GONE
                }
                .subscribe{ populateQuestions(it) }
        )
    }

    fun populateQuestions(questionsWithAnswers: List<QuestionWithAnswers>) {
        val recyclerView = view?.findViewById<RecyclerView>(R.id.questionsRecyclerView)
        val questionListAdapter = QuestionListAdapter(requireActivity(), questionsWithAnswers)

        recyclerView?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = questionListAdapter
        }
    }

}