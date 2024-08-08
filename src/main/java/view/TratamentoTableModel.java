package view;

import java.util.List;
import model.Cliente;
import model.ClienteDAO;
import model.Animal;
import model.AnimalDAO;
import model.Veterinario;
import model.VeterinarioDAO;
import model.Tratamento;
import model.TratamentoDAO;

/**
 *
 * @author Natalia Emboava
 */
public class TratamentoTableModel extends GenericTableModel{
    
     public TratamentoTableModel(List<Tratamento> vDados) {
        super(vDados, new String[]{"Nome", "Data de Início", "Data de Término", "Status"});
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {

        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Tratamento tratamento = (Tratamento) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return tratamento.getNome();
            case 1:
                return tratamento.getDataInicial();
            case 2:
                return tratamento.getDataFinal();
            case 3:
                return tratamento.isTerminou() == true ? "Finalizado" : "Em andamento";
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");

        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Tratamento tratamento = (Tratamento) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                tratamento.setNome((String) aValue);
                break;
            case 1:
                tratamento.setDataInicial((String) aValue);
                break;
            case 2:
                tratamento.setDataFinal((String) aValue);
                break;
            case 3:
                tratamento.setTerminou((Boolean) aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");

        }

        TratamentoDAO.getInstance().update(tratamento);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex != 3;
    }
}
