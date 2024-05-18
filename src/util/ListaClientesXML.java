package util;

import java.util.ArrayList;

public class ListaClientesXML {
	private ArrayList<ClienteAXML> lista=new ArrayList<ClienteAXML>();
	public ListaClientesXML() {
		
	}
	public ArrayList<ClienteAXML> getLista() {
		return lista;
	}
	public void setLista(ArrayList<ClienteAXML> lista) {
		this.lista = lista;
	}
	public void addLista(ClienteAXML cliente) {
		this.lista.add(cliente);
	}
	public String toString() {
		String aux="";
		for (ClienteAXML i:lista) {
			aux+=i+"\n";
		}
		return aux;
	}
}
