using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using MySQLDriverCS;
using BabelsPrinter.Properties;
using System.Threading;

namespace BabelsPrinter
{
    class Printer
    {
        private MySQLConnection DBConn;
        private ConfigReader ConfReader;
        private string _Name;
        private string SERVER;
        private string BD;
        private string USER;
        private string PASS;

        public string Name { get { return _Name; } }

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
                PrintJobWatcher watcher = new PrintJobWatcher(this);
                ThreadPool.QueueUserWorkItem(new WaitCallback(watcher.StartWatching), null);
            }
            catch (Exception ex)
            {
            }
        }

        public MySQLConnection GetDBConn()
        {
            if (DBConn == null)
            {
                DBConn = new MySQLConnection(new MySQLConnectionString(SERVER, BD, USER, PASS).AsString);
            }
            DBConn.Open();
            return DBConn;
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
