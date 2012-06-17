
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
            //hasar715.ImprimirReporteZ();
            //hasar715.ImprimirReporteZ(Hasar715.ConvertToSbyte("120505"), Hasar715.ConvertToSbyte("500505"), true);
            //hasar715.ImprimirReporteZ(50, 60, true);
            hasar715.ImprimirTextoFiscal(Hasar715.ConvertToSbyte("test"));
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
