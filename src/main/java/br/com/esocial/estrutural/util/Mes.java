package br.com.esocial.estrutural.util;

import java.util.Calendar;

public enum Mes {


JANEIRO("01","Janeiro","Jan"){
	
	public int getCalendarMonth(){
		return Calendar.getInstance().get(Calendar.JANUARY);
	}
	
},
FEVEREIRO("02","Fevereiro","Fev"){
	
	public int getCalendarMonth(){
		return Calendar.getInstance().get(Calendar.FEBRUARY);
	}
},
MARCO("03","Mar�o","Mar"){
	
	public int getCalendarMonth(){
		return Calendar.getInstance().get(Calendar.MARCH);
	}
	
},
ABRIL("04","Abril","Abr"){
	
	public int getCalendarMonth(){
		return Calendar.getInstance().get(Calendar.APRIL);
	}
	
},
MAIO("05","Maio","Mai"){
	
	public int getCalendarMonth(){
		return Calendar.getInstance().get(Calendar.MAY);
	}
	
},
JUNHO("06","Junho","Jun"){
	
	public int getCalendarMonth(){
		return Calendar.getInstance().get(Calendar.JUNE);
	}
	
},
JULHO("07","Julho","Jul"){
	
	public int getCalendarMonth(){
		return Calendar.getInstance().get(Calendar.JULY);
	}
	
},
AGOSTO("08","Agosto","Ago"){
	
	public int getCalendarMonth(){
		return Calendar.getInstance().get(Calendar.AUGUST);
	}
	
},
SETEMBRO("09","Setembro","Set"){
	
	public int getCalendarMonth(){
		return Calendar.getInstance().get(Calendar.SEPTEMBER);
	}
},
OUTUBRO("10","Outubro","Out"){
	public int getCalendarMonth(){
		return Calendar.getInstance().get(Calendar.OCTOBER);
	}
	
},
NOVEMBRO("11","Novembro","Nov"){
	
	public int getCalendarMonth(){
		return Calendar.getInstance().get(Calendar.NOVEMBER);
	}
	
},
DEZEMBRO("12","Dezembro","Dez"){
	
	public int getCalendarMonth(){
		return Calendar.getInstance().get(Calendar.DECEMBER);
	}
	
};

String numeroMes;
String nomeCompleto;
String nomeTresLetras;


private Mes(String numeroMes,String nomeCompleto,String nomeTresLetras) {
    this.numeroMes = numeroMes;
    this.nomeCompleto = nomeCompleto;
    this.nomeTresLetras = nomeTresLetras;
}
public String getNumeroMes(){
    return numeroMes;
}

public String getNomeCompleto() {
    return nomeCompleto;
}

public String getNomeTresLetras() {
    return nomeTresLetras;
}


public int getCalendarMonth(){
	throw new  UnsupportedOperationException();
}
public static Mes obterMesPor(int numeroMes){
	Mes mesSelecionado = null;
	if(numeroMes == 0 || numeroMes > 12){
		return mesSelecionado;
	}
	switch(numeroMes){
		case 1: mesSelecionado = Mes.JANEIRO;break;
		case 2: mesSelecionado = Mes.FEVEREIRO;break;
		case 3: mesSelecionado = Mes.MARCO;break;
		case 4:	mesSelecionado = Mes.ABRIL;break;
		case 5:	mesSelecionado = Mes.MAIO;break;
		case 6:	mesSelecionado = Mes.JUNHO;break;
		case 7:	mesSelecionado = Mes.JULHO;break;
		case 8:	mesSelecionado = Mes.AGOSTO;break;
		case 9:	mesSelecionado = Mes.SETEMBRO;break;
		case 10:mesSelecionado = Mes.OUTUBRO;break;
		case 11:mesSelecionado = Mes.NOVEMBRO;break;
		case 12:mesSelecionado = Mes.DEZEMBRO;break;
	}
	return mesSelecionado;
}

//TASK 3373 - RENAN
    public static Mes obterMesPor(String numero){
    	int numeroConvertido = Integer.parseInt(numero);
    	return obterMesPor(numeroConvertido);
    }
    
public static Mes obterMesPorNome(String nomeMes){
    Mes mesSelecionado = null;
    if(nomeMes == null || nomeMes.isEmpty()){
	return mesSelecionado;
    }
    
    String nomeMesBusca = nomeMes.toLowerCase();
    for(Mes mes : Mes.values()){
       if(mes.getNomeCompleto().toLowerCase().equals(nomeMesBusca)){
	   mesSelecionado = mes;
       }
    }
    return mesSelecionado;
}
}
