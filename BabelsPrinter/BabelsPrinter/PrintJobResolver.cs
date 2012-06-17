
using MySQLDriverCS;
using System;
using System.Text;
namespace BabelsPrinter
{
    public class PrintJobResolver
    {
        private MySQLConnection Conn;

        public unsafe PrintJobResolver()
        {
            Hasar715CLR.Hasar715CLR hasar715 = new Hasar715CLR.Hasar715CLR(Hasar715.ConvertToSbyte("C:\\Hasar715.txt"));
            hasar715.InitPrinter(Hasar715.ConvertToSbyte("C:\\Hasar715.ini"));
            hasar715.ImprimirReporteZ();

            /*IntPtr logger = Hasar715.InitLogger("C:\\HasarLog.txt");
            IntPtr obj = Hasar715.InitPrinter(out logger, "C:\\iniText.ini");*/
            //int num = Hasar715.GetNum();
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
