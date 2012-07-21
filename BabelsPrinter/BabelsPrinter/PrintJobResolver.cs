﻿
using MySQLDriverCS;
using System;
using System.Text;
using BabelsPrinter.Properties;
namespace BabelsPrinter
{
    public class PrintJobResolver
    {
        private MySQLConnection Conn;

        public unsafe PrintJobResolver()
        {
            Hasar715CLR.Hasar715CLR hasar715 = new Hasar715CLR.Hasar715CLR(Hasar715.ToSbyte("C:\\Hasar715.txt"));
            hasar715.InitPrinter(Hasar715.ToSbyte("C:\\Hasar715.ini"));
            //hasar715.ImprimirReporteZ();
            //hasar715.ImprimirReporteZ(Hasar715.ConvertToSbyte("120505"), Hasar715.ConvertToSbyte("500505"), true);
            //hasar715.ImprimirReporteZ(50, 60, true);
            /*hasar715.AbrirDNFTicket();
            hasar715.ImprimirCodigoDeBarras(Hasar715.ConvertToSbyte("999555111123"), true, true);
            hasar715.ImprimirTextoNoFiscal(Hasar715.ConvertToSbyte("Probando impresora fiscal con texto no fiscal"));
            hasar715.CerrarDNF();*/
            //hasar715.ObtenerEstadoInterno();
            //hasar715.ObtenerDatosMemoriaDeTrabajo();
            //hasar715.ObtenerDatosDeInicializacion();
            //hasar715.ObtenerReporteStatusImpresor();
            //hasar715.ObtenerReporteStatusFiscal();
            //hasar715.CambiarResponsabilidadIVA();
            //hasar715.CambiarFechaInicioActividades(Hasar715.ConvertToSbyte("160712"));
            //hasar715.ObtenerUltimosDocumentos();
            //hasar715.EstablecerFechaHora(12, 5, 2012, 16, 14, 11);
            //hasar715.ObtenerConfiguracionCF();
            //hasar715.ImprimirReporteX();
            //hasar715.ImprimirReporteZ(Hasar715.ConvertToSbyte("120617"));
            //hasar715.ImprimirReporteZ(1);
            //hasar715.ObtenerCapacidadZ();
            //hasar715.ConfigurarParametro(nombredelparam, valor);
            //hasar715.ConfigurarControlador(Hasar715.ToSbyte(ParametroConfiguracion.IMPRESION_CAMBIO.value), Hasar715.ToSbyte("M"));
            //hasar715.ObtenerConfiguracion();
            //hasar715.ConfigurarControlador(true, false, 1000, 5000, 50, Hasar715.ToSbyte(NumerosDeCopias.DUPLICADO.value), true, true, 
            //    Hasar715.ToSbyte(TiposDeCorteDePapel.CORTE_PARCIAL.value), true, false, Hasar715.ToSbyte("Cuenta Corriente"), true, 
            //    Hasar715.ToSbyte(TiposDeAltoHoja.ALTO_REDUCIDO.value), Hasar715.ToSbyte(TiposDeAnchoHoja.ANCHO_REDUCIDO.value),
            //    Hasar715.ToSbyte(TiposDeEstacion.ESTACION_TICKET.value), Hasar715.ToSbyte(TiposDeModoImpresion.USO_ESTACION_TICKET.value));
            //hasar715.ObtenerConfiguracion();
            hasar715.AbrirDF(Hasar715.ToSbyte(DocumentosFiscales.TICKET_FACTURA_A.value));
            hasar715.CerrarDF();
            //hasar715.CambiarResponsabilidadIVA();
        }

        public void ProcessJob(PrintJob job)
        {
            Logger.Log(Logger.MT_INFO, "Processing job: " + job.Id.ToString(), Settings.Default.LogLevel >= 4);
            try
            {
                CompleteJob(job);
            }
            catch (Exception ex)
            {
                Logger.Log(Logger.MT_ERROR, "Error while processing job. Error: " + ex.Message, Settings.Default.LogLevel >= 3);
            }
        }

        private void CompleteJob(PrintJob job)
        {
            Conn = PrinterService.GetDBConn();
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

                Logger.Log(Logger.MT_INFO, "Job successfully printed: " + job.Id.ToString(), Settings.Default.LogLevel >= 4);
            }
            finally
            {
                Conn.Close();
            }
        }
    }
}
