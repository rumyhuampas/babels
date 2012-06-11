using System;
using System.Threading;
using BabelsPrinter.Properties;
using MySQLDriverCS;

namespace BabelsPrinter
{
    public class Printer
    {
        private ConfigReader ConfReader;
        private static string _Name;
        private static string SERVER;
        private static string BD;
        private static string USER;
        private static string PASS;

        public static string Name { get { return _Name; } }

        public Printer()
        {
            ConfReader = new ConfigReader(Settings.Default.IniPath);
            SERVER = ConfReader.IniReadValue("BD", "SERVER");
            BD = ConfReader.IniReadValue("BD", "BD");
            USER = ConfReader.IniReadValue("BD", "USER");
            PASS = ConfReader.IniReadValue("BD", "PASS");

            _Name = Settings.Default.ServiceName;
        }

        public void Start()
        {
            try
            {
                ResetMyJobs();
                PrintJobWatcher watcher = new PrintJobWatcher();
                ThreadPool.QueueUserWorkItem(new WaitCallback(watcher.StartWatching), null);
            }
            catch (Exception ex)
            {
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
            string sql = "UPDATE " + PrintJob.TABLENAME + " SET " + 
                PrintJob.FIELD_STATUS + " = '" + PrintJob.ST_PEND + "'," +
                PrintJob.FIELD_PRINTER + " = NULL" +
                " WHERE " + PrintJob.FIELD_STATUS + " <> '" + PrintJob.ST_PEND + "'" +
                " AND " + PrintJob.FIELD_STATUS + " <> '" + PrintJob.ST_COMP + "'" +
                " AND " + PrintJob.FIELD_PRINTER + " = '" + Name + "'";
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
