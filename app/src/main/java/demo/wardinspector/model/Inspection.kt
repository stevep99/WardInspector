package demo.wardinspector.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Inspection(@PrimaryKey val inspectionId: Long,
                      val name: String,
                      val complete: Boolean = false)

