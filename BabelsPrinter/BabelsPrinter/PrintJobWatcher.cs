using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using MySQLDriverCS;
using System.Threading;

namespace BabelsPrinter
{
    class PrintJobWatcher
    {
        private Printer Printer;
        MySQLConnection Conn;

        public PrintJobWatcher(Printer printer)
        {
            Printer = printer;
        }

        public void StartWatching(object obj){
            try
            {
                
                while (true)
                {
                    Conn = null;
                    Conn =  Printer.GetDBConn();
                    try
                    {
                        PrintJob job = GetPendingPrint();
                        if (job != null)
                        {
                            ProcessJob(job);
                        }
                        else
                        {
                            Thread.Sleep(1000);
                        }
                    }
                    finally
                    {
                        Conn.Close();
                    }
                }
            }
            catch (Exception ex)
            {

            }
        }

        private PrintJob GetPendingPrint()
        {
            PrintJob result = null;
            string sql = "SELECT * FROM " + PrintJob.TABLENAME + 
                " WHERE " + PrintJob.FIELD_STATUS + "= '" + PrintJob.ST_PEND + "'" + 
                " AND " + PrintJob.FIELD_PRINTER + " = NULL";
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

        private void ProcessJob(PrintJob job)
        {

        }
    }
}
