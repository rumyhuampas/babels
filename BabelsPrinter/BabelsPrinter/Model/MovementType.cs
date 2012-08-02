using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using MySQLDriverCS;

namespace BabelsPrinter
{
    public class MovementType
    {
        public static string TABLENAME = "Movement_Types";
        public static string FIELD_ID = "Id";
        public static string FIELD_NAME = "Name";

        private MySQLConnection Conn;
        private int _Id;
        private string _Name;

        public int Id { get { return _Id; } set { _Id = value; } }
        public string Name { get { return _Name; } set { _Name = value; } }

        public MovementType(MySQLConnection conn)
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
                if(reader.HasRows)
                {
                    reader.Read();
                    this.Id = reader.GetInt32(reader.GetOrdinal(FIELD_ID));
                    this.Name = reader.GetString(reader.GetOrdinal(FIELD_NAME));
                }
            }
            finally
            {
                comm.Dispose();
            }
        }
    }
}
