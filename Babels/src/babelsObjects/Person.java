package babelsObjects;

import java.sql.Connection;
import java.sql.SQLException;

public class Person {

    protected final String FIELD_ID = "Id";
    protected final String FIELD_DNI = "Dni";
    protected final String FIELD_NAME = "Name";
    protected final String FIELD_LASTNAME = "LastName";
    protected final String FIELD_PHONE = "Phone";
    protected final String FIELD_PHONE2 = "Phone2";
    protected final String FIELD_ADDRESS = "Address";
    protected final String FIELD_IDSTATE = "IdState";
    protected final String FIELD_IDCITY = "IdCity";
    protected final String FIELD_EMAIL = "Email";
    protected Connection Conn;
    protected Integer Id;
    public String Dni;
    public String Name;
    public String LastName;
    public String Phone;
    public String Phone2;
    public String Address;
    public State State;
    public City City;
    public String Email;
    
    public Integer getId(){
        return this.Id;
    }

    public Person(Connection conn) throws SQLException {
        this.Conn = conn;
        Clear();
    }

    protected void Clear() {
        this.Id = -1;
        this.Dni = "";
        this.Name = "";
        this.LastName = "";
        this.Phone = "";
        this.Phone2 = "";
        this.Address = "";
        this.State = null;
        this.City = null;
        this.Email = "";
    }
}
