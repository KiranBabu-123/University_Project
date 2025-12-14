create table if not exists professor (
    id int auto_increment primary key,
    name varchar(255),
    department varchar(255)
);

create table if not exists student (
    id int auto_increment primary key,
    name varchar(255),
    email varchar(255)
);

create table if not exists course (
    id int auto_increment primary key,
    name varchar(255),
    credits int,
    professorid int,
    foreign key (professorid) references professor(id)
);

create table if not exists course_student (
    courseid int,
    studentid int,
    primary key (courseid, studentid),
    foreign key (courseid) references course(id),
    foreign key (studentid) references student(id)
);
