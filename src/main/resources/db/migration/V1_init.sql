create table teacher (
                         id bigserial primary key,
                         name varchar(255),
                         department varchar(255)
);

create table student (
                         id bigserial primary key,
                         name varchar(255),
                         email varchar(255)
);

create table course (
                        id bigserial primary key,
                        title varchar(255),
                        credits int,
                        teacher_id bigint references teacher(id)
);

create table student_courses (
                                 student_id bigint references student(id),
                                 course_id bigint references course(id),
                                 primary key (student_id, course_id)
);
