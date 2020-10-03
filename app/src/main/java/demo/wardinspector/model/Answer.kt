package demo.wardinspector.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Answer(@PrimaryKey val answerId: Long,
                  val questionId: Long,
                  val text: String)
