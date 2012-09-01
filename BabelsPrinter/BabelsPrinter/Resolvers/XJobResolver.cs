using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using BabelsPrinter.Interfaces;
using BabelsPrinter.Helpers;
using BabelsPrinter.Properties;
using System.Drawing.Printing;

namespace BabelsPrinter.Resolvers
{
    public class XJobResolver : IJobResolver
    {
        private XPrintHelper helper;
        private PrintJob Job;

        public XJobResolver() { }

        public void ProcessJob(PrintJob job)
        {
            Logger.Log(Logger.MT_INFO, "Processing X job: " + job.Id.ToString(), Settings.Default.LogLevel >= 4);
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
            doc.DocumentName = "Babels X Print";
            PrinterSettings PS = new PrinterSettings();
            PS.Copies = 1;
            PS.PrinterName = Settings.Default.PrinterName;
            doc.PrinterSettings = PS;
            doc.Print();
        }

        void doc_PrintPage(object sender, PrintPageEventArgs e)
        {
            string kitchenID = Job.Printer.Substring(Job.Printer.IndexOf("_") + 1, Job.Printer.Length - (Job.Printer.IndexOf("_") + 1));

            helper = new XPrintHelper(e);

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
            helper.DrawText("CANTIDAD/PRECIO UNIT(%IVA)");
            helper.DrawNoFiscal();
            helper.DrawText("DESCRIPCION        IMPORTE");
            helper.DrawLine();

            helper.AddWhiteSpace();

            helper.DrawJobItems(Job);

            helper.AddWhiteSpace();

            helper.DrawJobTotal(Job);

            helper.AddWhiteSpace();
            helper.DrawNoFiscal();
        }
    }
}
