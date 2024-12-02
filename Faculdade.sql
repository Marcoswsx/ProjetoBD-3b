CREATE DATABASE FACULDADE;

USE FACULDADE;

CREATE TABLE ALUNO(
    id_aluno int primary key not null auto_increment,
    nome text,
    matricula text,
    curso text,
    telefone text
);