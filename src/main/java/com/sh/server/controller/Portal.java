package com.sh.server.controller;

import com.sh.server.model.Course;
import com.sh.server.model.Student;
import com.sh.server.repository.CourseRepository;
import com.sh.server.model.ApplyList;
import com.sh.server.repository.StudentRepository;

import java.util.*;

public class Portal {
    public void run() {
        CourseRepository courseRepository = new CourseRepository();
        StudentRepository studentRepository = new StudentRepository();

        List<Course> courseList = courseRepository.fileToCourses();
//        List<Course> courseList;
//        if (courseRepository.fileToCourses() == null) {
//            courseList = courseRepository.createCourse();
//            courseRepository.coursesToFile(courseList);
//        } else {
//            courseList = courseRepository.fileToCourses();
//        }

        List<Student> studentList = studentRepository.fileToStudents();
//        List<Student> studentList;
//        if (studentRepository.fileToStudents() == null) {
//            studentList = studentRepository.createStudent();
//            studentRepository.studentsToFile(studentList);
//        } else {
//            studentList = studentRepository.fileToStudents();
//        }

        System.out.print("학번을 입력하세요 : ");
        Scanner sc = new Scanner(System.in);
        int stdId = sc.nextInt();

        try {
            if (!checkId(stdId, studentList)) {
                throw new RuntimeException();
            }
        } catch (RuntimeException e) {
            System.out.println("존재하지 않는 학번입니다.");
            System.exit(0);
        }

        try {
            if (!checkStatus(stdId, studentList)) {
                throw new RuntimeException();
            }
        } catch (RuntimeException e) {
            System.out.println("재학생이 아닙니다.");
            System.exit(0);
        }

        System.out.println(studentList.get(findStudent(stdId, studentList)));

        ApplyList applyList = new ApplyList(); // 메소드를 쓰기 위함
        Map<Integer, List<Course>> applyListIn;
        List<Course> applyCourses;
        if (applyList.fileToApplyList() == null) {
            applyCourses = new ArrayList<>();
            applyListIn = new HashMap<>();
            applyListIn.put(stdId, applyCourses);
        } else {
            applyListIn = applyList.fileToApplyList();
            if (applyListIn.containsKey(stdId)) {
                applyCourses = applyListIn.get(stdId);
            } else {
                applyCourses = new ArrayList<>();
                applyListIn = new HashMap<>();
                applyListIn.put(stdId, applyCourses);
            }
        }

        System.out.println("강의 목록");
        System.out.println(courseList);

        String menu = """
                1. 수강신청
                2. 수강철회
                3. 수강조회
                4. 끝내기
                """;

        while(true) {
            System.out.println(menu);
            int menuNum = sc.nextInt();

            switch (menuNum) {
                case 1 :
                    System.out.print("수강할 강의코드를 입력하세요 : ");
                    int courseId = sc.nextInt();
                    // 학점 체크
                    if (!checkCurrentCredits(stdId, courseId, studentList, courseList)) {
                        System.out.println("학점이 부족합니다.");
                        break;
//                        throw new RuntimeException();
                    }
                    // 정원 체크
                    if (!checkCapacity(courseId, courseList)) {
                        System.out.println("여석이 없습니다.");
                        break;
//                        return;
//                        throw new RuntimeException();
                    };
                    applyCourses.add(courseList.get(findCourse(courseId, courseList))); // 신청목록에추가
                    applyListIn.put(stdId, applyCourses); // 해당 stdId에 덮어쓰기
                    applyList.applylistToFile(applyListIn); // 파일에 저장
                    courseList.get(findCourse(courseId, courseList)).setCurrentCapacity("+"); // 신청인원추가
                    studentList.get(findStudent(stdId, studentList)).setCurrentCredits(courseList.get(findCourse(courseId, courseList)).getCredit(), "+"); // 신청학점추가
                    System.out.println("--------신청 현황--------");
                    System.out.println(applyCourses);
                    System.out.println("현재 " +
                            studentList.get(findStudent(stdId, studentList)).getName() +
                            "님의 신청 학점은" +
                            studentList.get(findStudent(stdId, studentList)).getCurrentCredits() +
                            "학점 입니다.");
                    break;
                case 2 :
                    System.out.println(applyCourses);
                    System.out.println("철회할 강의코드를 입력하세요 : ");
                    int courseId2 = sc.nextInt();
                    System.out.println(courseList.get(findCourse(courseId2, courseList)) + "를 철회하시겠습니까?");
                    applyCourses.remove(courseList.get(findCourse(courseId2, courseList))); // 신청목록에서제거
                    applyListIn.put(stdId, applyCourses); // 해당 stdId에 덮어쓰기
                    applyList.applylistToFile(applyListIn); // 파일에 저장
                    courseList.get(findCourse(courseId2, courseList)).setCurrentCapacity("-"); // 신청인원제거
                    studentList.get(findStudent(stdId, studentList)).setCurrentCredits(courseList.get(findCourse(courseId2, courseList)).getCredit(), "-"); // 신청학점제거
                    System.out.println("--------신청 현황--------");
                    System.out.println(applyCourses);
                    System.out.println("현재 " +
                            studentList.get(findStudent(stdId, studentList)).getName() +
                            "님의 신청 학점은" +
                            studentList.get(findStudent(stdId, studentList)).getCurrentCredits() +
                            "학점 입니다.");
                    break;
                case 3 :
                    System.out.println(studentList.get(findStudent(stdId, studentList)));
                    System.out.println(applyCourses);
                    break;
                case 4 :
                    // 파일로 저장
                    studentRepository.studentsToFile(studentList);
                    courseRepository.coursesToFile(courseList);
                    applyListIn.put(stdId, applyCourses);
                    applyList.applylistToFile(applyListIn);
                    return;
                default:
                    System.out.println("잘못된 입력입니다. 다시 시도해주세요");
                    break;
            }
        }
    }

//    public static void save(StudentRepository sr, CourseRepository cr, ApplyList al, List<Student> studentList, List<Course> courseList, Integer stdId, List<Course> applyCourses, Map<Integer, List<Course>> applyListIn) {
//        sr.studentsToFile(studentList);
//        cr.coursesToFile(courseList);
//        applyListIn.put(stdId, applyCourses);
//        al.applylistToFile(applyListIn);
//    }

