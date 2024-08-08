 package controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import model.*;
import view.AnimalTableModel;
import view.ClienteTableModel;
import view.ConsultaTableModel;
import view.Consultas;
import view.EspecieTableModel;
import view.ExameTableModel;
import view.GenericTableModel;
import view.TratamentoTableModel;
import view.VeterinarioTableModel;
import view.options;


/**
 *
 * @author 
 */
public class Controller {
    
    public final static int CLIENTE = 0;
    public final static int ANIMAL = 1;
    public final static int ESPECIE = 2;
    public final static int TRATAMENTO = 3;
    public final static int EXAME = 4;
    public final static int CONSULTA = 5;
    public final static int VETERINARIO = 6;

    /*private static List<Cliente> clientes = new ArrayList<>();
    private static List<Animal> animal = new ArrayList<>();
    private static List<Especie> especie = new ArrayList<>();
    private static List<Tratamento> tratamento = new ArrayList<>();
    private static List<Veterinario> veterinario = new ArrayList<>();*/
    
   
    private static Cliente selectedCliente = null;
    private static Animal selectedAnimal = null;
    private static String selectedEspecie = null;
    private static Tratamento selectedTratamento = null;
    private static Exame selectedExame = null;
    private static Consulta selectedConsulta = null;
    private static Veterinario selectedVeterinario = null;

   
    
    private static JTextField selectedClienteTextField = null;
    private static JTextField selectedAnimalTextField = null;
    private static JTextField selectedTratamentoTextField = null;
    private static JTextField selectedVeterinarioTextField = null;
    private static JTextField selectedEspecieTextField = null;
    
    public static Cliente getSelectedCliente() {
        return selectedCliente;
    }

    public static Animal getSelectedAnimal() {
        return selectedAnimal;
    }

    public static Tratamento getSelectedTratamento() {
        return selectedTratamento;
    }

    public static Exame getSelectedExame() {
        return selectedExame;
    }

    public static Consulta getSelectedConsulta() {
        return selectedConsulta;
    }

    public static Veterinario getSelectedVeterinario() {
        return selectedVeterinario;
    }
    
    public static String getSelectedEspecie() {
        return selectedEspecie;
    }
    
     public static void setTextFieldCadastro(JTextField cliente, JTextField animal) {
        selectedClienteTextField = cliente;
        selectedAnimalTextField = animal;
    }
    
    public static void setTextFieldConsultaValues(){
        selectedClienteTextField.setText(selectedCliente.getNome());
        selectedAnimalTextField.setText(selectedAnimal.getNome());
    }
     
    public static void setTextFieldConsultas(JTextField cliente, JTextField animal) {
        selectedClienteTextField = cliente;
        selectedAnimalTextField = animal;
        //selectedVeterinarioTextField = veterinario;
       // selectedTratamentoTextField = tratamento;
    }
    
    public static void setTableModel(JTable table, GenericTableModel tableModel) {
        table.setModel(tableModel);
        table.repaint(); 
    }
   
    public static void jRadioButtonClienteSelecionado(JTable table) {
       setTableModel(table, new ClienteTableModel(ClienteDAO.getInstance().retrieveAll()));
   }
   
    public static boolean jRadioButtonAnimaisSelecionados(JTable table) {
        Cliente selectedCliente = getSelectedCliente();

        if (selectedCliente != null) {
            // pega a lista de animais do cliente
            List<Animal> animais = AnimalDAO.getInstance().retrieveByClienteId(selectedCliente.getId());

            // atualiza o modelo da tabela com a nova lista de animais
            AnimalTableModel animalTableModel = new AnimalTableModel(animais);
            setTableModel(table, animalTableModel);
        } else {
            // se nenhum cliente estiver selecionado, atualiza o modelo com uma lista vazia
            setTableModel(table, new AnimalTableModel(new ArrayList<>()));
        }
        return false;
    }
 
