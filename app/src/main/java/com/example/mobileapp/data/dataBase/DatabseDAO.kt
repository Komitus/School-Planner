package com.example.mobileapp.data.dataBase

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mobileapp.data.Entities.*
import java.nio.file.Files.delete
import java.time.LocalDate



@Dao
interface DatabseDAO {

    @Insert
    fun addGrade(grade : GradeEntity)

    @Insert
    fun addCourse(course: CourseEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addPlusRow(plusEntity: PlusEntity)

    @Query("SELECT * FROM grades_table")
    fun readAllGrades() : LiveData<List<GradeEntity>>

    @Query("SELECT * FROM grades_table WHERE course  = :courseId ORDER BY date desc")
    fun readGradesForCourse(courseId: Int) : LiveData<List<GradeEntity>>

    @Query("SELECT * FROM courses_table")
    fun readAllCourses(): LiveData<List<CourseEntity>>

    @Query("SELECT * FROM lessons_table WHERE dayOfWeek = :day")
    fun getLessonsByDay(day: Int) : LiveData<List<LessonEntity>>

    @Query("SELECT * FROM lessons_table")
    fun readAllLessons() : LiveData<List<LessonEntity>>

    @Delete
    fun deletePrev(lesson: LessonEntity)

    @Insert
    fun addNew(lesson: LessonEntity)

    @Query("DELETE FROM courses_table WHERE name = :name")
    fun deleteOld(name: String)

    @Query("DELETE FROM lessons_table WHERE dayOfWeek = :day AND lessonNumber = :lesson")
    fun deleteOldLessons(day : Int, lesson : Int)

    @Query("UPDATE grades_table SET value = :value, category = :category, date = :date WHERE id = :gradeId")
    fun updateGrade(gradeId: Int, value: Int, category: String, date: LocalDate)

    @Query("UPDATE lessons_table SET courseName = :course WHERE dayOfWeek = :day AND lessonNumber = :lessonNumber")
    fun updateLesson(course: String, day: Int, lessonNumber: Int)

    @Query("UPDATE courses_table SET howMany = :pluses, teacher = :teacher WHERE name = :name")
    fun updateCourse(name: String, pluses: Int, teacher: String)

    @Query("DELETE FROM lessons_table WHERE lessonNumber = :lessonNumber AND dayOfWeek = :day")
    fun deleteLesson(lessonNumber: Int, day: Int)

    @Query("DELETE FROM grades_table WHERE id = :gradeId")
    fun removeSelectedGrade(gradeId: Int)

    @Transaction
    fun removeAllSelectedGrades(selectedGradesIds: List<Int>) {
        for(idx in selectedGradesIds){
            removeSelectedGrade(idx)
        }
    }

    @Query("DELETE FROM lessons_table WHERE courseName = :name")
    fun deleteLessonsByCourse(name: String)

    @Query("DELETE FROM grades_table WHERE course = :courseId")
    fun deleteGradesByCourse(courseId: Int)

    @Query("DELETE FROM pluses_table WHERE courseName = :courseName")
    fun deletePlusRow(courseName: String)

    @Transaction
    fun deleteAllCourseInfo(courseName: String, courseId: Int) {
        deleteLessonsByCourse(courseName)
        deleteGradesByCourse(courseId)
        deleteOld(courseName)
        deletePlusRow(courseName)
    }

    @Query("UPDATE pluses_table SET actual = actual + 1 WHERE courseName = :courseName")
    fun updateActualPluses(courseName: String)

    @Query("UPDATE pluses_table SET used = used + actual, actual = 0 WHERE courseName = :courseName")
    fun updateUsedPluses(courseName: String)

    @Query("SELECT actual FROM pluses_table WHERE courseName = :courseName")
    fun selectActualPluses(courseName: String): Int

    @Query("SELECT howMany FROM courses_table WHERE name = :courseName")
    fun selectRequiredPluses(courseName: String): Int

    @Query("SELECT id FROM courses_table WHERE name = :courseName")
    fun selectCourseId(courseName: String): Int

    @Transaction
    fun addPlusWithCheck(courseName: String){
        updateActualPluses(courseName)
        val required = selectRequiredPluses(courseName)
        val actual = selectActualPluses(courseName)
        if(required == actual){
            addGrade(GradeEntity(0, 5, "pluses", LocalDate.now(), selectCourseId(courseName)))
            updateUsedPluses(courseName)
        }
    }

    @Query("UPDATE pluses_table SET actual = actual - 1  WHERE courseName = :courseName AND actual > 0")
    fun deleteOnePlus(courseName: String)

    @Query("SELECT * FROM pluses_table")
    fun readAllPluses() : LiveData<List<PlusEntity>>

    @Insert
    fun addSubstitution(substitutionEntity: SubstitutionEntity)

    @Query("SELECT * FROM substitutions_table WHERE date = :date")
    fun readSubstitution(date: String) : LiveData<List<SubstitutionEntity>>
}