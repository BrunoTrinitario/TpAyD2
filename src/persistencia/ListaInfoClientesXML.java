package persistencia;

import java.util.ArrayList;

public class ListaInfoClientesXML {
	@Override
	public String toString() {
		return "ListaInfoClientesXML [lista=" + lista + "]";
	}
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
