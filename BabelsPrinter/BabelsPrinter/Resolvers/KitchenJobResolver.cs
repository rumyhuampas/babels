using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using BabelsPrinter.Properties;
using BabelsPrinter.Interfaces;
using System.Drawing.Printing;
using System.Drawing;
using BabelsPrinter.Model;

namespace BabelsPrinter.Resolvers
{
    public class KitchenJobResolver : IJobResolver
    {
        private KitchenPrintHelper helper;
        private PrintJob Job;

        public KitchenJobResolver() { }

        public void ProcessJob(PrintJob job)
        {
            Logger.Log(Logger.MT_INFO, "Processing kitchen job: " + job.Id.ToString(), Settings.Default.LogLevel >= 4);
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
            doc.DocumentName = "Babels Kitchen Print";
            PrinterSettings PS = new PrinterSettings();
            PS.Copies = 1;
            PS.PrinterName = Settings.Default.PrinterName;
            doc.PrinterSettings = PS;
            doc.Print();
        }

        void doc_PrintPage(object sender, PrintPageEventArgs e)
        {
            string kitchenID = Job.Printer.Substring(Job.Printer.IndexOf("_") + 1, Job.Printer.Length - (Job.Printer.IndexOf("_") + 1));

            helper = new KitchenPrintHelper(e);
            helper.DrawLogo();
            helper.DrawTitle("Babels - Cocina: " + kitchenID);
            helper.DrawJobInfo(Job);
            helper.DrawJobItems(Job);
        }
    }
}
