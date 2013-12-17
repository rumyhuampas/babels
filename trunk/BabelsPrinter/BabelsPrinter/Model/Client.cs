using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using MySQLDriverCS;
using BabelsPrinter.Properties;

namespace BabelsPrinter.Model
{
    public class Client
    {
        public static string TABLENAME = "Clients";
        public static string FIELD_ID = "Id";
        public static string FIELD_NAME = "Name";
        public static string FIELD_DOCNUM = "DocNum";
        public static string FIELD_DOCTYPE = "DocType";
        public static string FIELD_RESP = "Resp";
        public static string FIELD_ADDRESS = "Address";
        public static string FIELD_PHONE1 = "Phone1";
        public static string FIELD_PHONE2 = "Phone2";

        private MySQLConnection Conn;
        private int _Id;
        private string _Name;
        private string _DocNum;
        private Field _DocType;
        private Field _Resp;
        private string _Address;
        private string _Phone1;
        private string _Phone2;

        public int Id { get { return _Id; } set { _Id = value; } }
        public string Name { get { return _Name; } set { _Name = value; } }
        public string DocNum { get { return _DocNum; } set { _DocNum = value; } }
        public Field DocType { get { return _DocType; } set { _DocType = value; } }
        public Field Resp { get { return _Resp; } set { _Resp = value; } }
        public string Address { get { return _Address; } set { _Address = value; } }
        public string Phone1 { get { return _Phone1; } set { _Phone1 = value; } }
        public string Phone2 { get { return _Phone2; } set { _Phone2 = value; } }

        public Client(MySQLConnection conn)
        {
            Conn = conn;
        }

        public void Load(int id)
        {
            string sql = "SELECT * FROM " + TABLENAME +
                " WHERE " + FIELD_ID + "= " + id.ToString();
            MySQLCommand comm = new MySQLCommand(sql, Conn);
            try
            {
                MySQLDataReader reader = comm.ExecuteReaderEx();
                if (reader.HasRows)
                {
                    reader.Read();
                    this.Id = reader.GetInt32(reader.GetOrdinal(FIELD_ID));
                    this.Name = reader.GetString(reader.GetOrdinal(FIELD_NAME));
                    this.DocNum = reader.GetString(reader.GetOrdinal(FIELD_DOCNUM));
                    this.DocType = TiposDeDocumentoCliente.FromValue(reader.GetString(reader.GetOrdinal(FIELD_DOCTYPE)));
                    this.Resp = TiposDeResponsabilidadesCliente.FromValue(reader.GetString(reader.GetOrdinal(FIELD_RESP)));
                    this.Address = "";
                    if (!reader.IsDBNull(reader.GetOrdinal(FIELD_ADDRESS)))
                    {
                        this.Address = reader.GetString(reader.GetOrdinal(FIELD_ADDRESS));
                    }
                    this.Phone1 = "";
                    if (!reader.IsDBNull(reader.GetOrdinal(FIELD_PHONE1)))
                    {
                        this.Phone1 = reader.GetString(reader.GetOrdinal(FIELD_PHONE1));
                    }
                    this.Phone2 = "";
                    if (!reader.IsDBNull(reader.GetOrdinal(FIELD_PHONE2)))
                    {
                        this.Phone2 = reader.GetString(reader.GetOrdinal(FIELD_PHONE2));
                    }
                }
            }
            catch (Exception ex) { }
            finally
            {
                comm.Dispose();
            }
        }

        public static Client GetDefaultClient()
        {
            Client def = new Client(null);
            def.Name = Settings.Default.DefaultClientName;
            def.DocNum = Settings.Default.DefaultClientDocNum;
            def.DocType = TiposDeDocumentoCliente.FromValue(Settings.Default.DefaultClientDocType);
            def.Resp = TiposDeResponsabilidadesCliente.FromValue(Settings.Default.DefaultClientResp);
            def.Address = Settings.Default.DefaultClientAddress;
            def.Phone1 = "";
            def.Phone2 = "";
            return def;
        }
    }
}
