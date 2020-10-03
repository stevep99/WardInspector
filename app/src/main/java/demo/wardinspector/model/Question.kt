package demo.wardinspector.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Question(@PrimaryKey val questionId: Long,
                    val text: String)

