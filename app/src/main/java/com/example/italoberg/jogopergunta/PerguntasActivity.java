package com.example.italoberg.jogopergunta;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

public class PerguntasActivity extends AppCompatActivity {
    Pergunta p;
    TextView textViewTitulo;
    RadioButton radioButton;
    int dificuldade;
    TextView textViewDebug;
    public RadioGroup radio_g;
    public RadioButton radioButtonR1;
    public RadioButton radioButtonR2;
    public RadioButton radioButtonR3;
    public RadioButton radioButtonR4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perguntas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        textViewTitulo = (TextView) findViewById(R.id.textViewTitulo);

        radioButtonR1 = (RadioButton) findViewById(R.id.radio_r1);
        radioButtonR2 = (RadioButton) findViewById(R.id.radio_r2);
        radioButtonR3 = (RadioButton) findViewById(R.id.radio_r3);
        radioButtonR4 = (RadioButton) findViewById(R.id.radio_r4);

        //fillDatabase();
        p = new Pergunta();

        List<Pergunta> todo = Pergunta.listAll(Pergunta.class);
        p = todo.get(0);

        fillViewWithQuestion(p);

        dificuldade = getIntent().getExtras().getInt("dificuldade");
        Log.i("Dificuldade", Integer.toString(dificuldade));

        List<Pergunta> round = RandomByLevel(todo,dificuldade);
        Log.i("Pergunta", round.get(0).titulo);
        Log.i("Pergunta", round.get(1).titulo);
        Log.i("Tamanhooooo", Integer.toString(round.size()));

        fillViewWithQuestion(round.get(0));
        round.remove(0);
    }


    public void fillViewWithQuestion(Pergunta p){
        textViewTitulo.setText(p.titulo);
        radioButtonR1.setText(p.r1);
        radioButtonR2.setText(p.r2);
        radioButtonR3.setText(p.r3);
        radioButtonR4.setText(p.r4);

    }

    public List<Pergunta> RandomByLevel(List<Pergunta> perguntas, int dif){
        Random rand = new Random();
        int num;
        List<Pergunta> out = perguntas;
        if(dif==1) {
             perguntas = Pergunta.find(Pergunta.class, "difculdade<=?", "4");
            while (perguntas.size()>2){
                num = rand.nextInt(perguntas.size());
                perguntas.remove(num);
            }
            out = perguntas;
        }
        else if(dif==2){
             perguntas = Pergunta.find(Pergunta.class, "difculdade<=? and difculdade>?", "7", "4");
            Log.i("Pergunta",perguntas.get(0).titulo );
            while (perguntas.size()>3){
                num = rand.nextInt(perguntas.size());
                perguntas.remove(num);
            }
            out = perguntas;
        }
        else {
            perguntas = Pergunta.find(Pergunta.class, "difculdade>?", "7");
            Log.i("Pergunta",perguntas.get(0).titulo );

        }
        return out;
    }

    public void showBank(){
        textViewDebug = (TextView) findViewById(R.id.textViewDebug);

        List<Pergunta> todo = Pergunta.listAll(Pergunta.class);

        String out ="";
        for(int i=0; i<todo.size(); i++){
            out+= todo.get(i).show();
        }

        textViewDebug.setText(out);
    }

    public void fillDatabase(){
        //1
        p = new Pergunta();
        p.titulo = "Qual dessas versões do Android foi lançada por ultimo?";
        p.r1 = "Marshmallow";
        p.r2 = "Lolipop";
        p.r3 = "Kitkat";
        p.r4 = "Donut";
        p.difculdade = 4;
        p.save();

        //2
        p = new Pergunta();
        p.titulo = "Quantas ligações faz o carbono?";
        p.r1 = "4";
        p.r2 = "2";
        p.r3 = "3";
        p.r4 = "Nenhuma";
        p.difculdade = 4;
        p.save();
        //3
        p = new Pergunta();
        p.titulo = "Qual o continente que tem mais países?";
        p.r1 = "África";
        p.r2 = "Europa";
        p.r3 = "Ásia";
        p.r4 = "América";
        p.difculdade = 8;
        p.save();
        //4
        p = new Pergunta();
        p.titulo = "Qual é a maior cidade da Turquia?";
        p.r1 = "Istambul";
        p.r2 = "Ancara";
        p.r3 = "Bagdá";
        p.r4 = "Teerã";
        p.difculdade = 8;
        p.save();
        //5
        p = new Pergunta();
        p.titulo = "Quantas estrelas tem a bandeira da China?";
        p.r1 = "Cinco";
        p.r2 = "Duas";
        p.r3 = "Três";
        p.r4 = "Uma";
        p.difculdade = 8;
        p.save();
        //6
        p = new Pergunta();
        p.titulo = "Qual a montanha mais elevada da África?";
        p.r1 = "Kilimanjaro";
        p.r2 = "Monte Branco";
        p.r3 = "Aconcágua";
        p.r4 = "Everest";
        p.difculdade = 7;
        p.save();
    }

    public void buttonClickResponder(View v){




    }

}
