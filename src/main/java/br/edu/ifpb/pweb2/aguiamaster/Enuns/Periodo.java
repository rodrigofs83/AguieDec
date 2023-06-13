package br.edu.ifpb.pweb2.aguiamaster.Enuns;

public enum Periodo {
    PRIMEIRO("1"),
    SEGUNDO("2");

    private  final String periodo;
    private Periodo(String periodo){
        this.periodo =  periodo;
    }
    public String getValor(){
        return this.periodo;
    }
}