   public static void setTextFieldValues() {
       if (selectedCliente != null) {
            selectedClienteTextField.setText(selectedCliente.getNome());
            selectedAnimalTextField.setText(selectedAnimal != null ? selectedAnimal.getNome() : "");
           selectedEspecieTextField.setText(selectedEspecie);
       }
   }
   
   public static void jRadioButtonEspecieSelecionado(JTable table) {
       setTableModel(table, new EspecieTableModel(EspecieDAO.getInstance().retrieveAll()));
   }
   
   public static void jRadioButtonVeterinarioSelecionado(JTable table) {
       setTableModel(table, new VeterinarioTableModel(VeterinarioDAO.getInstance().retrieveAll()));
   }
   
   public static void jRadioButtonConsultaSelecionada(JTable table) {
       setTableModel(table, new ConsultaTableModel(ConsultaDAO.getInstance().retrieveAll()));
   }
   
   public static void jRadioButtonExameSelecionado(JTable table) {
       setTableModel(table, new ExameTableModel(ExameDAO.getInstance().retrieveAll()));
   }
   
   public static void jRadioButtonTratamentoSelecionado(JTable table) {
       setTableModel(table, new TratamentoTableModel(TratamentoDAO.getInstance().retrieveAll()));
   }
    
    public static void atualizarClientesNomesParecidos(JTable table, String nome) {
        List<Cliente> clientesAtualizados = nome.isBlank() ?
                ClienteDAO.getInstance().retrieveAll() :
                ClienteDAO.getInstance().retrieveBySimilarName(nome);

        ((GenericTableModel) table.getModel()).addListOfItems(clientesAtualizados);
    }
     
     public static void atualizarVeterinariosNomesParecidos(JTable table, String nome) {
        List<Veterinario> veterinariosAtualizados = nome.isBlank() ?
                VeterinarioDAO.getInstance().retrieveAll() :
                VeterinarioDAO.getInstance().retrieveBySimilarName(nome);

        ((GenericTableModel) table.getModel()).addListOfItems(veterinariosAtualizados);
    }
     
     public static void atualizarNomesParecidos(JTable table, String nomeParecido) {
        if(table.getModel() instanceof ClienteTableModel) {
            List<Cliente> clientesAtualizados = nomeParecido.isBlank() ?
                ClienteDAO.getInstance().retrieveAll() :
                ClienteDAO.getInstance().retrieveBySimilarName(nomeParecido);

        ((GenericTableModel) table.getModel()).addListOfItems(clientesAtualizados);
        } else if (table.getModel() instanceof VeterinarioTableModel) {
                List<Veterinario> veterinariosAtualizados = nomeParecido.isBlank() ?
                VeterinarioDAO.getInstance().retrieveAll() :
                VeterinarioDAO.getInstance().retrieveBySimilarName(nomeParecido);

                ((GenericTableModel) table.getModel()).addListOfItems(veterinariosAtualizados);
        } else if(table.getModel() instanceof AnimalTableModel) {
            if(getSelectedCliente() != null ) {
                List<Animal> animalAtualizados = nomeParecido.isBlank() ?
                AnimalDAO.getInstance().retrieveAll() :
                AnimalDAO.getInstance().retrieveBySimilarName(selectedCliente.getId(), nomeParecido);
        }
      }
    }
     
