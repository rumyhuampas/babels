using System;
using System.Threading;
using BabelsPrinter.Properties;
using MySQLDriverCS;

namespace BabelsPrinter
{
    public class PrinterService
    {
        private static string SERVER;
        private static string DB;
        private static string USER;
        private static string PASS;

        public PrintJobWatcher watcher;

        public PrinterService()
        {
            SERVER = Settings.Default.Server;
            DB = Settings.Default.DB;
            USER = Settings.Default.User;
            PASS = Settings.Default.Pass;
        }

        public void Start()
        {
            try
            {
                Logger.Log(Logger.MT_INFO, "Starting service...", Settings.Default.LogLevel >= 1);

                ResetMyJobs();
                watcher = new PrintJobWatcher();
                ThreadPool.QueueUserWorkItem(new WaitCallback(watcher.StartWatching), null);
            }
            catch (Exception ex)
            {
                Logger.Log(Logger.MT_ERROR, "Shutting down service. Error: " + ex.Message, Settings.Default.LogLevel >= 3);
            }
        }

        public static MySQLConnection GetDBConn()
        {
            MySQLConnection conn = new MySQLConnection(new MySQLConnectionString(SERVER, DB, USER, PASS).AsString);
            conn.Open();
            return conn;
        }

        private void ResetMyJobs()
        {
            Logger.Log(Logger.MT_INFO, "Reseting jobs for printers: " + Settings.Default.Printers, Settings.Default.LogLevel >= 4);

            string sql = "UPDATE " + PrintJob.TABLENAME + " SET " +
                PrintJob.FIELD_STATUS + " = '" + PrintJob.ST_PEND + "'" +
                " WHERE " + PrintJob.FIELD_STATUS + " <> '" + PrintJob.ST_PEND + "'" +
                " AND " + PrintJob.FIELD_STATUS + " <> '" + PrintJob.ST_COMP + "'" +
                " AND " + PrintJob.FIELD_STATUS + " <> '" + PrintJob.ST_FAIL + "'" +
                " AND " + PrintJob.FIELD_PRINTER + " IN (" + Printers.GetPrinterList() + ")";
            MySQLCommand comm = new MySQLCommand(sql, GetDBConn());
            try
            {
                comm.ExecuteNonQuery();
            }
            finally
            {
                comm.Dispose();
            }
        }
    }
}
