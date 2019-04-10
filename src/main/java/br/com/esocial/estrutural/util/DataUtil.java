package br.com.esocial.estrutural.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DataUtil {

	public static String converterCalendarDDMMYYYY(Calendar calendar) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String calendarFormatado = df.format(calendar.getTime());
		return calendarFormatado;
	}

	public static String converterDataYYYYMM(String YYYYMM) {
		return "01/" + YYYYMM.substring(5, 7) + "/" + YYYYMM.substring(0, 4);
	}

	public static boolean isDataDDMMYYYYValida(String ddMMyyyy) {
		SimpleDateFormat sdf_dd_MM_yyyy = new SimpleDateFormat("dd/MM/yyyy");
		sdf_dd_MM_yyyy.setLenient(false);
		if (ddMMyyyy == null || ddMMyyyy.trim().isEmpty() || ddMMyyyy.trim().length() != 10) {
			return false;
		}

		try {
			sdf_dd_MM_yyyy.parse(ddMMyyyy);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

//	public static String convertTimestampToYYYYMM(String DDMMYYYYHHMMSS){
//		SimpleDateFormat MMyyyy = new SimpleDateFormat("yyyy-MM");
//		String retorno = MMyyyy.format(DDMMYYYYHHMMSS);
//		return retorno;
//	}

	/**
	 * Recebe data no formato YYYY-MM.
	 */
	public static boolean isDataYYYYMMValida(String YYYYMM) {
		if (YYYYMM == null || YYYYMM.isEmpty() || YYYYMM.length() != 7) {
			return false;
		}
		String dataFormatada = "01/" + YYYYMM.substring(5, 7) + "/" + YYYYMM.substring(0, 4);
		return isDataDDMMYYYYValida(dataFormatada);
	}

	/**
	 * Método para comparar duas datas no formato dd/MM/yyyy e retornar um booleano.
	 * Exemplo método data menor : [ "10/10/2015" , "20/10/2016" ] -> retorna true.
	 * [ "01/01/2015" , "01/01/2015" ] -> retorna true. [ "30/06/2015" ,
	 * "01/01/2015" ] -> retorna false. Autor : Renan Watanabe
	 */
	public static boolean isPrimeiraDataMenorOuIgualSegundaData(String primeiraDataDDMMYYYY, String segundaDataDDMMYYYY)
			throws ParseException {
		return isPrimeiraDataMenorOuIgualSegundaData(obterCalendarSemHorario(primeiraDataDDMMYYYY),
				obterCalendarSemHorario(segundaDataDDMMYYYY));
	}

	public static boolean isPrimeiraDataMenorOuIgualSegundaData(Calendar primeiraData, Calendar segundaData)
			throws ParseException {

		boolean isPrimeiraDataMenorOuIgualSegundaData = false;

		int valor = primeiraData.compareTo(segundaData);
		if (valor == 0) {// Significa que as datas são iguais.
			isPrimeiraDataMenorOuIgualSegundaData = true;
		} else if (valor == -1) {// Significa que a primeiraData é menor que a segundaData.
			isPrimeiraDataMenorOuIgualSegundaData = true;
		} else if (valor == 1) {// Significa que a primeiraData é maior que a segundaData.
			isPrimeiraDataMenorOuIgualSegundaData = false;
		}
		return isPrimeiraDataMenorOuIgualSegundaData;
	}

	/**
	 * Método para comparar duas datas no formato dd/MM/yyyy e retornar um booleano.
	 * Exemplo método data maior : [ "10/10/2015" , "20/10/2016" ] -> retorna false.
	 * [ "01/01/2015" , "01/01/2015" ] -> retorna true. [ "30/06/2015" ,
	 * "01/01/2015" ] -> retorna true. Autor : Renan Watanabe
	 */

	public static boolean isPrimeiraDataMaiorOuIgualSegundaData(String primeiraDataDDMMYYYY, String segundaDataDDMMYYYY)
			throws ParseException {
		return isPrimeiraDataMaiorOuIgualSegundaData(obterCalendarSemHorario(primeiraDataDDMMYYYY),
				obterCalendarSemHorario(segundaDataDDMMYYYY));

	}

	public static boolean isPrimeiraDataMaiorOuIgualSegundaData(Calendar primeiraData, Calendar segundaData)
			throws ParseException {
		boolean isPrimeiraDataMaiorOuIgualSegundaData = false;
		int valor = primeiraData.compareTo(segundaData);
		if (valor == 0) {// Significa que as datas são iguais.
			isPrimeiraDataMaiorOuIgualSegundaData = true;
		} else if (valor == -1) {// Significa que a primeiraData é menor que a segundaData.
			isPrimeiraDataMaiorOuIgualSegundaData = false;
		} else if (valor == 1) {// Significa que a primeiraData é maior que a segundaData.
			isPrimeiraDataMaiorOuIgualSegundaData = true;
		}
		return isPrimeiraDataMaiorOuIgualSegundaData;
	}

	public static boolean isPrimeiraDataMaiorQueSegundaData(String primeiraDataDDMMYYYY, String segundaDataDDMMYYYY)
			throws ParseException {
		return isPrimeiraDataMaiorQueSegundaData(obterCalendarSemHorario(primeiraDataDDMMYYYY),
				obterCalendarSemHorario(segundaDataDDMMYYYY));
	}

	public static boolean isPrimeiraDataMaiorQueSegundaData(Calendar primeiraData, Calendar segundaData)
			throws ParseException {

		boolean isPrimeiraDataMaiorQueSegundaData = false;
		int valor = primeiraData.compareTo(segundaData);
		if (valor == 0) {// Significa que as datas são iguais.
			isPrimeiraDataMaiorQueSegundaData = false;
		} else if (valor == -1) {// Significa que a primeiraData é menor que a segundaData.
			isPrimeiraDataMaiorQueSegundaData = false;
		} else if (valor == 1) {// Significa que a primeiraData é maior que a segundaData.
			isPrimeiraDataMaiorQueSegundaData = true;
		}
		return isPrimeiraDataMaiorQueSegundaData;
	}

	public static boolean isPrimeiraDataMenorQueSegundaData(String primeiraDataDDMMYYYY, String segundaDataDDMMYYYY)
			throws ParseException {
		return isPrimeiraDataMenorQueSegundaData(obterCalendarSemHorario(primeiraDataDDMMYYYY),
				obterCalendarSemHorario(segundaDataDDMMYYYY));
	}

	public static boolean isPrimeiraDataMenorQueSegundaData(Calendar primeiraData, Calendar segundaData)
			throws ParseException {

		boolean isPrimeiraDataMenorQueSegundaData = false;
		int valor = primeiraData.compareTo(segundaData);

		if (valor == 0) {// Significa que as datas são iguais.
			isPrimeiraDataMenorQueSegundaData = false;
		} else if (valor == -1) {// Significa que a primeiraData é menor que a segundaData.
			isPrimeiraDataMenorQueSegundaData = true;
		} else if (valor == 1) {// Significa que a primeiraData é maior que a segundaData.
			isPrimeiraDataMenorQueSegundaData = false;
		}
		return isPrimeiraDataMenorQueSegundaData;
	}

	/**
	 * Este método retorna uma data do Tipo Calendar do dia de hoje. A diferença é
	 * que esse calendar não possui as horas, minutos, segundos e milissegundos.
	 */
	public static Calendar obterCalendarSemHorario(String dataDDYYMMMM) throws ParseException {
		SimpleDateFormat sdf_dd_MM_yyyy = new SimpleDateFormat("dd/MM/yyyy");
		sdf_dd_MM_yyyy.setLenient(false);
		if (dataDDYYMMMM.isEmpty()) {
			return null;
		}
		GregorianCalendar calendarSemHorario = new GregorianCalendar();
		calendarSemHorario.setTime(sdf_dd_MM_yyyy.parse(dataDDYYMMMM));
		return calendarSemHorario;
	}

	/**
	 * Este método retorna uma data do Tipo Calendar do dia de hoje.
	 */
	public static Calendar obterCalendarComHorario(String dataDDYYMMMMHHMMSS) throws ParseException {
		SimpleDateFormat sdf_dd_MM_yyyy_hh_mm_ss = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		sdf_dd_MM_yyyy_hh_mm_ss.setLenient(false);
		if (dataDDYYMMMMHHMMSS.isEmpty()) {
			return null;
		}
		GregorianCalendar calendarSemHorario = new GregorianCalendar();
		calendarSemHorario.setTime(sdf_dd_MM_yyyy_hh_mm_ss.parse(dataDDYYMMMMHHMMSS));
		return calendarSemHorario;
	}

	/**
     * Este método retorna uma data do Tipo Calendar do dia de hoje.
     * A diferença é que esse calendar não possui as horas, minutos, segundos e milissegundos.
     */
    public static Calendar obterCalendarAnoMes(String dataYYYYMM) throws ParseException{
    	if(!dataYYYYMM.isEmpty()){
			SimpleDateFormat sdf_YYYY_MM = new SimpleDateFormat("YYYY-MM");
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf_YYYY_MM.parse(dataYYYYMM));
			return cal;
    	} 
    	return null;
    }
    
    public static String obterStringAnoMes(Calendar cal) throws ParseException{
    	if(cal != null){
    		Calendar c = Calendar.getInstance();
    		SimpleDateFormat s = new SimpleDateFormat("YYYY-MM");
    		String YYYYMM = s.format(c.getTime());
			return YYYYMM;
    	} 
    	return "";
    }
    
    public static boolean isPrimeiraDataSobrepostaSegundaData(Calendar data1Ini,Calendar data1Fim, Calendar data2Ini, Calendar data2Fim) {
    	
    	int data1IniInt = Integer.parseInt("" + data1Ini.get(Calendar.YEAR) + data1Ini.get(Calendar.MONTH) + "01");
    	int data2IniInt = Integer.parseInt("" + data2Ini.get(Calendar.YEAR) + data2Ini.get(Calendar.MONTH) + "01");
    	int data1FimInt = Integer.parseInt("" + data1Fim.get(Calendar.YEAR) + data1Fim.get(Calendar.MONTH) + "28");
    	int data2FimInt = Integer.parseInt("" + data2Fim.get(Calendar.YEAR) + data2Fim.get(Calendar.MONTH) + "28");

    	if((data1IniInt <= data2FimInt) 
    			&& (data1FimInt <= data2IniInt) 
    			&& (data1IniInt <= data2IniInt) 
    			&& (data1FimInt <= data2FimInt)) {
    		return true;
    	}
    	return false;
    }
    

}