    public static void atualizaNomesParecidosConsulta(JTable table, String nomeParecido) {
        if(table.getModel() instanceof ConsultaTableModel) {
                List<Consulta> consultasAtualizadas = nomeParecido.isBlank() ?
                ConsultaDAO.getInstance().retrieveAll() :
                ConsultaDAO.getInstance().retrieveBySimilarName(nomeParecido);

                ((GenericTableModel) table.getModel()).addListOfItems(consultasAtualizadas);
        } else if (table.getModel() instanceof TratamentoTableModel) {
                List<Tratamento> tratamentossAtualizados = nomeParecido.isBlank() ?
                TratamentoDAO.getInstance().retrieveAll() :
                TratamentoDAO.getInstance().retrieveBySimilarName(nomeParecido);

                ((GenericTableModel) table.getModel()).addListOfItems(tratamentossAtualizados);
        } else if(table.getModel() instanceof ExameTableModel) {
                    if(getSelectedConsulta() != null ) {
                        List<Exame> examesAtualizados = nomeParecido.isBlank() ?
                        ExameDAO.getInstance().retrieveAll() :
                        ExameDAO.getInstance().retrieveBySimilarName(nomeParecido);
                        
                        ((GenericTableModel) table.getModel()).addListOfItems(examesAtualizados);
                    }
        } else if (table.getModel() instanceof VeterinarioTableModel) {
                List<Veterinario> veterinariosAtualizados = nomeParecido.isBlank() ?
                VeterinarioDAO.getInstance().retrieveAll() :
                VeterinarioDAO.getInstance().retrieveBySimilarName(nomeParecido);

                ((GenericTableModel) table.getModel()).addListOfItems(veterinariosAtualizados);
        }
   }
    
    
     
    public static void atualizarAnimaisNomeParecidos(JTable table, String nome) {
        /*List<Animal> animaisAtualizados = nome.isBlank() || selectedCliente == null ?
                new ArrayList<>() :
                AnimalDAO.getInstance().retrieveBySimilarName(nome, selectedCliente.getId());

        ((GenericTableModel) table.getModel()).addListOfItems(animaisAtualizados);*/
    }
    
     public static Cliente criarCliente(String nome, String endereco, String cep, String email, String telefone) {
        return ClienteDAO.getInstance().create(nome, endereco, cep, email, telefone);
    }

    public static void deletarCliente(JTable table, int selctedRow) {
        ClienteDAO.getInstance().delete(selectedCliente);
        ((GenericTableModel) table.getModel()).removeItem(table.getSelectedRow());
    }

    public static Animal criarAnimal(String nome, String idade, String sexo, int idEspecie, int idCliente) {
        if(selectedCliente != null) {
            return  AnimalDAO.getInstance().create(nome, idade, sexo,  idEspecie, idCliente);
            
        } else {
            return null;
        }
    } 

    public static void deletarAnimal(JTable table, int selectedRow) {
        AnimalDAO.getInstance().delete(selectedAnimal);
        ((GenericTableModel) table.getModel()).removeItem(table.getSelectedRow());
    }

    public static void criarTratamento(String tratamento) {
         TratamentoDAO.getInstance().create(selectedAnimal.getId(), tratamento, new SimpleDateFormat("dd/MM/yyyy").format(new Date()), "", false);
    }
    
    public static void deletarTratamento(JTable table, int selectedRow) {
        TratamentoDAO.getInstance().delete(selectedTratamento);
        ((GenericTableModel) table.getModel()).removeItem(table.getSelectedRow());
    }
        

    
    public static Exame criarExame(String nome) {
        // verifica se selectedConsulta não é nulo
        if (selectedConsulta != null) {
            // Configura o parâmetro na consulta SQL
            return ExameDAO.getInstance().create(nome, selectedConsulta.getIdConsulta());
        } else {
            // trata o caso em que selectedConsulta é nulo
            System.err.println("Error: selectedConsulta is null.");
        }
    return null;
}

     
    public static void deletarExame(JTable table, int selectedRow) {
      ExameDAO.getInstance().delete(selectedExame);
      ((GenericTableModel) table.getModel()).removeItem(table.getSelectedRow());
     }
    
    public static Especie criarEspecie(String nome) {
        return EspecieDAO.getInstance().create(nome);
    }
  
    public static Veterinario criarVeterinario(String nome, String telefone, String email) {
        return VeterinarioDAO.getInstance().create(nome, email, telefone);
    }

    public static void deletarVeterinario(JTable table, int selectedRow) {
        VeterinarioDAO.getInstance().delete(selectedVeterinario);
        ((GenericTableModel) table.getModel()).removeItem(table.getSelectedRow());
    }
    
