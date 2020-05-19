package com.example.firebaseopet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

class PopulateUtil {
    public static List<Pessoa> loadPessoas(){
        List<Pessoa> pessoas = new ArrayList<>();
         Pessoa pessoa = new Pessoa();
         pessoa.nome = "Pedro José";
         pessoa.qtde_filhos = 1;
         pessoa.salario = 2400.75;
         pessoa.ativo = false;
         pessoa.pets = Arrays.asList("Pingo","Odin");
         pessoa.data_aniversario = new GregorianCalendar(1991, Calendar.AUGUST,16).getTime();

        pessoas.add(pessoa);

        pessoa = new Pessoa();
        pessoa.nome = "Ana Maria";
        pessoa.qtde_filhos = 2;
        pessoa.salario = 2900.00;
        pessoa.ativo = true;
        pessoa.pets = Arrays.asList("Mingau");
        pessoa.data_aniversario = new GregorianCalendar(1988, Calendar.JANUARY,16).getTime();

        pessoas.add(pessoa);

        pessoa = new Pessoa();
        pessoa.nome = "Pedro Moreira";
        pessoa.qtde_filhos = 0;
        pessoa.salario = 1800.00;
        pessoa.ativo = true;
        pessoa.pets = null;
        pessoa.data_aniversario = new GregorianCalendar(1985, Calendar.DECEMBER,16).getTime();

        pessoas.add(pessoa);

        return pessoas;
    }
}
