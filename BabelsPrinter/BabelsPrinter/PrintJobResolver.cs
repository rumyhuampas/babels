
using MySQLDriverCS;
using System;
using System.Text;
using BabelsPrinter.Properties;
using System.Threading;
namespace BabelsPrinter
{
    public class PrintJobResolver
    {
        private MySQLConnection Conn;
        private Hasar715CLR.Hasar715CLR hasar715;

        public unsafe PrintJobResolver()
        {
            hasar715 = new Hasar715CLR.Hasar715CLR(Hasar715.ToSbyte("C:\\Hasar715.txt"));
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
            /*hasar715.TratarDeCancelarTodo();
            hasar715.AbrirDF(Hasar715.ToSbyte(DocumentosFiscales.TICKET_FACTURA_B.value));
            hasar715.ImprimirItem(Hasar715.ToSbyte("PROD1"), 1, 14.56, 21, 5, false);
            hasar715.ImprimirItem(Hasar715.ToSbyte("PROD2"), 2, 5.00, 21, 5, false);
            hasar715.ImprimirItem(Hasar715.ToSbyte("PROD3"), 1, 14.56, 21, 5, false);
            hasar715.ImprimirItem(Hasar715.ToSbyte("PROD4"), 2, 5.00, 21, 5, false);
            hasar715.ImprimirItem(Hasar715.ToSbyte("PROD5"), 1, 14.56, 21, 5, false);
            hasar715.ImprimirItem(Hasar715.ToSbyte("PROD6"), 2, 5.00, 21, 5, false);
            hasar715.CerrarDF();*/
            //hasar715.CambiarResponsabilidadIVA();
        }

        public void ProcessJob(PrintJob job)
        {
            Logger.Log(Logger.MT_INFO, "Processing job: " + job.Id.ToString(), Settings.Default.LogLevel >= 4);
            try
            {
                switch (job.Move.Type.Name)
                {
                    case Movement.MT_VENTAA:
                        Print_A_Ticket(job);
                        break;
                    case Movement.MT_VENTAB:
                        Print_B_Ticket(job);
                        break;
                    case Movement.MT_CANCELACION:
                        break;
                    case Movement.MT_APERTURA:
                        break;
                    case Movement.MT_CIERRE:
                        break;
                    case Movement.MT_CIERREPARCIAL:
                        break;
                    case Movement.MT_DEPOSITO:
                        break;
                    case Movement.MT_EXTRACCION:
                        break;
                }
                CompleteJob(job, false);
            }
            catch (Exception ex)
            {
                Logger.Log(Logger.MT_ERROR, "Error while processing job.", Settings.Default.LogLevel >= 3);
                if (job.Retries >= Settings.Default.MaxJobRetries)
                {
                    try
                    {
                        CompleteJob(job, true);
                    }
                    catch { }
                }
                else
                {
                    IncrementJobRetries(job);
                }
            }
        }

        private void CompleteJob(PrintJob job, bool fail)
        {
            Conn = PrinterService.GetDBConn();
            try
            {
                string sql;
                if (!fail)
                {
                    sql = "UPDATE " + PrintJob.TABLENAME + " SET " +
                        PrintJob.FIELD_STATUS + " = '" + PrintJob.ST_COMP + "'," +
                        PrintJob.FIELD_DATEPRINTED + " = '" + DateTime.Now.ToString("yyyy-MM-dd hh:mm:ss") + "'" +
                        " WHERE " + PrintJob.FIELD_ID + " = " + job.Id;
                }
                else
                {
                    sql = "UPDATE " + PrintJob.TABLENAME + " SET " +
                        PrintJob.FIELD_STATUS + " = '" + PrintJob.ST_FAIL + "'," +
                        PrintJob.FIELD_DATEPRINTED + " = '" + DateTime.Now.ToString("yyyy-MM-dd hh:mm:ss") + "'" +
                        " WHERE " + PrintJob.FIELD_ID + " = " + job.Id;
                }
                MySQLCommand comm = new MySQLCommand(sql, Conn);
                try
                {
                    comm.ExecuteNonQuery();
                }
                catch (Exception ex)
                {
                    throw ex;
                }
                finally
                {
                    comm.Dispose();
                }

                Logger.Log(Logger.MT_INFO, "Job successfully printed: " + job.Id.ToString(), Settings.Default.LogLevel >= 4);
            }
            catch (Exception ex)
            {
                throw ex;
            }
            finally
            {
                Conn.Close();
            }
        }

        private void IncrementJobRetries(PrintJob job)
        {
            job.Retries++; 
            Logger.Log(Logger.MT_INFO, "Incrementing retries. Job: " + job.Id.ToString() + ". Retries: " + job.Retries.ToString(), Settings.Default.LogLevel >= 4);

            Conn = PrinterService.GetDBConn();
            try
            {
                string sql = "UPDATE " + PrintJob.TABLENAME + " SET " +
                        PrintJob.FIELD_STATUS + " = '" + PrintJob.ST_PEND + "'," +
                        PrintJob.FIELD_DATEPRINTED + " = NULL," +
                        PrintJob.FIELD_RETRIES + " = " + job.Retries.ToString() +
                        " WHERE " + PrintJob.FIELD_ID + " = " + job.Id;
                MySQLCommand comm = new MySQLCommand(sql, Conn);
                try
                {
                    comm.ExecuteNonQuery();
                }
                catch (Exception ex)
                {
                    throw ex;
                }
                finally
                {
                    comm.Dispose();
                }
            }
            catch (Exception ex)
            {
                throw ex;
            }
            finally
            {
                Conn.Close();
            }
        }

        private unsafe void Print_A_Ticket(PrintJob job)
        {
            try
            {
                hasar715.TratarDeCancelarTodo();
                hasar715.IngresarDatosCliente(
                    Hasar715.ToSbyte(job.Move.MoveClient.Name), 
                    Hasar715.ToSbyte(job.Move.MoveClient.DocNum), 
                    Hasar715.ToSbyte(job.Move.MoveClient.DocType.value),
                    Hasar715.ToSbyte(job.Move.MoveClient.Resp.value), 
                    Hasar715.ToSbyte(job.Move.MoveClient.Address));
                hasar715.AbrirDF(Hasar715.ToSbyte(DocumentosFiscales.TICKET_FACTURA_A.value));
                foreach (SaleItem item in job.Move.Items.items)
                {
                    hasar715.ImprimirItem(Hasar715.ToSbyte(item.Name), item.Amount, item.Price, item.IVA, 0, false);
                }
                hasar715.CerrarDF();
            }
            catch (Exception ex)
            {
                throw ex;
            }
            finally
            {

            }
        }

        private unsafe void Print_B_Ticket(PrintJob job)
        {
            try
            {
                hasar715.TratarDeCancelarTodo();
                hasar715.AbrirDF(Hasar715.ToSbyte(DocumentosFiscales.TICKET_FACTURA_B.value));
                foreach (SaleItem item in job.Move.Items.items)
                {
                    hasar715.ImprimirItem(Hasar715.ToSbyte(item.Name), item.Amount, item.Price, item.IVA, 0, false);
                }
                hasar715.CerrarDF();
            }
            catch (Exception ex)
            {
                throw ex;
            }
            finally
            {

            }
        }
    }
}
