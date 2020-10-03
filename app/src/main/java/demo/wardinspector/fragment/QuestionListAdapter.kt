package demo.wardinspector.fragment

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import demo.wardinspector.databinding.ItemInspectionBinding
import demo.wardinspector.databinding.ItemQuestionBinding
import demo.wardinspector.model.QuestionWithAnswers

class QuestionListAdapter(private val context: Context,
                            private val data: List<QuestionWithAnswers>)
    : RecyclerView.Adapter<QuestionListAdapter.ViewHolder>() {

    private val inflater = LayoutInflater.from(context)
    private lateinit var binding: ItemQuestionBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionListAdapter.ViewHolder {
        binding = ItemQuestionBinding.inflate(inflater, parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: QuestionListAdapter.ViewHolder, position: Int) {
        binding.questionWithAnswers = data[position]
        binding.questionAnswerSeekBar.tag = binding.questionAnswerScore
        binding.questionAnswerSeekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, b: Boolean) {
                // Display the current progress of SeekBar in text field
                val scoreTextView = seekBar.tag as TextView
                scoreTextView.text = progress.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder internal constructor(itemView: View)
        : RecyclerView.ViewHolder(itemView) {


    }

}
