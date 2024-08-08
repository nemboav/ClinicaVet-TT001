package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Animal;
import model.AnimalDAO;
import model.ClienteDAO;
import model.Consulta;
import model.ConsultaDAO;
import model.Veterinario;
import model.VeterinarioDAO;


/**
 *
 * @author nemboav
 */
public class ConsultaTableModel extends GenericTableModel {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

    public ConsultaTableModel(List vDados) {
        super(vDados, new String[]{"Data", "Horário", "Comentário", "Veterinário", "Status"});

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
            case 4:
                return String.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Consulta consulta = (Consulta) vDados.get(rowIndex);
        //Animal animal;

        switch (columnIndex) {
            case 0:
                return dateFormat.format(consulta.getData().getTime());
            case 1:
                return consulta.getHorario();
            case 2:
                return consulta.getComentario();
            case 3:
                return VeterinarioDAO.getInstance().retrieveById(consulta.getIdVeterinario()).getNome(); 
            case 4:
                return consulta.isTerminou() ? "Realizada" : "Marcada";

            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");

        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Consulta consulta = (Consulta) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                Calendar calendar = Calendar.getInstance();
                try {
                    calendar.setTime(dateFormat.parse((String) aValue));
                } catch (ParseException e) {
                    Logger.getLogger(ConsultaTableModel.class.getName()).log(Level.SEVERE, null, e);
                }
                
                Date date = calendar.getTime();
                consulta.setData(calendar);
                break;
            case 1:
                consulta.setHorario((String) aValue);
                break;
            case 2:
                consulta.setComentario((String) aValue);
                break;
            case 3:
                //consulta.setTerminou.(Boolean.parseBoolean((String) aValue));
                consulta.setTerminou((Boolean) aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");

        }

        ConsultaDAO.getInstance().update(consulta);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
           return true;
    }
}