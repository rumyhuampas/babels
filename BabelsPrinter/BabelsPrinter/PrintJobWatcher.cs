using System;
using System.Threading;
using MySQLDriverCS;

namespace BabelsPrinter
{
    public class PrintJobWatcher
    {
        private PrintJobResolver JobResolver;
        MySQLConnection Conn;

        public PrintJobWatcher()
        {
            Conn = Printer.GetDBConn();
            JobResolver = new PrintJobResolver();
        }

        public void StartWatching(object obj){
            try
            {
                while (true)
                {
                    PrintJob job = GetPendingPrintJob();
                    if (job != null)
                    {
                        JobResolver.ProcessJob(job);
                    }
                    else
                    {
                        Thread.Sleep(1000);
                    }
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

        private PrintJob GetPendingPrintJob()
        {
            PrintJob result = null;
            string sql = "SELECT * FROM " + PrintJob.TABLENAME + 
                " WHERE " + PrintJob.FIELD_STATUS + "= '" + PrintJob.ST_PEND + "'" + 
                " AND " + PrintJob.FIELD_PRINTER + " IS NULL";
            MySQLCommand comm = new MySQLCommand(sql, Conn);
            try
            {
                MySQLDataReader reader = comm.ExecuteReaderEx();
                int jobID = -1;
                while (reader.Read())
                {
                    jobID = reader.GetInt32(reader.GetOrdinal(PrintJob.FIELD_ID));
                    if (ReserveJob(jobID) == true)
                    {
                        result = new PrintJob();
                        result.Id = jobID;
                        result.IdSale = reader.GetInt32(reader.GetOrdinal(PrintJob.FIELD_IDSALE));
                        result.DatePosted = reader.GetDateTime(reader.GetOrdinal(PrintJob.FIELD_DATEPOSTED));
                        result.Status = reader.GetString(reader.GetOrdinal(PrintJob.FIELD_STATUS));
                        result.Printer = reader.GetString(reader.GetOrdinal(PrintJob.FIELD_PRINTER));
                        break;
                    }
                }
                return result;
            }
            finally
            {
                comm.Dispose();
            }
        }

        private bool ReserveJob(int JobID)
        {
            string sql = "UPDATE " + PrintJob.TABLENAME + " SET " + 
                PrintJob.FIELD_STATUS + " = '" + PrintJob.ST_PRIN + "'," +
                PrintJob.FIELD_PRINTER + " = '" + Printer.Name + "'" +
                " WHERE " + PrintJob.FIELD_ID + " = " + JobID;
            MySQLCommand comm = new MySQLCommand(sql, Conn);
            try
            {
                return comm.ExecuteNonQuery() > 0;
            }
            finally
            {
                comm.Dispose();
            }
        }
    }
}
