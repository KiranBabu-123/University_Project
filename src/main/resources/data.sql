insert into professor(id, name, department)
values(1, 'John Smith', 'Computer Science');
insert into professor(id, name, department)
values(2, 'Mary Johnson', 'Physics');
insert into professor(id, name, department)
values(3, 'David Lee', 'Mathematics');

insert into student(id, name, email)
values(1, 'Alice Johnson', 'alice@example.com');
insert into student(id, name, email)
values(2, 'Bob Davis', 'bob@example.com');
insert into student(id, name, email)
values(3, 'Eva Wilson', 'eva@example.com');

insert into course(id, name, credits, professorId)
values(1, 'Introduction to Programming', 3, 1);
insert into course(id, name, credits, professorId)
values(2, 'Quantum Mechanics', 4, 2);
insert into course(id, name, credits, professorId)
values(3, 'Calculus', 4, 3);

insert into course_student(courseId, studentId)
values(1, 1);
insert into course_student(courseId, studentId)
values(1, 2);
insert into course_student(courseId, studentId)
values(2, 2);
insert into course_student(courseId, studentId)
values(2, 3);
insert into course_student(courseId, studentId)
values(3, 1);
insert into course_student(courseId, studentId)
values(3, 3);
