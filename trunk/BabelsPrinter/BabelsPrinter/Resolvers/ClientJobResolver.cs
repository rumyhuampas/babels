using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using BabelsPrinter.Interfaces;
using BabelsPrinter.Helpers;
using BabelsPrinter.Properties;
using System.Drawing.Printing;
using BabelsPrinter.Model;

namespace BabelsPrinter.Resolvers
{
    public class ClientJobResolver : IJobResolver
    {
        private ClientPrintHelper helper;
        private PrintJob Job;

        public ClientJobResolver() { }

        public void ProcessJob(PrintJob job)
        {
            Logger.Log(Logger.MT_INFO, "Processing Client job: " + job.Id.ToString(), Settings.Default.LogLevel >= 4);
            try
            {
                Job = job;
                Print();
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        private void Print()
        {
            PrintDocument doc = new PrintDocument();
            doc.PrintPage += new PrintPageEventHandler(doc_PrintPage);
            doc.DocumentName = "Babels Client Print";
            PrinterSettings PS = new PrinterSettings();
            PS.Copies = 1;
            PS.PrinterName = Settings.Default.PrinterName;
            doc.PrinterSettings = PS;
            doc.Print();
        }

        void doc_PrintPage(object sender, PrintPageEventArgs e)
        {
            Client client = new Client(PrinterService.GetDBConn());
            client.Load(Convert.ToInt32(Job.Data));

            helper = new ClientPrintHelper(e);

            helper.DrawText("** NO VALIDO COMO COMPROBANTE **");
            helper.AddWhiteSpace();
            helper.DrawNoFiscal();
            helper.AddWhiteSpace();
            helper.DrawNoFiscal();

            helper.AddWhiteSpace();

            helper.DrawLine();
            helper.DrawText("FECHA: " + DateTime.Now.ToShortDateString() + "    HORA: " + DateTime.Now.ToShortTimeString());
            helper.DrawLine();

            helper.AddWhiteSpace();

            helper.DrawLine();
            helper.DrawText("NOMBRE: " + client.Name);
            helper.DrawText("DIRECCION: " + client.Address);
            helper.DrawText("TELEFONO 1: " + client.Phone1);
            helper.DrawText("TELEFONO 2: " + client.Phone2);
            helper.DrawLine();

            helper.AddWhiteSpace();
            helper.DrawNoFiscal();
        }
    }
}