    public static void criarConsulta(Calendar data, String horario, String comentario) {
        ConsultaDAO.getInstance().create(data, horario, comentario, selectedAnimal.getId(),
                selectedVeterinario.getId(), selectedTratamento.getIdTratamento(),
                0);

    }

    public static void deletarConsulta(JTable table) {
        ConsultaDAO.getInstance().delete(selectedConsulta);
        ((GenericTableModel) table.getModel()).removeItem(table.getSelectedRow());
    }
   
   public static void setSelected(Object selected) {
        if (selected instanceof Cliente) {
            selectedCliente = (Cliente) selected;
            if (selectedClienteTextField != null) {
                selectedClienteTextField.setText(selectedCliente.getNome());
            }
            if (selectedAnimalTextField != null) {
                selectedAnimalTextField.setText("");
            }
            if (selectedEspecieTextField != null) {
                selectedEspecieTextField.setText("");
            }
    } else if (selected instanceof Animal) {
        selectedAnimal = (Animal) selected;
        } else if (selected instanceof Animal) {
            selectedAnimal = (Animal) selected;

            if (selectedAnimal.getIdEspecie() != 0) {
                selectedEspecie  = EspecieDAO.getInstance().retrieveById(selectedAnimal.getIdEspecie()).getNome();
            } else {
                selectedEspecie = "";
            }
            selectedAnimalTextField.setText(selectedAnimal.getNome());
            selectedEspecieTextField.setText(selectedEspecie);
        } else if (selected instanceof Tratamento) {
            selectedTratamento = (Tratamento) selected;
        } else if (selected instanceof Consulta) {
            selectedConsulta = (Consulta) selected;
        } else if (selected instanceof Exame) {
            selectedExame = (Exame) selected;
        } else if (selected instanceof Veterinario) {
            selectedVeterinario = (Veterinario) selected;
        }
    }
   
    public static List getTodosClientes() {
       return ClienteDAO.getInstance().retrieveAll();
    }
  
    public static void atualizaBotaoTodos(JTable table, JTextField textField) {
        if(table.getModel() instanceof ClienteTableModel) {
            ((GenericTableModel)table.getModel()).addListOfItems(Controller.getTodosClientes());
        } else if (table.getModel() instanceof VeterinarioTableModel) {
            ((GenericTableModel)table.getModel()).addListOfItems(VeterinarioDAO.getInstance().retrieveAll());
        } else if(table.getModel() instanceof AnimalTableModel) {
            if(getSelectedCliente() != null) {
                ((GenericTableModel)table.getModel()).addListOfItems(AnimalDAO.getInstance().retrieveByClienteId(getSelectedCliente().getId()));
            }
        } else if (table.getModel() instanceof EspecieTableModel) {
            ((GenericTableModel)table.getModel()).addListOfItems(VeterinarioDAO.getInstance().retrieveAll());
        } 
          textField.setText("");  
    }
    
     public static void clearSelection(int option) {
        switch (option) {
            case TRATAMENTO:
                selectedTratamento = null;
                break;
            case VETERINARIO:
                selectedVeterinario = null;
                break;
            case CONSULTA:
                selectedConsulta = null;
                break;
            case ANIMAL:
                selectedAnimal = null;
                selectedEspecieTextField.setText("");
                selectedAnimalTextField.setText("");
                break;
            case CLIENTE:
                selectedCliente = null;
                selectedClienteTextField.setText("");
                break;
            case EXAME:
                selectedExame = null;
                break;
            default:
                break;
        }
    }
     
