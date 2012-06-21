using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using MySQLDriverCS;
using BabelsPrinter.Properties;

namespace BabelsPrinter
{
    public static class Logger
    {
        private static string TABLENAME = "BabelsPrinterLog";
        private static string FIELD_ID = "Id";
        private static string FIELD_SERVICENAME = "ServiceName";
        private static string FIELD_MESSAGETYPE = "MessageType";
        private static string FIELD_MESSAGE = "Message";
        private static string FIELD_DATEPOSTED = "DatePosted";

        public static string MT_ERROR = "ERROR";
        public static string MT_INFO = "INFO";
        public static string MT_WARNING = "WARNING";
        public static string MT_QUERY = "QUERY";

        public static void Log(string MessageType, string Message, bool post)
        {
            if (post == true)
            {
                MySQLConnection Conn = null;
                try
                {
                    Conn = PrinterService.GetDBConn();
                    string sql = "INSERT INTO " + TABLENAME +
                        " (" + FIELD_SERVICENAME + "," + FIELD_MESSAGETYPE + "," + FIELD_MESSAGE + "," + FIELD_DATEPOSTED + ")" +
                        " VALUES ('" + Settings.Default.ServiceName + "','" + MessageType + "','" + Message + "','" + DateTime.Now.ToString("yyyy-MM-dd hh:mm:ss") + "')";
                    MySQLCommand comm = new MySQLCommand(sql, Conn);
                    try
                    {
                        comm.ExecuteNonQuery();
                    }
                    catch
                    {
                    }
                    finally
                    {
                        comm.Dispose();
                        Conn.Close();
                    }
                }
                catch
                {
                }
            }
        }
    }
}
