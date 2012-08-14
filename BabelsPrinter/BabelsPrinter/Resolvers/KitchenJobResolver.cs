using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using BabelsPrinter.Properties;
using BabelsPrinter.Interfaces;
using System.Drawing.Printing;

namespace BabelsPrinter
{
    public class KitchenJobResolver : IJobResolver
    {
        public KitchenJobResolver()
        {
            
        }

        public void ProcessJob(PrintJob job)
        {
            Logger.Log(Logger.MT_INFO, "Processing kitchen job: " + job.Id.ToString(), Settings.Default.LogLevel >= 4);
            try
            {
                KitchenReport kr = new KitchenReport();
                PrinterSettings  ps = new PrinterSettings();
                ps.Copies = 1;
                PageSettings pageSett = new PageSettings();
                pageSett.PrinterSettings = ps;
                kr.PrintToPrinter(ps, pageSett, false);
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }
    }
}
