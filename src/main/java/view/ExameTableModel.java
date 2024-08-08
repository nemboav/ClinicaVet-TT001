package view;

import java.util.List;
import model.Exame;
import model.ExameDAO;

public class ExameTableModel extends GenericTableModel {

    public ExameTableModel(List vDados) {
        super(vDados, new String[]{"Descricao"});
    }


    @Override
    public Class<?> getColumnClass(int columnIndex) {
        
        switch (columnIndex) {
            case 0:
                return String.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       /*Exame exame = (Exame) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return exame.getDescricao();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");

        }*/
        if (rowIndex >= 0 && rowIndex < vDados.size()) {
            Exame exame = (Exame) vDados.get(rowIndex);

            if (exame != null) {
                switch (columnIndex) {
                    case 0:
                        return exame.getDescricao();
                    default:
                        throw new IndexOutOfBoundsException("columnIndex out of bounds");
                }
            } else {
                System.err.println("Error: exame is null at rowIndex " + rowIndex);
                return null;  // ou retorne um valor padrão, dependendo do seu caso
            }
        } else {
            throw new IndexOutOfBoundsException("rowIndex out of bounds");
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Exame exame = (Exame) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                exame.setDescricao((String) aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }

        ExameDAO.getInstance().update(exame);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
           return true;
    }
}
