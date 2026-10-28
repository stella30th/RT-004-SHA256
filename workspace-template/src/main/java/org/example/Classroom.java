package org.example;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Classroom {

    private int id;
    List<Course> courses;

    public Classroom(int id) {
        this.id = id;
        this.courses = new ArrayList<>();
    }

    public static class Course {
        String name;
        LocalTime startTime;
        LocalTime endTime;

        public Course(String name, String startTime, String endTime) {
            this.name = name;
            this.startTime = LocalTime.parse(startTime);
            this.endTime = LocalTime.parse(endTime);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            Course course = (Course) obj;

            return name.equals(course.name)
                    && startTime.equals(course.startTime)
                    && endTime.equals(course.endTime);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, startTime, endTime);
        }
    }

    public void addCourse(Course course) {
        if (!courses.contains(course)) {
            courses.add(course);
        }
    }

    public void removeCourse(Course course) {
        courses.remove(course);
    }

    public boolean isFreeAt(String checkTime) {
        LocalTime time = LocalTime.parse(checkTime);

        for (Course course : courses) {
            if (!time.isBefore(course.startTime)
                    && !time.isAfter(course.endTime)) {
                return false;
            }
        }

        return true;
    }

    public boolean checkCourseConflict(Course newCourse) {
        LocalTime newStartTime = newCourse.startTime;
        LocalTime newEndTime = newCourse.endTime;

        for (Course course : courses) {
            if (!(newEndTime.isBefore(course.startTime)
                    || newStartTime.isAfter(course.endTime))) {
                return false;
            }
        }

        return true;
    }
}