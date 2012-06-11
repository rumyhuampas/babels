
using MySQLDriverCS;
using System;
namespace BabelsPrinter
{
    public class PrintJobResolver
    {
        private MySQLConnection Conn;

        public PrintJobResolver()
        {
            
        }

        public void ProcessJob(PrintJob job)
        {
            Conn = Printer.GetDBConn();
            try
            {
                string sql = "UPDATE " + PrintJob.TABLENAME + " SET " +
                    PrintJob.FIELD_STATUS + " = '" + PrintJob.ST_COMP + "'," +
                    PrintJob.FIELD_DATEPRINTED + " = '" + DateTime.Now.ToString("yyyy-MM-dd hh:mm:ss") + "'" +
                    " WHERE " + PrintJob.FIELD_ID + " = " + job.Id;
                MySQLCommand comm = new MySQLCommand(sql, Conn);
                try
                {
                    comm.ExecuteNonQuery();
                }
                finally
                {
                    comm.Dispose();
                }
            }
            catch (Exception ex)
            {

            }
            finally
            {
                Conn.Close();
            }
        }
    }
}
