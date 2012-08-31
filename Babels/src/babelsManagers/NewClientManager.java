package babelsManagers;

import babels.Babels;
import babelsInterfaces.IBabelsDialog;
import babelsListeners.KeyListenerType;
import babelsListeners.txtFieldListener;
import babelsObjects.Client;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class NewClientManager {

    public boolean CheckFields(JTextField txtName, JTextField txtPhone1, JTextField txtPhone2, JTextField txtAdress, JTextField txtDocNum) {
        if (!txtName.getText().equals("") && !txtPhone1.getText().equals("") && !txtAdress.getText().equals("") && !txtDocNum.getText().equals("")) {
            //VALIDAR TODOS LOS CAMPOS!!!!!!!!!
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Debe completar Nombre o Razon Social, CUIT y Direccion o Domicilio Fiscal",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
     public void SetFieldsListeners(JTextField txtName, JTextField txtPhone1, JTextField txtPhone2, JTextField txtAdress, JTextField txtDocNum, IBabelsDialog dialog) {
        txtName.addKeyListener(new txtFieldListener(KeyListenerType.ANY, dialog));
        txtPhone1.addKeyListener(new txtFieldListener(KeyListenerType.ANY, dialog));
        txtPhone2.addKeyListener(new txtFieldListener(KeyListenerType.ANY, dialog));
        txtAdress.addKeyListener(new txtFieldListener(KeyListenerType.ANY, dialog));
        txtDocNum.addKeyListener(new txtFieldListener(KeyListenerType.ANY, dialog));
    }

    public void setComboTypeDoc(JComboBox comboTypeDoc) {
        comboTypeDoc.addItem("Cuit");
        comboTypeDoc.addItem("Cuil");
        comboTypeDoc.addItem("Dni");
        comboTypeDoc.addItem("Lc");
        comboTypeDoc.addItem("Le");
        comboTypeDoc.addItem("Pasaporte");
        comboTypeDoc.addItem("Ci");
        comboTypeDoc.addItem("Ninguno");

    }

    public void setComboTypeResp(JComboBox comboTypeResp) {
        comboTypeResp.addItem("Responsable Inscripto");
        comboTypeResp.addItem("Responsable No Inscripto");
        comboTypeResp.addItem("Responsable Exento");
        comboTypeResp.addItem("No Responsable");
        comboTypeResp.addItem("Consumidor Final");
        comboTypeResp.addItem("Bienes De Uso");
        comboTypeResp.addItem("No Categorizado");
        comboTypeResp.addItem("Monotributo");
        comboTypeResp.addItem("Monotributo Social");
        comboTypeResp.addItem("Eventual");
        comboTypeResp.addItem("Eventual Social");

    }

    public boolean SaveClient(int IdClient, String Name, String Phone1, String Phone2, String Adress,
            String DocNum, String TypeResp, String TypeDoc) throws SQLException {
        Babels.mysql.Open();
        try {
            Client client = new Client(Babels.mysql.Conn);
            if (IdClient != -1) {
                client.Load(IdClient);
            }
            client.Name = Name;
            client.Phone1 = Phone1;
            client.Phone2 = Phone2;
            client.Address = Adress;
            client.DocNum = DocNum;
            client.Resp = this.setTypeResp(TypeResp);
            client.DocType = this.setTypeDoc(TypeDoc);

            if (client.Exists() == false) {
                if (client.Save() == true) {
                    JOptionPane.showMessageDialog(null, "Cliente guardado",
                            "Exito", JOptionPane.INFORMATION_MESSAGE);
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo guardar el Cliente",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "El Cliente ya existe",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } finally {
            Babels.mysql.Close();
        }
    }

    private char setTypeDoc(String TypeDoc) {
        if (TypeDoc.equals("Cuit")) {
            return ("C").charAt(0);
        } else {
            if (TypeDoc.equals("Cuil")) {
                return ("L").charAt(0);
            } else {
                if (TypeDoc.equals("Dni")) {
                    return ("2").charAt(0);
                } else {
                    if (TypeDoc.equals("Lc")) {
                        return ("1").charAt(0);
                    } else {
                        if (TypeDoc.equals("Le")) {
                            return ("0").charAt(0);
                        } else {
                            if (TypeDoc.equals("Pasaporte")) {
                                return ("3").charAt(0);
                            } else {
                                if (TypeDoc.equals("Ci")) {
                                    return ("4").charAt(0);
                                } else {
                                    return (" ").charAt(0);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private char setTypeResp(String TypeResp) {
        if (TypeResp.equals("Responsable Inscripto")) {
            return ("I").charAt(0);
        } else {
            if (TypeResp.equals("Responsable No Inscripto")) {
                return ("N").charAt(0);
            } else {
                if (TypeResp.equals("Responsable Exento")) {
                    return ("E").charAt(0);
                } else {
                    if (TypeResp.equals("No Responsable")) {
                        return ("A").charAt(0);
                    } else {
                        if (TypeResp.equals("Consumidor Final")) {
                            return ("C").charAt(0);
                        } else {
                            if (TypeResp.equals("Bienes De Uso")) {
                                return ("B").charAt(0);
                            } else {
                                if (TypeResp.equals("No Categorizado")) {
                                    return ("T").charAt(0);
                                } else {
                                    if (TypeResp.equals("Monotributo")) {
                                        return ("M").charAt(0);
                                    } else {
                                        if (TypeResp.equals("Monotributo Social")) {
                                            return ("S").charAt(0);
                                        } else {
                                            if (TypeResp.equals("Eventual")) {
                                                return ("V").charAt(0);
                                            } else {
                                                return ("W").charAt(0);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