    public static void botaoApaga(JTable table) {
        if(table.getSelectedRow() >= 0) {
                Object entidade = ((GenericTableModel) table.getModel()).getItem(table.getSelectedRow());
                ((GenericTableModel) table.getModel()).removeItem(table.getSelectedRow());
            if (entidade instanceof Veterinario) {
                VeterinarioDAO.getInstance().delete((Veterinario) entidade);
            } else if (entidade instanceof Animal) {
                AnimalDAO.getInstance().delete((Animal) entidade);
            } else if (entidade instanceof Cliente) {
                ClienteDAO.getInstance().delete((Cliente) entidade);
            } else if (entidade instanceof Especie) {
                EspecieDAO.getInstance().delete((Especie) entidade);
            } else if (entidade instanceof Consulta) {
                ConsultaDAO.getInstance().delete((Consulta) entidade);
            } else if(entidade instanceof Exame) {
                ExameDAO.getInstance().delete((Exame) entidade);
            } else if(entidade instanceof Tratamento) {
                TratamentoDAO.getInstance().delete((Tratamento) entidade);
            }
        } 
    }
    
    public static void selectTextFieldObject(JTable table, JTextField jTextField1, JTextField jTextField2) {
        Object selectedObject = ((GenericTableModel)table.getModel()).getItem(table.getSelectedRow());
        
        if (selectedObject instanceof Cliente) {
            setSelected((Cliente) selectedObject);
            jTextField1.setText(getSelectedCliente().getNome());
        } else if (selectedObject instanceof Animal) {
            setSelected((Animal) selectedObject);
            jTextField2.setText(getSelectedAnimal().getNome());
        }
    }

    public static void selectTextFieldObjectC(JTable table, JTextField jTextField1, JTextField jTextField3, JTextField jTextField5, JTextField jTextField6) {
        Object selectedObject = ((GenericTableModel)table.getModel()).getItem(table.getSelectedRow());
        
        if (selectedObject instanceof Cliente) {
            setSelected((Cliente) selectedObject);
            jTextField1.setText(getSelectedCliente().getNome());
        } else if (selectedObject instanceof Animal) {
            setSelected((Animal) selectedObject);
            jTextField3.setText(getSelectedAnimal().getNome());
        } else if (selectedObject instanceof Veterinario) {
            setSelected((Veterinario) selectedObject);
            jTextField5.setText(getSelectedVeterinario().getNome());
        } else if (selectedObject instanceof Tratamento) {
            setSelected((Tratamento) selectedObject);
            jTextField6.setText(getSelectedTratamento().getNome());
        }
    }
  
    public static boolean atualizaBotaoNovo(JTable table) {
        if (table.getModel() instanceof ClienteTableModel) {
            ((GenericTableModel) table.getModel()).addItem(Controller.criarCliente("", "", "", "", ""));
        } else if (table.getModel() instanceof AnimalTableModel) {
            if (Controller.getSelectedCliente() != null) {
                String selectedEspecie = Controller.getSelectedEspecie();
                int idCliente = Controller.getSelectedCliente().getId();
                
                //System.out.println("idCliente = " + idCliente);
                // Convertendo a String para int (assumindo que a String é o ID da espécie)
                int idEspecie = 0; 
                if (selectedEspecie != null && !selectedEspecie.isEmpty()) {
                    idEspecie = Integer.parseInt(selectedEspecie);
                }
                //((GenericTableModel) table.getModel()).addItem(Controller.criarEspecie(""));

                ((GenericTableModel) table.getModel()).addItem(Controller.criarAnimal("", "", "", idEspecie, idCliente));
                ((GenericTableModel) table.getModel()).fireTableDataChanged();
            }
        } else if (table.getModel() instanceof EspecieTableModel) {
            ((GenericTableModel) table.getModel()).addItem(Controller.criarEspecie(""));
            ((GenericTableModel) table.getModel()).fireTableDataChanged();
        } else if (table.getModel() instanceof VeterinarioTableModel) {
            ((GenericTableModel) table.getModel()).addItem(Controller.criarVeterinario("", "", ""));
        } 
        return false;
    }
    