    // 학번 체크
    public static boolean checkId(int stdId, List<Student> studentList) {
        for (Student std : studentList) {
            if (stdId == std.getId()) {
                return true;
            }
        }
        return false;
    }

    // 상태 체크
    public static boolean checkStatus(int stdId, List<Student> studentList) {
        // 학번 및 상태 체크
        for (Student std : studentList) {
            if (stdId == std.getId()) {
                if (std.isStatus()){
                    return true;
                }
            }
        }
        return false;
    }

    // 정원 체크
    public static boolean checkCapacity(int courseId, List<Course> courseList){
        return courseList.get(findCourse(courseId, courseList)).getCurrentCapacity() < courseList.get(findCourse(courseId, courseList)).getCapacity();
    }

    // 학점 체크
    public static boolean checkCurrentCredits(int stdId, int courseId, List<Student> studentList, List<Course> courseList) {
        return 18 - studentList.get(findStudent(stdId, studentList)).getCurrentCredits() -  courseList.get(findCourse(courseId, courseList)).getCredit() >= 0;
    }

    public static int findCourse(int courseId, List<Course> courseList) {
        for (int i = 0; i < courseList.size(); i++) {
            if (courseId == courseList.get(i).getId()) {
                return i;
            }
        }
        return 0;
    }

    public static int findStudent(int stdId, List<Student> studentList) {
        for (int i = 0; i < studentList.size(); i++) {
            if (stdId == studentList.get(i).getId()) {
                return i;
            }
        }
        return 0;
    }
}
