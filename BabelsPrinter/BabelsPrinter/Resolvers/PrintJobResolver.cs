
using MySQLDriverCS;
using System;
using System.Text;
using BabelsPrinter.Properties;
using System.Threading;
using BabelsPrinter.Interfaces;
using BabelsPrinter.Resolvers;

namespace BabelsPrinter.Resolvers
{
    public class PrintJobResolver: IJobResolver
    {
        private MySQLConnection Conn;
        private HasarJobResolver HasarResolver;
        private KitchenJobResolver KitchenResolver;
        private XJobResolver XResolver;
        private ClientJobResolver ClientResolver;

        public PrintJobResolver()
        {
            HasarResolver = new HasarJobResolver();
            KitchenResolver = new KitchenJobResolver();
            XResolver = new XJobResolver();
            ClientResolver = new ClientJobResolver();
        }

        public void ProcessJob(PrintJob job)
        {
            Logger.Log(Logger.MT_INFO, "Processing job: " + job.Id.ToString(), Settings.Default.LogLevel >= 4);
            try
            {
                if (job.Printer == Printers.PRINTER_FISCAL || job.Printer == Printers.PRINTER_NOFISCAL)
                {
                    HasarResolver.ProcessJob(job);
                }
                else
                {
                    if (job.Printer.Contains(Printers.PRINTER_COCINA))
                    {
                        KitchenResolver.ProcessJob(job);
                    }
                    else
                    {
                        if (job.Printer == Printers.PRINTER_X)
                        {
                            XResolver.ProcessJob(job);
                        }
                        else
                        {
                            if (job.Printer == Printers.PRINTER_CLIENTE)
                            {
                                ClientResolver.ProcessJob(job);
                            }
                        }
                    }
                }
                CompleteJob(job, false);
            }
            catch (Exception ex)
            {
                Logger.Log(Logger.MT_ERROR, "Error while processing job. Error: " + ex.Message, Settings.Default.LogLevel >= 3);
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
    }
}
