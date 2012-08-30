using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using MySQLDriverCS;

namespace BabelsPrinter.Model
{
    public class Cancelation
    {
        public static string TABLENAME = "Cancelations";
        public static string FIELD_ID = "Id";
        public static string FIELD_CANCELLERMOVEID = "CancellerMoveId";
        public static string FIELD_CANCELEDMOVEID = "CanceledMoveId";
        public static string FIELD_TICKETNUMBER = "TicketNumber";

        private MySQLConnection Conn;
        private int _Id;
        private int _CancellerId;
        private int _CanceledId;
        private string _TicketNumber;

        public int Id { get { return _Id; } set { _Id = value; } }
        public int CancellerId { get { return _CancellerId; } set { _CancellerId = value; } }
        public int CanceledId { get { return _CanceledId; } set { _CanceledId = value; } }
        public string TicketNumber { get { return _TicketNumber; } set { _TicketNumber = value; } }

        public Cancelation(MySQLConnection conn)
        {
            Conn = conn;
        }

        public void Load(int cancellerId)
        {
            string sql = "SELECT * FROM " + TABLENAME +
                " WHERE " + FIELD_CANCELLERMOVEID + "= " + cancellerId.ToString();
            MySQLCommand comm = new MySQLCommand(sql, Conn);
            try
            {
                MySQLDataReader reader = comm.ExecuteReaderEx();
                if (reader.HasRows)
                {
                    reader.Read();
                    this.Id = reader.GetInt32(reader.GetOrdinal(FIELD_ID));
                    this.CancellerId = cancellerId;
                    this.CanceledId = reader.GetInt32(reader.GetOrdinal(FIELD_CANCELEDMOVEID));
                    this.TicketNumber = reader.GetString(reader.GetOrdinal(FIELD_TICKETNUMBER));
                }
            }
            catch (Exception ex) { }
            finally
            {
                comm.Dispose();
            }
        }
    }
}
