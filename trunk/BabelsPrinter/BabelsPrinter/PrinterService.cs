using System;
using System.Threading;
using BabelsPrinter.Properties;
using MySQLDriverCS;

namespace BabelsPrinter
{
    public class PrinterService
    {
        private ConfigReader ConfReader;
        private static string SERVER;
        private static string BD;
        private static string USER;
        private static string PASS;

        public PrinterService()
        {
            ConfReader = new ConfigReader(Settings.Default.IniPath);
            SERVER = ConfReader.IniReadValue("BD", "SERVER");
            BD = ConfReader.IniReadValue("BD", "BD");
            USER = ConfReader.IniReadValue("BD", "USER");
            PASS = ConfReader.IniReadValue("BD", "PASS");
        }

        public void Start()
        {
            try
            {
                Logger.Log(Logger.MT_INFO, "Starting service...", Settings.Default.LogLevel >= 1);

                ResetMyJobs();
                PrintJobWatcher watcher = new PrintJobWatcher();
                ThreadPool.QueueUserWorkItem(new WaitCallback(watcher.StartWatching), null);
            }
            catch (Exception ex)
            {
                Logger.Log(Logger.MT_ERROR, "Shutting down service. Error: " + ex.Message, Settings.Default.LogLevel >= 3);
            }
        }

        public static MySQLConnection GetDBConn()
        {
            MySQLConnection conn = new MySQLConnection(new MySQLConnectionString(SERVER, BD, USER, PASS).AsString);
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