    public static boolean atualizaBotaoAgendar(JTable table, JFormattedTextField jFormattedTextField, JList<String> jList, JTextArea jTextArea) {
        if (table.getModel() instanceof TratamentoTableModel) {
            Controller.criarTratamento(""); 
            ((GenericTableModel) table.getModel()).fireTableDataChanged();
        } else if (table.getModel() instanceof ConsultaTableModel) {
            Calendar dt = Calendar.getInstance();
            SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yy");

            try {
            String dateString = jFormattedTextField.getText().trim();
                if (!dateString.isEmpty() && !dateString.equals("//")) {
                    dt.setTime(dataFormat.parse(dateString));
                    String comentario = jTextArea.getText();
                    Controller.criarConsulta(dt, jList.getSelectedValue(), comentario);
                    JOptionPane.showMessageDialog(null, "Consulta agendada!");
                    ((GenericTableModel) table.getModel()).fireTableDataChanged();
                } else {
                    JOptionPane.showMessageDialog(null, "Data inválida.");
                }
            } catch (ParseException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            }   
        } else if (table.getModel() instanceof ExameTableModel) {
            if (Controller.getSelectedConsulta() != null) {
                Exame exame = Controller.criarExame("");
                ((ExameTableModel) table.getModel()).addItem(exame);
                ((GenericTableModel) table.getModel()).fireTableDataChanged();
            }
        }
        return false;
    }
    
    public static void atualizarFiltrosAppointmentButtons(JTable table, options.ConsultasOptions opcao) {
        switch (opcao) {
            case todos:
                ((GenericTableModel) table.getModel()).addListOfItems(ConsultaDAO.getInstance().retrieveAllByTratamentoId(selectedTratamento.getIdTratamento()));
                break;
            case marcada:
                ((GenericTableModel) table.getModel()).addListOfItems(ConsultaDAO.getInstance().retrieveByStatus(selectedAnimal.getId(), options.ConsultasOptions.marcada));
                break;
            case realizada:
                ((GenericTableModel) table.getModel()).addListOfItems(ConsultaDAO.getInstance().retrieveByStatus(selectedAnimal.getId(), options.ConsultasOptions.realizada));
                break;
        }
    }
    
    public static List getConsultasAnimal() {
        SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yy");
        List lista = AnimalDAO.getInstance().getUltimasConsulta(selectedAnimal.getId());
        List listaString = new ArrayList();

        for (Object obj : lista) {
            listaString.add("Data: " + dataFormat.format(((Consulta) obj).getData().getTime())
                    + " | Horário:" + ((Consulta) obj).getHorario()
                    + " | Veterinário: " + (VeterinarioDAO.getInstance().retrieveById(((Consulta) obj).getIdVeterinario()).getNome())
            );
        }
        return listaString;
    }

    public static void fimTratamento() {
        if (!selectedTratamento.isTerminou()) {
            TratamentoDAO.getInstance().fimTratamento(selectedTratamento);
        } else {
            JOptionPane.showMessageDialog(null, "Tratamento já finalizado.");
        }
    }
    
    public static List getHorariosConsultas() {
        return new ArrayList<>(Arrays.asList(
                "08:00",
                "09:00",
                "10:00",
                "11:00",
                "14:00",
                "15:00",
                "16:00",
                "17:00",
                "18:00"));
    }

    public static void atualizarToggleButtons(JTable table, options.TratamentoOptions opcao) {
        switch (opcao) {
            case todos:
                ((GenericTableModel) table.getModel()).addListOfItems(TratamentoDAO.getInstance().retrieveAllByAnimalId(selectedAnimal.getId()));
                break;
            case andamento:
                ((GenericTableModel) table.getModel()).addListOfItems(TratamentoDAO.getInstance().retrieveByStatus(selectedAnimal.getId(), options.TratamentoOptions.andamento));
                break;
            default:
                ((GenericTableModel) table.getModel()).addListOfItems(TratamentoDAO.getInstance().retrieveByStatus(selectedAnimal.getId(), options.TratamentoOptions.finalizado));
                break;
        }
    }
 
}
   
