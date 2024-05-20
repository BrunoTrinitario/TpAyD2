package persistencia;

import java.util.ArrayList;

public class ListaInfoClientesXML {
	private ArrayList<ClienteRegistradoXML> lista=new ArrayList<ClienteRegistradoXML>();

	public ListaInfoClientesXML() {
		
	}
	public ArrayList<ClienteRegistradoXML> getLista() {
		return lista;
	}

	public void setLista(ArrayList<ClienteRegistradoXML> lista) {
		this.lista = lista;
	}
	public void addLista(ClienteRegistradoXML c) {
		this.lista.add(c);
	}
	
}
