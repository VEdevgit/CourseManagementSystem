/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursemanagemntsystem;

import Model.Group;
import Model.Lessons;
import Model.Students;
import Model.Teachers;
import dao.DBHelper;
import dao.GroupDao;
import dao.GroupImpl;
import dao.LessonsDao;
import dao.LessonsImpl;
import dao.StudentsImpl;
import dao.StudentsDao;
import dao.TeachersDao;
import dao.TeachersImpl;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DEA-Elgun
 */
public class CourseManagemntSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Lessons lessons = new Lessons();
        Group group = new Group();
        Students students = new Students();
        Teachers teachers = new Teachers();
        String emeliyyat = "";
        Scanner sc = new Scanner(System.in);
        System.out.println("Hansi table ustunde emeliyyatlar aparacaqsiniz?");
        System.out.println("1.Group");
        System.out.println("2.Lessons");
        System.out.println("3.Students");
        System.out.println("4.Teachers");
        String secilmisTable = sc.nextLine();

        if (secilmisTable.equals("Group")) {
            GroupDao groupDao = new GroupImpl();
            System.out.println("Group uzerinde hansi emeliyyatlar aparacaqsiniz?");
            System.out.println("1.Add");
            System.out.println("2.Show");
            System.out.println("3.Update");
            System.out.println("4.Delete");
            System.out.println("5.Id ile melumat getirmek");
            System.out.println("6.Search");
            emeliyyat = sc.nextLine();
            if (emeliyyat.equals("Add")) {
                System.out.println("Group no daxil edin: ");
                group.setGroup_No(sc.nextInt());
                System.out.println("Student i-sin qeyd edin: ");
                group.setStudent_id(sc.nextInt());
                System.out.println("Teacher i-sini qeyd edin: ");
                group.setTeacher_id(sc.nextInt());
                System.out.println("Documentation daxil edin: ");
                group.setDocumentation(sc.nextLine());
                try {
                    boolean result = groupDao.GroupAdd(group);
                    if (result) {
                        System.out.println("Emeliyyat yerine yetirildi");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (emeliyyat.equals("Show")) {
                try {
                    List<Group> listGroup = groupDao.getGroupList();
                    if (listGroup == null) {
                        System.out.println("Melumat yoxdur.");
                    }
                    for (Group group1 : listGroup) {
                        System.out.print(group1.getId() + " ");
                        System.out.print(group1.getGroup_No() + " ");
                        System.out.print(group1.getStudents().getName() + " ");
                        System.out.print(group1.getStudents().getSurname() + " ");
                        System.out.print(group1.getTeachers().getName() + " ");
                        System.out.print(group1.getTeachers().getSurname() + " ");
                        System.out.println(group1.getDocumentation() + " ");

                    }
                } catch (Exception e) {
                    System.out.println("Emeliyyat ugursuz oldu.");
                    e.printStackTrace();

                }
            } else if (emeliyyat.equals("Update")) {
                System.out.println("Deyisdirmek istediyiniz qrup-un id-sin daxil edin: ");
                int id = sc.nextInt();
                try {
                    group = groupDao.getGroupById(id);
                    System.out.print(group.getId() + "  ");
                    System.out.print(group.getGroup_No() + "  ");
                    System.out.print(group.getStudent_id() + "  ");
                    System.out.print(group.getTeacher_id() + "  ");
                    System.out.println(group.getDocumentation() + "  ");
                    System.out.println("Group nomresin yazin: ");
                    int group_No = sc.nextInt();
                    System.out.println("Student id-sin yazin: ");
                    int student_id = sc.nextInt();
                    System.out.println("Teacher id-sin yazin: ");
                    int teacher_id = sc.nextInt();
                    System.out.println("Documentation yazin: ");
                    sc.nextLine();
                    String documentation = sc.nextLine();
                    Group group1 = new Group();
                    group1.setId(id);
                    group1.setGroup_No(group_No);
                    group1.setStudent_id(student_id);
                    group1.setTeacher_id(teacher_id);
                    group1.setDocumentation(documentation);
                    boolean isUpdate = groupDao.updateGroup(group1);
                    if (isUpdate) {
                        System.out.println("Emeliyyat ugurla yerine yetirildi!");
                    } else {
                        System.out.println("Emeliyyat ugursuz oldu!");
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            } else if (emeliyyat.equals("Delete")) {
                try {
                    System.out.println("Silmek istediyiniz qrupun id-sin daxil edin: ");
                    int id = sc.nextInt();
                    boolean isDelete = groupDao.deleteGroup(id);
                    if (isDelete) {
                        System.out.println("Emeliyyat ugurla yerine yetirildi!");
                    } else {
                        System.out.println("Emeliyyat ugursuz oldu!");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (emeliyyat.equals("5")) {
                System.out.println("Id daxil edin: ");
                int id = sc.nextInt();
                try {
                    Group groups = groupDao.getGroupById(id);
                    System.out.print(groups.getId() + "  ");
                    System.out.print(groups.getGroup_No() + "  ");
                    System.out.print(groups.getStudents().getName() + "  ");
                    System.out.print(groups.getStudents().getSurname() + "  ");
                    System.out.print(groups.getTeachers().getName() + "  ");
                    System.out.print(groups.getTeachers().getSurname() + "  ");
                    System.out.println(groups.getDocumentation());
                } catch (Exception e) {
                    System.out.println("Emeliyyat ugursuz oldu!");
                    e.printStackTrace();

                }

            }else if(emeliyyat.equals("Search")){
                System.out.println("Axtardiginiz sozu daxil edin: ");
                String key = sc.nextLine();
                try {
                    List<Group> listGroup = groupDao.searchGroup(key);
                     if (listGroup == null) {
                        System.out.println("Melumat yoxdur.");
                    }
                    for (Group group1 : listGroup) {
                        System.out.print(group1.getId() + " ");
                        System.out.print(group1.getGroup_No() + " ");
                        System.out.print(group1.getStudents().getName() + " ");
                        System.out.print(group1.getStudents().getSurname() + " ");
                        System.out.print(group1.getTeachers().getName() + " ");
                        System.out.print(group1.getTeachers().getSurname() + " ");
                        System.out.println(group1.getDocumentation() + " ");

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
        } else if (secilmisTable.equals("Lessons")) {
            LessonsDao lessonsDao = new LessonsImpl();
            System.out.println("Lessons uzerinde hansi emeliyyatlar aparacaqsiniz?");
            System.out.println("1.Add");
            System.out.println("2.Show");
            System.out.println("3.Update");
            System.out.println("4.Delete");
            System.out.println("5.Id ile melumat getirmek");
            System.out.println("6.Search");
            emeliyyat = sc.nextLine();
            if (emeliyyat.equals("Add")) {
                System.out.println("Subject elave edin: ");
                lessons.setSubject(sc.nextLine());
                System.out.println("Duration qeyd edin: ");
                lessons.setDuration(sc.nextInt());
                System.out.println("Documentation daxil edin: ");
                sc.nextLine();
                lessons.setDocumentation(sc.nextLine());

                try {
                    boolean result = lessonsDao.LessonAdd(lessons);
                    if (result) {
                        System.out.println("Emeliyyat ugurla yerine yetirildi.");
                    } else {
                        System.out.println("Emeliyyat yerine yetirile bilmedi!!!");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (emeliyyat.equals("Show")) {
                try {
                    List<Lessons> listLesson = lessonsDao.getLessonList();
                    for (Lessons lesson : listLesson) {
                        System.out.print(lesson.getId() + "  ");
                        System.out.print(lesson.getSubject() + "  ");
                        System.out.print(lesson.getDuration() + "  ");
                        System.out.println(lesson.getDocumentation());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (emeliyyat.equals("Update")) {
                System.out.println("Deyismek istediyiniz lessonun id-sin yazin: ");
                int id = sc.nextInt();
                Lessons lesson = null;
                try {
                    lesson = lessonsDao.getLessonById(id);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                System.out.print(lesson.getId() + "  ");
                System.out.print(lesson.getSubject() + "  ");
                System.out.print(lesson.getDuration() + "  ");
                System.out.println(lesson.getDocumentation());

                Lessons lessons1 = new Lessons();
                lessons1.setId(id);
                System.out.println("Subject daxil edin:");
                sc.nextLine();
                lessons1.setSubject(sc.nextLine());
                System.out.println("Duration daxil edin:");
                lessons1.setDuration(sc.nextInt());
                sc.nextLine();
                System.out.println("Documentation daxil edin:");
                lessons1.setDocumentation(sc.nextLine());

                try {
                    boolean isAdd = lessonsDao.updateLesson(lessons1);
                    if (isAdd) {
                        System.out.println("Emeliyyat ugurla yerine yetirildi");
                    } else {
                        System.out.println("Emeliyyat ugursuz oldu");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (emeliyyat.equals("Delete")) {
                System.out.println("Silmek istediyiniz setrin id-sin daxil edin: ");
                int id = sc.nextInt();
                try {
                    boolean isDelete = lessonsDao.deleteLesson(id);
                    if (isDelete) {
                        System.out.println("Emeliyyat ugurla yerine yetirildi");
                    } else {
                        System.out.println("Emeliyyat ugursuz oldu");
                    }
                } catch (Exception ex) {
                    Logger.getLogger(CourseManagemntSystem.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (emeliyyat.equals("5")) {
                System.out.println("Hansi id-li istifadecini arasdirirsiniz?");
                int id = sc.nextInt();
                try {
                    Lessons lesson = lessonsDao.getLessonById(id);
                    System.out.print(lesson.getId() + "  ");
                    System.out.print(lesson.getSubject() + "  ");
                    System.out.print(lesson.getDuration() + "  ");
                    System.out.println(lesson.getDocumentation());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (emeliyyat.equals("Search")) {
                System.out.println("Axtardiginiz elementi daxil edin: ");
                String key = sc.nextLine();
                try {
                    List<Lessons> listLesson = lessonsDao.searchLesson(key);
                    for (Lessons lesson : listLesson) {
                        System.out.print(lesson.getSubject() + "        ");
                        System.out.print(lesson.getDuration() + "        ");
                        System.out.println(lesson.getDocumentation());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } else if (secilmisTable.equals("Students")) {
            StudentsDao studentsDao = new StudentsImpl();
            System.out.println("Students uzerinde hansi emeliyyatlar aparacaqsiniz?");
            System.out.println("1.Add");
            System.out.println("2.Show");
            System.out.println("3.Update");
            System.out.println("4.Delete");
            System.out.println("5.Id ile melumat getirmek");
            System.out.println("6.Search");
            emeliyyat = sc.nextLine();
            if (emeliyyat.equals("Add")) {
                System.out.println("Name elave edin: ");
                students.setName(sc.nextLine());
                System.out.println("Surname qeyd edin: ");
                students.setSurname(sc.nextLine());
                System.out.println("Dogum tarixin qeyd edin: ");
                students.setBirth_date(Date.valueOf(sc.nextLine()));
                System.out.println("Documentation daxil edin: ");
                students.setDocumentation(sc.nextLine());

                try {
                    boolean result = studentsDao.StudentsAdd(students);
                    if (result) {
                        System.out.println("Emeliyyat ugurla yerine yetirildi.");
                    } else {
                        System.out.println("Emeliyyat yerine yetirile bilmedi!!!");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            } else if (emeliyyat.equals("Show")) {
                try {
                    List<Students> listStudent = studentsDao.getStudentList();
                    for (Students student : listStudent) {
                        System.out.print(student.getId() + "  ");
                        System.out.print(student.getName() + "  ");
                        System.out.print(student.getSurname() + "  ");
                        System.out.print(student.getBirth_date() + "  ");
                        System.out.println(student.getDocumentation());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (emeliyyat.equals("Update")) {
                System.out.println("Deisdirmek istediyiniz telebenin id-sin yazin: ");
                int id = sc.nextInt();
                try {
                    Students student = studentsDao.getStudentById(id);
                    System.out.print(student.getId() + "  ");
                    System.out.print(student.getName() + "  ");
                    System.out.print(student.getSurname() + "  ");
                    System.out.print(student.getBirth_date() + "  ");
                    System.out.println(student.getDocumentation());
                    Students students1 = new Students();
                    students1.setId(id);
                    System.out.println("Name elave edin: ");
                    sc.nextLine();
                    students1.setName(sc.nextLine());
                    System.out.println("Surname qeyd edin: ");
                    students1.setSurname(sc.nextLine());
                    System.out.println("Dogum tarixin qeyd edin: ");
                    students1.setBirth_date(Date.valueOf(sc.nextLine()));
                    System.out.println("Documentation daxil edin: ");
                    students1.setDocumentation(sc.nextLine());
                    boolean isUpdate = studentsDao.updateStudent(students1);
                    if (isUpdate) {
                        System.out.println("Emeliyyat ugurla yerine yetirildi!");
                    } else {
                        System.out.println("Emeliyyat  yerine yetirilmedi!");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (emeliyyat.equals("Delete")) {

            } else if (emeliyyat.equals("5")) {
                System.out.println("Hansi id-li istifadecini arasdirirsiniz?");
                int id = sc.nextInt();
                try {
                    Students student = studentsDao.getStudentById(id);
                    System.out.print(student.getId() + "  ");
                    System.out.print(student.getName() + "  ");
                    System.out.print(student.getSurname() + "  ");
                    System.out.print(student.getBirth_date() + "  ");
                    System.out.println(student.getDocumentation());
                } catch (Exception ex) {
                    ex.printStackTrace();

                }
            } else if (emeliyyat.equals("Search")) {
                System.out.println("axtardiginiz sozu daxil edin: ");
                String key = sc.nextLine();
                try {
                    List<Students> liststudent = studentsDao.searcheStudent(key);
                    for (Students students1 : liststudent) {
                        System.out.print(students1.getId() + "  ");
                        System.out.print(students1.getName() + "  ");
                        System.out.print(students1.getSurname() + "  ");
                        System.out.print(students1.getBirth_date() + "  ");
                        System.out.println(students1.getDocumentation());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();

                }
            }
        } else if (secilmisTable.equals("Teachers")) {
            TeachersDao teachersDao = new TeachersImpl();
            System.out.println("Teachers uzerinde hansi emeliyyatlar aparacaqsiniz?");
            System.out.println("1.Add");
            System.out.println("2.Show");
            System.out.println("3.Update");
            System.out.println("4.Delete");
            System.out.println("5.Id ile melumat getirmek");
            System.out.println("6.Search");
            emeliyyat = sc.nextLine();

            if (emeliyyat.equals("Add")) {
                System.out.println("Adi daxil edin: ");
                teachers.setName(sc.nextLine());
                System.out.println("Soyadi daxil edin: ");
                teachers.setSurname(sc.nextLine());
                System.out.println("Dogum tarixini qeyd edin: ");
                teachers.setBirthday(Date.valueOf(sc.nextLine()));
                System.out.println("Elaqe nomresin qeyd edin: ");
                teachers.setPhone(sc.nextLong());
                System.out.println("Address daxil edin: ");
                sc.nextLine();
                teachers.setAddress(sc.nextLine());
                System.out.println("Documentation yazin: ");
                teachers.setDocumentation(sc.nextLine());
                System.out.println("Hansi fenden ders deyecek? ");
                teachers.setLesson_id(sc.nextInt());
                try {
                    boolean result = teachersDao.TeacherAdd(teachers);
                    if (result) {
                        System.out.println("Emeliyyat yerine yetirilirdi.");
                    } else {
                        System.out.println("Emeliyyat yerine yetirile bilmedi!!!");
                    }
                } catch (Exception ex) {
                    Logger.getLogger(CourseManagemntSystem.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (emeliyyat.equals("Show")) {
                try {
                    List<Teachers> listTeacher = teachersDao.getTeacherList();
                    if (listTeacher == null) {
                        System.out.println("Melumat yoxdur.");
                    }
                    for (Teachers teachers1 : listTeacher) {
                        System.out.print(teachers1.getId() + "  ");
                        System.out.print(teachers1.getName() + "  ");
                        System.out.print(teachers1.getSurname() + "  ");
                        System.out.print(teachers1.getBirthday() + "  ");
                        System.out.print(teachers1.getAddress() + "  ");
                        System.out.print(teachers1.getPhone() + "  ");
                        System.out.print(teachers1.getLesson().getSubject() + "  ");
                        System.out.println(teachers1.getDocumentation());

                    }
                } catch (Exception e) {
                    System.out.println("Emeliyyat ugursuz oldu");
                    e.printStackTrace();
                }

            } else if (emeliyyat.equals("Update")) {
                System.out.println("Deyisdirmek istediyiniz muelimin id-sin daxil edin: ");
                int id = sc.nextInt();
                try {
                    teachers = teachersDao.getTeacherById(id);
                    System.out.print(teachers.getId() + "  ");
                    System.out.print(teachers.getName() + "  ");
                    System.out.print(teachers.getSurname() + "  ");
                    System.out.print(teachers.getBirthday() + "  ");
                    System.out.print(teachers.getAddress() + "  ");
                    System.out.print(teachers.getPhone() + "  ");
                    System.out.print(teachers.getLesson().getSubject() + "  ");
                    System.out.println(teachers.getDocumentation());
                    Teachers teacher1 = new Teachers();
                    System.out.println("Muellimin adini yazin: ");
                    sc.nextLine();
                    teacher1.setId(id);
                    teacher1.setName(sc.nextLine());
                    System.out.println("Muellimin soyadini qeyd edin: ");
                    teacher1.setSurname(sc.nextLine());
                    System.out.println("Muellimin dogum tarixini qeyd edin: ");
                    teacher1.setBirthday(Date.valueOf(sc.nextLine()));
                    System.out.println("Address daxil edin: ");
                    teacher1.setAddress(sc.nextLine());
                    System.out.println("Telefon nomresin qeyd edin: ");
                    teacher1.setPhone(sc.nextInt());
                    System.out.println("Lesson id daxil edin: ");
                    teacher1.setLesson_id(sc.nextInt());
                    System.out.println("Documentation qeyd edin: ");
                    sc.nextLine();
                    teacher1.setDocumentation(sc.nextLine());
                    boolean isUpdate = teachersDao.updateTeacher(teacher1);
                    if (isUpdate) {
                        System.out.println("Emeliyyat ugurla yerine yetirildi!");
                    } else {
                        System.out.println("Emeliyyat ugursuz oldu!");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (emeliyyat.equals("Delete")) {
                System.out.println("silmek istediyniz muellimin id-sin daxil edin: ");
                int id = sc.nextInt();
                try {
                    boolean isDelete = teachersDao.deleteTeacher(id);
                    if (isDelete) {
                        System.out.println("Emeliyyat ugurla yerine yetirildi!");
                    } else {
                        System.out.println("Emeliyyat ugursuz oldu!");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (emeliyyat.equals("5")) {
                int id = 0;
                System.err.println("Gormek istediyininz muellimin id-sin daxil edin: ");
                id = sc.nextInt();
                try {
                    Teachers teacher = teachersDao.getTeacherById(id);
                    System.out.print(teacher.getId() + "  ");
                    System.out.print(teacher.getName() + "  ");
                    System.out.print(teacher.getSurname() + "  ");
                    System.out.print(teacher.getBirthday() + "  ");
                    System.out.print(teacher.getAddress() + "  ");
                    System.out.print(teacher.getPhone() + "  ");
                    System.out.print(teacher.getLesson().getSubject() + "  ");
                    System.out.println(teacher.getDocumentation());
                    if (teacher == null) {
                        System.out.println("Melumat yoxdur.");
                    }
                } catch (Exception ex) {
                    System.out.println("Emeliyyat ugursuz oldu");
                    ex.printStackTrace();
                }
            } else if (emeliyyat.equals("Search")) {
                int id = 0;
                System.err.println("Axtardiginiz elementi daxil edin: ");
                String key = sc.nextLine();
                try {
                    List<Teachers> listTeacher = teachersDao.searchTeacher(key);
                    for (Teachers teacher : listTeacher) {
                        System.out.print(teacher.getName() + "  ");
                        System.out.print(teacher.getSurname() + "  ");
                        System.out.print(teacher.getBirthday() + "  ");
                        System.out.print(teacher.getAddress() + "  ");
                        System.out.print(teacher.getPhone() + "  ");
                        System.out.print(teacher.getLesson().getSubject() + "  ");
                        System.out.println(teacher.getDocumentation());
                    }

                } catch (Exception ex) {
                    System.out.println("Emeliyyat ugursuz oldu");
                    ex.printStackTrace();
                }
            }

        }
    }

}
