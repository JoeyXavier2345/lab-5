package usecase;

import api.GradeDataBase;
import entity.Grade;
import entity.Team;

/**
 * GetAverageGradeUseCase class.
 */
public final class GetAverageGradeUseCase {
    private final GradeDataBase gradeDataBase;

    public GetAverageGradeUseCase(GradeDataBase gradeDataBase) {
        this.gradeDataBase = gradeDataBase;
    }

    /**
     * Get the average grade for a course across your team.
     * @param course The course.
     * @return The average grade.
     */
    public float getAverageGrade(String course) {
        float sum = 0;
        int count = 0;
        final Team team = gradeDataBase.getMyTeam();
        String[] members = team.getMembers();

        for (String s : members) {
            Grade[] grades; 
            try {grades = gradeDataBase.getGrades(s); } catch (Exception e) {throw e;}
            for (Grade g : grades) {
                if (g.getCourse() == course) {
                    count++;
                    sum += (float) g.getGrade();
                }
            }
        }

        if (count == 0) {
            return 0;
        }
        return sum / count;
    }
}
