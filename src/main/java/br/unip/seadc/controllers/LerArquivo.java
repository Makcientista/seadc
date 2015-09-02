package main.java.br.unip.seadc.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

public class LerArquivo {

	private List<String> fatoresList = new ArrayList<String>();
    private List<String> sintomasList = new ArrayList<String>();
    
    private Map<String, String> fatoresMap = new HashMap<String, String>();
    private Map<String, String> sintomasMap = new HashMap<String, String>();

	public LerArquivo(){
		String line;
	    String slices[];   
	    
	    final String filepath = "src/main/resources/files/config/fatores_e_sintomas.txt";
	    File f = new File(filepath).getAbsoluteFile();
	   
	    try{
		    BufferedReader br = new BufferedReader(new FileReader(f));
		     
		    while((line = br.readLine()) != null){
		        
		    	slices = line.split(";");
		        
				if(slices[0].equals("fator-de-risco")){
					fatoresList.add(slices[1]);
					fatoresMap.put(slices[1], slices[2]);
				}else if(slices[0].equals("sintoma")){
					sintomasList.add(slices[1]);
					sintomasMap.put(slices[1], slices[2]);
				}
				else{
					JOptionPane.showMessageDialog(null, "Não foi possível adicionar um ou muitos fatores de risco/sintomas");
				}	
		    }
		    
		    br.close();
	    }catch(IOException ioe){
	    	ioe.printStackTrace();
	    }
	}
	
	public List<String> getFatoresList() {
		return fatoresList;
	}

	public void setFatoresList(List<String> fatoresList) {
		this.fatoresList = fatoresList;
	}

	public List<String> getSintomasList() {
		return sintomasList;
	}

	public void setSintomasList(List<String> sintomasList) {
		this.sintomasList = sintomasList;
	}

	public Map<String, String> getFatoresMap() {
		return fatoresMap;
	}

	public void setFatoresMap(Map<String, String> fatoresMap) {
		this.fatoresMap = fatoresMap;
	}

	public Map<String, String> getSintomasMap() {
		return sintomasMap;
	}

	public void setSintomasMap(Map<String, String> sintomasMap) {
		this.sintomasMap = sintomasMap;
	}
	
}

