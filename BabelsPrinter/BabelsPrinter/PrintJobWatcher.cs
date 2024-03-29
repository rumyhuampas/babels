﻿using System;
using System.Threading;
using MySQLDriverCS;
using BabelsPrinter.Properties;
using BabelsPrinter.Resolvers;

namespace BabelsPrinter
{
    public class PrintJobWatcher
    {
        private PrintJobResolver JobResolver;
        private MySQLConnection Conn;
        private Boolean active;
        private Boolean watching;

        public PrintJobWatcher()
        {
            Conn = PrinterService.GetDBConn();
            JobResolver = new PrintJobResolver();
        }

        public void StartWatching(object obj){
            try
            {
                active = true;
                watching = true;
                Logger.Log(Logger.MT_INFO, "Starting job watcher", Settings.Default.LogLevel >= 4);
                while (active)
                {
                    PrintJob job = GetPendingPrintJob();
                    if (job != null)
                    {
                        JobResolver.ProcessJob(job);
                        Thread.Sleep(3000);
                    }
                    else
                    {
                        Thread.Sleep(1000);
                    }
                }
                watching = false;
            }
            catch (Exception ex)
            {
                Logger.Log(Logger.MT_ERROR, "Error while watching printing jobs. Error: " + ex.Message, Settings.Default.LogLevel >= 3);
                throw ex;
            }
            finally
            {
                Conn.Close();
            }
        }

        public void StopWatching()
        {
            active = false;
            int waiting = 0;
            while (waiting <= 5)
            {
                if (watching == false)
                {
                    waiting = 5;
                }
                else
                {
                    waiting++;
                    //Thread.Sleep(1000);
                }
            }
        }

        private PrintJob GetPendingPrintJob()
        {
            PrintJob result = null;
            string sql = "SELECT * FROM " + PrintJob.TABLENAME +
                " WHERE " + PrintJob.FIELD_STATUS + "= '" + PrintJob.ST_PEND + "'" +
                " AND " + PrintJob.FIELD_PRINTER + " IN (" + Printers.GetPrinterList() + ")";
            MySQLCommand comm = new MySQLCommand(sql, Conn);
            int jobID = -1;
            try
            {
                MySQLDataReader reader = comm.ExecuteReaderEx();
                while (reader.Read())
                {
                    jobID = reader.GetInt32(reader.GetOrdinal(PrintJob.FIELD_ID));
                    if (ReserveJob(jobID) == true)
                    {
                        result = new PrintJob();
                        result.Id = jobID;
                        Movement move = new Movement(Conn);
                        move.Load(reader.GetInt32(reader.GetOrdinal(PrintJob.FIELD_IDMOVE)));
                        result.Move = move;
                        result.DatePosted = reader.GetDateTime(reader.GetOrdinal(PrintJob.FIELD_DATEPOSTED));
                        result.Status = reader.GetString(reader.GetOrdinal(PrintJob.FIELD_STATUS));
                        result.Printer = reader.GetString(reader.GetOrdinal(PrintJob.FIELD_PRINTER));
                        result.Retries = reader.GetInt32(reader.GetOrdinal(PrintJob.FIELD_RETRIES));
                        result.Data = reader.GetString(reader.GetOrdinal(PrintJob.FIELD_DATA));
                        break;
                    }
                    else
                    {
                        Logger.Log(Logger.MT_WARNING, "Could not reserve job: " + jobID.ToString(), Settings.Default.LogLevel >= 4);
                    }
                }
                return result;
            }
            catch (Exception ex)
            {
                Logger.Log(Logger.MT_WARNING, "Error reserving job: " + jobID.ToString() + ". Error: " + ex.Message, Settings.Default.LogLevel >= 4);
                return null;
            }
            finally
            {
                comm.Dispose();
            }
        }

        private bool ReserveJob(int JobID)
        {
            Logger.Log(Logger.MT_INFO, "Reserving job: " + JobID.ToString(), Settings.Default.LogLevel >= 4);

            string sql = "UPDATE " + PrintJob.TABLENAME + " SET " + 
                PrintJob.FIELD_STATUS + " = '" + PrintJob.ST_PRIN + "'" +
                " WHERE " + PrintJob.FIELD_ID + " = " + JobID;
            MySQLCommand comm = new MySQLCommand(sql, Conn);
            try
            {
                return comm.ExecuteNonQuery() > 0;
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
    }
}
